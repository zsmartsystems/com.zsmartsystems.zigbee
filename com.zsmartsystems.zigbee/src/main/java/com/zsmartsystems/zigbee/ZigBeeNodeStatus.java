/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

/**
 * Enumeration defining the state of a node when joining or leaving
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeNodeStatus {

    /**
     * A device has joined the network without security
     */
    UNSECURED_JOIN,

    /**
     * A device has securely rejoined the network
     */
    SECURED_REJOIN,

    /**
     * A device has unsecurely rejoined the network
     */
    UNSECURED_REJOIN,

    /**
     * A device has left the network
     */
    DEVICE_LEFT

    ;

}
