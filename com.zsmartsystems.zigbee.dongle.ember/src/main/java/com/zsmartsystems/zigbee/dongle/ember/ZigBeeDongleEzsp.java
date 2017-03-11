package com.zsmartsystems.zigbee.dongle.ember;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.ZigBeeException;
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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspIncomingMessageHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLeaveNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLeaveNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSendBroadcastRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSendUnicastRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberZdoConfigurationFlags;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
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

    private Map<EzspConfigId, Integer> stackConfiguration;

    /**
     * Keep a map of which endpoint is registered with each cluster. This is used when sending messages.
     */
    private final Map<Integer, Integer> endpointMap = new HashMap<Integer, Integer>();

    /**
     * The reference to the receive interface
     */
    private ZigBeeTransportReceive zigbeeTransportReceive;

    public ZigBeeDongleEzsp(final ZigBeePort serialPort) {
        this.serialPort = serialPort;

        stackConfiguration = new HashMap<EzspConfigId, Integer>();
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_APPLICATION_ZDO_FLAGS,
                EmberZdoConfigurationFlags.EMBER_APP_RECEIVES_SUPPORTED_ZDO_REQUESTS.getKey());
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_SECURITY_LEVEL, 5);
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_SUPPORTED_NETWORKS, 2);
        stackConfiguration.put(EzspConfigId.EZSP_CONFIG_STACK_PROFILE, 2);
    }

    @Override
    public boolean initialize() {
        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        if (!serialPort.open()) {
            logger.error("Unable to open serial port");
            return false;
        }

        // final OutputStream out = serialPort.getOutputStream();
        ashHandler = new AshFrameHandler(serialPort.getInputStream(), serialPort.getOutputStream(), this);

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

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
        stackConfigurer.getConfiguration(stackConfiguration.keySet());
        stackConfigurer.setConfiguration(stackConfiguration);
        stackConfigurer.getConfiguration(stackConfiguration.keySet());

        // Add our endpoint(s)
        createEndpoints();

        initialiseNetwork();

        return false;
    }

    @Override
    public boolean startup() {

        return true;
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
        // Now initialise the network
        EzspNetworkInitRequest networkInitRequest = new EzspNetworkInitRequest();
        EzspTransaction networkInitTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkInitRequest, EzspNetworkInitResponse.class));
        EzspNetworkInitResponse networkInitResponse = (EzspNetworkInitResponse) networkInitTransaction.getResponse();
        logger.debug(networkInitResponse.toString());
        logger.debug("EZSP networkInitResponse {}", networkInitResponse.getStatus());

        EzspLeaveNetworkRequest leaveNetworkRequest = new EzspLeaveNetworkRequest();
        EzspTransaction leaveNetworkTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(leaveNetworkRequest, EzspLeaveNetworkResponse.class));
        EzspLeaveNetworkResponse leaveNetworkResponse = (EzspLeaveNetworkResponse) leaveNetworkTransaction
                .getResponse();
        logger.debug(leaveNetworkResponse.toString());

        EzspNetworkInitRequest networkInitRequest2 = new EzspNetworkInitRequest();
        EzspTransaction networkInitTransaction2 = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkInitRequest2, EzspNetworkInitResponse.class));
        EzspNetworkInitResponse networkInitResponse2 = (EzspNetworkInitResponse) networkInitTransaction2.getResponse();
        logger.debug(networkInitResponse2.toString());
        logger.debug("EZSP networkInitResponse {}", networkInitResponse2.getStatus());

        // Check if the network is initialised or if we're yet to join
        switch (networkInitResponse.getStatus()) {
            case EMBER_SUCCESS:
                // We're done (??)
                break;
            case EMBER_NOT_JOINED:
                EmberNetworkInitialisation netInitialiser = new EmberNetworkInitialisation(ashHandler);
                netInitialiser.formNetwork();
                break;
            default:
                // Unexpected response
                logger.debug("Unexpected response from network initialisation: {}", networkInitResponse.getStatus());
                break;
        }

        EzspGetNetworkParametersRequest networkParametersRequest = new EzspGetNetworkParametersRequest();
        EzspTransaction networkParametersTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkParametersRequest, EzspGetNetworkParametersResponse.class));
        EzspGetNetworkParametersResponse networkParametersResponse = (EzspGetNetworkParametersResponse) networkParametersTransaction
                .getResponse();
        logger.debug(networkParametersResponse.toString());

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
    }

    @Override
    public void shutdown() {
        serialPort.close();
    }

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

        /*
         * if (response instanceof EzspLookupEui64ByNodeIdResponse) {
         * IeeeAddressResponse zdoResponse = new IeeeAddressResponse();
         * zdoResponse.setIeeeAddress(((EzspLookupEui64ByNodeIdResponse) response).getEui64());
         * zigbeeTransportReceive.receiveZdoCommand(zdoResponse);
         *
         * return;
         * }
         */

        /*
         * if (response instanceof EzspChildJoinHandler) {
         * // Convert to an announce
         * DeviceAnnounce zdoResponse = new DeviceAnnounce();
         * zdoResponse.setIeeeAddress(((EzspChildJoinHandler) response).getChildEui64().getLong());
         * zdoResponse.setSourceAddress(((EzspChildJoinHandler) response).getChildId());
         * zigbeeTransportReceive.receiveZdoCommand(zdoResponse);
         *
         * return;
         * }
         */

        logger.debug("Unhandled EZSP Frame: " + response.toString());
    }

    @Override
    public void handleLinkStateChange(boolean linkState) {
        // TODO: Handle link changes and notify framework or just reset link with dongle?
    }

    @Override
    public int getZigBeeChannel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean setZigBeeChannel(int channel) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getZigBeePanId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean setZigBeePanId(int panId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long getZigBeeExtendedPanId() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean setZigBeeExtendedPanId(long extendedPanId) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean setZigBeeSecurityKey(byte[] networkKey) {
        return false;
    }
}
