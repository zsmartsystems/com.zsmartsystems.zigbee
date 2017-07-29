package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeWriteParameterRequest extends ConBeeFrame {

    private ConBeeNetworkParameter parameter;

    public void setParameter(ConBeeNetworkParameter parameter) {
        this.parameter = parameter;
    }

    public void setValue() {

    }

    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        serializeUInt8(WRITE_PARAMETER);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(0);
        serializeUInt16(0);
        serializeUInt8(parameter.getKey());
        serializeUInt8(0);

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WriteParameterRequest [sequence=");
        builder.append(sequence);
        builder.append(", parameter=");
        builder.append(parameter);
        builder.append(']');
        return builder.toString();
    }
}
