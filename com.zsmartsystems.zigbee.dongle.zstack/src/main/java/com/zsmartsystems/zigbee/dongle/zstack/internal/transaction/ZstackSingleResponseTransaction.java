/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal.transaction;

import java.util.Arrays;
import java.util.List;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 * Single ZStack transaction response handling. This matches a {@link ZstackFrameRequest} with a single
 * {@link ZstackFrameResponse}.
 *
 * @author Chris Jackson
 *
 */
public class ZstackSingleResponseTransaction implements ZstackTransaction {
    private ZstackFrameRequest request;
    private ZstackFrameResponse response;
    private Class<?> requiredResponse;

    public ZstackSingleResponseTransaction(ZstackFrameRequest request, Class<?> requiredResponse) {
        this.request = request;
        this.requiredResponse = requiredResponse;
    }

    @Override
    public boolean isMatch(ZstackFrameResponse response) {
        if (response.getClass() == requiredResponse) {
            this.response = response;
            return true;
        }

        // If this is an RPC Error Response, then it might be telling us that the command we tried to send is
        // unsupported.
        // We need to terminate the transaction if this is true.
        if (response instanceof ZstackRpcSreqErrorSrsp) {
            return request.matchSreqError((ZstackRpcSreqErrorSrsp) response);
        }
        return false;
    }

    @Override
    public ZstackFrameRequest getRequest() {
        return request;
    }

    @Override
    public ZstackFrameResponse getResponse() {
        return response;
    }

    @Override
    public List<ZstackFrameResponse> getResponses() {
        if (response == null) {
            return null;
        }

        // This transaction only allows a single response
        return Arrays.asList(response);
    }

    @Override
    public String toString() {
        return "ZstackSingleResponseTransaction [request=" + request + ", requiredResponse="
                + requiredResponse.getSimpleName() + ", response=" + response + "]";
    }

}
