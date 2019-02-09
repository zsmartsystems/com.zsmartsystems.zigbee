/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Block Period Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates a PublishBlockPeriod command for the currently scheduled block
 * periods. A server device shall be capable of storing at least two commands, the current
 * period and a period to be activated in the near future. <br> A ZCL Default response with status
 * NOT_FOUND shall be returned if there are no events available.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetBlockPeriodCommand extends ZclCommand {
    /**
     * Start Time command message field.
     * <p>
     * UTCTime stamp representing the minimum ending time for any scheduled or currently
     * block period events to be resent. If a command has a Start Time of 0x00000000, replace
     * that Start Time with the current time stamp.
     */
    private Calendar startTime;

    /**
     * Number Of Events command message field.
     * <p>
     * An 8 bit Integer which indicates the maximum number of Publish Block Period commands
     * that can be sent. Example: Number of Events = 1 would return the first event with an
     * EndTime greater than or equal to the value of Start Time field in the GetBlockPeriod(s)
     * command. (EndTime would be StartTime plus Duration of the event listed in the device’s
     * event table). Number of Events = 0 would return all available Publish Block Periods,
     * starting with the current block in progress. 8460 command. The least significant
     * nibble represents an enumeration of the tariff (Generation Meters shall use the
     * ‘Received’ Tariff.). If the TariffType is not specified, the server shall assume that
     * the request is for the ‘Delivered’ Tariff. The most significant nibble is reserved.
     */
    private Integer numberOfEvents;

    /**
     * Tariff Type command message field.
     */
    private Integer tariffType;

    /**
     * Default constructor.
     */
    public GetBlockPeriodCommand() {
        genericCommand = false;
        clusterId = 0x0700;
        commandId = 3;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Start Time.
     * <p>
     * UTCTime stamp representing the minimum ending time for any scheduled or currently
     * block period events to be resent. If a command has a Start Time of 0x00000000, replace
     * that Start Time with the current time stamp.
     *
     * @return the Start Time
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Sets Start Time.
     * <p>
     * UTCTime stamp representing the minimum ending time for any scheduled or currently
     * block period events to be resent. If a command has a Start Time of 0x00000000, replace
     * that Start Time with the current time stamp.
     *
     * @param startTime the Start Time
     */
    public void setStartTime(final Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets Number Of Events.
     * <p>
     * An 8 bit Integer which indicates the maximum number of Publish Block Period commands
     * that can be sent. Example: Number of Events = 1 would return the first event with an
     * EndTime greater than or equal to the value of Start Time field in the GetBlockPeriod(s)
     * command. (EndTime would be StartTime plus Duration of the event listed in the device’s
     * event table). Number of Events = 0 would return all available Publish Block Periods,
     * starting with the current block in progress. 8460 command. The least significant
     * nibble represents an enumeration of the tariff (Generation Meters shall use the
     * ‘Received’ Tariff.). If the TariffType is not specified, the server shall assume that
     * the request is for the ‘Delivered’ Tariff. The most significant nibble is reserved.
     *
     * @return the Number Of Events
     */
    public Integer getNumberOfEvents() {
        return numberOfEvents;
    }

    /**
     * Sets Number Of Events.
     * <p>
     * An 8 bit Integer which indicates the maximum number of Publish Block Period commands
     * that can be sent. Example: Number of Events = 1 would return the first event with an
     * EndTime greater than or equal to the value of Start Time field in the GetBlockPeriod(s)
     * command. (EndTime would be StartTime plus Duration of the event listed in the device’s
     * event table). Number of Events = 0 would return all available Publish Block Periods,
     * starting with the current block in progress. 8460 command. The least significant
     * nibble represents an enumeration of the tariff (Generation Meters shall use the
     * ‘Received’ Tariff.). If the TariffType is not specified, the server shall assume that
     * the request is for the ‘Delivered’ Tariff. The most significant nibble is reserved.
     *
     * @param numberOfEvents the Number Of Events
     */
    public void setNumberOfEvents(final Integer numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    /**
     * Gets Tariff Type.
     *
     * @return the Tariff Type
     */
    public Integer getTariffType() {
        return tariffType;
    }

    /**
     * Sets Tariff Type.
     *
     * @param tariffType the Tariff Type
     */
    public void setTariffType(final Integer tariffType) {
        this.tariffType = tariffType;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(numberOfEvents, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(tariffType, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        numberOfEvents = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        tariffType = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(117);
        builder.append("GetBlockPeriodCommand [");
        builder.append(super.toString());
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", numberOfEvents=");
        builder.append(numberOfEvents);
        builder.append(", tariffType=");
        builder.append(tariffType);
        builder.append(']');
        return builder.toString();
    }

}
