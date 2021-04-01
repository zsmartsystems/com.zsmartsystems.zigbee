/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>setPowerDescriptor</b>.
 * <p>
 * Sets the power descriptor to the specified value. The power descriptor is a dynamic value,
 * therefore you should call this function whenever the value changes.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspSetPowerDescriptorRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x16;

    /**
     * The new power descriptor for the local node.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int descriptor;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspSetPowerDescriptorRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The new power descriptor for the local node.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current descriptor as {@link int}
     */
    public int getDescriptor() {
        return descriptor;
    }

    /**
     * The new power descriptor for the local node.
     *
     * @param descriptor the descriptor to set as {@link int}
     */
    public void setDescriptor(int descriptor) {
        this.descriptor = descriptor;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt16(descriptor);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(82);
        builder.append("EzspSetPowerDescriptorRequest [networkId=");
        builder.append(networkId);
        builder.append(", descriptor=");
        builder.append(descriptor);
        builder.append(']');
        return builder.toString();
    }
}
