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
 * Get Holiday Schedule Response value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x12 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Returns the Holiday Schedule Entry for the specified Holiday ID.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:41:29Z")
public class GetHolidayScheduleResponse extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x12;

    /**
     * Holiday Schedule ID command message field.
     */
    private Integer holidayScheduleId;

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
     * Operating Mode command message field.
     */
    private Integer operatingMode;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetHolidayScheduleResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param holidayScheduleId {@link Integer} Holiday Schedule ID
     * @param status {@link Integer} Status
     * @param localStartTime {@link Integer} Local Start Time
     * @param localEndTime {@link Integer} Local End Time
     * @param operatingMode {@link Integer} Operating Mode
     */
    public GetHolidayScheduleResponse(
            Integer holidayScheduleId,
            Integer status,
            Integer localStartTime,
            Integer localEndTime,
            Integer operatingMode) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.holidayScheduleId = holidayScheduleId;
        this.status = status;
        this.localStartTime = localStartTime;
        this.localEndTime = localEndTime;
        this.operatingMode = operatingMode;
    }

    /**
     * Gets Holiday Schedule ID.
     *
     * @return the Holiday Schedule ID
     */
    public Integer getHolidayScheduleId() {
        return holidayScheduleId;
    }

    /**
     * Sets Holiday Schedule ID.
     *
     * @param holidayScheduleId the Holiday Schedule ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setHolidayScheduleId(final Integer holidayScheduleId) {
        this.holidayScheduleId = holidayScheduleId;
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

    /**
     * Gets Operating Mode.
     *
     * @return the Operating Mode
     */
    public Integer getOperatingMode() {
        return operatingMode;
    }

    /**
     * Sets Operating Mode.
     *
     * @param operatingMode the Operating Mode
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOperatingMode(final Integer operatingMode) {
        this.operatingMode = operatingMode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(holidayScheduleId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(localStartTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(localEndTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(operatingMode, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        holidayScheduleId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        status = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        localStartTime = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        localEndTime = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        operatingMode = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(191);
        builder.append("GetHolidayScheduleResponse [");
        builder.append(super.toString());
        builder.append(", holidayScheduleId=");
        builder.append(holidayScheduleId);
        builder.append(", status=");
        builder.append(status);
        builder.append(", localStartTime=");
        builder.append(localStartTime);
        builder.append(", localEndTime=");
        builder.append(localEndTime);
        builder.append(", operatingMode=");
        builder.append(operatingMode);
        builder.append(']');
        return builder.toString();
    }

}
