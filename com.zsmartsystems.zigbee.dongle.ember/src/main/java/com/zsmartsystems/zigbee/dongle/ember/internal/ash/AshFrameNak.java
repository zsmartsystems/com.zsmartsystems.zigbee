/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

/**
 * ASH NAK Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameNak extends AshFrame {
    /**
     * Constructor to create an ASH NAK frame.
     *
     * @param buffer
     */
    public AshFrameNak(int ackNum) {
        this.frameType = FrameType.NAK;
        this.ackNum = ackNum;
    }

    public AshFrameNak(int[] frameBuffer) {
        this.frameType = FrameType.NAK;
        processHeader(frameBuffer);
    }

    @Override
    public String toString() {
        return "AshFrameNak [ackNum=" + ackNum + ", notRdy=" + nRdy + "]";
    }
}
