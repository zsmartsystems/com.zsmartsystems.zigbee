package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeChangeNetworkStateRequest extends ConBeeFrame {
    private ConBeeNetworkState state;

    public void setState(ConBeeNetworkState state) {
        this.state = state;
    }

    @Override
    public int[] getOutputBuffer() {
        super.getOutputBuffer();

        serializeUInt8(CHANGE_NETWORK_STATE);
        serializeUInt8(sequence);
        serializeUInt8(0);
        serializeUInt16(6);
        serializeUInt8(state.ordinal());

        return copyOutputBuffer();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ChangeNetworkStateRequest [sequence=");
        builder.append(sequence);
        builder.append(']');
        return builder.toString();
    }
}
