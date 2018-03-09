/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeEvent;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeFrame;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeResponse;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Frame parser for the XBee API command protocol.
 *
 * @author Chris Jackson
 *
 */
public class XBeeFrameHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(XBeeFrameHandler.class);

    /**
     * The queue of {@link XBeeFrame} frames waiting to be sent
     */
    private final Queue<XBeeCommand> sendQueue = new ConcurrentLinkedQueue<XBeeCommand>();

    private ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * List of listeners waiting for commands to complete
     */
    private final List<XBeeListener> transactionListeners = new ArrayList<XBeeListener>();

    /**
     * List of event listeners
     */
    private final List<XBeeEventListener> eventListeners = new ArrayList<XBeeEventListener>();

    /**
     * The port.
     */
    private ZigBeePort serialPort;

    /**
     * The parser parserThread.
     */
    private Thread parserThread = null;

    /**
     * Object to synchronise access to sentCommand
     */
    private Object commandLock = new Object();

    /**
     * The command that we're currently sending, or null if no command is being transacted
     */
    private XBeeCommand sentCommand = null;

    /**
     * Flag reflecting that parser has been closed and parser parserThread should exit.
     */
    private boolean closeHandler = false;

    /**
     * Scheduler used as a timer to abort any transactions that don't complete in a timely manner
     */
    private ScheduledExecutorService timeoutScheduler;

    /**
     * The future be used for timeouts
     */
    private ScheduledFuture<?> timeoutTimer = null;

    /**
     * Frame ID counter
     */
    private final AtomicInteger frameId = new AtomicInteger();

    /**
     * The maximum number of milliseconds to wait for the response from the stick once the request was sent
     */
    private final int DEFAULT_TRANSACTION_TIMEOUT = 500;

    /**
     * The maximum number of milliseconds to wait for the completion of the transaction after it's queued
     */
    private final int DEFAULT_COMMAND_TIMEOUT = 10000;

    /**
     * The maximum number of milliseconds to wait for the response from the stick once the request was sent
     */
    private int transactionTimeout = DEFAULT_TRANSACTION_TIMEOUT;

    /**
     * The maximum number of milliseconds to wait for the completion of the transaction after it's queued
     */
    private int commandTimeout = DEFAULT_COMMAND_TIMEOUT;

    enum RxStateMachine {
        WAITING,
        RECEIVE_LEN1,
        RECEIVE_LEN2,
        RECEIVE_DATA
    }

    private final int XBEE_FLAG = 0x7E;
    private final int XBEE_ESCAPE = 0x7D;
    private final int XBEE_XOR = 0x20;
    private final int XBEE_XON = 0x11;
    private final int XBEE_XOFF = 0x13;

    private final List<Integer> escapeCodes = Arrays
            .asList(new Integer[] { XBEE_FLAG, XBEE_ESCAPE, XBEE_XON, XBEE_XOFF });

    /**
     * Construct which sets input stream where the packet is read from the and
     * handler which further processes the received packet.
     *
     * @param serialPort the {@link ZigBeePort}
     */
    public void start(final ZigBeePort serialPort) {
        frameId.set(1);

        this.serialPort = serialPort;
        this.timeoutScheduler = Executors.newSingleThreadScheduledExecutor();

        parserThread = new Thread("XBeeFrameHandler") {
            @Override
            public void run() {
                logger.debug("XBeeFrameHandler thread started");

                while (!closeHandler) {
                    try {
                        synchronized (commandLock) {
                            if (sentCommand == null) {
                                sendNextFrame();
                            }
                        }

                        // Get a packet from the serial port
                        int[] responseData = getPacket();
                        if (responseData == null) {
                            continue;
                        }

                        StringBuilder builder = new StringBuilder();
                        for (int value : responseData) {
                            builder.append(String.format(" %02X", value));
                        }
                        logger.debug("RX XBEE Data:{}", builder.toString());

                        // Use the Event Factory to get an event
                        XBeeEvent event = XBeeEventFactory.getXBeeFrame(responseData);
                        if (event != null) {
                            notifyEventReceived(event);
                        }

                        // Use the Response Factory to get a response
                        XBeeResponse response = XBeeResponseFactory.getXBeeFrame(responseData);
                        if (response != null && notifyResponseReceived(response)) {
                            sentCommand = null;
                        }
                    } catch (Exception e) {
                        logger.error("XBeeFrameHandler exception", e);
                    }
                }
                logger.debug("XBeeFrameHandler thread exited.");
            }
        };

        parserThread.setDaemon(true);
        parserThread.start();
    }

    private int[] getPacket() {
        int[] inputBuffer = new int[120];
        int inputBufferLength = 0;
        RxStateMachine rxState = RxStateMachine.WAITING;
        int length = 0;
        int cnt = 0;
        int checksum = 0;
        boolean escaped = false;

        logger.trace("XBEE: Get Packet");
        while (!closeHandler) {
            int val = serialPort.read();
            if (val == -1) {
                // Timeout
                continue;
            }
            if (inputBufferLength >= inputBuffer.length) {
                // If we overrun the buffer, reset and go to WAITING mode
                inputBufferLength = 0;
                rxState = RxStateMachine.WAITING;
                logger.debug("XBEE RX buffer overrun - resetting!");
            }

            logger.trace("RX XBEE: {}", String.format("%02X %c", val, val));

            if (escaped) {
                escaped = false;
                val = val ^ XBEE_XOR;
            } else if (val == XBEE_ESCAPE) {
                escaped = true;
                continue;
            }

            switch (rxState) {
                case WAITING:
                    if (val == XBEE_FLAG) {
                        rxState = RxStateMachine.RECEIVE_LEN1;
                    }
                    continue;
                case RECEIVE_LEN1:
                    inputBuffer[cnt++] = val;
                    rxState = RxStateMachine.RECEIVE_LEN2;
                    length = val << 8;
                    break;
                case RECEIVE_LEN2:
                    inputBuffer[cnt++] = val;
                    rxState = RxStateMachine.RECEIVE_DATA;
                    length += val + 3;
                    if (length > inputBuffer.length) {
                        // Return null and let the system resync by searching for the next FLAG
                        logger.debug("XBEE RX length too long ({}) - ignoring!", length);
                        return null;
                    }
                    break;
                case RECEIVE_DATA:
                    checksum += val;
                    inputBuffer[cnt++] = val;
                    break;
                default:
                    break;
            }

            if (cnt == length) {
                if ((checksum & 0xff) == 255) {
                    return Arrays.copyOfRange(inputBuffer, 0, length);
                } else {
                    return null;
                }
            }
        }

        return null;
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
        try {
            parserThread.interrupt();
            parserThread.join();
        } catch (InterruptedException e) {
            logger.debug("Interrupted in packet parser thread shutdown join.");
        }
        logger.debug("XBeeFrameHandler closed.");
    }

    /**
     * Checks if parser thread is alive.
     *
     * @return true if parser thread is alive.
     */
    public boolean isAlive() {
        return parserThread != null && parserThread.isAlive();
    }

    private void sendNextFrame() {
        synchronized (commandLock) {
            // Are we already processing a command?
            if (sentCommand != null) {
                logger.trace("TX XBEE Frame outstanding: {}", sentCommand);
                return;
            }

            XBeeCommand nextFrame = sendQueue.poll();
            if (nextFrame == null) {
                logger.trace("XBEE TX: Nothing to send");
                // Nothing to send
                stopTimer();
                return;
            }

            logger.debug("TX XBEE: {}", nextFrame);

            // Remember the command we're processing
            sentCommand = nextFrame;

            serialPort.write(XBEE_FLAG);

            // Send the data
            StringBuilder builder = new StringBuilder();
            for (int sendByte : nextFrame.serialize()) {
                builder.append(String.format(" %02X", sendByte));
                if (escapeCodes.contains(sendByte)) {
                    serialPort.write(XBEE_ESCAPE);
                    serialPort.write(sendByte ^ XBEE_XOR);
                } else {
                    serialPort.write(sendByte);
                }
            }
            logger.debug("TX XBEE Data:{}", builder.toString());

            // Start the timeout
            startTimer();
        }
    }

    /**
     * Add a XBee command frame to the send queue. The sendQueue is a FIFO queue.
     * This method queues a {@link XBeeCommand} frame without waiting for a response.
     *
     * @param request {@link XBeeFrame}
     */
    private void queueFrame(XBeeCommand request) {
        sendQueue.add(request);

        logger.debug("TX XBEE queue: {}: {}", sendQueue.size(), request);

        sendNextFrame();
    }

    /**
     * Notify any transaction listeners when we receive a response.
     *
     * @param response the {@link XBeeEvent} data received
     * @return true if the response was processed
     */
    private boolean notifyResponseReceived(final XBeeResponse response) {
        boolean processed = false;

        logger.debug("RX XBEE: {}", response.toString());
        synchronized (transactionListeners) {
            for (XBeeListener listener : transactionListeners) {
                try {
                    if (listener.transactionEvent(response)) {
                        processed = true;
                    }
                } catch (Exception e) {
                    logger.debug("Exception processing XBee frame: {}: ", response, e);
                }
            }
        }

        return processed;
    }

    /**
     * Sets the command timeout. This is the number of milliseconds to wait for a response from the stick once the
     * command has been sent.
     *
     * @param commandTimeout
     */
    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    /**
     * Sets the transaction timeout. This is the number of milliseconds to wait for a response from the stick once the
     * command has been initially queued.
     *
     * @param commandTimeout
     */
    public void setTransactionTimeout(int transactionTimeout) {
        this.transactionTimeout = transactionTimeout;
    }

    private void addTransactionListener(XBeeListener listener) {
        synchronized (transactionListeners) {
            if (transactionListeners.contains(listener)) {
                return;
            }

            transactionListeners.add(listener);
        }
    }

    private void removeTransactionListener(XBeeListener listener) {
        synchronized (transactionListeners) {
            transactionListeners.remove(listener);
        }
    }

    /**
     * Notify any event listeners when we receive an event.
     *
     * @param event the {@link XBeeEvent} received
     */
    private void notifyEventReceived(final XBeeEvent event) {
        logger.debug("RX XBEE: {}", event.toString());
        synchronized (eventListeners) {
            for (XBeeEventListener listener : eventListeners) {
                try {
                    listener.xbeeEventReceived(event);
                } catch (Exception e) {
                    logger.debug("Exception processing XBee frame: {}: ", event, e);
                }
            }
        }
    }

    public void addEventListener(XBeeEventListener listener) {
        synchronized (eventListeners) {
            if (eventListeners.contains(listener)) {
                return;
            }

            eventListeners.add(listener);
        }
    }

    public void removeEventListener(XBeeEventListener listener) {
        synchronized (eventListeners) {
            eventListeners.remove(listener);
        }
    }

    /**
     * Sends a XBee request to the NCP without waiting for the response.
     *
     * @param command Request {@link XBeeCommand} to send
     * @return response {@link Future} {@link XBeeResponse}
     */
    public Future<XBeeResponse> sendRequestAsync(final XBeeCommand command) {
        class TransactionWaiter implements Callable<XBeeResponse>, XBeeListener {
            private boolean complete = false;
            private XBeeResponse completionResponse = null;

            private int ourFrameId = 0;

            @Override
            public XBeeResponse call() {
                // Register a listener
                addTransactionListener(this);

                synchronized (frameId) {
                    ourFrameId = frameId.getAndIncrement();
                    command.setFrameId(ourFrameId);
                    if (frameId.get() == 256) {
                        frameId.set(1);
                    }
                }

                // Send the transaction
                queueFrame(command);

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

                return completionResponse;
            }

            @Override
            public boolean transactionEvent(XBeeResponse response) {
                // Check if this response completes our transaction
                if (response.getFrameId() != ourFrameId) {
                    return false;
                }

                completionResponse = response;
                complete = true;
                synchronized (this) {
                    notify();
                }

                return true;
            }
        }

        Callable<XBeeResponse> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

    /**
     * Sends a XBee request to the dongle and waits for the response. The response is correlated with the request
     * and the response data is returned as a {@link XBeeEvent}.
     *
     * @param command Request {@link XBeeCommand}
     * @return response {@link XBeeResponse} the response, or null if there was a timeout
     */
    public XBeeResponse sendRequest(final XBeeCommand command) {
        Future<XBeeResponse> future = sendRequestAsync(command);
        try {
            return future.get(transactionTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.debug("XBee interrupted in sendRequest {}", command);
            future.cancel(true);
            return null;
        }
    }

    /**
     * Sends a XBee request to the NCP without waiting for the response.
     *
     * @param command Request {@link XBeeCommand} to send
     * @return response {@link Future} {@link XBeeCommand}
     */
    private Future<XBeeEvent> waitEventAsync(final Class<?> eventClass) {
        class TransactionWaiter implements Callable<XBeeEvent>, XBeeEventListener {
            private boolean complete = false;
            private XBeeEvent receivedEvent = null;

            @Override
            public XBeeEvent call() {
                // Register a listener
                addEventListener(this);

                // Wait for the event
                synchronized (this) {
                    while (!complete) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            logger.debug("XBee interrupted in waitEventAsync {}", eventClass);
                        }
                    }
                }

                // Remove the listener
                removeEventListener(this);

                return receivedEvent;
            }

            @Override
            public void xbeeEventReceived(XBeeEvent event) {
                // Check if this response completes our transaction
                if (event.getClass() != eventClass) {
                    return;
                }

                receivedEvent = event;

                complete = true;
                synchronized (this) {
                    notify();
                }
            }
        }

        Callable<XBeeEvent> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

    /**
     * Wait for the requested {@link XBeeEvent} to occur
     *
     * @param eventClass the {@link XBeeEvent} to wait for
     * @return the {@link XBeeEvent} once received, or null on exception
     */
    public XBeeEvent eventWait(final Class<?> eventClass) {
        Future<XBeeEvent> future = waitEventAsync(eventClass);
        try {
            return future.get(commandTimeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            logger.debug("XBee interrupted in eventWait {}", eventClass);
            future.cancel(true);
            return null;
        }
    }

    /**
     * Starts the transaction timeout. This will simply cancel the transaction and send the next frame from the queue if
     * the timer times out. We don't try and retry as this might cause other unwanted issues.
     */
    private void startTimer() {
        stopTimer();
        logger.trace("XBEE Timer: Start");
        timeoutTimer = timeoutScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                timeoutTimer = null;
                logger.debug("XBEE Timer: Timeout");
                synchronized (commandLock) {
                    if (sentCommand != null) {
                        sentCommand = null;
                        sendNextFrame();
                    }
                }
            }
        }, commandTimeout, TimeUnit.MILLISECONDS);
    }

    private void stopTimer() {
        if (timeoutTimer != null) {
            logger.trace("XBEE Timer: Stop");
            timeoutTimer.cancel(false);
            timeoutTimer = null;
        }
    }

    interface XBeeListener {
        boolean transactionEvent(XBeeResponse response);
    }
}
