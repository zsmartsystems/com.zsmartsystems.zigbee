/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;
import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializable;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Snapshot Schedule Payload structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:02:05Z")
public class SnapshotSchedulePayload implements ZigBeeSerializable {
    /**
     * Snapshot Schedule ID structure field.
     */
    private Integer snapshotScheduleId;

    /**
     * Snapshot Start Time structure field.
     */
    private Calendar snapshotStartTime;

    /**
     * Snapshot Schedule structure field.
     */
    private Integer snapshotSchedule;

    /**
     * Snapshot Payload Type structure field.
     */
    private Integer snapshotPayloadType;

    /**
     * Snapshot Cause structure field.
     */
    private Integer snapshotCause;


    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public SnapshotSchedulePayload() {
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param snapshotScheduleId {@link Integer} Snapshot Schedule ID
     * @param snapshotStartTime {@link Calendar} Snapshot Start Time
     * @param snapshotSchedule {@link Integer} Snapshot Schedule
     * @param snapshotPayloadType {@link Integer} Snapshot Payload Type
     * @param snapshotCause {@link Integer} Snapshot Cause
     */
    public SnapshotSchedulePayload(
            Integer snapshotScheduleId,
            Calendar snapshotStartTime,
            Integer snapshotSchedule,
            Integer snapshotPayloadType,
            Integer snapshotCause) {

        this.snapshotScheduleId = snapshotScheduleId;
        this.snapshotStartTime = snapshotStartTime;
        this.snapshotSchedule = snapshotSchedule;
        this.snapshotPayloadType = snapshotPayloadType;
        this.snapshotCause = snapshotCause;
    }

    /**
     * Gets Snapshot Schedule ID.
     *
     * @return the Snapshot Schedule ID
     */
    public Integer getSnapshotScheduleId() {
        return snapshotScheduleId;
    }

    /**
     * Sets Snapshot Schedule ID.
     *
     * @param snapshotScheduleId the Snapshot Schedule ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotScheduleId(final Integer snapshotScheduleId) {
        this.snapshotScheduleId = snapshotScheduleId;
    }

    /**
     * Gets Snapshot Start Time.
     *
     * @return the Snapshot Start Time
     */
    public Calendar getSnapshotStartTime() {
        return snapshotStartTime;
    }

    /**
     * Sets Snapshot Start Time.
     *
     * @param snapshotStartTime the Snapshot Start Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotStartTime(final Calendar snapshotStartTime) {
        this.snapshotStartTime = snapshotStartTime;
    }

    /**
     * Gets Snapshot Schedule.
     *
     * @return the Snapshot Schedule
     */
    public Integer getSnapshotSchedule() {
        return snapshotSchedule;
    }

    /**
     * Sets Snapshot Schedule.
     *
     * @param snapshotSchedule the Snapshot Schedule
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotSchedule(final Integer snapshotSchedule) {
        this.snapshotSchedule = snapshotSchedule;
    }

    /**
     * Gets Snapshot Payload Type.
     *
     * @return the Snapshot Payload Type
     */
    public Integer getSnapshotPayloadType() {
        return snapshotPayloadType;
    }

    /**
     * Sets Snapshot Payload Type.
     *
     * @param snapshotPayloadType the Snapshot Payload Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotPayloadType(final Integer snapshotPayloadType) {
        this.snapshotPayloadType = snapshotPayloadType;
    }

    /**
     * Gets Snapshot Cause.
     *
     * @return the Snapshot Cause
     */
    public Integer getSnapshotCause() {
        return snapshotCause;
    }

    /**
     * Sets Snapshot Cause.
     *
     * @param snapshotCause the Snapshot Cause
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotCause(final Integer snapshotCause) {
        this.snapshotCause = snapshotCause;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(snapshotScheduleId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(snapshotStartTime, ZclDataType.UTCTIME);
        serializer.serialize(snapshotSchedule, ZclDataType.BITMAP_24_BIT);
        serializer.serialize(snapshotPayloadType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(snapshotCause, ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        snapshotScheduleId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        snapshotStartTime = deserializer.deserialize(ZclDataType.UTCTIME);
        snapshotSchedule = deserializer.deserialize(ZclDataType.BITMAP_24_BIT);
        snapshotPayloadType = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        snapshotCause = deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(209);
        builder.append("SnapshotSchedulePayload [");
        builder.append(super.toString());
        builder.append(", snapshotScheduleId=");
        builder.append(snapshotScheduleId);
        builder.append(", snapshotStartTime=");
        builder.append(snapshotStartTime);
        builder.append(", snapshotSchedule=");
        builder.append(snapshotSchedule);
        builder.append(", snapshotPayloadType=");
        builder.append(snapshotPayloadType);
        builder.append(", snapshotCause=");
        builder.append(snapshotCause);
        builder.append(']');
        return builder.toString();
    }
}
