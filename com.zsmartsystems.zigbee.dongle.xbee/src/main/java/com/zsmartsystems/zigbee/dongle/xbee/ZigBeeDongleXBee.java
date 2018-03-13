/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.EncryptionOptions;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.TransmitOptions;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeEvent;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeExtendedPanIdResponse;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeGetDetailedVersionCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeGetExtendedPanIdCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeGetFirmwareVersionCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeGetHardwareVersionCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeGetIeeeAddressHighCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeGetIeeeAddressLowCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeGetPanIdCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeIeeeAddressHighResponse;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeIeeeAddressLowResponse;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeModemStatusEvent;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeePanIdResponse;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeReceivePacketExplicitEvent;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetApiEnableCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetApiModeCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetCoordinatorEnableCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetEncryptionEnableCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetEncryptionOptionsCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetLinkKeyCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetNetworkKeyCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetSaveDataCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetSoftwareResetCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeSetZigbeeStackProfileCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeTransmitRequestExplicitCommand;
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

    private boolean coordinatorStarted = false;
    private boolean initialisationComplete = false;

    final private IeeeAddress groupIeeeAddress = new IeeeAddress("000000000000FFFE");
    final private IeeeAddress broadcastIeeeAddress = new IeeeAddress("000000000000FFFF");

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

        // Reset to a known state
        XBeeSetSoftwareResetCommand resetCommand = new XBeeSetSoftwareResetCommand();
        frameHandler.sendRequest(resetCommand);

        // Device sends WATCHDOG_TIMER_RESET event
        frameHandler.eventWait(XBeeModemStatusEvent.class);

        // Enable the API with escaping
        XBeeSetApiEnableCommand apiEnableCommand = new XBeeSetApiEnableCommand();
        apiEnableCommand.setMode(2);
        frameHandler.sendRequest(apiEnableCommand);

        // Set the API mode so we receive detailed data including ZDO
        XBeeSetApiModeCommand apiModeCommand = new XBeeSetApiModeCommand();
        apiModeCommand.setMode(3);
        frameHandler.sendRequest(apiModeCommand);

        // Get the product information
        XBeeGetHardwareVersionCommand hwVersionCommand = new XBeeGetHardwareVersionCommand();
        frameHandler.sendRequest(hwVersionCommand);

        XBeeGetFirmwareVersionCommand fwVersionCommand = new XBeeGetFirmwareVersionCommand();
        frameHandler.sendRequest(fwVersionCommand);

        XBeeGetDetailedVersionCommand versionCommand = new XBeeGetDetailedVersionCommand();
        frameHandler.sendRequest(versionCommand);

        // Get Ieee Address
        XBeeGetIeeeAddressHighCommand ieeeHighCommand = new XBeeGetIeeeAddressHighCommand();
        XBeeIeeeAddressHighResponse ieeeHighResponse = (XBeeIeeeAddressHighResponse) frameHandler
                .sendRequest(ieeeHighCommand);
        XBeeGetIeeeAddressLowCommand ieeeLowCommand = new XBeeGetIeeeAddressLowCommand();
        XBeeIeeeAddressLowResponse ieeeLowResponse = (XBeeIeeeAddressLowResponse) frameHandler
                .sendRequest(ieeeLowCommand);

        int[] tmpAddress = new int[8];
        tmpAddress[0] = ieeeLowResponse.getIeeeAddress()[3];
        tmpAddress[1] = ieeeLowResponse.getIeeeAddress()[2];
        tmpAddress[2] = ieeeLowResponse.getIeeeAddress()[1];
        tmpAddress[3] = ieeeLowResponse.getIeeeAddress()[0];
        tmpAddress[4] = ieeeHighResponse.getIeeeAddress()[3];
        tmpAddress[5] = ieeeHighResponse.getIeeeAddress()[2];
        tmpAddress[6] = ieeeHighResponse.getIeeeAddress()[1];
        tmpAddress[7] = ieeeHighResponse.getIeeeAddress()[0];
        ieeeAddress = new IeeeAddress(tmpAddress);

        logger.debug("XBee IeeeAddress={}", ieeeAddress);

        // Set the ZigBee stack profile
        XBeeSetZigbeeStackProfileCommand stackProfile = new XBeeSetZigbeeStackProfileCommand();
        stackProfile.setStackProfile(2);
        frameHandler.sendRequest(stackProfile);

        // Enable Security
        XBeeSetEncryptionEnableCommand enableEncryption = new XBeeSetEncryptionEnableCommand();
        enableEncryption.setEnableEncryption(true);
        frameHandler.sendRequest(enableEncryption);

        XBeeSetEncryptionOptionsCommand encryptionOptions = new XBeeSetEncryptionOptionsCommand();
        encryptionOptions.addEncryptionOptions(EncryptionOptions.ENABLE_TRUST_CENTRE);
        frameHandler.sendRequest(encryptionOptions);

        // Enable coordinator mode
        XBeeSetCoordinatorEnableCommand coordinatorEnable = new XBeeSetCoordinatorEnableCommand();
        coordinatorEnable.setEnable(true);
        frameHandler.sendRequest(coordinatorEnable);

        // Set the network key
        XBeeSetNetworkKeyCommand networkKey = new XBeeSetNetworkKeyCommand();
        networkKey.setNetworkKey(new ZigBeeKey());
        frameHandler.sendRequest(networkKey);

        // Set the link key
        XBeeSetLinkKeyCommand setLinkKey = new XBeeSetLinkKeyCommand();
        setLinkKey.setLinkKey(linkKey);
        frameHandler.sendRequest(setLinkKey);

        // Save the configuration in the XBee
        XBeeSetSaveDataCommand saveData = new XBeeSetSaveDataCommand();
        frameHandler.sendRequest(saveData);

        // Get network information

        XBeeGetPanIdCommand getPanId = new XBeeGetPanIdCommand();
        XBeePanIdResponse panIdResponse = (XBeePanIdResponse) frameHandler.sendRequest(getPanId);

        XBeeGetExtendedPanIdCommand getEPanId = new XBeeGetExtendedPanIdCommand();
        XBeeExtendedPanIdResponse epanIdResponse = (XBeeExtendedPanIdResponse) frameHandler.sendRequest(getEPanId);

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

        return ZigBeeInitializeResponse.JOINED;
    }

    @Override
    public boolean startup(boolean reinitialize) {
        logger.debug("XBee dongle startup.");

        // If frameHandler is null then the serial port didn't initialise
        if (frameHandler == null) {
            logger.error("Initialising XBee Dongle but low level handler is not initialised.");
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
            return false;
        }

        // If we want to reinitialize the network, then go...
        if (reinitialize) {
            logger.debug("Reinitialising XBee dongle and forming network.");
            initialiseNetwork();
        }

        // Check if the network is now up

        // radioChannel = networkInfo.getChannel();
        // panId = networkInfo.getPanId();
        // extendedPanId = networkInfo.getEpanId();

        initialisationComplete = true;

        if (coordinatorStarted) {
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
        }
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
            logger.debug("XBee frame handler not set for send.");
            return;
        }

        XBeeTransmitRequestExplicitCommand command = new XBeeTransmitRequestExplicitCommand();
        command.setNetworkAddress(apsFrame.getDestinationAddress());
        command.setDestinationEndpoint(apsFrame.getDestinationEndpoint());
        command.setSourceEndpoint(apsFrame.getSourceEndpoint());
        command.setProfileId(apsFrame.getProfile());
        command.setCluster(apsFrame.getCluster());
        command.setBroadcastRadius(0);
        if (apsFrame.getDestinationEndpoint() != 0) {
            command.addOptions(TransmitOptions.ENABLE_APS_ENCRYPTION);
        }
        if (apsFrame.getDestinationAddress() > 0xFFF8) {
            command.setIeeeAddress(broadcastIeeeAddress);
        } else if (apsFrame.getDestinationIeeeAddress() == null) {
            if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.GROUP) {
                command.setIeeeAddress(groupIeeeAddress);
            } else {
            }
            command.setIeeeAddress(new IeeeAddress("FFFFFFFFFFFFFFFF"));
        } else {
            command.setIeeeAddress(apsFrame.getDestinationIeeeAddress());
        }

        int[] data = new int[apsFrame.getPayload().length + 1];
        data[0] = apsFrame.getSequence();
        int cnt = 0;
        for (int value : apsFrame.getPayload()) {
            data[cnt++] = value;
        }
        command.setData(data);

        logger.debug("XBee send: {}", command.toString());
        frameHandler.sendRequestAsync(command);
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeTransportReceive = zigbeeTransportReceive;
    }

    @Override
    public void xbeeEventReceived(XBeeEvent event) {
        if (event instanceof XBeeReceivePacketExplicitEvent) {
            XBeeReceivePacketExplicitEvent rxMessage = (XBeeReceivePacketExplicitEvent) event;

            ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
            apsFrame.setCluster(rxMessage.getClusterId());
            apsFrame.setDestinationEndpoint(rxMessage.getDestinationEndpoint());
            apsFrame.setProfile(rxMessage.getProfileId());
            apsFrame.setSourceEndpoint(rxMessage.getSourceEndpoint());

            apsFrame.setSourceAddress(rxMessage.getNetworkAddress());
            apsFrame.setPayload(rxMessage.getData());

            zigbeeTransportReceive.receiveCommand(apsFrame);
            return;
        }

        // Handle devices joining and leaving
        if (event instanceof XBeeModemStatusEvent) {
            XBeeModemStatusEvent modemStatus = (XBeeModemStatusEvent) event;

            switch (modemStatus.getStatus()) {
                case COORDINATOR_STARTED:
                    coordinatorStarted = true;
                    if (initialisationComplete) {
                        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
                    }
                    break;
                case DISASSOCIATED:
                    zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
                    break;
                case HARDWARE_RESET:
                    break;
                case JOINED_NETWORK:
                    break;
                case NETWORK_SECURITY_KEY_UPDATED:
                    break;
                case WATCHDOG_TIMER_RESET:
                    break;
                default:
                    break;
            }

            return;
        }

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
                        logger.debug("Unsupported configuration option \"{}\" in XBee dongle", option);
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
