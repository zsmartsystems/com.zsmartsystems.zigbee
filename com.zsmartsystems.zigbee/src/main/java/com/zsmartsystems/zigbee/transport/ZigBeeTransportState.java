/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * An enumeration of the current state of the transport layer.
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeTransportState {
    /**
     * Transport has not yet been initialised
     */
    UNINITIALISED,
    /**
     * Transport is currently initialising
     */
    INITIALISING,
    /**
     * Transport is online and able to be used
     */
    ONLINE,
    /**
     * Transport is offline and not able to be used
     */
    OFFLINE
}
