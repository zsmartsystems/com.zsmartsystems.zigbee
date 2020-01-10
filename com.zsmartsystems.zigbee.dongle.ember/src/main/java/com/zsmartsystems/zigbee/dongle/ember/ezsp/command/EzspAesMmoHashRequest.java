/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberAesMmoHashContext;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember EZSP command <b>aesMmoHash</b>.
 * <p>
 * This routine processes the passed chunk of data and updates the hash context based on it. If
 * the 'finalize' parameter is not set, then the length of the data passed in must be a multiple of
 * 16. If the 'finalize' parameter is set then the length can be any value up 1-16, and the final
 * hash value will be calculated.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspAesMmoHashRequest extends EzspFrameRequest {
    public static final int FRAME_ID = 0x6F;

    /**
     * The hash context to update.
     * <p>
     * EZSP type is <i>EmberAesMmoHashContext</i> - Java type is {@link EmberAesMmoHashContext}
     */
    private EmberAesMmoHashContext context;

    /**
     * This indicates whether the final hash value should be calculated.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     */
    private boolean finalize;

    /**
     * The length of the data to hash.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int length;

    /**
     * The data to hash.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] data;

    /**
     * Serialiser used to serialise to binary line data
     */
    private EzspSerializer serializer;

    /**
     * Request constructor
     */
    public EzspAesMmoHashRequest() {
        frameId = FRAME_ID;
        serializer = new EzspSerializer();
    }

    /**
     * The hash context to update.
     * <p>
     * EZSP type is <i>EmberAesMmoHashContext</i> - Java type is {@link EmberAesMmoHashContext}
     *
     * @return the current context as {@link EmberAesMmoHashContext}
     */
    public EmberAesMmoHashContext getContext() {
        return context;
    }

    /**
     * The hash context to update.
     *
     * @param context the context to set as {@link EmberAesMmoHashContext}
     */
    public void setContext(EmberAesMmoHashContext context) {
        this.context = context;
    }

    /**
     * This indicates whether the final hash value should be calculated.
     * <p>
     * EZSP type is <i>bool</i> - Java type is {@link boolean}
     *
     * @return the current finalize as {@link boolean}
     */
    public boolean getFinalize() {
        return finalize;
    }

    /**
     * This indicates whether the final hash value should be calculated.
     *
     * @param finalize the finalize to set as {@link boolean}
     */
    public void setFinalize(boolean finalize) {
        this.finalize = finalize;
    }

    /**
     * The length of the data to hash.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current length as {@link int}
     */
    public int getLength() {
        return length;
    }

    /**
     * The length of the data to hash.
     *
     * @param length the length to set as {@link int}
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * The data to hash.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     *
     * @return the current data as {@link int[]}
     */
    public int[] getData() {
        return data;
    }

    /**
     * The data to hash.
     *
     * @param data the data to set as {@link int[]}
     */
    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(serializer);

        // Serialize the fields
        serializer.serializeEmberAesMmoHashContext(context);
        serializer.serializeBool(finalize);
        serializer.serializeUInt8(length);
        serializer.serializeUInt8Array(data);
        return serializer.getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(124);
        builder.append("EzspAesMmoHashRequest [context=");
        builder.append(context);
        builder.append(", finalize=");
        builder.append(finalize);
        builder.append(", length=");
        builder.append(length);
        builder.append(", data=");
        for (int cnt = 0; cnt < data.length; cnt++) {
            if (cnt > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", data[cnt]));
        }
        builder.append(']');
        return builder.toString();
    }
}
