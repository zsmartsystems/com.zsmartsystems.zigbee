package com.zsmartsystems.zigbee.dongle.ember.ash;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.ember.EzspFrameHandler;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame.ErrorCode;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame.FrameType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction.EzspTransaction;

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

    private final int ASH_MAX_LENGTH = 131;

    private Integer ackNum = 0;
    private int frmNum = 0;

    /**
     * The queue of {@link EzspFrameRequest} frames waiting to be sent
     */
    private final Queue<EzspFrameRequest> sendQueue = new LinkedList<EzspFrameRequest>();

    /**
     * The queue of {@link AshFrameData} frames that we have sent. These are kept in case a resend is required.
     */
    private final Queue<AshFrameData> sentQueue = new LinkedList<AshFrameData>();

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
     * The input stream.
     */
    // private final InputStream inputStream;

    /**
     * The output stream.
     */
    private final OutputStream outputStream;

    /**
     * The parser parserThread.
     */
    private Thread parserThread = null;

    /**
     * Flag reflecting that parser has been closed and parser parserThread
     * should exit.
     */
    private boolean close = false;

    /**
     * Construct which sets input stream where the packet is read from the and
     * handler which further processes the received packet.
     *
     * @param inputStream
     *            the input stream
     * @param outputStream
     *            the output stream
     * @param frameHandler
     *            the packet handler
     */
    public AshFrameHandler(final InputStream inputStream, final OutputStream outputStream,
            final EzspFrameHandler frameHandler) {
        this.outputStream = outputStream;
        // this.inputStream = inputStream;
        this.frameHandler = frameHandler;

        parserThread = new Thread("AshFrameHandler") {
            @Override
            public void run() {
                logger.trace("AshFrameHandler thread started");

                int exceptionCnt = 0;
                int[] inputBuffer = new int[ASH_MAX_LENGTH];
                int inputCount = 0;
                boolean inputError = false;

                while (!close) {
                    try {
                        int val = inputStream.read();
                        logger.trace("ASH RX: " + String.format("%02X", val));
                        if (val == ASH_CANCEL_BYTE) {
                            inputCount = 0;
                            inputError = false;

                            continue;
                        } else if (val == ASH_FLAG_BYTE) {
                            if (!inputError && inputCount != 0) {
                                AshFrame responseFrame = null;

                                final AshFrame packet = AshFrame.createFromInput(inputBuffer, inputCount);
                                if (packet == null) {
                                    logger.error("<-- RX ASH frame: BAD PACKET {}",
                                            AshFrame.frameToString(inputBuffer, inputCount));

                                    // Send a NAK
                                    responseFrame = new AshFrameNak(ackNum);
                                } else {
                                    logger.debug("<-- RX ASH frame: {}", packet.toString());

                                    // Reset the exception counter
                                    exceptionCnt = 0;

                                    // Extract the flags for DATA/ACK/NAK frames
                                    switch (packet.getFrameType()) {
                                        case DATA:
                                            // Always use the ackNum - even if this frame is discarded
                                            ackSentQueue(packet.getAckNum());

                                            // Check for out of sequence frame number
                                            if (packet.getFrmNum() != ackNum) {
                                                // Send a NAK
                                                responseFrame = new AshFrameNak(ackNum);
                                            } else {
                                                // Frame was in sequence

                                                // Get the EZSP frame
                                                EzspFrameResponse response = EzspFrame
                                                        .createHandler((AshFrameData) packet);
                                                logger.debug("RX EZSP: " + response);
                                                if (response == null) {
                                                    logger.debug("No frame handler created for {}", packet);
                                                } else if (response != null && !notifyTransactionComplete(response)) {
                                                    // No transactions owned this response, so we pass it to
                                                    // our unhandled response handler
                                                    EzspFrame ezspFrame = EzspFrame
                                                            .createHandler(((AshFrameData) packet));
                                                    if (ezspFrame != null) {
                                                        frameHandler.handlePacket(ezspFrame);
                                                    }
                                                }

                                                // Update our next expected data frame
                                                ackNum = (ackNum + 1) & 0x07;

                                                responseFrame = new AshFrameAck(ackNum);
                                            }
                                            break;
                                        case ACK:
                                            ackSentQueue(packet.getAckNum());
                                            break;
                                        case NAK:
                                            sendRetry();
                                            break;
                                        case RSTACK:
                                            AshFrameRstAck rstAck = (AshFrameRstAck) packet;
                                            // Make sure this is a software reset.
                                            // This avoids us reacting to a HW reset before our SW ack
                                            if (rstAck.getResetType() != ErrorCode.SOFTWARE) {
                                                break;
                                            }

                                            // Check the version
                                            if (rstAck.getVersion() == 2) {
                                                stateConnected = true;
                                                ackNum = 0;
                                                frmNum = 0;
                                                sentQueue.clear();
                                                logger.debug("ASH: Connected");
                                            } else {
                                                logger.debug("Invalid ASH version");
                                            }
                                            break;
                                        default:
                                            break;
                                    }
                                }

                                // Send the response
                                if (responseFrame != null) {
                                    sendFrame(responseFrame);
                                }

                                // Send the next frame
                                sendNextFrame();
                            }
                            inputCount = 0;
                            inputError = false;
                        } else if (val != -1) {
                            if (inputCount >= ASH_MAX_LENGTH) {
                                inputCount = 0;
                                inputError = true;
                            }
                            inputBuffer[inputCount++] = val;
                        }
                    } catch (final IOException e) {
                        logger.error("AshFrameHandler IOException: ", e);

                        if (exceptionCnt++ > 10) {
                            logger.error("AshFrameHandler exception count exceeded");
                            // if (!close) {
                            // frameHandler.error(e);
                            close = true;
                        }
                    }
                }
                logger.debug("AshFrameHandler exited.");
            }
        };

        parserThread.setDaemon(true);
        parserThread.start();
    }

    /**
     * Set the close flag to true.
     */
    public void setClosing() {
        this.close = true;
    }

    /**
     * Requests parser thread to shutdown.
     */
    public void close() {
        this.close = true;
        try {
            parserThread.interrupt();
            parserThread.join();
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
    private synchronized void sendNextFrame() {
        // We're not allowed to send if we're not connected
        if (!stateConnected) {
            logger.warn("Trying to send when not connected.");
            return;
        }

        // Check how many frames are outstanding
        if (sentQueue.size() >= TX_WINDOW) {
            logger.debug("Sent queue larger than window [{} > {}].", sentQueue.size(), TX_WINDOW);
            return;
        }

        EzspFrameRequest nextFrame = sendQueue.poll();
        if (nextFrame == null) {
            // Nothing to send
            return;
        }

        // Encapsulate the EZSP frame into the ASH packet
        logger.debug("TX EZSP: {}", nextFrame);
        AshFrameData ashFrame = new AshFrameData(nextFrame);

        sendFrame(ashFrame);
    }

    private synchronized void sendFrame(AshFrame ashFrame) {
        if (ashFrame.getFrameType() == FrameType.DATA) {
            // Set the frame number
            ((AshFrameData) ashFrame).setFrmNum(frmNum);
            frmNum = (frmNum + 1) & 0x07;

            // DATA frames need to go into a sent queue so we can retry if needed
            sentQueue.add((AshFrameData) ashFrame);
        }

        outputFrame(ashFrame);
    }

    private void sendRetry() {
        AshFrameData ashFrame = sentQueue.peek();
        if (ashFrame == null) {
            logger.debug("Retry, but nothing to resend!");
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
        try {
            for (int b : ashFrame.getOutputBuffer()) {
                // result.append(" ");
                // result.append(String.format("%02X", b));
                // logger.debug("ASH TX: " + String.format("%02X", b));
                outputStream.write(b);
            }
        } catch (IOException e) {
            logger.debug(e.getMessage());
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
     * @param transaction
     *            {@link EzspFrameRequest}
     */
    public void queueFrame(EzspFrameRequest request) {
        sendQueue.add(request);

        logger.debug("TX EZSP queue: {}", sendQueue.size());

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

    /**
     * Acknowledge frames we've sent and removes the from the sent queue.
     * This method is called for each DATA or ACK frame where we have the 'ack' property.
     *
     * @param ackNum
     *            the last ack from the NCP
     */
    private void ackSentQueue(int ackNum) {
        // Handle the timer if it's running
        if (sentTime != 0) {
            resetRetryTimer();
            receiveTimeout = (int) ((receiveTimeout * 7 / 8) + ((System.nanoTime() - sentTime) / 2000000));
            if (receiveTimeout < T_RX_ACK_MIN) {
                receiveTimeout = T_RX_ACK_MIN;
            } else if (receiveTimeout > T_RX_ACK_MAX) {
                receiveTimeout = T_RX_ACK_MAX;
            }
            logger.trace("ASH RX Timer: took {}ms, timer now {}ms", (System.nanoTime() - sentTime) / 1000000,
                    receiveTimeout);
            sentTime = 0;
        }

        while (sentQueue.peek() != null && sentQueue.peek().getFrmNum() != ackNum) {
            if (sentQueue.poll() == null) {
                logger.debug("Error: nothing to remove from sent queue [{} -- {}]", this.ackNum, ackNum);
            }
            logger.trace("Frame acked and removed");
        }
    }

    private synchronized void startRetryTimer() {
        // Stop any existing timer
        resetRetryTimer();

        // Create the timer task
        timerTask = new AshRetryTimer();
        timer.schedule(timerTask, receiveTimeout);
    }

    private synchronized void resetRetryTimer() {
        // Stop any existing timer
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private class AshRetryTimer extends TimerTask {
        // private final Logger logger =
        // LoggerFactory.getLogger(ZWaveTransactionTimer.class);

        @Override
        public void run() {
            // Resend the first message in the sentQueue
            if (sentQueue.isEmpty()) {
                return;
            }

            if (retries++ > ACK_TIMEOUTS) {
                // Too many retries.
                // TODO: We probably should alert the upper layer so they can reset the link?
                frameHandler.handleLinkStateChange(false);

                logger.debug("Error: number of retries exceeded [{}].", retries);
            }

            sendRetry();
        }
    }

    /**
     * Notify any transaction listeners when we receive a response.
     *
     * @param response
     *            the response data received
     * @return true if the response was processed
     */
    private boolean notifyTransactionComplete(final EzspFrameResponse response) {
        boolean processed = false;

        // logger.debug("NODE {}: notifyTransactionResponse {}",
        // transaction.getNodeId(), transaction.getTransactionId());
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
     * @param ezspTransaction
     *            Request {@link EzspTransaction}
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
                            logger.debug(e.getMessage());
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
     * @param ezspRequest
     *            Request {@link EzspFrame}
     * @return response {@link EzspFrame}
     */
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
            logger.debug("Error sending EZSP transaction to listeners: ", e);
        }

        return null;
    }

    interface AshListener {
        boolean transactionEvent(EzspFrameResponse ezspResponse);
    }
}
