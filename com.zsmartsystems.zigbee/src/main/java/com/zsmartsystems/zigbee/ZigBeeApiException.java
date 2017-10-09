/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Runtime exception class for ZigBee API.
 * 
 * @author Tommi S.E. Laukkanen
 */
public class ZigBeeApiException extends RuntimeException {
    /**
     * Constructor for setting message and cause.
     *
     * @param message the message
     * @param cause the cause
     */
    public ZigBeeApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
