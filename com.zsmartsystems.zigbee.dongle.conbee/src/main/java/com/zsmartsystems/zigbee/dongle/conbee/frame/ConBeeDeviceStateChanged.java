/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.conbee.frame;

/**
 * When the device receives a data frame an unsolicited DEVICE_STATE_CHANGED command will be send to the application.
 *
 * @author Chris Jackson
 *
 */
public class ConBeeDeviceStateChanged extends ConBeeFrameResponse {
    private ConBeeDeviceState state;

    public ConBeeDeviceStateChanged(final int[] response) {
        super(response);

        if (deserializeUInt8() != DEVICE_STATE_CHANGED) {
            throw new IllegalArgumentException();
        }

        sequence = deserializeUInt8();
        status = deserializeStatus();
        deserializeUInt16();
        state = deserializeDeviceState();
    }

    @Override
    public ConBeeDeviceState getDeviceState() {
        return state;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(110);
        builder.append("DeviceStateChanged [sequence=");
        builder.append(sequence);
        builder.append(", status=");
        builder.append(status);
        builder.append(", state=");
        builder.append(state);
        builder.append(']');
        return builder.toString();
    }

}
