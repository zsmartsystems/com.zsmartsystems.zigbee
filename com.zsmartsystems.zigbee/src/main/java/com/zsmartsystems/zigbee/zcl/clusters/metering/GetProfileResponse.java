/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Profile Response value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is sent when the Client command GetProfile is received.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetProfileResponse extends ZclCommand {
    /**
     * End Time command message field.
     * <p>
     * 32-bit value (in UTC) representing the end time of the most chronologically recent
     * interval being requested. Example: Data collected from 2:00 PM to 3:00 PM would be
     * specified as a 3:00 PM interval (end time). It is important to note that the current
     * interval accumulating is not included in most recent block but can be retrieved using
     * the CurrentPartialProfileIntervalValue attribute.
     */
    private Calendar endTime;

    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Profile Interval Period command message field.
     * <p>
     * Represents the interval or time frame used to capture metered Energy, Gas, and Water
     * consumption for profiling purposes.
     */
    private Integer profileIntervalPeriod;

    /**
     * Number Of Periods Delivered command message field.
     * <p>
     * Represents the number of intervals the device is returning. Please note the number of
     * periods returned in the Get Profile Response command can be calculated when the packets
     * are received and can replace the usage of this field. The intent is to provide this
     * information as a convenience.
     */
    private Integer numberOfPeriodsDelivered;

    /**
     * Intervals command message field.
     * <p>
     * Series of interval data captured using the period specified by the
     * ProfileIntervalPeriod field. The content of the interval data depends of the type of
     * information requested using the Channel field in the Get Profile Command, and will
     * represent the change in that information since the previous interval. Data is
     * organized in a reverse chronological order, the most recent interval is transmitted
     * first and the oldest interval is transmitted last. Invalid intervals should be marked
     * as 0xFFFFFF.
     */
    private Integer intervals;

    /**
     * Default constructor.
     */
    public GetProfileResponse() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 0;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets End Time.
     * <p>
     * 32-bit value (in UTC) representing the end time of the most chronologically recent
     * interval being requested. Example: Data collected from 2:00 PM to 3:00 PM would be
     * specified as a 3:00 PM interval (end time). It is important to note that the current
     * interval accumulating is not included in most recent block but can be retrieved using
     * the CurrentPartialProfileIntervalValue attribute.
     *
     * @return the End Time
     */
    public Calendar getEndTime() {
        return endTime;
    }

    /**
     * Sets End Time.
     * <p>
     * 32-bit value (in UTC) representing the end time of the most chronologically recent
     * interval being requested. Example: Data collected from 2:00 PM to 3:00 PM would be
     * specified as a 3:00 PM interval (end time). It is important to note that the current
     * interval accumulating is not included in most recent block but can be retrieved using
     * the CurrentPartialProfileIntervalValue attribute.
     *
     * @param endTime the End Time
     */
    public void setEndTime(final Calendar endTime) {
        this.endTime = endTime;
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
     * <p>
     * Represents the interval or time frame used to capture metered Energy, Gas, and Water
     * consumption for profiling purposes.
     *
     * @return the Profile Interval Period
     */
    public Integer getProfileIntervalPeriod() {
        return profileIntervalPeriod;
    }

    /**
     * Sets Profile Interval Period.
     * <p>
     * Represents the interval or time frame used to capture metered Energy, Gas, and Water
     * consumption for profiling purposes.
     *
     * @param profileIntervalPeriod the Profile Interval Period
     */
    public void setProfileIntervalPeriod(final Integer profileIntervalPeriod) {
        this.profileIntervalPeriod = profileIntervalPeriod;
    }

    /**
     * Gets Number Of Periods Delivered.
     * <p>
     * Represents the number of intervals the device is returning. Please note the number of
     * periods returned in the Get Profile Response command can be calculated when the packets
     * are received and can replace the usage of this field. The intent is to provide this
     * information as a convenience.
     *
     * @return the Number Of Periods Delivered
     */
    public Integer getNumberOfPeriodsDelivered() {
        return numberOfPeriodsDelivered;
    }

    /**
     * Sets Number Of Periods Delivered.
     * <p>
     * Represents the number of intervals the device is returning. Please note the number of
     * periods returned in the Get Profile Response command can be calculated when the packets
     * are received and can replace the usage of this field. The intent is to provide this
     * information as a convenience.
     *
     * @param numberOfPeriodsDelivered the Number Of Periods Delivered
     */
    public void setNumberOfPeriodsDelivered(final Integer numberOfPeriodsDelivered) {
        this.numberOfPeriodsDelivered = numberOfPeriodsDelivered;
    }

    /**
     * Gets Intervals.
     * <p>
     * Series of interval data captured using the period specified by the
     * ProfileIntervalPeriod field. The content of the interval data depends of the type of
     * information requested using the Channel field in the Get Profile Command, and will
     * represent the change in that information since the previous interval. Data is
     * organized in a reverse chronological order, the most recent interval is transmitted
     * first and the oldest interval is transmitted last. Invalid intervals should be marked
     * as 0xFFFFFF.
     *
     * @return the Intervals
     */
    public Integer getIntervals() {
        return intervals;
    }

    /**
     * Sets Intervals.
     * <p>
     * Series of interval data captured using the period specified by the
     * ProfileIntervalPeriod field. The content of the interval data depends of the type of
     * information requested using the Channel field in the Get Profile Command, and will
     * represent the change in that information since the previous interval. Data is
     * organized in a reverse chronological order, the most recent interval is transmitted
     * first and the oldest interval is transmitted last. Invalid intervals should be marked
     * as 0xFFFFFF.
     *
     * @param intervals the Intervals
     */
    public void setIntervals(final Integer intervals) {
        this.intervals = intervals;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(endTime, ZclDataType.UTCTIME);
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(profileIntervalPeriod, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(numberOfPeriodsDelivered, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(intervals, ZclDataType.UNSIGNED_24_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        endTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        profileIntervalPeriod = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        numberOfPeriodsDelivered = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        intervals = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_24_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(188);
        builder.append("GetProfileResponse [");
        builder.append(super.toString());
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append(", status=");
        builder.append(status);
        builder.append(", profileIntervalPeriod=");
        builder.append(profileIntervalPeriod);
        builder.append(", numberOfPeriodsDelivered=");
        builder.append(numberOfPeriodsDelivered);
        builder.append(", intervals=");
        builder.append(intervals);
        builder.append(']');
        return builder.toString();
    }

}
