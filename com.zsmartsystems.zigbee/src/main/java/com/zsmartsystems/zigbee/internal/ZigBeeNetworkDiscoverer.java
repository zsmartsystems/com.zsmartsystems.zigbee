/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeAnnounceListener;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressResponse;

/**
 * {@link ZigBeeNetworkDiscoverer} is used to discover devices in the network.
 * <p>
 * Notifications will be sent to the listeners when nodes and endpoints are discovered.
 * Device listeners are always notified first as each endpoint discovery completes.
 * Once a node is fully discovered and all its endpoints are included into the network,
 * we can notify the node listeners.
 *
 * @author Chris Jackson
 */
public class ZigBeeNetworkDiscoverer
        implements ZigBeeCommandListener, ZigBeeAnnounceListener, ZigBeeNetworkStateListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkDiscoverer.class);

    /**
     * Default maximum number of retries to perform
     */
    private final int DEFAULT_MAX_RETRY_COUNT = 3;

    /**
     * Default period between retries
     */
    private final int DEFAULT_RETRY_PERIOD = 1500;

    /**
     * Default minimum time before information can be queried again for same network address or endpoint.
     */
    private final int DEFAULT_REQUERY_TIME = 300000;

    /**
     * The ZigBee network manager.
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * Period between retries
     */
    private int retryPeriod = DEFAULT_RETRY_PERIOD;

    /**
     * Period between retries
     */
    private int retryCount = DEFAULT_MAX_RETRY_COUNT;

    /**
     * The minimum time before performing a requery
     */
    private int requeryPeriod = DEFAULT_REQUERY_TIME;

    /**
     * Map of node discovery times.
     */
    private final Map<Integer, Long> discoveryStartTime = Collections
            .synchronizedMap(new ConcurrentHashMap<Integer, Long>());

    /**
     * Flag used to initialise the discoverer once the network is ONLINE
     */
    private boolean initialized = false;

    /**
     * Discovers ZigBee network state.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     */
    public ZigBeeNetworkDiscoverer(final ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;

    }

    /**
     * Starts up ZigBee network discoverer. This adds a listener to wait for the network to go online.
     */
    public void startup() {
        logger.debug("Network discovery task starting");
        networkManager.addNetworkStateListener(this);
    }

    /**
     * Shuts down ZigBee network discoverer.
     */
    public void shutdown() {
        logger.debug("Network discovery task shutdown");
        networkManager.removeCommandListener(this);
        networkManager.removeAnnounceListener(this);
        networkManager.removeNetworkStateListener(this);
    }

    /**
     * Sets the retry period in milliseconds. This is the amount of time the service will wait following a failed
     * request before performing a retry.
     *
     * @param retryPeriod the period in milliseconds between retries
     */
    public void setRetryPeriod(int retryPeriod) {
        this.retryPeriod = retryPeriod;
    }

    /**
     * Sets the maximum number of retries the service will perform at any stage before failing.
     *
     * @param retryCount the maximum number of retries.
     */
    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    /**
     * Sets the minimum period between requeries on each node
     *
     * @param requeryPeriod the requery period in milliseconds
     */
    public void setRequeryPeriod(int requeryPeriod) {
        this.requeryPeriod = requeryPeriod;
    }

    @Override
    public void deviceStatusUpdate(final ZigBeeNodeStatus deviceStatus, final Integer networkAddress,
            final IeeeAddress ieeeAddress) {
        switch (deviceStatus) {
            case UNSECURED_JOIN:
            case SECURED_REJOIN:
            case UNSECURED_REJOIN:
                // We only care about devices that have joined or rejoined
                // startNodeDiscovery(networkAddress);
                addNode(ieeeAddress, networkAddress);
                break;
            default:
                break;
        }
    }

    @Override
    public void commandReceived(final ZigBeeCommand command) {
        // ZCL command received from remote node. Perform discovery if it is not yet known.
        if (command instanceof ZclCommand) {
            final ZclCommand zclCommand = (ZclCommand) command;
            if (networkManager.getNode(zclCommand.getSourceAddress().getAddress()) == null) {
                // TODO: Protect against group address?
                ZigBeeEndpointAddress address = (ZigBeeEndpointAddress) zclCommand.getSourceAddress();
                startNodeDiscovery(address.getAddress());
            }
        }

        // Node has been announced.
        if (command instanceof DeviceAnnounce) {
            final DeviceAnnounce announce = (DeviceAnnounce) command;
            // startNodeDiscovery(address.getNwkAddrOfInterest());

            addNode(announce.getIeeeAddr(), announce.getNwkAddrOfInterest());
        }
    }

    /**
     * Starts a discovery on a node.
     *
     * @param nodeAddress the network address of the node to discover
     */
    public void rediscoverNode(final int nodeAddress) {
        startNodeDiscovery(nodeAddress);
    }

    /**
     * Starts a discovery on a node. This will send a {@link NetworkAddressRequest} as a broadcast and will receive
     * the response to trigger a full discovery.
     *
     * @param ieeeAddress the {@link IeeeAddress} of the node to discover
     */
    public void rediscoverNode(final IeeeAddress ieeeAddress) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logger.debug("{}: NWK Discovery starting node rediscovery", ieeeAddress);
                int retries = 0;
                try {
                    do {
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }

                        NetworkAddressRequest request = new NetworkAddressRequest();
                        request.setIeeeAddr(ieeeAddress);
                        request.setRequestType(0);
                        request.setStartIndex(0);
                        request.setDestinationAddress(
                                new ZigBeeEndpointAddress(ZigBeeBroadcastDestination.BROADCAST_RX_ON.getKey()));
                        CommandResult response;
                        response = networkManager.unicast(request, request).get();

                        final NetworkAddressResponse nwkAddressResponse = response.getResponse();
                        if (nwkAddressResponse != null && nwkAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                            startNodeDiscovery(nwkAddressResponse.getNwkAddrRemoteDev());
                            break;
                        }

                        // We failed with the last request. Wait a bit then retry
                        try {
                            logger.debug("{}: NWK Discovery node rediscovery request failed. Wait before retry.",
                                    ieeeAddress);
                            Thread.sleep(retryPeriod);
                        } catch (InterruptedException e) {
                            break;
                        }
                    } while (retries++ < retryCount);
                } catch (InterruptedException | ExecutionException e) {
                    logger.debug("NWK Discovery error in rediscoverNode ", e);
                }
                logger.debug("{}: NWK Discovery finishing node rediscovery", ieeeAddress);
            }
        };

        networkManager.executeTask(runnable);
    }

    /**
     * Performs the top level node discovery. This discovers node level attributes such as the endpoints and
     * descriptors.
     *
     * @param networkAddress the network address to start a discovery on
     */
    private void startNodeDiscovery(final int nodeNetworkAddress) {
        // Check if we need to do a rediscovery on this node first...
        synchronized (discoveryStartTime) {
            if (discoveryStartTime.get(nodeNetworkAddress) != null
                    && System.currentTimeMillis() - discoveryStartTime.get(nodeNetworkAddress) < requeryPeriod) {
                logger.trace("{}: NWK Discovery node discovery already in progress", nodeNetworkAddress);
                return;
            }
            discoveryStartTime.put(nodeNetworkAddress, System.currentTimeMillis());
        }

        logger.debug("{}: NWK Discovery scheduling node discovery", nodeNetworkAddress);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    logger.debug("{}: NWK Discovery starting node discovery", nodeNetworkAddress);
                    int retries = 0;
                    boolean success;
                    do {
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }

                        success = getIeeeAddress(nodeNetworkAddress);

                        if (success) {
                            break;
                        }

                        // We failed with the last request. Wait a bit then retry
                        try {
                            Thread.sleep(retryPeriod);
                        } catch (InterruptedException e) {
                            break;
                        }
                    } while (retries++ < retryCount);

                    logger.debug("{}: NWK Discovery ending node discovery", nodeNetworkAddress);
                } catch (Exception e) {
                    logger.error("{}: NWK Discovery error during node discovery: ", nodeNetworkAddress, e);
                }
            }
        };

        networkManager.executeTask(runnable);
    }

    /**
     * Get Node IEEE address
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     */
    private boolean getIeeeAddress(final int networkAddress) {
        try {
            Integer startIndex = 0;
            int totalAssociatedDevices = 0;
            Set<Integer> associatedDevices = new HashSet<Integer>();
            IeeeAddress ieeeAddress = null;

            do {
                // Request extended response, start index for associated list is 0
                final IeeeAddressRequest ieeeAddressRequest = new IeeeAddressRequest();
                ieeeAddressRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
                ieeeAddressRequest.setRequestType(1);
                ieeeAddressRequest.setStartIndex(startIndex);
                ieeeAddressRequest.setNwkAddrOfInterest(networkAddress);
                CommandResult response = networkManager.unicast(ieeeAddressRequest, ieeeAddressRequest).get();
                if (response.isError()) {
                    return false;
                }

                final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
                logger.debug("{}: NWK Discovery IeeeAddressRequest returned {}", networkAddress, ieeeAddressResponse);
                if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                    ieeeAddress = ieeeAddressResponse.getIeeeAddrRemoteDev();
                    if (startIndex.equals(ieeeAddressResponse.getStartIndex())) {
                        associatedDevices.addAll(ieeeAddressResponse.getNwkAddrAssocDevList());

                        startIndex += ieeeAddressResponse.getNwkAddrAssocDevList().size();
                        totalAssociatedDevices = ieeeAddressResponse.getNwkAddrAssocDevList().size();
                    }
                }

            } while (startIndex < totalAssociatedDevices);

            addNode(ieeeAddress, networkAddress);

            // Start discovery for any associated nodes
            for (final int deviceNetworkAddress : associatedDevices) {
                startNodeDiscovery(deviceNetworkAddress);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("NWK Discovery Error in checkIeeeAddressResponse ", e);
        }

        return true;
    }

    /**
     * Updates {@link ZigBeeNode} and completes discovery if all devices are discovered in
     * this node.
     *
     * @param node the {@link ZigBeeNode} to add
     */
    private void addNode(final IeeeAddress ieeeAddress, int networkAddress) {
        ZigBeeNode node = networkManager.getNode(ieeeAddress);
        if (node != null) {
            node.setNetworkAddress(networkAddress);
            return;
        }

        node = new ZigBeeNode(networkManager, ieeeAddress);
        node.setNetworkAddress(networkAddress);

        // Add the node to the network...
        networkManager.addNode(node);
    }

    @Override
    public void networkStateUpdated(ZigBeeTransportState state) {
        if (state == ZigBeeTransportState.ONLINE && !initialized) {
            initialized = true;

            networkManager.addCommandListener(this);
            networkManager.addAnnounceListener(this);

            // Start discovery from root node.
            startNodeDiscovery(0);
        }
    }
}
