/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Measurement Profile Command value object class.
 * <p>
 * Cluster: <b>Electrical Measurement</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Electrical Measurement cluster.
 * <p>
 * Retrieves an electricity measurement profile from the electricity measurement server for
 * a specific attribute ID requested.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetMeasurementProfileCommand extends ZclCommand {
    /**
     * Attribute ID command message field.
     */
    private Integer attributeId;

    /**
     * Start Time command message field.
     */
    private Integer startTime;

    /**
     * Number Of Intervals command message field.
     */
    private Integer numberOfIntervals;

    /**
     * Default constructor.
     */
    public GetMeasurementProfileCommand() {
        genericCommand = false;
        clusterId = 0x0B04;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Attribute ID.
     *
     * @return the Attribute ID
     */
    public Integer getAttributeId() {
        return attributeId;
    }

    /**
     * Sets Attribute ID.
     *
     * @param attributeId the Attribute ID
     */
    public void setAttributeId(final Integer attributeId) {
        this.attributeId = attributeId;
    }

    /**
     * Gets Start Time.
     *
     * @return the Start Time
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * Sets Start Time.
     *
     * @param startTime the Start Time
     */
    public void setStartTime(final Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets Number Of Intervals.
     *
     * @return the Number Of Intervals
     */
    public Integer getNumberOfIntervals() {
        return numberOfIntervals;
    }

    /**
     * Sets Number Of Intervals.
     *
     * @param numberOfIntervals the Number Of Intervals
     */
    public void setNumberOfIntervals(final Integer numberOfIntervals) {
        this.numberOfIntervals = numberOfIntervals;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(attributeId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(startTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(numberOfIntervals, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        attributeId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        startTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        numberOfIntervals = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        builder.append("GetMeasurementProfileCommand [");
        builder.append(super.toString());
        builder.append(", attributeId=");
        builder.append(attributeId);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", numberOfIntervals=");
        builder.append(numberOfIntervals);
        builder.append(']');
        return builder.toString();
    }

}
