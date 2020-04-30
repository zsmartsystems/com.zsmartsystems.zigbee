/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>customFrameHandler</b>.
 * <p>
 * A callback indicating a custom EZSP message has been received.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspCustomFrameHandler extends EzspFrameResponse {
    public static final int FRAME_ID = 0x54;

    /**
     * The length of the custom frame payload.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int payloadLength;

    /**
     * The payload of the custom frame.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] payload;

    /**
     * Response and Handler constructor
     */
    public EzspCustomFrameHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        payloadLength = deserializer.deserializeUInt8();
        payload = deserializer.deserializeUInt8Array(payloadLength);
    }

    /**
     * The length of the custom frame payload.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current payloadLength as {@link int}
     */
    public int getPayloadLength() {
        return payloadLength;
    }

    /**
     * The length of the custom frame payload.
     *
     * @param payloadLength the payloadLength to set as {@link int}
     */
    public void setPayloadLength(int payloadLength) {
        this.payloadLength = payloadLength;
    }

    /**
     * The payload of the custom frame.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     *
     * @return the current payload as {@link int[]}
     */
    public int[] getPayload() {
        return payload;
    }

    /**
     * The payload of the custom frame.
     *
     * @param payload the payload to set as {@link int[]}
     */
    public void setPayload(int[] payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(75);
        builder.append("EzspCustomFrameHandler [payloadLength=");
        builder.append(payloadLength);
        builder.append(", payload=");
        for (int cnt = 0; cnt < payload.length; cnt++) {
            if (cnt > 0) {
                builder.append(' ');
            }
            builder.append(payload[cnt]);
        }
        builder.append(']');
        return builder.toString();
    }
}
