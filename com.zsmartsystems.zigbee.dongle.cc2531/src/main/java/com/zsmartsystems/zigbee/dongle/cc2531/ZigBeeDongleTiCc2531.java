package com.zsmartsystems.zigbee.dongle.cc2531;

import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeApsHeader;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeNwkHeader;
import com.zsmartsystems.zigbee.ZigBeePort;
import com.zsmartsystems.zigbee.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.ZigBeeTransportState;
import com.zsmartsystems.zigbee.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.dongle.cc2531.network.ApplicationFrameworkMessageListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.AsynchronousCommandListener;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ApplicationFrameworkLayer;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ZigBeeNetworkManagerException;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.ZigBeeNetworkManagerImpl;
import com.zsmartsystems.zigbee.dongle.cc2531.network.model.DriverStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.model.NetworkMode;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ResponseStatus;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_CONFIRM;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST_EXT;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_SRSP_EXT;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_ACTIVE_EP_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_ACTIVE_EP_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_BIND_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_END_DEVICE_ANNCE_IND;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_IEEE_ADDR_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_IEEE_ADDR_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LQI_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_LQI_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_PERMIT_JOIN_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_MGMT_PERMIT_JOIN_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_NODE_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_NODE_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_POWER_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_POWER_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_SIMPLE_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_UNBIND_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_USER_DESC_CONF;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_USER_DESC_REQ;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_USER_DESC_RSP;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.zdo.ZDO_USER_DESC_SET;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress16;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.ZToolAddress64;
import com.zsmartsystems.zigbee.zdo.SynchronousResponse;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsRequest;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;
import com.zsmartsystems.zigbee.zdo.command.BindRequest;
import com.zsmartsystems.zigbee.zdo.command.BindResponse;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoinRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoinResponse;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.UnbindRequest;
import com.zsmartsystems.zigbee.zdo.command.UnbindResponse;
import com.zsmartsystems.zigbee.zdo.command.UserDescriptorConfiguration;
import com.zsmartsystems.zigbee.zdo.command.UserDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.UserDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.UserDescriptorSet;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;

