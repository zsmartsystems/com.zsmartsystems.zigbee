package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeWriteParameterRequest extends ConBeeFrame {
    private final int WRITE_PARAMETER = 0x0b;

    private ConBeeNetworkParameter parameter;

    public void setParameter(ConBeeNetworkParameter parameter) {
        this.parameter = parameter;
    }

    public void setValue() {

    }

    @Override
    public int[] getOutputBuffer() {
        serializeUInt8(WRITE_PARAMETER);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(0);
        serializeUInt16(0);
        serializeUInt8(parameter.getKey());
        serializeUInt8(0);

        return copyOutputBuffer();
    }

}
