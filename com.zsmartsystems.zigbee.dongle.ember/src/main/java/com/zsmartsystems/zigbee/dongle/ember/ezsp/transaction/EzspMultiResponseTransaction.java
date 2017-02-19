package com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
public class EzspMultiResponseTransaction implements EzspTransaction {
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
    private Class<?> requiredResponse;

    /**
     * The response required to complete the transaction
     */
    private Set<Class<?>> relatedResponses;

    public EzspMultiResponseTransaction(EzspFrameRequest request, Class<?> requiredResponse,
            Set<Class<?>> relatedResponses) {
        this.request = request;
        this.requiredResponse = requiredResponse;
        this.relatedResponses = relatedResponses;
    }

    @Override
    public synchronized boolean isMatch(EzspFrameResponse response) {
        // Check if this response is related to this transaction
        if (relatedResponses.contains(response.getClass())) {
            // TODO: Check for a failure

            // Add the response to our responses received list
            responses.add(response);
            return false;
        }

        // Check if this response completes the transaction
        if (response.getClass() == requiredResponse && request.getSequenceNumber() == response.getSequenceNumber()) {
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
        if (responses.isEmpty() == true) {
            return EmberStatus.EMBED_UNKNOWN_STATUS;
        }

        // TODO: Fix the response status!
        return EmberStatus.EMBED_UNKNOWN_STATUS;
    }

    @Override
    public synchronized EzspFrameResponse getResponse() {
        if (!responses.isEmpty()) {
            return responses.get(responses.size() - 1);
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
