/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.transaction;

import java.util.Arrays;
import java.util.List;

import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeCommand;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeEvent;

/**
 * Single EZSP transaction response handling. This matches a {@link EzspFrameRequest} with a single
 * {@link EzspFrameResponse}. {@link EzspFrame#frameId} must also match.
 *
 * @author Chris Jackson
 *
 */
public class XBeeSingleResponseTransaction implements XBeeTransaction {
    private XBeeCommand request;
    private XBeeEvent response;
    private Class<?> requiredResponse;

    public XBeeSingleResponseTransaction(XBeeCommand request, Class<?> requiredResponse) {
        this.request = request;
        this.requiredResponse = requiredResponse;
    }

    @Override
    public boolean isMatch(XBeeEvent response) {
        // if (response.getClass() == requiredResponse && request.getSequenceNumber() == response.getSequenceNumber()) {
        // this.response = response;
        // return true;
        // } else {
        return false;
        // }
    }

    @Override
    public XBeeCommand getRequest() {
        return request;
    }

    // @Override
    // public EmberStatus getStatus() {
    // if (response == null) {
    // return EmberStatus.UNKNOWN;
    // }

    // TODO: Fix the response status!
    // return EmberStatus.UNKNOWN;
    // }

    @Override
    public XBeeEvent getResponse() {
        return response;
    }

    @Override
    public List<XBeeEvent> getResponses() {
        if (response == null) {
            return null;
        }

        // This transaction only allows a single response
        return Arrays.asList(response);
    }
}
