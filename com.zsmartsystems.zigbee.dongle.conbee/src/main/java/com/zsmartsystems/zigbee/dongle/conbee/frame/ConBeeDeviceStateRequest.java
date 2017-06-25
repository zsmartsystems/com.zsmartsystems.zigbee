package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeDeviceStateRequest extends ConBeeFrame {
    private final int DEVICE_STATE = 0x07;

    private ConBeeNetworkState state;

    public void setParameter(ConBeeNetworkState state) {
        this.state = state;
    }

    @Override
    public int[] getOutputBuffer() {
        serializeUInt8(DEVICE_STATE);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(8);
        serializeUInt8(0);
        serializeUInt8(0);
        serializeUInt8(0);

        return copyOutputBuffer();
    }

}
