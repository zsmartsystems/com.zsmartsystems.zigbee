package com.zsmartsystems.zigbee.dongle.ember;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeApsHeader;
import com.zsmartsystems.zigbee.ZigBeeException;
import com.zsmartsystems.zigbee.ZigBeeNwkHeader;
import com.zsmartsystems.zigbee.ZigBeePort;
import com.zsmartsystems.zigbee.ZigBeeTransportReceive;
import com.zsmartsystems.zigbee.ZigBeeTransportState;
import com.zsmartsystems.zigbee.ZigBeeTransportTransmit;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddEndpointRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddEndpointResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspIncomingMessageHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkInitResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspPermitJoiningRequest;
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
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorRequest;

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
    }

    @Override
    public boolean startup() {
        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.UNINITIALISED);

        if (!serialPort.open()) {
            logger.error("Unable to open serial port");
            return false;
        }

        // final OutputStream out = serialPort.getOutputStream();
        ashHandler = new AshFrameHandler(serialPort.getInputStream(), serialPort.getOutputStream(), this);

        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.INITIALISING);

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
        stackConfigurer.doConfiguration(stackConfiguration);

        // Add our endpoint(s)
        createEndpoints();

        initialiseNetwork();

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
                // Unknown response
                break;
        }

        EzspGetNetworkParametersRequest networkParametersRequest = new EzspGetNetworkParametersRequest();
        EzspTransaction networkParametersTransaction = ashHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkParametersRequest, EzspGetNetworkParametersResponse.class));
        EzspGetNetworkParametersResponse networkParametersResponse = (EzspGetNetworkParametersResponse) networkParametersTransaction
                .getResponse();
        logger.debug(networkParametersResponse.toString());

        /*
         * ashHandler .sendEzspRequest(new EzspGetNetworkParameters());
         * logger.debug(nwParameters.toString());
         *
         * EzspStartScan scanStart = (EzspStartScan)
         * ashHandler.sendEzspRequest(new EzspStartScan(
         * EzspNetworkScanType.EZSP_ENERGY_SCAN, 0, 1));
         * logger.debug(scanStart.toString());
         */
        zigbeeTransportReceive.setNetworkState(ZigBeeTransportState.ONLINE);
    }

    @Override
    public void shutdown() {
        serialPort.close();
    }

    @Override
    public void sendZclCommand(ZigBeeNwkHeader nwkHeader, ZigBeeApsHeader apsHeader, int[] payload)
            throws ZigBeeException {
        EzspSendUnicastRequest emberUnicast = new EzspSendUnicastRequest();
        EmberApsFrame apsFrame = new EmberApsFrame();
        apsFrame.setClusterId(apsHeader.getCluster());
        apsFrame.setProfileId(apsHeader.getProfile());
        apsFrame.setSourceEndpoint(apsHeader.getSourceEndpoint());
        apsFrame.setDestinationEndpoint(apsHeader.getDestinationEndpoint());
        apsFrame.setSequence(apsHeader.getApsCounter());
        apsFrame.setOptions(EmberApsOption.EMBER_APS_OPTION_RETRY);

        // if (zclCommand.getDestinationAddress() instanceof ZigBeeDeviceAddress) {
        // ZigBeeDeviceAddress deviceAddress = (ZigBeeDeviceAddress) zclCommand.getDestinationAddress();
        // emberUnicast.setIndexOrDestination(deviceAddress.getAddress());
        // apsFrame.setDestinationEndpoint(deviceAddress.getEndpoint());
        // } else {
        // ZigBeeGroupAddress groupAddress = (ZigBeeGroupAddress) zclCommand.getDestinationAddress();
        // apsFrame.setGroupId(groupAddress.getGroupId());
        // }

        emberUnicast.setMessageTag(nwkHeader.getSequence());
        emberUnicast.setSequenceNumber(nwkHeader.getSequence());
        emberUnicast.setType(EmberOutgoingMessageType.EMBER_OUTGOING_DIRECT);
        emberUnicast.setApsFrame(apsFrame);

        emberUnicast.setMessageContents(payload);
        logger.debug(emberUnicast.toString());
        ashHandler.queueFrame(emberUnicast);

        // emberUnicast = (EzspSendUnicast) ashHandler.sendEzspRequestAsync(emberUnicast);
    }

    @Override
    public void sendZdoCommand(ZdoCommand command) {
        // if (command instanceof ActiveEndpointsRequest) {
        // }
        // if (command instanceof IeeeAddressRequest) {
        // IeeeAddressRequest incomingCommand = (IeeeAddressRequest) command;
        // EzspLookupEui64ByNodeIdRequest request = new EzspLookupEui64ByNodeIdRequest();
        // request.setNodeId(incomingCommand.getDestinationAddress());
        // ashHandler.queueFrame(request);

        // return;
        // }
        // if (command instanceof SimpleDescriptorRequest) {
        // }

        if (command instanceof NodeDescriptorRequest) {
            // (NodeDescriptorRequest)
        }

        // if (command instanceof PowerDescriptorRequest) {
        // }

        if (command instanceof ManagementPermitJoiningRequest) {
            ManagementPermitJoiningRequest incomingCommand = (ManagementPermitJoiningRequest) command;

            EzspPermitJoiningRequest emberJoin = new EzspPermitJoiningRequest();
            emberJoin.setDuration(incomingCommand.getPermitDuration());
            ashHandler.queueFrame(emberJoin);

            return;
        }
        // if (command instanceof BindRequest) {
        // }
        // if (command instanceof UnbindRequest) {
        // }
        // if (command instanceof UserDescriptorSet) {
        // }
        // if (command instanceof UserDescriptorRequest) {
        // }
        // if (command instanceof ManagementLqiRequest) {
        // }

        logger.debug("Unhandled ZDO Request: " + command.toString());
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeTransportReceive = zigbeeTransportReceive;
    }

    @Override
    public void handlePacket(EzspFrame response) {
        if (response instanceof EzspIncomingMessageHandler) {
            EzspIncomingMessageHandler incomingMessage = (EzspIncomingMessageHandler) response;
            EmberApsFrame apsFrame = incomingMessage.getApsFrame();

            ZigBeeApsHeader apsHeader = new ZigBeeApsHeader();
            apsHeader.setApsCounter(apsFrame.getSequence());
            apsHeader.setCluster(apsFrame.getClusterId());
            apsHeader.setDestinationEndpoint(apsFrame.getDestinationEndpoint());
            apsHeader.setProfile(apsFrame.getProfileId());
            apsHeader.setSourceEndpoint(apsFrame.getSourceEndpoint());

            ZigBeeNwkHeader nwkHeader = new ZigBeeNwkHeader();
            nwkHeader.setSequence(incomingMessage.getSequenceNumber());
            nwkHeader.setSourceAddress(incomingMessage.getSender());

            int[] x = Arrays.copyOfRange(incomingMessage.getMessageContents(), 1,
                    incomingMessage.getMessageContents().length);
            zigbeeTransportReceive.receiveZclCommand(nwkHeader, apsHeader, incomingMessage.getMessageContents());

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
    public boolean initialize() {
        // TODO Auto-generated method stub
        return false;
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
