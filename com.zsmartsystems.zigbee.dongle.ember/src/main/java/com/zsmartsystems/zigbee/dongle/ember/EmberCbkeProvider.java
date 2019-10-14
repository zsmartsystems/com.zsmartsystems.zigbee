/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCalculateSmacs283k1Handler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCalculateSmacs283k1Request;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCalculateSmacs283k1Response;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCalculateSmacsHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCalculateSmacsRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCalculateSmacsResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearTemporaryDataMaybeStoreLinkKey283k1Request;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearTemporaryDataMaybeStoreLinkKey283k1Response;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearTemporaryDataMaybeStoreLinkKeyRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspClearTemporaryDataMaybeStoreLinkKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGenerateCbkeKeys283k1Handler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGenerateCbkeKeys283k1Request;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGenerateCbkeKeys283k1Response;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGenerateCbkeKeysHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGenerateCbkeKeysRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGenerateCbkeKeysResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetPreinstalledCbkeData283k1Request;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetPreinstalledCbkeData283k1Response;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetPreinstalledCbkeDataRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetPreinstalledCbkeDataResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetValueRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSetValueResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCertificate283k1Data;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCertificateData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStruct;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyStructBitmask;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberLibraryId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberLibraryStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberPrivateKey283k1Data;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberPrivateKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberPublicKey283k1Data;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberPublicKeyData;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspValueId;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * Provides CBKE services using the Ember NCP
 *
 * @author Chris Jackson
 *
 */
public class EmberCbkeProvider implements ZigBeeCbkeProvider {
    /**
     * The time it will take the NCP to generate the ephemeral data
     */
    private final static int EPHEMERAL_DATA_GENERATE_TIME = 4;

    /**
     * The time it will take the NCP to generate the confirm key
     */
    private final static int CONFIRM_KEY_GENERATE_TIME = 6;

    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(EmberCbkeProvider.class);

    /**
     * The {@link ZigBeeDongleEzsp} to be used for the crypto calls
     */
    private final ZigBeeDongleEzsp dongle;

    /**
     * The partner certificate
     */
    private ByteArray partnerCertificate;

    /**
     * The partner ephemeral data
     */
    private ByteArray partnerEphemeralData;

    /**
     * A list of crypto suites supported by the NCP
     */
    private Set<ZigBeeCryptoSuites> supportedSuites;

    /**
     * A list of crypto suites supported by the NCP
     */
    private Map<ZigBeeCryptoSuites, ByteArray> availableCertificates;

    /**
     * Both initiator and responder MACs are returned in the same call, so we buffer the responder here to avoid making
     * a second call.
     */
    private ByteArray responderMac;

    public EmberCbkeProvider(ZigBeeDongleEzsp dongle) {
        this.dongle = dongle;
    }

    @Override
    public boolean isAuthorised(IeeeAddress partner) {
        EmberNcp ncp = dongle.getEmberNcp();

        // Check the TC Link key first
        EmberKeyStruct linkKey = ncp.getKey(EmberKeyType.EMBER_TRUST_CENTER_LINK_KEY);
        if (linkKey != null && partner.equals(linkKey.getPartnerEUI64())) {
            return linkKey.getBitmask().contains(EmberKeyStructBitmask.EMBER_KEY_IS_AUTHORIZED);
        }

        // Now check all other keys
        Integer keyTableSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_KEY_TABLE_SIZE);

        for (int cnt = 0; cnt < keyTableSize; cnt++) {
            EmberKeyStruct key = ncp.getKeyTableEntry(cnt);
            if (key == null || !partner.equals(key.getPartnerEUI64())) {
                continue;
            }
            return key.getBitmask().contains(EmberKeyStructBitmask.EMBER_KEY_IS_AUTHORIZED);
        }

