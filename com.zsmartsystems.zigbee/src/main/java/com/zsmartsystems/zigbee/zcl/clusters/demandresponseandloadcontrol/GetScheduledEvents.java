/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.demandresponseandloadcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ZigBeeUtcTime;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Scheduled Events value object class.
 * <p>
 * Cluster: <b>Demand Response And Load Control</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Demand Response And Load Control cluster.
 * <p>
 * This command is used to request that all scheduled Load Control Events, starting at or after
 * the supplied Start Time, are re-issued to the requesting device. When received by the
 * Server, one or more Load Control Event commands will be sent covering both active and
 * scheduled Load Control Events.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T21:19:38Z")
public class GetScheduledEvents extends ZclDemandResponseAndLoadControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0701;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Start Time command message field.
     * <p>
     * UTC Timestamp representing the minimum ending time for any scheduled or currently
     * active events to be resent. If either command has a Start Time of 0x00000000, replace
     * that Start Time with the current time stamp.
     */
    private ZigBeeUtcTime startTime;

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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetScheduledEvents() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param startTime {@link ZigBeeUtcTime} Start Time
     * @param numberOfEvents {@link Integer} Number Of Events
     */
    public GetScheduledEvents(
            ZigBeeUtcTime startTime,
            Integer numberOfEvents) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.startTime = startTime;
        this.numberOfEvents = numberOfEvents;
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
    public ZigBeeUtcTime getStartTime() {
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartTime(final ZigBeeUtcTime startTime) {
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        startTime = deserializer.deserialize(ZclDataType.UTCTIME);
        numberOfEvents = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
