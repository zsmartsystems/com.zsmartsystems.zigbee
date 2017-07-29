package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeEnqueueSendDataResponse extends ConBeeFrameResponse {
    private ConBeeNetworkParameter parameter;
    private ConBeeNetworkState state;

    ConBeeEnqueueSendDataResponse(final int[] response) {
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

    public ConBeeNetworkParameter getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EnqueueSendDataResponse [sequence=");
        builder.append(sequence);
        builder.append(", status=");
        builder.append(status);
        builder.append(", state=");
        builder.append(state);
        builder.append(']');
        return builder.toString();
    }

}
