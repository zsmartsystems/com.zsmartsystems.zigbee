/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>neighborCount</b>.
 * <p>
 * Returns the number of active entries in the neighbor table.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspNeighborCountResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x7A;

    /**
     * The number of active entries in the neighbor table
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int value;

    /**
     * Response and Handler constructor
     */
    public EzspNeighborCountResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        value = deserializer.deserializeUInt8();
    }

    /**
     * The number of active entries in the neighbor table
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current value as {@link int}
     */
    public int getValue() {
        return value;
    }

    /**
     * The number of active entries in the neighbor table
     *
     * @param value the value to set as {@link int}
     */
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(78);
        builder.append("EzspNeighborCountResponse [networkId=");
        builder.append(networkId);
        builder.append(", value=");
        builder.append(value);
        builder.append(']');
        return builder.toString();
    }
}
