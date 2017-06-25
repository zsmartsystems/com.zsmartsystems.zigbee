package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadParameterRequest extends ConBeeFrame {
    private final int READ_PARAMETER = 0x0a;

    private ConBeeNetworkParameter parameter;

    public void setParameter(ConBeeNetworkParameter parameter) {
        this.parameter = parameter;
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
        builder.append("ReadParameter [parameter=");
        builder.append(parameter);
        builder.append(']');
        return builder.toString();
    }

}
