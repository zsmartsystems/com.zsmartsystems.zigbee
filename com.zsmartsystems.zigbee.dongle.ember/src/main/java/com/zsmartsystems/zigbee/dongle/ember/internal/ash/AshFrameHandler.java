/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.internal.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Frame parser for the Silicon Labs Asynchronous Serial Host (ASH) protocol.
 * <p>
 * This class handles all the ASH protocol including retries, and provides
 * services to higher layers to allow sending of EZSP frames and the correlation
 * of their responds. Higher layers can simply send EZSP messages synchronously
 * and the handler will return with the completed result.
 * <p>
 * UG101: UART GATEWAY PROTOCOL REFERENCE: FOR THE EMBER® EZSP NETWORK CO-PROCESSOR
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(AshFrameHandler.class);

    /**
     * The receive timeout settings - min/initial/max - defined in milliseconds
     */
    private final int T_RX_ACK_MIN = 400;
    private final int T_RX_ACK_INIT = 1600;
    private final int T_RX_ACK_MAX = 3200;
    private int receiveTimeout = T_RX_ACK_INIT;

    /**
     * Maximum number of consecutive timeouts allowed while waiting to receive
     * an ACK
     */
    private final int ACK_TIMEOUTS = 4;
    private int retries = 0;

    /**
     * Maximum number of DATA frames we can transmit without an ACK
     */
    private final int TX_WINDOW = 1;

    private long sentTime;

    private final int ASH_CANCEL_BYTE = 0x1A;
    private final int ASH_FLAG_BYTE = 0x7E;
    private final int ASH_SUBSTITUTE_BYTE = 0x18;
    private final int ASH_XON_BYTE = 0x11;
    private final int ASH_OFF_BYTE = 0x13;
    private final int ASH_TIMEOUT = -1;

    private final int ASH_MAX_LENGTH = 131;

    private Integer ackNum = 0;
    private int frmNum = 0;

    private long statsTxAcks = 0;
    private long statsTxNaks = 0;
    private long statsTxData = 0;
    private long statsRxAcks = 0;
    private long statsRxNaks = 0;
    private long statsRxData = 0;
    private long statsRxErrs = 0;

    /**
     * The queue of {@link EzspFrameRequest} frames waiting to be sent
     */
    private final Queue<EzspFrameRequest> sendQueue = new ConcurrentLinkedQueue<EzspFrameRequest>();

    /**
     * The queue of {@link AshFrameData} frames that we have sent. These are kept in case a resend is required.
     */
    private final Queue<AshFrameData> sentQueue = new ConcurrentLinkedQueue<AshFrameData>();

    private final Timer timer = new Timer();
    private TimerTask timerTask = null;

    private boolean stateConnected = false;

    private ExecutorService executor = Executors.newCachedThreadPool();
    private final List<AshListener> transactionListeners = new ArrayList<AshListener>();

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
     * Construct the handler and provide the {@link EzspFrameHandler}
     *
     * @param frameHandler
     *            the packet handler
     */
    public AshFrameHandler(final EzspFrameHandler frameHandler) {
        this.frameHandler = frameHandler;
    }

    /**
     * Starts the handler. Sets input stream where the packet is read from the and
     * handler which further processes the received packet.
     *
     * @param port the {@link ZigBeePort}
     */
    public void start(final ZigBeePort port) {
        this.port = port;

        parserThread = new Thread("AshFrameHandler") {
            @Override
            public void run() {
                logger.debug("AshFrameHandler thread started");

                int exceptionCnt = 0;

                while (!closeHandler) {
                    try {
                        int[] packetData = getPacket();
                        if (packetData == null) {
                            continue;
                        }

                        final AshFrame packet = AshFrame.createFromInput(packetData);
                        AshFrame responseFrame = null;
                        if (packet == null) {
                            logger.error("<-- RX ASH error: BAD PACKET {}", AshFrame.frameToString(packetData));

                            // Send a NAK
                            responseFrame = new AshFrameNak(ackNum);
                        } else {
                            logger.debug("<-- RX ASH frame: {}", packet.toString());

                            // Reset the exception counter
                            exceptionCnt = 0;

                            // Extract the flags for DATA/ACK/NAK frames
                            switch (packet.getFrameType()) {
                                case DATA:
                                    statsRxData++;

                                    // Always use the ackNum - even if this frame is discarded
                                    ackSentQueue(packet.getAckNum());

                                    AshFrameData dataPacket = (AshFrameData) packet;

                                    // Check for out of sequence frame number
                                    if (packet.getFrmNum() == ackNum) {
                                        // Frame was in sequence

                                        // Get the EZSP frame
                                        EzspFrameResponse response = EzspFrame.createHandler(dataPacket);
                                        logger.debug("RX EZSP: " + response);
                                        if (response == null) {
                                            logger.debug("ASH: No frame handler created for {}", packet);
                                        } else if (response != null && !notifyTransactionComplete(response)) {
                                            // No transactions owned this response, so we pass it to
                                            // our unhandled response handler
                                            EzspFrame ezspFrame = EzspFrame.createHandler((dataPacket));
                                            if (ezspFrame != null) {
                                                frameHandler.handlePacket(ezspFrame);
                                            }
                                        }

                                        // Update our next expected data frame
                                        ackNum = (ackNum + 1) & 0x07;

                                        responseFrame = new AshFrameAck(ackNum);
                                    } else if (!dataPacket.getReTx()) {
                                        // Send a NAK - this is out of sequence and not a retransmission
                                        responseFrame = new AshFrameNak(ackNum);
                                    } else {
                                        // Send an ACK - this was out of sequence but was a retransmission
                                        responseFrame = new AshFrameAck(ackNum);
                                    }
                                    break;
                                case ACK:
                                    statsRxAcks++;
                                    ackSentQueue(packet.getAckNum());
                                    break;
                                case NAK:
                                    statsRxNaks++;
                                    sendRetry();
                                    break;
                                case RSTACK:
                                    // Stack has been reset!
                                    handleReset((AshFrameRstAck) packet);
                                    break;
                                case ERROR:
                                    statsRxErrs++;
                                    break;
                                default:
                                    break;
                            }
                        }

                        // Due to possible I/O buffering, it is important to note that the Host could receive several
                        // valid or invalid frames after triggering a reset of the NCP. The Host must discard all frames
                        // and errors until a valid RSTACK frame is received.
                        if (!stateConnected) {
                            continue;
                        }

                        // Send the next frame
                        // Note that ASH protocol requires the host always sends an ack.
                        // Piggybacking on data packets is not allowed
                        if (responseFrame != null) {
                            sendFrame(responseFrame);
                        }

                        sendNextFrame();
                    } catch (final IOException e) {
                        logger.error("AshFrameHandler IOException: ", e);

                        if (exceptionCnt++ > 10) {
                            logger.error("AshFrameHandler exception count exceeded");
                            // if (!close) {
                            // frameHandler.error(e);
                            closeHandler = true;
                        }
                    } catch (final Exception e) {
                        logger.error("AshFrameHandler Exception: ", e);
                    }
                }
                logger.debug("AshFrameHandler exited.");
            }
        };

        parserThread.setDaemon(true);
        parserThread.start();
    }

    private int[] getPacket() throws IOException {
        int[] inputBuffer = new int[ASH_MAX_LENGTH];
        int inputCount = 0;
        boolean inputError = false;

        while (!closeHandler) {
            int val = port.read();
            logger.trace("ASH RX: " + String.format("%02X", val));
            switch (val) {
                case ASH_CANCEL_BYTE:
                    // Cancel Byte: Terminates a frame in progress. A Cancel Byte causes all data received since the
                    // previous Flag Byte to be ignored. Note that as a special case, RST and RSTACK frames are preceded
                    // by Cancel Bytes to ignore any link startup noise.
                    inputCount = 0;
                    inputError = false;
                    break;
                case ASH_FLAG_BYTE:
                    // Flag Byte: Marks the end of a frame.When a Flag Byte is received, the data received since the
                    // last Flag Byte or Cancel Byte is tested to see whether it is a valid frame.
                    if (!inputError && inputCount != 0) {
                        return Arrays.copyOfRange(inputBuffer, 0, inputCount);
                    }
                    inputCount = 0;
                    inputError = false;
                    break;
                case ASH_SUBSTITUTE_BYTE:
                    // Substitute Byte: Replaces a byte received with a low-level communication error (e.g., framing
                    // error) from the UART.When a Substitute Byte is processed, the data between the previous and the
                    // next Flag Bytes is ignored.
                    inputError = true;
                    break;
                case ASH_XON_BYTE:
                    // XON: Resume transmissionUsed in XON/XOFF flow control. Always ignored if received by the NCP.
                    break;
                case ASH_OFF_BYTE:
                    // XOFF: Stop transmissionUsed in XON/XOFF flow control. Always ignored if received by the NCP.
                    break;
                case ASH_TIMEOUT:
                    break;
                default:
                    if (inputCount >= ASH_MAX_LENGTH) {
                        inputCount = 0;
                        inputError = true;
                    }
                    inputBuffer[inputCount++] = val;
                    break;
            }
        }

        return null;
    }

    private void handleReset(AshFrameRstAck rstAck) {
        // If we are already connected, we need to reconnect
        if (stateConnected) {
            reconnect();
            return;
        }

        // Make sure this is a software reset.
        // This avoids us reacting to a HW reset before our SW ack
        if (rstAck.getResetType() != AshErrorCode.RESET_SOFTWARE) {
            return;
        }

        // Check the version
        if (rstAck.getVersion() == 2) {
            stateConnected = true;
            ackNum = 0;
            frmNum = 0;
            sentQueue.clear();
            logger.debug("ASH: Connected");
            frameHandler.handleLinkStateChange(false);
        } else {
            logger.debug("ASH: Invalid version");
        }
    }

    /**
     * Set the close flag to true.
     */
    public void setClosing() {
        this.closeHandler = true;
    }

    /**
     * Requests parser thread to shutdown.
     */
    public void close() {
        setClosing();
        stopRetryTimer();

        try {
            parserThread.interrupt();
            parserThread.join();
            logger.debug("AshFrameHandler close complete.");
        } catch (InterruptedException e) {
            logger.warn("Interrupted in packet parser thread shutdown join.");
        }
    }

    /**
     * Checks if parser thread is alive.
     *
     * @return true if parser thread is alive.
     */
    public boolean isAlive() {
        return parserThread != null && parserThread.isAlive();
    }

    // Synchronize this method so we can do the window check without interruption.
    // Otherwise this method could be called twice from different threads that could end up with
    // more than the TX_WINDOW number of frames sent.
    private synchronized boolean sendNextFrame() {
        // We're not allowed to send if we're not connected
        if (!stateConnected) {
            logger.debug("ASH: Trying to send when not connected.");
            return false;
        }

        // Check how many frames are outstanding
        if (sentQueue.size() >= TX_WINDOW) {
            // check timer task
            if (timerTask == null) {
                startRetryTimer();
            }
            return false;
        }

        EzspFrameRequest nextFrame = sendQueue.poll();
        if (nextFrame == null) {
            // Nothing to send
            return false;
        }

        // Encapsulate the EZSP frame into the ASH packet
        logger.debug("TX EZSP: {}", nextFrame);
        AshFrameData ashFrame = new AshFrameData(nextFrame);

        sendFrame(ashFrame);
        return true;
    }

    private synchronized void sendFrame(AshFrame ashFrame) {
        switch (ashFrame.frameType) {
            case ACK:
                statsTxAcks++;
                break;
            case DATA:
                statsTxData++;
                // Set the frame number
                ((AshFrameData) ashFrame).setFrmNum(frmNum);
                frmNum = (frmNum + 1) & 0x07;

                // DATA frames need to go into a sent queue so we can retry if needed
                sentQueue.add((AshFrameData) ashFrame);
                break;
            case NAK:
                statsTxNaks++;
                break;
            default:
                break;
        }

        outputFrame(ashFrame);
    }

    private void sendRetry() {
        logger.debug("ASH: Retry Sent Queue Length {}", sentQueue.size());
        AshFrameData ashFrame = sentQueue.peek();
        if (ashFrame == null) {
            logger.debug("ASH: Retry nothing to resend!");
            return;
        }

        ashFrame.setReTx();
        outputFrame(ashFrame);
    }

    // Synchronize this method to ensure a packet gets sent as a block
    private synchronized void outputFrame(AshFrame ashFrame) {
        ashFrame.setAckNum(ackNum);
        logger.debug("--> TX ASH frame: {}", ashFrame);

        // Send the data
        for (int outByte : ashFrame.getOutputBuffer()) {
            port.write(outByte);
        }

        // logger.debug(result.toString());

        // Only start the timer for data frames
        if (ashFrame instanceof AshFrameData) {
            sentTime = System.nanoTime();
            startRetryTimer();
        }
    }

    /**
     * Add an EZSP frame to the send queue. The sendQueue is a FIFO queue.
     * This method queues a {@link EzspFrameRequest} frame without waiting for a response and
     * no transaction management is performed.
     *
     * @param request {@link EzspFrameRequest}
     */
    public void queueFrame(EzspFrameRequest request) {
        sendQueue.add(request);

        logger.debug("ASH: TX EZSP queue: {}", sendQueue.size());

        sendNextFrame();
    }

    /**
     * Connect to the ASH/EZSP NCP
     */
    public void connect() {
        stateConnected = false;
        AshFrame reset = new AshFrameRst();

        ackNum = 0;
        frmNum = 0;
        sentQueue.clear();
        sendQueue.clear();

        receiveTimeout = T_RX_ACK_INIT;

        sendFrame(reset);
    }

    private void reconnect() {
        frameHandler.handleLinkStateChange(false);

        connect();
    }

    /**
     * Acknowledge frames we've sent and removes the from the sent queue.
     * This method is called for each DATA or ACK frame where we have the 'ack' property.
     *
     * @param ackNum the last ack from the NCP
     */
    private void ackSentQueue(int ackNum) {
        // Handle the timer if it's running
        if (sentTime != 0) {
            stopRetryTimer();
            receiveTimeout = (int) ((receiveTimeout * 7 / 8) + ((System.nanoTime() - sentTime) / 2000000));
            if (receiveTimeout < T_RX_ACK_MIN) {
                receiveTimeout = T_RX_ACK_MIN;
            } else if (receiveTimeout > T_RX_ACK_MAX) {
                receiveTimeout = T_RX_ACK_MAX;
            }
            logger.trace("ASH: RX Timer took {}ms, timer now {}ms", (System.nanoTime() - sentTime) / 1000000,
                    receiveTimeout);
            sentTime = 0;
        }

        while (sentQueue.peek() != null && sentQueue.peek().getFrmNum() != ackNum) {
            AshFrameData ackedFrame = sentQueue.poll();
            if (ackedFrame == null) {
                logger.debug("ASH: Error nothing to remove from sent queue [{} -- {}]", this.ackNum, ackNum);
            }
            logger.debug("ASH: Frame acked and removed {}", ackedFrame);
        }
    }

    private synchronized void startRetryTimer() {
        // Stop any existing timer
        stopRetryTimer();

        // Create the timer task
        timerTask = new AshRetryTimer();
        timer.schedule(timerTask, receiveTimeout);
    }

    private synchronized void stopRetryTimer() {
        // Stop any existing timer
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        retries = 0;
    }

    private class AshRetryTimer extends TimerTask {
        @Override
        public void run() {
            // Resend the first message in the sentQueue
            if (sentQueue.isEmpty()) {
                return;
            }

            if (retries++ > ACK_TIMEOUTS) {
                // Too many retries.
                // We should alert the upper layer so they can reset the link?
                frameHandler.handleLinkStateChange(false);

                logger.debug("ASH: Error number of retries exceeded [{}].", retries);
                // drop message from queue
                sentQueue.poll();
                retries = 0;
            }

            // If a DATA frame acknowledgement is not received within the current timeout value,
            // then t_rx_ack is doubled.
            receiveTimeout *= 2;
            if (receiveTimeout > T_RX_ACK_MAX) {
                receiveTimeout = T_RX_ACK_MAX;
            }

            try {
                sendRetry();
            } catch (Exception e) {
                logger.warn("Caught exception while attempting to retry message in AshRetryTimer", e);
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
            for (AshListener listener : transactionListeners) {
                if (listener.transactionEvent(response)) {
                    processed = true;
                }
            }
        }

        return processed;
    }

    private void addTransactionListener(AshListener listener) {
        synchronized (transactionListeners) {
            if (transactionListeners.contains(listener)) {
                return;
            }

            transactionListeners.add(listener);
        }
    }

    private void removeTransactionListener(AshListener listener) {
        synchronized (transactionListeners) {
            transactionListeners.remove(listener);
        }
    }

    /**
     * Sends an EZSP request to the NCP without waiting for the response.
     *
     * @param ezspTransaction Request {@link EzspTransaction}
     * @return response {@link Future} {@link EzspFrame}
     */
    public Future<EzspFrame> sendEzspRequestAsync(final EzspTransaction ezspTransaction) {
        class TransactionWaiter implements Callable<EzspFrame>, AshListener {
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
                complete = true;
                synchronized (this) {
                    notify();
                }

                return true;
            }
        }

        Callable<EzspFrame> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

    /**
     * Sends an EZSP request to the NCP and waits for the response. The response is correlated with the request and the
     * returned {@link EzspFrame} contains the request and response data.
     *
     * @param ezspTransaction
     *            Request {@link EzspFrame}
     * @return response {@link EzspFrame}
     */
    public EzspTransaction sendEzspTransaction(EzspTransaction ezspTransaction) {
        Future<EzspFrame> futureResponse = sendEzspRequestAsync(ezspTransaction);
        if (futureResponse == null) {
            logger.debug("ASH: Error sending EZSP transaction: Future is null");
            return null;
        }

        try {
            futureResponse.get();
            return ezspTransaction;
        } catch (InterruptedException | ExecutionException e) {
            futureResponse.cancel(true);
            logger.debug("ASH interrupted in sendRequest: ", e);
        }

        return null;
    }

    /**
     * Get a map of statistics counters from the handler
     *
     * @return map of counters
     */
    public Map<String, Long> getCounters() {
        Map<String, Long> counters = new HashMap<String, Long>();

        counters.put("ASH_TX_DAT", statsTxData);
        counters.put("ASH_TX_NAK", statsTxNaks);
        counters.put("ASH_TX_ACK", statsTxAcks);
        counters.put("ASH_RX_DAT", statsRxData);
        counters.put("ASH_RX_NAK", statsRxNaks);
        counters.put("ASH_RX_ACK", statsRxAcks);
        counters.put("ASH_RX_ERR", statsRxErrs);

        return counters;
    }

    interface AshListener {
        boolean transactionEvent(EzspFrameResponse ezspResponse);
    }
}
