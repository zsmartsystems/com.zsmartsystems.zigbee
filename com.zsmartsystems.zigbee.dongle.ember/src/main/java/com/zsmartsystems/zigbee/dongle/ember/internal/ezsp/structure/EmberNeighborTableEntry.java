/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.serializer.EzspDeserializer;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.serializer.EzspSerializer;

/**
 * Class to implement the Ember Structure <b>EmberNeighborTableEntry</b>.
 * <p>
 * A neighbor table entry stores information about the reliability of RF links to and from
 * neighboring nodes.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EmberNeighborTableEntry {

    /**
     * The neighbor's two byte network id.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int shortId;

    /**
     * An exponentially weighted moving average of the link quality values of incoming packets
     * from this neighbor as reported by the PHY.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int averageLqi;

    /**
     * The incoming cost for this neighbor, computed from the average LQI. Values range from 1 for a
     * good link to 7 for a bad link.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int inCost;

    /**
     * The outgoing cost for this neighbor, obtained from the most recently received neighbor
     * exchange message from the neighbor. A value of zero means that a neighbor exchange message
     * from the neighbor has not been received recently enough, or that our id was not present in the
     * most recently received one.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int outCost;

    /**
     * The number of aging periods elapsed since a link status message was last received from this
     * neighbor. The aging period is 16 seconds.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int age;

    /**
     * The 8 byte EUI64 of the neighbor.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     */
    private IeeeAddress longId;

    /**
     * Default Constructor
     */
    public EmberNeighborTableEntry() {
    }

    public EmberNeighborTableEntry(EzspDeserializer deserializer) {
        deserialize(deserializer);
    }

    /**
     * The neighbor's two byte network id.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current shortId as {@link int}
     */
    public int getShortId() {
        return shortId;
    }

    /**
     * The neighbor's two byte network id.
     *
     * @param shortId the shortId to set as {@link int}
     */
    public void setShortId(int shortId) {
        this.shortId = shortId;
    }

    /**
     * An exponentially weighted moving average of the link quality values of incoming packets
     * from this neighbor as reported by the PHY.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current averageLqi as {@link int}
     */
    public int getAverageLqi() {
        return averageLqi;
    }

    /**
     * An exponentially weighted moving average of the link quality values of incoming packets
     * from this neighbor as reported by the PHY.
     *
     * @param averageLqi the averageLqi to set as {@link int}
     */
    public void setAverageLqi(int averageLqi) {
        this.averageLqi = averageLqi;
    }

    /**
     * The incoming cost for this neighbor, computed from the average LQI. Values range from 1 for a
     * good link to 7 for a bad link.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current inCost as {@link int}
     */
    public int getInCost() {
        return inCost;
    }

    /**
     * The incoming cost for this neighbor, computed from the average LQI. Values range from 1 for a
     * good link to 7 for a bad link.
     *
     * @param inCost the inCost to set as {@link int}
     */
    public void setInCost(int inCost) {
        this.inCost = inCost;
    }

    /**
     * The outgoing cost for this neighbor, obtained from the most recently received neighbor
     * exchange message from the neighbor. A value of zero means that a neighbor exchange message
     * from the neighbor has not been received recently enough, or that our id was not present in the
     * most recently received one.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current outCost as {@link int}
     */
    public int getOutCost() {
        return outCost;
    }

    /**
     * The outgoing cost for this neighbor, obtained from the most recently received neighbor
     * exchange message from the neighbor. A value of zero means that a neighbor exchange message
     * from the neighbor has not been received recently enough, or that our id was not present in the
     * most recently received one.
     *
     * @param outCost the outCost to set as {@link int}
     */
    public void setOutCost(int outCost) {
        this.outCost = outCost;
    }

    /**
     * The number of aging periods elapsed since a link status message was last received from this
     * neighbor. The aging period is 16 seconds.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current age as {@link int}
     */
    public int getAge() {
        return age;
    }

    /**
     * The number of aging periods elapsed since a link status message was last received from this
     * neighbor. The aging period is 16 seconds.
     *
     * @param age the age to set as {@link int}
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * The 8 byte EUI64 of the neighbor.
     * <p>
     * EZSP type is <i>EmberEUI64</i> - Java type is {@link IeeeAddress}
     *
     * @return the current longId as {@link IeeeAddress}
     */
    public IeeeAddress getLongId() {
        return longId;
    }

    /**
     * The 8 byte EUI64 of the neighbor.
     *
     * @param longId the longId to set as {@link IeeeAddress}
     */
    public void setLongId(IeeeAddress longId) {
        this.longId = longId;
    }

    /**
     * Serialise the contents of the EZSP structure.
     *
     * @param serializer the {@link EzspSerializer} used to serialize
     */
    public int[] serialize(EzspSerializer serializer) {
        // Serialize the fields
        serializer.serializeUInt16(shortId);
        serializer.serializeUInt8(averageLqi);
        serializer.serializeUInt8(inCost);
        serializer.serializeUInt8(outCost);
        serializer.serializeUInt8(age);
        serializer.serializeEmberEui64(longId);
        return serializer.getPayload();
    }

    /**
     * Deserialise the contents of the EZSP structure.
     *
     * @param deserializer the {@link EzspDeserializer} used to deserialize
     */
    public void deserialize(EzspDeserializer deserializer) {
        // Deserialize the fields
        shortId = deserializer.deserializeUInt16();
        averageLqi = deserializer.deserializeUInt8();
        inCost = deserializer.deserializeUInt8();
        outCost = deserializer.deserializeUInt8();
        age = deserializer.deserializeUInt8();
        longId = deserializer.deserializeEmberEui64();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(176);
        builder.append("EmberNeighborTableEntry [shortId=");
        builder.append(shortId);
        builder.append(", averageLqi=");
        builder.append(averageLqi);
        builder.append(", inCost=");
        builder.append(inCost);
        builder.append(", outCost=");
        builder.append(outCost);
        builder.append(", age=");
        builder.append(age);
        builder.append(", longId=");
        builder.append(longId);
        builder.append(']');
        return builder.toString();
    }
}
