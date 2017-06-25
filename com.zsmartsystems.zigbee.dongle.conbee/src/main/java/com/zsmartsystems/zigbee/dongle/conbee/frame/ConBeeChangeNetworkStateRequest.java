package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeChangeNetworkStateRequest extends ConBeeFrame {
    private final int CHANGE_NETWORK_STATE = 0x08;

    private ConBeeNetworkState state;

    public void setState(ConBeeNetworkState state) {
        this.state = state;
    }

    @Override
    public int[] getOutputBuffer() {
        serializeUInt8(CHANGE_NETWORK_STATE);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(6);
        serializeUInt8(0);

        return copyOutputBuffer();
    }

}
