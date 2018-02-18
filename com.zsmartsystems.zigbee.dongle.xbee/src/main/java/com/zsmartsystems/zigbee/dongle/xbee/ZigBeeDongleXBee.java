/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeKey;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager.ZigBeeInitializeResponse;
import com.zsmartsystems.zigbee.ZigBeeNwkAddressMode;
import com.zsmartsystems.zigbee.dongle.xbee.internal.XBeeEventListener;
import com.zsmartsystems.zigbee.dongle.xbee.internal.XBeeFrameHandler;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeEvent;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TransportConfigResult;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 * Implementation of the XBee ZigBee dongle.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleXBee implements ZigBeeTransportTransmit, XBeeEventListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeDongleXBee.class);

    /**
     * The serial port used to connect to the dongle
     */
    private ZigBeePort serialPort;

    /**
     * The XBee protocol handler used to send and receive packets
     */
    private XBeeFrameHandler frameHandler;

    /**
     * The reference to the receive interface
     */
    private ZigBeeTransportReceive zigbeeTransportReceive;

    /**
     * The current link key. Default to the ZigBeeAliance09 key
     */
    private ZigBeeKey linkKey = new ZigBeeKey(new int[] { 0x5A, 0x69, 0x67, 0x42, 0x65, 0x65, 0x41, 0x6C, 0x6C, 0x69,
            0x61, 0x6E, 0x63, 0x65, 0x30, 0x39 });

    /**
     * The current network key
     */
    private ZigBeeKey networkKey = new ZigBeeKey();

    /**
     * The current radio channel
     */
    private int radioChannel;

    /**
     * The current pan ID
     */
    private int panId;

    /**
     * The current extended pan ID
     */
    private ExtendedPanId extendedPanId;

    /**
     * The IeeeAddress of the XBee dongle
     */
    private IeeeAddress ieeeAddress;

    /**
     * The XBee version used in this system.
     */
    private String versionString = "Unknown";

    public ZigBeeDongleXBee(final ZigBeePort serialPort) {
        this.serialPort = serialPort;
    }

    @Override
    public ZigBeeInitializeResponse initialize() {
        logger.debug("XBee dongle initialize.");

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        if (!serialPort.open()) {
            logger.error("Unable to open XBee serial port");
            return ZigBeeInitializeResponse.FAILED;
        }

        // Create and start the frame handler
        frameHandler = new XBeeFrameHandler();
        frameHandler.start(serialPort);
        frameHandler.addEventListener(this);

        // Get the product information

        // Get network information

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

        return ZigBeeInitializeResponse.JOINED;
    }

    @Override
    public boolean startup(boolean reinitialize) {
        logger.debug("Telegesis dongle startup.");

        // If frameHandler is null then the serial port didn't initialise
        if (frameHandler == null) {
            logger.error("Initialising Telegesis Dongle but low level handler is not initialised.");
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
            return false;
        }

        // If we want to reinitialize the network, then go...
        if (reinitialize) {
            logger.debug("Reinitialising Telegesis dongle and forming network.");
            initialiseNetwork();
        }

        // Check if the network is now up

        // radioChannel = networkInfo.getChannel();
        // panId = networkInfo.getPanId();
        // extendedPanId = networkInfo.getEpanId();

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
        return true;
    }

    @Override
    public void shutdown() {
        if (frameHandler == null) {
            return;
        }
        frameHandler.setClosing();
        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
        serialPort.close();
        frameHandler.close();
        logger.debug("XBee dongle shutdown.");
    }

    @Override
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    private void initialiseNetwork() {

    }

    @Override
    public void sendCommand(final ZigBeeApsFrame apsFrame) {
        if (frameHandler == null) {
            logger.debug("Telegesis frame handler not set for send.");
            return;
        }

        XBeeCommand command;
        if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.DEVICE && apsFrame.getDestinationAddress() < 0xfff8) {
            // Unicast command
            // TelegesisSendUnicastCommand unicastCommand = new TelegesisSendUnicastCommand();
            // unicastCommand.setAddress(apsFrame.getDestinationAddress());
            // unicastCommand.setDestEp(apsFrame.getDestinationEndpoint());
            // unicastCommand.setSourceEp(0);
            // unicastCommand.setProfileId(apsFrame.getProfile());
            // unicastCommand.setClusterId(apsFrame.getCluster());
            // unicastCommand.setMessageData(apsFrame.getPayload());
            // command = unicastCommand;
        } else if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.DEVICE
                && apsFrame.getDestinationAddress() >= 0xfff8) {
            // Broadcast command
            // TelegesisSendMulticastCommand multicastCommand = new TelegesisSendMulticastCommand();
            // multicastCommand.setAddress(apsFrame.getDestinationAddress());
            // multicastCommand.setDestEp(apsFrame.getDestinationEndpoint());
            // multicastCommand.setProfileId(apsFrame.getProfile());
            // multicastCommand.setClusterId(apsFrame.getCluster());
            // multicastCommand.setRadius(apsFrame.getRadius());
            // multicastCommand.setMessageData(apsFrame.getPayload());
            // multicastCommand.setSourceEp(apsFrame.getSourceEndpoint());
            // command = multicastCommand;
        } else if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.GROUP) {
            // Multicast command
            // TelegesisSendMulticastCommand multicastCommand = new TelegesisSendMulticastCommand();
            // command = multicastCommand;
        } else {
            logger.debug("Telegesis message not sent: {}, {}", apsFrame);
            return;
        }

        // logger.debug("XBee send: {}", command.toString());
        // frameHandler.queueFrame(command);
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeTransportReceive = zigbeeTransportReceive;
    }

    @Override
    public void xbeeEventReceived(XBeeEvent event) {
        logger.debug("XBee RX: " + event.toString());
        // if (event instanceof TelegesisReceiveMessageEvent) {
        // TelegesisReceiveMessageEvent rxMessage = (TelegesisReceiveMessageEvent) event;

        // ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
        // apsFrame.setApsCounter(emberApsFrame.getSequence());
        // apsFrame.setCluster(rxMessage.getClusterId());
        // apsFrame.setDestinationEndpoint(rxMessage.getDestinationEp());
        // apsFrame.setProfile(rxMessage.getProfileId());
        // apsFrame.setSourceEndpoint(rxMessage.getSourceEp());

        // apsFrame.setSourceAddress(rxMessage.getNetworkAddress());
        // apsFrame.setPayload(rxMessage.getMessageData());
        // zigbeeTransportReceive.receiveCommand(apsFrame);
        // return;
        // }

        // Handle devices joining and leaving
        // if (event instanceof TelegesisDeviceJoinedNetworkEvent) {
        // TelegesisDeviceJoinedNetworkEvent deviceJoinedEvent = (TelegesisDeviceJoinedNetworkEvent) event;

        // zigbeeTransportReceive.nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN,
        // deviceJoinedEvent.getNetworkAddress(), deviceJoinedEvent.getIeeeAddress());
        // return;
        // }
        // if (event instanceof TelegesisDeviceLeftNetworkEvent) {
        // TelegesisDeviceLeftNetworkEvent deviceLeftEvent = (TelegesisDeviceLeftNetworkEvent) event;

        // zigbeeTransportReceive.nodeStatusUpdate(ZigBeeNodeStatus.DEVICE_LEFT, deviceLeftEvent.getNetworkAddress(),
        // deviceLeftEvent.getIeeeAddress());
        // return;
        // }

        // Handle link changes and notify framework or just reset link with dongle?
        // if (event instanceof TelegesisNetworkLeftEvent | event instanceof TelegesisNetworkLostEvent) {
        // zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
        // return;
        // }
        // if (event instanceof TelegesisNetworkJoinedEvent) {
        // zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
        // return;
        // }

        logger.debug("Unhandled XBee Frame: {}", event.toString());
    }

    @Override
    public int getZigBeeChannel() {
        return radioChannel;
    }

    @Override
    public boolean setZigBeeChannel(int channel) {
        ZigBeeChannelMask channelMask = new ZigBeeChannelMask();
        channelMask.addChannel(channel);

        return false;
    }

    @Override
    public int getZigBeePanId() {
        return panId;
    }

    @Override
    public boolean setZigBeePanId(int panId) {
        // Note that Telegesis dongle will not set a PAN ID if it detects the same PAN is already in use.
        // This can cause issues when trying to create a new coordinator on the same PAN.
        this.panId = panId;
        return true;
    }

    @Override
    public ExtendedPanId getZigBeeExtendedPanId() {
        return extendedPanId;
    }

    @Override
    public boolean setZigBeeExtendedPanId(ExtendedPanId extendedPanId) {
        this.extendedPanId = extendedPanId;
        return false;
    }

    @Override
    public boolean setZigBeeNetworkKey(final ZigBeeKey key) {
        networkKey = key;

        return false;
    }

    @Override
    public String getVersionString() {
        return versionString;
    }

    @Override
    public boolean setTcLinkKey(ZigBeeKey key) {
        linkKey = key;

        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateTransportConfig(TransportConfig configuration) {
        for (TransportConfigOption option : configuration.getOptions()) {
            try {
                switch (option) {
                    case SUPPORTED_INPUT_CLUSTERS:
                        configuration.setResult(option,
                                setSupportedInputClusters((Collection<Integer>) configuration.getValue(option)));
                        break;

                    case SUPPORTED_OUTPUT_CLUSTERS:
                        configuration.setResult(option,
                                setSupportedOutputClusters((Collection<Integer>) configuration.getValue(option)));
                        break;

                    case TRUST_CENTRE_JOIN_MODE:
                        // configuration.setResult(option,
                        // setTcJoinMode((TrustCentreJoinMode) configuration.getValue(option)));
                        break;

                    case TRUST_CENTRE_LINK_KEY:
                        configuration.setResult(option,
                                setTcLinkKey((ZigBeeKey) configuration.getValue(option)) ? TransportConfigResult.SUCCESS
                                        : TransportConfigResult.FAILURE);
                        break;

                    default:
                        configuration.setResult(option, TransportConfigResult.ERROR_UNSUPPORTED);
                        logger.debug("Unsupported configuration option \"{}\" in Telegesis dongle", option);
                        break;
                }
            } catch (ClassCastException e) {
                configuration.setResult(option, TransportConfigResult.ERROR_INVALID_VALUE);
            }
        }
    }

    private TransportConfigResult setSupportedInputClusters(Collection<Integer> supportedClusters) {
        return TransportConfigResult.SUCCESS;
    }

    private TransportConfigResult setSupportedOutputClusters(Collection<Integer> supportedClusters) {
        return TransportConfigResult.SUCCESS;
    }

}
