/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(110);
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
