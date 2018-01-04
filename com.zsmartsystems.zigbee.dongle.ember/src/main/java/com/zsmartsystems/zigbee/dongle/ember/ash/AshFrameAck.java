/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Frame Error
 *
 * @author Chris Jackson
 *
 */
public class AshFrameAck extends AshFrame {
    /**
     * Constructor to create an ASH ACK frame.
     *
     */
    public AshFrameAck() {
        this.frameType = FrameType.ACK;
    }

    @Override
    public String toString() {
        return "AshFrameAck [ackNum=" + ackNum + "]";
    }
}
