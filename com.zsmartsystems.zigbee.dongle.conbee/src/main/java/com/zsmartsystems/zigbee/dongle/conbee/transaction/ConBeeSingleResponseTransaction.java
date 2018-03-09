/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.transaction;

import java.util.Arrays;
import java.util.List;

import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrame;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrameRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrameResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeStatus;

/**
 * Single ConBee transaction response handling. This matches a {@link ConBeeFrameRequest} with a single
 * {@link ConBeeFrameResponse}. {@link ConBeeFrame#frameId} must also match.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeSingleResponseTransaction implements ConBeeTransaction {
    private ConBeeFrameRequest request;
    private ConBeeFrameResponse response;
    private Class<?> requiredResponse;

    public ConBeeSingleResponseTransaction(ConBeeFrameRequest request, Class<?> requiredResponse) {
        this.request = request;
        this.requiredResponse = requiredResponse;
    }

    @Override
    public boolean isMatch(ConBeeFrameResponse response) {
        if (response.getClass() == requiredResponse && request.getSequence() == response.getSequence()) {
            this.response = response;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ConBeeFrameRequest getRequest() {
        return request;
    }

    @Override
    public ConBeeStatus getStatus() {
        if (response == null) {
            return ConBeeStatus.INVALID_VALUE;
        }

        // TODO: Fix the response status!
        return ConBeeStatus.INVALID_VALUE;
    }

    @Override
    public ConBeeFrameResponse getResponse() {
        return response;
    }

    @Override
    public List<ConBeeFrameResponse> getResponses() {
        if (response == null) {
            return null;
        }

        // This transaction only allows a single response
        return Arrays.asList(response);
    }
}
