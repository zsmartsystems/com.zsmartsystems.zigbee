/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Scheduled Events value object class.
 * <p>
 * Cluster: <b>Demand Response And Load Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Demand Response And Load Control cluster.
 * <p>
 * This command is used to request that all scheduled Load Control Events, starting at or after
 * the supplied Start Time, are re-issued to the requesting device. When received by the
 * Server, one or more Load Control Event commands will be sent covering both active and
 * scheduled Load Control Events.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetScheduledEvents extends ZclCommand {
    /**
     * Start Time command message field.
     * <p>
     * UTC Timestamp representing the minimum ending time for any scheduled or currently
     * active events to be resent. If either command has a Start Time of 0x00000000, replace
     * that Start Time with the current time stamp.
     */
    private Calendar startTime;

    /**
     * Number Of Events command message field.
     * <p>
     * Represents the maximum number of events to be sent. A value of 0 would indicate all
     * available events are to be returned. Example: Number of Events = 1 would return the first
     * event with an EndTime greater than or equal to the value of Start Time field in the Get
     * Scheduled Events command (EndTime would be StartTime plus Duration of the event listed
     * in the device's event table).
     */
    private Integer numberOfEvents;

    /**
     * Default constructor.
     */
    public GetScheduledEvents() {
        genericCommand = false;
        clusterId = 0x0701;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Start Time.
     * <p>
     * UTC Timestamp representing the minimum ending time for any scheduled or currently
     * active events to be resent. If either command has a Start Time of 0x00000000, replace
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
     * UTC Timestamp representing the minimum ending time for any scheduled or currently
     * active events to be resent. If either command has a Start Time of 0x00000000, replace
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
     * Represents the maximum number of events to be sent. A value of 0 would indicate all
     * available events are to be returned. Example: Number of Events = 1 would return the first
     * event with an EndTime greater than or equal to the value of Start Time field in the Get
     * Scheduled Events command (EndTime would be StartTime plus Duration of the event listed
     * in the device's event table).
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
     * event with an EndTime greater than or equal to the value of Start Time field in the Get
     * Scheduled Events command (EndTime would be StartTime plus Duration of the event listed
     * in the device's event table).
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
        final StringBuilder builder = new StringBuilder(84);
        builder.append("GetScheduledEvents [");
        builder.append(super.toString());
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", numberOfEvents=");
        builder.append(numberOfEvents);
        builder.append(']');
        return builder.toString();
    }

}
