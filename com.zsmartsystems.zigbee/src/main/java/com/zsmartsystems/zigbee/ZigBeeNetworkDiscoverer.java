package com.zsmartsystems.zigbee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
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
import com.zsmartsystems.zigbee.zdo.command.UserDescriptorResponse;

/**
 * <p>
 * ZigBee network discoverer is used to discover devices in the network.
 * <p>
 * Notifications will be sent to the listeners when nodes and devices are discovered.
 * Device listeners are always notified first as each device discovery completes.
 * Once a node is fully discovered and all its devices are included into the network,
 * we can notify the node listeners.
 * <p>
 * This class is thread safe.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZigBeeNetworkDiscoverer implements CommandListener {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeNetworkDiscoverer.class);
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
     * Map of node descriptor request times.
     */
    private final Map<Integer, Long> nodeDescriptorRequestTimes = Collections
            .synchronizedMap(new HashMap<Integer, Long>());
    /**
     * Map of power descriptor request times.
     */
    // private final Map<Integer, Long> powerDescriptorRequestTimes = Collections
    // .synchronizedMap(new HashMap<Integer, Long>());
    /**
     * Map of active endpoints request times.
     */
    private final Map<Integer, Long> activeEndpointsRequestTimes = Collections
            .synchronizedMap(new HashMap<Integer, Long>());
    /**
     * Map of endpoint descriptor request times.
     */
    private final Map<ZigBeeDeviceAddress, Long> endpointDescriptorRequestTimes = Collections
            .synchronizedMap(new HashMap<ZigBeeDeviceAddress, Long>());

    /**
     * List of devices being discovered so we know when to notify of node updates
     */
    private final List<ZigBeeDeviceAddress> discoveryProgress = new ArrayList<ZigBeeDeviceAddress>();

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
        requestNodeIeeeAddressAndAssociatedNodes(0);
    }

    /**
     * Shuts down ZigBee network discoverer.
     */
    public void shutdown() {
        networkManager.removeCommandListener(this);
    }

    @Override
    public void commandReceived(final Command command) {
        // 0. ZCL command received from remote node. Request IEEE address if it
        // is not yet known.
        if (command instanceof ZclCommand) {
            final ZclCommand zclCommand = (ZclCommand) command;
            if (networkManager.getDevice(zclCommand.getSourceAddress()) == null) {
                // TODO: Protect against group address?
                ZigBeeDeviceAddress address = (ZigBeeDeviceAddress) zclCommand.getSourceAddress();
                requestNodeIeeeAddressAndAssociatedNodes(address.getAddress());
            }
        }

        // 0. Node has been announced.
        if (command instanceof DeviceAnnounce) {
            final DeviceAnnounce deviceAnnounce = (DeviceAnnounce) command;
            requestNodeIeeeAddressAndAssociatedNodes(deviceAnnounce.getNetworkAddress());
        }

        // 1. Node IEEE address and associated nodes have been received.
        if (command instanceof IeeeAddressResponse) {
            final IeeeAddressResponse ieeeAddressResponse = (IeeeAddressResponse) command;

            if (ieeeAddressResponse.getStatus() == 0) {
                ieeeAddresses.put(ieeeAddressResponse.getNetworkAddress(), ieeeAddressResponse);
                describeNode(ieeeAddressResponse.getNetworkAddress());

                for (final int networkAddress : ieeeAddressResponse.associatedDeviceList) {
                    requestNodeIeeeAddressAndAssociatedNodes(networkAddress);
                }
            } else {
                logger.warn(ieeeAddressResponse.toString());
            }
        }

        // 2. Node has been described.
        if (command instanceof NodeDescriptorResponse) {
            final NodeDescriptorResponse nodeDescriptorResponse = (NodeDescriptorResponse) command;

            if (nodeDescriptorResponse.getStatus() == 0) {
                // Create the node

                nodeDescriptors.put(nodeDescriptorResponse.getNetworkAddress(), nodeDescriptorResponse);
                requestPowerDescriptor(nodeDescriptorResponse.getNetworkAddress());
            } else {
                logger.warn(nodeDescriptorResponse.toString());
            }
        }

        // 3. Node power descriptor has been received
        if (command instanceof PowerDescriptorResponse) {
            final PowerDescriptorResponse powerDescriptorResponse = (PowerDescriptorResponse) command;

            if (powerDescriptorResponse.getStatus() == 0) {
                powerDescriptors.put(powerDescriptorResponse.getSourceAddress(), powerDescriptorResponse);

                requestNodeEndpoints(powerDescriptorResponse.getSourceAddress());
            } else {
                logger.warn(powerDescriptorResponse.toString());
            }
        }

        // 4. Endpoints have been received.
        if (command instanceof ActiveEndpointsResponse) {
            final ActiveEndpointsResponse activeEndpointsResponse = (ActiveEndpointsResponse) command;
            if (activeEndpointsResponse.getStatus() == 0) {
                for (final int endpoint : activeEndpointsResponse.getActiveEndpoints()) {
                    ZigBeeDeviceAddress deviceAddress = new ZigBeeDeviceAddress(
                            activeEndpointsResponse.getNetworkAddress(), endpoint);
                    describeEndpoint(deviceAddress);

                    if (!discoveryProgress.contains(deviceAddress)) {
                        discoveryProgress.add(deviceAddress);
                    }
                }
            } else {
                logger.warn(activeEndpointsResponse.toString());
            }
        }

        // 5. Endpoint is described.
        if (command instanceof SimpleDescriptorResponse) {
            final SimpleDescriptorResponse simpleDescriptorResponse = (SimpleDescriptorResponse) command;
            if (simpleDescriptorResponse.getStatus() == 0) {
                final int networkAddress = simpleDescriptorResponse.getNetworkAddress();
                final IeeeAddressResponse ieeeAddressResponse = ieeeAddresses.get(networkAddress);
                final NodeDescriptorResponse nodeDescriptorResponse = nodeDescriptors.get(networkAddress);

                if (ieeeAddressResponse == null || nodeDescriptorResponse == null) {
                    return;
                }

                addOrUpdateDevice(ieeeAddressResponse, simpleDescriptorResponse);
            } else {
                logger.warn(simpleDescriptorResponse.toString());
            }
        }

        // 6. Endpoint user descriptor is received.
        if (command instanceof UserDescriptorResponse) {
            final UserDescriptorResponse userDescriptorResponse = (UserDescriptorResponse) command;
            logger.info("Received user descriptor response: " + userDescriptorResponse);
        }
    }

    /**
     * Requests node IEEE address and associated nodes.
     *
     * @param networkAddress
     *            the network address
     */
    private void requestNodeIeeeAddressAndAssociatedNodes(final int networkAddress) {
        if (ieeeAddressRequestTimes.get(networkAddress) != null && System.currentTimeMillis()
                - ieeeAddressRequestTimes.get(networkAddress) < MINIMUM_REQUERY_TIME_MILLIS) {
            return;
        }
        ieeeAddressRequestTimes.put(networkAddress, System.currentTimeMillis());

        try {
            final IeeeAddressRequest ieeeAddressRequest = new IeeeAddressRequest(networkAddress, 1, 0);
            networkManager.sendCommand(ieeeAddressRequest);
        } catch (ZigBeeException e) {
            logger.error("Error sending discovery command.", e);
        }
    }

    /**
     * Describe node at given network address.
     *
     * @param networkAddress
     *            the network address
     */
    private void describeNode(final int networkAddress) {
        if (nodeDescriptorRequestTimes.get(networkAddress) != null && System.currentTimeMillis()
                - nodeDescriptorRequestTimes.get(networkAddress) < MINIMUM_REQUERY_TIME_MILLIS) {
            return;
        }
        nodeDescriptorRequestTimes.put(networkAddress, System.currentTimeMillis());

        try {
            final NodeDescriptorRequest nodeDescriptorRequest = new NodeDescriptorRequest(networkAddress,
                    networkAddress);
            networkManager.sendCommand(nodeDescriptorRequest);
        } catch (ZigBeeException e) {
            logger.error("Error sending discovery command.", e);
        }
    }

    private void requestPowerDescriptor(final int networkAddress) {
        // if (nodeDescriptorRequestTimes.get(networkAddress) != null
        // && System.currentTimeMillis() -
        // nodeDescriptorRequestTimes.get(networkAddress) <
        // MINIMUM_REQUERY_TIME_MILLIS) {
        // return;
        // }
        // nodeDescriptorRequestTimes.put(networkAddress,
        // System.currentTimeMillis());

        try {
            final PowerDescriptorRequest powerDescriptorRequest = new PowerDescriptorRequest(networkAddress,
                    networkAddress);
            networkManager.sendCommand(powerDescriptorRequest);
        } catch (ZigBeeException e) {
            logger.error("Error sending discovery command.", e);
        }
    }

    /**
     * Discover node endpoints.
     *
     * @param networkAddress
     *            the network address
     */
    private void requestNodeEndpoints(final int networkAddress) {
        if (activeEndpointsRequestTimes.get(networkAddress) != null && System.currentTimeMillis()
                - activeEndpointsRequestTimes.get(networkAddress) < MINIMUM_REQUERY_TIME_MILLIS) {
            return;
        }
        activeEndpointsRequestTimes.put(networkAddress, System.currentTimeMillis());

        try {
            final ActiveEndpointsRequest activeEndpointsRequest = new ActiveEndpointsRequest();
            activeEndpointsRequest.setDestinationAddress(networkAddress);
            activeEndpointsRequest.setNetworkAddressOfInterest(networkAddress);
            networkManager.sendCommand(activeEndpointsRequest);
        } catch (ZigBeeException e) {
            logger.error("Error sending discovery command.", e);
        }
    }

    /**
     * Describe node endpoint
     *
     * @param networkAddress
     *            the network address
     * @param endpoint
     *            the endpoint
     */
    private void describeEndpoint(final ZigBeeDeviceAddress deviceAddress) {
        if (endpointDescriptorRequestTimes.get(deviceAddress) != null && System.currentTimeMillis()
                - endpointDescriptorRequestTimes.get(deviceAddress) < MINIMUM_REQUERY_TIME_MILLIS) {
            return;
        }
        endpointDescriptorRequestTimes.put(deviceAddress, System.currentTimeMillis());
        try {
            final SimpleDescriptorRequest request = new SimpleDescriptorRequest();
            request.setDestinationAddress(deviceAddress.getAddress());
            request.setEndpoint(deviceAddress.getEndpoint());
            networkManager.sendCommand(request);
        } catch (ZigBeeException e) {
            logger.error("Error sending discovery command.", e);
        }
    }

    private void addOrUpdateNode(final IeeeAddressResponse ieeeAddressResponse,
            final NodeDescriptorResponse nodeDescriptorResponse,
            final PowerDescriptorResponse powerDescriptorResponse) {
        final ZigBeeNode node;
        final boolean newDevice = networkManager.getNode(ieeeAddressResponse.getNetworkAddress()) == null;

        if (newDevice) {
            node = new ZigBeeNode();
        } else {
            node = networkManager.getNode(ieeeAddressResponse.getNetworkAddress());
        }

        node.setNetworkAddress(ieeeAddressResponse.getNetworkAddress());
        node.setNodeDescriptor(nodeDescriptorResponse.getNodeDescriptor());
        node.setIeeeAddress(ieeeAddressResponse.getIeeeAddress());
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
                ieeeAddressResponse.getNetworkAddress(), simpleDescriptorResponse.getEndpoint())) == null;

        if (newDevice) {
            device = new ZigBeeDevice(networkManager);
        } else {
            device = networkManager.getDevice(new ZigBeeDeviceAddress(ieeeAddressResponse.getNetworkAddress(),
                    simpleDescriptorResponse.getEndpoint()));
        }

        ZigBeeDeviceAddress networkAddress = new ZigBeeDeviceAddress(ieeeAddressResponse.getNetworkAddress(),
                simpleDescriptorResponse.getEndpoint());

        device.setIeeeAddress(ieeeAddressResponse.getIeeeAddress());
        device.setDeviceAddress(networkAddress);
        device.setProfileId(simpleDescriptorResponse.getProfileId());
        device.setDeviceId(simpleDescriptorResponse.getDeviceId());
        device.setDeviceVersion(simpleDescriptorResponse.getDeviceVersion());
        device.setInputClusterIds(simpleDescriptorResponse.getInputClusters());
        device.setOutputClusterIds(simpleDescriptorResponse.getOutputClusters());

        if (newDevice) {
            networkManager.addDevice(device);
        } else {
            networkManager.updateDevice(device);
        }

        // Remove this device from the progress list
        discoveryProgress.remove(networkAddress);

        // Check the progress list to see if there are still devices to be discovered on this node
        boolean deviceOngoing = false;
        for (ZigBeeDeviceAddress address : discoveryProgress) {
            if (address.getAddress() == networkAddress.getAddress()) {
                deviceOngoing = true;
            }
        }

        // If there's no more devices being discovered at this address
        // then notify the node update
        if (!deviceOngoing) {
            // final int networkAddress =
            // powerDescriptorResponse.getSourceAddress();
            // final IeeeAddressResponse ieeeAddressResponse =
            // ieeeAddresses.get(networkAddress);
            final NodeDescriptorResponse nodeDescriptorResponse = nodeDescriptors.get(networkAddress.getAddress());
            final PowerDescriptorResponse powerDescriptorResponse = powerDescriptors.get(networkAddress.getAddress());

            addOrUpdateNode(ieeeAddressResponse, nodeDescriptorResponse, powerDescriptorResponse);
        }

        /*
         * final UserDescriptorRequest userDescriptorRequest = new
         * UserDescriptorRequest( device.getNetworkAddress(),
         * device.getNetworkAddress()); try {
         * commandInterface.sendCommand(userDescriptorRequest); } catch (final
         * ZigBeeException e) { LOGGER.error("Error sending discovery command.",
         * e); }
         */
    }
}
