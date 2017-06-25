package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterResponse extends ConBeeFrame {
    private final int READ_PARAMETER = 0x0a;

    private ConBeeNetworkParameter parameter;
    private ConBeeStatus status;
    private int[] value;

    ConBeeReadParameterResponse(int[] buffer) {
        if (buffer[0] != READ_PARAMETER) {
            throw new IllegalArgumentException();
        }
        sequence = buffer[1];
        // ConBeeStatus.
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
        for (int val : value) {
            if (val != 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", val));
        }
        builder.append(']');
        return builder.toString();
    }

}
