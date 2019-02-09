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
 * Get Profile value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * The GetProfile command is generated when a client device wishes to retrieve a list of
 * captured Energy, Gas or water consumption for profiling purposes.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetProfile extends ZclCommand {
    /**
     * Interval Channel command message field.
     * <p>
     * Enumerated value used to select the quantity of interest returned by the
     * GetProfileReponse command.
     */
    private Integer intervalChannel;

    /**
     * End Time command message field.
     * <p>
     * 32-bit value (in UTCTime) used to select an Intervals block from all the Intervals
     * blocks available. The Intervals block returned is the most recent block with its
     * EndTime equal or older to the one provided. The most recent Intervals block is requested
     * using an End Time set to 0x00000000, subsequent Intervals block are requested using an
     * End time set to the EndTime of the previous block - (number of intervals of the previous
     * block * ProfileIntervalPeriod).
     */
    private Calendar endTime;

    /**
     * Number Of Periods command message field.
     * <p>
     * Represents the number of intervals being requested. This value cannot exceed the size
     * stipulated in the MaxNumberOfPeriodsDelivered attribute. If more intervals are
     * requested than can be delivered, the GetProfileResponse will return the number of
     * intervals equal to MaxNumberOfPeriodsDelivered. If fewer intervals are available
     * for the time period, only those available are returned.
     */
    private Integer numberOfPeriods;

    /**
     * Default constructor.
     */
    public GetProfile() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Interval Channel.
     * <p>
     * Enumerated value used to select the quantity of interest returned by the
     * GetProfileReponse command.
     *
     * @return the Interval Channel
     */
    public Integer getIntervalChannel() {
        return intervalChannel;
    }

    /**
     * Sets Interval Channel.
     * <p>
     * Enumerated value used to select the quantity of interest returned by the
     * GetProfileReponse command.
     *
     * @param intervalChannel the Interval Channel
     */
    public void setIntervalChannel(final Integer intervalChannel) {
        this.intervalChannel = intervalChannel;
    }

    /**
     * Gets End Time.
     * <p>
     * 32-bit value (in UTCTime) used to select an Intervals block from all the Intervals
     * blocks available. The Intervals block returned is the most recent block with its
     * EndTime equal or older to the one provided. The most recent Intervals block is requested
     * using an End Time set to 0x00000000, subsequent Intervals block are requested using an
     * End time set to the EndTime of the previous block - (number of intervals of the previous
     * block * ProfileIntervalPeriod).
     *
     * @return the End Time
     */
    public Calendar getEndTime() {
        return endTime;
    }

    /**
     * Sets End Time.
     * <p>
     * 32-bit value (in UTCTime) used to select an Intervals block from all the Intervals
     * blocks available. The Intervals block returned is the most recent block with its
     * EndTime equal or older to the one provided. The most recent Intervals block is requested
     * using an End Time set to 0x00000000, subsequent Intervals block are requested using an
     * End time set to the EndTime of the previous block - (number of intervals of the previous
     * block * ProfileIntervalPeriod).
     *
     * @param endTime the End Time
     */
    public void setEndTime(final Calendar endTime) {
        this.endTime = endTime;
    }

    /**
     * Gets Number Of Periods.
     * <p>
     * Represents the number of intervals being requested. This value cannot exceed the size
     * stipulated in the MaxNumberOfPeriodsDelivered attribute. If more intervals are
     * requested than can be delivered, the GetProfileResponse will return the number of
     * intervals equal to MaxNumberOfPeriodsDelivered. If fewer intervals are available
     * for the time period, only those available are returned.
     *
     * @return the Number Of Periods
     */
    public Integer getNumberOfPeriods() {
        return numberOfPeriods;
    }

    /**
     * Sets Number Of Periods.
     * <p>
     * Represents the number of intervals being requested. This value cannot exceed the size
     * stipulated in the MaxNumberOfPeriodsDelivered attribute. If more intervals are
     * requested than can be delivered, the GetProfileResponse will return the number of
     * intervals equal to MaxNumberOfPeriodsDelivered. If fewer intervals are available
     * for the time period, only those available are returned.
     *
     * @param numberOfPeriods the Number Of Periods
     */
    public void setNumberOfPeriods(final Integer numberOfPeriods) {
        this.numberOfPeriods = numberOfPeriods;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(intervalChannel, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(endTime, ZclDataType.UTCTIME);
        serializer.serialize(numberOfPeriods, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        intervalChannel = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        endTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        numberOfPeriods = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(110);
        builder.append("GetProfile [");
        builder.append(super.toString());
        builder.append(", intervalChannel=");
        builder.append(intervalChannel);
        builder.append(", endTime=");
        builder.append(endTime);
        builder.append(", numberOfPeriods=");
        builder.append(numberOfPeriods);
        builder.append(']');
        return builder.toString();
    }

}
