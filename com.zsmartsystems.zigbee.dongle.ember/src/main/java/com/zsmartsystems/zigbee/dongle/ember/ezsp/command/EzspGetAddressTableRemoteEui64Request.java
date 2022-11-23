/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>getAddressTableRemoteEui64</b>.
 * <p>
 * Gets the EUI64 of an address table entry.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGetAddressTableRemoteEui64Request extends EzspFrameRequest {
    public static final int FRAME_ID = 0x5E;

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
    public EzspGetAddressTableRemoteEui64Request() {
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
    public int[] serialize(int ezspVersion) {
        // Serialize the header
        serializeHeader(ezspVersion, serializer);

        // Serialize the fields
        serializer.serializeUInt8(addressTableIndex);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(90);
        builder.append("EzspGetAddressTableRemoteEui64Request [networkId=");
        builder.append(networkId);
        builder.append(", addressTableIndex=");
        builder.append(addressTableIndex);
        builder.append(']');
        return builder.toString();
    }
}
