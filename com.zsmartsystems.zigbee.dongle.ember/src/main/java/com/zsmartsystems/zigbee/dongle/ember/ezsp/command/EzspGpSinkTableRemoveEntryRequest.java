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
 * Class to implement the Ember EZSP command <b>gpSinkTableRemoveEntry</b>.
 * <p>
 * Removes the sink table entry stored at the passed index.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspGpSinkTableRemoveEntryRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0xE0;

    /**
     * The index of the requested sink entry.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int sinkIndex;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspGpSinkTableRemoveEntryRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The index of the requested sink entry.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current sinkIndex as {@link int}
     */
    public int getSinkIndex() {
        return sinkIndex;
    }

    /**
     * The index of the requested sink entry.
     *
     * @param sinkIndex the sinkIndex to set as {@link int}
     */
    public void setSinkIndex(int sinkIndex) {
        this.sinkIndex = sinkIndex;
    }

    @Override
    public int[] serialize(int ezspVersion) {
        // Serialize the header
        serializeHeader(ezspVersion, serializer);

        // Serialize the fields
        serializer.serializeUInt8(sinkIndex);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(86);
        builder.append("EzspGpSinkTableRemoveEntryRequest [networkId=");
        builder.append(networkId);
        builder.append(", sinkIndex=");
        builder.append(sinkIndex);
        builder.append(']');
        return builder.toString();
    }
}
