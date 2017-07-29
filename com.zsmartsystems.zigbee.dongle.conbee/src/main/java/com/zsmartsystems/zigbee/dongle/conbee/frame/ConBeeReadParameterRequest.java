package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
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
        StringBuilder builder = new StringBuilder();
        builder.append("ReadParameterRequest [sequence=");
        builder.append(sequence);
        builder.append(", parameter=");
        builder.append(parameter);
        builder.append(']');
        return builder.toString();
    }

}
