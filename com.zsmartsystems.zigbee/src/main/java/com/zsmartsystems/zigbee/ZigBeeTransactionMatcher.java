/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Defines the interface for transaction matcher
 *
 * @author Chris Jackson
 */
public interface ZigBeeTransactionMatcher {
    /**
     * Matches request and response.
     *
     * @param request the request {@link ZigBeeCommand}
     * @param response the response {@link ZigBeeCommand}
     * @return true if request matches response
     */
    boolean isTransactionMatch(ZigBeeCommand request, ZigBeeCommand response);
}
