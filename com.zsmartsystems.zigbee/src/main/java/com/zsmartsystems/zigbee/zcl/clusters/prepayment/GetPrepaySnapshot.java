/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Prepay Snapshot value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is used to request the cluster server for snapshot data.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetPrepaySnapshot extends ZclCommand {
    /**
     * Earliest Start Time command message field.
     */
    private Calendar earliestStartTime;

    /**
     * Latest End Time command message field.
     */
    private Calendar latestEndTime;

    /**
     * Snapshot Offset command message field.
     */
    private Integer snapshotOffset;

    /**
     * Snapshot Cause command message field.
     */
    private Integer snapshotCause;

    /**
     * Default constructor.
     */
    public GetPrepaySnapshot() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 7;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Earliest Start Time.
     *
     * @return the Earliest Start Time
     */
    public Calendar getEarliestStartTime() {
        return earliestStartTime;
    }

    /**
     * Sets Earliest Start Time.
     *
     * @param earliestStartTime the Earliest Start Time
     */
    public void setEarliestStartTime(final Calendar earliestStartTime) {
        this.earliestStartTime = earliestStartTime;
    }

    /**
     * Gets Latest End Time.
     *
     * @return the Latest End Time
     */
    public Calendar getLatestEndTime() {
        return latestEndTime;
    }

    /**
     * Sets Latest End Time.
     *
     * @param latestEndTime the Latest End Time
     */
    public void setLatestEndTime(final Calendar latestEndTime) {
        this.latestEndTime = latestEndTime;
    }

    /**
     * Gets Snapshot Offset.
     *
     * @return the Snapshot Offset
     */
    public Integer getSnapshotOffset() {
        return snapshotOffset;
    }

    /**
     * Sets Snapshot Offset.
     *
     * @param snapshotOffset the Snapshot Offset
     */
    public void setSnapshotOffset(final Integer snapshotOffset) {
        this.snapshotOffset = snapshotOffset;
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
     */
    public void setSnapshotCause(final Integer snapshotCause) {
        this.snapshotCause = snapshotCause;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(earliestStartTime, ZclDataType.UTCTIME);
        serializer.serialize(latestEndTime, ZclDataType.UTCTIME);
        serializer.serialize(snapshotOffset, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(snapshotCause, ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        earliestStartTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        latestEndTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        snapshotOffset = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        snapshotCause = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(157);
        builder.append("GetPrepaySnapshot [");
        builder.append(super.toString());
        builder.append(", earliestStartTime=");
        builder.append(earliestStartTime);
        builder.append(", latestEndTime=");
        builder.append(latestEndTime);
        builder.append(", snapshotOffset=");
        builder.append(snapshotOffset);
        builder.append(", snapshotCause=");
        builder.append(snapshotCause);
        builder.append(']');
        return builder.toString();
    }

}
