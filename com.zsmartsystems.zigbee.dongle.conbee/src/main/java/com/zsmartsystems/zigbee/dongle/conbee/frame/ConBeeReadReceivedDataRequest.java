package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeReadReceivedDataRequest extends ConBeeFrameRequest {
    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        serializeUInt8(APS_DATA_INDICATION);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(7);
        serializeUInt16(0);

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ReadReceivedDataRequest [sequence=");
        builder.append(sequence);
        builder.append(']');
        return builder.toString();
    }

}
