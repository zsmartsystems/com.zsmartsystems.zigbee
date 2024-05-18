/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Week Day Schedule value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x0B is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Set a weekly repeating schedule for a specified user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public class SetWeekDaySchedule extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0B;

    /**
     * Schedule ID command message field.
     */
    private Integer scheduleId;

    /**
     * User ID command message field.
     */
    private Integer userId;

    /**
     * Days Mask command message field.
     */
    private Integer daysMask;

    /**
     * Start Hour command message field.
     */
    private Integer startHour;

    /**
     * Start Minute command message field.
     */
    private Integer startMinute;

    /**
     * End Hour command message field.
     */
    private Integer endHour;

    /**
     * End Minute command message field.
     */
    private Integer endMinute;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetWeekDaySchedule() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @param daysMask {@link Integer} Days Mask
     * @param startHour {@link Integer} Start Hour
     * @param startMinute {@link Integer} Start Minute
     * @param endHour {@link Integer} End Hour
     * @param endMinute {@link Integer} End Minute
     */
    public SetWeekDaySchedule(
            Integer scheduleId,
            Integer userId,
            Integer daysMask,
            Integer startHour,
            Integer startMinute,
            Integer endHour,
            Integer endMinute) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.scheduleId = scheduleId;
        this.userId = userId;
        this.daysMask = daysMask;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    /**
     * Gets Schedule ID.
     *
     * @return the Schedule ID
     */
    public Integer getScheduleId() {
        return scheduleId;
    }

    /**
     * Sets Schedule ID.
     *
     * @param scheduleId the Schedule ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setScheduleId(final Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * Gets User ID.
     *
     * @return the User ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets User ID.
     *
     * @param userId the User ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets Days Mask.
     *
     * @return the Days Mask
     */
    public Integer getDaysMask() {
        return daysMask;
    }

    /**
     * Sets Days Mask.
     *
     * @param daysMask the Days Mask
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDaysMask(final Integer daysMask) {
        this.daysMask = daysMask;
    }

    /**
     * Gets Start Hour.
     *
     * @return the Start Hour
     */
    public Integer getStartHour() {
        return startHour;
    }

    /**
     * Sets Start Hour.
     *
     * @param startHour the Start Hour
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartHour(final Integer startHour) {
        this.startHour = startHour;
    }

    /**
     * Gets Start Minute.
     *
     * @return the Start Minute
     */
    public Integer getStartMinute() {
        return startMinute;
    }

    /**
     * Sets Start Minute.
     *
     * @param startMinute the Start Minute
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartMinute(final Integer startMinute) {
        this.startMinute = startMinute;
    }

    /**
     * Gets End Hour.
     *
     * @return the End Hour
     */
    public Integer getEndHour() {
        return endHour;
    }

    /**
     * Sets End Hour.
     *
     * @param endHour the End Hour
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEndHour(final Integer endHour) {
        this.endHour = endHour;
    }

    /**
     * Gets End Minute.
     *
     * @return the End Minute
     */
    public Integer getEndMinute() {
        return endMinute;
    }

    /**
     * Sets End Minute.
     *
     * @param endMinute the End Minute
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEndMinute(final Integer endMinute) {
        this.endMinute = endMinute;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(scheduleId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(userId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(daysMask, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(startHour, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(startMinute, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(endHour, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(endMinute, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        scheduleId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        userId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        daysMask = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        startHour = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        startMinute = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        endHour = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        endMinute = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(221);
        builder.append("SetWeekDaySchedule [");
        builder.append(super.toString());
        builder.append(", scheduleId=");
        builder.append(scheduleId);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", daysMask=");
        builder.append(daysMask);
        builder.append(", startHour=");
        builder.append(startHour);
        builder.append(", startMinute=");
        builder.append(startMinute);
        builder.append(", endHour=");
        builder.append(endHour);
        builder.append(", endMinute=");
        builder.append(endMinute);
        builder.append(']');
        return builder.toString();
    }

}