        return false;
    }

    @Override
    public boolean addCertificate(ZigBeeCbkeCertificate certificate) {
        switch (certificate.getCryptoSuite()) {
            case ECC_163K1:
                return setPreinstalledCbke163k1Data(certificate) == EmberStatus.EMBER_SUCCESS;
            case ECC_283K1:
                return setPreinstalledCbke283k1Data(certificate) == EmberStatus.EMBER_SUCCESS;
            default:
                break;
        }
        return false;
    }

    @Override
    public Set<ZigBeeCryptoSuites> getSupportedCryptoSuites() {
        return getNcpCryptoSuites();
    }

    @Override
    public Set<ZigBeeCryptoSuites> getAvailableCryptoSuites() {
        return getNcpCryptoCertificates().keySet();
    }

    @Override
    public ByteArray getCertificate(ZigBeeCryptoSuites suite) {
        return getNcpCryptoCertificates().get(suite);
    }

    @Override
    public ByteArray getCbkeEphemeralData(ZigBeeCryptoSuites suite) {
        switch (suite) {
            case ECC_163K1:
                EzspGenerateCbkeKeysHandler response163k1 = ezspGenerateCbke163k1Keys();
                if (response163k1 == null || response163k1.getStatus() != EmberStatus.EMBER_SUCCESS) {
                    break;
                }
                return new ByteArray(response163k1.getEphemeralPublicKey().getContents());
            case ECC_283K1:
                EzspGenerateCbkeKeys283k1Handler response283k1 = ezspGenerateCbke283k1Keys();
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
    public boolean addPartnerCertificate(ZigBeeCryptoSuites suite, ByteArray partnerCertificate) {
        this.partnerCertificate = partnerCertificate;
        return true;
    }

    @Override
    public boolean addPartnerEphemeralData(ZigBeeCryptoSuites suite, ByteArray partnerEphemeralData) {
        this.partnerEphemeralData = partnerEphemeralData;
        return true;
    }

    @Override
    public ByteArray getInitiatorMac(ZigBeeCryptoSuites suite) {
        switch (suite) {
            case ECC_163K1:
                EzspCalculateSmacsHandler response163k1 = ezspCalculateSmacs163k1();
                if (response163k1 == null || response163k1.getStatus() != EmberStatus.EMBER_SUCCESS) {
                    break;
                }

                responderMac = new ByteArray(response163k1.getResponderSmac().getContents());
                return new ByteArray(response163k1.getInitiatorSmac().getContents());
            case ECC_283K1:
                EzspCalculateSmacs283k1Handler response283k1 = ezspCalculateSmacs283k1();
                if (response283k1 == null || response283k1.getStatus() != EmberStatus.EMBER_SUCCESS) {
                    break;
                }

                responderMac = new ByteArray(response283k1.getResponderSmac().getContents());
                return new ByteArray(response283k1.getInitiatorSmac().getContents());
            default:
                break;
        }

        return null;
    }

    @Override
    public ByteArray getResponderMac(ZigBeeCryptoSuites suite) {
        return responderMac;
    }

    @Override
    public boolean completeKeyExchange(ZigBeeCryptoSuites suite, boolean success) {
        switch (suite) {
            case ECC_163K1:
                EzspClearTemporaryDataMaybeStoreLinkKeyResponse response163k1 = ezspClearTemporaryDataMaybeStoreLinkKey163k1(
                        success);
                return (response163k1.getStatus() == EmberStatus.EMBER_SUCCESS);
            case ECC_283K1:
                EzspClearTemporaryDataMaybeStoreLinkKey283k1Response response283k1 = ezspClearTemporaryDataMaybeStoreLinkKey283k1(
                        success);
                return (response283k1.getStatus() == EmberStatus.EMBER_SUCCESS);
            default:
                return false;
        }
    }

    @Override
    public int getEphemeralDataGenerateTime() {
        return EPHEMERAL_DATA_GENERATE_TIME;
    }

    @Override
    public int getConfirmKeyGenerateTime() {
        return CONFIRM_KEY_GENERATE_TIME;
    }

    /**
     * Sets the device's CA public key, local certificate, and static private key on the NCP associated with this node.
     * <p>
     * This function allows a one-time write of the MFG token if it has not already been set. It does NOT utilize the
     * ::EMBER_CERTIFICATE_TABLE_SIZE so that should remain set at 0. Attempts to write the certificate that has already
     * been written will return a result of ::EMBER_ERR_FLASH_WRITE_INHIBITED. If the EUI64 in the certificate is the
     * same as the current EUI of the device then this function may be called while the stack is up. If the EUI in the
     * certificate is different than the current value, this function may only be called when the network is in a state
     * of ::EMBER_NO_NETWORK. Attempts to do otherwise will result in a return value of ::EMBER_INVALID_CALL. If the EUI
     * in the certificate is different than the current value this function will also write the Custom EUI64 MFG token.
     * If that token has already been written the operation will fail and return a result of ::EMBER_BAD_ARGUMENT. If
     * all the above criteria is met the token will be written and ::EMBER_SUCCESS will be returned.
     *
     * @param certificate the {@link ZigBeeCbkeCertificate} to set
     * @return the {@link EmberStatus} with success or failure
     */
    private EmberStatus setPreinstalledCbke163k1Data(ZigBeeCbkeCertificate certificate) {
        EmberPrivateKeyData myKey = new EmberPrivateKeyData();
        myKey.setContents(certificate.getPrivateKey());
        EmberCertificateData myCert = new EmberCertificateData();
        myCert.setContents(certificate.getCertificate());
        EmberPublicKeyData caCert = new EmberPublicKeyData();
        caCert.setContents(certificate.getCaPublicKey());

        EzspProtocolHandler protocolHandler = dongle.getProtocolHandler();
        EzspSetPreinstalledCbkeDataRequest request = new EzspSetPreinstalledCbkeDataRequest();
        request.setMyKey(myKey);
        request.setMyCert(myCert);
        request.setCaCert(caCert);
        EzspTransaction<EzspSetPreinstalledCbkeDataResponse> transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction<>(request, EzspSetPreinstalledCbkeDataResponse.class));
        EzspSetPreinstalledCbkeDataResponse response = transaction.getResponse();
        logger.debug(response.toString());

        return response.getStatus();
    }

    /**
     * Sets the device's 283k1 curve CA public key, local certificate, and static private key on the NCP associated with
     * this node.
     * <p>
     * This function allows a one-time write of the MFG token if it has not already been set. It does NOT utilise the
     * ::EMBER_CERTIFICATE_TABLE_SIZE so that should remain set at 0. Attempts to write the certificate that has already
     * been written will return a result of ::EMBER_ERR_FLASH_WRITE_INHIBITED. If the EUI64 in the certificate is the
     * same as the current EUI of the device then this function may be called while the stack is up. If the EUI in the
     * certificate is different than the current value, this function may only be called when the network is in a state
     * of ::EMBER_NO_NETWORK. Attempts to do otherwise will result in a return value of ::EMBER_INVALID_CALL. If the EUI
     * in the certificate is different than the current value this function will also write the Custom EUI64 MFG token.
     * If that token has already been written the operation will fail and return a result of ::EMBER_BAD_ARGUMENT. If
     * all the above criteria is met the token will be written and ::EMBER_SUCCESS will be returned.
     *
     * @param certificate the {@link ZigBeeCbkeCertificate} to set
     * @return the {@link EmberStatus} with success or failure
     */
    private EmberStatus setPreinstalledCbke283k1Data(ZigBeeCbkeCertificate certificate) {
        EmberPrivateKey283k1Data myKey = new EmberPrivateKey283k1Data();
        myKey.setContents(certificate.getPrivateKey());
        EmberCertificate283k1Data myCert = new EmberCertificate283k1Data();
        myCert.setContents(certificate.getCertificate());
        EmberPublicKey283k1Data caCert = new EmberPublicKey283k1Data();
        caCert.setContents(certificate.getCaPublicKey());

        EzspProtocolHandler protocolHandler = dongle.getProtocolHandler();

        EzspSetValueRequest setMyCert = new EzspSetValueRequest();
        setMyCert.setValueId(EzspValueId.EZSP_VALUE_CERTIFICATE_283K1);
        setMyCert.setValue(certificate.getCertificate());
        EzspTransaction<EzspSetValueResponse> transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction<>(setMyCert, EzspSetValueResponse.class));
        EzspSetValueResponse response = transaction.getResponse();

        EzspSetValueRequest setCaKey = new EzspSetValueRequest();
        setCaKey.setValueId(EzspValueId.EZSP_VALUE_PUBLIC_KEY_283K1);
        setCaKey.setValue(certificate.getCaPublicKey());
        transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction<>(setCaKey, EzspSetValueResponse.class));
        response = transaction.getResponse();

        EzspSetValueRequest setMyKey = new EzspSetValueRequest();
        setMyKey.setValueId(EzspValueId.EZSP_VALUE_PRIVATE_KEY_283K1);
        setMyKey.setValue(certificate.getPrivateKey());
        transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction<>(setMyKey, EzspSetValueResponse.class));
        response = transaction.getResponse();

        EzspSetPreinstalledCbkeData283k1Request request = new EzspSetPreinstalledCbkeData283k1Request();
        EzspTransaction<EzspSetPreinstalledCbkeData283k1Response> t2 = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction<>(request, EzspSetPreinstalledCbkeData283k1Response.class));
        EzspSetPreinstalledCbkeData283k1Response setCbkeResponse = t2.getResponse();
        logger.debug(response.toString());

        return setCbkeResponse.getStatus();
    }

    /**
     * This call starts the generation of the ECC Ephemeral Public/Private key pair. When complete it stores the private
     * key. The results are returned via ezspGenerateCbkeKeysHandler().
     *
     * @return the {@link EzspGenerateCbkeKeysHandler}
     */
    private EzspGenerateCbkeKeysHandler ezspGenerateCbke163k1Keys() {
        EmberNcp ncp = dongle.getEmberNcp();
        ncp.getCertificateData();
        ncp.getKey(EmberKeyType.EMBER_TRUST_CENTER_LINK_KEY);
        EzspProtocolHandler protocolHandler = dongle.getProtocolHandler();
        EzspGenerateCbkeKeysRequest request = new EzspGenerateCbkeKeysRequest();
        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspGenerateCbkeKeysResponse.class));
        EzspGenerateCbkeKeysResponse response = (EzspGenerateCbkeKeysResponse) transaction.getResponse();
        logger.debug("ezspGenerateCbke163k1Keys response {}", response);
        if (response == null || response.getStatus() != EmberStatus.EMBER_OPERATION_IN_PROGRESS) {
            return null;
        }

        EzspGenerateCbkeKeysHandler callbackHandler = (EzspGenerateCbkeKeysHandler) protocolHandler
                .eventWait(EzspGenerateCbkeKeysHandler.class, EPHEMERAL_DATA_GENERATE_TIME * 1000);
        logger.debug("ezspGenerateCbke163k1Keys callbackHandler {}", callbackHandler);
        if (callbackHandler == null || callbackHandler.getStatus() != EmberStatus.EMBER_SUCCESS) {
            return null;
        }
        return callbackHandler;
    }

    /**
     * This call starts the generation of the ECC 283k1 curve Ephemeral Public/Private key pair. When complete it stores
     * the private key. The results are returned via ezspGenerateCbkeKeysHandler283k1().
     *
     * @return the {@link EzspGenerateCbkeKeys283k1Handler}
     */
    private EzspGenerateCbkeKeys283k1Handler ezspGenerateCbke283k1Keys() {
        EzspProtocolHandler protocolHandler = dongle.getProtocolHandler();
        EzspGenerateCbkeKeys283k1Request request = new EzspGenerateCbkeKeys283k1Request();
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspGenerateCbkeKeys283k1Response.class));
        EzspGenerateCbkeKeys283k1Response response = (EzspGenerateCbkeKeys283k1Response) transaction.getResponse();
        logger.debug("ezspGenerateCbke283k1Keys response {}", response);
        if (response == null || response.getStatus() != EmberStatus.EMBER_OPERATION_IN_PROGRESS) {
            return null;
        }

        EzspGenerateCbkeKeys283k1Handler callbackHandler = (EzspGenerateCbkeKeys283k1Handler) protocolHandler
                .eventWait(EzspGenerateCbkeKeys283k1Handler.class, EPHEMERAL_DATA_GENERATE_TIME * 1000);
        logger.debug("ezspGenerateCbke283k1Keys callbackHandler {}", callbackHandler);
        if (callbackHandler == null || callbackHandler.getStatus() != EmberStatus.EMBER_SUCCESS) {
            return null;
        }
        return callbackHandler;
    }

    /**
     * Calculates the SMAC verification keys for both the initiator and responder roles of CBKE using the passed
     * parameters and the stored public/private key pair previously generated with ezspGenerateKeysRetrieveCert(). It
     * also stores the unverified link key data in temporary storage on the NCP until the key establishment is complete.
     *
     * @return the {@link EzspCalculateSmacsHandler}
     */
    private EzspCalculateSmacsHandler ezspCalculateSmacs163k1() {
        EzspProtocolHandler protocolHandler = dongle.getProtocolHandler();
        EzspCalculateSmacsRequest request = new EzspCalculateSmacsRequest();
        request.setAmInitiator(true);

        EmberCertificateData certificateData = new EmberCertificateData();
        certificateData.setContents(partnerCertificate.getAsIntArray());
        request.setPartnerCertificate(certificateData);

        EmberPublicKeyData ephemeralData = new EmberPublicKeyData();
        ephemeralData.setContents(partnerEphemeralData.getAsIntArray());
        request.setPartnerEphemeralPublicKey(ephemeralData);

        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspCalculateSmacsResponse.class));
        EzspCalculateSmacsResponse response = (EzspCalculateSmacsResponse) transaction.getResponse();
        logger.debug(response.toString());
        logger.debug("ezspCalculateSmacs163k1 response {}", response);
        if (response.getStatus() != EmberStatus.EMBER_OPERATION_IN_PROGRESS) {
            return null;
        }

        EzspCalculateSmacsHandler callbackHandler = (EzspCalculateSmacsHandler) protocolHandler
                .eventWait(EzspCalculateSmacsHandler.class, CONFIRM_KEY_GENERATE_TIME * 1000);
        logger.debug("ezspCalculateSmacs163k1 callbackHandler {}", callbackHandler);
        if (callbackHandler == null || callbackHandler.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("ezspCalculateSmacs163k1 failed: {}", callbackHandler);
            return null;
        }
        return callbackHandler;
    }

    /**
     * Calculates the SMAC verification keys for both the initiator and responder roles of CBKE for the 283k1 ECC curve
     * using the passed parameters and the stored public/private key pair previously generated with
     * ezspGenerateKeysRetrieveCert283k1(). It also stores the unverified link key data in temporary storage on the NCP
     * until the key establishment is complete.
     *
     * @return the {@link EzspCalculateSmacs283k1Handler}
     */
    private EzspCalculateSmacs283k1Handler ezspCalculateSmacs283k1() {
        EzspProtocolHandler protocolHandler = dongle.getProtocolHandler();
        EzspCalculateSmacs283k1Request request = new EzspCalculateSmacs283k1Request();
        request.setAmInitiator(true);

        EmberCertificate283k1Data certificateData = new EmberCertificate283k1Data();
        certificateData.setContents(partnerCertificate.getAsIntArray());
        request.setPartnerCertificate(certificateData);

        EmberPublicKey283k1Data ephemeralData = new EmberPublicKey283k1Data();
        ephemeralData.setContents(partnerEphemeralData.getAsIntArray());
        request.setPartnerEphemeralPublicKey(ephemeralData);

        EzspTransaction transaction = protocolHandler
                .sendEzspTransaction(new EzspSingleResponseTransaction(request, EzspCalculateSmacs283k1Response.class));
        EzspCalculateSmacs283k1Response response = (EzspCalculateSmacs283k1Response) transaction.getResponse();
        logger.debug("ezspCalculateSmacs283k1 response {}", response);
        if (response.getStatus() != EmberStatus.EMBER_OPERATION_IN_PROGRESS) {
            return null;
        }

        EzspCalculateSmacs283k1Handler callbackHandler = (EzspCalculateSmacs283k1Handler) protocolHandler
                .eventWait(EzspCalculateSmacs283k1Handler.class, CONFIRM_KEY_GENERATE_TIME * 1000);
        logger.debug("ezspCalculateSmacs283k1 callbackHandler {}", callbackHandler);
        if (callbackHandler == null || callbackHandler.getStatus() != EmberStatus.EMBER_SUCCESS) {
            logger.debug("ezspCalculateSmacs283k1 failed: {}", callbackHandler);
            return null;
        }
        return callbackHandler;
    }

    /**
     * Clears the temporary data associated with CBKE and the key establishment, most notably the ephemeral
     * public/private key pair. If storeLinKey is true it moves the unverified link key stored in temporary storage into
     * the link key table. Otherwise it discards the key.
     *
     * @param storeLinkKey true if the link key should be used
     * @return the {@link EzspClearTemporaryDataMaybeStoreLinkKeyResponse}
     */
    private EzspClearTemporaryDataMaybeStoreLinkKeyResponse ezspClearTemporaryDataMaybeStoreLinkKey163k1(
            boolean storeLinkKey) {
        EzspProtocolHandler protocolHandler = dongle.getProtocolHandler();
        EzspClearTemporaryDataMaybeStoreLinkKeyRequest request = new EzspClearTemporaryDataMaybeStoreLinkKeyRequest();
        request.setStoreLinkKey(storeLinkKey);
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspClearTemporaryDataMaybeStoreLinkKeyResponse.class));
        EzspClearTemporaryDataMaybeStoreLinkKeyResponse response = (EzspClearTemporaryDataMaybeStoreLinkKeyResponse) transaction
                .getResponse();
        logger.debug("ezspClearTemporaryDataMaybeStoreLinkKey response {}", response);

        return response;
    }

    /**
     * Clears the temporary data associated with CBKE and the key establishment, most notably the ephemeral
     * public/private key pair. If storeLinKey is true it moves the unverified link key stored in temporary storage into
     * the link key table. Otherwise it discards the key.
     *
     * @param storeLinkKey true if the link key should be used
     * @return the {@link EzspClearTemporaryDataMaybeStoreLinkKeyResponse}
     */
    private EzspClearTemporaryDataMaybeStoreLinkKey283k1Response ezspClearTemporaryDataMaybeStoreLinkKey283k1(
            boolean storeLinkKey) {
        EzspProtocolHandler protocolHandler = dongle.getProtocolHandler();
        EzspClearTemporaryDataMaybeStoreLinkKey283k1Request request = new EzspClearTemporaryDataMaybeStoreLinkKey283k1Request();
        request.setStoreLinkKey(storeLinkKey);
        EzspTransaction transaction = protocolHandler.sendEzspTransaction(
                new EzspSingleResponseTransaction(request, EzspClearTemporaryDataMaybeStoreLinkKey283k1Response.class));
        EzspClearTemporaryDataMaybeStoreLinkKey283k1Response response = (EzspClearTemporaryDataMaybeStoreLinkKey283k1Response) transaction
                .getResponse();
        logger.debug("ezspClearTemporaryDataMaybeStoreLinkKey response {}", response);

        return response;
    }

    /**
     * Gets the set of crypto suits that the NCP can support - ie those that there is crypto library support. It doesn't
     * necessarily mean that there is a certificate available.
     *
     * @return Set of supported {@link ZigBeeCryptoSuites}
     */
    private Set<ZigBeeCryptoSuites> getNcpCryptoSuites() {
        if (supportedSuites != null) {
            return supportedSuites;
        }

        EmberNcp ncp = dongle.getEmberNcp();

        // Check that CBKE is supported in the Ember NCP
        if (ncp.getLibraryStatus(EmberLibraryId.EMBER_ECC_LIBRARY) != EmberLibraryStatus.EMBER_LIBRARY_PRESENT || ncp
                .getLibraryStatus(EmberLibraryId.EMBER_CBKE_CORE_LIBRARY) != EmberLibraryStatus.EMBER_LIBRARY_PRESENT) {

        }

        // Check which algorithms are supported
        Set<ZigBeeCryptoSuites> suites = new HashSet<>();
        if (ncp.getLibraryStatus(EmberLibraryId.EMBER_CBKE_LIBRARY) == EmberLibraryStatus.EMBER_LIBRARY_PRESENT) {
            suites.add(ZigBeeCryptoSuites.ECC_163K1);
        }
        if (ncp.getLibraryStatus(EmberLibraryId.EMBER_CBKE_LIBRARY_283K1) == EmberLibraryStatus.EMBER_LIBRARY_PRESENT) {
            suites.add(ZigBeeCryptoSuites.ECC_283K1);
        }
        supportedSuites = Collections.unmodifiableSet(suites);
        return supportedSuites;
    }

    /**
     * Gets the available crypto certificates if the NCP supports the crypto suit, and the certificate exists within the
     * NCP.
     *
     * @return Map of {@link ZigBeeCryptoSuites} and certificates
     */
    private Map<ZigBeeCryptoSuites, ByteArray> getNcpCryptoCertificates() {
        if (availableCertificates != null) {
            return availableCertificates;
        }

        EmberNcp ncp = dongle.getEmberNcp();
        Map<ZigBeeCryptoSuites, ByteArray> certificates = new HashMap<>();
        for (ZigBeeCryptoSuites suite : getNcpCryptoSuites()) {
            switch (suite) {
                case ECC_163K1:
                    EmberCertificateData certificate163k1 = ncp.getCertificateData();
                    if (certificate163k1 != null) {
                        certificates.put(suite, new ByteArray(certificate163k1.getContents()));
                    }
                    break;
                case ECC_283K1:
                    EmberCertificate283k1Data certificate283k1 = ncp.getCertificate283k1Data();
                    if (certificate283k1 != null) {
                        certificates.put(suite, new ByteArray(certificate283k1.getContents()));
                    }
                    break;
                default:
                    logger.debug("Unknown crypto suite {}", suite);
                    break;
            }
        }
        availableCertificates = Collections.unmodifiableMap(certificates);
        return availableCertificates;
    }
}
