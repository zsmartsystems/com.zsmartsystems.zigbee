/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal.transaction;

import java.util.List;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameResponse;

/**
 * Interface for ZStack protocol transaction.
 * <p>
 * The transaction looks for a received {@link ZstackFrameResponse} that matches the sent {@link ZstackFrameRequest}.
 * Generally, this is matching the Srsp matching the Sreq.
 * The {@link ZstackFrameRequest} and {@link ZstackFrameResponse} classes are provided when the transaction is created.
 *
 * @author Chris Jackson
 *
 */
public interface ZstackTransaction {
    /**
     * Matches request and response.
     *
     * @param response the response {@link ZstackFrameResponse}
     * @return true if response matches the request
     */
    boolean isMatch(ZstackFrameResponse response);

    /**
     * Gets the {@link ZstackFrameRequest} associated with this transaction
     *
     * @return the {@link ZstackFrameRequest}
     */
    ZstackFrameRequest getRequest();

    /**
     * Gets the {@link ZstackFrameResponse} for the transaction. If multiple responses are returned, this will return
     * the
     * last response, indicating the final response used to complete the transaction.
     *
     * @return {@link ZstackFrameResponse} to complete the transaction or null if no response received
     */
    ZstackFrameResponse getResponse();

    /**
     * Gets a {@link List} of the {@link ZstackFrameResponse}s received for the transaction. This is used for
     * transactions
     * returning multiple responses - for single response transactions, use {@link #getResponse}.
     *
     * @return {@link ZstackFrameResponse} to complete the transaction or null if no response received
     */
    List<ZstackFrameResponse> getResponses();
}
