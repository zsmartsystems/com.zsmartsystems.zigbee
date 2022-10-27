/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.ZigBeeExecutors;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackCommand;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameFactory;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Class for the ZStack protocol handler. The protocol handler manages the low level data transfer of ZStack frames.
 *
 * @author Chris Jackson
 *
 */
public class ZstackProtocolHandler {
    private static int ZSTACK_MAX_LENGTH = 100;

    /**
     * Time to wait for a SRSP response to an SREQ before moving on
     */
    private static int TIMEOUT = 3500;

    private static int ZSTACK_SOF = 0xFE;

    private enum ZstackState {
        SOF,
        LENGTH,
        DATA,
        FCS
    }

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZstackProtocolHandler.class);

    /**
     * The port.
     */
    private ZigBeePort port;

    /**
     * Flag reflecting that parser has been closed and parser parserThread should exit.
     */
    private boolean closeHandler = false;

    /**
     * The queue of {@link ZstackFrameRequest} frames waiting to be sent
     */
    private final BlockingQueue<ZstackFrameRequest> sendQueue = new LinkedBlockingQueue<>();

    private ExecutorService executor = ZigBeeExecutors.newCachedThreadPool("ZstackProtocolHandler");

    private final List<ZstackListener> transactionListeners = new ArrayList<>();

    /**
     * Starts the handler. Sets input stream where the packet is read from the and
     * handler which further processes the received packet.
     *
     * @param port the {@link ZigBeePort}
     */
    public void start(final ZigBeePort port) {
        this.port = port;

        executor.execute(() -> {
            logger.debug("ZstackFrameHandler sender started.");
            while (!closeHandler) {
                try {
                    sendNextFrame();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                    logger.error("ZstackFrameHandler sending Exception: ", e);
                }
            }
            logger.debug("ZstackFrameHandler sender exited.");
        });

        executor.execute(() -> {
            logger.debug("ZstackFrameHandler thread started");

            int exceptionCnt = 0;

            while (!closeHandler) {
                try {
                    final int[] frameData = getPacket();
                    logger.debug("ZSTACK RX: FrameData [ data={}]", frameToString(frameData));

                    if (frameData == null) {
                        continue;
                    }

                    ZstackFrameResponse response = ZstackFrameFactory.createFrame(frameData);
                    if (response == null) {
                        logger.debug("RX ZSTACK: ZstackUnknownFrame [ data={}]", frameToString(frameData));
                        continue;
                    } else {
                        logger.debug("RX ZSTACK: {}", response);
                    }

                    // Also handle any ZStack level transactions (SREQ transactions or waiting events)
                    notifyResponseReceived(response);
                } catch (final IOException e) {
                    logger.error("ZstackFrameHandler IOException: ", e);

                    if (exceptionCnt++ > 10) {
                        logger.error("ZstackFrameHandler exception count exceeded");
                        closeHandler = true;
                    }
                } catch (final Exception e) {
                    logger.error("ZstackFrameHandler Exception: ", e);
                }
            }
            logger.debug("ZstackFrameHandler exited.");
        });
    }

    public void setClosing() {
        closeHandler = true;
        executor.shutdown();
    }

    public void close() {
        logger.debug("ZstackFrameHandler close.");
        setClosing();

        clearTransactionQueue();

        sendQueue.clear();

        executor.shutdownNow();
    }

    /**
     * Add a ZStack frame to the send queue. The sendQueue is a FIFO queue.
     * This method queues a {@link ZstackFrameRequest} frame without waiting for a response and
     * no transaction management is performed.
     *
     * @param request {@link ZstackFrameRequest}
     */
    public void queueFrame(ZstackFrameRequest request) {
        if (closeHandler) {
            logger.debug("ZSTACK: Handler is closed");
            return;
        }
        sendQueue.add(request);

        logger.debug("ZSTACK TX Queue length={}", sendQueue.size());
    }

    /**
     * Wait for some specific event
     * @param <RES> The type of response to wait for
     * @param requiredResponse The class of response to wait for
     * @param predicate The predicate to determine if the event was this
     * @return A Future that is completed upon receiving the specific event requested
     */
    public <RES extends ZstackFrameResponse> Future<RES> waitForEvent(final Class<RES> requiredResponse, final Predicate<RES> predicate) {
        if (closeHandler) {
            return null;
        }

        final TransactionWaiter<RES> transactionWaiter = new TransactionWaiter<>(null, 100000, requiredResponse, predicate);

        // Register a listener
        addTransactionListener(transactionWaiter);

        return executor.submit(transactionWaiter);
    }

    /**
     * Register a listener for certain classes.
     * @param <RES> The type which this listener listens to
     * @param requiredResponse The class of events the listener responds to
     * @param delivery The callback method which is fed the events of type {@link ZstackFrameReponse}
     * @return a registered listener. To unregister execute {@link ZstackListener#transactionComplete()}.
     */
    public <RES extends ZstackFrameResponse> ZstackListener listener(final Class<RES> requiredResponse, final Consumer<RES> delivery) {
        if (closeHandler) {
            return null;
        }

        final ZstackListener listener = new ZstackListener() {
            @Override
            public void transactionEvent(ZstackFrameResponse response) {
                if (requiredResponse.isInstance(response)) {
                    executor.execute(() -> delivery.accept(requiredResponse.cast(response)));
                }
            }

            @Override
            public void transactionComplete() {
                removeTransactionListener(this);
            }
        };

        addTransactionListener(listener);

        return listener;
    }

    /**
     * Sends a ZStack request to the NCP and waits for the response. The response is correlated with the request and the
     * returned {@link ZstackTransaction} contains the request and response data.
     *
     * @param request The message to send {@link ZstackFrameRequest}
     * @param responseType The expected returntype {@link ZstackFrameResponse}
     * @return response {@link ZstackCommand}
     */
    public <REQ extends ZstackFrameRequest, RES extends ZstackFrameResponse> RES sendTransaction(REQ request, Class<RES> responseType) {
        logger.debug("QUEUE ZSTACK: {}", request);

        Future<RES> futureResponse = sendZstackRequestAsync(request, responseType);
        if (futureResponse == null) {
            logger.debug("ZSTACK: Error sending transaction: Future is null");
            return null;
        }

        try {
            return futureResponse.get();
        } catch (InterruptedException | ExecutionException e) {
            futureResponse.cancel(true);
            logger.debug("ZSTACK interrupted in sendTransaction for {}", request, e);
        }

        return null;
    }

    private void notifyResponseReceived(final ZstackFrameResponse response) {
        synchronized (transactionListeners) {
            for (ZstackListener listener : transactionListeners) {
                listener.transactionEvent(response);
            }
        }
    }

    private void addTransactionListener(ZstackListener listener) {
        logger.debug("Adding Zstack Transaction listener");
        synchronized (transactionListeners) {
            if (transactionListeners.contains(listener)) {
                return;
            }

            transactionListeners.add(listener);
        }
    }

    private void removeTransactionListener(ZstackListener listener) {
        synchronized (transactionListeners) {
            transactionListeners.remove(listener);
        }
    }

    /**
     * Aborts all waiting transactions
     */
    private void clearTransactionQueue() {
        synchronized (transactionListeners) {
            for (ZstackListener listener : new ArrayList<>(transactionListeners)) {
                listener.transactionComplete();
            }
        }
    }

    private synchronized boolean sendNextFrame() throws InterruptedException {
        final ZstackFrameRequest request = sendQueue.poll(1000L, TimeUnit.MILLISECONDS);
        if (request == null) {
            return false;
        }

        sendFrame(request);

        return true;
    }

    private synchronized void sendFrame(ZstackFrameRequest request) {
        logger.debug("TX ZSTACK: {}", request);

        StringBuilder builder = new StringBuilder(100);
        builder.append("ZSTACK TX: FrameData [ data=");

        for (int outByte : request.serialize()) {
            port.write(outByte);
            builder.append(String.format("%02X ", outByte));
        }
        builder.append(']');
        logger.debug(builder.toString());
    }

    private <REQ extends ZstackFrameRequest, RES extends ZstackFrameResponse> Future<RES> sendZstackRequestAsync(final REQ request, final Class<RES> responseType) {
        if (closeHandler) {
            logger.debug("ZSTACK: Handler is closed");
            return null;
        }

        final TransactionWaiter<RES> waiter = new TransactionWaiter<>(request, TIMEOUT, responseType, null);

        addTransactionListener(waiter);

        return executor.submit(waiter);
    }

    private String frameToString(int[] inputBuffer) {
        if (inputBuffer == null) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int data : inputBuffer) {
            result.append(String.format("%02X ", data));
        }
        return result.toString();
    }

    private int[] getPacket() throws IOException {
        int length = 0;
        int bytesRead = 0;
        int[] frameData = null;

        ZstackState state = ZstackState.SOF;

        while (!closeHandler) {
            int val = port.read(1000);
            logger.trace("ZSTACK RX Byte: {} [{}/{}]", String.format("%02X", val), bytesRead, length);
            if (val == -1) {
                continue;
            }

            switch (state) {
                case SOF:
                    if (val == ZSTACK_SOF) {
                        state = ZstackState.LENGTH;
                    }
                    break;
                case LENGTH:
                    if (val > ZSTACK_MAX_LENGTH) {
                        logger.debug("ZSTACK Length greater than allowed: {}", val);
                        state = ZstackState.SOF;
                        continue;
                    }
                    length = val + 2;
                    frameData = new int[length];
                    state = ZstackState.DATA;
                    bytesRead = 0;
                    break;
                case DATA:
                    requireNonNull(frameData, "FrameData should have been set earlier on")[bytesRead++] = val;
                    if (bytesRead >= length) {
                        state = ZstackState.FCS;
                    }
                    break;
                case FCS:
                    int checksum = getChecksum(frameData);
                    if (val != checksum) {
                        logger.debug("ZSTACK Checksum error: {} <> {}", val, checksum);
                        state = ZstackState.SOF;
                        continue;
                    }
                    return frameData;
                default:
                    logger.debug("ZSTACK Unknown decoder state: {}", state);
                    break;
            }
        }

        return null;
    }

    private int getChecksum(int[] data) {
        int checksum = (data.length - 2);
        for (int value : data) {
            checksum ^= value;
        }
        return checksum & 0xFF;
    }

    public interface ZstackListener {
        void transactionEvent(ZstackFrameResponse response);

        void transactionComplete();
    }

    private class TransactionWaiter<RES extends ZstackFrameResponse> implements Callable<RES>, ZstackListener {
        private final ZstackFrameRequest request;
        private final Class<RES> requiredResponse;
        private final Predicate<RES> responsePredicate;
        private final int timeout;
        private RES response = null;

        private TransactionWaiter(final ZstackFrameRequest request, final int timeout, final Class<RES> requiredResponse, final Predicate<RES> responsePredicate) {
            this.request = request;
            this.timeout = timeout;
            this.requiredResponse = requiredResponse;
            this.responsePredicate = responsePredicate;
        }

        @Override
        public RES call() {
            // Send the transaction
            if (request != null) {
                queueFrame(request);
            }

            // Wait for the transaction to complete
            synchronized (this) {
                try {
                    wait(timeout);
                } catch (InterruptedException e) {
                } finally {
                    removeTransactionListener(this);
                }
            }

            return response;
        }

        @Override
        public void transactionEvent(ZstackFrameResponse response) {
            // Check if this response completes our transaction
            if (requiredResponse.isInstance(response)) {
                if (responsePredicate == null) {
                    this.response = requiredResponse.cast(response);
                } else if (responsePredicate.test(requiredResponse.cast(response))) {
                    this.response = requiredResponse.cast(response);
                } else {
                    return;
                }
            } else if (response instanceof ZstackRpcSreqErrorSrsp && request != null && request.matchSreqError((ZstackRpcSreqErrorSrsp) response)) {
                // no response. Error code ?
            } else {
                return;
            }

            transactionComplete();
        }

        @Override
        public void transactionComplete() {
            synchronized (this) {
                notify();
            }
        }
    }
}
