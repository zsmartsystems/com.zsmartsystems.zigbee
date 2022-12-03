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
 * Class to implement the Ember EZSP command <b>getSourceRouteTableEntry</b>.
 * <p>
 * Returns information about a source route table entry.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetSourceRouteTableEntryRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0xC1;

    /**
     * The index of the entry of interest in the source route table. Possible indexes range from zero
     * to SOURCE_ROUTE_TABLE_FILLED_SIZE.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int index;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspGetSourceRouteTableEntryRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The index of the entry of interest in the source route table. Possible indexes range from zero
     * to SOURCE_ROUTE_TABLE_FILLED_SIZE.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current index as {@link int}
     */
    public int getIndex() {
        return index;
    }

    /**
     * The index of the entry of interest in the source route table. Possible indexes range from zero
     * to SOURCE_ROUTE_TABLE_FILLED_SIZE.
     *
     * @param index the index to set as {@link int}
     */
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int[] serialize(int ezspVersion) {
        // Serialize the header
        serializeHeader(ezspVersion, serializer);

        // Serialize the fields
        serializer.serializeUInt8(index);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(88);
        builder.append("EzspGetSourceRouteTableEntryRequest [networkId=");
        builder.append(networkId);
        builder.append(", index=");
        builder.append(index);
        builder.append(']');
        return builder.toString();
    }
}
