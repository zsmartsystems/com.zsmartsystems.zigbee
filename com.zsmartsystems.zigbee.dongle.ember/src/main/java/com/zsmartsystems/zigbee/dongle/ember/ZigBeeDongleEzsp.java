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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspAddEndpointRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspIncomingMessageHandlerResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspNetworkInitRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspPermitJoiningRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspSendUnicast;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.network.EmberNetworkInitialisation;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclProfileType;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoinRequest;

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

    private AshFrameHandler ashHandler;

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
        version = (EzspVersionRequest) ashHandler.sendEzspRequest(version);
        logger.debug(version.toString());

        // Perform any stack configuration

        // Add our endpoint(s)
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
            addEndpoint = (EzspAddEndpointRequest) ashHandler.sendEzspRequest(addEndpoint);
            logger.debug(addEndpoint.toString());

            endpoint++;
        }

        // Now intialise the network
        EzspNetworkInitRequest networkInit = new EzspNetworkInitRequest();
        networkInit = (EzspNetworkInitRequest) ashHandler.sendEzspRequest(networkInit);
        logger.debug(networkInit.toString());

        // Check if the network is initialised or if we're yet to join
        switch (networkInit.getEmberStatus()) {
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

        return true;
    }

    @Override
    public void shutdown() {
        serialPort.close();
    }

    @Override
    public void sendZclCommand(ZigBeeNwkHeader nwkHeader, ZigBeeApsHeader apsHeader, int[] payload)
            throws ZigBeeException {
        EzspSendUnicast emberUnicast = new EzspSendUnicast();
        EmberApsFrame apsFrame = new EmberApsFrame(apsHeader);

        apsFrame.setClusterId(apsHeader.getCluster());
        apsFrame.addOption(EmberApsOption.EMBER_APS_OPTION_RETRY);

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

        emberUnicast = (EzspSendUnicast) ashHandler.sendEzspRequestAsync(emberUnicast);
        logger.debug(emberUnicast.toString());
    }

    @Override
    public void sendZdoCommand(ZdoCommand command) {
        // if (command instanceof ActiveEndpointsRequest) {
        // }
        // if (command instanceof IeeeAddressRequest) {
        // }
        // if (command instanceof SimpleDescriptorRequest) {
        // }
        // if (command instanceof NodeDescriptorRequest) {
        // }
        // if (command instanceof PowerDescriptorRequest) {
        // }
        if (command instanceof ManagementPermitJoinRequest) {
            ManagementPermitJoinRequest join = (ManagementPermitJoinRequest) command;
            join.getDuration();

            EzspPermitJoiningRequest emberJoin = new EzspPermitJoiningRequest();
            emberJoin.setDuration(join.getDuration());
            emberJoin = (EzspPermitJoiningRequest) ashHandler.sendEzspRequest(emberJoin);
            logger.debug(emberJoin.toString());
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
    }

    @Override
    public void setZigBeeTransportReceive(ZigBeeTransportReceive zigbeeTransportReceive) {
        this.zigbeeTransportReceive = zigbeeTransportReceive;
    }

    @Override
    public void handlePacket(EzspFrame response) {
        logger.debug("Unhandled EZSP Frame: " + response.toString());

        if (response instanceof EzspIncomingMessageHandlerResponse) {
            EzspIncomingMessageHandlerResponse incomingMessage = (EzspIncomingMessageHandlerResponse) response;
            EmberApsFrame apsFrame = incomingMessage.getApsFrame();

            ZigBeeApsHeader apsHeader = new ZigBeeApsHeader();
            apsHeader.setCluster(apsFrame.getClusterId());
            apsHeader.setDestinationEndpoint(apsFrame.getDestinationEndpoint());
            apsHeader.setProfile(apsFrame.getProfileId());
            apsHeader.setSourceEndpoint(apsFrame.getSourceEndpoint());

            ZigBeeNwkHeader nwkHeader = new ZigBeeNwkHeader();
            nwkHeader.setSequence(incomingMessage.getSequenceNumber());
            nwkHeader.setSourceAddress(incomingMessage.getAddressIndex());

            zigbeeTransportReceive.receiveZclCommand(nwkHeader, apsHeader, incomingMessage.getOutputBuffer());
        }
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
}
