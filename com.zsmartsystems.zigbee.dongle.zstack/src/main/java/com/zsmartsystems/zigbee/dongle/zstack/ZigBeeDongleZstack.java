/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeDeviceType;
import com.zsmartsystems.zigbee.ZigBeeExecutors;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.ZigBeeNwkAddressMode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.aps.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackResponseCode;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.AfDataOptions;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfDataConfirmAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfDataRequestSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfDataRequestSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.ZstackAfIncomingMsgAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.appcnf.ZstackCentralizedLinkKeyMode;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackConfigId;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackResetType;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysResetIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSysVersionSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.api.sys.ZstackSystemCapabilities;
import com.zsmartsystems.zigbee.dongle.zstack.api.util.ZstackUtilGetDeviceInfoSreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoMsgCbIncomingAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoStateChangeIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.api.zdo.ZstackZdoTcDevIndAreq;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackProtocolHandler;
import com.zsmartsystems.zigbee.dongle.zstack.internal.ZstackStackConfiguration;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.ZigBeePort;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportProgressState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;
import com.zsmartsystems.zigbee.zdo.command.BindResponse;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.EndDeviceBindResponse;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementBindResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingResponse;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.UnbindResponse;
import com.zsmartsystems.zigbee.zdo.command.UserDescriptorResponse;