/**
 * ZigBee Dongle TI CC2531 implementation.
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
                Thread.sleep(10);
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
    public void sendZclCommand(ZigBeeNwkHeader nwkHeader, ZigBeeApsHeader apsHeader, int[] payload)
            throws ZigBeeException {
        synchronized (networkManager) {
            final short sender = ApplicationFrameworkLayer.getAFLayer(networkManager)
                    .getSendingEndpoint(apsHeader.getProfile(), apsHeader.getCluster());

            // TODO: How to differentiate group and device addressing?????
            boolean groupCommand = false;
            if (!groupCommand) {
                final AF_DATA_CONFIRM response = networkManager.sendAFDataRequest(new AF_DATA_REQUEST(
                        nwkHeader.getDestinationAddress(), (short) apsHeader.getDestinationEndpoint(), sender,
                        apsHeader.getCluster(), nwkHeader.getSequence(), (byte) 0, (byte) 0, payload));
                if (response == null) {
                    throw new ZigBeeException("Unable to send cluster on the ZigBee network due to general error.");
                }
                if (response.getStatus() != 0) {
                    throw new ZigBeeException("Unable to send cluster on the ZigBee network due to: "
                            + ResponseStatus.getStatus(response.getStatus()) + " "
                            + (response.getErrorMsg() != null ? " - " + response.getErrorMsg() : "") + ")");
                }
            } else {
                final AF_DATA_SRSP_EXT response = networkManager
                        .sendAFDataRequestExt(new AF_DATA_REQUEST_EXT(nwkHeader.getDestinationAddress(), sender,
                                apsHeader.getCluster(), nwkHeader.getSequence(), (byte) (0), (byte) 0, payload));
                if (response.getStatus() != 0) {
                    throw new ZigBeeException("Unable to send cluster on the ZigBee network due to: "
                            + ResponseStatus.getStatus(response.getStatus()));
                }
            }
        }
    }

    private ZToolAddress16 getZToolAddress16(int networkAddress) {
        return new ZToolAddress16(Integers.getByteAsInteger(networkAddress, 1),
                Integers.getByteAsInteger(networkAddress, 0));
    }

    private ZToolAddress64 getZToolAddress64(long networkAddress) {
        return new ZToolAddress64(networkAddress);
    }

    /**
     * Sends ZDO command message.
     *
     * @param command the {@link ZdoCommand}
     * @throws ZigBeeNetworkManagerException
     */
    @Override
    public void sendZdoCommand(final ZdoCommand command) throws ZigBeeException {
        synchronized (networkManager) {
            if (command instanceof ActiveEndpointsRequest) {
                final ActiveEndpointsRequest activeEndpointsRequest = (ActiveEndpointsRequest) command;
                networkManager.sendCommand(
                        new ZDO_ACTIVE_EP_REQ(getZToolAddress16(activeEndpointsRequest.getDestinationAddress()),
                                getZToolAddress16(activeEndpointsRequest.getNetworkAddressOfInterest())));
            }
            if (command instanceof IeeeAddressRequest) {
                final IeeeAddressRequest ieeeAddressRequest = (IeeeAddressRequest) command;
                networkManager
                        .sendCommand(new ZDO_IEEE_ADDR_REQ(getZToolAddress16(ieeeAddressRequest.getNetworkAddress()),
                                ieeeAddressRequest.getType(), ieeeAddressRequest.getStartIndex()));
            }
            if (command instanceof SimpleDescriptorRequest) {
                final SimpleDescriptorRequest simpleDescriptorRequest = (SimpleDescriptorRequest) command;
                networkManager
                        .sendCommand(new ZDO_SIMPLE_DESC_REQ((short) simpleDescriptorRequest.getDestinationAddress(),
                                (short) simpleDescriptorRequest.getEndpoint()));
            }
            if (command instanceof NodeDescriptorRequest) {
                final NodeDescriptorRequest nodeDescriptorRequest = (NodeDescriptorRequest) command;
                networkManager.sendCommand(
                        new ZDO_NODE_DESC_REQ(getZToolAddress16(nodeDescriptorRequest.getDestinationAddress()),
                                getZToolAddress16(nodeDescriptorRequest.getNetworkAddressOfInterest())));
            }
            if (command instanceof PowerDescriptorRequest) {
                final PowerDescriptorRequest powerDescriptorRequest = (PowerDescriptorRequest) command;
                networkManager
                        .sendCommand(new ZDO_POWER_DESC_REQ((short) powerDescriptorRequest.getDestinationAddress()));
            }
            if (command instanceof ManagementPermitJoinRequest) {
                final ManagementPermitJoinRequest managementPermitJoinRequest = (ManagementPermitJoinRequest) command;
                networkManager.sendCommand(
                        new ZDO_MGMT_PERMIT_JOIN_REQ((byte) managementPermitJoinRequest.getAddressingMode(),
                                getZToolAddress16(managementPermitJoinRequest.getDestinationAddress()),
                                managementPermitJoinRequest.getDuration(),
                                managementPermitJoinRequest.getTrustCenterSignificance()));
            }
            if (command instanceof BindRequest) {
                final BindRequest bindRequest = (BindRequest) command;
                networkManager.sendCommand(new ZDO_BIND_REQ(getZToolAddress16(bindRequest.getDestinationAddress()),
                        getZToolAddress64(bindRequest.getBindSourceAddress()), bindRequest.getBindSourceEndpoint(),
                        new DoubleByte(bindRequest.getBindCluster()), bindRequest.getBindDestinationAddressingMode(),
                        getZToolAddress64(bindRequest.getBindDestinationAddress()),
                        bindRequest.getBindDestinationEndpoint()));
            }
            if (command instanceof UnbindRequest) {
                final UnbindRequest unbindRequest = (UnbindRequest) command;
                networkManager.sendCommand(new ZDO_UNBIND_REQ(getZToolAddress16(unbindRequest.getDestinationAddress()),
                        getZToolAddress64(unbindRequest.getBindSourceAddress()), unbindRequest.getBindSourceEndpoint(),
                        new DoubleByte(unbindRequest.getBindCluster()),
                        unbindRequest.getBindDestinationAddressingMode(),
                        getZToolAddress64(unbindRequest.getBindDestinationAddress()),
                        unbindRequest.getBindDestinationEndpoint()));
            }
            if (command instanceof UserDescriptorSet) {
                final UserDescriptorSet userDescriptorSet = (UserDescriptorSet) command;
                final byte[] bytes = userDescriptorSet.getDescriptor().getBytes(Charset.forName("ASCII"));
                int length = bytes.length;
                if (length > 16) {
                    length = 16;
                }
                final int[] characters = new int[length];
                for (int i = 0; i < characters.length; i++) {
                    characters[i] = bytes[i];
                }
                networkManager
                        .sendCommand(new ZDO_USER_DESC_SET(getZToolAddress16(userDescriptorSet.getDestinationAddress()),
                                getZToolAddress16(userDescriptorSet.getNetworkAddress()), length, characters));
            }
            if (command instanceof UserDescriptorRequest) {
                final UserDescriptorRequest userDescriptorRequest = (UserDescriptorRequest) command;
                networkManager.sendCommand(
                        new ZDO_USER_DESC_REQ(getZToolAddress16(userDescriptorRequest.getDestinationAddress()),
                                getZToolAddress16(userDescriptorRequest.getNetworkAddressOfInterest())));
            }
            if (command instanceof ManagementLqiRequest) {
                final ManagementLqiRequest managementLqiRequest = (ManagementLqiRequest) command;
                networkManager
                        .sendCommand(new ZDO_MGMT_LQI_REQ(getZToolAddress16(managementLqiRequest.getNetworkAddress()),
                                managementLqiRequest.getStartIndex()));
            }
        }
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeNetworkReceive = zigbeeTransportReceive;
    }

    @Override
    public boolean notify(final AF_INCOMING_MSG clusterMessage) {
        ZigBeeApsHeader apsHeader = new ZigBeeApsHeader();
        apsHeader.setCluster(clusterMessage.getClusterId());
        apsHeader.setDestinationEndpoint(clusterMessage.getDstEndpoint());
        apsHeader.setSourceEndpoint(clusterMessage.getSrcEndpoint());
        apsHeader.setProfile(ApplicationFrameworkLayer.getAFLayer(networkManager)
                .getSenderEndpointProfileId(clusterMessage.getDstEndpoint(), clusterMessage.getClusterId()));

        ZigBeeNwkHeader nwkHeader = new ZigBeeNwkHeader();
        // nwkHeader.setDestinationAddress(clusterMessage.geta);
        nwkHeader.setSourceAddress(clusterMessage.getSrcAddr());
        nwkHeader.setSequence(clusterMessage.getTransId());

        zigbeeNetworkReceive.receiveZclCommand(nwkHeader, apsHeader, clusterMessage.getData());

        return true;
    }

    @Override
    public void receivedAsynchronousCommand(ZToolPacket packet) {
        if (packet.isError()) {
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_SIMPLE_DESC_RSP) {
            final ZDO_SIMPLE_DESC_RSP message = (ZDO_SIMPLE_DESC_RSP) packet;

            final SimpleDescriptorResponse command = new SimpleDescriptorResponse();
            command.setSourceAddress(message.SrcAddress.get16BitValue());
            command.setStatus(message.Status);
            command.setProfileId(message.getProfileId() & 0xffff);
            command.setDeviceId(message.getDeviceId());
            command.setDeviceVersion(message.getDeviceVersion());
            command.setNetworkAddress(message.nwkAddr.get16BitValue());
            command.setEndpoint(message.getEndPoint());
            final short[] inputClusterShorts = message.getInputClustersList();
            final List<Integer> inputClusters = new ArrayList<Integer>(message.getInputClustersCount());
            for (int i = 0; i < message.getInputClustersCount(); i++) {
                inputClusters.add((int) inputClusterShorts[i]);
            }
            command.setInputClusters(inputClusters);
            final short[] outputClusterShorts = message.getOutputClustersList();
            final List<Integer> outputClusters = new ArrayList<Integer>(message.getOutputClustersCount());
            for (int i = 0; i < message.getOutputClustersCount(); i++) {
                outputClusters.add((int) outputClusterShorts[i]);
            }
            command.setOutputClusters(outputClusters);

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_ACTIVE_EP_RSP) {
            final ZDO_ACTIVE_EP_RSP message = (ZDO_ACTIVE_EP_RSP) packet;

            final ActiveEndpointsResponse command = new ActiveEndpointsResponse();
            command.setSourceAddress(message.SrcAddress.get16BitValue());
            command.setStatus(message.Status);
            command.setNetworkAddress(message.nwkAddr.get16BitValue());

            final short[] activeEndPointList = message.getActiveEndPointList();
            final int[] activeEndPoints = new int[activeEndPointList.length];
            for (int i = 0; i < activeEndPoints.length; i++) {
                activeEndPoints[i] = activeEndPointList[i];
            }
            command.setActiveEndpoints(activeEndPoints);

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_END_DEVICE_ANNCE_IND) {
            final ZDO_END_DEVICE_ANNCE_IND message = (ZDO_END_DEVICE_ANNCE_IND) packet;

            final DeviceAnnounce command = new DeviceAnnounce(message.SrcAddr.get16BitValue(),
                    message.IEEEAddr.getLong(), message.NwkAddr.get16BitValue(), message.Capabilities);

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_IEEE_ADDR_RSP) {
            final ZDO_IEEE_ADDR_RSP message = (ZDO_IEEE_ADDR_RSP) packet;

            final IeeeAddressResponse command = new IeeeAddressResponse(message.Status, message.SrcAddrMode,
                    new IeeeAddress(message.getIeeeAddress().getLong()), message.nwkAddr.get16BitValue(),
                    message.getStartIndex(), message.getAssociatedNodeCount(), message.getAssociatedNodesList());

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_NODE_DESC_RSP) {
            final ZDO_NODE_DESC_RSP message = (ZDO_NODE_DESC_RSP) packet;
            final NodeDescriptor nodeDescriptor = new NodeDescriptor(message.APSFlags, message.BufferSize,
                    message.Capabilities, message.ComplexDescriptorAvailable, message.ManufacturerCode.get16BitValue(),
                    message.NodeType, message.ServerMask, message.TransferSize.get16BitValue(),
                    message.UserDescriptorAvailable, message.FreqBand);
            final NodeDescriptorResponse command = new NodeDescriptorResponse(message.Status,
                    message.SrcAddress.get16BitValue(), message.nwkAddr.get16BitValue(), nodeDescriptor);

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_POWER_DESC_RSP) {
            final ZDO_POWER_DESC_RSP message = (ZDO_POWER_DESC_RSP) packet;
            final PowerDescriptor powerDescriptor = new PowerDescriptor(message.getCurrentMode(),
                    message.getAvailableSources(), message.getCurrentSource(), message.getCurrentLevel());

            final PowerDescriptorResponse command = new PowerDescriptorResponse(message.getStatus(),
                    message.getSrcAddress().get16BitValue(), powerDescriptor);

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_MGMT_PERMIT_JOIN_RSP) {
            final ZDO_MGMT_PERMIT_JOIN_RSP message = (ZDO_MGMT_PERMIT_JOIN_RSP) packet;

            final ManagementPermitJoinResponse command = new ManagementPermitJoinResponse(message.Status,
                    message.SrcAddress.get16BitValue());

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_BIND_RSP) {
            final ZDO_BIND_RSP message = (ZDO_BIND_RSP) packet;

            final BindResponse command = new BindResponse(message.Status, message.SrcAddress.get16BitValue());

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_UNBIND_RSP) {
            final ZDO_UNBIND_RSP message = (ZDO_UNBIND_RSP) packet;

            final UnbindResponse command = new UnbindResponse(message.Status, message.SrcAddress.get16BitValue());

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_USER_DESC_RSP) {
            final ZDO_USER_DESC_RSP message = (ZDO_USER_DESC_RSP) packet;

            final byte[] bytes = new byte[message.DescLen];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) message.Descriptor[i];
            }
            final String descriptor = new String(bytes, Charset.forName("ASCII"));
            final UserDescriptorResponse command = new UserDescriptorResponse(message.SrcAddress.get16BitValue(),
                    message.nwkAddr.get16BitValue(), message.Status, descriptor);

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_USER_DESC_CONF) {
            final ZDO_USER_DESC_CONF message = (ZDO_USER_DESC_CONF) packet;

            final UserDescriptorConfiguration command = new UserDescriptorConfiguration(
                    message.SrcAddress.get16BitValue(), message.nwkAddr.get16BitValue(), message.Status);

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_MGMT_LQI_RSP) {
            final ZDO_MGMT_LQI_RSP message = (ZDO_MGMT_LQI_RSP) packet;

            final ZDO_MGMT_LQI_RSP.NeighborLqiListItemClass[] neighborItems = message.getNeighborLqiList();
            final ManagementLqiResponse.Neighbor[] neighbors = new ManagementLqiResponse.Neighbor[neighborItems.length];
            for (int i = 0; i < neighbors.length; i++) {
                neighbors[i] = new ManagementLqiResponse.Neighbor(neighborItems[i].Depth,
                        neighborItems[i].ExtendedPanID, neighborItems[i].ExtendedAddress.getLong(),
                        neighborItems[i].NetworkAddress.get16BitValue(),
                        neighborItems[i].Reserved_Relationship_RxOnWhenIdle_DeviceType,
                        neighborItems[i].Reserved_PermitJoining, neighborItems[i].RxLQI);
            }

            final ManagementLqiResponse command = new ManagementLqiResponse(message.Status,
                    message.SrcAddress.get16BitValue(), message.getStartIndex(), message.getNeighborLQICount(),
                    neighbors);

            zigbeeNetworkReceive.receiveZdoCommand(command);
            return;
        }

        // TODO: Implement state change handler
        // if (packet.getCMD().get16BitValue() == ZToolCMD.ZDO_STATE_CHANGE_IND) {
        // final ZDO_STATE_CHANGE_IND message = (ZDO_STATE_CHANGE_IND) packet;
        // }
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
