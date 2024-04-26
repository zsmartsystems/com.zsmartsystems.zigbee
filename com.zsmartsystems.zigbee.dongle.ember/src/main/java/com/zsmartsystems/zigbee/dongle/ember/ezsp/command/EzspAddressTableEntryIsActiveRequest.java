/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>addressTableEntryIsActive</b>.
 * <p>
 * Indicates whether any messages are currently being sent using this address table entry.
 * Note that this function does not indicate whether the address table entry is unused. To
 * determine whether an address table entry is unused, check the remote node ID. The remote node
 * ID will have the value EMBER_TABLE_ENTRY_UNUSED_NODE_ID when the address table entry is
 * not in use.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspAddressTableEntryIsActiveRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x5B;

    /**
     * The index of an address table entry.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int addressTableIndex;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspAddressTableEntryIsActiveRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The index of an address table entry.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current addressTableIndex as {@link int}
     */
    public int getAddressTableIndex() {
        return addressTableIndex;
    }

    /**
     * The index of an address table entry.
     *
     * @param addressTableIndex the addressTableIndex to set as {@link int}
     */
    public void setAddressTableIndex(int addressTableIndex) {
        this.addressTableIndex = addressTableIndex;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeUInt8(addressTableIndex);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(89);
        builder.append("EzspAddressTableEntryIsActiveRequest [networkId=");
        builder.append(networkId);
        builder.append(", addressTableIndex=");
        builder.append(addressTableIndex);
        builder.append(']');
        return builder.toString();
    }
}
