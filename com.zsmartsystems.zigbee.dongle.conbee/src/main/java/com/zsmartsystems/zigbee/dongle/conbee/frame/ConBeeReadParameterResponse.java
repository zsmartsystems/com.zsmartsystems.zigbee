package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterResponse extends ConBeeFrameResponse {
    private ConBeeNetworkParameter parameter;
    private ConBeeStatus status;
    private int[] value;

    ConBeeReadParameterResponse(int[] buffer) {
        super(buffer);

        if (deserializeUInt8() != READ_PARAMETER) {
            throw new IllegalArgumentException();
        }
        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        int size = deserializeUInt16();
        parameter = ConBeeNetworkParameter.getParameterType(deserializeUInt8());
        value = Arrays.copyOfRange(buffer, length, length + size);
    }

    public ConBeeStatus getStatus() {
        return status;
    }

    public ConBeeNetworkParameter getParameter() {
        return parameter;
    }

    @Override
    public int[] getOutputBuffer() {
        serializeUInt8(READ_PARAMETER);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(9);
        serializeUInt16(1);
        serializeUInt8(parameter.getKey());

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReadParameterResponse [parameter=");
        builder.append(parameter);
        builder.append(", value=");
        boolean first = true;
        for (int val : value) {
            if (first == false) {
                builder.append(' ');
            }
            first = false;
            builder.append(String.format("%02X", val));
        }
        builder.append(']');
        return builder.toString();
    }

}