/**
 * Implementation of the Texas instruments Z-Stack dongle implementation.
 * <p>
 * Usage notes...
 * <ul>
 * <li>To be compatible with older devices (ie pre-ZigBee 3.0), MT_APP_CNF_BDB_SET_TC_REQUIRE_KEY_EXCHANGE should be set
 * to FALSE. Failing to set this to FALSE will require the R21 join procedure to exchange keys following the initial
 * association, which older devices will not perform, and the coordinator will then remove them from the network. This
 * can be achieved with the {@link ZigBeeDongleZstack#requireKeyExchange(boolean)} method.
 * <li>There is a bug in the TI ZStack 3.0.2 which always return 00 as the SeqNum in the ZstackZdoMsgCbIncomingAreq
 * </ul>
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleZstack implements ZigBeeTransportTransmit {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeDongleZstack.class);

    /**
     * The serial port used to connect to the dongle
     */
    private ZigBeePort serialPort;

    /**
     * The protocol handler used to send and receive ZStack packets
     */
    private ZstackProtocolHandler protocolHandler;

    /**
     * The stack configuration we need for the NCP
     */
    private Map<ZstackConfigId, int[]> stackConfiguration;

    /**
     * The reference to the receive interface
     */
    private ZigBeeTransportReceive zigbeeTransportReceive;

    /**
     * The current link key as {@link ZigBeeKey}
     */
    private ZigBeeKey linkKey = new ZigBeeKey();

    /**
     * The current network key as {@link ZigBeeKey}
     */
    private ZigBeeKey networkKey = new ZigBeeKey();

    /**
     * The IeeeAddress of the NCP
     */
    private IeeeAddress ieeeAddress;

    /**
     * The network address of the NCP
     */
    private Integer nwkAddress;

    /**
     * The PAN ID
     */
    private Integer panId;

    /**
     * Requested TX power
     */
    private int txPower = 0;

    /**
     * The extended PAN ID
     */
    private ExtendedPanId extendedPanId;

    /**
     * The ZStack version used in this system. Set during initialisation and save in case the client is interested.
     */
    private String versionString = "Unknown";

    /**
     * Boolean that is true when the network is UP
     */
    private boolean networkStateUp = false;

    /**
     * Default profile to use for the main endpoint
     */
    ZigBeeProfileType defaultProfile = ZigBeeProfileType.ZIGBEE_HOME_AUTOMATION;

    /**
     * Boolean to hold initialisation state. Set to true after {@link #startup()} completes.
     */
    private boolean initialised = false;

    private ScheduledExecutorService executorService;
    private ScheduledFuture<?> pollingTimer = null;

    /**
     * The rate at which we will do a status poll if we've not sent any other messages within this period
     */
    private int pollRate = 100000;

    /**
     * The time the last command was sent from the {@link ZigBeeNetworkManager}. This is used by the dongle polling task
     * to not poll if commands are otherwise being sent so as to reduce unnecessary communications with the dongle.
     */
    private long lastSendCommandTime;

    private ResetListener resetListener;

    /**
     * Create a {@link ZigBeeDongleZstack}
     *
     * @param serialPort the {@link ZigBeePort} to use for the connection
     */
    public ZigBeeDongleZstack(final ZigBeePort serialPort) {
        this.serialPort = serialPort;

        // Define the default configuration
        stackConfiguration = new LinkedHashMap<>();

        stackConfiguration.put(ZstackConfigId.ZCD_NV_ZDO_DIRECT_CB, new int[] { 0x00 });

        networkKey = new ZigBeeKey();
    }

    @Override
    public ZigBeeStatus initialize() {
        logger.debug("ZStack dongle initialize: Starting");

        if (!serialPort.open()) {
            logger.error("Unable to open ZStack serial port");
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        resetListener = new ResetListener();

        protocolHandler = new ZstackProtocolHandler();
        protocolHandler.listener(ZstackSysResetIndAreq.class, resetListener);
        protocolHandler.start(serialPort);

        ZstackNcp ncp = getZstackNcp();

        Set<ZstackSystemCapabilities> capabilities = ncp.pingNcp();
        if (capabilities.isEmpty()) {
            if (ncp.resetNcp(ZstackResetType.TARGET_DEVICE) == null) {
                return ZigBeeStatus.COMMUNICATION_ERROR;
            } else {
                capabilities = ncp.pingNcp();
            }
        }
        logger.debug("ZStack subsystem capabilities: {}", capabilities);

        ZstackSysVersionSrsp version = ncp.getVersion();
        if (version == null) {
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Software=");
        builder.append(version.getMajorRel());
        builder.append(".");
        builder.append(version.getMinorRel());
        builder.append(".");
        builder.append(version.getMaintRel());
        builder.append(" Product=");
        builder.append(version.getProduct());
        builder.append(" Transport=");
        builder.append(version.getTransportRev());
        versionString = builder.toString();

        ieeeAddress = ncp.getIeeeAddress();
        logger.debug("ZStack local IeeeAddress: {}", ieeeAddress);

        /*
         * Create the scheduler with a single thread. This ensures that commands sent to the dongle, and the processing
         * of responses is performed in order
         */
        executorService = ZigBeeExecutors.newScheduledThreadPool(1, "ZstackTransport");

        logger.debug("ZStack dongle initialize: Done");

        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus startup(boolean reinitialize) {
        logger.debug("ZStack dongle startup: Starting");

        // If frameHandler is null then the serial port didn't initialise
        if (protocolHandler == null) {
            logger.error("Initialising ZStack Dongle but low level handler is not initialised.");
            return ZigBeeStatus.INVALID_STATE;
        }

        ZstackNcp ncp = getZstackNcp();

        // If we want to reinitialize the network, then go...
        if (reinitialize) {
            logger.debug("Reinitialising ZStack NCP network.");
            if (ncp.setStartupOptions(true, true) != ZstackResponseCode.SUCCESS) {
                logger.debug("ZStack Initialisation: Failed to set startup options");
                return ZigBeeStatus.COMMUNICATION_ERROR;
            }

            ZstackSysResetIndAreq result = ncp.resetNcp(ZstackResetType.TARGET_DEVICE);
            if (result == null) {
                return ZigBeeStatus.COMMUNICATION_ERROR;
            }

            // configure non-volatile
            ZstackResponseCode ncpResponse = ncp.setNetworkKey(networkKey);
            if (ncpResponse != ZstackResponseCode.SUCCESS) {
                logger.debug("ZStack error setting network key: {}", ncpResponse);
                return ZigBeeStatus.COMMUNICATION_ERROR;
            }

            ncpResponse = ncp.setCentralisedKey(ZstackCentralizedLinkKeyMode.PROVIDED_APS_KEY, linkKey.getValue());
            if (ncpResponse != ZstackResponseCode.SUCCESS) {
                logger.debug("ZStack error setting link key: {}", ncpResponse);
                return ZigBeeStatus.COMMUNICATION_ERROR;
            }

            if (panId == null) {
                // Allow the NCP to create a random PAN ID
                panId = 0xFFFF;
            }
            ncpResponse = ncp.setPanId(panId);
            if (ncpResponse != ZstackResponseCode.SUCCESS) {
                logger.debug("ZStack error setting PAN ID: {}", ncpResponse);
                return ZigBeeStatus.COMMUNICATION_ERROR;
            }

            if (extendedPanId == null) {
                // Allow the NCP to create a random extended PAN ID
                extendedPanId = new ExtendedPanId("FFFFFFFFFFFFFFFF");
            }
            ncpResponse = ncp.setExtendedPanId(extendedPanId);
            if (ncpResponse != ZstackResponseCode.SUCCESS) {
                logger.debug("ZStack error setting extended PAN ID: {}", ncpResponse);
                return ZigBeeStatus.COMMUNICATION_ERROR;
            }
        }

        // Perform any stack configuration
        ZstackStackConfiguration stackConfigurer = new ZstackStackConfiguration(ncp);

        Map<ZstackConfigId, int[]> configuration = stackConfigurer.getConfiguration(stackConfiguration.keySet());
        for (Entry<ZstackConfigId, int[]> config : configuration.entrySet()) {
            logger.debug("Configuration state {} = {}", config.getKey(), config.getValue());
        }

        stackConfigurer.setConfiguration(stackConfiguration);
        configuration = stackConfigurer.getConfiguration(stackConfiguration.keySet());
        for (Entry<ZstackConfigId, int[]> config : configuration.entrySet()) {
            logger.debug("Configuration state {} = {}", config.getKey(), config.getValue());
        }

        // reset the device
        // reload non-volatile memory
        ncp.resetNcp(ZstackResetType.SERIAL_BOOTLOADER);

        // volatile configuration
        // setup callbacks
        ncp.zdoRegisterCallback(MatchDescriptorRequest.CLUSTER_ID);
        ncp.zdoRegisterCallback(DeviceAnnounce.CLUSTER_ID);
        ncp.zdoRegisterCallback(ManagementPermitJoiningRequest.CLUSTER_ID);
        ncp.zdoRegisterCallback(NetworkAddressResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(IeeeAddressResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(NodeDescriptorResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(PowerDescriptorResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(SimpleDescriptorResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(ActiveEndpointsResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(MatchDescriptorResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(UserDescriptorResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(EndDeviceBindResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(BindResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(UnbindResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(ManagementLqiResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(ManagementRoutingResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(ManagementBindResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(ManagementLeaveResponse.CLUSTER_ID);
        ncp.zdoRegisterCallback(ManagementPermitJoiningResponse.CLUSTER_ID);

        // Add the endpoint
        ncp.addEndpoint(1, ZigBeeDeviceType.HOME_GATEWAY.getKey(), defaultProfile.getKey(),
                new int[] { 0 }, new int[] { 0 });

        if (setTxPower(txPower) != ZigBeeStatus.SUCCESS) {
            logger.error("ZStack error setting transmit power");
            // return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        startNetwork();

        logger.debug("ZStack dongle startup: Done");
        initialised = true;
        handleLinkStateChange(true);
        scheduleNetworkStatePolling();

        return ZigBeeStatus.SUCCESS;
    }

    /**
     * This method schedules sending a status request frame on the interval specified by pollRate. If the frameHandler
     * does not receive a response after a certain amount of retries, the state will be set to OFFLINE.
     * The poll will not be sent if other commands have been sent to the dongle within the pollRate period so as to
     * eliminate any unnecessary traffic with the dongle.
     */
    private void scheduleNetworkStatePolling() {
        if (pollingTimer != null) {
            pollingTimer.cancel(true);
        }

        pollingTimer = executorService.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                // Don't poll the state if the network is down
                // or we've sent a command to the dongle within the pollRate
                if (!networkStateUp || (lastSendCommandTime + pollRate > System.currentTimeMillis())) {
                    return;
                }
                // Don't wait for the response. This is running in a single thread scheduler
                protocolHandler.queueFrame(new ZstackUtilGetDeviceInfoSreq());
            }
        }, pollRate, pollRate, TimeUnit.MILLISECONDS);
    }

    @Override
    public void shutdown() {
        if (protocolHandler == null) {
            return;
        }

        if (pollingTimer != null) {
            pollingTimer.cancel(true);
        }

        if (executorService != null) {
            executorService.shutdown();
        }

        protocolHandler.setClosing();
        serialPort.close();
        protocolHandler.close();
        handleLinkStateChange(false);
        protocolHandler = null;
    }

    /**
     * Returns an instance of the {@link ZstackNcp}
     *
     * @return an instance of the {@link ZstackNcp}
     */
    public ZstackNcp getZstackNcp() {
        return new ZstackNcp(protocolHandler);
    }

    @Override
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    @Override
    public Integer getNwkAddress() {
        return nwkAddress;
    }

    @Override
    public void sendCommand(final int msgTag, final ZigBeeApsFrame apsFrame) {
        if (protocolHandler == null) {
            return;
        }

        // Remember the time to reduce unnecessary polling
        lastSendCommandTime = System.currentTimeMillis();

        ZstackAfDataRequestSreq request = new ZstackAfDataRequestSreq();
        request.setClusterID(apsFrame.getCluster());
        request.setDstAddr(apsFrame.getDestinationAddress());
        request.setDestEndpoint(apsFrame.getDestinationEndpoint());
        request.setSrcEndpoint(apsFrame.getSourceEndpoint());
        request.setTransID(msgTag);
        request.setRadius(apsFrame.getRadius());
        request.setData(apsFrame.getPayload());

        request.addOptions(AfDataOptions.AF_DISCV_ROUTE);
        if (apsFrame.getAckRequest()) {
            request.addOptions(AfDataOptions.AF_ACK_REQUEST);
        }
        if (apsFrame.getSecurityEnabled()) {
            request.addOptions(AfDataOptions.AF_EN_SECURITY);
        }

        // We need to correlate with the messageTag
        executorService.execute(() -> {
            ZstackAfDataRequestSrsp response = protocolHandler.sendTransaction(request, ZstackAfDataRequestSrsp.class);

            ZigBeeTransportProgressState sentHandlerState;
            if (response == null || response.getStatus() != ZstackResponseCode.SUCCESS) {
                sentHandlerState = ZigBeeTransportProgressState.TX_NAK;
            } else {
                sentHandlerState = ZigBeeTransportProgressState.TX_ACK;
            }

            zigbeeTransportReceive.receiveCommandState(msgTag, sentHandlerState);
        });
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeTransportReceive = zigbeeTransportReceive;
    }

    @Override
    public ZigBeeChannel getZigBeeChannel() {
        return ZigBeeChannel.create(getZstackNcp().getNetworkInfo().getChannel());
    }

    @Override
    public ZigBeeStatus setZigBeeChannel(ZigBeeChannel channel) {
        if ((ZigBeeChannelMask.CHANNEL_MASK_2GHZ & channel.getMask()) == 0) {
            logger.debug("Unable to set channel outside of 2.4GHz channels: {}", channel);
            return ZigBeeStatus.INVALID_ARGUMENTS;
        }
        // networkParameters.setRadioChannel(channel.getChannel());
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public int getZigBeePanId() {
        return getZstackNcp().getNetworkInfo().getPanId();
    }

    @Override
    public ZigBeeStatus setZigBeePanId(int panId) {
        // Can't change this when the network is up
        if (networkStateUp) {
            return ZigBeeStatus.INVALID_STATE;
        }
        this.panId = panId;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ExtendedPanId getZigBeeExtendedPanId() {
        return new ExtendedPanId(getZstackNcp().getNetworkInfo().getExtendedPanId());
    }

    @Override
    public ZigBeeStatus setZigBeeExtendedPanId(ExtendedPanId extendedPanId) {
        // Can't change this when the network is up
        if (networkStateUp) {
            return ZigBeeStatus.INVALID_STATE;
        }
        this.extendedPanId = extendedPanId;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus setZigBeeNetworkKey(final ZigBeeKey key) {
        if (networkStateUp) {
            return ZigBeeStatus.INVALID_STATE;
        }
        networkKey = key;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeKey getZigBeeNetworkKey() {
        return getZstackNcp().getNetworkKey();
    }

    @Override
    public ZigBeeStatus setTcLinkKey(ZigBeeKey key) {
        linkKey = key;
        ZstackNcp ncp = getZstackNcp();

        return ncp.setCentralisedKey(ZstackCentralizedLinkKeyMode.PROVIDED_APS_KEY,
                key.getValue()) == ZstackResponseCode.SUCCESS ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE;
    }

    @Override
    public ZigBeeKey getTcLinkKey() {
        return linkKey;
    }

    /**
     * Sets the policy flag on Trust Center device to mandate or not the TCLK exchange procedure.
     * <p>
     * Note that for ZB3, this should be set to true, however for backward compatability with HA1.2, this needs to be
     * set to false or devices will not be able to join the network.
     *
     * @param required true if the TCLK exchange procedure is required.
     * @return {@link ZigBeeStatus}
     */
    public ZigBeeStatus requireKeyExchange(boolean required) {
        ZstackNcp ncp = getZstackNcp();

        return ncp.requireKeyExchange(required) == ZstackResponseCode.SUCCESS ? ZigBeeStatus.SUCCESS
                : ZigBeeStatus.FAILURE;
    }

    @Override
    public void updateTransportConfig(TransportConfig configuration) {
        for (TransportConfigOption option : configuration.getOptions()) {
            try {
                switch (option) {
                    case CONCENTRATOR_CONFIG:
                        break;

                    case INSTALL_KEY:
                        ZstackNcp ncp = getZstackNcp();
                        ZigBeeKey nodeKey = (ZigBeeKey) configuration.getValue(option);
                        if (!nodeKey.hasAddress()) {
                            logger.debug("Attempt to set INSTALL_KEY without setting address");
                            configuration.setResult(option, ZigBeeStatus.FAILURE);
                            break;
                        }
                        ZstackResponseCode result = ncp.addInstallCode(nodeKey.getAddress(), nodeKey);

                        configuration.setResult(option,
                                result == ZstackResponseCode.SUCCESS ? ZigBeeStatus.SUCCESS : ZigBeeStatus.FAILURE);
                        break;

                    case RADIO_TX_POWER:
                        txPower = (int) configuration.getValue(option);
                        configuration.setResult(option, setTxPower(txPower));
                        break;

                    case TRUST_CENTRE_LINK_KEY:
                        setTcLinkKey((ZigBeeKey) configuration.getValue(option));
                        configuration.setResult(option, ZigBeeStatus.SUCCESS);
                        break;

                    case TRUST_CENTRE_JOIN_MODE:
                        // configuration.setResult(option,
                        // setTcJoinMode((TrustCentreJoinMode) configuration.getValue(option)));
                        break;

                    default:
                        configuration.setResult(option, ZigBeeStatus.UNSUPPORTED);
                        logger.debug("Unsupported configuration option \"{}\" in ZStack dongle", option);
                        break;
                }
            } catch (ClassCastException e) {
                configuration.setResult(option, ZigBeeStatus.INVALID_ARGUMENTS);
            }
        }
    }

    @Override
    public String getVersionString() {
        return versionString;
    }

    private ZigBeeStatus setTxPower(int txPower) {
        ZstackNcp ncp = getZstackNcp();
        Integer powerResponse = ncp.setTxPower(txPower);
        if (powerResponse == null) {
            logger.debug("ZStack error setting transmit power");
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        if (txPower != powerResponse) {
            logger.debug("ZStack error setting transmit power. Requested {}dBm, but set to {}dBm", txPower,
                    powerResponse);
        }

        return ZigBeeStatus.SUCCESS;
    }

    private void handleLinkStateChange(final boolean linkState) {
        // Only act on changes to OFFLINE once we have completed initialisation
        // changes to ONLINE have to work during init because they mark the end of the
        // initialisation
        if (!initialised || linkState == networkStateUp) {
            logger.debug("ZStack dongle state change to {} ignored. initialised={}, networkStateUp={}", linkState,
                    initialised, networkStateUp);
            return;
        }
        logger.debug("ZStack dongle state change to {}", linkState);

        networkStateUp = linkState;

        if (linkState) {
            executorService.execute(() -> {
                ZstackNcp ncp = getZstackNcp();
                nwkAddress = ncp.getNwkAddress();
                zigbeeTransportReceive.setTransportState(ZigBeeTransportState.ONLINE);
            });
        } else {
            zigbeeTransportReceive.setTransportState(ZigBeeTransportState.OFFLINE);
        }
    }

    /**
     * Starts the NCP application, placing it on the network as previously
     * configured.
     *
     * @return {@link ZigBeeStatus} defining the result
     */
    private ZigBeeStatus startNetwork() {
        logger.debug("ZStack starting network");
        ZstackNcp ncp = getZstackNcp();

        // setup listeners
        protocolHandler.listener(ZstackZdoStateChangeIndAreq.class, response -> {
            logger.debug("Zstack dongle state changed");
            switch (response.getState()) {
                case DEV_ROUTER:
                case DEV_ZB_COORD:
                case DEV_END_DEVICE:
                    handleLinkStateChange(true);
                    break;
                default:
                    handleLinkStateChange(false);
                    break;
            }
        });

        protocolHandler.listener(ZstackAfIncomingMsgAreq.class, response -> {
            logger.debug("Zstack incoming message");
            ZstackAfIncomingMsgAreq incomingMsg = (ZstackAfIncomingMsgAreq) response;
            ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
            apsFrame.setProfile(defaultProfile.getKey());
            apsFrame.setDestinationAddress(nwkAddress);
            apsFrame.setCluster(incomingMsg.getClusterId());
            apsFrame.setDestinationEndpoint(incomingMsg.getDestEndpoint());
            apsFrame.setSourceEndpoint(incomingMsg.getSrcEndpoint());
            apsFrame.setSourceAddress(incomingMsg.getSrcAddr());
            apsFrame.setSecurityEnabled(incomingMsg.getSecurityUse());
            apsFrame.setReceivedLqi(incomingMsg.getLinkQuality());
            apsFrame.setAddressMode(ZigBeeNwkAddressMode.DEVICE);
            apsFrame.setApsCounter(-1);
            apsFrame.setPayload(incomingMsg.getData());

            zigbeeTransportReceive.receiveCommand(apsFrame);
        });

        protocolHandler.listener(ZstackAfDataConfirmAreq.class, response -> {
            logger.debug("Zstack: APS confirmed {}", response.getEndpoint());
            ZigBeeTransportProgressState sentHandlerState;
            if (response.getStatus() == ZstackResponseCode.SUCCESS) {
                sentHandlerState = ZigBeeTransportProgressState.RX_ACK;
            } else {
                sentHandlerState = ZigBeeTransportProgressState.RX_NAK;
            }
            zigbeeTransportReceive.receiveCommandState(response.getTransId(), sentHandlerState);
        });

        protocolHandler.listener(ZstackZdoTcDevIndAreq.class, response -> {
            logger.debug("Zstack: device joined");
            zigbeeTransportReceive.nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN, response.getSrcAddr(),
                    response.getExtAddr());
        });

        protocolHandler.listener(ZstackZdoMsgCbIncomingAreq.class, response -> {
            logger.debug("Zstack: Incoming ZDO message");

            ZstackZdoMsgCbIncomingAreq incomingMsg = (ZstackZdoMsgCbIncomingAreq) response;
            ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
            apsFrame.setProfile(0);
            apsFrame.setCluster(incomingMsg.getClusterId());
            apsFrame.setDestinationAddress(incomingMsg.getDstAddr());
            apsFrame.setDestinationEndpoint(0);
            apsFrame.setSourceAddress(incomingMsg.getSrcAddr());
            apsFrame.setSourceEndpoint(0);
            apsFrame.setSecurityEnabled(incomingMsg.getSecurityUse());
            apsFrame.setAddressMode(ZigBeeNwkAddressMode.DEVICE);
            apsFrame.setApsCounter(-1);

            int[] payload = new int[incomingMsg.getData().length + 1];
            payload[0] = incomingMsg.getSeqNumber();
            System.arraycopy(incomingMsg.getData(), 0, payload, 1, incomingMsg.getData().length);
            apsFrame.setPayload(payload);

            zigbeeTransportReceive.receiveCommand(apsFrame);
        });

        // Now start the NCP
        if (ncp.startupApplication() != ZstackResponseCode.SUCCESS) {
            return ZigBeeStatus.COMMUNICATION_ERROR;
        }

        return ZigBeeStatus.SUCCESS;
    }

    private class ResetListener implements Consumer<ZstackSysResetIndAreq> {
        @Override
        public synchronized void accept(final ZstackSysResetIndAreq t) {
            logger.warn("Unexpected reset occured");
        }
    }
}
