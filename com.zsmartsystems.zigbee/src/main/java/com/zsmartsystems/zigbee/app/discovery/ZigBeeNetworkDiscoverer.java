/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

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
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
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
public class ZigBeeNetworkDiscoverer implements ZigBeeCommandListener, ZigBeeAnnounceListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkDiscoverer.class);

    /**
     * Default minimum time before information can be queried again for same network address or endpoint.
     */
    private final int DEFAULT_REQUERY_TIME = 3600000;

    /**
     * The ZigBee network manager.
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * The minimum time before performing a requery
     */
    private int requeryPeriod = DEFAULT_REQUERY_TIME;

    /**
     * Map of node discovery times.
     */
    private final Map<Integer, Long> discoveryStartTime = Collections.synchronizedMap(new ConcurrentHashMap<>());

    /**
     * Flag used to initialise the discoverer once the network is ONLINE
     */
    private boolean initialized = false;

    /**
     * Discovers ZigBee network state.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     */
    protected ZigBeeNetworkDiscoverer(final ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Starts up ZigBee network discoverer. This adds a listener to wait for the network to go online.
     */
    protected void startup() {
        logger.debug("Network discovery task: starting");

        initialized = true;

        networkManager.addCommandListener(this);
        networkManager.addAnnounceListener(this);

        // Start discovery from root node.
        startNodeDiscovery(0);
    }

    /**
     * Shuts down ZigBee network discoverer.
     */
    protected void shutdown() {
        logger.debug("Network discovery task: shutdown");
        networkManager.removeCommandListener(this);
        networkManager.removeAnnounceListener(this);
        initialized = false;
    }

    /**
     * Sets the retry period in milliseconds. This is the amount of time the service will wait following a failed
     * request before performing a retry.
     *
     * @param retryPeriod the period in milliseconds between retries
     * @deprecated Retires are handled in the transaction manager
     */
    @Deprecated
    protected void setRetryPeriod(int retryPeriod) {
    }

    /**
     * Sets the maximum number of retries the service will perform at any stage before failing.
     *
     * @param retryCount the maximum number of retries.
     * @deprecated Retires are handled in the transaction manager
     */
    @Deprecated
    protected void setRetryCount(int retryCount) {
    }

    /**
     * Sets the minimum period between requeries on each node
     *
     * @param requeryPeriod the requery period in milliseconds
     */
    protected void setRequeryPeriod(int requeryPeriod) {
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
                logger.debug("{}: Device status updated. NWK={}", ieeeAddress, String.format("%04X", networkAddress));
                addNode(ieeeAddress, networkAddress);
                break;
            default:
                break;
        }
    }

    @Override
    public void announceUnknownDevice(final Integer networkAddress) {
        startNodeDiscovery(networkAddress);
    }

    @Override
    public void commandReceived(final ZigBeeCommand command) {
        // Node has been announced.
        if (command instanceof DeviceAnnounce) {
            final DeviceAnnounce announce = (DeviceAnnounce) command;

            logger.debug("{}: Device announce received. From {}, for {}", announce.getIeeeAddr(),
                    String.format("%04X", announce.getSourceAddress().getAddress()),
                    String.format("%04X", announce.getNwkAddrOfInterest()));
            addNode(announce.getIeeeAddr(), announce.getNwkAddrOfInterest());
        }
    }

    /**
     * Starts a discovery on a node given the network address.
     *
     * @param networkAddress the network address of the node to discover
     */
    protected void rediscoverNode(final int networkAddress) {
        if (!initialized) {
            logger.debug("Network discovery task: can't perform rediscovery on {} until initialization complete.",
                    String.format("%04X", networkAddress));
            return;
        }

        networkManager.executeTask(new Runnable() {
            @Override
            public void run() {
                logger.debug("NWK Discovery starting node rediscovery {}", String.format("%04X", networkAddress));

                try {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }

                    IeeeAddressResponse ieeeAddressResponse = null;
                    logger.debug("NWK Discovery: Rediscovery of {} using unicast",
                            String.format("%04X", networkAddress));
                    IeeeAddressRequest request = new IeeeAddressRequest(networkAddress, 0, 0);
                    request.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
                    CommandResult response = networkManager.sendTransaction(request, request).get();
                    ieeeAddressResponse = response.getResponse();

                    if (ieeeAddressResponse == null) {
                        logger.debug("NWK Discovery: Rediscovery of {} using broadcast",
                                String.format("%04X", networkAddress));
                        request = new IeeeAddressRequest(networkAddress, 0, 0);
                        request.setDestinationAddress(
                                new ZigBeeEndpointAddress(ZigBeeBroadcastDestination.BROADCAST_RX_ON.getKey()));
                        response = networkManager.sendTransaction(request, request).get();
                        ieeeAddressResponse = response.getResponse();
                    }

                    logger.debug("{}: NWK Discovery IeeeAddressRequest returned from {}", ieeeAddressResponse,
                            String.format("%04X", networkAddress));
                    if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                        addNode(ieeeAddressResponse.getIeeeAddrRemoteDev(),
                                ieeeAddressResponse.getNwkAddrRemoteDev());
                        startNodeDiscovery(ieeeAddressResponse.getNwkAddrRemoteDev());
                        return;
                    }

                    // We failed with the request
                    logger.debug("NWK Discovery node rediscovery for {} request failed.",
                            String.format("%04X", networkAddress));
                } catch (InterruptedException | ExecutionException e) {
                    logger.debug("NWK Discovery interrupted in checkIeeeAddressResponse");
                }
                logger.debug("NWK Discovery for {} finishing node rediscovery",
                        String.format("%04X", networkAddress));
            }
        });
    }

    /**
     * Starts a discovery on a node. This will send a {@link NetworkAddressRequest} as a broadcast and will receive
     * the response to trigger a full discovery.
     *
     * @param ieeeAddress the {@link IeeeAddress} of the node to discover
     */
    protected void rediscoverNode(final IeeeAddress ieeeAddress) {
        if (!initialized) {
            logger.debug("{}: NWK discovery task: can't perform rediscovery until initialization complete.",
                    ieeeAddress);
            return;
        }

        networkManager.executeTask(new Runnable() {
            @Override
            public void run() {
                logger.debug("{}: NWK Discovery starting node rediscovery", ieeeAddress);
                try {
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }

                    NetworkAddressResponse nwkAddressResponse = null;
                    ZigBeeNode node = networkManager.getNode(ieeeAddress);
                    if (node != null) {
                        logger.debug("{}: NWK Discovery: Rediscovery using unicast to {}", ieeeAddress,
                                String.format("%04X", node.getNetworkAddress()));
                        NetworkAddressRequest request = new NetworkAddressRequest(ieeeAddress, 0, 0);
                        request.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
                        CommandResult response = networkManager.sendTransaction(request, request).get();
                        nwkAddressResponse = response.getResponse();
                    }

                    if (nwkAddressResponse == null) {
                        logger.debug("{}: NWK Discovery: Rediscovery using broadcast", ieeeAddress);
                        NetworkAddressRequest request = new NetworkAddressRequest(ieeeAddress, 0, 0);
                        request.setDestinationAddress(
                                new ZigBeeEndpointAddress(ZigBeeBroadcastDestination.BROADCAST_RX_ON.getKey()));
                        CommandResult response = networkManager.sendTransaction(request, request).get();
                        nwkAddressResponse = response.getResponse();
                    }

                    logger.debug("{}: NWK Discovery NetworkAddressRequest returned from {}", ieeeAddress,
                            nwkAddressResponse);
                    if (nwkAddressResponse != null && nwkAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                        logger.debug("{}: NWK Discovery: Rediscovery found network address to {}", ieeeAddress,
                                String.format("%04X", nwkAddressResponse.getNwkAddrRemoteDev()));
                        addNode(nwkAddressResponse.getIeeeAddrRemoteDev(),
                                nwkAddressResponse.getNwkAddrRemoteDev());
                        startNodeDiscovery(nwkAddressResponse.getNwkAddrRemoteDev());
                        return;
                    }

                    // We failed with the request
                    logger.debug("{}: NWK Discovery node rediscovery request failed.",
                            ieeeAddress);
                } catch (InterruptedException | ExecutionException e) {
                    logger.debug("NWK Discovery interrupt in rediscoverNode");
                }
                logger.debug("{}: NWK Discovery finishing node rediscovery", ieeeAddress);
            }
        });
    }

    /**
     * Performs the top level node discovery. This discovers the node IEEE address, and all associated nodes in the
     * network. Once a node is discovered, it is added to the {@link ZigBeeNetworkManager} and the node service
     * discovery will start.
     *
     * @param nodeNetworkAddress the network address to discover
     */
    private void startNodeDiscovery(final int nodeNetworkAddress) {
        // Check if we need to do a rediscovery on this node first...
        synchronized (discoveryStartTime) {
            if (discoveryStartTime.get(nodeNetworkAddress) != null
                    && System.currentTimeMillis() - discoveryStartTime.get(nodeNetworkAddress) < requeryPeriod) {
                logger.trace("NWK Discovery for node {} discovery already in progress",
                        String.format("%04X", nodeNetworkAddress));
                return;
            }
            discoveryStartTime.put(nodeNetworkAddress, System.currentTimeMillis());
        }

        logger.debug("NWK Discovery for {} scheduling node discovery", String.format("%04X", nodeNetworkAddress));
        networkManager.executeTask(new Runnable() {
            @Override
            public void run() {
                try {
                    logger.debug("NWK Discovery for {} starting node discovery",
                            String.format("%04X", nodeNetworkAddress));
                    boolean success = true;
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }

                    if (!success) {
                        // We failed with the request.
                        return;
                    }

                    // If we don't know the node yet, then try to find the IEEE address
                    // before requesting the associated nodes.
                    if (networkManager.getNode(nodeNetworkAddress) == null) {
                        success = getIeeeAddress(nodeNetworkAddress);
                        return;
                    }

                    success = getAssociatedNodes(nodeNetworkAddress);
                    if (success) {
                        return;
                    }

                    logger.debug("NWK Discovery for {} ending node discovery. Success={}.",
                            String.format("%04X", nodeNetworkAddress), success);
                } catch (InterruptedException | ExecutionException e) {
                    logger.debug("NWK Discovery interrupted");
                } catch (Exception e) {
                    logger.error("NWK Discovery for {} error during node discovery: ",
                            String.format("%04X", nodeNetworkAddress), e);
                }
            }
        });
    }

    /**
     * Discovers the {@link IeeeAddress} of a remote device. This uses a unicast request as we assume address discovery
     * was already completed.
     *
     * @param networkAddress the network address of the device
     * @return true if the node {@link IeeeAddress} was found
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private boolean getIeeeAddress(final int networkAddress) throws InterruptedException, ExecutionException {
        // Request basic response, start index for associated list is 0
        final IeeeAddressRequest request = new IeeeAddressRequest(networkAddress, 0, 0);
        request.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
        CommandResult response = networkManager.sendTransaction(request, request).get();
        if (response.isError()) {
            return false;
        }

        final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
        logger.debug("NWK Discovery for {} IeeeAddressRequest returned {}", String.format("%04X", networkAddress),
                ieeeAddressResponse);
        if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
            addNode(ieeeAddressResponse.getIeeeAddrRemoteDev(), ieeeAddressResponse.getNwkAddrRemoteDev());
            startNodeDiscovery(ieeeAddressResponse.getNwkAddrRemoteDev());
            return true;
        }

        return false;
    }

    /**
     * Get the associated nodes for this address, and start a discovery of the associated nodes.
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean getAssociatedNodes(final int networkAddress) throws InterruptedException, ExecutionException {
        Integer startIndex = 0;
        int totalAssociatedDevices = 0;
        Set<Integer> associatedDevices = new HashSet<Integer>();

        do {
            // Request extended response, start index for associated list is 0
            final IeeeAddressRequest ieeeAddressRequest = new IeeeAddressRequest(networkAddress, 1, startIndex);
            ieeeAddressRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            CommandResult response = networkManager.sendTransaction(ieeeAddressRequest, ieeeAddressRequest).get();
            if (response.isError()) {
                return false;
            }

            final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
            logger.debug("NWK Discovery for {} IeeeAddressRequest returned {}", String.format("%04X", networkAddress),
                    ieeeAddressResponse);
            if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS
                    && startIndex.equals(ieeeAddressResponse.getStartIndex())) {
                associatedDevices.addAll(ieeeAddressResponse.getNwkAddrAssocDevList());

                startIndex += ieeeAddressResponse.getNwkAddrAssocDevList().size();
                totalAssociatedDevices = ieeeAddressResponse.getNwkAddrAssocDevList().size();
            }
        } while (startIndex < totalAssociatedDevices);

        // Start discovery for any associated nodes
        for (final int deviceNetworkAddress : associatedDevices) {
            startNodeDiscovery(deviceNetworkAddress);
        }

        return true;
    }

    /**
     * Updates {@link ZigBeeNode} and adds it to the {@link ZigBeeNetworkManager}
     *
     * @param ieeeAddress the {@link IeeeAddress} of the newly announced node
     * @param networkAddress the network address of the newly announced node
     */
    private void addNode(final IeeeAddress ieeeAddress, int networkAddress) {
        logger.debug("{}: NWK Discovery add node {}", ieeeAddress, String.format("%04X", networkAddress));
        ZigBeeNode node = new ZigBeeNode(networkManager, ieeeAddress, networkAddress);
        node.setNodeState(ZigBeeNodeState.ONLINE);
        networkManager.addOrUpdateNode(node);
    }
}
