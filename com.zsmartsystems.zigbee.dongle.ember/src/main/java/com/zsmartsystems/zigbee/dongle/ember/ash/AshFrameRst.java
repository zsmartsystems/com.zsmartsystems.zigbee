/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash;

/**
 * ASH Reset Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameRst extends AshFrame {
    /**
     * Constructor to create an ASH Reset frame.
     *
     */
    public AshFrameRst() {
        this.frameType = FrameType.RST;
    }

    @Override
    public String toString() {
        return "AshFrameRst []";
    }

}
