package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeDeviceStateResponse extends ConBeeFrameResponse {
    private ConBeeStatus status;
    private ConBeeNetworkState state;

    public ConBeeDeviceStateResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != DEVICE_STATE) {
            throw new IllegalArgumentException();
        }

        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();

        int tmp = deserializeUInt8();
        state = ConBeeNetworkState.values()[tmp & 0x03];
    }

    public void setParameter(ConBeeNetworkState state) {
        this.state = state;
    }

    public ConBeeStatus getStatus() {
        return status;
    }

    public ConBeeNetworkState getNetworkState() {
        return state;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConBeeDeviceStateResponse [sequence=");
        builder.append(sequence);
        builder.append(", status=");
        builder.append(status);
        builder.append(", state=");
        builder.append(state);
        builder.append(']');
        return builder.toString();
    }

}
