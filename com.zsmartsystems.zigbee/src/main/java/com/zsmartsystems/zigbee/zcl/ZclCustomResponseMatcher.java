/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.Command;
import com.zsmartsystems.zigbee.CommandResponseMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;

/**
 * ZCL custom response matcher.
 * <p>
 * Implements {@link CommandResponseMatcher} to check if a ZCL transaction matches a request.
 * The matcher will return true if the request and response transaction IDs match.
 * If the response matches the {@link DefaultResponse} class, then the status code must not be 0.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ZclCustomResponseMatcher implements CommandResponseMatcher {
    @Override
    public boolean isMatch(Command request, Command response) {
        if (response instanceof ZclCommand && ((ZclCommand) request).getTransactionId() != null) {
            final int transactionId = ((ZclCommand) request).getTransactionId();
            if (Integer.valueOf(transactionId).equals(((ZclCommand) response).getTransactionId())) {
                if (response instanceof DefaultResponse) {
                    return (((DefaultResponse) response).getStatusCode().getId() == 0) ? false : true;
                } else {
                    return true; // This is the actual response, return this one.
                }
            } else {
                return false; // Transaction ID mismatch.
            }
        } else {
            return false; // Transaction ID not set in original command or not ZclCommand.
        }
    }
}
