/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCalculateSmacs283k1Handler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCalculateSmacsHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearTemporaryDataMaybeStoreLinkKey283k1Response;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearTemporaryDataMaybeStoreLinkKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGenerateCbkeKeys283k1Handler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGenerateCbkeKeysHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeExchange;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * Provides CBKE services using the Ember NCP.
 *
 * Note that the Ember NCP only supports a single crypto transaction at once.
 *
 * @author Chris Jackson
 *
 */
public class EmberCbkeExchange implements ZigBeeCbkeExchange {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(EmberCbkeExchange.class);

    /**
     * The {@link EmberCbkeProvider} used to communicate with the NCP
     */
    private final EmberCbkeProvider cbkeProvider;

    /**
     * Defines if this provider is the initiator (true=Client) or not (false=Server)
     */
    private boolean amInitiator = true;

    /**
     * The crypto suite being used for this exchange
     */
    private ZigBeeCryptoSuites suite;

    /**
     * The partner certificate
     */
    private ByteArray partnerCertificate;

    /**
     * The partner ephemeral data
     */
    private ByteArray partnerEphemeralData;

    /**
     * Both initiator and responder MACs are returned in the same call, so we buffer the responder here to avoid making
     * a second call.
     */
    private ByteArray responderMac;

    /**
     * Creates the Ember CBKE exchange
     *
     * @param cbkeProvider the {@link EmberCbkeProvider} this exchange is to use
     * @param amInitiator true if for this transfer the local node is initiating the exchange
     */
    public EmberCbkeExchange(EmberCbkeProvider cbkeProvider, boolean amInitiator) {
        logger.debug("Ember CBKE Provider: Exchange starting, intiator={}", amInitiator);
        this.cbkeProvider = cbkeProvider;
        this.amInitiator = amInitiator;
    }

    @Override
    public ZigBeeStatus setCryptoSuite(ZigBeeCryptoSuites suite) {
        this.suite = suite;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeCryptoSuites getCryptoSuite() {
        return suite;
    }

    @Override
    public ByteArray getCertificate() {
        if (suite == null) {
            logger.debug("Unable to request ephemeral data until Crypto Suite is set");
            return null;
        }

        return cbkeProvider.getCertificate(suite);
    }

    @Override
    public ByteArray getCbkeEphemeralData() {
        if (suite == null) {
            logger.debug("Unable to request ephemeral data until Crypto Suite is set");
            return null;
        }

        switch (suite) {
            case ECC_163K1:
                EzspGenerateCbkeKeysHandler response163k1 = cbkeProvider.ezspGenerateCbke163k1Keys();
                if (response163k1 == null || response163k1.getStatus() != EmberStatus.EMBER_SUCCESS) {
                    break;
                }
                return new ByteArray(response163k1.getEphemeralPublicKey().getContents());
            case ECC_283K1:
                EzspGenerateCbkeKeys283k1Handler response283k1 = cbkeProvider.ezspGenerateCbke283k1Keys();
                if (response283k1 == null || response283k1.getStatus() != EmberStatus.EMBER_SUCCESS) {
                    break;
                }
                return new ByteArray(response283k1.getEphemeralPublicKey().getContents());
            default:
                break;
        }

        return null;
    }

    @Override
    public ZigBeeStatus addPartnerCertificate(ByteArray partnerCertificate) {
        this.partnerCertificate = partnerCertificate;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus addPartnerEphemeralData(ByteArray partnerEphemeralData) {
        this.partnerEphemeralData = partnerEphemeralData;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ByteArray getInitiatorMac() {
        if (suite == null) {
            logger.debug("Unable to request initiator MAC until Crypto Suite is set");
            return null;
        }
        if (partnerEphemeralData == null || partnerCertificate == null) {
            logger.debug("Unable to request initiator MAC until partner ephemeral data and certificate are set");
            return null;
        }

        switch (suite) {
            case ECC_163K1:
                EzspCalculateSmacsHandler response163k1 = cbkeProvider.ezspCalculateSmacs163k1(amInitiator,
                        partnerCertificate, partnerEphemeralData);
                if (response163k1 == null || response163k1.getStatus() != EmberStatus.EMBER_SUCCESS) {
                    break;
                }

                if (amInitiator) {
                    responderMac = new ByteArray(response163k1.getResponderSmac().getContents());
                    return new ByteArray(response163k1.getInitiatorSmac().getContents());
                } else {
                    responderMac = new ByteArray(response163k1.getInitiatorSmac().getContents());
                    return new ByteArray(response163k1.getResponderSmac().getContents());
                }
            case ECC_283K1:
                EzspCalculateSmacs283k1Handler response283k1 = cbkeProvider.ezspCalculateSmacs283k1(amInitiator,
                        partnerCertificate, partnerEphemeralData);
                if (response283k1 == null || response283k1.getStatus() != EmberStatus.EMBER_SUCCESS) {
                    break;
                }

                if (amInitiator) {
                    responderMac = new ByteArray(response283k1.getResponderSmac().getContents());
                    return new ByteArray(response283k1.getInitiatorSmac().getContents());
                } else {
                    responderMac = new ByteArray(response283k1.getInitiatorSmac().getContents());
                    return new ByteArray(response283k1.getResponderSmac().getContents());
                }
            default:
                break;
        }

        return null;
    }

    @Override
    public ByteArray getResponderMac() {
        return responderMac;
    }

    @Override
    public ZigBeeStatus completeKeyExchange(boolean success) {
        if (suite == null) {
            logger.debug("Unable to complete key exchange until Crypto Suite is set");
            return ZigBeeStatus.FAILURE;
        }
        logger.debug("Ember CBKE Provider: Exchange complete, success={}", success);

        switch (suite) {
            case ECC_163K1:
                EzspClearTemporaryDataMaybeStoreLinkKeyResponse response163k1 = cbkeProvider
                        .clearTemporaryDataMaybeStoreLinkKey163k1(success);
                return (response163k1.getStatus() == EmberStatus.EMBER_SUCCESS) ? ZigBeeStatus.SUCCESS
                        : ZigBeeStatus.FAILURE;
            case ECC_283K1:
                EzspClearTemporaryDataMaybeStoreLinkKey283k1Response response283k1 = cbkeProvider
                        .clearTemporaryDataMaybeStoreLinkKey283k1(success);
                return (response283k1.getStatus() == EmberStatus.EMBER_SUCCESS) ? ZigBeeStatus.SUCCESS
                        : ZigBeeStatus.FAILURE;
            default:
                return ZigBeeStatus.FAILURE;
        }
    }

}
