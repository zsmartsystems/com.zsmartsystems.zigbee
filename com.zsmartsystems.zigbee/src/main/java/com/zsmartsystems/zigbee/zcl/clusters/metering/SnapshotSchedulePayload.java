/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
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
     * @return the SnapshotSchedulePayload command
     */
    public SnapshotSchedulePayload setSnapshotScheduleId(final Integer snapshotScheduleId) {
        this.snapshotScheduleId = snapshotScheduleId;
        return this;
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
     * @return the SnapshotSchedulePayload command
     */
    public SnapshotSchedulePayload setSnapshotStartTime(final Calendar snapshotStartTime) {
        this.snapshotStartTime = snapshotStartTime;
        return this;
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
     * @return the SnapshotSchedulePayload command
     */
    public SnapshotSchedulePayload setSnapshotSchedule(final Integer snapshotSchedule) {
        this.snapshotSchedule = snapshotSchedule;
        return this;
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
     * @return the SnapshotSchedulePayload command
     */
    public SnapshotSchedulePayload setSnapshotPayloadType(final Integer snapshotPayloadType) {
        this.snapshotPayloadType = snapshotPayloadType;
        return this;
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
     * @return the SnapshotSchedulePayload command
     */
    public SnapshotSchedulePayload setSnapshotCause(final Integer snapshotCause) {
        this.snapshotCause = snapshotCause;
        return this;
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
        snapshotScheduleId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        snapshotStartTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        snapshotSchedule = (Integer) deserializer.deserialize(ZclDataType.BITMAP_24_BIT);
        snapshotPayloadType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        snapshotCause = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
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
