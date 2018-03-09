/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.transaction;

import java.util.List;

import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrameRequest;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeFrameResponse;
import com.zsmartsystems.zigbee.dongle.conbee.frame.ConBeeStatus;

/**
 * Interface for ConBee transaction.
 * <p>
 * The transaction looks for a {@link ConBeeFrameResponse} that matches the {@link ConBeeFrameRequest}.
 * The {@link ConBeeFrameResponse} and {@link ConBeeFrameRequest} classes are provided when the transaction is created.
 *
 * @author Chris Jackson
 *
 */
public interface ConBeeTransaction {
    /**
     * Matches request and response.
     *
     * @param response the response {@link ConBeeFrameResponse}
     * @return true if response matches the request
     */
    boolean isMatch(ConBeeFrameResponse response);

    /**
     * Gets the {@link ConBeeFrameRequest} associated with this transaction
     *
     * @return the {@link ConBeeFrameRequest}
     */
    ConBeeFrameRequest getRequest();

    /**
     * Gets the {@link ConBeeFrameResponse} for the transaction. If multiple responses are returned, this will return
     * the
     * last response, indicating the final response used to complete the transaction.
     *
     * @return {@link ConBeeFrameResponse} to complete the transaction or null if no response received
     */
    ConBeeFrameResponse getResponse();

    /**
     * Gets a {@link List} of the {@link ConBeeFrameResponse}s received for the transaction. This is used for
     * transactions
     * returning multiple responses - for single response transactions, use {@link #getResponse}.
     *
     * @return {@link ConBeeFrameResponse} to complete the transaction or null if no response received
     */
    List<ConBeeFrameResponse> getResponses();

    /**
     * Get the {@link ConBeeStatus} of the transaction. If multiple responses are returned, this will return the last
     * response indicating the final state of the transaction.
     *
     * @return {@link ConBeeStatus} indicating the transaction completion state or
     *         {@link ConBeeStatus#UNKNOWN_STATUS} on error.
     */
    ConBeeStatus getStatus();
}
