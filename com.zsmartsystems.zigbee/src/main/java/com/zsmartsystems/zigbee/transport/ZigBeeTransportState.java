/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;

/**
 * An enumeration of the current state of the transport layer.
 * <p>
 * This is used to provide status updates to higher layer listeners registered through the
 * {@link ZigBeeNetworkStateListener} interface.
 * <p>
 * Valid state transitions are -:
 * <ul>
 * <li>UNITIALISED to INITIALISING or OFFLINE
 * <li>INITIALISING to ONLINE or OFFLINE
 * <li>ONLINE to OFFLINE
 * <li>OFFLINE to ONLINE
 * </ul
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeTransportState {
    /**
     * Network has not yet been initialised
     */
    UNINITIALISED,
    /**
     * Network is currently initialising
     */
    INITIALISING,
    /**
     * Network is online and able to be used
     */
    ONLINE,
    /**
     * Network is offline and not able to be used
     */
    OFFLINE

}
