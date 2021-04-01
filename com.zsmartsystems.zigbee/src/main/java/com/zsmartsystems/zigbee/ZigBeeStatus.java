/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * An Enumeration of possible return values from the ZigBee framework providing a usable level of feedback to callers
 * about the success, or reason for failure of an operation.
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeStatus {
    /**
     * The operation completed successfully.
     */
    SUCCESS,

    /**
     * The operation failed and no further information on the reason is available.
     */
    FAILURE,

    /**
     * The caller provided invalid arguments for the requested function.
     */
    INVALID_ARGUMENTS,

    /**
     * The transport has not set a result for this request. This could mean it did not process the request.
     */
    NO_RESPONSE,

    /**
     * The system was not in the correct state to process the request.
     */
    INVALID_STATE,

    /**
     * A request was made that is not supported by the function.
     */
    UNSUPPORTED,

    /**
     * The system could not communicate. Depending on the method returning this error, the cause may be one of the
     * following -:
     *
     * <ul>
     * <li>Could not communicate with the transport layer
     * </ul>
     */
    COMMUNICATION_ERROR,

    /**
     * An unexpected response was received
     */
    BAD_RESPONSE,

    /**
     * The system could not perform the requested action at this time due to limited resources. The caller should wait
     * an appropriate amount of time and retry.
     */
    NO_RESOURCES,

    /**
     * A fatal error occurred that cannot be recovered from.
     */
    FATAL_ERROR,

    /**
     * No ZigBee network was found or formed
     */
    NO_NETWORK
}
