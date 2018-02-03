/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction;

import java.util.List;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;

/**
 * Interface for EZSP protocol transaction.
 * <p>
 * The transaction looks for a {@link EzspFrameResponse} that matches the {@link EzspFrameRequest}.
 * The {@link EzspFrameResponse} and {@link EzspFrameRequest} classes are provided when the transaction is created.
 *
 * @author Chris Jackson
 *
 */
public interface EzspTransaction {
    /**
     * Matches request and response.
     *
     * @param response the response {@link EzspFrameResponse}
     * @return true if response matches the request
     */
    boolean isMatch(EzspFrameResponse response);

    /**
     * Gets the {@link EzspFrameRequest} associated with this transaction
     *
     * @return the {@link EzspFrameRequest}
     */
    EzspFrameRequest getRequest();

    /**
     * Gets the {@link EzspFrameResponse} for the transaction. If multiple responses are returned, this will return the
     * last response, indicating the final response used to complete the transaction.
     *
     * @return {@link EzspFrameResponse} to complete the transaction or null if no response received
     */
    EzspFrameResponse getResponse();

    /**
     * Gets a {@link List} of the {@link EzspFrameResponse}s received for the transaction. This is used for transactions
     * returning multiple responses - for single response transactions, use {@link #getResponse}.
     *
     * @return {@link EzspFrameResponse} to complete the transaction or null if no response received
     */
    List<EzspFrameResponse> getResponses();

    /**
     * Get the {@link EmberStatus} of the transaction. If multiple responses are returned, this will return the last
     * response indicating the final state of the transaction.
     *
     * @return {@link EmberStatus} indicating the transaction completion state or
     *         {@link EmberStatus#EMBED_UNKNOWN_STATUS} on error.
     */
    EmberStatus getStatus();
}
