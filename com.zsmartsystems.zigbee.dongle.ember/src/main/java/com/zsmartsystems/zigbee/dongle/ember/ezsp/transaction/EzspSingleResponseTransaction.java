package com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction;

import java.util.Arrays;
import java.util.List;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * Single EZSP transaction response handling. This matches a {@link EzspFrameRequest} with a single
 * {@link EzspFrameResponse}. {@link EzspFrame#frameId} must also match.
 *
 * @author Chris Jackson
 *
 */
public class EzspSingleResponseTransaction implements EzspTransaction {
    private EzspFrameRequest request;
    private EzspFrameResponse response;
    private Class<?> requiredResponse;

    public EzspSingleResponseTransaction(EzspFrameRequest request, Class<?> requiredResponse) {
        this.request = request;
        this.requiredResponse = requiredResponse;
    }

    @Override
    public boolean isMatch(EzspFrameResponse response) {
        if (response.getClass() == requiredResponse && request.getSequenceNumber() == response.getSequenceNumber()) {
            this.response = response;
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
    public EmberStatus getStatus() {
        if (response == null) {
            return EmberStatus.UNKNOWN;
        }

        // TODO: Fix the response status!
        return EmberStatus.UNKNOWN;
    }

    @Override
    public EzspFrameResponse getResponse() {
        return response;
    }

    @Override
    public List<EzspFrameResponse> getResponses() {
        if (response == null) {
            return null;
        }

        // This transaction only allows a single response
        return Arrays.asList(response);
    }
}
