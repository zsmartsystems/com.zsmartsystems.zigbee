/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeExecutors;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCertificate;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuite1Certificate;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclCommandListener;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.ConfirmKeyResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.EphemeralDataResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.InitiateKeyEstablishmentResponse;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.KeyEstablishmentStatusEnum;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.KeyEstablishmentSuiteBitmap;
import com.zsmartsystems.zigbee.zcl.clusters.keyestablishment.TerminateKeyEstablishment;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Implements the key establishment client.
 * <p>
 * If the device does not need to perform discovery queries or other non-secure operations after
 * it joins an SE network and receives the Network Key, it should immediately initiate Key
 * Establishment with the Trust Center to obtain a new Trust Center Link Key.
 * <p>
 * If Key Establishment fails with a result of UNKNOWN_ISSUER the device shall leave the
 * network. A device that does not initiate Key Establishment with the Trust Center within a
 * reasonable period of time MAY be told to leave depending on the network operator’s policy. A
 * maximum period of 20 minutes is recommended.
 *
 * @author Chris Jackson
 *
 */
public class ZclKeyEstablishmentClient implements ZclCommandListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZclKeyEstablishmentClient.class);

    /**
     * Number of seconds from initial request to first response from server
     */
    private final int STARTUP_TIMER = 5;

    /**
     * The KeyEstablishmentCluster used to communicate with the remote device
     */
    private ZclKeyEstablishmentCluster keCluster;

    /**
     * The crypto suite that we are using for key exchange.
     */
    private ZigBeeCryptoSuites cryptoSuite;

    /**
     * Used to fix the crypto suite. May be set by the application to limit the crypto suite to a known value rather
     * than let the system choose the highest usable suite.
     */
    private ZigBeeCryptoSuites forceCryptoSuite;

    /**
     * A Bitmap of the available encryption suites on the remote device
     */
    private Integer availableSuites;

    /**
     * The {@link ZigBeeCbkeProvider} provides the security algorithms required for the Certificate Based Key Exchange.
     */
    private ZigBeeCbkeProvider cbkeProvider;

    /**
     * The current state of the key establishment process. We need to ensure that things happen in the correct order!
     */
    private KeyEstablishmentState state = KeyEstablishmentState.UNINITIALISED;

    /**
     * The {@link SmartEnergyClient} used for notifications
     */
    private SmartEnergyClient smartEnergyClient;

    private ExecutorService executorService;
    private ScheduledExecutorService timerService;

    private ScheduledFuture<?> timer;

    /**
     * Stores the number of seconds the server will take to generate the confirm key message
     */
    private int confirmKeyGenerateTime;

    private Map<ZigBeeCryptoSuites, KeyEstablishmentSuiteBitmap> cryptoSuiteTranslation = new HashMap<>();

    private enum KeyEstablishmentState {
        /**
         * Key estblishment has not started
         */
        UNINITIALISED,
        /**
         * Currently checking the remote server for the list of supported curves
         */
        CHECK_CURVES,
        /**
         * We've sent the initiate request, and are waiting for the response
         */
        INITIATE_REQUEST,
        /**
         * We've sent the ephemeral data request, and are waiting for the response
         */
        EPHEMERAL_DATA_REQUEST,
        /**
         * We've sent the confirm key request, and are waiting for the response
         */
        CONFIRM_KEY_REQUEST,
        /**
         * Key establishment completed successfully
         */
        COMPLETE,
        /**
         * Key establishment failed but can be retried after the required delay
         */
        FAILED,
        /**
         * Key establishment failed and cannot be retried. The application should now leave the network
         */
        FATAL
    }

    /**
     * The period to wait (in seconds) before retrying the key exchange if there's an error.
     */
    private final static int DELAY_BEFORE_RETRY = 10;

    ZclKeyEstablishmentClient(SmartEnergyClient smartEnergyClient, ZclKeyEstablishmentCluster keCluster) {
        this.smartEnergyClient = smartEnergyClient;
        this.keCluster = keCluster;

        cryptoSuiteTranslation.put(ZigBeeCryptoSuites.ECC_163K1, KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1);
        cryptoSuiteTranslation.put(ZigBeeCryptoSuites.ECC_283K1, KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_2);

        keCluster.addCommandListener(this);

        timerService = ZigBeeExecutors.newScheduledThreadPool(1, "ZclKeyEstablishmentClient-Timeout");
        executorService = ZigBeeExecutors.newSingleThreadScheduledExecutor("ZclKeyEstablishmentClient");
    }

    /**
     * Sets the {@link ZigBeeCbkeProvider} to be used for the crypto services
     */
    public void setCbkeProvider(ZigBeeCbkeProvider cbkeProvider) {
        this.cbkeProvider = cbkeProvider;
    }

    /**
     * Set the crypto suite to a fixed value. May be set by the application to limit the crypto suite to a known value
     * rather than let the system choose the highest usable suite.
     * <p>
     * Once the crypto suite has been manually selected the system will not request the supported suites on the remote
     * device. If the remote device does not support the manually selected crypto suite, then the CBKE will fail.
     *
     * @param requestedCryptoSuite the {@link ZigBeeCryptoSuites} to use
     * @return true if the {@link ZigBeeCryptoSuites} was set
     */
    public boolean setCryptoSuite(ZigBeeCryptoSuites requestedCryptoSuite) {
        if (!cbkeProvider.getSupportedCryptoSuites().contains(cryptoSuite)) {
            logger.debug("CBKE Key establishment client: Failed to set crypto suite to unsupported value {}",
                    requestedCryptoSuite);
            return false;
        }

        forceCryptoSuite = requestedCryptoSuite;
        return true;
    }

    /**
     * Gets the {@link ZigBeeCryptoSuites} being used by the CBKE procedure.
     *
     * @return the {@link ZigBeeCryptoSuites} in use. If CBKE has not started, this will return null.
     */
    public ZigBeeCryptoSuites getCryptoSuite() {
        return cryptoSuite;
    }

    /**
     * Starts the CBKE procedure
     *
     * @return true if the CBKE was started successfully
     */
    public boolean start() {
        if (cbkeProvider == null) {
            logger.debug("CBKE Key establishment client: Initiate key establishment failed - cbkeProvider is null");

            return false;
        }

        if (state != KeyEstablishmentState.UNINITIALISED) {
            logger.debug("CBKE Key establishment client: Initiate key establishment failed - state is {}", state);

            return false;
        }

        logger.debug("CBKE Key establishment client: Initiate key establishment");

        ZigBeeCryptoSuites requestedCryptoSuite;
        if (forceCryptoSuite != null) {
            requestedCryptoSuite = forceCryptoSuite;
            logger.debug("CBKE Key establishment client: Forced suite {}", requestedCryptoSuite);
        } else {
            setState(KeyEstablishmentState.CHECK_CURVES);
            ZclAttribute keSuitesAttr = keCluster
                    .getAttribute(ZclKeyEstablishmentCluster.ATTR_SERVERKEYESTABLISHMENTSUITE);
            availableSuites = (Integer) keSuitesAttr.readValue(Long.MAX_VALUE);

            List<ZigBeeCryptoSuites> suites = new ArrayList<>();
            if ((availableSuites & 0x0001) != 0) {
                suites.add(ZigBeeCryptoSuites.ECC_163K1);
            }
            if ((availableSuites & 0x0002) != 0) {
                suites.add(ZigBeeCryptoSuites.ECC_283K1);
            }
            logger.debug("CBKE Key establishment client: Remote supports suites {}", suites);

            // Find a matching suite - using the highest possible security
            if (forceCryptoSuite != null) {
                requestedCryptoSuite = forceCryptoSuite;
            } else if (cbkeProvider.getAvailableCryptoSuites().contains(ZigBeeCryptoSuites.ECC_283K1)
                    && suites.contains(ZigBeeCryptoSuites.ECC_283K1)) {
                requestedCryptoSuite = ZigBeeCryptoSuites.ECC_283K1;
            } else if (cbkeProvider.getAvailableCryptoSuites().contains(ZigBeeCryptoSuites.ECC_163K1)
                    && suites.contains(ZigBeeCryptoSuites.ECC_163K1)) {
                requestedCryptoSuite = ZigBeeCryptoSuites.ECC_163K1;
            } else {
                logger.error("CBKE Key establishment client: Unable to find compatible security suite.");
                requestedCryptoSuite = ZigBeeCryptoSuites.ECC_163K1;

                return false;
            }
            logger.debug("CBKE Key establishment client: Selected suite {}", requestedCryptoSuite);
        }

        setState(KeyEstablishmentState.INITIATE_REQUEST);
        ByteArray certificate = cbkeProvider.getCertificate(requestedCryptoSuite);
        if (certificate == null) {
            logger.debug(
                    "CBKE Key establishment client: Initiate key establishment failed - no certificate returned from CBKE provider");

            setState(KeyEstablishmentState.FAILED);
            stopCbke(0);
            return false;
        }

        // Remember the crypto suite we request - the response must be the same
        cryptoSuite = requestedCryptoSuite;

        keCluster.initiateKeyEstablishmentRequestCommand(cryptoSuiteTranslation.get(requestedCryptoSuite).getKey(),
                cbkeProvider.getEphemeralDataGenerateTime(), cbkeProvider.getConfirmKeyGenerateTime(), certificate);
        startTerminationTimer(STARTUP_TIMER);

        return true;
    }

    /**
     * Stops the key establishment if it's in progress and frees any resources. Once shutdown, the CBKE cannot be
     * restarted.
     */
    public void shutdown() {
        logger.debug("CBKE Key establishment client: Shutdown key establishment at state {}", state);

        state = KeyEstablishmentState.UNINITIALISED;
        keCluster.removeCommandListener(this);

        timerService.shutdown();
        executorService.shutdown();
    }

    /**
     * Stops the CBKE if it's currently in progress.
     */
    public void stop() {
        logger.debug("CBKE Key establishment client: Key establishment stopped");
        stopTerminationTimer();
        setState(KeyEstablishmentState.UNINITIALISED);
    }

    private void setState(KeyEstablishmentState newState) {
        logger.debug("CBKE Key establishment client: state updated from {} to {}", state, newState);
        state = newState;
    }

    class HandleInitiateKeyEstablishmentResponse implements Runnable {
        private final InitiateKeyEstablishmentResponse response;

        HandleInitiateKeyEstablishmentResponse(InitiateKeyEstablishmentResponse response) {
            this.response = response;
        }

        @Override
        public void run() {
            logger.debug("CBKE Key establishment client: handleInitiateKeyEstablishmentResponse {}", response);
            stopTerminationTimer();

            // Check the state and terminate if we're not expecting this response
            if (state != KeyEstablishmentState.INITIATE_REQUEST) {
                logger.debug(
                        "CBKE Key establishment client: Invalid InitiateKeyEstablishmentResponse packet with state {}",
                        state);
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), DELAY_BEFORE_RETRY,
                        KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }

            // Only one crypto suite can be selected
            if (Integer.bitCount(response.getRequestedKeyEstablishmentSuite()) != 1) {
                logger.debug(
                        "CBKE Key establishment client: Invalid InitiateKeyEstablishmentResponse packet with multiple suites selected [{}]",
                        response.getRequestedKeyEstablishmentSuite());
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), DELAY_BEFORE_RETRY,
                        getPreferredCryptoSuite().getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }

            ZigBeeCryptoSuites requestedSuite = null;
            KeyEstablishmentSuiteBitmap keCryptoSuite = KeyEstablishmentSuiteBitmap
                    .getByValue(response.getRequestedKeyEstablishmentSuite());
            for (ZigBeeCryptoSuites suite : ZigBeeCryptoSuites.values()) {
                if (cryptoSuiteTranslation.get(suite) == keCryptoSuite) {
                    requestedSuite = suite;
                    break;
                }
            }

            if (requestedSuite != cryptoSuite) {
                logger.debug(
                        "CBKE Key establishment client: Requested crypto suite from remote {} is inconsistent with our request {}",
                        requestedSuite, cryptoSuite);
                keCluster.sendDefaultResponse(response, ZclStatus.FAILURE);
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }

            ZigBeeCertificate localCertificate = null;
            ZigBeeCertificate remoteCertificate = null;

            try {
                switch (cryptoSuite) {
                    case ECC_163K1:
                        localCertificate = new ZigBeeCryptoSuite1Certificate(
                                cbkeProvider.getCertificate(ZigBeeCryptoSuites.ECC_163K1));
                        remoteCertificate = new ZigBeeCryptoSuite1Certificate(response.getIdentity());
                        break;
                    case ECC_283K1:
                        break;
                    default:
                        break;
                }
            } catch (IllegalArgumentException e) {
                logger.debug("CBKE Key establishment client: Certificates invalid: {}", e.getMessage());
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), DELAY_BEFORE_RETRY,
                        KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }

            logger.debug("CBKE Key establishment client: Local  Certificate is {}", localCertificate);
            logger.debug("CBKE Key establishment client: Remote Certificate is {}", remoteCertificate);

            if (localCertificate == null || remoteCertificate == null) {
                logger.debug("CBKE Key establishment client: Certificates not found");
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.UNSUPPORTED_SUITE.getKey(),
                        DELAY_BEFORE_RETRY, KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
            } else if (!Objects.equals(localCertificate.getIssuer(), remoteCertificate.getIssuer())) {
                logger.debug("CBKE Key establishment client: Issuer is not known - expected={}, received={}",
                        localCertificate.getIssuer(), remoteCertificate.getIssuer());
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.UNKNOWN_ISSUER.getKey(),
                        DELAY_BEFORE_RETRY, KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }

            ByteArray ephemeralData = cbkeProvider.getCbkeEphemeralData(requestedSuite);

            // Make sure we have a certificate for the requested crypto suite
            if (ephemeralData == null) {
                logger.debug(
                        "CBKE Key establishment client: Unable to get ephemeral data for requested security suite {}",
                        requestedSuite);
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.UNSUPPORTED_SUITE.getKey(),
                        DELAY_BEFORE_RETRY, KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }
            logger.debug("CBKE Key establishment client: Sertificate for requested security suite {} is {}",
                    requestedSuite, ephemeralData);

            cbkeProvider.addPartnerCertificate(cryptoSuite, response.getIdentity());
            keCluster.ephemeralDataRequestCommand(ephemeralData);

            confirmKeyGenerateTime = response.getConfirmKeyGenerateTime();
            setState(KeyEstablishmentState.EPHEMERAL_DATA_REQUEST);
            startTerminationTimer(response.getEphemeralDataGenerateTime());
        }
    }

    class HandleEphemeralDataResponse implements Runnable {
        private final EphemeralDataResponse response;

        HandleEphemeralDataResponse(EphemeralDataResponse response) {
            this.response = response;
        }

        @Override
        public void run() {
            logger.debug("CBKE Key establishment client: handleEphemeralDataResponse {}", response);
            stopTerminationTimer();

            // Check the state and terminate if we're not expecting this response
            if (state != KeyEstablishmentState.EPHEMERAL_DATA_REQUEST) {
                logger.debug("CBKE Key establishment client: Invalid EphemeralDataResponse packet with state {}",
                        state);
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), DELAY_BEFORE_RETRY,
                        KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }

            cbkeProvider.addPartnerEphemeralData(cryptoSuite, response.getEphemeralData());

            ByteArray initiatorMac = cbkeProvider.getInitiatorMac(cryptoSuite);
            keCluster.confirmKeyDataRequestCommand(initiatorMac);

            setState(KeyEstablishmentState.CONFIRM_KEY_REQUEST);
            startTerminationTimer(confirmKeyGenerateTime);
        }
    }

    class HandleConfirmKeyResponse implements Runnable {
        private final ConfirmKeyResponse response;

        HandleConfirmKeyResponse(ConfirmKeyResponse response) {
            this.response = response;
        }

        @Override
        public void run() {
            logger.debug("CBKE Key establishment client: handleConfirmKeyResponse {}", response);
            stopTerminationTimer();

            // Check the state and terminate if we're not expecting this response
            if (state != KeyEstablishmentState.CONFIRM_KEY_REQUEST) {
                logger.debug("CBKE Invalid ConfirmKeyResponse packet with state {}", state);
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.BAD_MESSAGE.getKey(), DELAY_BEFORE_RETRY,
                        KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }

            ByteArray responseMac = cbkeProvider.getResponderMac(cryptoSuite);

            logger.debug("CBKE Key establishment client: Confirm key response our={} theirs={}", responseMac,
                    response.getSecureMessageAuthenticationCode());
            boolean success = responseMac.equals(response.getSecureMessageAuthenticationCode());

            if (!success) {
                // Failure
                logger.debug("CBKE Key establishment client: Key establishment failed - SMAC codes were not confirmed");
                keCluster.terminateKeyEstablishment(KeyEstablishmentStatusEnum.BAD_KEY_CONFIRM.getKey(),
                        DELAY_BEFORE_RETRY, KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1.getKey());
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
                return;
            }

            cbkeProvider.completeKeyExchange(cryptoSuite, success);
            setState(KeyEstablishmentState.COMPLETE);
            stopCbke(0);
        }
    }

    class HandleTerminateKeyEstablishment implements Runnable {
        private final TerminateKeyEstablishment response;

        HandleTerminateKeyEstablishment(TerminateKeyEstablishment response) {
            this.response = response;
        }

        @Override
        public void run() {
            logger.debug("CBKE Key establishment client: handleTerminateKeyEstablishment {}", response);
            stopTerminationTimer();

            Integer suite = response.getKeyEstablishmentSuite();
            KeyEstablishmentStatusEnum status = KeyEstablishmentStatusEnum.getByValue(response.getStatusCode());
            Integer waitTime = response.getWaitTime();
            if (response.getStatusCode() == KeyEstablishmentStatusEnum.UNKNOWN_ISSUER.getKey()) {
                // If UNSUPPORTED_SUITE is received then we should signal to leave the network
                setState(KeyEstablishmentState.FATAL);
            } else {
                setState(KeyEstablishmentState.FAILED);
            }
            logger.debug("CBKE Terminate Key establishment client: Terminate status={}, suite={}, wait={} seconds",
                    status, suite, waitTime);
            stopCbke(waitTime);
        }
    }

    /**
     * Starts a timer, after which the CBKE will be terminated
     *
     * @param delay the number of seconds to wait
     */
    private void startTerminationTimer(int delay) {
        stopTerminationTimer();
        logger.debug("CBKE Key establishment client: Timer started for {} seconds at {}", delay, state);

        timer = timerService.schedule(new Runnable() {
            @Override
            public void run() {
                timer = null;
                logger.debug("CBKE Key establishment client: Timeout waiting for message in state {}", state);
                // Note that no TerminateKeyEstablishment message should be sent.
                setState(KeyEstablishmentState.FAILED);
                stopCbke(0);
            }
        }, delay, TimeUnit.SECONDS);
    }

    /**
     * Stops a currently running termination timer.
     */
    private void stopTerminationTimer() {
        if (timer != null) {
            logger.debug("CBKE Key establishment client: Timer stopped");
            timer.cancel(true);
            timer = null;
        }
    }

    @Override
    public boolean commandReceived(ZclCommand command) {
        Runnable handler = null;

        if (command.getCommandDirection() != ZclCommandDirection.SERVER_TO_CLIENT) {
            return false;
        }

        // Process the response
        if (command instanceof InitiateKeyEstablishmentResponse) {
            handler = new HandleInitiateKeyEstablishmentResponse((InitiateKeyEstablishmentResponse) command);
        }
        if (command instanceof EphemeralDataResponse) {
            handler = new HandleEphemeralDataResponse((EphemeralDataResponse) command);
        }
        if (command instanceof ConfirmKeyResponse) {
            handler = new HandleConfirmKeyResponse((ConfirmKeyResponse) command);
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

    private void stopCbke(int waitTime) {
        logger.debug("CBKE Key establishment client: Terminated");
        stopTerminationTimer();

        ZigBeeStatus returnState;
        switch (state) {
            case COMPLETE:
                returnState = ZigBeeStatus.SUCCESS;
                break;
            case FATAL:
                returnState = ZigBeeStatus.FATAL_ERROR;
                break;
            default:
                returnState = ZigBeeStatus.FAILURE;
                break;
        }

        smartEnergyClient.keyEstablishmentCallback(returnState, waitTime);
    }

    private KeyEstablishmentSuiteBitmap getPreferredCryptoSuite() {
        return KeyEstablishmentSuiteBitmap.CRYPTO_SUITE_1;
    }
}