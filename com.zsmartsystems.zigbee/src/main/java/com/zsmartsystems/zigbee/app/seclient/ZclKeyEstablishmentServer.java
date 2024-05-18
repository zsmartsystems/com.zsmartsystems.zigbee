/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeExecutors;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeExchange;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuite1Certificate;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclCommandListener;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentRequestCommand;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.KeyEstablishmentStatusEnum;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.KeyEstablishmentSuiteBitmap;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.TerminateKeyEstablishment;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Implements the Key Establishment Server
 *
 * @author Chris Jackson
 *
 */
public class ZclKeyEstablishmentServer implements ZclCommandListener {
    /**
     * Number of seconds to wait when there is already a key establishment running.
     */
    private static final int DELAY_NO_RESOURCES = 20;

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclKeyEstablishmentServer.class);

    /**
     * The {@link IeeeAddress} of the remote node. The client will check that the certificate is valid for this address
     */
    private final IeeeAddress ieeeAddress;

    /**
     * The KeyEstablishmentCluster used to communicate with the remote device
     */
    private ZclKeyEstablishmentCluster keCluster;

    /**
     * The {@link ZigBeeCbkeProvider} provides the security algorithms required for the Certificate Based Key Exchange.
     */
    private ZigBeeCbkeProvider cbkeProvider;

    /**
     * The {@link ZigBeeCbkeProvider} provides the security algorithms required for the Certificate Based Key Exchange.
     */
    private ZigBeeCbkeExchange cbkeExchange;

    /**
     * Used to manually define the crypto suite. May be set by the application to limit the crypto suite to a known
     * value rather than let the system choose the highest usable suite.
     */
    private ZigBeeCryptoSuites forceCryptoSuite;

    private ExecutorService executorService;
    private ScheduledExecutorService timerService;

    private ScheduledFuture<?> timer;

    /**
     * Stores the number of seconds the server will take to generate the confirm key message
     */
    private int confirmKeyGenerateTime;

    private Map<ZigBeeCryptoSuites, KeyEstablishmentSuiteBitmap> cryptoSuiteTranslation = new HashMap<>();

    private KeyEstablishmentState keyEstablishmentState = KeyEstablishmentState.UNINITIALISED;

    private Future<CommandResult> lastTransaction;

    protected enum KeyEstablishmentState {
        UNINITIALISED,
        CHECK_CURVES,
        INITIATE_REQUEST,
        EPHEMERAL_DATA_REQUEST,
        CONFIRM_KEY_REQUEST,
        COMPLETE,
        FAILED
    }

    /**
     * The period to wait (in seconds) before retrying the key exchange if there's an error.
     */
    private final static int DELAY_BEFORE_RETRY = 10;

    /**
     * Constructs a Key Establishment Server.
     *
     * @param ieeeAddress the {@link IeeeAddress} of the remote node
     * @param keCluster the {@link ZclKeyEstablishmentCluster} used for communicating with the remote device
     */
    ZclKeyEstablishmentServer(IeeeAddress ieeeAddress, ZclKeyEstablishmentCluster keCluster) {
        this.ieeeAddress = ieeeAddress;
        this.keCluster = keCluster;

        cryptoSuiteTranslation.put(ZigBeeCryptoSuites.ECC_163K1, KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1);
        cryptoSuiteTranslation.put(ZigBeeCryptoSuites.ECC_283K1, KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_2);

        executorService = ZigBeeExecutors.newSingleThreadScheduledExecutor("ZclKeyEstablishmentServer");
        timerService = ZigBeeExecutors.newScheduledThreadPool(1, "ZclKeyEstablishmentServerTimeout");

        keCluster.addCommandListener(this);
    }

    /**
     * Sets the {@link ZigBeeCbkeProvider} to be used for the crypto services
     */
    public void setCbkeProvider(ZigBeeCbkeProvider cbkeProvider) {
        this.cbkeProvider = cbkeProvider;

        cbkeProvider.getAvailableCryptoSuites();
    }

    public void shutdown() {
        keCluster.removeCommandListener(this);
    }

    /**
     * Set the crypto suite to a fixed value. May be set by the application to limit the crypto suite to a known value
     * rather than let the system choose the highest usable suite.
     * <p>
     * Cannot be called before the {@link ZigBeeCbkeProvider} is set via the
     * {@link #setCbkeProvider(ZigBeeCbkeProvider)} method.
     *
     * @param requestedCryptoSuite the {@link ZigBeeCryptoSuites} to use
     * @return true if the {@link ZigBeeCryptoSuites} was set
     */
    public boolean setCryptoSuite(ZigBeeCryptoSuites requestedCryptoSuite) {
        if (cbkeProvider == null || !cbkeProvider.getAvailableCryptoSuites().contains(requestedCryptoSuite)) {
            logger.debug("{}: CBKE Key Establishment Server: Failed to set crypto suite to unsupported value {} {}",
                    ieeeAddress, requestedCryptoSuite,
                    cbkeProvider == null ? "[]" : cbkeProvider.getAvailableCryptoSuites());
            return false;
        }

        forceCryptoSuite = requestedCryptoSuite;
        setServerCryptoSuite(cryptoSuiteTranslation.get(forceCryptoSuite).getKey());
        return true;
    }

    private void setServerCryptoSuite(int suite) {
        ZclAttribute attribute = keCluster
                .getLocalAttribute(ZclKeyEstablishmentCluster.ATTR_SERVERKEYESTABLISHMENTSUITE);
        if (attribute == null) {
            logger.debug("{}: CBKE Key Establishment Server: Failed to get local server attribute", ieeeAddress);
            return;
        }
        attribute.setValue(suite);
    }

    /**
     * Gets the {@link ZigBeeCryptoSuites} being used by the CBKE procedure
     *
     * @return the {@link ZigBeeCryptoSuites} in use. If CBKE has not started, this will return null.
     */
    public ZigBeeCryptoSuites getCryptoSuite() {
        if (cbkeExchange == null) {
            return null;
        }
        return cbkeExchange.getCryptoSuite();
    }

    class HandleInitiateKeyEstablishmentRequest implements Runnable {
        private final InitiateKeyEstablishmentRequestCommand request;

        HandleInitiateKeyEstablishmentRequest(InitiateKeyEstablishmentRequestCommand request) {
            this.request = request;
        }

        @Override
        public void run() {
            logger.debug("{}: CBKE Key Establishment Server: handleInitiateKeyEstablishmentRequest {}", ieeeAddress,
                    request);

            // If the device does not currently have the resources to respond to a key establishment request it shall
            // send a Terminate Key Establishment command with the result value set to NO_RESOURCES and the Wait Time
            // field shall be set to an approximation of the time that must pass before the device will have the
            // resources to process a new Key Establishment Request.
            if (cbkeProvider == null || keyEstablishmentState != KeyEstablishmentState.UNINITIALISED) {
                if (cbkeProvider == null) {
                    logger.debug("{}: CBKE Key Establishment Server: Initiate Key Establishment with no key provider",
                            ieeeAddress);
                } else {
                    logger.debug("{}: CBKE Key Establishment Server: Initiate Key Establishment Invalid state {}",
                            ieeeAddress, keyEstablishmentState);
                }
                keCluster.sendCommand(new TerminateKeyEstablishment(
                        KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(),
                        DELAY_NO_RESOURCES,
                        request.getKeyEstablishmentSuite()));
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
                return;
            }

            cbkeExchange = cbkeProvider.getCbkeKeyExchangeResponder();
            if (cbkeExchange == null) {
                logger.debug("{}: CBKE Key Establishment Server: Unable to get CBKE Exchange", ieeeAddress);
                keCluster.sendCommand(new TerminateKeyEstablishment(
                        KeyEstablishmentStatusEnum.NO_RESOURCES.getKey(),
                        DELAY_NO_RESOURCES,
                        request.getKeyEstablishmentSuite()));
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
                return;
            }

            ZigBeeCertificate localCertificate = null;
            ZigBeeCertificate remoteCertificate = null;

            try {
                switch (request.getKeyEstablishmentSuite()) {
                    case 1:
                        cbkeExchange.setCryptoSuite(ZigBeeCryptoSuites.ECC_163K1);
                        if (request.getIdentity().size() < ZigBeeCryptoSuite1Certificate.CERTIFICATE_LENGTH) {
                            keCluster.sendCommand(new TerminateKeyEstablishment(
                                    KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(),
                                    DELAY_BEFORE_RETRY,
                                    KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey()));
                            setState(KeyEstablishmentState.FAILED);
                            stopCbke();
                            return;
                        }
                        localCertificate = new ZigBeeCryptoSuite1Certificate(cbkeExchange.getCertificate());
                        remoteCertificate = new ZigBeeCryptoSuite1Certificate(new ByteArray(request.getIdentity()
                                .getAsIntArray(0, ZigBeeCryptoSuite1Certificate.CERTIFICATE_LENGTH)));
                        break;
                    case 2:
                        cbkeExchange.setCryptoSuite(ZigBeeCryptoSuites.ECC_283K1);
                        break;
                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                logger.debug("{}: CBKE Key Establishment Server: Certificates invalid: {}", ieeeAddress,
                        e.getMessage());
                keCluster.sendCommand(new TerminateKeyEstablishment(
                        KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(),
                        DELAY_BEFORE_RETRY,
                        KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey()));
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
                return;
            }

            logger.debug("{}: CBKE Key Establishment Server: Local  Certificate is {}", ieeeAddress, localCertificate);
            logger.debug("{}: CBKE Key Establishment Server: Remote Certificate is {}", ieeeAddress, remoteCertificate);

            // If the device can process this request, it shall check the Issuer field of the device's implicit
            // certificate. If the Issuer field does not contain a value that corresponds to a known Certificate
            // Authority, the device shall send a Terminate Key Establishment command with the result set to
            // UNKNOWN_ISSUER.
            if (!Objects.equals(localCertificate.getIssuer(), remoteCertificate.getIssuer())) {
                logger.debug("{}: CBKE Key Establishment Server: Issuer is not known - expected={}, received={}",
                        ieeeAddress, localCertificate.getIssuer(), remoteCertificate.getIssuer());
                keCluster.sendCommand(new TerminateKeyEstablishment(
                        KeyEstablishmentStatusEnum.UNKNOWN_ISSUER.getKey(),
                        DELAY_BEFORE_RETRY,
                        KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey()));
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
                return;
            }

            // The device should verify the certificate belongs to the address that the device is communicating with.
            if (!remoteCertificate.getSubject().equals(ieeeAddress)) {
                logger.debug(
                        "{}: CBKE Key Establishment Server: Certificate is not for this address - expected={}, received={}",
                        ieeeAddress, ieeeAddress, remoteCertificate.getSubject());
                keCluster.sendCommand(new TerminateKeyEstablishment(
                        KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(),
                        DELAY_BEFORE_RETRY,
                        KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey()));
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
                return;
            }

            // If the device accepts the request it shall send an Initiate Key Establishment Response command containing
            // its own identity information. The device should verify the certificate belongs to the address that the
            // device is communicating with. The binding between the identity of the communicating device and its
            // address is verifiable using out-of-band method.
            int requestedSuite = request.getKeyEstablishmentSuite();
            cbkeExchange.addPartnerCertificate(request.getIdentity());

            setState(KeyEstablishmentState.INITIATE_REQUEST);
            lastTransaction = keCluster.sendCommand(new InitiateKeyEstablishmentResponse(
                    requestedSuite,
                    cbkeProvider.getEphemeralDataGenerateTime(),
                    cbkeProvider.getConfirmKeyGenerateTime(),
                    localCertificate.getByteArray()));
            startTerminationTimer(request.getEphemeralDataGenerateTime());
            confirmKeyGenerateTime = request.getConfirmKeyGenerateTime();
        }
    }

    class HandleEphemeralDataRequest implements Runnable {
        private final EphemeralDataRequestCommand request;

        HandleEphemeralDataRequest(EphemeralDataRequestCommand request) {
            this.request = request;
        }

        @Override
        public void run() {
            logger.debug("{}: CBKE Key Establishment Server: handleEphemeralDataRequest {}", ieeeAddress, request);
            stopTerminationTimer();
            completeLastTransaction();

            // If the device is not currently in the middle of negotiating Key Establishment with the sending device
            // when it receives this message, it shall send back a Terminate Key Establishment message with a result of
            // BAD_MESSAGE.
            // If the device is in the middle of Key Establishment with the sender but did not receive this message in
            // response to an Initiate Key Establishment Response command, it shall send back a Terminate Key
            // Establishment message with a result of BAD_MESSAGE.
            if (keyEstablishmentState != KeyEstablishmentState.INITIATE_REQUEST) {
                logger.debug("{}: CBKE Key Establishment Server: Ephemeral Data Request Invalid state {}", ieeeAddress,
                        keyEstablishmentState);
                keCluster.sendCommand(new TerminateKeyEstablishment(
                        KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(),
                        10,
                        1));
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
                return;
            }

            // If the device can process the request it shall respond by generating its own ephemeral data and sending
            // an Ephemeral Data Response command containing that value.
            cbkeExchange.addPartnerEphemeralData(request.getEphemeralData());

            setState(KeyEstablishmentState.CONFIRM_KEY_REQUEST);
            lastTransaction = keCluster.sendCommand(new EphemeralDataResponse(cbkeExchange.getCbkeEphemeralData()));
            startTerminationTimer(confirmKeyGenerateTime);
        }
    }

    class HandleConfirmKeyRequest implements Runnable {
        private final ConfirmKeyDataRequestCommand request;

        HandleConfirmKeyRequest(ConfirmKeyDataRequestCommand request) {
            this.request = request;
        }

        @Override
        public void run() {
            logger.debug("{}: CBKE Key Establishment Server: handleConfirmKeyRequest {}", ieeeAddress, request);
            stopTerminationTimer();
            completeLastTransaction();

            // If the device is not currently in the middle of negotiating Key Establishment with the sending device
            // when it receives this message, it shall send back a Terminate Key Establishment message with a result of
            // BAD_MESSAGE.
            // If the device is in the middle of Key Establishment with the sender but did not receive this message in
            // response to an Ephemeral Data Response command, it shall send back a Terminate Key Establishment message
            // with a result of BAD_MESSAGE.
            if (keyEstablishmentState != KeyEstablishmentState.CONFIRM_KEY_REQUEST) {
                logger.debug("{}: CBKE Key Establishment Server: Confirm Key Invalid state {}", ieeeAddress,
                        keyEstablishmentState);
                keCluster.sendCommand(new TerminateKeyEstablishment(
                        KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(),
                        DELAY_BEFORE_RETRY,
                        1));
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
                return;
            }

            // On receipt of the Confirm Key Request command the responder device shall compare the received MACU value
            // with its own reconstructed version of MACU. If the two match the responder shall send back MACV by
            // generating an appropriate Confirm Key Response command. If the two do not match, the responder shall send
            // back a Terminate Key Establishment with a result of BAD KEY_CONFIRM and terminate the key establishment.
            ByteArray secureMessageAuthenticationCode = request.getSecureMessageAuthenticationCode();
            ByteArray remoteMac = cbkeExchange.getInitiatorMac();
            ByteArray localMac = cbkeExchange.getResponderMac();
            boolean success = secureMessageAuthenticationCode.equals(localMac);
            if (!success) {
                logger.debug("{}: CBKE Key Establishment Server: Confirm Key Invalid SMAC = expected {}, received {}",
                        ieeeAddress, localMac, secureMessageAuthenticationCode);
                keCluster.sendCommand(new TerminateKeyEstablishment(
                        KeyEstablishmentStatusEnum.BAD_KEY_CONFIRM.getKey(),
                        DELAY_BEFORE_RETRY,
                        1));
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
                return;
            }
            lastTransaction = keCluster.sendCommand(new ConfirmKeyResponse(remoteMac));

            setState(KeyEstablishmentState.COMPLETE);
            stopCbke();

            // Send the default response since we don't handle this in the main handler
            keCluster.sendDefaultResponse(request, ZclStatus.SUCCESS);
        }
    }

    class HandleTerminateKeyEstablishment implements Runnable {
        private final TerminateKeyEstablishment request;

        HandleTerminateKeyEstablishment(TerminateKeyEstablishment request) {
            this.request = request;
        }

        @Override
        public void run() {
            logger.debug("{}: CBKE Key Establishment Server: handleTerminateKeyEstablishment {}", ieeeAddress, request);
            stopTerminationTimer();
            completeLastTransaction();

            setState(KeyEstablishmentState.FAILED);
            stopCbke();

            // Send the default response since we don't handle this in the main handler
            keCluster.sendDefaultResponse(request, ZclStatus.SUCCESS);
        }
    }

    private void setState(KeyEstablishmentState newState) {
        logger.debug("{}: CBKE Key Establishment Server: State updated from {} to {}", ieeeAddress,
                keyEstablishmentState, newState);
        keyEstablishmentState = newState;
    }

    private void stopCbke() {
        logger.debug("{}: CBKE Key Establishment Server: Terminated", ieeeAddress);
        stopTerminationTimer();

        // Always call completeKeyExchange so that the system can release the lock
        cbkeExchange.completeKeyExchange(keyEstablishmentState == KeyEstablishmentState.COMPLETE);
        cbkeExchange = null;

        setState(KeyEstablishmentState.UNINITIALISED);
    }

    private void completeLastTransaction() {
        if (lastTransaction == null) {
            return;
        }
        lastTransaction.cancel(true);
        lastTransaction = null;
    }

    /**
     * Starts a timer, after which the CBKE will be terminated
     *
     * @param delay the number of seconds to wait
     */
    private void startTerminationTimer(int delay) {
        stopTerminationTimer();
        logger.debug("{}: CBKE Key Establishment Server: Timer started for {} seconds at {}", ieeeAddress, delay,
                keyEstablishmentState);

        timer = timerService.schedule(new Runnable() {
            @Override
            public void run() {
                timer = null;
                logger.debug("{}: CBKE Key Establishment Server: Timeout waiting for message in state {}", ieeeAddress,
                        keyEstablishmentState);
                // Note that no TerminateKeyEstablishment message should be sent.
                setState(KeyEstablishmentState.FAILED);
                stopCbke();
            }
        }, delay, TimeUnit.SECONDS);
    }

    /**
     * Stops a currently running termination timer.
     */
    private void stopTerminationTimer() {
        if (timer != null) {
            logger.debug("{}: CBKE Key Establishment Server: Timer stopped", ieeeAddress);
            timer.cancel(true);
            timer = null;
        }
    }

    @Override
    public boolean commandReceived(ZclCommand command) {
        logger.debug("{}: CBKE Key Establishment Server: Received {}", ieeeAddress, command);
        Runnable handler = null;

        if (command.getCommandDirection() != ZclCommandDirection.CLIENT_TO_SERVER) {
            return false;
        }

        // Process the response
        if (command instanceof InitiateKeyEstablishmentRequestCommand) {
            handler = new HandleInitiateKeyEstablishmentRequest((InitiateKeyEstablishmentRequestCommand) command);
        }
        if (command instanceof EphemeralDataRequestCommand) {
            handler = new HandleEphemeralDataRequest((EphemeralDataRequestCommand) command);
        }
        if (command instanceof ConfirmKeyDataRequestCommand) {
            handler = new HandleConfirmKeyRequest((ConfirmKeyDataRequestCommand) command);
        }
        if (command instanceof TerminateKeyEstablishment) {
            handler = new HandleTerminateKeyEstablishment((TerminateKeyEstablishment) command);
        }

        if (handler != null) {
            executorService.submit(handler);
            return true;
        }

        return false;
    }

}
