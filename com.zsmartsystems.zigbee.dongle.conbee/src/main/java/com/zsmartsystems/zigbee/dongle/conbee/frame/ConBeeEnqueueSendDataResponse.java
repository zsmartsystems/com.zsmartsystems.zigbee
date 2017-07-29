package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 * A data response with a status of SUCCESS signals that the request is enqueued and will be processed by the device.
 * Note that the response does not reflect the actual completion of the request, which should be further monitored with
 * an APSDE-DATA.confirm command as soon as the relevant flag is set in the device status fields. The APS Request ID
 * shall be used to match a specific request to its confirmation.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeEnqueueSendDataResponse extends ConBeeFrameResponse {
    private ConBeeDeviceState state;

    public ConBeeEnqueueSendDataResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != APS_DATA_REQUEST) {
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
