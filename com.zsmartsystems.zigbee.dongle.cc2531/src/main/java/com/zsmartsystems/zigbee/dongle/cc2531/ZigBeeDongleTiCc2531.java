package com.zsmartsystems.zigbee.dongle.cc2531;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeePort;
import com.zsmartsystems.zigbee.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.ZigBeeTransportState;
import com.zsmartsystems.zigbee.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoActiveEndpoint;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoEndDeviceAnnounce;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoIeeeAddress;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoManagementLqi;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoManagementRouting;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoNodeDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoPowerDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoSimpleDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.AsynchronousCommandListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ApplicationFrameworkLayer;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ZigBeeNetworkManagerImpl;
import com.zsmartsystems.zigbee.dongle.cc2531.network.model.DriverStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.model.NetworkMode;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST_EXT;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG;
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
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeDongleTiCc2531.class);

    /**
     * The {@link ZigBeeNetworkManagerImpl ZigBee network manager}.
     */
    private final ZigBeeNetworkManagerImpl networkManager;

    /**
     * The reference to the network
     */
    private ZigBeeTransportReceive zigbeeNetworkReceive;

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
    public boolean initialize() {
        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        if (!networkManager.startup()) {
            return false;
        }

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

        return networkManager.initializeZigBeeNetwork();
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
        return networkManager.getExtendedPanId();
    }

    @Override
    public boolean setZigBeeExtendedPanId(long panId) {
        return networkManager.setZigBeeExtendedPanId(panId);
    }

    @Override
    public boolean setZigBeeSecurityKey(int[] keyData) {
        // TODO: Fix!
        return networkManager.setNetworkKey(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });// keyData);
    }

    @Override
    public boolean startup() {
        // Add listeners for ZCL and ZDO received messages
        networkManager.addAFMessageListener(this);
        networkManager.addAsynchronousCommandListener(this);

        // if (!networkManager.initializeZigBeeNetwork()) {
        // zigbeeNetworkReceive.setNetworkState(TransportState.UNINITIALISED);
        // return false;
        // }

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

        ApplicationFrameworkLayer.getAFLayer(networkManager).createDefaultSendingEndPoint();

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.ONLINE);

        return true;
    }

    @Override
    public void shutdown() {
        networkManager.shutdown();
    }

    @Override
    public void sendCommand(final ZigBeeApsFrame apsFrame) throws ZigBeeException {
        synchronized (networkManager) {
            final short sender;
            if (apsFrame.getProfile() == 0) {
                sender = 0;
            } else {
                sender = ApplicationFrameworkLayer.getAFLayer(networkManager).getSendingEndpoint(apsFrame.getProfile(),
                        apsFrame.getCluster());
            }

            // TODO: How to differentiate group and device addressing?????
            boolean groupCommand = false;
            if (!groupCommand) {
                // final AF_DATA_CONFIRM response =
                networkManager.sendCommand(new AF_DATA_REQUEST(apsFrame.getDestinationAddress(),
                        (short) apsFrame.getDestinationEndpoint(), sender, apsFrame.getCluster(),
                        apsFrame.getSequence(), (byte) 0, (byte) 0, apsFrame.getPayload()));
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

    /**
     * Sends ZDO command message.
     *
     * @param command the {@link ZdoCommand}
     * @throws ZigBeeNetworkManagerException
     */
    /**
     * @Override
     *           public void sendZdoCommand(final ZdoCommand command) throws ZigBeeException {
     *           synchronized (networkManager) {
     *           if (command instanceof ActiveEndpointsRequest) {
     *           final ActiveEndpointsRequest activeEndpointsRequest = (ActiveEndpointsRequest) command;
     *           networkManager.sendCommand(new ZDO_ACTIVE_EP_REQ(
     *           getZToolAddress16(activeEndpointsRequest.getDestinationAddress().getAddress()),
     *           getZToolAddress16(activeEndpointsRequest.getNwkAddrOfInterest())));
     *           }
     *           if (command instanceof IeeeAddressRequest) {
     *           final IeeeAddressRequest ieeeAddressRequest = (IeeeAddressRequest) command;
     *           networkManager.sendCommand(new ZDO_IEEE_ADDR_REQ(
     *           getZToolAddress16(ieeeAddressRequest.getDestinationAddress().getAddress()), 1, 0));
     *           }
     *           if (command instanceof SimpleDescriptorRequest) {
     *           final SimpleDescriptorRequest simpleDescriptorRequest = (SimpleDescriptorRequest) command;
     *           networkManager.sendCommand(
     *           new ZDO_SIMPLE_DESC_REQ((short) simpleDescriptorRequest.getDestinationAddress().getAddress(),
     *           simpleDescriptorRequest.getEndpoint().shortValue()));
     *           }
     *           if (command instanceof NodeDescriptorRequest) {
     *           final NodeDescriptorRequest nodeDescriptorRequest = (NodeDescriptorRequest) command;
     *           networkManager.sendCommand(
     *           new ZDO_NODE_DESC_REQ(getZToolAddress16(nodeDescriptorRequest.getNwkAddrOfInterest()),
     *           getZToolAddress16(nodeDescriptorRequest.getNwkAddrOfInterest())));
     *           }
     *           if (command instanceof PowerDescriptorRequest) {
     *           final PowerDescriptorRequest powerDescriptorRequest = (PowerDescriptorRequest) command;
     *           networkManager.sendCommand(
     *           new ZDO_POWER_DESC_REQ((short) powerDescriptorRequest.getDestinationAddress().getAddress()));
     *           }
     *           if (command instanceof ManagementPermitJoiningRequest) {
     *           final ManagementPermitJoiningRequest managementPermitJoinRequest = (ManagementPermitJoiningRequest)
     *           command;
     *           networkManager.sendCommand(new ZDO_MGMT_PERMIT_JOIN_REQ((byte) 2, // (byte)
     *           // managementPermitJoinRequest.getAddressingMode(),
     *           getZToolAddress16(managementPermitJoinRequest.getDestinationAddress().getAddress()),
     *           managementPermitJoinRequest.getPermitDuration(),
     *           managementPermitJoinRequest.getTcSignificance()));
     *           }
     *
     *           if (command instanceof BindRequest) {
     *           final BindRequest bindRequest = (BindRequest) command;
     *           networkManager.sendCommand(new ZDO_BIND_REQ(
     *           getZToolAddress16(bindRequest.getDestinationAddress().getAddress()),
     *           getZToolAddress64(bindRequest.getSrcAddress().getLong()), bindRequest.getSrcEndpoint(),
     *           new DoubleByte(bindRequest.getClusterId()), bindRequest.getDstAddrMode(),
     *           getZToolAddress64(bindRequest.getDstAddress().getLong()), bindRequest.getDstEndpoint()));
     *           }
     *
     *           if (command instanceof UnbindRequest) {
     *           final UnbindRequest unbindRequest = (UnbindRequest) command;
     *           networkManager.sendCommand(new ZDO_UNBIND_REQ(
     *           getZToolAddress16(unbindRequest.getDestinationAddress().getAddress()),
     *           getZToolAddress64(unbindRequest.getSrcAddress().getLong()), unbindRequest.getSrcEndpoint(),
     *           new DoubleByte(unbindRequest.getClusterId()), unbindRequest.getDstAddrMode(),
     *           getZToolAddress64(unbindRequest.getDstAddress().getLong()), unbindRequest.getDstEndpoint()));
     *           }
     *
     *
     *           if (command instanceof UserDescriptorSet) {
     *           final UserDescriptorSet userDescriptorSet = (UserDescriptorSet) command;
     *           final byte[] bytes = userDescriptorSet.getDescriptor().getBytes(Charset.forName("ASCII"));
     *           int length = bytes.length;
     *           if (length > 16) {
     *           length = 16;
     *           }
     *           final int[] characters = new int[length];
     *           for (int i = 0; i < characters.length; i++) {
     *           characters[i] = bytes[i];
     *           }
     *           networkManager
     *           .sendCommand(new ZDO_USER_DESC_SET(getZToolAddress16(userDescriptorSet.getDestinationAddress()),
     *           getZToolAddress16(userDescriptorSet.getSourceAddress()), length, characters));
     *           }
     *
     *           if (command instanceof UserDescriptorRequest) {
     *           final UserDescriptorRequest userDescriptorRequest = (UserDescriptorRequest) command;
     *           networkManager.sendCommand(
     *           new ZDO_USER_DESC_REQ(getZToolAddress16(userDescriptorRequest.getNwkAddrOfInterest()),
     *           getZToolAddress16(userDescriptorRequest.getNwkAddrOfInterest())));
     *           }
     *           if (command instanceof ManagementLqiRequest) {
     *           final ManagementLqiRequest managementLqiRequest = (ManagementLqiRequest) command;
     *           networkManager.sendCommand(new ZDO_MGMT_LQI_REQ(
     *           getZToolAddress16(managementLqiRequest.getDestinationAddress().getAddress()),
     *           managementLqiRequest.getStartIndex()));
     *           }
     *           }
     *           }
     */

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
        apsFrame.setProfile(ApplicationFrameworkLayer.getAFLayer(networkManager)
                .getSenderEndpointProfileId(clusterMessage.getDstEndpoint(), clusterMessage.getClusterId()));

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
        logger.debug("ZToolPacket packet not processed", packet);

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

}
