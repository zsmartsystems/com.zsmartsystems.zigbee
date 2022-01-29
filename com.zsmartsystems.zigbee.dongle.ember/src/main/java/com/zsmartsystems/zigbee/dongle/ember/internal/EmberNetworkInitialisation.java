/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspFindAndRejoinNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspFindAndRejoinNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspFormNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspFormNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspJoinNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspJoinNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetInitialSecurityStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetInitialSecurityStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberJoinMethod;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspValueId;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * This class provides utility functions to establish an Ember ZigBee network
 *
 * @author Chris Jackson
 *
 */
public class EmberNetworkInitialisation {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(EmberNetworkInitialisation.class);

    /**
     * The number of milliseconds to wait for the NCP to leave the network
     */
    private static final int WAIT_FOR_LEAVE = 5000;

    /**
     * The frame handler used to send the EZSP frames to the NCP
     */
    private EzspProtocolHandler protocolHandler;

    /**
     * Scan duration used for scans
     */
    private int scanDuration = 1;

    /**
     * @param protocolHandler the {@link EzspProtocolHandler} used to communicate with the NCP
     */
    public EmberNetworkInitialisation(EzspProtocolHandler protocolHandler) {
        this.protocolHandler = protocolHandler;
    }

    /**
     * Sets the scan duration used when performing scans.
     *
     * @param scanDuration the scan duration. Sets the exponent of the number of scan periods, where a scan period is
     *            960 symbols. The scan will occur for ((2^duration) + 1) scan periods.
     */
    public void setScanDuration(int scanDuration) {
        this.scanDuration = scanDuration;
    }

    /**
     * This utility function uses emberStartScan, emberStopScan, emberScanCompleteHandler, emberEnergyScanResultHandler,
     * and emberNetworkFoundHandler to discover other networks or determine the background noise level. It then uses
     * emberFormNetwork to create a new network with a unique PAN-ID on a channel with low background noise.
     * <p>
     * Setting the PAN-ID or Extended PAN-ID to 0 will set these values to a random value.
     * <p>
     * If channel is set to 0, the quietest channel will be used.
     *
     * @param networkParameters the required {@link EmberNetworkParameters}
     * @param linkKey the {@link ZigBeeKey} with the link key. This can not be set to all 00 or all FF.
     * @param networkKey the {@link ZigBeeKey} with the network key. This can not be set to all 00 or all FF.
     * @return {@link ZigBeeStatus} with success or failure code
     */
    public ZigBeeStatus formNetwork(EmberNetworkParameters networkParameters, ZigBeeKey linkKey, ZigBeeKey networkKey) {
        if (networkParameters.getExtendedPanId() == null) {
            networkParameters.setExtendedPanId(new ExtendedPanId());
        }

        logger.debug("Initialising Ember network with configuration {}", networkParameters);

        EmberNcp ncp = new EmberNcp(protocolHandler);

        // Leave the current network so we can initialise a new network
        ensureNetworkLeft();

        // Perform an energy scan to find a clear channel
        Integer quietestChannel = doEnergyScan(ncp, scanDuration);
        if (quietestChannel == null) {
            return ZigBeeStatus.FATAL_ERROR;
        }
        logger.debug("Energy scan reports quietest channel is {}", quietestChannel);

        // Check if any current networks were found and avoid those channels, PAN ID and especially Extended PAN ID
        ncp.doActiveScan(ZigBeeChannelMask.CHANNEL_MASK_2GHZ, scanDuration);

        // Read the current network parameters
        getNetworkParameters();

        // Create a random PAN ID and Extended PAN ID
        if (networkParameters.getPanId() == 0 || networkParameters.getExtendedPanId().equals(new ExtendedPanId())) {
            Random random = new Random();
            int panId = random.nextInt(65535);
            networkParameters.setPanId(panId);
            logger.debug("Created random PAN ID: {}", String.format("%04X", panId));

            int extendedPanId[] = new int[8];
            StringBuilder extendedPanIdBuilder = new StringBuilder();
            for (int cnt = 0; cnt < 8; cnt++) {
                extendedPanId[cnt] = random.nextInt(256);
                extendedPanIdBuilder.append(String.format("%02X", extendedPanId[cnt]));
            }

            networkParameters.setExtendedPanId(new ExtendedPanId(extendedPanId));
            logger.debug("Created random Extended PAN ID: {}", extendedPanIdBuilder.toString());
        }

        if (networkParameters.getRadioChannel() == ZigBeeChannel.UNKNOWN.getChannel()) {
            networkParameters.setRadioChannel(quietestChannel);
        }

        // If the channel set is empty, use the single channel defined above
        if (networkParameters.getChannels() == 0) {
            networkParameters.setChannels(1 << networkParameters.getRadioChannel());
        }

        // Initialise security
        setSecurityState(linkKey, networkKey);

        // And now form the network
        if (doFormNetwork(networkParameters) == false) {
            return ZigBeeStatus.FATAL_ERROR;
        }

        return ZigBeeStatus.SUCCESS;
    }

