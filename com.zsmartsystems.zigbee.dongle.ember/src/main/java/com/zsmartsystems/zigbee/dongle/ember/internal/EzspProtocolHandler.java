/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal;

import java.util.Map;
import java.util.concurrent.Future;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.transaction.EzspTransaction;
import com.zsmartsystems.zigbee.transport.ZigBeePort;

/**
 * Interface for the EZSP protocol handler. The protocol handler manages the low level data transfer of EZSP frames.
 *
 * @author Chris Jackson - Initial Implementation
 *
 */
public interface EzspProtocolHandler {

    /**
     * Starts the handler. Sets input stream where the packet is read from the and
     * handler which further processes the received packet.
     *
     * @param port the {@link ZigBeePort}
     */
    public void start(final ZigBeePort port);

    /**
     * Set the close flag to true.
     */
    public void setClosing();

    /**
     * Requests parser thread to shutdown.
     */
    public void close();

    /**
     * Checks if parser thread is alive.
     *
     * @return true if parser thread is alive.
     */
    public boolean isAlive();

    /**
     * Add an EZSP frame to the send queue. The sendQueue is a FIFO queue.
     * This method queues a {@link EzspFrameRequest} frame without waiting for a response and
     * no transaction management is performed.
     *
     * @param request {@link EzspFrameRequest}
     */
    public void queueFrame(EzspFrameRequest request);

    /**
     * Connect to the ASH/EZSP NCP
     */
    public void connect();

    /**
     * Sends an EZSP request to the NCP without waiting for the response.
     *
     * @param ezspTransaction Request {@link EzspTransaction}
     *
     * @return response {@link Future} {@link EzspFrame}
     */
    public <T extends EzspFrameResponse> Future<T> sendEzspRequestAsync(final EzspTransaction<T> ezspTransaction);

    /**
     * Sends an EZSP request to the NCP and waits for the response. The response is correlated with the request and the
     * returned {@link EzspTransaction} contains the request and response data.
     *
     * @param ezspTransaction Request {@link EzspTransaction}
     *
     * @return response {@link EzspTransaction}
     */
    public <T extends EzspFrameResponse> EzspTransaction<T> sendEzspTransaction(EzspTransaction<T> ezspTransaction);

    /**
     * Wait for the requested {@link EzspFrameResponse} to be received
     *
     * @param eventClass Request {@link EzspFrameResponse} to wait for
     * @return response {@link Future} {@link EzspFrameResponse}
     */
    public Future<EzspFrameResponse> eventWaitAsync(final Class<?> eventClass);

    /**
     * Wait for the requested {@link EzspFrameResponse} to be received
     *
     * @param eventClass Request {@link EzspFrameResponse} to wait for
     * @param timeout the time in milliseconds to wait for the response
     * @return the {@link EzspFrameResponse} once received, or null on exception
     */
    public EzspFrameResponse eventWait(final Class<?> eventClass, int timeout);

    /**
     * Get a map of statistics counters from the handler
     *
     * @return map of counters
     */
    public Map<String, Long> getCounters();
}
