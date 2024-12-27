/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.time;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zcl.clusters.ZclTimeCluster;
import com.zsmartsystems.zigbee.zcl.field.ZigBeeUtcTime;

/**
 * Application to centrally manage the network time. This will keep all times on the network synchronised to the time on
 * the local computer.
 * <p>
 * A single instance of {@link ZclTimeServer} is created to act as a local time server. This server manages requests
 * from remote clients that may want to update their time.
 * <p>
 * An instance of {@link ZclTimeClient} is created for each node on the network that has a time server. The
 * {@link ZclTimeClient} will periodically poll the time on the remote server, update the time when it is above the
 * maximum allowed delta. The client will keep track of the time drift seen in a 24 hour period.
 * <p>
 * Management of both {@link ZclTimeClient} and {@link ZclTimeServer} are handled through the
 * {@link ZigBeeTimeExtension}. This class consolidates the client and server requests in the event that a device
 * supports both, and ensures that time, and time configuration (eg Daylight Saving Time) is managed centrally and
 * consistently.
 * <p>
 * It should be noted that a single thread is used to keep resource usage to a minimum. The updates are therefore
 * managed directly in the {@link ZigBeeTimeExtension} class.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeTimeExtension implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeTimeExtension.class);

    /**
     * The default time (in seconds) between each poll of the time on a remote device
     */
    private final static int DEFAULT_POLL_PERIOD = 3600;

    private final static long MINIMUM_WORKER_PERIOD = 30;

    private ZigBeeNetworkManager networkManager;

    /**
     * Our time server that handles any requests from remote clients
     */
    private ZclTimeServer localTimeServer;

    private ZigBeeUtcTime dstStart;
    private ZigBeeUtcTime dstEnd;
    private int dstOffset;

    private boolean master = true;
    private boolean superceding = false;

    /**
     * The maximum allowed time delta (in seconds) after when the time will be updated by the client.
     */
    private int updateDelta = 25;

    /**
     * The approximate number of seconds between each time request to each remote server. The time is requested
     * periodically so that the system can ensure it remains within the value set by {@link #setUpdateDelta(int)}.
     */
    private int pollPeriod = DEFAULT_POLL_PERIOD;

    /**
     * The worker thread that is scheduled periodically to check the synchronisation of remote devices
     */
    private ScheduledFuture<?> workerJob;
    /**
     * A map of all the time clients on the network
     */
    private final Map<ZigBeeEndpointAddress, ZclTimeClient> timeClients = new ConcurrentHashMap<>();

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
        networkManager.addSupportedClientCluster(ZclTimeCluster.CLUSTER_ID);
        networkManager.addSupportedServerCluster(ZclTimeCluster.CLUSTER_ID);
        networkManager.addNetworkNodeListener(this);

        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus extensionStartup() {
        logger.debug("Time extension: startup");

        localTimeServer = new ZclTimeServer(networkManager);
        localTimeServer.setMaster(master);
        localTimeServer.setSuperceding(superceding);

        startWorker();
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void extensionShutdown() {
        networkManager.removeNetworkNodeListener(this);

        if (localTimeServer != null) {
            localTimeServer.shutdown();
        }

        logger.debug("Time extension: shutdown");
    }

    /**
     * Enables or disables the master flag. When set, we publish that our server is internally set to a time standard.
     *
     * @param master true if this is a master clock source.
     */
    public void setMaster(boolean master) {
        this.master = master;
        if (localTimeServer != null) {
            localTimeServer.setMaster(master);
        }
    }

    /**
     * Enables or disables the superceding flag. When set, we publish that our server is superceding other masters.
     *
     * @param master true if this is a master clock source.
     */
    public void setSuperceding(boolean superceding) {
        this.superceding = superceding;
        if (localTimeServer != null) {
            localTimeServer.setSuperceding(superceding);
        }
    }

    /**
     * Returns true if the server is a master clock source.
     *
     * @return true if the server is a master clock source.
     */
    public boolean isMaster() {
        return localTimeServer.isMaster();
    }

    /**
     * Returns true of the server is marked as a superceding server.
     *
     * @return true of the server is marked as a superceding server.
     */
    public boolean isSuperceding() {
        return localTimeServer.isSuperceding();
    }

    /**
     * Set the daylight savings time parameters, including the start and end of daylight saving, and the offset. This
     * will be passed on to all remote nodes at the next update.
     *
     * @param dstStart an {@link ZigBeeUtcTime} defining the time daylight savings time starts
     * @param dstEnd an {@link ZigBeeUtcTime} defining the time daylight savings time ends
     * @param dstOffset the number of seconds that daylight savings is adjusted
     */
    public void setDst(ZigBeeUtcTime dstStart, ZigBeeUtcTime dstEnd, int dstOffset) {
        this.dstStart = dstStart;
        this.dstEnd = dstEnd;
        this.dstOffset = dstOffset;

        logger.debug("Time extension: set DST start={}, end={}, offset={}", dstStart, dstEnd, dstOffset);

        localTimeServer.setDst(dstStart, dstEnd, dstOffset);

        // Update all the clients
        synchronized (timeClients) {
            for (ZclTimeClient client : timeClients.values()) {
                client.setDst(dstStart, dstEnd, dstOffset);
            }
        }
    }

    /**
     * Gets the last update time by either the client or the server. This may be used in conjunction with
     * {@link #getLastUpdateMethod(ZigBeeEndpointAddress)}.
     *
     * @param address the {@link ZigBeeEndpointAddress} of the remote device.
     * @return an {@link ZigBeeUtcTime} with the last update time or null if no update has been made to the requested
     *         address.
     */
    public ZigBeeUtcTime getLastUpdateTime(ZigBeeEndpointAddress address) {
        switch (getLastUpdateMethod(address)) {
            case CLIENT:
                return timeClients.get(address).getLastUpdate();
            case SERVER:
                return localTimeServer.getLastUpdateTime(address.getAddress());
            case NONE:
            default:
                return null;
        }
    }

    /**
     * Gets the {@link ZigBeeTimeSetMethod} by which the time was last set on the remote device. This may be used in
     * conjunction with {@link #getLastUpdateTime(ZigBeeEndpointAddress).
     *
     * @param address the {@link ZigBeeEndpointAddress} of the remote device.
     * @return the {@link ZigBeeTimeSetMethod} by which the time was last set on the remote device.
     */
    public ZigBeeTimeSetMethod getLastUpdateMethod(ZigBeeEndpointAddress address) {
        ZigBeeUtcTime clientTime = null;
        ZigBeeUtcTime serverTime = localTimeServer.getLastUpdateTime(address.getAddress());

        ZclTimeClient client = timeClients.get(address);
        if (client != null) {
            clientTime = client.getLastUpdate();
        }

        if (clientTime == null && serverTime == null) {
            return ZigBeeTimeSetMethod.NONE;
        } else if (serverTime == null) {
            return ZigBeeTimeSetMethod.CLIENT;
        } else {
            return clientTime.compareTo(serverTime) < 0 ? ZigBeeTimeSetMethod.SERVER : ZigBeeTimeSetMethod.CLIENT;
        }
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            ZclTimeCluster timeServer = (ZclTimeCluster) endpoint.getInputCluster(ZclTimeCluster.CLUSTER_ID);
            if (timeServer != null && !timeClients.containsKey(timeServer.getZigBeeAddress())) {
                // Create a new local client for the remote server
                ZclTimeClient timeClient = new ZclTimeClient(timeServer);
                synchronized (timeClients) {
                    logger.debug("Time extension: Adding client for {} endpoint {}", node.getIeeeAddress(),
                            timeServer.getZigBeeAddress());
                    timeClients.put(timeServer.getZigBeeAddress(), timeClient);
                }
            }
            ZclTimeCluster timeClient = (ZclTimeCluster) endpoint.getOutputCluster(ZclTimeCluster.CLUSTER_ID);
            if (timeClient != null) {
                localTimeServer.addRemote(timeClient);
            }
        }

        startWorker();
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        // Not used
    }

    @Override
    public void nodeRemoved(ZigBeeNode node) {
        synchronized (timeClients) {
            for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                if (timeClients.remove(endpoint.getEndpointAddress()) != null) {
                    logger.debug("Time extension: Removed client for {} endpoint {}", node.getIeeeAddress(),
                            endpoint.getEndpointAddress());
                }
            }
        }
    }

    /**
     * Gets the collection of {@link ZclTimeClient}s known on the network
     *
     * @return collection of {@link ZclTimeClient}s known on the network
     */
    public Collection<ZclTimeClient> getTimeClients() {
        return timeClients.values();
    }

    /**
     * Gets the {@link ZclTimeClient} for the {@link ZigBeeEndpointAddress}
     *
     * @param endpointAddress the {@link ZigBeeEndpointAddress} of the client
     * @return the {@link ZclTimeClient} for the client, or null if there is no time client set
     */
    public ZclTimeClient getTimeClient(ZigBeeEndpointAddress endpointAddress) {
        return timeClients.get(endpointAddress);
    }

    /**
     * @return the dstStart time as {@link ZigBeeUtcTime}
     */
    public ZigBeeUtcTime getDstStart() {
        return dstStart;
    }

    /**
     * @return the dstEnd time as {@link ZigBeeUtcTime}
     */
    public ZigBeeUtcTime getDstEnd() {
        return dstEnd;
    }

    /**
     * @return the dstOffset in seconds
     */
    public int getDstOffset() {
        return dstOffset;
    }

    /**
     * Gets the current allowable clock drift, in seconds, before the client will update the remote server
     *
     * @return the current allowable drift in seconds
     */
    public int getUpdateDelta() {
        return updateDelta;
    }

    /**
     * Sets the current allowable clock drift, in seconds, before the client will update the remote server.
     * If a remote time is observed to be more than his number of seconds from the local time, it will be synchronised.
     *
     * @param updateDelta the allowable drift in seconds
     */
    public void setUpdateDelta(int updateDelta) {
        this.updateDelta = updateDelta;
    }

    /**
     * @return the nominal period between each request of the time on a remote device
     */
    public int getPollPeriod() {
        return pollPeriod;
    }

    /**
     * @param pollPeriod the nominal period between each request of the time on a remote device
     */
    public void setPollPeriod(int pollPeriod) {
        this.pollPeriod = pollPeriod;
    }

    private ZclTimeClient getNextUpdateClient() {
        ZigBeeUtcTime pollTime = ZigBeeUtcTime.ofEpochSecond(System.currentTimeMillis() / 1000 - pollPeriod);

        synchronized (timeClients) {
            ZclTimeClient updateClient = null;
            for (ZclTimeClient client : timeClients.values()) {
                if (client.getLastRequestedTime() == null || client.getLastRequestedTime().isBefore(pollTime)
                        && (updateClient == null || updateClient.getLastRequestedTime() == null
                                || updateClient.getLastRequestedTime().isAfter(client.getLastRequestedTime()))) {
                    updateClient = client;
                }
            }
            return updateClient;
        }
    }

    private void startWorker() {
        if (workerJob != null) {
            workerJob.cancel(true);
            workerJob = null;
        }

        ZclTimeClient client = getNextUpdateClient();
        if (client == null) {
            return;
        }

        long updateSeconds = (client.getLastRequestedTime() == null) ? MINIMUM_WORKER_PERIOD
                : Math.max(client.getLastRequestedTime().getEpochSecond() - ZigBeeUtcTime.now().getEpochSecond(),
                        MINIMUM_WORKER_PERIOD);
        logger.debug("Time extension: schedule next worker in {} seconds", updateSeconds);

        workerJob = networkManager.scheduleTask(new TimeWorkerThread(), updateSeconds * 1000);
    }

    /**
     * Class implementing the {@link Runnable} that will perform the time synchronisation work
     */
    private class TimeWorkerThread implements Runnable {
        @Override
        public void run() {
            try {
                doTimePollAndSet();
            } catch (InterruptedException | ExecutionException e) {
                logger.debug("Time extension: worker exception", e);
            }

            startWorker();
        }

        private void doTimePollAndSet() throws InterruptedException, ExecutionException {
            ZclTimeClient client = getNextUpdateClient();
            if (client == null) {
                logger.debug("Time extension: worker running - no client found", client);
                startWorker();
            }

            logger.debug("Time extension: worker polling {}", client.getZigBeeAddress());
            if (!client.doPoll()) {
                // Poll failed
                startWorker();
            }

            if (Math.abs(client.getCurrentDelta()) >= updateDelta) {
                logger.debug("Time extension: worker updating {}, delta is {}", client.getZigBeeAddress(),
                        client.getCurrentDelta());
                client.doUpdate();
            }
        }
    }
}