    /**
     * Utility function to join an existing network as a Router
     *
     * @param networkParameters the required {@link EmberNetworkParameters}
     * @param linkKey the {@link ZigBeeKey} with the initial link key. This cannot be set to all 00 or all FF.
     */
    public void joinNetwork(EmberNetworkParameters networkParameters, ZigBeeKey linkKey) {
        logger.debug("Joining Ember network with configuration {}", networkParameters);

        // Leave the current network so we can initialise a new network
        ensureNetworkLeft();

        // Initialise security - no network key as we'll get that from the coordinator
        setSecurityState(linkKey, null);

        doJoinNetwork(networkParameters);
    }

    /**
     * Searches for the current network, assuming that we know the network key.
     * This will search all current channels.
     */
    public void rejoinNetwork() {
        doRejoinNetwork(true, new ZigBeeChannelMask(0));
    }

    /**
     * Ensures that the NCP is not on a network. If the NCP is currently joined to a network, then it will leave and
     * will wait until the network state is {@link EmberNetworkStatus#EMBER_NO_NETWORK} before returning.
     *
     * @return true if the NCP is not on a network. false if the network leave was unsuccessful.
     */
    private boolean ensureNetworkLeft() {
        EmberNcp ncp = new EmberNcp(protocolHandler);

        // If the current network state isn't NO NETWORK, or LEAVING NETWORK, then we leave the current network
        EmberNetworkStatus currentNetworkState = ncp.getNetworkState();
        if (currentNetworkState != EmberNetworkStatus.EMBER_NO_NETWORK
                && currentNetworkState != EmberNetworkStatus.EMBER_LEAVING_NETWORK) {
            logger.debug("Ember network initialisation: Leaving current network. Network status={}",
                    currentNetworkState);
            ncp.leaveNetwork();
        }

        // Then wait until the state is NO NETWORK
        long timer = System.currentTimeMillis() + WAIT_FOR_LEAVE;
        do {
            currentNetworkState = ncp.getNetworkState();
            if (currentNetworkState == EmberNetworkStatus.EMBER_NO_NETWORK) {
                break;
            }
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                break;
            }
        } while (timer > System.currentTimeMillis());

        if (currentNetworkState != EmberNetworkStatus.EMBER_NO_NETWORK) {
            logger.debug("Ember network initialisation: Failed to leave network. Network status={}",
                    currentNetworkState);
            return false;
        }
        logger.debug("Ember network initialisation: Network leave confirmed");

        // And clear the key table so we don't leave any device specific keys set
        ncp.clearKeyTable();

