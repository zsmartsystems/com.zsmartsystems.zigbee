package com.zsmartsystems.zigbee.dongle.conbee.frame;

import java.util.Arrays;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterResponse extends ConBeeFrameResponse {
    private ConBeeNetworkParameter parameter;
    private int[] value;

    ConBeeReadParameterResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != READ_PARAMETER) {
            throw new IllegalArgumentException();
        }
        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        int size = deserializeUInt16() - 1;
        parameter = ConBeeNetworkParameter.getParameterType(deserializeUInt8());
        value = Arrays.copyOfRange(buffer, length, length + size);
    }

    public ConBeeNetworkParameter getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReadParameterResponse [sequence=");
        builder.append(sequence);
        builder.append(", parameter=");
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
