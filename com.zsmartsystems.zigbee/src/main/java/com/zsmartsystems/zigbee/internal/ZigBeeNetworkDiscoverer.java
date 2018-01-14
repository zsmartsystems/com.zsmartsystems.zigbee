/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.Collections;
import java.util.EnumMap;
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
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsRequest;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;

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
    private final int DEFAULT_REQUERY_TIME = 60000;

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
     * Map of discovery nodes - nodes that are being actively discovered
     */
    private final Map<Integer, ZigBeeNode> discoveryNodes = new ConcurrentHashMap<Integer, ZigBeeNode>();

    /**
     * Map of node discovery times.
     */
    private final Map<Integer, Long> discoveryStartTime = Collections
            .synchronizedMap(new ConcurrentHashMap<Integer, Long>());

    /**
     * List of endpoints being discovered so we know when to notify of node updates
     */
    private final Set<ZigBeeEndpointAddress> discoveryProgress = new HashSet<ZigBeeEndpointAddress>();

    /**
     * Flag used to initialise the discoverer once the network is ONLINE
     */
    private boolean initialized = false;

    private enum NodeDiscoveryState {
        NWK_ADDRESS,
        IEEE_ADDRESS,
        NODE_DESCRIPTOR,
        POWER_DESCRIPTOR,
        ACTIVE_ENDPOINTS,
        DISCOVERY_END;
    }

    private final Map<NodeDiscoveryState, NodeDiscoveryState> discoveryFlow = new EnumMap<NodeDiscoveryState, NodeDiscoveryState>(
            NodeDiscoveryState.class);

    /**
     * Discovers ZigBee network state.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     */
    public ZigBeeNetworkDiscoverer(final ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;

        discoveryFlow.put(NodeDiscoveryState.IEEE_ADDRESS, NodeDiscoveryState.NODE_DESCRIPTOR);
        discoveryFlow.put(NodeDiscoveryState.NODE_DESCRIPTOR, NodeDiscoveryState.POWER_DESCRIPTOR);
        discoveryFlow.put(NodeDiscoveryState.POWER_DESCRIPTOR, NodeDiscoveryState.ACTIVE_ENDPOINTS);
        discoveryFlow.put(NodeDiscoveryState.ACTIVE_ENDPOINTS, NodeDiscoveryState.DISCOVERY_END);
    }

    /**
     * Starts up ZigBee network discoverer. This adds a listener to wait for the network to go online.
     */
    public void startup() {
        networkManager.addNetworkStateListener(this);
    }

    /**
     * Shuts down ZigBee network discoverer.
     */
    public void shutdown() {
        networkManager.removeCommandListener(this);
        networkManager.removeAnnounceListener(this);
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
                startNodeDiscovery(networkAddress);
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
            final DeviceAnnounce address = (DeviceAnnounce) command;
            startNodeDiscovery(address.getNwkAddrOfInterest());
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
                logger.debug("{}: Starting node rediscovery", ieeeAddress);
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
                            logger.debug("{}: Node rediscovery request failed. Wait before retry.", ieeeAddress);
                            Thread.sleep(retryPeriod);
                        } catch (InterruptedException e) {
                            break;
                        }
                    } while (retries++ < retryCount);
                } catch (InterruptedException | ExecutionException e) {
                    logger.debug("Error in rediscoverNode ", e);
                }
                logger.debug("{}: Finishing node rediscovery", ieeeAddress);
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
                logger.trace("{}: Node discovery already in progress", nodeNetworkAddress);
                return;
            }
            discoveryStartTime.put(nodeNetworkAddress, System.currentTimeMillis());
        }

        logger.debug("{}: Scheduling node discovery", nodeNetworkAddress);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    logger.debug("{}: Starting node discovery", nodeNetworkAddress);
                    int retries = 0;
                    boolean success;
                    NodeDiscoveryState discoveryState = NodeDiscoveryState.IEEE_ADDRESS;
                    do {
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }

                        success = false;
                        switch (discoveryState) {
                            case IEEE_ADDRESS:
                                success = getIeeeAddress(nodeNetworkAddress);
                                break;
                            case NODE_DESCRIPTOR:
                                success = getNodeDescriptor(nodeNetworkAddress);
                                break;
                            case POWER_DESCRIPTOR:
                                success = getPowerDescriptor(nodeNetworkAddress);
                                break;
                            case ACTIVE_ENDPOINTS:
                                success = getActiveEndpoints(nodeNetworkAddress);
                                break;
                            default:
                                logger.debug("{}: Unknown discovery state: {}", nodeNetworkAddress, discoveryState);
                                break;
                        }

                        if (success) {
                            logger.debug("{}: Discovery request {} successful. Advanced to {}.", nodeNetworkAddress,
                                    discoveryState, discoveryFlow.get(discoveryState));
                            discoveryState = discoveryFlow.get(discoveryState);
                            if (discoveryState == NodeDiscoveryState.DISCOVERY_END) {
                                break;
                            }

                            retries = 0;
                            continue;
                        }

                        // We failed with the last request. Wait a bit then retry
                        try {
                            logger.debug("{}: Node discovery request {} failed. Wait before retry.", nodeNetworkAddress,
                                    discoveryState);
                            Thread.sleep(retryPeriod);
                        } catch (InterruptedException e) {
                            break;
                        }
                    } while (retries++ < retryCount);

                    // Remove this node from the discovery list - just in case this exits following an error or retries.
                    synchronized (discoveryStartTime) {
                        discoveryStartTime.remove(nodeNetworkAddress);
                    }

                    logger.debug("{}: Ending node discovery", nodeNetworkAddress);
                } catch (Exception e) {
                    logger.error("{}: Error during node discovery: ", nodeNetworkAddress, e);
                }
            }
        };

        networkManager.executeTask(runnable);
    }

    /**
     * Performs endpoint level discovery. This discovers the endpoint attributes such as clusters.
     *
     * @param networkAddress the {@link ZigBeeEndpointAddress} to discover
     */
    private void startEndpointDiscovery(final ZigBeeEndpointAddress endpointNetworkAddress) {
        synchronized (discoveryProgress) {
            // Don't start a new discovery thread if one already exists for this endpoint
            if (discoveryProgress.contains(endpointNetworkAddress)) {
                logger.debug("{}: Endpoint discovery already in progress", endpointNetworkAddress);
                return;
            }

            // Add this endpoint to the progress list
            discoveryProgress.add(endpointNetworkAddress);
        }

        logger.debug("{}: Scheduling endpoint discovery", endpointNetworkAddress);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    logger.debug("{}: Starting endpoint discovery", endpointNetworkAddress);
                    int retries = 0;
                    while (!getSimpleDescriptor(endpointNetworkAddress)) {
                        if (Thread.currentThread().isInterrupted()) {
                            break;
                        }

                        if (retries++ > retryPeriod) {
                            break;
                        }
                        try {
                            logger.debug("{}: Endpoint discovery request failed. Wait before retry.",
                                    endpointNetworkAddress);
                            Thread.sleep(retryCount);
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    logger.error("{}: Error during endpoint discovery: ", endpointNetworkAddress, e);
                }

                // Remove this endpoint from the discovery list - just in case this exits following an error or retries.
                synchronized (discoveryProgress) {
                    discoveryProgress.remove(endpointNetworkAddress);
                }

                logger.debug("{}: Ending endpoint discovery", endpointNetworkAddress);
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
                logger.debug("{}: Ieee Address returned {}", networkAddress, ieeeAddressResponse);
                if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                    ieeeAddress = ieeeAddressResponse.getIeeeAddrRemoteDev();
                    if (startIndex.equals(ieeeAddressResponse.getStartIndex())) {
                        associatedDevices.addAll(ieeeAddressResponse.getNwkAddrAssocDevList());

                        startIndex += ieeeAddressResponse.getNwkAddrAssocDevList().size();
                        totalAssociatedDevices = ieeeAddressResponse.getNwkAddrAssocDevList().size();
                    }
                }

            } while (startIndex < totalAssociatedDevices);

            ZigBeeNode node = new ZigBeeNode(networkManager, ieeeAddress);
            node.setNetworkAddress(networkAddress);
            node.setAssociatedDevices(associatedDevices);
            discoveryNodes.put(networkAddress, node);

            // Start discovery for any associated nodes
            for (final int deviceNetworkAddress : associatedDevices) {
                startNodeDiscovery(deviceNetworkAddress);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error in checkIeeeAddressResponse ", e);
        }

        return true;
    }

    /**
     * Get node descriptor
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     */
    private boolean getNodeDescriptor(final int networkAddress) {
        try {
            final NodeDescriptorRequest nodeDescriptorRequest = new NodeDescriptorRequest();
            nodeDescriptorRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            nodeDescriptorRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager.unicast(nodeDescriptorRequest, nodeDescriptorRequest).get();

            final NodeDescriptorResponse nodeDescriptorResponse = (NodeDescriptorResponse) response.getResponse();
            logger.debug("{}: Node Descriptor returned {}", networkAddress, nodeDescriptorResponse);
            if (nodeDescriptorResponse == null) {
                return false;
            }

            if (nodeDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
                ZigBeeNode node = discoveryNodes.get(networkAddress);
                if (node == null) {
                    logger.debug("{}: Discovery node not found getNodeDescriptor", networkAddress);
                } else {
                    node.setNodeDescriptor(nodeDescriptorResponse.getNodeDescriptor());
                }

                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error in checkNodeDescriptorResponse ", e);
        }

        return false;
    }

    /**
     * Get node power descriptor
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok, or if the end device does not support the power descriptor
     */
    private boolean getPowerDescriptor(final int networkAddress) {
        try {
            final PowerDescriptorRequest powerDescriptorRequest = new PowerDescriptorRequest();
            powerDescriptorRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            powerDescriptorRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager.unicast(powerDescriptorRequest, powerDescriptorRequest).get();

            final PowerDescriptorResponse powerDescriptorResponse = (PowerDescriptorResponse) response.getResponse();
            logger.debug("{}: Power Descriptor returned {}", networkAddress, powerDescriptorResponse);
            if (powerDescriptorResponse == null) {
                return false;
            }

            if (powerDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
                ZigBeeNode node = discoveryNodes.get(networkAddress);
                if (node == null) {
                    logger.debug("{}: Discovery node not found getPowerDescriptor", networkAddress);
                } else {
                    node.setPowerDescriptor(powerDescriptorResponse.getPowerDescriptor());
                }

                return true;
            } else if (powerDescriptorResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error in checkPowerDescriptorResponse ", e);
        }

        return false;
    }

    /**
     * Get the active endpoints for a node
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     */
    private boolean getActiveEndpoints(final int networkAddress) {
        try {
            final ActiveEndpointsRequest activeEndpointsRequest = new ActiveEndpointsRequest();
            activeEndpointsRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            activeEndpointsRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager.unicast(activeEndpointsRequest, activeEndpointsRequest).get();

            final ActiveEndpointsResponse activeEndpointsResponse = (ActiveEndpointsResponse) response.getResponse();
            logger.debug("{}: Active Endpoints returned {}", networkAddress, activeEndpointsResponse);
            if (activeEndpointsResponse == null) {
                return false;
            }

            final ZigBeeNode node = discoveryNodes.get(networkAddress);
            if (node == null) {
                logger.debug("{}: Error finding node in getActiveEndpoints", networkAddress);
                return false;
            }

            if (activeEndpointsResponse.getStatus() == ZdoStatus.SUCCESS) {
                for (final int endpointId : activeEndpointsResponse.getActiveEpList()) {
                    ZigBeeEndpoint endpoint = new ZigBeeEndpoint(networkManager, node, endpointId);
                    node.addEndpoint(endpoint);

                    startEndpointDiscovery(
                            new ZigBeeEndpointAddress(activeEndpointsResponse.getNwkAddrOfInterest(), endpointId));
                }

                // Call updateNode in case there are no endpoints in this node
                updateNode(node);

                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error in checkActiveEndpointsResponse ", e);
        }

        return false;
    }

    /**
     * Get the description of an endpoint
     *
     * @param endpointAddress the {@link ZigBeeEndpointAddress} of the endpoint
     * @return true if the message was processed ok
     */
    private boolean getSimpleDescriptor(final ZigBeeEndpointAddress endpointAddress) {
        try {
            final SimpleDescriptorRequest simpleDescriptorRequest = new SimpleDescriptorRequest();
            simpleDescriptorRequest.setDestinationAddress(new ZigBeeEndpointAddress(endpointAddress.getAddress()));
            simpleDescriptorRequest.setNwkAddrOfInterest(endpointAddress.getAddress());
            simpleDescriptorRequest.setEndpoint(endpointAddress.getEndpoint());
            CommandResult response = networkManager.unicast(simpleDescriptorRequest, simpleDescriptorRequest).get();

            final SimpleDescriptorResponse simpleDescriptorResponse = (SimpleDescriptorResponse) response.getResponse();
            logger.debug("{}: Simple Descriptor returned {}", endpointAddress, simpleDescriptorResponse);
            if (simpleDescriptorResponse == null) {
                return false;
            }

            if (simpleDescriptorResponse.getStatus() == ZdoStatus.SUCCESS
                    && simpleDescriptorResponse.getSimpleDescriptor() != null) {
                updateEndpoint(endpointAddress, simpleDescriptorResponse.getSimpleDescriptor());

                return true;
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in checkActiveEndpointsResponse ", e);
        }

        return false;
    }

    /**
     * Updates {@link ZigBeeEndpoint} in the {@link ZigBeeNode} and completes discovery if all endpoints are discovered
     * in this node.
     *
     * @param endpointAddress the {@link ZigBeeEndpointAddress} of the device to add
     * @param simpleDescriptor the {@link SimpleDescriptor} of the device
     */
    private void updateEndpoint(final ZigBeeEndpointAddress endpointAddress, final SimpleDescriptor simpleDescriptor) {
        final ZigBeeNode node = discoveryNodes.get(endpointAddress.getAddress());
        if (node == null) {
            logger.debug("{}: Error finding node in updateEndpoint", endpointAddress);
            return;
        }

        final ZigBeeEndpoint endpoint = node.getEndpoint(endpointAddress.getEndpoint());
        if (endpoint == null) {
            logger.debug("{}: Error finding endpoint in updateEndpoint", endpointAddress);
            return;
        }
        endpoint.setProfileId(simpleDescriptor.getProfileId());
        endpoint.setDeviceId(simpleDescriptor.getDeviceId());
        endpoint.setDeviceVersion(simpleDescriptor.getDeviceVersion());
        endpoint.setInputClusterIds(simpleDescriptor.getInputClusterList());
        endpoint.setOutputClusterIds(simpleDescriptor.getOutputClusterList());

        node.addEndpoint(endpoint);

        synchronized (discoveryProgress) {
            // Remove this device from the progress list
            discoveryProgress.remove(endpointAddress);
        }
        updateNode(node);
    }

    /**
     * Updates {@link ZigBeeNode} and completes discovery if all devices are discovered in
     * this node.
     *
     * @param node the {@link ZigBeeNode} to add
     */
    private void updateNode(final ZigBeeNode node) {
        synchronized (discoveryProgress) {
            // Check the progress list to see if there are still endpoints to be discovered on this node
            for (ZigBeeEndpointAddress address : discoveryProgress) {
                if (address.getAddress() == node.getNetworkAddress()) {
                    return;
                }
            }
        }

        logger.debug("{}: Discovery has completed all endpoints", node.getNetworkAddress());

        // Add the node to the network...
        networkManager.addNode(node);

        // Remove the node from the discovery list
        discoveryNodes.remove(node.getNetworkAddress());
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
