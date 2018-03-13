/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeTransactionMatcher;

/**
 * The ZCL transaction response matcher.
 * <p>
 * Implements {@link ZigBeeTransactionMatcher} to check if a ZCL transaction matches a request.
 * The matcher will return true if the request and response transaction IDs match and the request destination address,
 * and response source address match.
 *
 * @author Chris Jackson
 */
public class ZclTransactionMatcher implements ZigBeeTransactionMatcher {

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!request.getDestinationAddress().equals(response.getSourceAddress())) {
            return false;
        }

        if (response instanceof ZclCommand && ((ZclCommand) request).getTransactionId() != null) {
            final int transactionId = ((ZclCommand) request).getTransactionId();
            return Integer.valueOf(transactionId).equals(((ZclCommand) response).getTransactionId());
        } else {
            return false;
        }
    }
}
