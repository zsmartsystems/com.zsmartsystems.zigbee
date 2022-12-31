/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * An enumeration of possible network states.
 * <p>
 * <img src="./doc-files/ZigBeeNetworkStateTransitions.png" width="50%">
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeNetworkState {
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
    OFFLINE,
    /**
     * The network has been closed and may not be restarted
     */
    SHUTDOWN
}
