/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandListener;
import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.DeviceStatusListener;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsRequest;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
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
 * Notifications will be sent to the listeners when nodes and devices are discovered.
 * Device listeners are always notified first as each device discovery completes.
 * Once a node is fully discovered and all its devices are included into the network,
 * we can notify the node listeners.
 *
 * @author Chris Jackson
 */
public class ZigBeeNetworkDiscoverer implements CommandListener, DeviceStatusListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkDiscoverer.class);

    /**
     * Maximum number of retries to perform
     */
    private static final int RETRY_COUNT = 3;

    /**
     * Period between retries
     */
    private static final int RETRY_PERIOD = 1500;

    /**
     * Minimum time before information can be queried again for same network address or endpoint.
     */
    private static final int MINIMUM_REQUERY_TIME_MILLIS = 60000;

    /**
     * The ZigBee network manager.
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * Map of discovery nodes - nodes that are being actively discovered
     */
    private final Map<Integer, ZigBeeNode> discoveryNodes = new ConcurrentHashMap<Integer, ZigBeeNode>();

    /**
     * Map of node discovery times.
     */
    private final Map<Integer, Long> discoveryStartTime = Collections.synchronizedMap(new HashMap<Integer, Long>());

    /**
     * List of devices being discovered so we know when to notify of node updates
     */
    private final Set<ZigBeeEndpointAddress> discoveryProgress = new HashSet<ZigBeeEndpointAddress>();

    /**
     * Executor service to execute discovery threads
     */
    private static ExecutorService executorService = Executors.newFixedThreadPool(6);

    private enum NodeDiscoveryState {
        IEEE_ADDRESS,
        NODE_DESCRIPTOR,
        POWER_DESCRIPTOR,
        ACTIVE_ENDPOINTS;

        public NodeDiscoveryState next() {
            return values()[(this.ordinal() + 1) % values().length];
        }
    }

    /**
     * Discovers ZigBee network state.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     */
    public ZigBeeNetworkDiscoverer(final ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Starts up ZigBee network discoverer.
     */
    public void startup() {
        networkManager.addCommandListener(this);
        networkManager.addDeviceStatusListener(this);

        // Start discovery from root node.
        startNodeDiscovery(0);
    }

    /**
     * Shuts down ZigBee network discoverer.
     */
    public void shutdown() {
        networkManager.removeCommandListener(this);
        networkManager.removeDeviceStatusListener(this);
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
    public void commandReceived(final Command command) {
        // ZCL command received from remote node. Request IEEE address if it is not yet known.
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
     * Performs the top level node discovery. This discovers node level attributes such as the endpoints and
     * descriptors.
     *
     * @param networkAddress
     */
    private void startNodeDiscovery(final int nodeNetworkAddress) {
        // Check if we need to do a rediscovery on this node first...
        synchronized (discoveryStartTime) {
            if (discoveryStartTime.get(nodeNetworkAddress) != null && System.currentTimeMillis()
                    - discoveryStartTime.get(nodeNetworkAddress) < MINIMUM_REQUERY_TIME_MILLIS) {
                logger.trace("{}: Node discovery already in progress", nodeNetworkAddress);
                return;
            }
            discoveryStartTime.put(nodeNetworkAddress, System.currentTimeMillis());
        }

        ZigBeeNode node = new ZigBeeNode(networkManager);
        node.setNetworkAddress(nodeNetworkAddress);
        discoveryNodes.put(nodeNetworkAddress, node);

        logger.debug("{}: Scheduling node discovery", nodeNetworkAddress);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logger.debug("{}: Starting node discovery", nodeNetworkAddress);
                int retries = 0;
                boolean success;
                NodeDiscoveryState discoveryState = NodeDiscoveryState.IEEE_ADDRESS;
                do {
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
                        logger.debug("{}: Discovery request {} successfull. Advanced to {}.", nodeNetworkAddress,
                                discoveryState, discoveryState.next());
                        discoveryState = discoveryState.next();
                        continue;
                    }

                    // We failed with the last request. Wait a bit then retry
                    try {
                        logger.debug("{}: Discovery request {} failed. Wait before retry.", nodeNetworkAddress,
                                discoveryState);
                        Thread.sleep(RETRY_PERIOD);
                    } catch (InterruptedException e) {
                        return;
                    }

                } while (retries++ < RETRY_COUNT);

                logger.debug("{}: Ending node discovery", nodeNetworkAddress);
            }
        };

        executorService.execute(new Thread(runnable, "Discovery-Node-" + nodeNetworkAddress));
    }

    /**
     * Performs device level discovery. This discovers the device level attributes such as clusters.
     *
     * @param networkAddress the {@link ZigBeeEndpointAddress} to discover
     */
    private void startDeviceDiscovery(final ZigBeeEndpointAddress deviceNetworkAddress) {
        synchronized (discoveryProgress) {
            // Don't start a new discovery thread if one already exists for this device
            if (discoveryProgress.contains(deviceNetworkAddress)) {
                return;
            }

            // Add this device to the progress list
            discoveryProgress.add(deviceNetworkAddress);
        }

        logger.debug("{}: Scheduling device discovery", deviceNetworkAddress);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                logger.debug("{}: Starting device discovery", deviceNetworkAddress);
                int retries = 0;
                while (!getSimpleDescriptor(deviceNetworkAddress)) {
                    if (retries++ > RETRY_COUNT) {
                        break;
                    }
                    try {
                        Thread.sleep(RETRY_PERIOD);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        };

        executorService.execute(new Thread(runnable, "Discovery-Device-" + deviceNetworkAddress));
    }

    /**
     * Get Node IEEE address
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     */
    private boolean getIeeeAddress(final int networkAddress) {
        try {
            // Request extended response, start index for associated list is 0
            final IeeeAddressRequest ieeeAddressRequest = new IeeeAddressRequest();
            ieeeAddressRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            ieeeAddressRequest.setRequestType(1);
            ieeeAddressRequest.setStartIndex(0);
            ieeeAddressRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager.unicast(ieeeAddressRequest, ieeeAddressRequest).get();

            final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
            if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                ZigBeeNode node = discoveryNodes.get(networkAddress);
                if (node == null) {
                    logger.debug("Discovery node not found in getIeeeAddress");
                } else {
                    node.setIeeeAddress(ieeeAddressResponse.getIeeeAddrRemoteDev());
                    node.setAssociatedDevices(ieeeAddressResponse.getNwkAddrAssocDevList());
                }

                // Start discovery for any associated nodes
                for (final int deviceNetworkAddress : ieeeAddressResponse.getNwkAddrAssocDevList()) {
                    startNodeDiscovery(deviceNetworkAddress);
                }
                return true;
            } else {
                logger.debug("Ieee Address for {} returned {}", networkAddress, ieeeAddressResponse);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error in checkIeeeAddressResponse", e);
        }

        return false;
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
            if (nodeDescriptorResponse == null) {
                return false;
            }

            if (nodeDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
                ZigBeeNode node = discoveryNodes.get(networkAddress);
                if (node == null) {
                    logger.debug("Discovery node not found getNodeDescriptor");
                } else {
                    node.setNodeDescriptor(nodeDescriptorResponse.getNodeDescriptor());
                }

                return true;
            } else {
                logger.debug("Node Descriptor for {} returned {}", networkAddress, nodeDescriptorResponse);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error in checkNodeDescriptorResponse", e);
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
            if (powerDescriptorResponse == null) {
                return false;
            }

            if (powerDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
                ZigBeeNode node = discoveryNodes.get(networkAddress);
                if (node == null) {
                    logger.debug("Discovery node not found getPowerDescriptor");
                } else {
                    node.setPowerDescriptor(powerDescriptorResponse.getPowerDescriptor());
                }

                return true;
            } else if (powerDescriptorResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                return true;
            } else {
                logger.debug("Power Descriptor for {} returned {}", networkAddress, powerDescriptorResponse);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error in checkPowerDescriptorResponse", e);
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
            if (activeEndpointsResponse == null) {
                return false;
            }

            final ZigBeeNode node = discoveryNodes.get(networkAddress);
            if (node == null) {
                logger.debug("Error finding node in getActiveEndpoints");
                return false;
            }

            if (activeEndpointsResponse.getStatus() == ZdoStatus.SUCCESS) {
                for (final int endpoint : activeEndpointsResponse.getActiveEpList()) {
                    ZigBeeEndpoint device = new ZigBeeEndpoint(networkManager, node, endpoint);
                    node.addEndpoint(device);

                    startDeviceDiscovery(
                            new ZigBeeEndpointAddress(activeEndpointsResponse.getNwkAddrOfInterest(), endpoint));
                }

                return true;
            } else {
                logger.debug("Active Endpoints for {} returned {}", networkAddress, activeEndpointsResponse);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error in checkActiveEndpointsResponse", e);
        }

        return false;
    }

    /**
     * Get the description of a device endpoint
     *
     * @param deviceAddress the {@link ZigBeeEndpointAddress} of the device
     * @return true if the message was processed ok
     */
    private boolean getSimpleDescriptor(final ZigBeeEndpointAddress deviceAddress) {
        try {
            final SimpleDescriptorRequest simpleDescriptorRequest = new SimpleDescriptorRequest();
            simpleDescriptorRequest.setDestinationAddress(new ZigBeeEndpointAddress(deviceAddress.getAddress()));
            simpleDescriptorRequest.setNwkAddrOfInterest(deviceAddress.getAddress());
            simpleDescriptorRequest.setEndpoint(deviceAddress.getEndpoint());
            CommandResult response = networkManager.unicast(simpleDescriptorRequest, simpleDescriptorRequest).get();

            final SimpleDescriptorResponse simpleDescriptorResponse = (SimpleDescriptorResponse) response.getResponse();

            if (simpleDescriptorResponse == null) {
                return false;
            }

            if (simpleDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
                updateDevice(deviceAddress, simpleDescriptorResponse.getSimpleDescriptor());

                return true;
            } else {
                logger.debug("Simple Descriptor for {} returned {}", deviceAddress, simpleDescriptorResponse);
            }
        } catch (InterruptedException |

                ExecutionException e) {
            logger.error("Error in checkActiveEndpointsResponse", e);
        }

        return false;
    }

    // TODO: Get supported Attributes and Commands

    /**
     * Updates {@link ZigBeeEndpoint} in the {@link ZigBeeNode} and completes discovery if all devices are discovered in
     * this node.
     *
     * @param deviceAddress the {@link ZigBeeEndpointAddress} of the device to add
     * @param simpleDescriptor the {@link SimpleDescriptor} of the device
     */
    private void updateDevice(final ZigBeeEndpointAddress deviceAddress, final SimpleDescriptor simpleDescriptor) {
        final ZigBeeNode node = discoveryNodes.get(deviceAddress.getAddress());
        if (node == null) {
            logger.debug("Error finding node when adding {}", deviceAddress);
            return;
        }

        final ZigBeeEndpoint device = node.getEndpoint(deviceAddress.getEndpoint());

        device.setProfileId(simpleDescriptor.getProfileId());
        device.setDeviceId(simpleDescriptor.getDeviceId());
        device.setDeviceVersion(simpleDescriptor.getDeviceVersion());
        device.setInputClusterIds(simpleDescriptor.getInputClusterList());
        device.setOutputClusterIds(simpleDescriptor.getOutputClusterList());

        node.addEndpoint(device);

        synchronized (discoveryProgress) {
            // Remove this device from the progress list
            discoveryProgress.remove(deviceAddress);

            // Check the progress list to see if there are still devices to be discovered on this node
            for (ZigBeeEndpointAddress address : discoveryProgress) {
                if (address.getAddress() == deviceAddress.getAddress()) {
                    return;
                }
            }
        }

        // Add the node to the network...
        networkManager.addNode(node);

        // Remove the node from the discovery list
        discoveryNodes.remove(deviceAddress.getAddress());
    }
}
