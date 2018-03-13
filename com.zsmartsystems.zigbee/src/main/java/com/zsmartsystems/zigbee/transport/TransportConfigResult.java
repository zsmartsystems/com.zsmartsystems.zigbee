/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * Defines the possible responses from the {@link ZigBeeTransportTransmit#updateTransportConfig()} method.
 *
 * @author Chris Jackson
 *
 */
public enum TransportConfigResult {
    /**
     * Success. All configuration options were implemented or queued.
     */
    SUCCESS,
    /**
     * The transport has not set a result for this request. This could mean it did not process the request.
     */
    ERROR_NO_RESULT,
    /**
     * There was an error implementing the request.
     */
    FAILURE,
    /**
     * A configuration for the requested option has not been set
     */
    ERROR_REQUEST_NOT_SET,
    /**
     * The transport was unable to implement a configuration option as it was in an invalid mode
     */
    ERROR_INVALID_MODE,
    /**
     * A value for a configuration option was an invalid format
     */
    ERROR_INVALID_VALUE,
    /**
     * A configuration option is unsupported within the transport layer
     */
    ERROR_UNSUPPORTED;
}
