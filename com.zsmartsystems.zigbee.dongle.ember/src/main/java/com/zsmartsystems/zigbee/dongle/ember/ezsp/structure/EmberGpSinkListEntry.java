/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.structure;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspDeserializer;
import com.zsmartsystems.zigbee.dongle.ember.internal.serializer.EzspSerializer;

/**
 * Class to implement the Ember Structure <b>EmberGpSinkListEntry</b>.
 * <p>
 * A sink list entry
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EmberGpSinkListEntry {

    /**
     * The sink list type.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int type;

    /**
     * The EUI64 of the target sink.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress sinkEui;

    /**
     * The short address of the target sink.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     */
    private int sinkNodeId;

    /**
     * Default Constructor
     */
    public EmberGpSinkListEntry() {
    }

    public EmberGpSinkListEntry(EzspDeserializer deserializer) {
        deserialize(deserializer);
    }

    /**
     * The sink list type.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current type as {@link int}
     */
    public int getType() {
        return type;
    }

    /**
     * The sink list type.
     *
     * @param type the type to set as {@link int}
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * The EUI64 of the target sink.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current sinkEui as {@link IeeeAddress}
     */
    public IeeeAddress getSinkEui() {
        return sinkEui;
    }

    /**
     * The EUI64 of the target sink.
     *
     * @param sinkEui the sinkEui to set as {@link IeeeAddress}
     */
    public void setSinkEui(IeeeAddress sinkEui) {
        this.sinkEui = sinkEui;
    }

    /**
     * The short address of the target sink.
     * <p>
     * EZSP type is <i>EmberNodeId</i> - Java type is {@link int}
     *
     * @return the current sinkNodeId as {@link int}
     */
    public int getSinkNodeId() {
        return sinkNodeId;
    }

    /**
     * The short address of the target sink.
     *
     * @param sinkNodeId the sinkNodeId to set as {@link int}
     */
    public void setSinkNodeId(int sinkNodeId) {
        this.sinkNodeId = sinkNodeId;
    }

    /**
     * Serialise the contents of the EZSP structure.
     *
     * @param serializer the {@link EzspSerializer} used to serialize
     */
    public int[] serialize(EzspSerializer serializer) {
        // Serialize the fields
        serializer.serializeUInt8(type);
        serializer.serializeEmberEui64(sinkEui);
        serializer.serializeUInt16(sinkNodeId);
        return serializer.getPayload();
    }

    /**
     * Deserialise the contents of the EZSP structure.
     *
     * @param deserializer the {@link EzspDeserializer} used to deserialize
     */
    public void deserialize(EzspDeserializer deserializer) {
        // Deserialize the fields
        type = deserializer.deserializeUInt8();
        sinkEui = deserializer.deserializeEmberEui64();
        sinkNodeId = deserializer.deserializeUInt16();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(98);
        builder.append("EmberGpSinkListEntry [type=");
        builder.append(type);
        builder.append(", sinkEui=");
        builder.append(sinkEui);
        builder.append(", sinkNodeId=");
        builder.append(String.format("%04X", sinkNodeId));
        builder.append(']');
        return builder.toString();
    }
}
