/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeDeviceState;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrame;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrameRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrameResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeQuerySendDataRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeReadReceivedDataRequest;
import com.zsmartsystems.zigbee.dongle.conbee.transaction.ConBeeTransaction;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Frame parser for ConBee SLIP protocol.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeFrameHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ConBeeFrameHandler.class);

    private final static int SLIP_ESC = 0xDB;
    private final static int SLIP_ESC_END = 0xDC;
    private final static int SLIP_ESC_ESC = 0xDD;
    private final static int SLIP_END = 0xC0;

    private final int SLIP_MAX_LENGTH = 84;

    private final static AtomicInteger callbackSequence = new AtomicInteger(1);

    private int receiveTimeout = 175;
    private final Timer timer = new Timer();
    private TimerTask timerTask = null;

    /**
     * The queue of {@link ConBeeFrameRequest} frames waiting to be sent
     */
    private final BlockingQueue<ConBeeFrameRequest> sendQueue = new ArrayBlockingQueue<ConBeeFrameRequest>(10);

    private ExecutorService executor = Executors.newCachedThreadPool();
    private final List<ConBeeListener> transactionListeners = new ArrayList<ConBeeListener>();

    /**
     * The port.
     */
    private ZigBeePort serialPort;

    /**
     * The receive thread.
     */
    private Thread receiveThread = null;

    /**
     * The transmit thread.
     */
    private Thread transmitThread = null;

    private ConBeeFrameRequest outstandingRequest = null;

    private Object transmitSync = new Object();

    /**
     * Flag reflecting that parser has been closed and parser parserThread
     * should exit.
     */
    private boolean close = false;

    /**
     * Construct which sets input stream where the packet is read from the and
     * handler which further processes the received packet.
     *
     * @param serialPort the {@link ZigBeePort}
     * @param frameHandler the packet handler
     */
    public ConBeeFrameHandler(final ZigBeePort serialPort, final ZigBeeDongleConBee dongle) {
        this.serialPort = serialPort;

        transmitThread = new Thread("ConBeeTransmitHandler") {
            @Override
            public void run() {
                logger.debug("ConBeeTransmitHandler thread started");

                while (!close) {
                    try {
                        synchronized (transmitSync) {
                            // logger.debug("ConBeeTransmitHandler thread WAIT");
                            // Wait until we're allowed to send
                            if (outstandingRequest != null) {
                                // Wait will block here until we receive the response to the last frame we sent
                                transmitSync.wait(250);
                                if (outstandingRequest != null) {
                                    logger.debug("ConBeeTransmitHandler thread STILL OUTSTANDING");
                                    continue;
                                }
                            }
                            // logger.debug("ConBeeTransmitHandler thread GOOD TO GO");

                            // Now wait until there's something to send
                            ConBeeFrameRequest request = sendQueue.poll(250, TimeUnit.MILLISECONDS);
                            if (request != null) {
                                // logger.debug("ConBeeTransmitHandler thread WE'RE OFF");
                                outstandingRequest = request;
                                outputFrame(request);
                            }
                        }
                    } catch (InterruptedException e) {
                        logger.debug("Transmit thread interrupted: ", e);
                    }
                }
                logger.debug("ConBeeTransmitHandler exited.");
            }
        };

        receiveThread = new Thread("ConBeeReceiveHandler") {
            @Override
            public void run() {
                logger.debug("ConBeeReceiveHandler thread started");

                int[] inputBuffer = new int[SLIP_MAX_LENGTH];
                int inputCount = 0;
                boolean inputError = false;

                boolean escaped = false;

                while (!close) {
                    int val = serialPort.read();
                    // logger.debug("CONBEE RX: " + String.format("[% 2d] %02X", inputCount, val));
                    if (val == SLIP_ESC) {
                        escaped = true;
                    } else if (val == SLIP_END) {
                        // Frame complete
                        if (inputCount > 0) {
                            ConBeeFrameResponse frame = (ConBeeFrameResponse) ConBeeFrame
                                    .create(Arrays.copyOfRange(inputBuffer, 0, inputCount));
                            if (frame != null) {
                                logger.debug("CONBEE RX Frame: {}", frame);
                                dongle.receiveIncomingFrame(frame);
                                notifyTransactionComplete(frame);
                                resetRetryTimer();

                                // Check the device state
                                handleConBeeState(frame.getDeviceState());
                            } else {
                                logger.debug("CONBEE RX Frame: ERROR: [{}] {}", inputCount,
                                        bufferToString(inputBuffer, inputCount));
                            }
                            inputCount = 0;
                        }
                    } else if (escaped) {
                        escaped = false;
                        switch (val) {
                            case SLIP_ESC_END:
                                inputBuffer[inputCount++] = SLIP_END;
                                break;
                            case SLIP_ESC_ESC:
                                inputBuffer[inputCount++] = SLIP_ESC;
                                break;
                            default:
                                inputBuffer[inputCount++] = val;
                                break;
                        }
                    } else if (val != -1) {
                        if (inputCount >= SLIP_MAX_LENGTH) {
                            logger.debug("CONBEE RX error: len=" + inputCount);
                            inputCount = 0;
                            inputError = true;
                        }
                        inputBuffer[inputCount++] = val;
                    }
                }
                logger.debug("ConBeeFrameHandler exited.");
            }
        };

        receiveThread.setDaemon(true);
        receiveThread.start();

        transmitThread.setDaemon(true);
        transmitThread.start();
    }

    /**
     * Process a received device state update
     * <p>
     * This method will perform any polling etc as required to fulfill any device requests
     *
     * @param deviceState the latest {@link ConBeeDeviceState}
     */
    protected void handleConBeeState(ConBeeDeviceState deviceState) {
        if (deviceState.isDataIndication()) {
            // There is data available to be read
            ConBeeReadReceivedDataRequest readRequest = new ConBeeReadReceivedDataRequest();
            readRequest.setSequence(callbackSequence.getAndIncrement());
            outputFrame(readRequest);
        } else if (deviceState.isDataConfirm()) {
            // Check the state
            ConBeeQuerySendDataRequest queryRequest = new ConBeeQuerySendDataRequest();
            queryRequest.setSequence(callbackSequence.getAndIncrement());
            outputFrame(queryRequest);
        }
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
            receiveThread.interrupt();
            receiveThread.join();
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
        return receiveThread != null && receiveThread.isAlive();
    }

    // Synchronize this method to ensure a packet gets sent as a block
    private synchronized void outputFrame(ConBeeFrameRequest frame) {
        StringBuilder result = new StringBuilder();
        // Send the data
        logger.debug("CONBEE TX: {}", frame);
        serialPort.write(SLIP_END);

        for (int val : frame.getOutputBuffer()) {
            result.append(String.format(" %02X", val));
            // logger.debug("CONBEE TX: {}", String.format("%02X", val));
            switch (val) {
                case SLIP_END:
                    logger.debug("CONBEE TX: [ESC]");
                    serialPort.write(SLIP_ESC);
                    serialPort.write(SLIP_ESC_END);
                    break;
                case SLIP_ESC:
                    logger.debug("CONBEE TX: [ESC]");
                    serialPort.write(SLIP_ESC);
                    serialPort.write(SLIP_ESC_ESC);
                    break;
                default:
                    serialPort.write(val);
                    break;
            }
        }

        logger.debug("CONBEE TX:{}", result.toString());

        serialPort.write(SLIP_END);

        startRetryTimer();
    }

    private synchronized void startRetryTimer() {
        // Stop any existing timer
        resetRetryTimer();

        // Create the timer task
        timerTask = new ConBeeRetryTimer();
        timer.schedule(timerTask, receiveTimeout);
    }

    private synchronized void resetRetryTimer() {
        // Stop any existing timer
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
    }

    private class ConBeeRetryTimer extends TimerTask {
        @Override
        public void run() {
            logger.debug("CONBEE TX: TIMEOUT");

            // TODO: Retransmit?

            synchronized (transmitSync) {
                outstandingRequest = null;
                transmitSync.notify();
            }
        }
    }

    /**
     * Add a frame to the send queue. The sendQueue is a FIFO queue.
     * This method queues a {@link ConBeeFrame} frame without waiting for a response and
     * no transaction management is performed.
     *
     * @param request
     *            {@link ConBeeFrameRequest}
     */
    public synchronized void queueFrame(ConBeeFrameRequest request) {
        request.setSequence(callbackSequence.getAndIncrement());
        if (request.getSequence() == 255) {
            callbackSequence.set(1);
        }

        sendQueue.add(request);

        logger.debug("TX CONBEE queue: {}", sendQueue.size());
    }

    /**
     * Notify any transaction listeners when we receive a response.
     *
     * @param response
     *            the response data received
     * @return true if the response was processed
     */
    private boolean notifyTransactionComplete(final ConBeeFrameResponse response) {
        boolean processed = false;

        // logger.debug("NODE {}: notifyTransactionResponse {}",
        // transaction.getNodeId(), transaction.getTransactionId());
        synchronized (transactionListeners) {
            for (ConBeeListener listener : transactionListeners) {
                if (listener.transactionEvent(response)) {
                    processed = true;
                }
            }
        }

        synchronized (transmitSync) {
            if (outstandingRequest != null && outstandingRequest.getSequence() == response.getSequence()) {
                resetRetryTimer();
                outstandingRequest = null;
                transmitSync.notify();
            }
        }

        return processed;
    }

    private void addTransactionListener(ConBeeListener listener) {
        synchronized (transactionListeners) {
            if (transactionListeners.contains(listener)) {
                return;
            }

            transactionListeners.add(listener);
        }
    }

    private void removeTransactionListener(ConBeeListener listener) {
        synchronized (transactionListeners) {
            transactionListeners.remove(listener);
        }
    }

    /**
     * Sends an ConBee request to the NCP without waiting for the response.
     *
     * @param transaction
     *            Request {@link ConBeeTransaction}
     * @return response {@link Future} {@link ConBeeFrame}
     */
    public Future<ConBeeFrame> sendRequestAsync(final ConBeeTransaction transaction) {
        class TransactionWaiter implements Callable<ConBeeFrame>, ConBeeListener {
            private boolean complete = false;

            @Override
            public ConBeeFrame call() {
                // Register a listener
                addTransactionListener(this);

                // Send the transaction
                queueFrame(transaction.getRequest());

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
            public boolean transactionEvent(ConBeeFrameResponse response) {
                // Check if this response completes our transaction
                if (!transaction.isMatch(response)) {
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

        Callable<ConBeeFrame> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

    /**
     * Sends a ConBee request to the NCP and waits for the response. The response is correlated with the request and the
     * returned {@link ConBeeFrame} contains the request and response data.
     *
     * @param transaction
     *            Request {@link ConBeeTransaction}
     * @return response {@link ConBeeFrame}
     */
    public ConBeeTransaction sendTransaction(ConBeeTransaction transaction) {
        Future<ConBeeFrame> futureResponse = sendRequestAsync(transaction);
        if (futureResponse == null) {
            logger.debug("Error sending ConBee transaction: Future is null");
            return null;
        }

        try {
            futureResponse.get();
            return transaction;
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("Error sending ConBee transaction to listeners: ", e);
        }

        return null;
    }

    interface ConBeeListener {
        boolean transactionEvent(ConBeeFrameResponse response);
    }

    private String bufferToString(final int[] buffer, final int length) {
        StringBuilder builder = new StringBuilder();
        boolean first = true;
        for (int cnt = 0; cnt < length; cnt++) {
            if (first == false) {
                builder.append(' ');
            }
            first = false;
            builder.append(String.format("%02X", buffer[cnt]));
        }
        return builder.toString();
    }
}
