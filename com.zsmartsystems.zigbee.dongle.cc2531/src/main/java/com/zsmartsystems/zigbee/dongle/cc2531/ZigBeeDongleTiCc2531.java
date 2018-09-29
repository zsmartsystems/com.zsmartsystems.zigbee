/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeStatus;
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
import com.zsmartsystems.zigbee.dongle.cc2531.network.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.cc2531.network.impl.CommandInterfaceImpl;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_DATA_REQUEST_EXT;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_INCOMING_MSG;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af.AF_REGISTER_SRSP;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zdo.SynchronousResponse;

/**
 * ZigBee Dongle TI implementation for the CC2531 processor.
 * <p>
 * Implements the {@link ZigBeeTransportTransmit} interface and provides the following dongle specific methods for
 * configuring the dongle -:
 * <ul>
 * <li>{@link #setMagicNumber(int)} to set the bootloader exit sequence
 * </ul>
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
     * The {@link ZigBeeNetworkManager ZigBee network manager}.
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The reference to the network
     */
    private ZigBeeTransportReceive zigbeeNetworkReceive;

    private final HashMap<Integer, Integer> sender2EndPoint = new HashMap<Integer, Integer>();
    private final HashMap<Integer, Integer> endpoint2Profile = new HashMap<Integer, Integer>();

    /**
     * The IeeeAddress of the Ember NCP
     */
    private IeeeAddress ieeeAddress;

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
        networkManager = new ZigBeeNetworkManager(new CommandInterfaceImpl(serialPort), NetworkMode.Coordinator, 2500L);
    }

    /**
     * Different hardware may use a different "Magic Number" to skip waiting in the bootloader. Otherwise
     * the dongle may wait in the bootloader for 60 seconds after it's powered on or reset.
     * <p>
     * This method allows the user to change the magic number which may be required when using different
     * sticks.
     *
     * @param magicNumber
     */
    public void setMagicNumber(int magicNumber) {
        networkManager.setMagicNumber(magicNumber);
    }

    @Override
    public ZigBeeStatus initialize() {
        logger.debug("CC2531 transport initialize");

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        // This basically just initialises the hardware so we can communicate with the 2531
        versionString = networkManager.startup();
        if (versionString == null) {
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    @Override
    public ZigBeeChannel getZigBeeChannel() {
        return ZigBeeChannel.create(networkManager.getCurrentChannel());
    }

    @Override
    public ZigBeeStatus setZigBeeChannel(ZigBeeChannel channel) {
        return networkManager.setZigBeeChannel(channel.getChannel()) ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE;
    }

    @Override
    public int getZigBeePanId() {
        return networkManager.getCurrentPanId();
    }

    @Override
    public ZigBeeStatus setZigBeePanId(int panId) {
        return networkManager.setZigBeePanId(panId) ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE;
    }

    @Override
    public ExtendedPanId getZigBeeExtendedPanId() {
        return networkManager.getCurrentExtendedPanId();
    }

    @Override
    public ZigBeeStatus setZigBeeExtendedPanId(ExtendedPanId panId) {
        return networkManager.setZigBeeExtendedPanId(panId) ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE;
    }

    @Override
    public ZigBeeStatus setZigBeeNetworkKey(ZigBeeKey key) {
        byte[] keyData = new byte[16];
        int cnt = 0;
        for (int keyVal : key.getValue()) {
            keyData[cnt] = (byte) keyVal;
        }
        return networkManager.setNetworkKey(keyData) ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE;
    }

    @Override
    public ZigBeeStatus setTcLinkKey(ZigBeeKey key) {
        return ZigBeeStatus.FAILURE;
    }

    @Override
    public void updateTransportConfig(TransportConfig configuration) {
        for (TransportConfigOption option : configuration.getOptions()) {
            try {
                switch (option) {
                    default:
                        configuration.setResult(option, ZigBeeStatus.UNSUPPORTED);
                        logger.debug("Unsupported configuration option \"{}\" in Telegesis dongle", option);
                        break;
                }
            } catch (ClassCastException e) {
                configuration.setResult(option, ZigBeeStatus.INVALID_ARGUMENTS);
            }
        }
    }

    @Override
    public ZigBeeStatus startup(boolean reinitialize) {
        logger.debug("CC2531 transport startup");

        // Add listeners for ZCL and ZDO received messages
        networkManager.addAFMessageListener(this);
        networkManager.addAsynchronousCommandListener(this);

        if (!networkManager.initializeZigBeeNetwork(reinitialize)) {
            zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);
            return ZigBeeStatus.INVALID_STATE;
        }

        while (true) {
            if (networkManager.getDriverStatus() == DriverStatus.NETWORK_READY) {
                break;
            }
            if (networkManager.getDriverStatus() == DriverStatus.CLOSED) {
                zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);
                return ZigBeeStatus.BAD_RESPONSE;
            }
            try {
                Thread.sleep(50);
            } catch (final InterruptedException e) {
                zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);
                return ZigBeeStatus.BAD_RESPONSE;
            }
        }

        createEndPoint(1, 0x104);

        zigbeeNetworkReceive.setNetworkState(ZigBeeTransportState.ONLINE);

        return ZigBeeStatus.SUCCESS;
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
    public void sendCommand(final ZigBeeApsFrame apsFrame) {
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
                networkManager.sendCommand(new AF_DATA_REQUEST(apsFrame.getDestinationAddress(),
                        (short) apsFrame.getDestinationEndpoint(), sender, apsFrame.getCluster(),
                        apsFrame.getApsCounter(), (byte) 0x30, (byte) apsFrame.getRadius(), apsFrame.getPayload()));
            } else {
                networkManager.sendCommand(new AF_DATA_REQUEST_EXT(apsFrame.getDestinationAddress(), sender,
                        apsFrame.getCluster(), apsFrame.getApsCounter(), (byte) (0), (byte) 0, apsFrame.getPayload()));
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
        apsFrame.setApsCounter(clusterMessage.getTransId());

        apsFrame.setPayload(clusterMessage.getData());

        zigbeeNetworkReceive.receiveCommand(apsFrame);

        return true;
    }

    @Override
    public void receivedAsynchronousCommand(ZToolPacket packet) {
        if (packet.isError()) {
            return;
        }

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
                new AF_REGISTER(endpointId, profileId, (short) 0, (byte) 0, new int[] {}, new int[] {0x500}));
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

    @Override
    public Integer getNwkAddress() {
        return 0;
    }

    @Override
    public ZigBeeKey getZigBeeNetworkKey() {
        return null;
    }

    @Override
    public ZigBeeKey getTcLinkKey() {
        return null;
    }

}
