/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.transport;

/**
 * Response states for feedback responses
 *
 * @author Chris Jackson
 *
 */
public enum ZigBeeTransportProgressState {
    /**
     * The command was successfully sent
     */
    TX_ACK,

    /**
     * The command was not sent
     */
    TX_NAK,

    /**
     * The command was sent and the acknowledgement was received from the remote device
     */
    RX_ACK,

    /**
     * The command was sent but not acknowledgement by the remote device
     */
    RX_NAK
}
