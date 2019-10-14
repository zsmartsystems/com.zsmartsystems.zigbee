/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Multiple EZSP transaction response handling. This matches a {@link EzspFrameRequest} which returns with multiple
 * {@link EzspFrameResponse}. {@link EzspFrame#frameId} must also match on the first response.
 * <p>
 * As example of this transaction is the SCAN requests. Such requests to the EZSP NCP result in multiple responses -:
 * <ul>
 * <li>An initial acknowledgement response
 * <li>Subsequent scan result responses
 * <li>A scan complete response
 * </ul>
 * <p>
 * This transaction handler will record all responses related to the request, but only complete on the final response.
 *
 * @author Chris Jackson
 *
 */
public class EzspMultiResponseTransaction<R extends EzspFrameResponse> implements EzspTransaction<R> {
    /**
     * The request we sent
     */
    private EzspFrameRequest request;

    /**
     * A list of responses received in relation to this transaction
     */
    private List<EzspFrameResponse> responses = new ArrayList<EzspFrameResponse>();

    /**
     * The response required to complete the transaction
     */
    private Class<R> requiredResponse;

    /**
     * The response required to complete the transaction
     */
    private Set<Class<? extends EzspFrameResponse>> relatedResponses;

    public EzspMultiResponseTransaction(EzspFrameRequest request, Class<R> requiredResponse,
            Set<Class<? extends EzspFrameResponse>> relatedResponses) {
        this.request = request;
        this.requiredResponse = requiredResponse;
        this.relatedResponses = relatedResponses;
    }

    private AtomicBoolean complete = new AtomicBoolean();

    @Override
    public boolean isComplete() {
        return complete.get();
    }

    @Override
    public synchronized boolean handleResponse(EzspFrameResponse response) {
        // Check if this response is related to this transaction
        if (relatedResponses.contains(response.getClass())) {
            // TODO: Check for a failure

            // Add the response to our responses received list
            responses.add(response);
            return true;
        }

        // Check if this response completes the transaction
        if (response.getClass() == requiredResponse) {
            complete.set(true);
            responses.add(response);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public EzspFrameRequest getRequest() {
        return request;
    }

    @Override
    public synchronized EmberStatus getStatus() {
        if (responses.isEmpty()) {
            return EmberStatus.UNKNOWN;
        }

        // TODO: Fix the response status! Needs a common response method?
        // for(EzspFrameResponse response : responses) {
        // }

        return EmberStatus.UNKNOWN;
    }

    @Override
    public synchronized R getResponse() {
        if (!responses.isEmpty()) {
            return (R) responses.get(responses.size() - 1);
        }
        return null;
    }

    @Override
    public synchronized List<EzspFrameResponse> getResponses() {
        if (responses.isEmpty()) {
            return null;
        }

        return responses;
    }
}
