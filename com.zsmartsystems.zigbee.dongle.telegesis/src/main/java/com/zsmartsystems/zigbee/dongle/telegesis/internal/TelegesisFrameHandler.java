/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisCommand;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisFrame;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisStatusCode;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Frame parser for the Telegesis AT command protocol.
 *
 * @author Chris Jackson
 *
 */
public class TelegesisFrameHandler {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(TelegesisFrameHandler.class);

    /**
     * The queue of {@link TelegesisFrame} frames waiting to be sent
     */
    private final Queue<TelegesisCommand> sendQueue = new LinkedList<TelegesisCommand>();

    private ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * List of listeners waiting for commands to complete
     */
    private final List<TelegesisListener> transactionListeners = new ArrayList<TelegesisListener>();

    /**
     * List of event listeners
     */
    private final List<TelegesisEventListener> eventListeners = new ArrayList<TelegesisEventListener>();

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
    private TelegesisCommand sentCommand = null;

    /**
     * Flag reflecting that parser has been closed and parser parserThread should exit.
     */
    private boolean closeHandler = false;

    enum RxStateMachine {
        WAITING,
        RECEIVE_CMD,
        RECEIVE_ASCII,
        RECEIVE_BINARY
    }

    /**
     * Construct which sets input stream where the packet is read from the and
     * handler which further processes the received packet.
     *
     * @param serialPort the {@link ZigBeePort}
     */
    public void start(final ZigBeePort serialPort) {

        this.serialPort = serialPort;

        parserThread = new Thread("TelegesisFrameHandler") {
            @Override
            public void run() {
                logger.debug("TelegesisFrameHandler thread started");

                while (!closeHandler) {
                    // Get a packet from the serial port
                    int[] responseData = getPacket();
                    if (responseData == null) {
                        continue;
                    }

                    StringBuilder builder = new StringBuilder();
                    builder.append("Data");
                    for (int value : responseData) {
                        builder.append(String.format(" %02X", value));
                    }
                    logger.debug("TELEGESIS RX: {}", builder.toString());

                    // Use the Event Factory to get an event
                    TelegesisEvent event = TelegesisEventFactory.getTelegesisFrame(responseData);
                    if (event != null) {
                        notifyEventReceived(event);
                        continue;
                    }

                    // If we're sending a command, then we need to process any responses
                    synchronized (commandLock) {
                        if (sentCommand != null && sentCommand.deserialize(responseData)) {
                            // Command completed
                            notifyTransactionComplete(sentCommand);
                            sentCommand = null;
                            sendNextFrame();
                        }
                    }

                }
                logger.debug("TelegesisFrameHandler thread exited.");
            }
        };

        parserThread.setDaemon(true);
        parserThread.start();
    }

    private int[] getPacket() {
        int[] inputBuffer = new int[120];
        int inputBufferLength = 0;
        RxStateMachine rxState = RxStateMachine.WAITING;
        int binaryLength = 0;

        logger.trace("TELEGESIS: Get Packet");
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
                logger.debug("TELEGESIS RX buffer overrun - resetting!");
            }

            logger.trace("TELEGESIS RX: {}", String.format("%02X %c", val, val));

