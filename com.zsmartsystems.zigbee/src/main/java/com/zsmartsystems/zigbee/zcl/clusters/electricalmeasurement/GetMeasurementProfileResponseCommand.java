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
 * Get Measurement Profile Response Command value object class.
 * <p>
 * Cluster: <b>Electrical Measurement</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Electrical Measurement cluster.
 * <p>
 * Returns the electricity measurement profile. The electricity measurement profile
 * includes information regarding the amount of time used to capture data related to the flow of
 * electricity as well as the intervals thes
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetMeasurementProfileResponseCommand extends ZclCommand {
    /**
     * Start Time command message field.
     */
    private Integer startTime;

    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Profile Interval Period command message field.
     */
    private Integer profileIntervalPeriod;

    /**
     * Number Of Intervals Delivered command message field.
     */
    private Integer numberOfIntervalsDelivered;

    /**
     * Attribute ID command message field.
     */
    private Integer attributeId;

    /**
     * Intervals command message field.
     */
    private Integer intervals;

    /**
     * Default constructor.
     */
    public GetMeasurementProfileResponseCommand() {
        genericCommand = false;
        clusterId = 0x0B04;
        commandId = 1;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     * Gets Status.
     *
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    /**
     * Gets Profile Interval Period.
     *
     * @return the Profile Interval Period
     */
    public Integer getProfileIntervalPeriod() {
        return profileIntervalPeriod;
    }

    /**
     * Sets Profile Interval Period.
     *
     * @param profileIntervalPeriod the Profile Interval Period
     */
    public void setProfileIntervalPeriod(final Integer profileIntervalPeriod) {
        this.profileIntervalPeriod = profileIntervalPeriod;
    }

    /**
     * Gets Number Of Intervals Delivered.
     *
     * @return the Number Of Intervals Delivered
     */
    public Integer getNumberOfIntervalsDelivered() {
        return numberOfIntervalsDelivered;
    }

    /**
     * Sets Number Of Intervals Delivered.
     *
     * @param numberOfIntervalsDelivered the Number Of Intervals Delivered
     */
    public void setNumberOfIntervalsDelivered(final Integer numberOfIntervalsDelivered) {
        this.numberOfIntervalsDelivered = numberOfIntervalsDelivered;
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
     * Gets Intervals.
     *
     * @return the Intervals
     */
    public Integer getIntervals() {
        return intervals;
    }

    /**
     * Sets Intervals.
     *
     * @param intervals the Intervals
     */
    public void setIntervals(final Integer intervals) {
        this.intervals = intervals;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(startTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(profileIntervalPeriod, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(numberOfIntervalsDelivered, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(attributeId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(intervals, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        profileIntervalPeriod = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        numberOfIntervalsDelivered = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        attributeId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        intervals = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(241);
        builder.append("GetMeasurementProfileResponseCommand [");
        builder.append(super.toString());
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", status=");
        builder.append(status);
        builder.append(", profileIntervalPeriod=");
        builder.append(profileIntervalPeriod);
        builder.append(", numberOfIntervalsDelivered=");
        builder.append(numberOfIntervalsDelivered);
        builder.append(", attributeId=");
        builder.append(attributeId);
        builder.append(", intervals=");
        builder.append(intervals);
        builder.append(']');
        return builder.toString();
    }

}
