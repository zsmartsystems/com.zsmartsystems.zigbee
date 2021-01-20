/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Measurement Profile Response Command value object class.
 * <p>
 * Cluster: <b>Electrical Measurement</b>. Command ID 0x01 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Electrical Measurement cluster.
 * <p>
 * Returns the electricity measurement profile. The electricity measurement profile
 * includes information regarding the amount of time used to capture data related to the flow of
 * electricity as well as the intervals thes
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetMeasurementProfileResponseCommand extends ZclElectricalMeasurementCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0B04;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetMeasurementProfileResponseCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param startTime {@link Integer} Start Time
     * @param status {@link Integer} Status
     * @param profileIntervalPeriod {@link Integer} Profile Interval Period
     * @param numberOfIntervalsDelivered {@link Integer} Number Of Intervals Delivered
     * @param attributeId {@link Integer} Attribute ID
     * @param intervals {@link Integer} Intervals
     */
    public GetMeasurementProfileResponseCommand(
            Integer startTime,
            Integer status,
            Integer profileIntervalPeriod,
            Integer numberOfIntervalsDelivered,
            Integer attributeId,
            Integer intervals) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.startTime = startTime;
        this.status = status;
        this.profileIntervalPeriod = profileIntervalPeriod;
        this.numberOfIntervalsDelivered = numberOfIntervalsDelivered;
        this.attributeId = attributeId;
        this.intervals = intervals;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
