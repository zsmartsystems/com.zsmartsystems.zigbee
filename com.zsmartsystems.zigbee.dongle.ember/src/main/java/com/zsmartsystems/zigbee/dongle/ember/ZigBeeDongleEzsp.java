package com.zsmartsystems.zigbee.dongle.ember;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager.ZigBeeInitializeResponse;
import com.zsmartsystems.zigbee.ZigBeeNwkAddressMode;
import com.zsmartsystems.zigbee.ZigBeePort;
import com.zsmartsystems.zigbee.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.ZigBeeTransportState;
import com.zsmartsystems.zigbee.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddEndpointRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddEndpointResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspChildJoinHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetCurrentSecurityStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetCurrentSecurityStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspIncomingMessageHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSendBroadcastRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSendUnicastRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStackStatusHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCurrentSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberZdoConfigurationFlags;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspDecisionId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspPolicyId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.EmberNetworkInitialisation;
import com.zsmartsystems.zigbee.dongle.ember.internal.EmberStackConfiguration;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclProfileType;

/**
 * Implementation of the Silabs Ember NCP (Network Co Processor) EZSP dongle implementation.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDongleEzsp implements ZigBeeTransportTransmit, EzspFrameHandler {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeDongleEzsp.class);

    /**
     * The serial port used to connect to the dongle
     */
    private ZigBeePort serialPort;

    /**
     * The ASH protocol handler used to send and receive EZSP packets
     */
    private AshFrameHandler ashHandler;

    /**
     * The stack configuration we need for the NCP
     */
    private Map<EzspConfigId, Integer> stackConfiguration;

    /**
     * The stack policies we need for the NCP
     */
    private Map<EzspPolicyId, EzspDecisionId> stackPolicies;

    /**
     * Keep a map of which endpoint is registered with each cluster. This is used when sending messages.
     */
    private final Map<Integer, Integer> endpointMap = new HashMap<Integer, Integer>();

    /**
     * The reference to the receive interface
     */
    private ZigBeeTransportReceive zigbeeTransportReceive;

    /**
     * The current network key as {@link EmberKeyData}
     */
    private final EmberKeyData networkKey = new EmberKeyData();

    /**
     * The current network parameters as {@link EmberNetworkParameters}
     */
    private EmberNetworkParameters networkParameters = new EmberNetworkParameters();

    public ZigBeeDongleEzsp(final ZigBeePort serialPort) {
        this.serialPort = serialPort;

        stackConfiguration = new HashMap<EzspConfigId, Integer>();
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_SOURCE_ROUTE_TABLE_SIZE, 2);
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_SECURITY_LEVEL, 5);
        // stackConfiguration.put(EzspConfigId.EZSP_CONFIG_ADDRESS_TABLE_SIZE, 2);
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_TRUST_CENTER_ADDRESS_CACHE_SIZE, 2);
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_STACK_PROFILE, 2);
        // stackConfiguration.put(EzspConfigId.EZSP_CONFIG_INDIRECT_TRANSMISSION_TIMEOUT, 0x1E00);
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_SUPPORTED_NETWORKS, 1);
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_APPLICATION_ZDO_FLAGS,
                EmberZdoConfigurationFlags.EMBER_APP_RECEIVES_SUPPORTED_ZDO_REQUESTS.getKey());
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_STACK_PROFILE, 2);
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_KEY_TABLE_SIZE, 4);

        stackPolicies = new HashMap<EzspPolicyId, EzspDecisionId>();
        stackPolicies.put(EzspPolicyId.EZSP_TRUST_CENTER_POLICY, EzspDecisionId.EZSP_ALLOW_JOINS);
        stackPolicies.put(EzspPolicyId.EZSP_MESSAGE_CONTENTS_IN_CALLBACK_POLICY,
                EzspDecisionId.EZSP_MESSAGE_TAG_ONLY_IN_CALLBACK);
        // stackPolicies.put(EzspPolicyId.EZSP_APP_KEY_REQUEST_POLICY, value)

        networkKey.setContents(new int[16]);
    }

    @Override
    public ZigBeeInitializeResponse initialize() {
        logger.debug("EZSP dongle initialize.");

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        if (!serialPort.open()) {
            logger.error("Unable to open Ember serial port");
            return ZigBeeInitializeResponse.FAILED;
        }
        ashHandler = new AshFrameHandler(serialPort.getInputStream(), serialPort.getOutputStream(), this);

        // Connect to the ASH handler and NCP
        ashHandler.connect();

        // We MUST send the version command first.
        EzspVersionRequest version = new EzspVersionRequest();
        version.setDesiredProtocolVersion(4);
        EzspTransaction versionTransaction = ashHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(version, EzspVersionResponse.class));
        EzspVersionResponse versionResponse = (EzspVersionResponse) versionTransaction.getResponse();
        logger.debug(versionResponse.toString());

        // Perform any stack configuration
        EmberStackConfiguration stackConfigurer = new EmberStackConfiguration(ashHandler);

        Map<EzspConfigId, Integer> configuration = stackConfigurer.getConfiguration(stackConfiguration.keySet());
        for (EzspConfigId config : configuration.keySet()) {
            logger.debug("Configuration state {} = {}", config, configuration.get(config));
        }

        Map<EzspPolicyId, EzspDecisionId> policies = stackConfigurer.getPolicy(stackPolicies.keySet());
        for (EzspPolicyId policy : policies.keySet()) {
            logger.debug("Policy state {} = {}", policy, policies.get(policy));
        }

        stackConfigurer.setConfiguration(stackConfiguration);
        configuration = stackConfigurer.getConfiguration(stackConfiguration.keySet());
        for (EzspConfigId config : configuration.keySet()) {
            logger.debug("Configuration state {} = {}", config, configuration.get(config));
        }

        stackConfigurer.setPolicy(stackPolicies);
        policies = stackConfigurer.getPolicy(stackPolicies.keySet());
        for (EzspPolicyId policy : policies.keySet()) {
            logger.debug("Policy state {} = {}", policy, policies.get(policy));
        }

        // Add our endpoint(s)
        createEndpoints();

        // Now initialise the network
        EzspNetworkInitRequest networkInitRequest = new EzspNetworkInitRequest();
        EzspTransaction networkInitTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkInitRequest, EzspNetworkInitResponse.class));
        EzspNetworkInitResponse networkInitResponse = (EzspNetworkInitResponse) networkInitTransaction.getResponse();
        logger.debug(networkInitResponse.toString());

        networkParameters = getNetworkParameters();
        getCurrentSecurityState();

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

        logger.debug("EZSP dongle initialize done: Initialised {}",
                networkInitResponse.getStatus() == EmberStatus.EMBER_NOT_JOINED);

        // Check if the network is initialised or if we're yet to join
        if (networkInitResponse.getStatus() == EmberStatus.EMBER_NOT_JOINED) {
            return ZigBeeInitializeResponse.NOT_JOINED;
        }

        return ZigBeeInitializeResponse.JOINED;
    }

    @Override
    public boolean startup(boolean reinitialize) {
        logger.debug("EZSP dongle startup.");

        // Check if the network is initialised
        EzspNetworkStateRequest networkStateRequest = new EzspNetworkStateRequest();
        EzspTransaction networkStateTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkStateRequest, EzspNetworkStateResponse.class));
        EzspNetworkStateResponse networkStateResponse = (EzspNetworkStateResponse) networkStateTransaction
                .getResponse();
        logger.debug(networkStateResponse.toString());
        logger.debug("EZSP networkStateResponse {}", networkStateResponse.getStatus());

        // If we want to reinitialize the network, then go...
        if (reinitialize) {
            logger.debug("Reinitialising Ember NCP and forming network.");
            initialiseNetwork();
        }

        // Check if the network is now up
        networkStateTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkStateRequest, EzspNetworkStateResponse.class));
        networkStateResponse = (EzspNetworkStateResponse) networkStateTransaction.getResponse();
        logger.debug(networkStateResponse.toString());
        logger.debug("EZSP networkStateResponse {}", networkStateResponse.getStatus());
        if (networkStateResponse.getStatus() == EmberNetworkStatus.EMBER_JOINED_NETWORK) {
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
        }

        // Get the security state - mainly for debug
        EmberCurrentSecurityState currentSecurityState = getCurrentSecurityState();
        logger.debug("Current Security State = {}", currentSecurityState);

        logger.debug("EZSP dongle startup done.");

        // Mainly for debug we run a task to periodically download the neighbor table
        new EzspNeighborTable(ashHandler, 31);

        return true;
    }

    @Override
    public void shutdown() {
        serialPort.close();
    }

    /**
     * Gets the current network parameters, or an empty parameters class if there's an error
     *
     * @return {@link EmberNetworkParameters}
     */
    private EmberNetworkParameters getNetworkParameters() {
        EzspGetNetworkParametersRequest networkParms = new EzspGetNetworkParametersRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(networkParms,
                EzspGetNetworkParametersResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetNetworkParametersResponse getNetworkParametersResponse = (EzspGetNetworkParametersResponse) transaction
                .getResponse();
        logger.debug(getNetworkParametersResponse.toString());
        if (getNetworkParametersResponse.getStatus() != EmberStatus.EMBER_SUCCESS
                && getNetworkParametersResponse.getStatus() != EmberStatus.EMBER_NOT_JOINED) {
            logger.debug("Error during retrieval of network parameters: {}", getNetworkParametersResponse);
            return new EmberNetworkParameters();
        }

        return getNetworkParametersResponse.getParameters();
    }

    private void createEndpoints() {
        // Create a list of all the clusters we want to register
        final List<Integer> clusterSet = Arrays.asList(ZclClusterType.BASIC.getId(),
                ZclClusterType.POWER_CONFIGURATION.getId(), ZclClusterType.ON_OFF.getId());

        EzspAddEndpointRequest addEndpoint;

        int startIndex = 0;
        int endIndex = 0;

        final int MAX_CLUSTERS_PER_AF_REGISTER = 16;
        int endpoint = 1;
        while (endIndex < clusterSet.size()) {
            endIndex = clusterSet.size();

            if ((endIndex - startIndex) > MAX_CLUSTERS_PER_AF_REGISTER) {
                endIndex = startIndex + MAX_CLUSTERS_PER_AF_REGISTER;
            }

            final int[] clusters = new int[endIndex - startIndex];

            int index = 0;
            for (final Integer cluster : clusterSet.subList(startIndex, endIndex)) {
                endpointMap.put(cluster, endpoint);
                clusters[index] = cluster;
                index++;
            }
            startIndex = endIndex;

            addEndpoint = new EzspAddEndpointRequest();
            addEndpoint.setEndpoint(endpoint);
            addEndpoint.setDeviceId(0);
            addEndpoint.setProfileId(ZclProfileType.HOME_AUTOMATION.getId());
            addEndpoint.setInputClusterList(clusters);
            addEndpoint.setOutputClusterList(clusters);
            // addEndpoint = (EzspAddEndpointRequest) ashHandler.sendEzspRequest(addEndpoint);
            logger.debug(addEndpoint.toString());

            EzspTransaction addEndpointTransaction = ashHandler
                    .sendEzspTransaction(new EzspSingleResponseTransaction(addEndpoint, EzspAddEndpointResponse.class));
            EzspAddEndpointResponse addEndpointResponse = (EzspAddEndpointResponse) addEndpointTransaction
                    .getResponse();
            logger.debug(addEndpointResponse.toString());

            endpoint++;
        }
    }

    private void initialiseNetwork() {
        EmberNetworkInitialisation netInitialiser = new EmberNetworkInitialisation(ashHandler);
        netInitialiser.formNetwork(networkParameters, networkKey);

        EzspGetNetworkParametersRequest networkParametersRequest = new EzspGetNetworkParametersRequest();
        EzspTransaction networkParametersTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkParametersRequest, EzspGetNetworkParametersResponse.class));
        EzspGetNetworkParametersResponse networkParametersResponse = (EzspGetNetworkParametersResponse) networkParametersTransaction
                .getResponse();
        logger.debug(networkParametersResponse.toString());
    }

    /**
     * Get the current security parameters
     *
     * @return the {@link EmberNetworkParameters} or null on error
     */
    private EmberCurrentSecurityState getCurrentSecurityState() {
        EzspGetCurrentSecurityStateRequest networkParms = new EzspGetCurrentSecurityStateRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(networkParms,
                EzspGetCurrentSecurityStateResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetCurrentSecurityStateResponse currentSecurityStateResponse = (EzspGetCurrentSecurityStateResponse) transaction
                .getResponse();
        logger.debug(currentSecurityStateResponse.toString());
        if (currentSecurityStateResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of security parameters: {}", currentSecurityStateResponse);
            return null;
        }
        return currentSecurityStateResponse.getState();
    }

    // private void permitJoin() {
    // EzspPermitJoiningRequest request = new EzspPermitJoiningRequest();
    // request.setDuration(255);
    // EzspTransaction transaction = ashHandler
    // .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspPermitJoiningResponse.class));
    // EzspPermitJoiningResponse response = (EzspPermitJoiningResponse) transaction.getResponse();
    // logger.debug(response.toString());

    // zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
    // }

    @Override
    public void sendCommand(final ZigBeeApsFrame apsFrame) throws ZigBeeException {
        EzspFrameRequest emberCommand;

        EmberApsFrame emberApsFrame = new EmberApsFrame();
        emberApsFrame.setClusterId(apsFrame.getCluster());
        emberApsFrame.setProfileId(apsFrame.getProfile());
        emberApsFrame.setSourceEndpoint(apsFrame.getSourceEndpoint());
        emberApsFrame.setDestinationEndpoint(apsFrame.getDestinationEndpoint());
        emberApsFrame.setSequence(apsFrame.getApsCounter());
        emberApsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_RETRY);
        emberApsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_ENABLE_ROUTE_DISCOVERY);
        emberApsFrame.addOptions(EmberApsOption.EMBER_APS_OPTION_ENABLE_ADDRESS_DISCOVERY);

        if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.DEVICE && apsFrame.getDestinationAddress() < 0xfff8) {
            EzspSendUnicastRequest emberUnicast = new EzspSendUnicastRequest();
            emberUnicast.setIndexOrDestination(apsFrame.getDestinationAddress());
            emberUnicast.setMessageTag(apsFrame.getSequence());
            emberUnicast.setSequenceNumber(apsFrame.getSequence());
            emberUnicast.setType(EmberOutgoingMessageType.EMBER_OUTGOING_DIRECT);
            emberUnicast.setApsFrame(emberApsFrame);
            emberUnicast.setMessageContents(apsFrame.getPayload());

            emberCommand = emberUnicast;
        } else if (apsFrame.getAddressMode() == ZigBeeNwkAddressMode.DEVICE
                && apsFrame.getDestinationAddress() >= 0xfff8) {

            EzspSendBroadcastRequest emberBroadcast = new EzspSendBroadcastRequest();
            emberBroadcast.setDestination(apsFrame.getDestinationAddress());
            emberBroadcast.setMessageTag(apsFrame.getSequence());
            emberBroadcast.setSequenceNumber(apsFrame.getSequence());
            emberBroadcast.setApsFrame(emberApsFrame);
            emberBroadcast.setRadius(apsFrame.getRadius());
            emberBroadcast.setMessageContents(apsFrame.getPayload());

            emberCommand = emberBroadcast;
        } else {
            logger.debug("EZSP message not sent: {}, {}", apsFrame);
            // ZigBeeGroupAddress groupAddress = (ZigBeeGroupAddress) zclCommand.getDestinationAddress();
            // apsFrame.setGroupId(groupAddress.getGroupId());
            return;
        }

        logger.debug(emberCommand.toString());
        ashHandler.queueFrame(emberCommand);

        // emberUnicast = (EzspSendUnicast) ashHandler.sendEzspRequestAsync(emberUnicast);
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeTransportReceive = zigbeeTransportReceive;
    }

    @Override
    public void handlePacket(EzspFrame response) {
        logger.debug("RX: " + response.toString());

        if (response instanceof EzspIncomingMessageHandler) {
            EzspIncomingMessageHandler incomingMessage = (EzspIncomingMessageHandler) response;
            EmberApsFrame emberApsFrame = incomingMessage.getApsFrame();

            ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
            apsFrame.setApsCounter(emberApsFrame.getSequence());
            apsFrame.setCluster(emberApsFrame.getClusterId());
            apsFrame.setDestinationEndpoint(emberApsFrame.getDestinationEndpoint());
            apsFrame.setProfile(emberApsFrame.getProfileId());
            apsFrame.setSourceEndpoint(emberApsFrame.getSourceEndpoint());

            apsFrame.setSequence(incomingMessage.getSequenceNumber());
            apsFrame.setSourceAddress(incomingMessage.getSender());
            apsFrame.setPayload(incomingMessage.getMessageContents());
            zigbeeTransportReceive.receiveCommand(apsFrame);

            return;
        }

        // TODO: Check if this should be done only after initialisation is complete?
        if (response instanceof EzspStackStatusHandler) {
            switch (((EzspStackStatusHandler) response).getStatus()) {
                case EMBER_NETWORK_BUSY:
                    break;
                case EMBER_NETWORK_DOWN:
                    zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
                    break;
                case EMBER_NETWORK_UP:
                    break;
                default:
                    break;
            }
        }

        if (response instanceof EzspChildJoinHandler) {
            EzspChildJoinHandler childHandler = (EzspChildJoinHandler) response;
            zigbeeTransportReceive.announceDevice(childHandler.getChildId());

            /*
             * // Convert to an announce
             * int message[] = new int[12];
             * message[0] = 0;
             * message[1] = childHandler.getChildId() & 0xff;
             * message[2] = (childHandler.getChildId() >> 8) & 0xff;
             *
             * message[3] = (int) (childHandler.getChildEui64().getLong() & 0xFF);
             * message[4] = (int) ((childHandler.getChildEui64().getLong() >> 8) & 0xFF);
             * message[5] = (int) ((childHandler.getChildEui64().getLong() >> 16) & 0xFF);
             * message[6] = (int) ((childHandler.getChildEui64().getLong() >> 24) & 0xFF);
             * message[7] = (int) ((childHandler.getChildEui64().getLong() >> 32) & 0xFF);
             * message[8] = (int) ((childHandler.getChildEui64().getLong() >> 40) & 0xFF);
             * message[9] = (int) ((childHandler.getChildEui64().getLong() >> 48) & 0xFF);
             * message[10] = (int) ((childHandler.getChildEui64().getLong() >> 56) & 0xFF);
             *
             * message[11] = 0;
             *
             * ZigBeeApsFrame apsFrame = new ZigBeeApsFrame();
             * apsFrame.setApsCounter(0);
             * apsFrame.setCluster(0x0013);
             * apsFrame.setDestinationEndpoint(0);
             * apsFrame.setProfile(0);
             * apsFrame.setSourceEndpoint(0);
             *
             * apsFrame.setSequence(0);
             * apsFrame.setSourceAddress(childHandler.getChildId());
             * apsFrame.setPayload(message);
             * zigbeeTransportReceive.receiveCommand(apsFrame);
             */
            return;
        }

        logger.debug("Unhandled EZSP Frame: " + response.toString());
    }

    @Override
    public void handleLinkStateChange(boolean linkState) {
        // Handle link changes and notify framework or just reset link with dongle?
        if (!linkState) {
            zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.OFFLINE);
        }
    }

    @Override
    public int getZigBeeChannel() {
        return networkParameters.getRadioChannel();
    }

    @Override
    public boolean setZigBeeChannel(int channel) {
        networkParameters.setRadioChannel(channel);
        return false;
    }

    @Override
    public int getZigBeePanId() {
        return networkParameters.getPanId();
    }

    @Override
    public boolean setZigBeePanId(int panId) {
        networkParameters.setPanId(panId);
        return true;
    }

    @Override
    public long getZigBeeExtendedPanId() {
        long value = 0;
        int cnt = 0;
        for (long val : networkParameters.getExtendedPanId()) {
            value += val << (cnt * 8);
            cnt++;
        }
        return value;
    }

    @Override
    public boolean setZigBeeExtendedPanId(long extendedPanId) {
        int[] panArray = new int[8];
        for (int cnt = 0; cnt < 8; cnt++) {
            panArray[cnt] = (int) ((extendedPanId >> (cnt * 8)) & 0xff);
        }
        networkParameters.setExtendedPanId(panArray);
        return false;
    }

    @Override
    public boolean setZigBeeSecurityKey(int[] keyData) {
        networkKey.setContents(keyData);

        return false;
    }
}