        return true;
    }

    /**
     * Performs an energy scan and returns the quietest channel
     *
     * @param ncp {@link EmberNcp}
     * @param scanDuration duration of the scan on each channel
     * @return the quietest channel, or null on error
     */
    private Integer doEnergyScan(EmberNcp ncp, int scanDuration) {
        List<EzspEnergyScanResultHandler> channels = ncp.doEnergyScan(ZigBeeChannelMask.CHANNEL_MASK_2GHZ,
                scanDuration);

        if (channels == null) {
            logger.debug("Error during energy scan: {}", ncp.getLastStatus());
            return null;
        }

        int[] ccaThresholdArray = ncp.getValue(EzspValueId.EZSP_VALUE_CCA_THRESHOLD);
        int ccaThreshold = (byte) ccaThresholdArray[0];

        int lowestRSSI = 999;
        int lowestChannel = 11;
        int ccaThresholdViolation = 0;
        for (EzspEnergyScanResultHandler channel : channels) {
            if (channel.getMaxRssiValue() < lowestRSSI) {
                lowestRSSI = channel.getMaxRssiValue();
                lowestChannel = channel.getChannel();
            }

            if (channel.getMaxRssiValue() > ccaThreshold) {
                ccaThresholdViolation++;
            }
        }

        logger.debug(
                "Energy scan complete: Channel {} has lowest RSSI at {}dBm. {}/{} channels violate CCA threshold of {}dBm",
                lowestChannel, lowestRSSI, ccaThresholdViolation, channels.size(), ccaThreshold);
        if (lowestRSSI > ccaThreshold) {
            logger.warn("Energy scan found all channels violate CCA threshold!");
            return null;
        }

        return lowestChannel;
    }

    /**
     * Get the current network parameters
     *
     * @return the {@link EmberNetworkParameters} or null on error
     */
    private EmberNetworkParameters getNetworkParameters() {
        EzspGetNetworkParametersRequest networkParms = new EzspGetNetworkParametersRequest();
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(networkParms,
                EzspGetNetworkParametersResponse.class);
        protocolHandler.sendEzspTransaction(transaction);
        EzspGetNetworkParametersResponse getNetworkParametersResponse = (EzspGetNetworkParametersResponse) transaction
                .getResponse();
        logger.debug(getNetworkParametersResponse.toString());
        if (getNetworkParametersResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of network parameters: {}", getNetworkParametersResponse);
            return null;
        }
        return getNetworkParametersResponse.getParameters();
    }

    /**
     * Sets the initial security state
     *
     * @param linkKey the initial {@link ZigBeeKey}
     * @param networkKey the initial {@link ZigBeeKey}
     * @return true if the security state was set successfully
     */
    private boolean setSecurityState(ZigBeeKey linkKey, ZigBeeKey networkKey) {
        EzspSetInitialSecurityStateRequest securityState = new EzspSetInitialSecurityStateRequest();
        EmberInitialSecurityState state = new EmberInitialSecurityState();
        state.addBitmask(EmberInitialSecurityBitmask.EMBER_TRUST_CENTER_GLOBAL_LINK_KEY);

        EmberKeyData networkKeyData = new EmberKeyData();
        if (networkKey != null) {
            networkKeyData.setContents(networkKey.getValue());
            state.addBitmask(EmberInitialSecurityBitmask.EMBER_HAVE_NETWORK_KEY);
            if (networkKey.hasSequenceNumber()) {
                state.setNetworkKeySequenceNumber(networkKey.getSequenceNumber());
            }
        }
        state.setNetworkKey(networkKeyData);

        EmberKeyData linkKeyData = new EmberKeyData();
        if (linkKey != null) {
            linkKeyData.setContents(linkKey.getValue());
            state.addBitmask(EmberInitialSecurityBitmask.EMBER_HAVE_PRECONFIGURED_KEY);
            state.addBitmask(EmberInitialSecurityBitmask.EMBER_REQUIRE_ENCRYPTED_KEY);
        }
        state.setPreconfiguredKey(linkKeyData);

        state.setPreconfiguredTrustCenterEui64(new IeeeAddress());

        securityState.setState(state);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(securityState,
                EzspSetInitialSecurityStateResponse.class);
        protocolHandler.sendEzspTransaction(transaction);
        EzspSetInitialSecurityStateResponse securityStateResponse = (EzspSetInitialSecurityStateResponse) transaction
                .getResponse();
        logger.debug(securityStateResponse.toString());
        if (securityStateResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of network parameters: {}", securityStateResponse);
            return false;
        }

        EmberNcp ncp = new EmberNcp(protocolHandler);
        if (networkKey != null && networkKey.hasOutgoingFrameCounter()) {
            EzspSerializer serializer = new EzspSerializer();
            serializer.serializeUInt32(networkKey.getOutgoingFrameCounter());
            if (ncp.setValue(EzspValueId.EZSP_VALUE_NWK_FRAME_COUNTER,
                    serializer.getPayload()) != EzspStatus.EZSP_SUCCESS) {
                return false;
            }
        }
        if (linkKey != null && linkKey.hasOutgoingFrameCounter()) {
            EzspSerializer serializer = new EzspSerializer();
            serializer.serializeUInt32(linkKey.getOutgoingFrameCounter());
            if (ncp.setValue(EzspValueId.EZSP_VALUE_APS_FRAME_COUNTER,
                    serializer.getPayload()) != EzspStatus.EZSP_SUCCESS) {
                return false;
            }
        }

        return true;
    }

    /**
     * Forms the ZigBee network as a coordinator
     *
     * @param networkParameters the {@link EmberNetworkParameters}
     * @return true if the network was formed successfully
     */
    private boolean doFormNetwork(EmberNetworkParameters networkParameters) {
        networkParameters.setJoinMethod(EmberJoinMethod.EMBER_USE_MAC_ASSOCIATION);

        EzspFormNetworkRequest formNetwork = new EzspFormNetworkRequest();
        formNetwork.setParameters(networkParameters);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(formNetwork,
                EzspFormNetworkResponse.class);
        protocolHandler.sendEzspTransaction(transaction);
        EzspFormNetworkResponse formNetworkResponse = (EzspFormNetworkResponse) transaction.getResponse();
        logger.debug(formNetworkResponse.toString());
        if (formNetworkResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error forming network: {}", formNetworkResponse);
            return false;
        }

        return true;
    }

    /**
     * Joins an existing ZigBee network as a router
     *
     * @param networkParameters the {@link EmberNetworkParameters}
     * @return true if the network was joined successfully
     */
    private boolean doJoinNetwork(EmberNetworkParameters networkParameters) {
        networkParameters.setJoinMethod(EmberJoinMethod.EMBER_USE_MAC_ASSOCIATION);

        EzspJoinNetworkRequest joinNetwork = new EzspJoinNetworkRequest();
        joinNetwork.setNodeType(EmberNodeType.EMBER_ROUTER);
        joinNetwork.setParameters(networkParameters);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(joinNetwork,
                EzspJoinNetworkResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspJoinNetworkResponse joinNetworkResponse = (EzspJoinNetworkResponse) transaction.getResponse();
        logger.debug(joinNetworkResponse.toString());
        if (joinNetworkResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error joining network: {}", joinNetworkResponse);
            return false;
        }

        return true;
    }

    /**
     * Rejoins an existing ZigBee network as a router.
     *
     * @param haveCurrentNetworkKey true if we already know the network key
     * @param channelMask the channel mask to scan.
     * @return true if the network was joined successfully
     */
    private boolean doRejoinNetwork(boolean haveCurrentNetworkKey, ZigBeeChannelMask channelMask) {
        EzspFindAndRejoinNetworkRequest rejoinNetwork = new EzspFindAndRejoinNetworkRequest();
        rejoinNetwork.setHaveCurrentNetworkKey(haveCurrentNetworkKey);
        rejoinNetwork.setChannelMask(channelMask.getChannelMask());
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(rejoinNetwork,
                EzspFindAndRejoinNetworkResponse.class);
        protocolHandler.sendEzspTransaction(transaction);

        EzspFindAndRejoinNetworkResponse rejoinNetworkResponse = (EzspFindAndRejoinNetworkResponse) transaction
                .getResponse();
        logger.debug(rejoinNetworkResponse.toString());
        if (rejoinNetworkResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error rejoining network: {}", rejoinNetworkResponse);
            return false;
        }

        return true;
    }

}
