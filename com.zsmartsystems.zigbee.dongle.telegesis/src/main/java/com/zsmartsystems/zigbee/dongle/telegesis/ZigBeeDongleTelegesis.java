/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis;

import java.io.InputStream;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.ZigBeeNwkAddressMode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.TelegesisEventListener;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.TelegesisFirmwareUpdateHandler;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.TelegesisFrameHandler;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisBootloadCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisChangeChannelCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisCreatePanCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceJoinedNetworkEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceLeftNetworkEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceType;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDisallowTcJoinCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDisallowUnsecuredRejoinCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDisplayNetworkInformationCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDisplayProductIdentificationCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisGetChannelMaskCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisGetFrameCntCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkJoinedEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkLeftEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisNetworkLostEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisReceiveMessageEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisRestoreFactoryDefaultsCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSendMulticastCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSendUnicastCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetChannelMaskCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetEpanIdCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetExtendedFunctionCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetFrameCntCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetInputClustersCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetMainFunctionCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetNetworkKeyCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetOutputClustersCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetOutputPowerCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetPanIdCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetPromptEnable1Command;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetPromptEnable2Command;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetRegisterBitCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetRegisterCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSetTrustCentreLinkKeyCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisSoftwareResetCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisStatusCode;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TrustCentreJoinMode;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareCallback;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareStatus;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareUpdate;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 * Implementation of the Telegesis ZigBee dongle.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleTelegesis
        implements ZigBeeTransportTransmit, ZigBeeTransportFirmwareUpdate, TelegesisEventListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeDongleTelegesis.class);

    /**
     * The serial port used to connect to the dongle
     */
    private ZigBeePort serialPort;

    /**
     * The Telegesis protocol handler used to send and receive packets
     */
    private TelegesisFrameHandler frameHandler;

    /**
     * The Telegesis bootload handler
     */
    private TelegesisFirmwareUpdateHandler bootloadHandler;

    /**
     * The dongle password
     */
    private String telegesisPassword = "";

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
    private ZigBeeChannel radioChannel;

    /**
     * The current pan ID
     */
    private int panId;

    /**
     * The current extended pan ID
     */
    private ExtendedPanId extendedPanId;

    /**
     * The IeeeAddress of the Telegesis radio
     */
    private IeeeAddress ieeeAddress;

    /**
     * The Telegesis version used in this system.
     */
    private String versionString = "Unknown";

    /**
     * Flag to remember if startup has been completed, so that we don't inform the higher layers that the dongle is
     * online before the higher layer has had the chance to initialise.
     */
    private boolean startupComplete = false;

    /**
     * S0A – Main Function
     * <p>
     * - Bit E-F: Device Selection
     * <br>
     * + -- 0 0 Router (FFD)
     * <br>
     * - -- 1 0 End Device
     * <br>
     * - -- 0 1 Sleepy End Device
     * <br>
     * - -- 1 1 Mobile End Device
     * <br>
     * - Bit D: Set: If a router, do not route any messages
     * <br>
     * - Bit C: Prescale PWM clock to reduce frequency by 256
     * <br>
     * - Bit B: Set: Allows Endpoint 2 to reply to ZDO endpoint queries
     * <br>
     * - Bit A: Set: When joining don’t ask for Trust Centre link key
     * <br>
     * - Bit 9: Set: Don’t use central Trust Centre (distributed TC Mode)
     * <br>
     * + Bit 8: Set: Use Pre-Configured Trust Centre Link Key when joining
     * <br>
     * - Bit 7: Set: Trust centre uses hashed link key
     * <br>
     * + Bit 6: Set: Append RSSI and LQI to all RX: prompts
     * <br>
     * - Bit 5: Set: Don’t allow nodes to join (TC setting)
     * <br>
     * + Bit 4: Set: Send Network key encrypted with the link key to nodes joining
     * <br>
     * + Bit 3: Set: Do not allow nodes to re-join unsecured
     * <br>
     * + Bit 2: Set: Send Network key encrypted with the link key to nodes re-joining unsecured
     * <br>
     * + Bit 1: Set: Disable received interpan messages
     * <br>
     * - Bit 0: Set: Do not allow other nodes to join the network via this node
     */
    private final int defaultS0A = 0x015E;

    /**
     * S0E – Prompt Enable 1
     * <p>
     * + Bit F: Set: Disable ‘>’ prompt when entering binary data
     * <br>
     * - Bit E: Set: Disable UCAST, MCAST, BCAST, SCAST data
     * <br>
     * - Bit D: Set: Disable RAW data
     * <br>
     * - Bit C: Set: Disable SEQ prompt
     * <br>
     * + Bit B: Set: Disable SINK prompt
     * <br>
     * - Bit A: Set: Disable SR: prompt
     * <br>
     * - Bit 9: Set: Disable RAW wrapper
     * <br>
     * - Bit 8: Set: Disable NEWNODE prompt
     * <br>
     * - Bit 7: Set: Disable NACK:XX prompt
     * <br>
     * - Bit 6: Set: Disable ACK:XX
     * <br>
     * - Bit 5: Set: Disable UCAST, MCAST, BCAST, SCAST wrapper
     * <br>
     * - Bit 4: Set: Disable LeftPAN prompt
     * <br>
     * - Bit 3: Set: Disable JPAN prompt
     * <br>
     * + Bit 2: Set: Disable PWRCHANGE:nn prompt
     * <br>
     * - Bit 1: Set: Disable OK prompt
     * <br>
     * - Bit 0: Set: Disable ERROR:XX prompt
     */
    private final int defaultS0E = 0x8804;

    /**
     * S0F – Prompt Enable 2
     * <p>
     * - Bit F: Add prefix to local S-register reads
     * <br>
     * + Bit E: Show RSSI and LQI for all received unicasts, broadcasts and AT+PANSCAN reports
     * <br>
     * + Bit D: Set: Display incoming ZDO messages by RX prompt instead of normal text prompt
     * <br>
     * - Bit C: Set: Message payload of RX prompt is displayed as hexadecimal instead of ASCII text
     * <br>
     * + Bit B: Set: Show NODELEFT prompt on COO when a device leaves the PAN
     * <br>
     * - Bit A: Set: Add remote endpoint and Network address to ACK and NACK prompts
     * <br>
     * + Bit 9: Set: Disable SWRITE prompt
     * <br>
     * + Bit 8: Set: Show unhandled messages received by Endpoints 1 and above
     * <br>
     * - Bit 7: Set: Hide “AddrResp” prompt
     * <br>
     * - Bit 6: Set: Hide Network Manager Warning
     * <br>
     * + Bit 5: Set: Hide “DataMODE” prompt
     * <br>
     * + Bit 4: Set: Hide “CLOSED” prompt
     * <br>
     * + Bit 3: Set: Hide “OPEN” prompt
     * <br>
     * + Bit 2: Set: Hide all Sink Advertisements
     * <br>
     * - Bit 1: Set: Disable showing unhandled messages received by all Endpoints
     * <br>
     * - Bit 0: Set: Disable COO, FFD, SED and MED prompts
     */
    private final int defaultS0F = 0x6B3C;

    /**
     * S10 – Extended Function
     * <p>
     * - Bit F: Set: Don’t exit data mode in case of data loss
     * <br>
     * + Bit E: Set: Don’t accept Data Mode
     * <br>
     * - Bit D: Set: High RAM concentrator instead of Low RAM concentrator
     * <br>
     * + Bit C: Set: Display RSSI and LQI of the last hop when devices report to AT+SN or AT+ANNCE
     * <br>
     * - Bit B: Set: UCASTs and SCASTs wait for ACK
     * <br>
     * + Bit A: Set: Disable playing Tune when receiving AT+IDENT
     * <br>
     * + Bit 9: Set: Enable one second character timeout when entering data for xCASTB
     * <br>
     * - Bit 8: Set: Actively search for a sink if none is known
     * <br>
     * + Bit 7: Set: Node doesn’t replace existing sink with better one (lower cost)
     * <br>
     * - Bit 6: Set: Node doesn’t lose sink if it couldn’t be reached for three times
     * <br>
     * + Bit 5: Set: Sink won’t reply to nodes searching for a sink
     * <br>
     * - Bit 4: Set: Node is Sink
     * <br>
     * + Bit 3: Set: Changes to S01 take effect instantly
     * <br>
     * - Bit 2: Set: Send BCAST[B] messages to routers only
     * <br>
     * - Bit 1: Set: Send unicast messages unacknowledged
     * <br>
     * + Bit 0: Set: Don’t attach EUI64 to NWK frame when sending a message.
     */
    private final int defaultS10 = 0x56A9;

    /**
     * Constructor for Telegesis dongle.
     *
     * @param serialPort the {@link ZigBeePort} for communicating with the dongle
     */
    public ZigBeeDongleTelegesis(final ZigBeePort serialPort) {
        this.serialPort = serialPort;
    }

    /**
     * Gets an instance of the {@link TelegesisNcp}
     *
     * @return an instance of the {@link TelegesisNcp}
     */
    public TelegesisNcp getTelegesisNcp() {
        return new TelegesisNcp(frameHandler);
    }

    @Override
    public ZigBeeStatus initialize() {
        logger.debug("Telegesis dongle initialize.");

        bootloadHandler = null;

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        if (!serialPort.open()) {
            logger.error("Unable to open Telegesis serial port");
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        // Create and start the frame handler
        frameHandler = new TelegesisFrameHandler(this);
        frameHandler.start(serialPort);
        frameHandler.addEventListener(this);

        // Software reset the stick so we are in a known state on startup!
        // For the first command, we add a retry mechanism
        // in case the serial port takes some time to get started
        for (int retry = 0; retry < 3; retry++) {
            TelegesisSoftwareResetCommand softwareReset = new TelegesisSoftwareResetCommand();
            if (frameHandler.sendRequest(softwareReset) != null) {
                break;
            }
        }

        if (!initialiseDongle()) {
            logger.error("Unable to configure Telegesis dongle");
            return ZigBeeStatus.FAILURE;
        }

        // Get the product information
        TelegesisDisplayProductIdentificationCommand productInfo = new TelegesisDisplayProductIdentificationCommand();
        if (frameHandler.sendRequest(productInfo) != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("Device=");
            builder.append(productInfo.getDeviceName());
            builder.append("Version=R");
            builder.append(productInfo.getFirmwareRevision());
            versionString = builder.toString();

            ieeeAddress = productInfo.getIeeeAddress();
        }

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus startup(boolean reinitialize) {
        logger.debug("Telegesis dongle startup.");

        // If frameHandler is null then the serial port didn't initialise
        if (frameHandler == null) {
            logger.error("Initialising Telegesis Dongle but low level handler is not initialised.");
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
            return ZigBeeStatus.INVALID_STATE;
        }

        // If we want to reinitialize the network, then go...
        if (reinitialize) {
            logger.debug("Reinitialising Telegesis dongle and forming network.");
            if (!initialiseNetwork()) {
                logger.error("Error while initialising Telegesis dongle and forming network.");
                return ZigBeeStatus.FAILURE;
            }
        }

        // Enable boost mode
        TelegesisSetRegisterBitCommand setRegister = new TelegesisSetRegisterBitCommand();
        setRegister.setRegister(0x11);
        setRegister.setBit(0xE);
        setRegister.setState(true);
        if (frameHandler.sendRequest(setRegister) == null) {
            logger.debug("Error setting Telegesis boost mode");
            return ZigBeeStatus.BAD_RESPONSE;
        }

        // Set to maximum output power
        TelegesisSetOutputPowerCommand outputPower = new TelegesisSetOutputPowerCommand();
        if (versionString.contains("LRS")) {
            // The output power of the “-LRS” variant is higher than the value in S01.
            // The ETRX357-LRS power is reduced for EC regulatory compliance.
            outputPower.setPowerLevel(-7);
        } else {
            outputPower.setPowerLevel(8);
        }
        if (frameHandler.sendRequest(outputPower) == null || outputPower.getStatus() != TelegesisStatusCode.SUCCESS) {
            logger.debug("Telegesis failed to set maximum output power.");
            return ZigBeeStatus.BAD_RESPONSE;
        }

        // Check if the network is now up
        TelegesisDisplayNetworkInformationCommand networkInfo = new TelegesisDisplayNetworkInformationCommand();
        if (frameHandler.sendRequest(networkInfo) == null || networkInfo.getStatus() != TelegesisStatusCode.SUCCESS) {
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
            return ZigBeeStatus.BAD_RESPONSE;
        }
        if (networkInfo.getDevice() != TelegesisDeviceType.COO) {
            return ZigBeeStatus.INVALID_STATE;
        }

        radioChannel = ZigBeeChannel.create(networkInfo.getChannel());

        TelegesisGetChannelMaskCommand channelMask = new TelegesisGetChannelMaskCommand();
        if (frameHandler.sendRequest(channelMask) == null || channelMask.getStatus() != TelegesisStatusCode.SUCCESS) {
            logger.debug("Telegesis failed to get current channel mask.");
        } else {
            logger.debug("Telegesis current channel mask: {}", channelMask);
        }

        panId = networkInfo.getPanId();
        extendedPanId = networkInfo.getEpanId();

        startupComplete = true;

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void shutdown() {
        if (frameHandler == null) {
            return;
        }
        frameHandler.removeEventListener(this);
        frameHandler.setClosing();
        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
        serialPort.close();
        frameHandler.close();
        logger.debug("Telegesis dongle shutdown.");
    }

    @Override
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    @Override
    public Integer getNwkAddress() {
        return 0;
    }

    private boolean initialiseDongle() {
        // Disable echo on the serial port
        TelegesisSetRegisterBitCommand setRegister = new TelegesisSetRegisterBitCommand();
        setRegister.setRegister(0x12);
        setRegister.setBit(4);
        setRegister.setState(true);
        if (frameHandler.sendRequest(setRegister) == null) {
            logger.debug("Error setting Telegesis port echo");
            return false;
        }

        // Configure the dongle
        TelegesisSetPromptEnable1Command prompt1Function = new TelegesisSetPromptEnable1Command();
        prompt1Function.setConfiguration(defaultS0E);
        if (frameHandler.sendRequest(prompt1Function) == null) {
            logger.debug("Error setting Telegesis Prompt 1 register");
            return false;
        }

        TelegesisSetMainFunctionCommand mainFunction = new TelegesisSetMainFunctionCommand();
        mainFunction.setConfiguration(defaultS0A);
        mainFunction.setPassword(telegesisPassword);
        if (frameHandler.sendRequest(mainFunction) == null) {
            logger.debug("Error setting Telegesis Main Function register");
            return false;
        }
        TelegesisSetExtendedFunctionCommand extendedFunction = new TelegesisSetExtendedFunctionCommand();
        extendedFunction.setConfiguration(defaultS10);
        if (frameHandler.sendRequest(extendedFunction) == null) {
            logger.debug("Error setting Telegesis Extended Function register");
            return false;
        }
        TelegesisSetPromptEnable2Command prompt2Function = new TelegesisSetPromptEnable2Command();
        prompt2Function.setConfiguration(defaultS0F);
        if (frameHandler.sendRequest(prompt2Function) == null) {
            logger.debug("Error setting Telegesis Prompt 2 register");
            return false;
        }

        return true;
    }

    private boolean initialiseNetwork() {
        TelegesisRestoreFactoryDefaultsCommand setDefaults = new TelegesisRestoreFactoryDefaultsCommand();
        if (frameHandler.sendRequest(setDefaults) == null) {
            logger.debug("Error setting Telegesis dongle to factory defaults");
            return false;
        }

        if (!initialiseDongle()) {
            logger.error("Unable to configure Telegesis dongle after hard reset");
            return false;
        }

        TelegesisSetTrustCentreLinkKeyCommand linkKeyCommand = new TelegesisSetTrustCentreLinkKeyCommand();
        linkKeyCommand.setLinkKey(linkKey);
        linkKeyCommand.setPassword(telegesisPassword);
        if (frameHandler.sendRequest(linkKeyCommand) == null) {
            logger.debug("Error setting Telegesis link key");
            return false;
        }

        TelegesisSetNetworkKeyCommand networkKeyCommand = new TelegesisSetNetworkKeyCommand();
        networkKeyCommand.setNetworkKey(networkKey);
        networkKeyCommand.setPassword(telegesisPassword);
        if (frameHandler.sendRequest(networkKeyCommand) == null) {
            logger.debug("Error setting Telegesis network key");
            return false;
        }

        TelegesisSetPanIdCommand panIdCommand = new TelegesisSetPanIdCommand();
        panIdCommand.setPanId(panId);
        if (frameHandler.sendRequest(panIdCommand) == null) {
            logger.debug("Error setting Telegesis PAN ID");
            return false;
        }

        TelegesisSetEpanIdCommand epanIdCommand = new TelegesisSetEpanIdCommand();
        epanIdCommand.setEpanId(extendedPanId);
        if (frameHandler.sendRequest(epanIdCommand) == null) {
            logger.debug("Error setting Telegesis EPAN ID");
            return false;
        }

        // Set the profile to HA
        TelegesisSetRegisterCommand setRegister = new TelegesisSetRegisterCommand();
        setRegister.setRegister(0x48);
        setRegister.setValue(ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION.getKey());
        if (frameHandler.sendRequest(setRegister) == null) {
            logger.debug("Error setting Telegesis profile ID");
            return false;
        }

        setZigBeeChannel(radioChannel);

        TelegesisCreatePanCommand createNetwork = new TelegesisCreatePanCommand();
        frameHandler.queueFrame(createNetwork);
        if (frameHandler.eventWait(TelegesisNetworkJoinedEvent.class) == null) {
            logger.debug("Error creating Telegesis PAN");
            return false;
        }
        logger.debug("Telegesis PAN Created");

        return true;
    }

    @Override
    public void sendCommand(final ZigBeeApsFrame apsFrame) {
        if (frameHandler == null) {
            logger.debug("Telegesis frame handler not set for send.");
            return;
        }

        TelegesisCommand command;
        if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.DEVICE && apsFrame.getDestinationAddress() < 0xfff8) {
            // Unicast command
            TelegesisSendUnicastCommand unicastCommand = new TelegesisSendUnicastCommand();
            unicastCommand.setAddress(apsFrame.getDestinationAddress());
            unicastCommand.setDestEp(apsFrame.getDestinationEndpoint());
            unicastCommand.setSourceEp(0);
            unicastCommand.setProfileId(apsFrame.getProfile());
            unicastCommand.setClusterId(apsFrame.getCluster());
            unicastCommand.setMessageData(apsFrame.getPayload());
            command = unicastCommand;
        } else if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.DEVICE
                && apsFrame.getDestinationAddress() >= 0xfff8) {
            // Broadcast command
            TelegesisSendMulticastCommand multicastCommand = new TelegesisSendMulticastCommand();
            multicastCommand.setAddress(apsFrame.getDestinationAddress());
            multicastCommand.setDestEp(apsFrame.getDestinationEndpoint());
            multicastCommand.setProfileId(apsFrame.getProfile());
            multicastCommand.setClusterId(apsFrame.getCluster());
            multicastCommand.setRadius(apsFrame.getRadius());
            multicastCommand.setMessageData(apsFrame.getPayload());
            multicastCommand.setSourceEp(apsFrame.getSourceEndpoint());
            command = multicastCommand;
        } else if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.GROUP) {
            // Multicast command
            TelegesisSendMulticastCommand multicastCommand = new TelegesisSendMulticastCommand();
            command = multicastCommand;
        } else {
            logger.debug("Telegesis message not sent: {}, {}", apsFrame);
            return;
        }

        logger.debug("Telegesis send: {}", command.toString());
        frameHandler.queueFrame(command);
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeTransportReceive = zigbeeTransportReceive;
    }

    @Override
    public void telegesisEventReceived(TelegesisEvent event) {
        // Ignore events prior to the app starting - to avoid confusion!
        if (!startupComplete) {
            return;
        }

        if (event instanceof TelegesisReceiveMessageEvent) {
            TelegesisReceiveMessageEvent rxMessage = (TelegesisReceiveMessageEvent) event;

            ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
            // apsFrame.setApsCounter(emberApsFrame.getSequence());
            apsFrame.setCluster(rxMessage.getClusterId());
            apsFrame.setDestinationEndpoint(rxMessage.getDestinationEp());
            apsFrame.setProfile(rxMessage.getProfileId());
            apsFrame.setSourceEndpoint(rxMessage.getSourceEp());

            apsFrame.setSourceAddress(rxMessage.getNetworkAddress());
            apsFrame.setPayload(rxMessage.getMessageData());
            zigbeeTransportReceive.receiveCommand(apsFrame);
            return;
        }

        // Handle devices joining and leaving
        if (event instanceof TelegesisDeviceJoinedNetworkEvent) {
            TelegesisDeviceJoinedNetworkEvent deviceJoinedEvent = (TelegesisDeviceJoinedNetworkEvent) event;

            zigbeeTransportReceive.nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN,
                    deviceJoinedEvent.getNetworkAddress(), deviceJoinedEvent.getIeeeAddress());
            return;
        }
        if (event instanceof TelegesisDeviceLeftNetworkEvent) {
            TelegesisDeviceLeftNetworkEvent deviceLeftEvent = (TelegesisDeviceLeftNetworkEvent) event;

            zigbeeTransportReceive.nodeStatusUpdate(ZigBeeNodeStatus.DEVICE_LEFT, deviceLeftEvent.getNetworkAddress(),
                    deviceLeftEvent.getIeeeAddress());
            return;
        }

        // Handle link changes and notify framework or just reset link with dongle?
        if (event instanceof TelegesisNetworkLeftEvent | event instanceof TelegesisNetworkLostEvent) {
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
            return;
        }
        if (event instanceof TelegesisNetworkJoinedEvent) {
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
            return;
        }

        logger.debug("Unhandled Telegesis Frame: {}", event.toString());
    }

    @Override
    public ZigBeeChannel getZigBeeChannel() {
        return radioChannel;
    }

    @Override
    public ZigBeeStatus setZigBeeChannel(ZigBeeChannel channel) {
        radioChannel = channel;

        ZigBeeChannelMask channelMask = new ZigBeeChannelMask();
        channelMask.addChannel(channel);

        TelegesisSetChannelMaskCommand maskCommand = new TelegesisSetChannelMaskCommand();
        maskCommand.setChannelMask(channelMask.getChannelMask() >> 11);
        if (frameHandler.sendRequest(maskCommand) == null || maskCommand.getStatus() != TelegesisStatusCode.SUCCESS) {
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
            return ZigBeeStatus.BAD_RESPONSE;
        }

        TelegesisChangeChannelCommand channelCommand = new TelegesisChangeChannelCommand();
        channelCommand.setChannel(channel.getChannel());
        if (frameHandler.sendRequest(channelCommand) == null
                || channelCommand.getStatus() != TelegesisStatusCode.SUCCESS) {
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
            return ZigBeeStatus.BAD_RESPONSE;
        }

        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public int getZigBeePanId() {
        return panId;
    }

    @Override
    public ZigBeeStatus setZigBeePanId(int panId) {
        // Note that Telegesis dongle will not set a PAN ID if it detects the same PAN is already in use.
        // This can cause issues when trying to create a new coordinator on the same PAN.
        this.panId = panId;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ExtendedPanId getZigBeeExtendedPanId() {
        return extendedPanId;
    }

    @Override
    public ZigBeeStatus setZigBeeExtendedPanId(ExtendedPanId extendedPanId) {
        this.extendedPanId = extendedPanId;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus setZigBeeNetworkKey(final ZigBeeKey key) {
        networkKey = key;

        TelegesisSetNetworkKeyCommand networkKeyCommand = new TelegesisSetNetworkKeyCommand();
        networkKeyCommand.setNetworkKey(networkKey);
        networkKeyCommand.setPassword(telegesisPassword);
        if (frameHandler.sendRequest(networkKeyCommand) == null) {
            logger.debug("Error setting Telegesis network key");
            return ZigBeeStatus.FAILURE;
        }
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeKey getZigBeeNetworkKey() {
        TelegesisGetFrameCntCommand frameCntCommand = new TelegesisGetFrameCntCommand();
        if (frameHandler.sendRequest(frameCntCommand) == null) {
            logger.debug("Error getting Telegesis frame counter");
        } else {
            networkKey.setOutgoingFrameCounter(frameCntCommand.getFrameCnt().intValue());
        }

        return networkKey;
    }

    @Override
    public String getVersionString() {
        return versionString;
    }

    /**
     * Set the password to use for protected commands when communicating with the Telegesis dongle.
     * <p>
     * Note: The default password is "password".
     *
     * @param telegesisPassword {@link String} containing the dongle password
     */
    public void setTelegesisPassword(String telegesisPassword) {
        this.telegesisPassword = telegesisPassword;
    }

    @Override
    public boolean updateFirmware(final InputStream firmware, final ZigBeeTransportFirmwareCallback callback) {
        if (frameHandler == null) {
            logger.debug("frameHandler is uninitialised in updateFirmware");
            return false;
        }

        if (!serialPort.open()) {
            logger.error("Unable to open Telegesis serial port");
            return false;
        }

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
        callback.firmwareUpdateCallback(ZigBeeTransportFirmwareStatus.FIRMWARE_UPDATE_STARTED);

        // Send the bootload command, but ignore the response since there doesn't seem to be one
        // despite what the documentation seems to indicate
        TelegesisBootloadCommand bootloadCommand = new TelegesisBootloadCommand();
        frameHandler.sendRequest(bootloadCommand);

        // Stop the handler and close the serial port
        logger.debug("Telegesis closing frame handler");
        frameHandler.setClosing();
        serialPort.close();
        frameHandler.close();
        frameHandler = null;

        bootloadHandler = new TelegesisFirmwareUpdateHandler(this, firmware, serialPort, callback);
        bootloadHandler.startBootload();

        return true;
    }

    // Callback from the bootload handler when the transfer is completed/aborted/failed
    public void bootloadComplete() {
        bootloadHandler = null;
    }

    @Override
    public String getFirmwareVersion() {
        int versionIndex = versionString.indexOf("Version=");
        if (versionIndex == -1) {
            return "";
        }
        return versionString.substring(versionIndex + 8);
    }

    @Override
    public boolean cancelUpdateFirmware() {
        if (bootloadHandler == null) {
            return false;
        }
        bootloadHandler.cancelUpdate();
        return true;
    }

    @Override
    public ZigBeeStatus setTcLinkKey(ZigBeeKey key) {
        linkKey = key;

        TelegesisSetTrustCentreLinkKeyCommand linkKeyCommand = new TelegesisSetTrustCentreLinkKeyCommand();
        linkKeyCommand.setLinkKey(linkKey);
        linkKeyCommand.setPassword(telegesisPassword);
        if (frameHandler.sendRequest(linkKeyCommand) == null) {
            logger.debug("Error setting Telegesis link key");
            return ZigBeeStatus.BAD_RESPONSE;
        }

        if (key.hasOutgoingFrameCounter()) {
            TelegesisSetFrameCntCommand setCount = new TelegesisSetFrameCntCommand();
            setCount.setFrameCnt((long) key.getOutgoingFrameCounter());
        }

        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeKey getTcLinkKey() {

        return linkKey;
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
                        configuration.setResult(option,
                                setTcJoinMode((TrustCentreJoinMode) configuration.getValue(option)));
                        break;

                    case TRUST_CENTRE_LINK_KEY:
                        configuration.setResult(option, setTcLinkKey((ZigBeeKey) configuration.getValue(option)));
                        break;

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

    public void reinitializeDongle(Integer panId, ExtendedPanId epanId, Long frameCounter) {
        TelegesisSetFrameCntCommand setCount = new TelegesisSetFrameCntCommand();
        setCount.setFrameCnt(frameCounter);
        if (frameHandler.sendRequest(setCount) == null) {
            logger.debug("Error setting Telegesis security frame counter");
            return;
        }
    }

    private ZigBeeStatus setSupportedInputClusters(Collection<Integer> supportedClusters) {
        TelegesisSetInputClustersCommand inputClusters = new TelegesisSetInputClustersCommand();
        inputClusters.setClusterList(supportedClusters);
        if (frameHandler.sendRequest(inputClusters) == null) {
            logger.debug("Error setting Telegesis input clusters");
            return ZigBeeStatus.BAD_RESPONSE;
        }

        return ZigBeeStatus.SUCCESS;
    }

    private ZigBeeStatus setSupportedOutputClusters(Collection<Integer> supportedClusters) {
        TelegesisSetOutputClustersCommand outputClusters = new TelegesisSetOutputClustersCommand();
        outputClusters.setClusterList(supportedClusters);
        if (frameHandler.sendRequest(outputClusters) == null) {
            logger.debug("Error setting Telegesis output clusters");
            return ZigBeeStatus.BAD_RESPONSE;
        }

        return ZigBeeStatus.SUCCESS;
    }

    private ZigBeeStatus setTcJoinMode(TrustCentreJoinMode linkMode) {
        logger.debug("Setting Telegesis trust centre link mode: {}", linkMode);

        TelegesisDisallowTcJoinCommand disallowJoinCommand = new TelegesisDisallowTcJoinCommand();
        switch (linkMode) {
            case TC_JOIN_DENY:
                disallowJoinCommand.setDisallowJoin(true);
                disallowJoinCommand.setPassword(telegesisPassword);
                frameHandler.sendRequest(disallowJoinCommand);
                break;
            case TC_JOIN_SECURE:
            case TC_JOIN_INSECURE:
                disallowJoinCommand.setDisallowJoin(false);
                disallowJoinCommand.setPassword(telegesisPassword);
                frameHandler.sendRequest(disallowJoinCommand);

                TelegesisDisallowUnsecuredRejoinCommand unsecuredRejoinCommand = new TelegesisDisallowUnsecuredRejoinCommand();
                unsecuredRejoinCommand.setDisallowRejoin(linkMode == TrustCentreJoinMode.TC_JOIN_SECURE ? true : false);
                unsecuredRejoinCommand.setPassword(telegesisPassword);
                frameHandler.sendRequest(unsecuredRejoinCommand);
                break;
            default:
                logger.info("Unknown trust centre link mode: {}", linkMode);
                return ZigBeeStatus.INVALID_ARGUMENTS;
        }

        return ZigBeeStatus.SUCCESS;
    }

    /**
     * Callback method from the handler to notify if the handler goes on/off line
     *
     * @param state true if the handler is online, false if offline
     */
    public void notifyStateUpdate(boolean state) {
        zigbeeTransportReceive.setNetworkState(state ? ZigBeeTransportState.ONLINE : ZigBeeTransportState.OFFLINE);
    }

}
