package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeChangeNetworkStateResponse extends ConBeeFrameResponse {
    private ConBeeDeviceState state;

    public ConBeeChangeNetworkStateResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != CHANGE_NETWORK_STATE) {
            throw new IllegalArgumentException();
        }

        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        state = deserializeDeviceState();
    }

    public ConBeeDeviceState getDeviceState() {
        return state;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ChangeNetworkStateResponse [sequence=");
        builder.append(sequence);
        builder.append(", status=");
        builder.append(status);
        builder.append(", state=");
        builder.append(state);
        builder.append(']');
        return builder.toString();
    }

}
