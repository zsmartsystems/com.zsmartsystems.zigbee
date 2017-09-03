/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Defines the interface for the response matcher
 *
 * @author Tommi S.E. Laukkanen
 */
public interface CommandResponseMatcher {
    /**
     * Matches request and response.
     *
     * @param request the request {@link Command}
     * @param response the response {@link Command}
     * @return true if request matches response
     */
    boolean isMatch(Command request, Command response);
}
