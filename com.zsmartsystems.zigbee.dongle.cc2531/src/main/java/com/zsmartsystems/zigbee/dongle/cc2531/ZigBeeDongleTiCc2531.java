package com.zsmartsystems.zigbee.dongle.cc2531;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager.ZigBeeInitializeResponse;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoActiveEndpoint;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoCallbackIncoming;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoEndDeviceAnnounce;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoIeeeAddress;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoManagementLeave;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoManagementLqi;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoManagementRouting;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoNodeDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoPowerDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoSimpleDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.AsynchronousCommandListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.DriverStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.NetworkMode;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ZigBeeNetworkManagerImpl;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST_EXT;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER_SRSP;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zdo.SynchronousResponse;

/**
 * ZigBee Dongle TI implementation for the CC2531 processor.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleTiCc2531
        implements ZigBeeTransportTransmit, ApplicationFrameworkMessageListener, AsynchronousCommandListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeDongleTiCc2531.class);

    /**
     * The {@link ZigBeeNetworkManagerImpl ZigBee network manager}.
     */
    private final ZigBeeNetworkManagerImpl networkManager;

    /**
     * The reference to the network
     */
    private ZigBeeTransportReceive zigbeeNetworkReceive;

    private final HashMap<Integer, Integer> sender2EndPoint = new HashMap<Integer, Integer>();
    private final HashMap<Integer, Integer> endpoint2Profile = new HashMap<Integer, Integer>();

    /**
     * The Ember version used in this system. Set during initialisation and saved in case the client is interested.
     */
    private String versionString = "Unknown";

    /**
     * Constructor to configure the port interface.
     *
     * @param serialPort
     *            the serial port
     */
    public ZigBeeDongleTiCc2531(final ZigBeePort serialPort) {
        networkManager = new ZigBeeNetworkManagerImpl(serialPort, NetworkMode.Coordinator, 2500L);
    }

    @Override
    public ZigBeeInitializeResponse initialize() {
        logger.debug("CC2531 transport initialize");

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        // This basically just initialises the hardware so we can communicate with the 2531
        versionString = networkManager.startup();
        if (versionString == null) {
            return ZigBeeInitializeResponse.FAILED;
        }

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

        return ZigBeeInitializeResponse.JOINED;
    }

    @Override
    public int getZigBeeChannel() {
        return networkManager.getCurrentChannel();
    }

    @Override
    public boolean setZigBeeChannel(int channel) {
        return networkManager.setZigBeeChannel(channel);
    }

    @Override
    public int getZigBeePanId() {
        return networkManager.getCurrentPanId();
    }

    @Override
    public boolean setZigBeePanId(int panId) {
        return networkManager.setZigBeePanId(panId);
    }

    @Override
    public long getZigBeeExtendedPanId() {
        return networkManager.getCurrentExtendedPanId();
    }

    @Override
    public boolean setZigBeeExtendedPanId(long panId) {
        return networkManager.setZigBeeExtendedPanId(panId);
    }

    @Override
    public boolean setZigBeeSecurityKey(int[] keyData) {
        byte[] key = new byte[16];
        int cnt = 0;
        for (int keyVal : keyData) {
            key[cnt] = (byte) keyVal;
        }
        return networkManager.setNetworkKey(key);
    }

    @Override
    public boolean startup(boolean reinitialize) {
        logger.debug("CC2531 transport startup");

        // Add listeners for ZCL and ZDO received messages
        networkManager.addAFMessageListener(this);
        networkManager.addAsynchronousCommandListener(this);

        if (!networkManager.initializeZigBeeNetwork(reinitialize)) {
            zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);
            return false;
        }

        while (true) {
            if (networkManager.getDriverStatus() == DriverStatus.NETWORK_READY) {
                break;
            }
            if (networkManager.getDriverStatus() == DriverStatus.CLOSED) {
                zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);
                return false;
            }
            try {
                Thread.sleep(50);
            } catch (final InterruptedException e) {
                zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);
                return false;
            }
        }

        createEndPoint(1, 0x104);

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.ONLINE);

        return true;
    }

    @Override
    public void shutdown() {
        networkManager.shutdown();
    }

    @Override
    public String getVersionString() {
        return versionString;
    }

    @Override
    public void sendCommand(final ZigBeeApsFrame apsFrame) throws ZigBeeException {
        synchronized (networkManager) {
            final short sender;
            if (apsFrame.getProfile() == 0) {
                sender = 0;
            } else {
                sender = (short) getSendingEndpoint(apsFrame.getProfile());
            }

            // TODO: How to differentiate group and device addressing?????
            boolean groupCommand = false;
            if (!groupCommand) {
                // final AF_DATA_CONFIRM response =
                networkManager.sendCommand(new AF_DATA_REQUEST(apsFrame.getDestinationAddress(),
                        (short) apsFrame.getDestinationEndpoint(), sender, apsFrame.getCluster(),
                        apsFrame.getSequence(), (byte) 0x30, (byte) apsFrame.getRadius(), apsFrame.getPayload()));
                // if (response == null) {
                // throw new ZigBeeException("Unable to send cluster on the ZigBee network due to general error.");
                // }
                // if (response.getStatus() != 0) {
                // throw new ZigBeeException("Unable to send cluster on the ZigBee network due to: "
                // + ResponseStatus.getStatus(response.getStatus()) + " "
                // + (response.getErrorMsg() != null ? " - " + response.getErrorMsg() : "") + ")");
                // }
            } else {
                // final AF_DATA_SRSP_EXT response =
                networkManager.sendCommand(new AF_DATA_REQUEST_EXT(apsFrame.getDestinationAddress(), sender,
                        apsFrame.getCluster(), apsFrame.getSequence(), (byte) (0), (byte) 0, apsFrame.getPayload()));
                // if (response.getStatus() != 0) {
                // throw new ZigBeeException("Unable to send cluster on the ZigBee network due to: "
                // + ResponseStatus.getStatus(response.getStatus()));
                // }
            }
        }
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeNetworkReceive = zigbeeTransportReceive;
    }

    @Override
    public boolean notify(final AF_INCOMING_MSG clusterMessage) {
        ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        apsFrame.setCluster(clusterMessage.getClusterId());
        apsFrame.setDestinationEndpoint(clusterMessage.getDstEndpoint());
        apsFrame.setSourceEndpoint(clusterMessage.getSrcEndpoint());
        apsFrame.setProfile(getEndpointProfile(clusterMessage.getDstEndpoint()));

        // nwkHeader.setDestinationAddress(clusterMessage.geta);
        apsFrame.setSourceAddress(clusterMessage.getSrcAddr());
        apsFrame.setSequence(clusterMessage.getTransId());

        apsFrame.setPayload(clusterMessage.getData());

        zigbeeNetworkReceive.receiveCommand(apsFrame);

        return true;
    }

    @Override
    public void receivedAsynchronousCommand(ZToolPacket packet) {
        switch (packet.getCommandSubsystem()) {
            case AF:
                return;
            /*
             * logger.debug("AF!!!!!!!!!!!!");
             * AF_INCOMING_MSG afPacket = new AF_INCOMING_MSG(packet.getPacket());
             *
             * ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
             * apsFrame.setCluster(0);
             * apsFrame.setDestinationEndpoint(afPacket.getDstEndpoint());
             * apsFrame.setSourceEndpoint(afPacket.getSrcEndpoint());
             * apsFrame.setProfile(ApplicationFrameworkLayer.getAFLayer(networkManager)
             * .getSenderEndpointProfileId(afPacket.getDstEndpoint(), afPacket.getClusterId()));
             *
             * ZigBeeNwkHeader nwkHeader = new ZigBeeNwkHeader();
             * // nwkHeader.setDestinationAddress(clusterMessage.geta);
             * nwkHeader.setSourceAddress(afPacket.getSrcAddr());
             * nwkHeader.setSequence(afPacket.getTransId());
             */
            // zigbeeNetworkReceive.receiveCommand(apsFrame);
            // break;
            case ZDO:
                break;
            default:
                break;
        }

        ZigBeeApsFrame apsFrame = null;
        switch (packet.getCMD().get16BitValue()) {
            case ZToolCMD.ZDO_MSG_CB_INCOMING:
                apsFrame = ZdoCallbackIncoming.create(packet);
                break;

            case ZToolCMD.ZDO_IEEE_ADDR_RSP:
                apsFrame = ZdoIeeeAddress.create(packet);
                break;
            case ZToolCMD.ZDO_END_DEVICE_ANNCE_IND:
                apsFrame = ZdoEndDeviceAnnounce.create(packet);
                break;
            case ZToolCMD.ZDO_NODE_DESC_RSP:
                apsFrame = ZdoNodeDescriptor.create(packet);
                break;
            case ZToolCMD.ZDO_POWER_DESC_RSP:
                apsFrame = ZdoPowerDescriptor.create(packet);
                break;
            case ZToolCMD.ZDO_ACTIVE_EP_RSP:
                apsFrame = ZdoActiveEndpoint.create(packet);
                break;
            case ZToolCMD.ZDO_SIMPLE_DESC_RSP:
                apsFrame = ZdoSimpleDescriptor.create(packet);
                break;
            case ZToolCMD.ZDO_MGMT_LQI_RSP:
                apsFrame = ZdoManagementLqi.create(packet);
                break;
            case ZToolCMD.ZDO_MGMT_RTG_RSP:
                apsFrame = ZdoManagementRouting.create(packet);
                break;
            case ZToolCMD.ZDO_MGMT_LEAVE_RSP:
                apsFrame = ZdoManagementLeave.create(packet);
                break;
            default:
                logger.debug("Unhandled ZToolPacket type {}", packet.getCMD());
                break;
        }

        if (apsFrame != null) {
            zigbeeNetworkReceive.receiveCommand(apsFrame);

            return;
        }

        /*
         * if (packet.isError()) {
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_SIMPLE_DESC_RSP) {
         * final ZDO_SIMPLE_DESC_RSP message = (ZDO_SIMPLE_DESC_RSP) packet;
         *
         * final SimpleDescriptorResponse command = new SimpleDescriptorResponse();
         * SimpleDescriptor simpleDescriptor = new SimpleDescriptor();
         * command.setSourceAddress(new ZigBeeDeviceAddress(message.SrcAddress.get16BitValue()));
         * command.setStatus(message.Status);
         * command.setSimpleDescriptor(simpleDescriptor);
         * command.setNwkAddrOfInterest(message.nwkAddr.get16BitValue());
         * final short[] inputClusterShorts = message.getInputClustersList();
         * final List<Integer> inputClusters = new ArrayList<Integer>(message.getInputClustersCount());
         * for (int i = 0; i < message.getInputClustersCount(); i++) {
         * inputClusters.add((int) inputClusterShorts[i]);
         * }
         * simpleDescriptor.setInputClusterList(inputClusters);
         * final short[] outputClusterShorts = message.getOutputClustersList();
         * final List<Integer> outputClusters = new ArrayList<Integer>(message.getOutputClustersCount());
         * for (int i = 0; i < message.getOutputClustersCount(); i++) {
         * outputClusters.add((int) outputClusterShorts[i]);
         * }
         * simpleDescriptor.setOutputClusterList(outputClusters);
         *
         * simpleDescriptor.setProfileId(message.getProfileId() & 0xffff);
         * simpleDescriptor.setDeviceId(message.getDeviceId());
         * simpleDescriptor.setDeviceVersion(message.getDeviceVersion());
         * simpleDescriptor.setEndpoint(message.getEndPoint());
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_ACTIVE_EP_RSP) {
         * final ZDO_ACTIVE_EP_RSP message = (ZDO_ACTIVE_EP_RSP) packet;
         *
         * final ActiveEndpointsResponse command = new ActiveEndpointsResponse();
         * command.setSourceAddress(new ZigBeeDeviceAddress(message.SrcAddress.get16BitValue()));
         * command.setStatus(message.Status);
         * command.setNwkAddrOfInterest(message.nwkAddr.get16BitValue());
         *
         * final short[] activeEndPointList = message.getActiveEndPointList();
         * List<Integer> activeEndPoints = new ArrayList<Integer>();
         * for (int i = 0; i < activeEndPointList.length; i++) {
         * activeEndPoints.add((int) activeEndPointList[i]);
         * }
         * command.setActiveEpList(activeEndPoints);
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_END_DEVICE_ANNCE_IND) {
         * final ZDO_END_DEVICE_ANNCE_IND message = (ZDO_END_DEVICE_ANNCE_IND) packet;
         *
         * final DeviceAnnounce command = new DeviceAnnounce();
         * command.setSourceAddress(new ZigBeeDeviceAddress(message.SrcAddr.get16BitValue()));
         * command.setIeeeAddr(new IeeeAddress(message.IEEEAddr.getLong()));
         * command.setNwkAddrOfInterest(message.NwkAddr.get16BitValue());
         * command.setCapability(message.Capabilities);
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_IEEE_ADDR_RSP) {
         * final ZDO_IEEE_ADDR_RSP message = (ZDO_IEEE_ADDR_RSP) packet;
         *
         * final IeeeAddressResponse command = new IeeeAddressResponse();
         * command.setStatus(message.Status);
         * command.setIeeeAddrRemoteDev(new IeeeAddress(message.getIeeeAddress().getLong()));
         *
         * // message.SrcAddrMode;
         *
         * command.setIeeeAddrRemoteDev(new IeeeAddress(message.getIeeeAddress().getLong()));
         * command.setNwkAddrRemoteDev(message.nwkAddr.get16BitValue());
         *
         * // TODO: Once extended responses are handled
         * // command.setStartIndex(message.getStartIndex());
         *
         * final int[] deviceList = message.getAssociatedNodesList();
         * List<Integer> devices = new ArrayList<Integer>();
         * for (int i = 0; i < deviceList.length; i++) {
         * devices.add(deviceList[i]);
         * }
         * // command.setNwkAddrAssocDevList(devices);
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_NODE_DESC_RSP) {
         * final ZDO_NODE_DESC_RSP message = (ZDO_NODE_DESC_RSP) packet;
         * final NodeDescriptor nodeDescriptor = new NodeDescriptor(message.APSFlags, message.BufferSize,
         * message.Capabilities, message.ComplexDescriptorAvailable, message.ManufacturerCode.get16BitValue(),
         * message.NodeType, message.ServerMask, message.TransferSize.get16BitValue(),
         * message.UserDescriptorAvailable, message.FreqBand);
         * final NodeDescriptorResponse command = new NodeDescriptorResponse();
         * command.setStatus(message.Status);
         * command.setSourceAddress(new ZigBeeDeviceAddress(message.SrcAddress.get16BitValue()));
         * command.setNwkAddrOfInterest(message.nwkAddr.get16BitValue());
         * command.setNodeDescriptor(nodeDescriptor);
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_POWER_DESC_RSP) {
         * final ZDO_POWER_DESC_RSP message = (ZDO_POWER_DESC_RSP) packet;
         * final PowerDescriptor powerDescriptor = new PowerDescriptor(message.getCurrentMode(),
         * message.getAvailableSources(), message.getCurrentSource(), message.getCurrentLevel());
         *
         * final PowerDescriptorResponse command = new PowerDescriptorResponse();
         * command.setStatus(message.getStatus());
         * command.setSourceAddress(new ZigBeeDeviceAddress(message.getSrcAddress().get16BitValue()));
         * command.setPowerDescriptor(powerDescriptor);
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_MGMT_PERMIT_JOIN_RSP) {
         * final ZDO_MGMT_PERMIT_JOIN_RSP message = (ZDO_MGMT_PERMIT_JOIN_RSP) packet;
         *
         * final ManagementPermitJoiningResponse command = new ManagementPermitJoiningResponse();
         * command.setStatus(message.Status);
         * command.setSourceAddress(new ZigBeeDeviceAddress(message.SrcAddress.get16BitValue()));
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_BIND_RSP) {
         * final ZDO_BIND_RSP message = (ZDO_BIND_RSP) packet;
         *
         * final BindResponse command = new BindResponse();
         * command.setStatus(message.Status);
         * command.setSourceAddress(new ZigBeeDeviceAddress(message.SrcAddress.get16BitValue()));
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_UNBIND_RSP) {
         * final ZDO_UNBIND_RSP message = (ZDO_UNBIND_RSP) packet;
         *
         * final UnbindResponse command = new UnbindResponse();
         * command.setStatus(message.Status);
         * command.setSourceAddress(new ZigBeeDeviceAddress(message.SrcAddress.get16BitValue()));
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_USER_DESC_RSP) {
         * final ZDO_USER_DESC_RSP message = (ZDO_USER_DESC_RSP) packet;
         *
         * final byte[] bytes = new byte[message.DescLen];
         * for (int i = 0; i < bytes.length; i++) {
         * bytes[i] = (byte) message.Descriptor[i];
         * }
         * final String descriptor = new String(bytes, Charset.forName("ASCII"));
         * final UserDescriptorResponse command = new UserDescriptorResponse();
         * command.setStatus(message.Status);
         * command.setSourceAddress(message.SrcAddress.get16BitValue());
         * command.setNwkAddrOfInterest(message.nwkAddr.get16BitValue());
         * command.setUserDescriptor(descriptor);
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         *
         * // if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_USER_DESC_CONF) {
         * // final ZDO_USER_DESC_CONF message = (ZDO_USER_DESC_CONF) packet;
         *
         * // final UserDescriptorConfiguration command = new UserDescriptorConfiguration(
         * // message.SrcAddress.get16BitValue(), message.nwkAddr.get16BitValue(), message.Status);
         *
         * // zigbeeNetworkReceive.receiveZdoCommand(command);
         * // return;
         * // }
         *
         *
         * if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_MGMT_LQI_RSP) {
         * final ZDO_MGMT_LQI_RSP message = (ZDO_MGMT_LQI_RSP) packet;
         *
         * final ZDO_MGMT_LQI_RSP.NeighborLqiListItemClass[] neighborItems = message.getNeighborLqiList();
         * final ManagementLqiResponse.Neighbor[] neighbors = new ManagementLqiResponse.Neighbor[neighborItems.length];
         * for (int i = 0; i < neighbors.length; i++) {
         * neighbors[i] = new ManagementLqiResponse.Neighbor(neighborItems[i].Depth,
         * neighborItems[i].ExtendedPanID, neighborItems[i].ExtendedAddress.getLong(),
         * neighborItems[i].NetworkAddress.get16BitValue(),
         * neighborItems[i].Reserved_Relationship_RxOnWhenIdle_DeviceType,
         * neighborItems[i].Reserved_PermitJoining, neighborItems[i].RxLQI);
         * }
         *
         * final ManagementLqiResponse command = new ManagementLqiResponse(message.Status,
         * message.SrcAddress.get16BitValue(), message.getStartIndex(), message.getNeighborLQICount(),
         * neighbors);
         *
         * zigbeeNetworkReceive.receiveZdoCommand(command);
         * return;
         * }
         *
         *
         * // TODO: Implement state change handler
         * // if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_STATE_CHANGE_IND) {
         * // final ZDO_STATE_CHANGE_IND message = (ZDO_STATE_CHANGE_IND) packet;
         * // }
         */
        logger.debug("ZToolPacket packet not processed: {}", packet);

    }

    @Override
    public void receivedUnclaimedSynchronousCommandResponse(ZToolPacket packet) {
        if (packet.getClass().getSimpleName().endsWith("SRSP")) {
            final SynchronousResponse response = new SynchronousResponse();
            response.setType(packet.getClass().getSimpleName());

            try {
                final Class<?> packetClass = packet.getClass();
                final Field statusField = packetClass.getDeclaredField("Status");
                response.setStatus(statusField.getInt(packet));
            } catch (final NoSuchFieldException e) {
                logger.error("Error reading status from synchronous response.", e);
                return;
            } catch (final IllegalAccessException e) {
                logger.error("Error reading status from synchronous response.", e);
                return;
            }
            if (response.getStatus() != 0) {
                logger.error("Sychronours response error: " + response);
            }
        }
    }

    private int getSendingEndpoint(int profileId) {
        synchronized (sender2EndPoint) {
            if (sender2EndPoint.containsKey(profileId)) {
                return sender2EndPoint.get(profileId);
            } else {
                logger.info("No endpoint registered for profileId={}", profileId);
                // final byte ep = createEndPoint( profileId);
                return -1;
            }
        }
    }

    private int getEndpointProfile(int endpointId) {
        synchronized (endpoint2Profile) {
            if (endpoint2Profile.containsKey(endpointId)) {
                return endpoint2Profile.get(endpointId);
            } else {
                logger.info("No endpoint {} registered", endpointId);
                // final byte ep = createEndPoint( profileId);
                return -1;
            }
        }
    }

    private int createEndPoint(int endpointId, int profileId) {
        logger.trace("Registering a new endpoint {} for profile {}", endpointId, profileId);

        AF_REGISTER_SRSP result;
        result = networkManager.sendAFRegister(
                new AF_REGISTER(endpointId, profileId, (short) 0, (byte) 0, new int[] {}, new int[] {}));
        // FIX We should retry only when Status != 0xb8 ( Z_APS_DUPLICATE_ENTRY )
        if (result.getStatus() != 0) {
            // TODO We should provide a workaround for the maximum number of registered EndPoint
            // For example, with the CC2480 we could reset the dongle
            throw new IllegalStateException("Unable create a new Endpoint. AF_REGISTER command failed with "
                    + result.getStatus() + ":" + result.getErrorMsg());
        }

        sender2EndPoint.put(profileId, endpointId);
        endpoint2Profile.put(endpointId, profileId);

        logger.debug("Registered endpoint {} with profile: {}", endpointId, profileId);

        return endpointId;
    }

}
