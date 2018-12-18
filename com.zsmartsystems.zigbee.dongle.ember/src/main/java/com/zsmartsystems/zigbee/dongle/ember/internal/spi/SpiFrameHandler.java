/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.spi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspCallbackRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNoCallbacksResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSendBroadcastResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspSendUnicastResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Frame parser for the Silicon Labs SPI protocol.
 * <p>
 * This class handles all the SPI protocol, and provides services to higher layers to allow sending of EZSP frames and
 * the correlation of their responds. Higher layers can simply send EZSP messages synchronously and the handler will
 * return with the completed result.
 * <p>
 * AN711: EZSP-SPI Host Interfacing Guide
 *
 * @author Chris Jackson
 *
 */
public class SpiFrameHandler implements EzspProtocolHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(SpiFrameHandler.class);

    /**
     * Maximum number of consecutive timeouts allowed while waiting to receive the response
     */
    private final int ACK_TIMEOUTS = 4;
    private int retries = 0;

    private final int SPI_FLAG_BYTE = 0xA7;

    private final int SPI_MAX_LENGTH = 136;

    private final int SPI_CMD_RESET = 0x00;
    private final int SPI_CMD_OVERSIZE = 0x01;
    private final int SPI_CMD_ABORT = 0x02;
    private final int SPI_CMD_MISSINGTERMINATOR = 0x03;
    private final int SPI_CMD_UNSUPPORTEDCMD = 0x04;
    private final int SPI_CMD_SPIVERSION = 0x0A;
    private final int SPI_CMD_SPISTATUS = 0x0B;
    private final int SPI_CMD_BOOTLOADER = 0xFD;
    private final int SPI_CMD_EZSP = 0xFE;
    private final int SPI_CMD_INVALID = 0xFF;

    private final int SPI_MASK = 0xC0;
    private final int SPI_MASK_STATUS = 0xC0;
    private final int SPI_MASK_VERSION = 0x80;
    private final int SPI_MASK_VERSIONDATA = 0x3F;
    private final int SPI_MASK_READY = 0x01;

    private final int[] requestSpiStatus = new int[] { SPI_CMD_SPISTATUS };
    private final int[] requestSpiVersion = new int[] { SPI_CMD_SPIVERSION };

    private final AtomicBoolean doCallbackRequest = new AtomicBoolean();

    private ScheduledExecutorService pollingScheduler;
    private ScheduledFuture<?> pollingTimer = null;

    private Object outputFrameSynchronisation = new Object();

    /**
     * Response timeout. The number of milliseconds to wait for a response before resending the request.
     */
    private int receiveTimeout = 500;

    /**
     * Callback polling rate in milliseconds.
     */
    private int pollRate = 250;

    private final Timer timer = new Timer();
    private TimerTask timerTask = null;

    /**
     * The queue of {@link EzspFrameRequest} frames waiting to be sent
     */
    private final Queue<EzspFrameRequest> sendQueue = new ConcurrentLinkedQueue<EzspFrameRequest>();

    private boolean stateConnected = false;

    private int[] lastFrameSent = null;

    private ExecutorService executor = Executors.newCachedThreadPool();
    private final List<SpiListener> transactionListeners = new ArrayList<SpiListener>();

    private final Map<Integer, String> errorMessages = new ConcurrentHashMap<Integer, String>();

    /**
     * The packet handler.
     */
    private final EzspFrameHandler frameHandler;

    /**
     * The port.
     */
    private ZigBeePort port;

    /**
     * The parser parserThread.
     */
    private Thread parserThread = null;

    /**
     * Flag reflecting that parser has been closed and parser parserThread
     * should exit.
     */
    private boolean closeHandler = false;

    /**
     * Simple counter of the number of error frames received
     */
    private int spiErrors = 0;

    /**
     * Construct the handler and provide the {@link EzspFrameHandler}
     *
     * @param frameHandler the {@link EzspFrameHandler} packet handler
     */
    public SpiFrameHandler(final EzspFrameHandler frameHandler) {
        logger.debug("SpiFrameHandler created");
        this.frameHandler = frameHandler;

        errorMessages.put(SPI_CMD_RESET, "Reset");
        errorMessages.put(SPI_CMD_OVERSIZE, "Oversized Frame");
        errorMessages.put(SPI_CMD_ABORT, "Abort");
        errorMessages.put(SPI_CMD_MISSINGTERMINATOR, "Missing Terminator");
        errorMessages.put(SPI_CMD_UNSUPPORTEDCMD, "Unsupported Command");
        errorMessages.put(SPI_CMD_INVALID, "Invalid");
    }

    @Override
    public void start(final ZigBeePort port) {
        this.port = port;

        pollingScheduler = Executors.newSingleThreadScheduledExecutor();

        parserThread = new Thread("SpiFrameHandler") {
            @Override
            public void run() {
                logger.debug("SpiFrameHandler thread started");

                int exceptionCnt = 0;

                while (!closeHandler) {
                    try {
                        int[] packetData = getPacket();
                        if (packetData == null) {
                            continue;
                        }

                        // Reset the exception counter
                        exceptionCnt = 0;

                        processSpiCommand(packetData);

                        // Send the next frame
                        sendNextFrame();
                    } catch (final IOException e) {
                        logger.error("SpiFrameHandler IOException: ", e);

                        if (exceptionCnt++ > 10) {
                            logger.error("SpiFrameHandler exception count exceeded");
                            closeHandler = true;
                        }
                    } catch (final Exception e) {
                        logger.error("SpiFrameHandler Exception: ", e);
                    }
                }
                logger.debug("SpiFrameHandler exited.");
            }
        };

        parserThread.setDaemon(true);
        parserThread.start();

        restartPolling();
    }

    enum RxState {
        RX_SEARCH,
        RX_TYPE,
        RX_LENGTH,
        RX_DATA,
        RX_FLAG,
    }

    /**
     * Gets the data packet. Reads the first byte, then gets the length, and reads this number of bytes. The last byte
     * must then be the FLAG - if it isn't, the routine enters a scanning mode waiting for the FLAG byte to try and
     * resynchronise.
     *
     * @return integer array of received data without the termination flag
     * @throws IOException
     */
    private int[] getPacket() throws IOException {
        int[] inputBuffer = new int[SPI_MAX_LENGTH];
        int inputCount = 0;
        int inputLength = 0;
        RxState rxState = RxState.RX_TYPE;

        while (!closeHandler) {
            int val = port.read();
            logger.trace("SPI RX: {}", String.format("%02X", val));
            switch (rxState) {
                case RX_TYPE:
                    inputBuffer[0] = val;
                    rxState = RxState.RX_LENGTH;
                    break;
                case RX_LENGTH:
                    // This catches all the special 1-byte frames
                    if (val == SPI_FLAG_BYTE) {
                        return Arrays.copyOfRange(inputBuffer, 0, 1);
                    }

                    inputBuffer[1] = val;
                    if (val >= SPI_MAX_LENGTH) {
                        rxState = RxState.RX_SEARCH;
                        break;
                    } else if (val == 0) {
                        rxState = RxState.RX_FLAG;
                    } else {
                        rxState = RxState.RX_DATA;
                    }
                    inputLength = 2;
                    inputCount = 0;
                    break;
                case RX_DATA:
                    inputBuffer[inputLength++] = val;
                    if (++inputCount == inputBuffer[1]) {
                        rxState = RxState.RX_FLAG;
                    }
                    break;
                case RX_FLAG:
                    if (val == SPI_FLAG_BYTE) {
                        return Arrays.copyOfRange(inputBuffer, 0, inputLength);
                    }
                    rxState = RxState.RX_SEARCH;
                    break;
                case RX_SEARCH:
                    if (val == SPI_FLAG_BYTE) {
                        rxState = RxState.RX_TYPE;
                    }
                    break;
                default:
                    logger.error("Unknown state {} in SPI receive state machine", rxState);
                    rxState = RxState.RX_SEARCH;
                    break;
            }
        }

        return null;
    }

    /**
     * Called when a low level transaction is complete (ie there is a response to our request). This resets the various
     * variables, and stops the timer.
     */
    private void spiTransactionComplete() {
        lastFrameSent = null;
        retries = 0;
        stopRetryTimer();
    }

    private void processSpiCommand(int[] packetData) {
        if (lastFrameSent == null) {
            logger.debug("<-- RX SPI frame when lastFrameSent is null: {}", frameToString(packetData));
            return;
        }

        switch (packetData[0]) {
            case SPI_CMD_EZSP:
                if (lastFrameSent[0] == SPI_CMD_EZSP) {
                    spiTransactionComplete();
                } else {
                    logger.debug("<-- RX SPI frame type mismatch SPI_CMD_EZSP != {}",
                            String.format("%02X", lastFrameSent[0]));
                }

                int[] ezspPacketData = Arrays.copyOfRange(packetData, 2, packetData.length);

                // Only trace-log in case of NoCallBacksResponse (which is due to polling)
                String logMessage = String.format("<-- RX SPI frame: %s", frameToString(packetData));
                if (isEzspNoCallbacksResponse(ezspPacketData)) {
                    logger.trace(logMessage);
                } else {
                    logger.debug(logMessage);
                }

                processSpiEzsp(ezspPacketData);
                break;

            case SPI_CMD_BOOTLOADER:
                logger.debug("<-- RX SPI frame: {}", frameToString(packetData));
                if (lastFrameSent[0] == SPI_CMD_BOOTLOADER) {
                    spiTransactionComplete();
                } else {
                    logger.debug("<-- RX SPI frame type mismatch SPI_CMD_BOOTLOADER != {}",
                            String.format("%02X", lastFrameSent[0]));
                }
                break;

            case SPI_CMD_OVERSIZE:
            case SPI_CMD_ABORT:
            case SPI_CMD_MISSINGTERMINATOR:
            case SPI_CMD_UNSUPPORTEDCMD:
            case SPI_CMD_RESET:
            case SPI_CMD_INVALID:
                logger.debug("<-- RX SPI frame: {}", frameToString(packetData));
                logger.debug("SPI frame: {}", errorMessages.get(packetData[0]));
                spiErrors++;
                break;

            default:
                logger.debug("<-- RX SPI frame: {}", frameToString(packetData));
                switch (lastFrameSent[0]) {
                    case SPI_CMD_SPIVERSION:
                        if ((packetData[0] & SPI_MASK) != SPI_MASK_VERSION) {
                            break;
                        }
                        spiTransactionComplete();
                        logger.debug("SPI Protocol Version: {}", packetData[0] & SPI_MASK_VERSIONDATA);
                        outputFrame(requestSpiStatus);
                        break;

                    case SPI_CMD_SPISTATUS:
                        if ((packetData[0] & SPI_MASK) != SPI_MASK_STATUS) {
                            break;
                        }
                        spiTransactionComplete();
                        stateConnected = (packetData[0] & SPI_MASK_READY) == SPI_MASK_READY;
                        logger.debug("SPI Protocol Ready: {}", stateConnected);
                        if (stateConnected) {
                            frameHandler.handleLinkStateChange(true);
                        } else {
                            outputFrame(requestSpiStatus);
                        }
                        break;
                    default:
                        logger.error("Unknown last frame ID {} in SPI command processor",
                                String.format("%02X", lastFrameSent[0]));
                }
                break;
        }
    }

    /**
     * Hard-code the check for 'NoCallbacksResponse' here, so that decision for trace/debug-level logging can be taken
     * here without using {@link EzspFrame} code.
     */
    private boolean isEzspNoCallbacksResponse(int[] ezspPacketData) {
        return ezspPacketData[2] == 0x07;
    }

    private void processSpiEzsp(int[] packetData) {
        // Get the EZSP frame
        EzspFrameResponse response = EzspFrame.createHandler(packetData);
        if (response == null) {
            logger.debug("No frame handler created for {}", frameToString(packetData));
            return;
        }

        // Only trace-log for NoCallbacksResponse (which is due to polling)
        String logMessage = String.format("RX EZSP: %s", response.toString());
        if (response instanceof EzspNoCallbacksResponse) {
            logger.trace(logMessage);
        } else {
            logger.debug(logMessage);
        }

        // If there is a callback pending, then send a poll
        if (response.isCallbackPending()) {
            doCallbackRequest.set(true);
        }

        if (notifyTransactionComplete(response)) {
            // Response to our request was received
            stopRetryTimer();
        } else {
            // No transactions owned this response, so we pass it to
            // our unhandled response handler
            if (!(response instanceof EzspNoCallbacksResponse)) {
                frameHandler.handlePacket(response);
            }
        }

        // We restart polling to avoid polling too often.
        // Rational here is that every EZSP frame contains a "Callback Pending" flag, so at this point
        // in time, we don't need to poll as we know the state.
        restartPolling();
    }

    @Override
    public void setClosing() {
        executor.shutdown();
        closeHandler = true;
    }

    @Override
    public void close() {
        if (pollingTimer != null) {
            pollingTimer.cancel(true);
        }
        pollingScheduler.shutdown();

        logger.debug("SpiFrameHandler close.");

        setClosing();
        stopRetryTimer();

        synchronized (transactionListeners) {
            for (SpiListener listener : transactionListeners) {
                listener.transactionComplete();
            }
        }

        timer.cancel();
        executor.shutdownNow();

        try {
            parserThread.interrupt();
            parserThread.join();
            logger.debug("SpiFrameHandler close complete.");
        } catch (InterruptedException e) {
            logger.warn("SpiFrameHandler interrupted in packet parser thread shutdown join.");
        }
    }

    @Override
    public boolean isAlive() {
        return parserThread != null && parserThread.isAlive();
    }

    private synchronized boolean sendNextFrame() {
        // We're not allowed to send if we're not connected or waiting a response to a previous frame
        if (!stateConnected || lastFrameSent != null) {
            return false;
        }

        EzspFrameRequest nextFrame;
        boolean isCallbackRequest = false;

        if (doCallbackRequest.getAndSet(false)) {
            // We need to poll for callbacks..
            // This takes priority to avoid overflow of the callback queue in the NCP
            nextFrame = new EzspCallbackRequest();
            isCallbackRequest = true;
        } else {
            nextFrame = sendQueue.poll();
            if (nextFrame == null) {
                // Nothing to send
                return false;
            }
        }

        // Only trace-log for callback requests (which occur due to polling)
        String logMessage = String.format("TX EZSP: %s", nextFrame.toString());
        if (isCallbackRequest) {
            logger.trace(logMessage);
        } else {
            logger.debug(logMessage);
        }

        // Encapsulate the EZSP frame into the SPI packet
        int[] serializedData = nextFrame.serialize();
        int[] outputData = new int[serializedData.length + 2];
        outputData[0] = SPI_CMD_EZSP;
        outputData[1] = serializedData.length;
        int cnt = 2;
        for (int outByte : serializedData) {
            outputData[cnt++] = outByte;
        }
        outputFrame(outputData, isCallbackRequest);

        return true;
    }

    private void outputFrame(int[] outputData) {
        this.outputFrame(outputData, false);
    }

    // Synchronize this method to ensure a packet gets sent as a block
    private void outputFrame(int[] outputData, boolean tracelogFrame) {
        synchronized (outputFrameSynchronisation) {
            lastFrameSent = outputData;

            String logMessage = String.format("--> TX SPI frame: %s", frameToString(outputData));
            if (tracelogFrame) {
                logger.trace(logMessage);
            } else {
                logger.debug(logMessage);
            }

            // Send the data
            for (int outByte : outputData) {
                logger.trace("SPI TX: {}", String.format("%02X", outByte));
                port.write(outByte);
            }
            port.write(SPI_FLAG_BYTE);

            startRetryTimer();
        }
    }

    @Override
    public void queueFrame(EzspFrameRequest request) {
        sendQueue.add(request);

        logger.debug("TX EZSP queue: {}", sendQueue.size());

        sendNextFrame();
    }

    @Override
    public void connect() {
        logger.debug("SPI: connect");
        stateConnected = false;
        sendQueue.clear();

        outputFrame(requestSpiVersion);
    }

    private void restartPolling() {
        if (pollingTimer != null) {
            pollingTimer.cancel(true);
        }

        pollingTimer = pollingScheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (stateConnected && sendQueue.isEmpty()) {
                    doCallbackRequest.set(true);
                    sendNextFrame();
                }
            }
        }, pollRate, pollRate, TimeUnit.MILLISECONDS);
    }

    private synchronized void startRetryTimer() {
        if (closeHandler) {
            logger.debug("SPI Timer task not started as thread closing.");
            return;
        }

        // Stop any existing timer
        stopRetryTimer();

        // Create the timer task
        timerTask = new RetryTimer();
        timer.schedule(timerTask, receiveTimeout);
    }

    private synchronized void stopRetryTimer() {
        // Stop any existing timer
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private class RetryTimer extends TimerTask {
        @Override
        public void run() {
            synchronized (outputFrameSynchronisation) {

                logger.debug("SPI Timer task [{}].  {}", retries, frameToString(lastFrameSent));

                // Resend the last message sent if there is one
                if (lastFrameSent == null) {
                    return;
                }

                if (retries++ > ACK_TIMEOUTS) {
                    // Too many retries.
                    // We should alert the upper layer so they can reset the link?
                    frameHandler.handleLinkStateChange(false);

                    logger.debug("Error: number of retries exceeded [{}].", retries);
                    // drop message
                    lastFrameSent = null;
                    retries = 0;
                    return;
                }

                try {
                    outputFrame(lastFrameSent);
                } catch (Exception e) {
                    logger.warn("Caught exception while attempting to retry message in SpiRetryTimer", e);
                }
            }
        }
    }

    /**
     * Notify any transaction listeners when we receive a response.
     *
     * @param response the response data received
     * @return true if the response was processed
     */
    private boolean notifyTransactionComplete(final EzspFrameResponse response) {
        boolean processed = false;

        synchronized (transactionListeners) {
            for (SpiListener listener : transactionListeners) {
                if (listener.transactionEvent(response)) {
                    processed = true;
                }
            }
        }

        // For responses to higher level commands, we still want to pass these up so we can provide the
        // update the transaction progress.
        if (response instanceof EzspSendUnicastResponse || response instanceof EzspSendBroadcastResponse) {
            processed = false;
        }

        return processed;
    }

    private void addTransactionListener(SpiListener listener) {
        synchronized (transactionListeners) {
            if (transactionListeners.contains(listener)) {
                return;
            }

            transactionListeners.add(listener);
        }
    }

    private void removeTransactionListener(SpiListener listener) {
        synchronized (transactionListeners) {
            transactionListeners.remove(listener);
        }
    }

    @Override
    public Future<EzspFrame> sendEzspRequestAsync(final EzspTransaction ezspTransaction) {
        class TransactionWaiter implements Callable<EzspFrame>, SpiListener {
            // private EzspFrame response = null;
            private boolean complete = false;

            @Override
            public EzspFrame call() {
                // Register a listener
                addTransactionListener(this);

                // Send the transaction
                queueFrame(ezspTransaction.getRequest());

                // Wait for the transaction to complete
                synchronized (this) {
                    while (!complete) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            complete = true;
                        }
                    }
                }

                // Remove the listener
                removeTransactionListener(this);

                return null;// response;
            }

            @Override
            public boolean transactionEvent(EzspFrameResponse ezspResponse) {
                // Check if this response completes our transaction
                if (!ezspTransaction.isMatch(ezspResponse)) {
                    return false;
                }

                // response = request;
                synchronized (this) {
                    complete = true;
                    notify();
                }

                return true;
            }

            @Override
            public void transactionComplete() {
                synchronized (this) {
                    complete = true;
                    notify();
                }
            }
        }

        Callable<EzspFrame> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

    @Override
    public EzspTransaction sendEzspTransaction(EzspTransaction ezspTransaction) {
        Future<EzspFrame> futureResponse = sendEzspRequestAsync(ezspTransaction);
        if (futureResponse == null) {
            logger.debug("Error sending EZSP transaction: Future is null");
            return null;
        }

        try {
            futureResponse.get();
            return ezspTransaction;
        } catch (InterruptedException | ExecutionException e) {
            futureResponse.cancel(true);
            logger.debug("EZSP interrupted in sendRequest: ", e);
        }

        return null;
    }

    private String frameToString(int[] inputBuffer) {
        if (inputBuffer == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < inputBuffer.length; i++) {
            result.append(String.format("%02X ", inputBuffer[i]));
        }
        return result.toString();
    }

    public int getSpiErrors() {
        return spiErrors;
    }

    interface SpiListener {
        boolean transactionEvent(EzspFrameResponse ezspResponse);

        void transactionComplete();
    }

    @Override
    public Map<String, Long> getCounters() {
        Map<String, Long> counters = new ConcurrentHashMap<String, Long>();

        // counters.put("ASH_TX_DAT", statsTxData);
        // counters.put("ASH_TX_NAK", statsTxNaks);
        // counters.put("ASH_TX_ACK", statsTxAcks);
        // counters.put("ASH_RX_DAT", statsRxData);
        // counters.put("ASH_RX_NAK", statsRxNaks);
        // counters.put("ASH_RX_ACK", statsRxAcks);
        // counters.put("ASH_RX_ERR", statsRxErrs);

        return counters;
    }
}
