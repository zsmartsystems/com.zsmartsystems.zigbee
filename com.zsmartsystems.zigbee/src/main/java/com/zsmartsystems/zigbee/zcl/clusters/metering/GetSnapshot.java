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

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Snapshot value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x06 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is used to request snapshot data from the cluster server.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetSnapshot extends ZclMeteringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x06;

    /**
     * Earliest Start Time command message field.
     * <p>
     * A UTC Timestamp indicating the earliest time of a snapshot to be returned by a
     * corresponding Publish Snapshot command. Snapshots with a time stamp equal to or
     * greater than the specified Earliest Start Time shall be returned.
     */
    private Calendar earliestStartTime;

    /**
     * Latest End Time command message field.
     * <p>
     * A UTC Timestamp indicating the latest time of a snapshot to be returned by a
     * corresponding Publish Snapshot command. Snapshots with a time stamp less than the
     * specified Latest End Time shall be returned.
     */
    private Calendar latestEndTime;

    /**
     * Snapshot Offset command message field.
     * <p>
     * Where multiple snapshots satisfy the selection criteria specified by the other fields
     * in this command, this field identifies the individual snapshot to be returned. An
     * offset of zero (0x00) indicates that the first snapshot satisfying the selection
     * criteria should be returned, 0x01 the second, and so on.
     */
    private Integer snapshotOffset;

    /**
     * Snapshot Cause command message field.
     * <p>
     * This field is used to select only snapshots that were taken due to a specific cause.
     * Setting this field to 0xFFFFFFFF indicates that all snapshots should be selected,
     * irrespective of the cause.
     */
    private Integer snapshotCause;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetSnapshot() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param earliestStartTime {@link Calendar} Earliest Start Time
     * @param latestEndTime {@link Calendar} Latest End Time
     * @param snapshotOffset {@link Integer} Snapshot Offset
     * @param snapshotCause {@link Integer} Snapshot Cause
     */
    public GetSnapshot(
            Calendar earliestStartTime,
            Calendar latestEndTime,
            Integer snapshotOffset,
            Integer snapshotCause) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.earliestStartTime = earliestStartTime;
        this.latestEndTime = latestEndTime;
        this.snapshotOffset = snapshotOffset;
        this.snapshotCause = snapshotCause;
    }

    /**
     * Gets Earliest Start Time.
     * <p>
     * A UTC Timestamp indicating the earliest time of a snapshot to be returned by a
     * corresponding Publish Snapshot command. Snapshots with a time stamp equal to or
     * greater than the specified Earliest Start Time shall be returned.
     *
     * @return the Earliest Start Time
     */
    public Calendar getEarliestStartTime() {
        return earliestStartTime;
    }

    /**
     * Sets Earliest Start Time.
     * <p>
     * A UTC Timestamp indicating the earliest time of a snapshot to be returned by a
     * corresponding Publish Snapshot command. Snapshots with a time stamp equal to or
     * greater than the specified Earliest Start Time shall be returned.
     *
     * @param earliestStartTime the Earliest Start Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEarliestStartTime(final Calendar earliestStartTime) {
        this.earliestStartTime = earliestStartTime;
    }

    /**
     * Gets Latest End Time.
     * <p>
     * A UTC Timestamp indicating the latest time of a snapshot to be returned by a
     * corresponding Publish Snapshot command. Snapshots with a time stamp less than the
     * specified Latest End Time shall be returned.
     *
     * @return the Latest End Time
     */
    public Calendar getLatestEndTime() {
        return latestEndTime;
    }

    /**
     * Sets Latest End Time.
     * <p>
     * A UTC Timestamp indicating the latest time of a snapshot to be returned by a
     * corresponding Publish Snapshot command. Snapshots with a time stamp less than the
     * specified Latest End Time shall be returned.
     *
     * @param latestEndTime the Latest End Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setLatestEndTime(final Calendar latestEndTime) {
        this.latestEndTime = latestEndTime;
    }

    /**
     * Gets Snapshot Offset.
     * <p>
     * Where multiple snapshots satisfy the selection criteria specified by the other fields
     * in this command, this field identifies the individual snapshot to be returned. An
     * offset of zero (0x00) indicates that the first snapshot satisfying the selection
     * criteria should be returned, 0x01 the second, and so on.
     *
     * @return the Snapshot Offset
     */
    public Integer getSnapshotOffset() {
        return snapshotOffset;
    }

    /**
     * Sets Snapshot Offset.
     * <p>
     * Where multiple snapshots satisfy the selection criteria specified by the other fields
     * in this command, this field identifies the individual snapshot to be returned. An
     * offset of zero (0x00) indicates that the first snapshot satisfying the selection
     * criteria should be returned, 0x01 the second, and so on.
     *
     * @param snapshotOffset the Snapshot Offset
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotOffset(final Integer snapshotOffset) {
        this.snapshotOffset = snapshotOffset;
    }

    /**
     * Gets Snapshot Cause.
     * <p>
     * This field is used to select only snapshots that were taken due to a specific cause.
     * Setting this field to 0xFFFFFFFF indicates that all snapshots should be selected,
     * irrespective of the cause.
     *
     * @return the Snapshot Cause
     */
    public Integer getSnapshotCause() {
        return snapshotCause;
    }

    /**
     * Sets Snapshot Cause.
     * <p>
     * This field is used to select only snapshots that were taken due to a specific cause.
     * Setting this field to 0xFFFFFFFF indicates that all snapshots should be selected,
     * irrespective of the cause.
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
        final StringBuilder builder = new StringBuilder(151);
        builder.append("GetSnapshot [");
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
