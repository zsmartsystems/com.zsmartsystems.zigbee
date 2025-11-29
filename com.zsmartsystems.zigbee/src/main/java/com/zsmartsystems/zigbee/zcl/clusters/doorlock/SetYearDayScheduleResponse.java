/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
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
 * Set Year Day Schedule Response value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x0F is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Returns the weekly repeating schedule data for the specified schedule ID.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:41:29Z")
public class SetYearDayScheduleResponse extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0F;

    /**
     * Schedule ID command message field.
     */
    private Integer scheduleId;

    /**
     * User ID command message field.
     */
    private Integer userId;

    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Local Start Time command message field.
     */
    private Integer localStartTime;

    /**
     * Local End Time command message field.
     */
    private Integer localEndTime;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetYearDayScheduleResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param scheduleId {@link Integer} Schedule ID
     * @param userId {@link Integer} User ID
     * @param status {@link Integer} Status
     * @param localStartTime {@link Integer} Local Start Time
     * @param localEndTime {@link Integer} Local End Time
     */
    public SetYearDayScheduleResponse(
            Integer scheduleId,
            Integer userId,
            Integer status,
            Integer localStartTime,
            Integer localEndTime) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.scheduleId = scheduleId;
        this.userId = userId;
        this.status = status;
        this.localStartTime = localStartTime;
        this.localEndTime = localEndTime;
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
     * Gets Local Start Time.
     *
     * @return the Local Start Time
     */
    public Integer getLocalStartTime() {
        return localStartTime;
    }

    /**
     * Sets Local Start Time.
     *
     * @param localStartTime the Local Start Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setLocalStartTime(final Integer localStartTime) {
        this.localStartTime = localStartTime;
    }

    /**
     * Gets Local End Time.
     *
     * @return the Local End Time
     */
    public Integer getLocalEndTime() {
        return localEndTime;
    }

    /**
     * Sets Local End Time.
     *
     * @param localEndTime the Local End Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setLocalEndTime(final Integer localEndTime) {
        this.localEndTime = localEndTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(scheduleId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(userId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(localStartTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(localEndTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        scheduleId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        userId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        status = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        localStartTime = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        localEndTime = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(177);
        builder.append("SetYearDayScheduleResponse [");
        builder.append(super.toString());
        builder.append(", scheduleId=");
        builder.append(scheduleId);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", localStartTime=");
        builder.append(localStartTime);
        builder.append(", localEndTime=");
        builder.append(localEndTime);
        builder.append(']');
        return builder.toString();
    }

}
