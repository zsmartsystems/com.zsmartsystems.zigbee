/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeWriteParameterRequest extends ConBeeFrameRequest {

    private ConBeeNetworkParameter parameter;
    private Object value;

    public void setParameter(ConBeeNetworkParameter parameter) {
        this.parameter = parameter;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        int[] data;
        switch (parameter) {
            case APS_EXTENDED_PANID:
                if (!(value instanceof ExtendedPanId)) {
                    throw new IllegalArgumentException(
                            "Parameter type " + parameter + " is expected to be ExtendedPanId");
                }

                data = ((ExtendedPanId) value).getValue();
                break;
            case CHANNEL_MASK:
                data = new int[4];
                break;
            case NETWORK_KEY:
                data = new int[16];
                break;
            case DEVICE_TYPE:
            case NWK_UPDATE_ID:
            case SECURITY_MODE:
                if (!(value instanceof Integer)) {
                    throw new IllegalArgumentException("Parameter type " + parameter + " is expected to be Integer");
                }

                data = new int[1];
                data[0] = (int) value & 0xff;
                break;
            case TRUST_CENTRE_ADDRESS:
                if (!(value instanceof IeeeAddress)) {
                    throw new IllegalArgumentException(
                            "Parameter type " + parameter + " is expected to be IeeeAddress");
                }

                data = ((IeeeAddress) value).getValue();
                break;
            default:
                throw new IllegalArgumentException("Parameter type " + parameter + " is READ-ONLY");
        }

        serializeUInt8(WRITE_PARAMETER);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(8 + data.length);
        serializeUInt16(1 + data.length);
        serializeUInt8(parameter.getKey());
        serializeUInt8Array(data);

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(70);
        builder.append("WriteParameterRequest [sequence=");
        builder.append(sequence);
        builder.append(", parameter=");
        builder.append(parameter);
        builder.append(", value=");
        builder.append(value);
        builder.append(']');
        return builder.toString();
    }
}
