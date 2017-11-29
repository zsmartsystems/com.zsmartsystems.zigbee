/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeQuerySendDataRequest extends ConBeeFrameRequest {

    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        serializeUInt8(APS_DATA_CONFIRM);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(7);
        serializeUInt16(0);

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(35);
        builder.append("QuerySendDataRequest [sequence=");
        builder.append(sequence);
        builder.append(']');
        return builder.toString();
    }

}
