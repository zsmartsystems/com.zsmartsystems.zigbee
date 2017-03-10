package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspFormNetworkRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspFormNetworkResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkFoundHandler;
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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspChannelMask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspMultiResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspTransaction;

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

    private AshFrameHandler ashHandler;

    /**
     * @param ashHandler the {@link AshFrameHandler} used to communicate with the NCP
     */
    public EmberNetworkInitialisation(AshFrameHandler ashHandler) {
        this.ashHandler = ashHandler;
    }

    /**
     * This utility function uses emberStartScan, emberStopScan, emberScanCompleteHandler, emberEnergyScanResultHandler,
     * and emberNetworkFoundHandler to discover other networks or determine the background noise level. It then uses
     * emberFormNetwork to create a new network with a unique PAN-ID on a channel with low background noise.
     *
     */
    public void formNetwork() {
        int scanDuration = 1; // 6

        // First we do an energy scan to find a clear channel
        EzspStartScanRequest energyScan = new EzspStartScanRequest();
        energyScan.setChannelMask(EzspChannelMask.EZSP_CHANNEL_MASK_ALL.getKey());
        energyScan.setDuration(scanDuration);
        energyScan.setScanType(EzspNetworkScanType.EZSP_ENERGY_SCAN);

        Set<Class<?>> relatedResponses = new HashSet<Class<?>>(Arrays.asList(EzspStartScanResponse.class,
                EzspNetworkFoundHandler.class, EzspEnergyScanResultHandler.class));
        EzspTransaction transaction = new EzspMultiResponseTransaction(energyScan, EzspScanCompleteHandler.class,
                relatedResponses);
        ashHandler.sendEzspTransaction(transaction);

        EzspScanCompleteHandler energyScanCompleteResponse = (EzspScanCompleteHandler) transaction.getResponse();
        logger.debug(energyScanCompleteResponse.toString());

        if (energyScanCompleteResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during energy scan: {}", energyScanCompleteResponse);
            // TODO: Error handling
        }

        // logger.debug("Quietest channel is " + energyScan.getQuietestChannel());

        // Now do an active scan to see if there are other networks operating
        EzspStartScanRequest activeScan = new EzspStartScanRequest();
        activeScan.setChannelMask(EzspChannelMask.EZSP_CHANNEL_MASK_ALL.getKey());
        activeScan.setDuration(scanDuration);
        activeScan.setScanType(EzspNetworkScanType.EZSP_ACTIVE_SCAN);

        relatedResponses = new HashSet<Class<?>>(Arrays.asList(EzspStartScanResponse.class,
                EzspNetworkFoundHandler.class, EzspEnergyScanResultHandler.class));
        transaction = new EzspMultiResponseTransaction(energyScan, EzspScanCompleteHandler.class, relatedResponses);
        ashHandler.sendEzspTransaction(transaction);
        EzspScanCompleteHandler activeScanCompleteResponse = (EzspScanCompleteHandler) transaction.getResponse();
        logger.debug(activeScanCompleteResponse.toString());

        if (activeScanCompleteResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during energy scan: {}", activeScanCompleteResponse);
            // TODO: Error handling
        }

        // Read the current network parameters
        EzspGetNetworkParametersRequest networkParms = new EzspGetNetworkParametersRequest();
        transaction = new EzspSingleResponseTransaction(networkParms, EzspGetNetworkParametersResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspGetNetworkParametersResponse getNetworkParametersResponse = (EzspGetNetworkParametersResponse) transaction
                .getResponse();
        logger.debug(getNetworkParametersResponse.toString());
        if (getNetworkParametersResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of network parameters: {}", getNetworkParametersResponse);
            // TODO: Error handling
        }

        // Check if any current networks were found and avoid those channels, PAN ID and especially Extended PAN ID

        // Create a random PAN ID and Extended PAN ID
        Random random = new Random();
        int panId = random.nextInt(65535);
        int extendedPanId[] = new int[8];
        for (int cnt = 0; cnt < 8; cnt++) {
            extendedPanId[cnt] = random.nextInt(256);
        }

        EmberKeyData networkKey = new EmberKeyData();
        networkKey.setContents(new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 });
        EzspSetInitialSecurityStateRequest securityState = new EzspSetInitialSecurityStateRequest();
        EmberInitialSecurityState state = new EmberInitialSecurityState();
        state.setBitmask(EmberInitialSecurityBitmask.EMBER_HAVE_PRECONFIGURED_KEY);
        // state.addBitmask(EmberInitialSecurityBitmask.);
        state.setNetworkKey(networkKey);
        state.setPreconfiguredKey(networkKey);
        state.setPreconfiguredTrustCenterEui64(new IeeeAddress(0));
        securityState.setState(state);
        transaction = new EzspSingleResponseTransaction(securityState, EzspSetInitialSecurityStateResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspSetInitialSecurityStateResponse securityStateResponse = (EzspSetInitialSecurityStateResponse) transaction
                .getResponse();
        logger.debug(securityStateResponse.toString());
        if (securityStateResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of network parameters: {}", securityStateResponse);
            // TODO: Error handling
        }

        EmberNetworkParameters networkParameters = new EmberNetworkParameters();
        networkParameters.setJoinMethod(EmberJoinMethod.EMBER_USE_MAC_ASSOCIATION);
        networkParameters.setExtendedPanId(extendedPanId);
        networkParameters.setPanId(panId);
        networkParameters.setRadioChannel(11); // energyScan.getQuietestChannel());
        EzspFormNetworkRequest formNetwork = new EzspFormNetworkRequest();
        formNetwork.setParameters(networkParameters);
        transaction = new EzspSingleResponseTransaction(formNetwork, EzspFormNetworkResponse.class);
        ashHandler.sendEzspTransaction(transaction);
        EzspFormNetworkResponse formNetworkResponse = (EzspFormNetworkResponse) transaction.getResponse();
        logger.debug(formNetworkResponse.toString());
        if (formNetworkResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during retrieval of network parameters: {}", formNetworkResponse);
            // TODO: Error handling
        }
    }
}
