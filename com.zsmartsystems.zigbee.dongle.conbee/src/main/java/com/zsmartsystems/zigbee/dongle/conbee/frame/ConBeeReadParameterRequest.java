/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 * By reading parameters the current configuration can be obtained. Be aware that this configuration might not reflect
 * the active configuration, since changes to parameters affect the network operation only as soon as it’s stopped and
 * started again.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterRequest extends ConBeeFrameRequest {
    private ConBeeNetworkParameter parameter;

    public void setParameter(ConBeeNetworkParameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        serializeUInt8(READ_PARAMETER);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(8);
        serializeUInt16(1);
        serializeUInt8(parameter.getKey());

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(50);
        builder.append("ReadParameterRequest [sequence=");
        builder.append(sequence);
        builder.append(", parameter=");
        builder.append(parameter);
        builder.append(']');
        return builder.toString();
    }

}