            switch (rxState) {
                case WAITING:
                    // Ignore any CR and LF
                    if (val == '\n' || val == '\r') {
                        continue;
                    }

                    inputBuffer[inputBufferLength++] = val;
                    rxState = RxStateMachine.RECEIVE_CMD;
                    break;
                case RECEIVE_CMD:
                    if (val == '\n' || val == '\r') {
                        // We're done
                        // Return the packet without the CRLF on the end
                        return Arrays.copyOfRange(inputBuffer, 0, inputBufferLength);
                    }

                    // Here we wait for the colon
                    // This allows us to avoid switching into binary mode for ATS responses
                    inputBuffer[inputBufferLength++] = val;
                    if (val == ':') {
                        rxState = RxStateMachine.RECEIVE_ASCII;
                    }
                    break;
                case RECEIVE_ASCII:
                    if (val == '\n' || val == '\r') {
                        // We're done
                        // Return the packet without the CRLF on the end
                        return Arrays.copyOfRange(inputBuffer, 0, inputBufferLength);
                    }

                    inputBuffer[inputBufferLength++] = val;

                    // Handle switching to binary mode...
                    // This detects the = sign, and then gets the previous numbers which should
                    // be the length of binary data
                    if ((val == '=' || val == ':') && inputBufferLength > 2) {
                        char[] chars = new char[2];
                        chars[0] = (char) inputBuffer[inputBufferLength - 3];
                        chars[1] = (char) inputBuffer[inputBufferLength - 2];
                        try {
                            binaryLength = Integer.parseInt(new String(chars), 16);
                            rxState = RxStateMachine.RECEIVE_BINARY;
                        } catch (NumberFormatException e) {
                            // Eat the exception
                            // This will occur when we have an '=' that's not part of a binary field
                        }
                    }

                    break;
                case RECEIVE_BINARY:
                    inputBuffer[inputBufferLength++] = val;
                    if (--binaryLength == 0) {
                        rxState = RxStateMachine.RECEIVE_ASCII;
                    }
                    break;
                default:
                    break;
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
        logger.debug("TelegesisFrameHandler closed.");
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
    private void sendNextFrame() {
        synchronized (commandLock) {
            // Are we already processing a command?
            if (sentCommand != null) {
                return;
            }

            TelegesisCommand nextFrame = sendQueue.poll();
            if (nextFrame == null) {
                // Nothing to send
                return;
            }

            logger.trace("TX Telegesis: {}", nextFrame);

            // Remember the command we're processing
            sentCommand = nextFrame;

            // Send the data
            StringBuilder builder = new StringBuilder();
            for (int sendByte : nextFrame.serialize()) {
                builder.append(String.format(" %02X", sendByte));
                serialPort.write(sendByte);
            }
            logger.debug("TELEGESIS TX: Data{}", builder.toString());
        }
    }

    /**
     * Add a Telegesis command frame to the send queue. The sendQueue is a FIFO queue.
     * This method queues a {@link TelegesisCommand} frame without waiting for a response.
     *
     * @param request {@link TelegesisFrame}
     */
    public void queueFrame(TelegesisCommand request) {
        sendQueue.add(request);

        logger.debug("TX Telegesis queue: {}", sendQueue.size());

        if (sendQueue.size() > 2) {
            sentCommand = null;
        }

        sendNextFrame();
    }

    /**
     * Notify any transaction listeners when we receive a response.
     *
     * @param response the response data received
     * @return true if the response was processed
     */
    private boolean notifyTransactionComplete(final TelegesisCommand response) {
        boolean processed = false;

        logger.debug("Telegesis command complete: {}", response);
        synchronized (transactionListeners) {
            for (TelegesisListener listener : transactionListeners) {
                try {
                    if (listener.transactionEvent(response)) {
                        processed = true;
                    }
                } catch (Exception e) {
                    logger.debug("Exception processing Telegesis frame: {}: ", response, e);
                }
            }
        }

        return processed;
    }

    private void addTransactionListener(TelegesisListener listener) {
        synchronized (transactionListeners) {
            if (transactionListeners.contains(listener)) {
                return;
            }

            transactionListeners.add(listener);
        }
    }

    private void removeTransactionListener(TelegesisListener listener) {
        synchronized (transactionListeners) {
            transactionListeners.remove(listener);
        }
    }

    /**
     * Notify any event listeners when we receive an event.
     *
     * @param response the {@link TelegesisEvent} received
     */
    private void notifyEventReceived(final TelegesisEvent event) {
        logger.debug("Telegesis event received: {}", event);
        synchronized (eventListeners) {
            for (TelegesisEventListener listener : eventListeners) {
                try {
                    listener.telegesisEventReceived(event);
                } catch (Exception e) {
                    logger.debug("Exception processing Telegesis frame: {}: ", event, e);
                }
            }
        }
    }

    public void addEventListener(TelegesisEventListener listener) {
        synchronized (eventListeners) {
            if (eventListeners.contains(listener)) {
                return;
            }

            eventListeners.add(listener);
        }
    }

    public void removeEventListener(TelegesisEventListener listener) {
        synchronized (eventListeners) {
            eventListeners.remove(listener);
        }
    }

    /**
     * Sends a Telegesis request to the NCP without waiting for the response.
     *
     * @param command
     *            Request {@link TelegesisCommand} to send
     * @return response {@link Future} {@link TelegesisCommand}
     */
    public Future<TelegesisCommand> sendRequestAsync(final TelegesisCommand command) {
        class TransactionWaiter implements Callable<TelegesisCommand>, TelegesisListener {
            private boolean complete = false;

            @Override
            public TelegesisCommand call() {
                // Register a listener
                addTransactionListener(this);

                // Send the transaction
                queueFrame(command);

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
            public boolean transactionEvent(TelegesisCommand response) {
                // Check if this response completes our transaction
                if (!command.equals(response)) {
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

        Callable<TelegesisCommand> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

    /**
     * Sends a Telegesis request to the dongle and waits for the response. The response is correlated with the request
     * and the response data is available for the caller in the original command class.
     *
     * @param command
     *            Request {@link TelegesisCommand}
     * @return response {@link TelegesisStatusCode} of the response, or null if there was a timeout
     */
    public TelegesisStatusCode sendRequest(final TelegesisCommand command) {
        Future<TelegesisCommand> future = sendRequestAsync(command);
        try {
            future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return null;
        }

        return command.getStatus();
    }

    /**
     * Sends a Telegesis request to the NCP without waiting for the response.
     *
     * @param command
     *            Request {@link TelegesisCommand} to send
     * @return response {@link Future} {@link TelegesisCommand}
     */
    public Future<TelegesisEvent> waitEventAsync(final Class<?> eventClass) {
        class TransactionWaiter implements Callable<TelegesisEvent>, TelegesisEventListener {
            private boolean complete = false;
            private TelegesisEvent receivedEvent = null;

            @Override
            public TelegesisEvent call() {
                // Register a listener
                addEventListener(this);

                // Wait for the event
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
                removeEventListener(this);

                return receivedEvent;
            }

            @Override
            public void telegesisEventReceived(TelegesisEvent event) {
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

        Callable<TelegesisEvent> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

    public TelegesisEvent eventWait(final Class<?> eventClass) {
        Future<TelegesisEvent> future = waitEventAsync(eventClass);
        try {
            return future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            return null;
        }
    }

    interface TelegesisListener {
        boolean transactionEvent(TelegesisCommand response);
    }
}
