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
 * Get Scheduled Prices Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates a PublishPrice command for available price events. A server device
 * shall be capable of storing five price events at a minimum On receipt of this command, the
 * device shall send a PublishPrice command for the currently scheduled time.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class GetScheduledPricesCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Start Time command message field.
     * <p>
     * UTCTime stamp representing the minimum ending time for any scheduled or currently
     * active pricing events to be resent. If a command has a StartTime of 0x00000000, replace
     * that StartTime with the current time stamp.
     */
    private Calendar startTime;

    /**
     * Number Of Events command message field.
     * <p>
     * Represents the maximum number of events to be sent. A value of 0 would indicate all
     * available events are to be returned. Example: Number of Events = 1 would return the first
     * event with an EndTime greater than or equal to the value of StartTime field in the
     * GetScheduledPrices command. (EndTime would be StartTime plus Duration of the event
     * listed in the device’s event table).
     */
    private Integer numberOfEvents;

    /**
     * Default constructor.
     */
    public GetScheduledPricesCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Start Time.
     * <p>
     * UTCTime stamp representing the minimum ending time for any scheduled or currently
     * active pricing events to be resent. If a command has a StartTime of 0x00000000, replace
     * that StartTime with the current time stamp.
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
     * active pricing events to be resent. If a command has a StartTime of 0x00000000, replace
     * that StartTime with the current time stamp.
     *
     * @param startTime the Start Time
     */
    public void setStartTime(final Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets Number Of Events.
     * <p>
     * Represents the maximum number of events to be sent. A value of 0 would indicate all
     * available events are to be returned. Example: Number of Events = 1 would return the first
     * event with an EndTime greater than or equal to the value of StartTime field in the
     * GetScheduledPrices command. (EndTime would be StartTime plus Duration of the event
     * listed in the device’s event table).
     *
     * @return the Number Of Events
     */
    public Integer getNumberOfEvents() {
        return numberOfEvents;
    }

    /**
     * Sets Number Of Events.
     * <p>
     * Represents the maximum number of events to be sent. A value of 0 would indicate all
     * available events are to be returned. Example: Number of Events = 1 would return the first
     * event with an EndTime greater than or equal to the value of StartTime field in the
     * GetScheduledPrices command. (EndTime would be StartTime plus Duration of the event
     * listed in the device’s event table).
     *
     * @param numberOfEvents the Number Of Events
     */
    public void setNumberOfEvents(final Integer numberOfEvents) {
        this.numberOfEvents = numberOfEvents;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(numberOfEvents, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        numberOfEvents = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(91);
        builder.append("GetScheduledPricesCommand [");
        builder.append(super.toString());
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", numberOfEvents=");
        builder.append(numberOfEvents);
        builder.append(']');
        return builder.toString();
    }

}
