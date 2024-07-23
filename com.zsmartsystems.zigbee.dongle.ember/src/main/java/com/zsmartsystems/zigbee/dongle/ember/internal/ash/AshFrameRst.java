/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

/**
 * ASH Reset Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameRst extends AshFrame {
    /**
     * Constructor to create an ASH Reset frame.
     */
    public AshFrameRst() {
        this.frameType = FrameType.RST;
    }

    /**
     * Constructor taking an incoming data buffer
     *
     * @param frameBuffer the incoming data buffer
     */
    public AshFrameRst(int[] frameBuffer) {
        this();
    }

    @Override
    public int[] getOutputBuffer() {
        int[] rstFrame = super.getOutputBuffer();

        int[] outFrame = new int[rstFrame.length + 1];
        outFrame[0] = 0x1A; // Prepend reset frames with the ASH_CANCEL_BYTE to clear the RX buffer in the NCP
        for (int cnt = 0; cnt < rstFrame.length; cnt++) {
            outFrame[cnt + 1] = rstFrame[cnt];
        }

        return outFrame;
    }

    @Override
    public String toString() {
        return "AshFrameRst []";
    }
}
