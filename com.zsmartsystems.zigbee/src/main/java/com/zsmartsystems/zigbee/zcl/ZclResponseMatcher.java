/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.ZigBeeCommand;

/**
 * The ZCL response matcher.
 * <p>
 * Implements {@link CommandResponseMatcher} to check if a ZCL transaction matches a request.
 * The matcher will return true if the request and response transaction IDs match.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZclResponseMatcher implements CommandResponseMatcher {

    @Override
    public boolean isMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (!request.getSourceAddress().equals(response.getDestinationAddress())) {
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
