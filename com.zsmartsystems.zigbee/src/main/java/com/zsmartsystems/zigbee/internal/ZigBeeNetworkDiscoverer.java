package com.zsmartsystems.zigbee.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandListener;
import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeDevice;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zdo.ZdoPacketResponseMatcher;
import com.zsmartsystems.zigbee.zdo.ZdoSimpleDescriptorResponseMatcher;
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

/**
 * {@link ZigBeeNetworkDiscoverer} is used to discover devices in the network.
 * <p>
 * Notifications will be sent to the listeners when nodes and devices are discovered.
 * Device listeners are always notified first as each device discovery completes.
 * Once a node is fully discovered and all its devices are included into the network,
 * we can notify the node listeners.
 * <p>
 * This class is thread safe.
 *
 * @author Chris Jackson
 * @author Tommi S.E. Laukkanen
 */
public class ZigBeeNetworkDiscoverer implements CommandListener {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeNetworkDiscoverer.class);

    /**
     * Maximum number of retries to perform
     */
    private static final int RETRIES_COUNT = 3;

    /**
     * Period between retries
     */
    private static final int RETRIES_PERIOD = 1500;

    /**
     * Minimum time before information can be queried again for same network address or endpoint.
     */
    private static final int MINIMUM_REQUERY_TIME_MILLIS = 10000;

    /**
     * The ZigBee command interface.
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * The received IEEE addresses.
     */
    private Map<Integer, IeeeAddressResponse> ieeeAddresses = Collections
            .synchronizedMap(new HashMap<Integer, IeeeAddressResponse>());

    /**
     * The received node descriptors.
     */
    private Map<Integer, NodeDescriptorResponse> nodeDescriptors = Collections
            .synchronizedMap(new HashMap<Integer, NodeDescriptorResponse>());

    /**
     * The received power descriptors.
     */
    private Map<Integer, PowerDescriptorResponse> powerDescriptors = Collections
            .synchronizedMap(new HashMap<Integer, PowerDescriptorResponse>());

    /**
     * Map of IEEE address request times.
     */
    private final Map<Integer, Long> ieeeAddressRequestTimes = Collections
            .synchronizedMap(new HashMap<Integer, Long>());

    /**
     * List of devices being discovered so we know when to notify of node updates
     */
    private final Set<ZigBeeDeviceAddress> discoveryProgress = new HashSet<ZigBeeDeviceAddress>();

    /**
     * Executor service to execute discovery threads
     */
    private static ExecutorService executorService = Executors.newCachedThreadPool();

    private enum NodeDiscoveryState {
        IEEE_ADDRESS,
        NODE_DESCRIPTOR,
        POWER_DESCRIPTOR,
        ACIVE_ENDPOINTS;

        public NodeDiscoveryState next() {
            return values()[(this.ordinal() + 1) % values().length];
        }
    }

    /**
     * Discovers ZigBee network state.
     *
     * @param networkState
     *            the network state
     * @param dongle
     *            the command interface
     */
    public ZigBeeNetworkDiscoverer(final ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Starts up ZigBee network discoverer.
     */
    public void startup() {
        networkManager.addCommandListener(this);

        // Start discovery from root node.
        startNodeDiscovery(0);
    }

    /**
     * Shuts down ZigBee network discoverer.
     */
    public void shutdown() {
        networkManager.removeCommandListener(this);
    }

    @Override
    public void commandReceived(final Command command) {
        // 0. ZCL command received from remote node. Request IEEE address if it is not yet known.
        if (command instanceof ZclCommand) {
            final ZclCommand zclCommand = (ZclCommand) command;
            if (networkManager.getDevice(zclCommand.getSourceAddress()) == null) {
                // TODO: Protect against group address?
                ZigBeeDeviceAddress address = (ZigBeeDeviceAddress) zclCommand.getSourceAddress();
                startNodeDiscovery(address.getAddress());
            }
        }

        // 0. Node has been announced.
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
        synchronized (ieeeAddressRequestTimes) {
            if (ieeeAddressRequestTimes.get(nodeNetworkAddress) != null && System.currentTimeMillis()
                    - ieeeAddressRequestTimes.get(nodeNetworkAddress) < MINIMUM_REQUERY_TIME_MILLIS) {
                return;
            }
            ieeeAddressRequestTimes.put(nodeNetworkAddress, System.currentTimeMillis());
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                logger.debug("Starting node discovery for {}", nodeNetworkAddress);
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
                        case ACIVE_ENDPOINTS:
                            success = getActiveEndpoints(nodeNetworkAddress);
                            break;
                        default:
                            logger.debug("Unknown state: {}", discoveryState);
                            break;
                    }

                    if (success) {
                        discoveryState = discoveryState.next();
                        continue;
                    }

                    // We failed with the last request. Wait a bit then retry
                    try {
                        Thread.sleep(RETRIES_PERIOD);
                    } catch (InterruptedException e) {
                        return;
                    }

                } while (retries++ < RETRIES_COUNT);

                logger.debug("Ending node discovery for {}", nodeNetworkAddress);
            }
        });
    }

    /**
     * Performs device level discovery. This discovers the device level attributes such as clusters.
     *
     * @param networkAddress
     */
    private void startDeviceDiscovery(final ZigBeeDeviceAddress deviceNetworkAddress) {
        synchronized (discoveryProgress) {
            // Don't start a new discovery thread if one already exists for this device
            if (discoveryProgress.contains(deviceNetworkAddress)) {
                return;
            }

            // Add this device to the progress list
            discoveryProgress.add(deviceNetworkAddress);
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                logger.debug("Starting device discovery for {}", deviceNetworkAddress);
                int retries = 0;
                while (!getSimpleDescriptor(deviceNetworkAddress)) {
                    if (retries++ > RETRIES_COUNT) {
                        break;
                    }
                    try {
                        Thread.sleep(RETRIES_PERIOD);
                    } catch (InterruptedException e) {
                        return;
                    }
                }
            }
        });
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
            ieeeAddressRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager
                    .unicast(ieeeAddressRequest, new ZdoPacketResponseMatcher(IeeeAddressResponse.class)).get();

            final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
            if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == 0) {
                synchronized (ieeeAddresses) {
                    ieeeAddresses.put(ieeeAddressResponse.getSourceAddress(), ieeeAddressResponse);
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
            logger.error("Error in checkIeeeAddressResponse", e);
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
            nodeDescriptorRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager
                    .unicast(nodeDescriptorRequest, new ZdoPacketResponseMatcher(NodeDescriptorResponse.class)).get();

            final NodeDescriptorResponse nodeDescriptorResponse = (NodeDescriptorResponse) response.getResponse();
            if (nodeDescriptorResponse != null && nodeDescriptorResponse.getStatus() == 0) {
                // Create the node
                synchronized (nodeDescriptors) {
                    nodeDescriptors.put(nodeDescriptorResponse.getNwkAddrOfInterest(), nodeDescriptorResponse);
                }
                return true;
            } else {
                logger.debug("Node Descriptor for {} returned {}", networkAddress, nodeDescriptorResponse);
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in checkNodeDescriptorResponse", e);
        }

        return false;
    }

    /**
     * Get node power descriptor
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     */
    private boolean getPowerDescriptor(final int networkAddress) {
        try {
            final PowerDescriptorRequest powerDescriptorRequest = new PowerDescriptorRequest();
            powerDescriptorRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager
                    .unicast(powerDescriptorRequest, new ZdoPacketResponseMatcher(PowerDescriptorResponse.class)).get();

            final PowerDescriptorResponse powerDescriptorResponse = (PowerDescriptorResponse) response.getResponse();
            if (powerDescriptorResponse != null && powerDescriptorResponse.getStatus() == 0) {
                if (powerDescriptorResponse.getStatus() == 0) {
                    synchronized (powerDescriptors) {
                        powerDescriptors.put(powerDescriptorResponse.getSourceAddress(), powerDescriptorResponse);
                    }

                    return true;
                } else {
                    logger.debug("Power Descriptor for {} returned {}", networkAddress, powerDescriptorResponse);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in checkPowerDescriptorResponse", e);
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
            activeEndpointsRequest.setDestinationAddress(networkAddress);
            activeEndpointsRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager
                    .unicast(activeEndpointsRequest, new ZdoPacketResponseMatcher(ActiveEndpointsResponse.class)).get();

            final ActiveEndpointsResponse activeEndpointsResponse = (ActiveEndpointsResponse) response.getResponse();
            if (activeEndpointsResponse != null && activeEndpointsResponse.getStatus() == 0) {

                if (activeEndpointsResponse.getStatus() == 0) {
                    for (final int endpoint : activeEndpointsResponse.getActiveEpList()) {
                        startDeviceDiscovery(
                                new ZigBeeDeviceAddress(activeEndpointsResponse.getNwkAddrOfInterest(), endpoint));
                    }

                    return true;
                } else {
                    logger.debug("Active Endpoints for {} returned {}", networkAddress, activeEndpointsResponse);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in checkActiveEndpointsResponse", e);
        }

        return false;
    }

    /**
     * Get the description of a device endpoint
     *
     * @param deviceAddress the network address of the device
     * @return true if the message was processed ok
     */
    private boolean getSimpleDescriptor(final ZigBeeDeviceAddress deviceAddress) {
        try {
            final SimpleDescriptorRequest simpleDescriptorRequest = new SimpleDescriptorRequest();
            simpleDescriptorRequest.setDestinationAddress(deviceAddress.getAddress());
            simpleDescriptorRequest.setEndpoint(deviceAddress.getEndpoint());
            CommandResult response = networkManager
                    .unicast(simpleDescriptorRequest, new ZdoSimpleDescriptorResponseMatcher()).get();

            final SimpleDescriptorResponse simpleDescriptorResponse = (SimpleDescriptorResponse) response.getResponse();

            if (simpleDescriptorResponse != null && simpleDescriptorResponse.getStatus() == 0) {
                if (simpleDescriptorResponse.getStatus() == 0) {
                    final IeeeAddressResponse ieeeAddressResponse;
                    final NodeDescriptorResponse nodeDescriptorResponse;

                    final int networkAddress = simpleDescriptorResponse.getNwkAddrOfInterest();

                    synchronized (powerDescriptors) {
                        ieeeAddressResponse = ieeeAddresses.get(networkAddress);
                    }

                    synchronized (powerDescriptors) {
                        nodeDescriptorResponse = nodeDescriptors.get(networkAddress);
                    }

                    // TODO: nodeDescriptorResponse is not used - does this matter?!
                    if (ieeeAddressResponse != null && nodeDescriptorResponse != null) {
                        addOrUpdateDevice(ieeeAddressResponse, simpleDescriptorResponse);

                        return true;
                    }
                } else {
                    logger.debug("Simple Descriptor for {} returned {}", deviceAddress, simpleDescriptorResponse);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in checkActiveEndpointsResponse", e);
        }

        return false;
    }

    // TODO: Get supported Attributes

    private void addOrUpdateNode(final IeeeAddressResponse ieeeAddressResponse,
            final NodeDescriptorResponse nodeDescriptorResponse,
            final PowerDescriptorResponse powerDescriptorResponse) {
        final ZigBeeNode node;
        final boolean newDevice = networkManager.getNode(ieeeAddressResponse.getSourceAddress()) == null;

        if (newDevice) {
            node = new ZigBeeNode();
        } else {
            node = networkManager.getNode(ieeeAddressResponse.getSourceAddress());
        }

        node.setNetworkAddress(ieeeAddressResponse.getSourceAddress());
        node.setNodeDescriptor(nodeDescriptorResponse.getNodeDescriptor());
        node.setIeeeAddress(new IeeeAddress(ieeeAddressResponse.getIeeeAddrRemoteDev()));
        node.setPowerDescriptor(powerDescriptorResponse.getPowerDescriptor());

        if (newDevice) {
            networkManager.addNode(node);
        } else {
            networkManager.updateNode(node);
        }
    }

    /**
     * Adds device to network state.
     *
     * @param ieeeAddressResponse
     *            the IEEE address response
     * @param nodeDescriptorResponse
     *            the node descriptor response
     * @param simpleDescriptorResponse
     *            the simple descriptor response
     */
    private void addOrUpdateDevice(final IeeeAddressResponse ieeeAddressResponse,
            final SimpleDescriptorResponse simpleDescriptorResponse) {
        final ZigBeeDevice device;
        final boolean newDevice = networkManager.getDevice(new ZigBeeDeviceAddress(
                ieeeAddressResponse.getSourceAddress(), simpleDescriptorResponse.getEndpoint())) == null;

        if (newDevice) {
            device = new ZigBeeDevice(networkManager);
        } else {
            device = networkManager.getDevice(new ZigBeeDeviceAddress(ieeeAddressResponse.getSourceAddress(),
                    simpleDescriptorResponse.getEndpoint()));
        }

        ZigBeeDeviceAddress networkAddress = new ZigBeeDeviceAddress(ieeeAddressResponse.getSourceAddress(),
                simpleDescriptorResponse.getEndpoint());

        device.setIeeeAddress(new IeeeAddress(ieeeAddressResponse.getIeeeAddrRemoteDev()));
        device.setDeviceAddress(networkAddress);
        device.setProfileId(simpleDescriptorResponse.getApplicationProfileId());
        device.setDeviceId(simpleDescriptorResponse.getApplicationDeviceId());
        device.setDeviceVersion(simpleDescriptorResponse.getApplicationDeviceVersion());
        device.setInputClusterIds(simpleDescriptorResponse.getApplicationInputClusters());
        device.setOutputClusterIds(simpleDescriptorResponse.getApplicationOutputClusters());

        if (newDevice) {
            networkManager.addDevice(device);
        } else {
            networkManager.updateDevice(device);
        }

        boolean deviceOngoing = false;
        synchronized (discoveryProgress) {
            // Remove this device from the progress list
            discoveryProgress.remove(networkAddress);

            // Check the progress list to see if there are still devices to be discovered on this node
            for (ZigBeeDeviceAddress address : discoveryProgress) {
                if (address.getAddress() == networkAddress.getAddress()) {
                    deviceOngoing = true;
                }
            }
        }

        // If there's no more devices being discovered at this address then notify the node update
        if (deviceOngoing) {
            return;
        }

        // There's no more devices being discovered with this node, so update the node
        final NodeDescriptorResponse nodeDescriptorResponse;
        final PowerDescriptorResponse powerDescriptorResponse;

        synchronized (nodeDescriptors) {
            nodeDescriptorResponse = nodeDescriptors.get(networkAddress.getAddress());
        }

        synchronized (powerDescriptors) {
            powerDescriptorResponse = powerDescriptors.get(networkAddress.getAddress());
        }

        addOrUpdateNode(ieeeAddressResponse, nodeDescriptorResponse, powerDescriptorResponse);
    }
}
