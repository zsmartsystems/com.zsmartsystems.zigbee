package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeChangeNetworkStateResponse extends ConBeeFrameResponse {
    private ConBeeNetworkState state;

    public ConBeeChangeNetworkStateResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != CHANGE_NETWORK_STATE) {
            throw new IllegalArgumentException();
        }

        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();

        int tmp = deserializeUInt8();
        state = ConBeeNetworkState.values()[tmp];
    }

    public void setParameter(ConBeeNetworkState state) {
        this.state = state;
    }

    public ConBeeNetworkState getNetworkState() {
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
