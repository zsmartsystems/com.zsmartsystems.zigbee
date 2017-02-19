package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkFoundHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspScanCompleteHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspStartScanResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspChannelMask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspNetworkScanType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspMultiResponseTransaction;

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
        EzspMultiResponseTransaction scanTransaction = new EzspMultiResponseTransaction(energyScan,
                EzspScanCompleteHandler.class, relatedResponses);
        ashHandler.sendEzspTransaction(scanTransaction);

        EzspScanCompleteHandler scanCompleteResponse = (EzspScanCompleteHandler) scanTransaction.getResponse();
        logger.debug(scanCompleteResponse.toString());

        if (scanCompleteResponse.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("Error during energy scan: {}", scanCompleteResponse);
            // TODO: Error handling
        }
        // logger.debug("Quietest channel is " + energyScan.getQuietestChannel());

        // Now do an active scan to see if there are other networks operating
        // EzspStartScanRequest activeScan = new EzspStartScanRequest();
        // activeScan.setChannelMask(EzspChannelMask.EZSP_CHANNEL_MASK_ALL.getKey());
        // activeScan.setDuration(scanDuration);
        // activeScan.setScanType(EzspNetworkScanType.EZSP_ACTIVE_SCAN);
        // activeScan = (EzspStartScanRequest) ashHandler.sendEzspRequest(activeScan);
        // logger.debug(activeScan.toString());

        // Check if any current networks were found and avoid those channels

        //
        Random random = new Random();
        int panId = random.nextInt(65535);
        int extendedPanId[] = new int[8];
        for (int cnt = 0; cnt < 8; cnt++) {
            extendedPanId[cnt] = random.nextInt(256);
        }
        // EmberNetworkParameters networkParameters = new EmberNetworkParameters();
        // networkParameters.setJoinMethod(EmberJoinMethod.EMBER_USE_MAC_ASSOCIATION);
        // networkParameters.setExtendedPanId(extendedPanId);
        // networkParameters.setPanId(panId);
        // networkParameters.setRadioChannel(energyScan.getQuietestChannel());

        // EzspGetNetworkParametersRequest networkParms = new EzspGetNetworkParametersRequest();
        // networkParms = (EzspGetNetworkParametersRequest) ashHandler.sendEzspRequest(networkParms);
        // logger.debug(networkParms.toString());

        // EzspSetInitialSecurityStateRequest securityState = new EzspSetInitialSecurityStateRequest();
        // EmberInitialSecurityState state = new EmberInitialSecurityState();
        // state.addBitmask(EmberInitialSecurityBitmask.EMBER_STANDARD_SECURITY_MODE);
        // state.addBitmask(EmberInitialSecurityBitmask.);
        // state.setNetworkKey(networkKey);
        // securityState.setState(state);
        // securityState = (EzspSetInitialSecurityStateRequest) ashHandler.sendEzspRequest(securityState);
        // logger.debug(securityState.toString());

        // EzspFormNetworkRequest formNetwork = new EzspFormNetworkRequest();
        // formNetwork.setParameters(networkParameters);
        // formNetwork = (EzspFormNetworkRequest) ashHandler.sendEzspRequest(formNetwork);
        // logger.debug(formNetwork.toString());
    }
}
