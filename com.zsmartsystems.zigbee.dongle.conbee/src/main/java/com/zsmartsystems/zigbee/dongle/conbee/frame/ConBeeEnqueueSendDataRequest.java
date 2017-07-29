package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeEnqueueSendDataRequest extends ConBeeFrameRequest {

    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        serializeUInt8(APS_DATA_REQUEST);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(8);
        serializeUInt16(0); // length!!!!!!
        serializeUInt8(0);
        serializeUInt8(0);

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeviceStateRequest [sequence=");
        builder.append(sequence);
        builder.append(']');
        return builder.toString();
    }

}
