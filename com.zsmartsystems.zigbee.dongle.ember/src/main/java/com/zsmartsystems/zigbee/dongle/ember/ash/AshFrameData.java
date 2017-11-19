/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ash;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;

/**
 * ASH Data Frame
 *
 * @author Chris Jackson
 *
 */
public class AshFrameData extends AshFrame {
    /**
     * Constructor to create an ASH Data frame for sending.
     *
     * @param buffer
     */
    public AshFrameData() {
        frameType = FrameType.DATA;
    }

    /**
     * Set the {@link EzspFrameRequest} data to send
     *
     * @param ezspRequestFrame the {@link EzspFrameRequest} data frame to send
     */
    public void setData(EzspFrameRequest ezspRequestFrame) {
        dataBuffer = ezspRequestFrame.serialize();
    }

    public void setData(int[] data) {
        dataBuffer = data;
    }

    public void setReTx() {
        reTx = true;
    }

    public boolean getReTx() {
        return reTx;
    }

    @Override
    public int[] getDataBuffer() {
        return dataBuffer;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("AshFrameData [frmNum=");
        result.append(frmNum);
        result.append(", ackNum=");
        result.append(ackNum);
        result.append(", reTx=");
        result.append(reTx);
        result.append(", data=");

        if (dataBuffer == null) {
            result.append("null");
        } else {
            for (int i = 0; i < dataBuffer.length; i++) {
                if (i != 0) {
                    result.append(" ");
                }
                result.append(String.format("%02X", dataBuffer[i]));
            }
        }
        result.append(']');

        return result.toString();
    }

}
