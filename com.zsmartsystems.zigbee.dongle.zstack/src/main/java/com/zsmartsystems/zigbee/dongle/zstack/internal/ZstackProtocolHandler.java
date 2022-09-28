/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

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
     * Flag to note if we are waiting for a SRSP to ensure that only one synchronous request is outstanding at any time
     */
    private AtomicBoolean waitingSrsp = new AtomicBoolean();

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
    private final Queue<ZstackFrameRequest> sendQueue = new ConcurrentLinkedQueue<>();

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
            logger.debug("ZstackFrameHandler thread started");

            int exceptionCnt = 0;

            while (!closeHandler) {
                try {
                    final int[] frameData = getPacket();
                    logger.debug("ZSTACK RX: FrameData [ data={}]", frameToString(frameData));

                    if (frameData == null) {
                        continue;
                    }

                    if (waitingSrsp.compareAndSet(isSynchronous(frameData[0]), false)) {
                        logger.debug("ZSTACK synchronous frame received. waitingSrsp={}, isSynchronous={}",
                                waitingSrsp, isSynchronous(frameData[0]));
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

                    sendNextFrame();
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
                    frameData[bytesRead++] = val;
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

    /**
     * Checks the frame type to see if it is a synchronous request or response.
     *
     * @param cmd0 the first command byte of the request or response frame
     * @return true if this is a synchronous frame
     */
    private boolean isSynchronous(int cmd0) {
        int frameType = cmd0 & 0xE0;
        return (frameType == 0x20 || frameType == 0x60);
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

        logger.debug("ZSTACK TX Queue length={}, waitingSync={}", sendQueue.size(), waitingSrsp);

        sendNextFrame();
    }

    /**
     * Notify any transaction listeners when we receive a response.
     *
     * @param response the response {@link ZstackFrameResponse} received
     * @return true if the response was processed
     */
    private void notifyResponseReceived(final ZstackFrameResponse response) {
        synchronized (transactionListeners) {
            for (ZstackListener listener : transactionListeners) {
                listener.transactionEvent(response);
            }
        }
    }

    private void addTransactionListener(ZstackListener listener) {
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

    private synchronized boolean sendNextFrame() {
        if (sendQueue.isEmpty()) {
            return false;
        }

        if (waitingSrsp.get() && sendQueue.peek().isSynchronous()) {
            // We are waiting for an SRSP and the next frame is an SREQ, so we need to wait
            return false;
        }

        ZstackFrameRequest nextFrame = sendQueue.poll();
        if (waitingSrsp.compareAndSet(false, nextFrame.isSynchronous())) {
            logger.debug("ZSTACK synchronous frame sent. waitingSrsp={}", waitingSrsp);
        }

        sendFrame(nextFrame);
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

    public void sendRaw(int rawByte) {
        logger.trace("ZSTACK TX Byte: {}", String.format("%02X", rawByte));
        port.write(rawByte);
    }

    private <REQ extends ZstackFrameRequest, RES extends ZstackFrameResponse> Future<RES> sendZstackRequestAsync(final REQ request, final Class<RES> responseType) {
        if (closeHandler) {
            logger.debug("ZSTACK: Handler is closed");
            return null;
        }

        class TransactionWaiter implements Callable<RES>, ZstackListener {
            private RES response = null;

            @Override
            public RES call() {
                // Register a listener
                addTransactionListener(this);

                // Send the transaction
                queueFrame(request);

                // Wait for the transaction to complete
                synchronized (this) {
                    try {
                        wait(TIMEOUT);
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
                if (responseType.isInstance(response)) {
                    this.response = responseType.cast(response);
                } else if (ZstackRpcSreqErrorSrsp.class.isInstance(response) && request.matchSreqError((ZstackRpcSreqErrorSrsp) response)) {
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

        Callable<RES> worker = new TransactionWaiter();
        return executor.submit(worker);
    }

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
     * @param transaction Request {@link ZstackTransaction}
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

    interface ZstackListener {
        void transactionEvent(ZstackFrameResponse response);

        void transactionComplete();
    }
}
