/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspFormNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspFormNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLeaveNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLeaveNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkFoundHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspScanCompleteHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetInitialSecurityStateRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetInitialSecurityStateResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberInitialSecurityState;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberJoinMethod;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspValueId;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspMultiResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
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
     * The frame handler used to send the EZSP frames to the NCP
     */
    private EzspProtocolHandler protocolHandler;

    /**
     * @param protocolHandler the {@link EzspProtocolHandler} used to communicate with the NCP
     */
    public EmberNetworkInitialisation(EzspProtocolHandler protocolHandler) {
        this.protocolHandler = protocolHandler;
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
     */
    public void formNetwork(EmberNetworkParameters networkParameters, ZigBeeKey linkKey, ZigBeeKey networkKey) {
        if (networkParameters.getExtendedPanId() == null) {
            networkParameters.setExtendedPanId(new ExtendedPanId());
        }

        logger.debug("Initialising Ember network with configuration {}", networkParameters);

        int scanDuration = 1; // 6

        // Leave the current network so we can initialise a new network
        if (checkNetworkJoined()) {
            doLeaveNetwork();
        }

        // Perform an energy scan to find a clear channel
        int quietestChannel = doEnergyScan(scanDuration);
        logger.debug("Energy scan reports quietest channel is {}", quietestChannel);

        // Check if any current networks were found and avoid those channels, PAN ID and especially Extended PAN ID
        doActiveScan(scanDuration);

        // Read the current network parameters
        getNetworkParameters();

        // Create a random PAN ID and Extended PAN ID
        if (networkParameters.getPanId() == 0 || networkParameters.getExtendedPanId().equals(new ExtendedPanId())) {
            Random random = new Random();
            int panId = random.nextInt(65535);
            networkParameters.setPanId(panId);
            logger.debug("Created random PAN ID: {}", panId);

            int extendedPanId[] = new int[8];
            StringBuilder extendedPanIdBuilder = new StringBuilder();
            for (int cnt = 0; cnt < 8; cnt++) {
                extendedPanId[cnt] = random.nextInt(256);
                extendedPanIdBuilder.append(String.format("%02X", extendedPanId[cnt]));
            }

            networkParameters.setExtendedPanId(new ExtendedPanId(extendedPanId));
            logger.debug("Created random Extended PAN ID: {}", extendedPanIdBuilder.toString());
        }

        if (networkParameters.getRadioChannel() == 0) {
            networkParameters.setRadioChannel(quietestChannel);
        }

        // If the channel set is empty, use the single channel defined above
        if (networkParameters.getChannels() == 0) {
            networkParameters.setChannels(1 << networkParameters.getRadioChannel());
        }

        // Initialise security
        setSecurityState(linkKey, networkKey);

        // And now form the network
        doFormNetwork(networkParameters.getPanId(), networkParameters.getExtendedPanId(),
                networkParameters.getRadioChannel());
    }

    private boolean checkNetworkJoined() {
        // Check if the network is initialised
        EzspNetworkStateRequest networkStateRequest = new EzspNetworkStateRequest();
        EzspTransaction networkStateTransaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(networkStateRequest, EzspNetworkStateResponse.class));
        EzspNetworkStateResponse networkStateResponse = (EzspNetworkStateResponse) networkStateTransaction
                .getResponse();
        logger.debug(networkStateResponse.toString());
        logger.debug("EZSP networkStateResponse {}", networkStateResponse.getStatus());

        return networkStateResponse.getStatus() == EmberNetworkStatus.EMBER_JOINED_NETWORK;
    }

    private boolean doLeaveNetwork() {
        EzspLeaveNetworkRequest leaveNetworkRequest = new EzspLeaveNetworkRequest();
        EzspTransaction leaveNetworkTransaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(leaveNetworkRequest, EzspLeaveNetworkResponse.class));
        EzspLeaveNetworkResponse leaveNetworkResponse = (EzspLeaveNetworkResponse) leaveNetworkTransaction
                .getResponse();
        logger.debug(leaveNetworkResponse.toString());

        return leaveNetworkResponse.getStatus() == EmberStatus.EMBER_SUCCESS;
    }

    /**
     * Performs an energy scan and returns the quietest channel
     *
     * @param scanDuration duration of the scan on each channel
     * @return the quietest channel, or null on error
     */
    private Integer doEnergyScan(int scanDuration) {
        EzspStartScanRequest energyScan = new EzspStartScanRequest();
        energyScan.setChannelMask(ZigBeeChannelMask.CHANNEL_MASK_2GHZ);
        energyScan.setDuration(scanDuration);
        energyScan.setScanType(EzspNetworkScanType.EZSP_ENERGY_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(Arrays.asList(EzspStartScanResponse.class,
                EzspNetworkFoundHandler.class, EzspEnergyScanResultHandler.class));
        EzspMultiResponseTransaction scanTransaction = new EzspMultiResponseTransaction(energyScan,
                EzspScanCompleteHandler.class, relatedResponses);
        protocolHandler.sendEzspTransaction(scanTransaction);

        EzspScanCompleteHandler scanCompleteResponse = (EzspScanCompleteHandler) scanTransaction.getResponse();
        logger.debug(scanCompleteResponse.toString());

        if (scanCompleteResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during energy scan: {}", scanCompleteResponse);
            // TODO: Error handling

            return null;
        }
        logger.debug("Energy scan completed: {}", scanCompleteResponse);

        int lowestRSSI = 999;
        int lowestChannel = 11;
        for (EzspFrameResponse response : scanTransaction.getResponses()) {
            if (!(response instanceof EzspEnergyScanResultHandler)) {
                continue;
            }

            EzspEnergyScanResultHandler energyResponse = (EzspEnergyScanResultHandler) response;
            if (energyResponse.getMaxRssiValue() < lowestRSSI) {
                lowestRSSI = energyResponse.getMaxRssiValue();
                lowestChannel = energyResponse.getChannel();
            }
        }

        return lowestChannel;
    }

    /**
     * Perform an active scan of all channels
     *
     * @param scanDuration
     * @return true if the security state was set successfully
     */
    private boolean doActiveScan(int scanDuration) {
        // Now do an active scan to see if there are other networks operating
        EzspStartScanRequest activeScan = new EzspStartScanRequest();
        activeScan.setChannelMask(ZigBeeChannelMask.CHANNEL_MASK_2GHZ);
        activeScan.setDuration(scanDuration);
        activeScan.setScanType(EzspNetworkScanType.EZSP_ACTIVE_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(Arrays.asList(EzspStartScanResponse.class,
                EzspNetworkFoundHandler.class, EzspEnergyScanResultHandler.class));
        EzspMultiResponseTransaction transaction = new EzspMultiResponseTransaction(activeScan,
                EzspScanCompleteHandler.class, relatedResponses);
        protocolHandler.sendEzspTransaction(transaction);
        EzspScanCompleteHandler activeScanCompleteResponse = (EzspScanCompleteHandler) transaction.getResponse();
        logger.debug(activeScanCompleteResponse.toString());

        if (activeScanCompleteResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during active scan: {}", activeScanCompleteResponse);
            return false;
        }
        return true;
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
        state.addBitmask(EmberInitialSecurityBitmask.EMBER_HAVE_PRECONFIGURED_KEY);
        state.addBitmask(EmberInitialSecurityBitmask.EMBER_HAVE_NETWORK_KEY);
        state.addBitmask(EmberInitialSecurityBitmask.EMBER_NO_FRAME_COUNTER_RESET);
        state.addBitmask(EmberInitialSecurityBitmask.EMBER_REQUIRE_ENCRYPTED_KEY);

        EmberKeyData networkKeyData = new EmberKeyData();
        networkKeyData.setContents(networkKey.getValue());
        state.setNetworkKey(networkKeyData);

        EmberKeyData linkKeyData = new EmberKeyData();
        linkKeyData.setContents(linkKey.getValue());
        state.setPreconfiguredKey(linkKeyData);
        state.setPreconfiguredTrustCenterEui64(new IeeeAddress());

        if (networkKey.hasSequenceCounter()) {
            state.setNetworkKeySequenceNumber(networkKey.getSequenceCounter());
        }

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

        if (!networkKey.hasFrameCounter() && !linkKey.hasFrameCounter()) {
            return true;
        }

        EmberNcp ncp = new EmberNcp(protocolHandler);
        if (networkKey.hasFrameCounter()) {
            EzspSerializer serializer = new EzspSerializer();
            serializer.serializeUInt32(networkKey.getFrameCounter());
            if (!ncp.setValue(EzspValueId.EZSP_VALUE_NWK_FRAME_COUNTER, serializer.getPayload())) {
                return false;
            }
        }
        if (linkKey.hasFrameCounter()) {
            EzspSerializer serializer = new EzspSerializer();
            serializer.serializeUInt32(linkKey.getFrameCounter());
            if (!ncp.setValue(EzspValueId.EZSP_VALUE_APS_FRAME_COUNTER, serializer.getPayload())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Forms the ZigBee network
     *
     * @param panId the panId as int
     * @param extendedPanId the extended pan ID as {@link ExtendedPanId}
     * @param channel the radio channel to use
     * @return true if the network was formed successfully
     */
    private boolean doFormNetwork(int panId, ExtendedPanId extendedPanId, int channel) {
        EmberNetworkParameters networkParameters = new EmberNetworkParameters();
        networkParameters.setJoinMethod(EmberJoinMethod.EMBER_USE_MAC_ASSOCIATION);
        networkParameters.setExtendedPanId(extendedPanId);
        networkParameters.setPanId(panId);
        networkParameters.setRadioChannel(channel);
        EzspFormNetworkRequest formNetwork = new EzspFormNetworkRequest();
        formNetwork.setParameters(networkParameters);
        EzspSingleResponseTransaction transaction = new EzspSingleResponseTransaction(formNetwork,
                EzspFormNetworkResponse.class);
        protocolHandler.sendEzspTransaction(transaction);
        EzspFormNetworkResponse formNetworkResponse = (EzspFormNetworkResponse) transaction.getResponse();
        logger.debug(formNetworkResponse.toString());
        if (formNetworkResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of network parameters: {}", formNetworkResponse);
            return false;
        }

        return true;
    }
}
