package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 * The device state determines if the device is operation in a ZigBee network and if so, various flags provide the state
 * of incoming and outgoing command queues. The ‘Network state’ field value can be NET_OFFLINE, NET_CONNECTED,
 * NET_JOINING and NET_LEAVING.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeDeviceStateResponse extends ConBeeFrameResponse {
    private ConBeeDeviceState state;

    public ConBeeDeviceStateResponse(final int[] response) {
        super(response);

        if (deserializeUInt8() != DEVICE_STATE) {
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
        builder.append("DeviceStateResponse [sequence=");
        builder.append(sequence);
        builder.append(", status=");
        builder.append(status);
        builder.append(", state=");
        builder.append(state);
        builder.append(']');
        return builder.toString();
    }

}
