/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeExecutors;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspInvalidCommandResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.EzspProtocolHandler;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
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
 * <p>
 * Any errors in this class will be reported to the next layer by setting the handler OFFLINE (reported through the
 * {@link EzspFrameHandler#handleLinkStateChange(boolean)} method). The application is then responsible to reconfigure
 * and restart the connection.
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandler implements EzspProtocolHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(AshFrameHandler.class);

    /**
     * The receive timeout settings - min/initial/max - defined in milliseconds
     */
    private static final int T_RX_ACK_MIN = 400;
    private static final int T_RX_ACK_INIT = 1600;
    private static final int T_RX_ACK_MAX = 3200;
    private static final int T_RSTACK_MAX = 3200;
    private int receiveTimeout = T_RX_ACK_INIT;

    private static final int T_CON_HOLDOFF = 1250;
    private int connectTimeout = T_CON_HOLDOFF;

    /**
     * Maximum number of consecutive timeouts allowed while waiting to receive an ACK
     */
    private static final int ACK_TIMEOUTS = 4;
    private int retries = 0;

    /**
     * Maximum number of DATA frames we can transmit without an ACK
     */
    private final int TX_WINDOW = 1;

    private long sentTime;

    private static final int ASH_CANCEL_BYTE = 0x1A;
    private static final int ASH_FLAG_BYTE = 0x7E;
    private static final int ASH_SUBSTITUTE_BYTE = 0x18;
    private static final int ASH_XON_BYTE = 0x11;
    private static final int ASH_OFF_BYTE = 0x13;
    private static final int ASH_TIMEOUT = -1;

    private static final int ASH_MAX_LENGTH = 220;

    private static final int RX_QUEUE_LEN = 20;

    /**
     * Timeout after which sending an EZSP transaction is aborted.
     */
    private static final long EZSP_TRANSACTION_TIMEOUT_SECONDS = 10;

    private Integer ackNum = 0;
    private int frmNum = 0;

    private long statsTxAcks = 0;
    private long statsTxNaks = 0;
    private long statsTxData = 0;
    private long statsRxAcks = 0;
    private long statsRxNaks = 0;
    private long statsRxData = 0;
    private long statsRxErrs = 0;

    private final BlockingQueue<EzspFrameResponse> recvQueue = new ArrayBlockingQueue<>(RX_QUEUE_LEN);

    /**
     * The queue of {@link EzspFrameRequest} frames waiting to be sent
     */
    private final Queue<EzspFrameRequest> sendQueue = new ConcurrentLinkedQueue<EzspFrameRequest>();

    /**
     * The queue of {@link AshFrameData} frames that we have sent. These are kept in case a resend is required.
     */
    private final Queue<AshFrameData> sentQueue = new ConcurrentLinkedQueue<AshFrameData>();

    private ScheduledExecutorService timer = ZigBeeExecutors.newScheduledThreadPool(1, "AshTimer");
    private ScheduledFuture<?> timerFuture;

    private boolean stateConnected = false;

    private ExecutorService executor = ZigBeeExecutors.newCachedThreadPool("AshExecutor");
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
     * The parser parser thread.
     */
    private AshReceiveParserThread parserThread;

    /**
     * The processor thread
     */
    private AshReceiveProcessorThread processorThread;

    /**
     * Flag reflecting that parser has been closed and parser parserThread
     * should exit.
     */
    private boolean closeHandler = false;

    /**
     * Construct the handler and provide the {@link EzspFrameHandler}
     *
     * @param frameHandler the {@link EzspFrameHandler} packet handler
     */
    public AshFrameHandler(final EzspFrameHandler frameHandler) {
        this.frameHandler = frameHandler;
    }

    @Override
    public void start(final ZigBeePort port) {
        this.port = port;

        processorThread = new AshReceiveProcessorThread();
        processorThread.setDaemon(true);
        processorThread.start();

        parserThread = new AshReceiveParserThread();
        parserThread.setDaemon(true);
        parserThread.start();
    }

    private int[] getPacket() throws IOException {
        int[] inputBuffer = new int[ASH_MAX_LENGTH];
        int inputCount = 0;
        boolean inputError = false;

        while (!closeHandler) {
            int val = port.read();
            logger.trace("ASH RX: {}", String.format("%02X", val));
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

    private void handleIncomingFrame(EzspFrameResponse ezspFrame) {
        if (stateConnected && ezspFrame != null) {
            synchronized (recvQueue) {
                recvQueue.add(ezspFrame);
                logger.trace("ASH added EZSP frame to receive queue. Queue length {}", recvQueue.size());
                recvQueue.notify();
            }
        } else {
            logger.debug("ASH unable to add EZSP frame to receive queue. connected {}", stateConnected);
        }
    }

    private class AshReceiveParserThread extends Thread {
        AshReceiveParserThread() {
            super("AshReceiveParserThread");
        }

        @Override
        public void run() {
            logger.debug("AshReceiveParserThread thread started");

            setPriority(Thread.MAX_PRIORITY);

            int exceptionCnt = 0;

            // Ensure that a NAK is only sent for the first error in a sequence and a
            // valid response is required before sending another NAK.
            boolean rejectionCondition = false;

            while (!closeHandler) {
                try {
                    int[] packetData = getPacket();
                    if (packetData == null) {
                        continue;
                    }

                    final AshFrame packet = AshFrame.createFromInput(packetData);
                    AshFrame responseFrame = null;
                    if (packet == null) {
                        logger.debug("<-- RX ASH error: BAD PACKET {}", frameToString(packetData));

                        // Send a NAK and set rejection condition
                        if (!rejectionCondition) {
                            rejectionCondition = true;
                            responseFrame = new AshFrameNak(ackNum);
                        }
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
                                    // Clear rejection condition
                                    rejectionCondition = false;

                                    // Frame was in sequence - prepare the response
                                    ackNum = (ackNum + 1) & 0x07;
                                    responseFrame = new AshFrameAck(ackNum);

                                    // Get the EZSP frame
                                    EzspFrameResponse response = EzspFrame
                                            .createHandler(dataPacket.getDataBuffer());
                                    logger.trace("ASH RX EZSP: {}", response);
                                    if (response == null) {
                                        logger.debug("ASH: No frame handler created for {}", packet);
                                    } else {
                                        handleIncomingFrame(response);
                                    }
                                } else if (!dataPacket.getReTx()) {
                                    // Send a NAK - this is out of sequence and not a retransmission
                                    logger.debug("ASH: Frame out of sequence - expected {}, received {}", ackNum,
                                            packet.getFrmNum());

                                    // Send a NAK and set rejection condition
                                    if (!rejectionCondition) {
                                        rejectionCondition = true;
                                        responseFrame = new AshFrameNak(ackNum);
                                    }
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
                                // Stack has entered FAILED state
                                handleError((AshFrameError) packet);
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
                    logger.error("AshReceiveParserThread IOException: ", e);

                    if (exceptionCnt++ > 10) {
                        logger.error("AshReceiveParserThread exception count exceeded");
                        closeHandler = true;
                    }
                } catch (final Exception e) {
                    logger.error("AshReceiveParserThread Exception: ", e);
                }
            }
            logger.debug("AshReceiveParserThread exited.");
        }
    }

    private class AshReceiveProcessorThread extends Thread {
        AshReceiveProcessorThread() {
            super("AshReceiveProcessorThread");
        }

        @Override
        public void run() {
            EzspFrameResponse ezspFrame;
            while (!interrupted()) {
                if (closeHandler) {
                    break;
                }
                try {
                    ezspFrame = recvQueue.take();
                    logger.trace("ASH took EZSP frame from receive queue. Queue length {}", recvQueue.size());
                    notifyTransactionComplete(ezspFrame);
                    frameHandler.handlePacket(ezspFrame);
                } catch (final Exception e) {
                    logger.error("AshFrameHandler Exception processing EZSP frame: ", e);
                }
            }
        }
    }

    private synchronized void handleReset(AshFrameRstAck rstAck) {
        // If we are already connected, we need to reconnect
        if (stateConnected) {
            logger.debug("ASH: RESET received while connected. Disconnecting.");
            disconnect();
            return;
        }

        // Make sure this is a software reset.
        // This avoids us reacting to a HW reset before our SW ack
        if (rstAck.getResetType() != AshErrorCode.RESET_SOFTWARE) {
            return;
        }

        // Check the version
        if (rstAck.getVersion() == 2) {
            startConnectTimer();
        } else {
            logger.debug("ASH: Invalid version");
        }

        // We used a longer timeout when sending the RESET, so set the timeout to the initial value
        receiveTimeout = T_RX_ACK_INIT;
    }

    private void handleError(AshFrameError packet) {
        logger.debug("ASH: ERROR received (code {}).", packet.getErrorCode());
        statsRxErrs++;
        if (stateConnected) {
            logger.warn("ASH: ERROR received (code {}). Disconnecting.", packet.getErrorCode());
            disconnect();
        }
    }

    @Override
    public void setClosing() {
        executor.shutdown();
        closeHandler = true;
    }

    @Override
    public void close() {
        logger.debug("AshFrameHandler close.");
        setClosing();
        stopRetryTimer();
        stateConnected = false;

        synchronized (recvQueue) {
            recvQueue.notify();
        }

        clearTransactionQueue();

        sentQueue.clear();
        sendQueue.clear();

        frameHandler.handleLinkStateChange(false);

        timer.shutdownNow();
        executor.shutdownNow();

        try {
            parserThread.interrupt();
            parserThread.join();
            logger.debug("AshFrameHandler parsed thread terminated.");
        } catch (InterruptedException e) {
            logger.debug("AshFrameHandler interrupted in packet parser thread shutdown join.");
        }

        try {
            processorThread.interrupt();
            processorThread.join();
            logger.debug("AshFrameHandler processor thread terminated.");
        } catch (InterruptedException e) {
            logger.debug("AshFrameHandler interrupted in processor thread shutdown join.");
        }

        logger.debug("AshFrameHandler close complete.");
    }

    @Override
    public boolean isAlive() {
        return parserThread != null && parserThread.isAlive();
    }

    // Synchronize this method so we can do the window check without interruption.
    // Otherwise this method could be called twice from different threads that could end up with
    // more than the TX_WINDOW number of frames sent.
    private synchronized boolean sendNextFrame() {
        // We're not allowed to send if we're not connected
        if (!stateConnected) {
            return false;
        }

        // Check how many frames are outstanding
        if (sentQueue.size() >= TX_WINDOW) {
            // check timer task
            if (timerFuture == null) {
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
        logger.trace("TX ASH EZSP: {}", nextFrame);
        AshFrameData ashFrame = new AshFrameData(nextFrame);

        retries = 0;
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
        int[] outputBuffer = ashFrame.getOutputBuffer();
        if(logger.isTraceEnabled()) {
            for (int val : outputBuffer) {
                logger.trace("ASH TX: {}", String.format("%02X", val));
            }
        }
        port.write(outputBuffer);

        // Only start the timer for data and reset frames
        if (ashFrame instanceof AshFrameData || ashFrame instanceof AshFrameRst) {
            if (ashFrame instanceof AshFrameRst) {
                // Set the timeout to allow for the stack reset time
                receiveTimeout = T_RSTACK_MAX;
            }
            sentTime = System.nanoTime();
            startRetryTimer();
        }
    }

    @Override
    public void queueFrame(EzspFrameRequest request) {
        if (closeHandler) {
            logger.debug("ASH: Handler is closed");
            return;
        }
        sendQueue.add(request);

        logger.trace("ASH: TX EZSP queue size: {}", sendQueue.size());

        sendNextFrame();
    }

    /**
     * Connect to the ASH/EZSP NCP
     */
    @Override
    public synchronized void connect() {
        logger.debug("ASH: Connect");
        stateConnected = false;

        sentQueue.clear();
        sendQueue.clear();

        reconnect();
    }

    public synchronized void reconnect() {
        logger.debug("ASH: Reconnect");
        ackNum = 0;
        frmNum = 0;
        receiveTimeout = T_RX_ACK_INIT;

        sendFrame(new AshFrameRst());
    }

    private void disconnect() {
        logger.debug("ASH: Disconnected!");

        close();
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
            logger.trace("ASH: Frame acked and removed {}", ackedFrame);
        }
    }

    private synchronized void startRetryTimer() {
        // Stop any existing timer
        stopRetryTimer();

        timerFuture = timer.schedule(new AshRetryTimer(), receiveTimeout, TimeUnit.MILLISECONDS);

        logger.trace("ASH: Started retry timer");
    }

    private synchronized void startConnectTimer() {
        // Stop any existing timer - shouldn't happen here, but play safe!
        stopRetryTimer();

        timerFuture = timer.schedule(new AshConnectTimer(), connectTimeout, TimeUnit.MILLISECONDS);

        logger.trace("ASH: Started connect timer");
    }

    private synchronized void stopRetryTimer() {
        // Stop any existing timer
        if (timerFuture != null) {
            timerFuture.cancel(true);
            timerFuture = null;
        }
    }

    private class AshRetryTimer implements Runnable {
        @Override
        public void run() {
            // Resend the first message in the sentQueue
            if (stateConnected && sentQueue.isEmpty()) {
                return;
            }

            // If we're not connected, then try again
            if (!stateConnected) {
                stopRetryTimer();
                reconnect();
                return;
            }

            if (retries++ > ACK_TIMEOUTS) {
                logger.debug("ASH: Error number of retries exceeded [{}].", retries);

                // Too many retries.
                // We should alert the upper layer so they can reset the link?
                disconnect();
                return;
            }

            // If we're not connected, then try the reset again
            if (!stateConnected) {
                sendFrame(new AshFrameRst());
                return;
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

    private class AshConnectTimer implements Runnable {
        @Override
        public void run() {
            logger.debug("ASH: Connected");
            stopRetryTimer();
            stateConnected = true;
            ackNum = 0;
            frmNum = 0;
            sentQueue.clear();
            frameHandler.handleLinkStateChange(true);
            sendNextFrame();
        }
    }

    /**
     * Aborts all waiting transactions
     */
    private void clearTransactionQueue() {
        synchronized (transactionListeners) {
            for (AshListener listener : transactionListeners) {
                listener.transactionComplete();
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

    @Override
    public Future<EzspFrame> sendEzspRequestAsync(final EzspTransaction ezspTransaction) {
        if (closeHandler) {
            logger.debug("ASH: Handler is closed");
            return null;
        }

        class TransactionWaiter implements Callable<EzspFrame>, AshListener {
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

                return ezspTransaction.getResponse();
            }

            @Override
            public boolean transactionEvent(EzspFrameResponse ezspResponse) {
                if (ezspResponse.getSequenceNumber() == ezspTransaction.getRequest().getSequenceNumber()
                        && ezspResponse instanceof EzspInvalidCommandResponse) {
                    // NCP doesn't support this command!
                    transactionComplete();
                    return true;
                }

                // Check if this response completes our transaction
                if (!ezspTransaction.isMatch(ezspResponse)) {
                    return false;
                }

                transactionComplete();

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

        return executor.submit(new TransactionWaiter());
    }

    @Override
    public EzspTransaction sendEzspTransaction(EzspTransaction ezspTransaction) {
        return sendEzspTransaction(ezspTransaction, EZSP_TRANSACTION_TIMEOUT_SECONDS);
    }

    @Override
    public EzspTransaction sendEzspTransaction(EzspTransaction ezspTransaction, long timeout) {
        logger.debug("ASH TX EZSP: {}", ezspTransaction.getRequest());

        Future<EzspFrame> futureResponse = sendEzspRequestAsync(ezspTransaction);
        if (futureResponse == null) {
            logger.debug("ASH: Error sending EZSP transaction: Future is null");
            return null;
        }

        try {
            futureResponse.get(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            futureResponse.cancel(true);
            logger.debug("ASH interrupted in sendRequest while sending {}", ezspTransaction.getRequest());
        } catch (TimeoutException e) {
            futureResponse.cancel(true);
            logger.debug("Sending EZSP transaction timed out after {} seconds", timeout);
        }

        return ezspTransaction;
    }

    /**
     * Wait for the requested {@link EzspFrameResponse} to be received
     *
     * @param eventClass Request {@link EzspFrameResponse} to wait for
     * @return response {@link Future} {@link EzspFrameResponse}
     */
    @Override
    public Future<EzspFrameResponse> eventWaitAsync(final Class<?> eventClass) {
        class TransactionWaiter implements Callable<EzspFrameResponse>, AshListener {
            private boolean complete = false;
            private EzspFrameResponse receivedEvent = null;

            @Override
            public EzspFrameResponse call() {
                // Register a listener
                addTransactionListener(this);

                // Wait for the event
                synchronized (this) {
                    while (!complete) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            logger.debug("ASH interrupted in eventWaitAsync {}", eventClass);
                        }
                    }
                }

                // Remove the listener
                removeTransactionListener(this);

                return receivedEvent;
            }

            @Override
            public boolean transactionEvent(EzspFrameResponse ezspResponse) {
                // Check if this response completes our transaction
                if (ezspResponse.getClass() != eventClass) {
                    return false;
                }

                receivedEvent = ezspResponse;

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

        return executor.submit(new TransactionWaiter());
    }

    /**
     * Wait for the requested {@link EzspFrameResponse} to be received
     *
     * @param eventClass Request {@link EzspFrameResponse} to wait for
     * @param timeout the time in milliseconds to wait for the response
     * @return the {@link EzspFrameResponse} once received, or null on exception
     */
    @Override
    public EzspFrameResponse eventWait(final Class<?> eventClass, int timeout) {
        Future<EzspFrameResponse> future = eventWaitAsync(eventClass);
        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.debug("ASH interrupted in eventWait {}", eventClass);
            future.cancel(true);
            return null;
        }
    }

    @Override
    public synchronized Map<String, Long> getCounters() {
        Map<String, Long> counters = new ConcurrentHashMap<String, Long>();

        counters.put("ASH_TX_DAT", statsTxData);
        counters.put("ASH_TX_NAK", statsTxNaks);
        counters.put("ASH_TX_ACK", statsTxAcks);
        counters.put("ASH_RX_DAT", statsRxData);
        counters.put("ASH_RX_NAK", statsRxNaks);
        counters.put("ASH_RX_ACK", statsRxAcks);
        counters.put("ASH_RX_ERR", statsRxErrs);

        return counters;
    }

    private String frameToString(int[] inputBuffer) {
        if (inputBuffer == null || inputBuffer.length == 4) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < inputBuffer.length - 2; i++) {
            result.append(String.format("%02X ", inputBuffer[i]));
        }
        return result.toString();
    }

    interface AshListener {
        boolean transactionEvent(EzspFrameResponse ezspResponse);

        void transactionComplete();
    }
}
