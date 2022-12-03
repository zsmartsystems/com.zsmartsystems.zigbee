/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspValueId;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>setValue</b>.
 * <p>
 * Writes a value to the NCP.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSetValueRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0xAB;

    /**
     * Identifies which policy to change.
     * <p>
     * EZSP type is <i>EzspValueId</i> - Java type is {@link EzspValueId}
     */
    private EzspValueId valueId;

    /**
     * The new value.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] value;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspSetValueRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * Identifies which policy to change.
     * <p>
     * EZSP type is <i>EzspValueId</i> - Java type is {@link EzspValueId}
     *
     * @return the current valueId as {@link EzspValueId}
     */
    public EzspValueId getValueId() {
        return valueId;
    }

    /**
     * Identifies which policy to change.
     *
     * @param valueId the valueId to set as {@link EzspValueId}
     */
    public void setValueId(EzspValueId valueId) {
        this.valueId = valueId;
    }

    /**
     * The new value.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     *
     * @return the current value as {@link int[]}
     */
    public int[] getValue() {
        return value;
    }

    /**
     * The new value.
     *
     * @param value the value to set as {@link int[]}
     */
    public void setValue(int[] value) {
        this.value = value;
    }

    @Override
    public int[] serialize(int ezspVersion) {
        // Serialize the header
        serializeHeader(ezspVersion, serializer);

        // Serialize the fields
        serializer.serializeEzspValueId(valueId);
        serializer.serializeUInt8(value.length);
        serializer.serializeUInt8Array(value);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(122);
        builder.append("EzspSetValueRequest [networkId=");
        builder.append(networkId);
        builder.append(", valueId=");
        builder.append(valueId);
        builder.append(", value=");
        for (int cnt = 0; cnt < value.length; cnt++) {
            if (cnt > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", value[cnt]));
        }
        builder.append(']');
        return builder.toString();
    }
}
