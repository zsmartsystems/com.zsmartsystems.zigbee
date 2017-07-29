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
        StringBuilder builder = new StringBuilder();
        builder.append("ReadParameterRequest [sequence=");
        builder.append(sequence);
        builder.append(", parameter=");
        builder.append(parameter);
        builder.append(']');
        return builder.toString();
    }

}
