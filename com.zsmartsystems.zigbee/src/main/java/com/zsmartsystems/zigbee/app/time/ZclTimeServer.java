/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.time;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.clusters.ZclTimeCluster;
import com.zsmartsystems.zigbee.zcl.clusters.time.TimeStatusBitmap;
import com.zsmartsystems.zigbee.zcl.field.ZigBeeUtcTime;

/**
 * Local time server. This allows any clients on the network to read time as required.
 *
 * @author Chris Jackson
 *
 */
public class ZclTimeServer {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclTimeServer.class);

    /**
     * True if we are a time master server on the network
     */
    private boolean master = true;

    /**
     * True if we are a superceding time server on the network
     */
    private boolean superceding = true;

    private ZigBeeUtcTime dstStart;
    private ZigBeeUtcTime dstEnd;
    private int dstOffset;
    private boolean dstSet = false;

    private int timezoneOffset = 0;

    private final ZigBeeNetworkManager networkManager;

    /**
     * Map holding the last update times for each remote
     */
    private final Map<Integer, ZigBeeUtcTime> lastUpdate = new HashMap<>();

    /**
     * The worker thread that is scheduled periodically to check the synchronisation of remote devices
     */
    private ScheduledFuture<?> workerJob;

    protected ZclTimeServer(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
        scheduleTimeUpdate();
    }

    /**
     * Shuts down the time server and frees any resources
     */
    protected void shutdown() {
        if (workerJob != null) {
            workerJob.cancel(true);
            workerJob = null;
        }
    }

    /**
     * Gets the time the server last set the time on the requested client
     *
     * @param address the address of the remote client
     * @return the {@link ZigBeeUtcTime} the time was last provided to the client, or null if it has not been set.
     */
    public ZigBeeUtcTime getLastUpdateTime(int address) {
        return lastUpdate.get(address);
    }

    /**
     * Enables or disables the master flag. When set, we publish that our server is internally set to a time standard.
     *
     * @param master true if this is a master clock source.
     */
    protected void setMaster(boolean master) {
        this.master = master;
        setTimeStatusAttribute();
    }

    /**
     * Returns true if the server is a master clock source.
     *
     * @return true if the server is a master clock source.
     */
    protected boolean isMaster() {
        return master;
    }

    /**
     * Enables or disables the superceding flag. When set, we publish that our server is superceding other masters.
     *
     * @param master true if this is a master clock source.
     */

    protected void setSuperceding(boolean superceding) {
        this.superceding = superceding;
        setTimeStatusAttribute();

    }

    /**
     * Returns true of the server is marked as a superceding server.
     *
     * @return true of the server is marked as a superceding server.
     */
    protected boolean isSuperceding() {
        return superceding;
    }

    protected void setTimezone(int timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
        updateEndpointAttribute(ZclTimeCluster.ATTR_TIMEZONE, this.timezoneOffset);

    }

    /**
     * Set the daylight savings time parameters, including the start and end of daylight saving, and the offset. This
     * will be passed on to all remote nodes at the next update.
     *
     * @param dstStart an {@link ZigBeeUtcTime} defining the time daylight savings time starts
     * @param dstEnd an {@link ZigBeeUtcTime} defining the time daylight savings time ends
     * @param dstOffset the number of seconds that daylight savings is adjusted
     */
    protected void setDst(ZigBeeUtcTime dstStart, ZigBeeUtcTime dstEnd, int dstOffset) {
        this.dstStart = dstStart;
        this.dstEnd = dstEnd;
        this.dstOffset = dstOffset;
        dstSet = true;
        setTimeStatusAttribute();
        updateEndpointAttribute(ZclTimeCluster.ATTR_DSTSTART, this.dstStart);
        updateEndpointAttribute(ZclTimeCluster.ATTR_DSTEND, this.dstEnd);
        updateEndpointAttribute(ZclTimeCluster.ATTR_DSTSTART, this.dstStart);
        updateEndpointAttribute(ZclTimeCluster.ATTR_DSTSHIFT, this.dstOffset); // TODO Only if DST active
    }

    /**
     * Adds a remote client to be managed by the local server
     *
     * @param timeClient the remote client
     */
    protected void addRemote(ZclTimeCluster timeClient) {
        timeClient.addCommandListener(this);
    }

    /**
     * Removes a remote client to be managed by the local server
     *
     * @param timeClient the remote client
     */
    protected void removeRemote(ZclTimeCluster timeClient) {
        timeClient.removeCommandListener(this);
    }

    private void setTimeStatusAttribute() {
        int status = TimeStatusBitmap.SYNCHRONIZED.getKey();
        status |= master ? TimeStatusBitmap.MASTER.getKey() : 0x0;
        status |= superceding ? TimeStatusBitmap.SUPERSEDING.getKey() : 0x0;
        status |= dstSet ? TimeStatusBitmap.MASTER_ZONE_DST.getKey() : 0x0;

        updateEndpointAttribute(ZclTimeCluster.ATTR_TIMESTATUS, status);
    }

    private void updateEndpointAttribute(int attributeId, Object attributeValue) {
        logger.debug("*********** UPDATE {} -> {}", attributeId, attributeValue);

        for (ZigBeeNode node : networkManager.getNodes()) {
            for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                ZclTimeCluster cluster = (ZclTimeCluster) endpoint.getOutputCluster(ZclTimeCluster.CLUSTER_ID);
                if (cluster != null) {
                    ZclAttribute attribute = cluster.getLocalAttribute(attributeId);
                    if (attribute == null) {
                        logger.debug("{}: Endpoint {}. Time Server Extension: Updating attribute {} UNSUPPORTED",
                                node.getIeeeAddress(), endpoint.getEndpointId(), attributeId);
                        continue;
                    }
                    attribute.setValue(attributeValue);
                    attribute.setImplemented(true);
                    logger.debug("{}: Endpoint {}. Time Server Extension: Updated attribute {}",
                            node.getIeeeAddress(), endpoint.getEndpointId(), attribute);
                }
            }
        }
    }

    private void scheduleTimeUpdate() {
        logger.debug("*********** SCHEDULE");
        workerJob = networkManager.scheduleTask(new TimeWorkerThread(), 1000, 1000);
    }

    /**
     * Class implementing the {@link Runnable} that will perform the time synchronisation work
     */
    private class TimeWorkerThread implements Runnable {
        @Override
        public void run() {
            updateEndpointAttribute(ZclTimeCluster.ATTR_TIME, ZigBeeUtcTime.now());
            updateEndpointAttribute(ZclTimeCluster.ATTR_STANDARDTIME, ZigBeeUtcTime.now()); // Standard Time = Time +
                                                                                            // TimeZone
            updateEndpointAttribute(ZclTimeCluster.ATTR_LOCALTIME, ZigBeeUtcTime.now()); // Local Time = Standard Time +
                                                                                         // DstShift
        }
    }

}
