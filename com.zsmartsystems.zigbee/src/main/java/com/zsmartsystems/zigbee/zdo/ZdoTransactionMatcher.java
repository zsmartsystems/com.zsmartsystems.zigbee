/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeTransactionMatcher;

/**
 * The ZDO response matcher.
 * <p>
 * The matcher will return true if the the response packet is a {@link ZdoResponse}
 * and the response source address matches the destination of the request.
 *
 * @author Chris Jackson
 */
public class ZdoTransactionMatcher implements ZigBeeTransactionMatcher {

    @Override
    public boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response) {
        if (response instanceof ZdoResponse) {
            return ((ZdoRequest) request).getDestinationAddress().equals(((ZdoResponse) response).getSourceAddress());
        } else {
            return false;
        }
    }
}
