/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameResponse;

/**
 * Class to implement the Ember EZSP command <b>findKeyTableEntry</b>.
 * <p>
 * This function searches through the Key Table and tries to find the entry that matches the
 * passed search criteria.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspFindKeyTableEntryResponse extends EzspFrameResponse {
    public static final int FRAME_ID = 0x75;

    /**
     * This indicates the index of the entry that matches the search criteria. A value of 0xFF is
     * returned if not matching entry is found.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int index;

    /**
     * Response and Handler constructor
     */
    public EzspFindKeyTableEntryResponse(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        index = deserializer.deserializeUInt8();
    }

    /**
     * This indicates the index of the entry that matches the search criteria. A value of 0xFF is
     * returned if not matching entry is found.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current index as {@link int}
     */
    public int getIndex() {
        return index;
    }

    /**
     * This indicates the index of the entry that matches the search criteria. A value of 0xFF is
     * returned if not matching entry is found.
     *
     * @param index the index to set as {@link int}
     */
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(82);
        builder.append("EzspFindKeyTableEntryResponse [networkId=");
        builder.append(networkId);
        builder.append(", index=");
        builder.append(index);
        builder.append(']');
        return builder.toString();
    }
}
