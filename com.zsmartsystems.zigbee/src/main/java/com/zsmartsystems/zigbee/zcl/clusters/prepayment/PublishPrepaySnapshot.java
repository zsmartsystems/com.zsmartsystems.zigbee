/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.prepayment;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Publish Prepay Snapshot value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command ID 0x01 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is generated in response to a GetPrepaySnapshot command. It is used to
 * return a single snapshot to the client.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class PublishPrepaySnapshot extends ZclPrepaymentCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0705;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Snapshot ID command message field.
     */
    private Integer snapshotId;

    /**
     * Snapshot Time command message field.
     */
    private Calendar snapshotTime;

    /**
     * Total Snapshots Found command message field.
     */
    private Integer totalSnapshotsFound;

    /**
     * Command Index command message field.
     */
    private Integer commandIndex;

    /**
     * Total Number Of Commands command message field.
     */
    private Integer totalNumberOfCommands;

    /**
     * Snapshot Cause command message field.
     */
    private Integer snapshotCause;

    /**
     * Snapshot Payload Type command message field.
     */
    private Integer snapshotPayloadType;

    /**
     * Snapshot Payload command message field.
     */
    private Integer snapshotPayload;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public PublishPrepaySnapshot() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param snapshotId {@link Integer} Snapshot ID
     * @param snapshotTime {@link Calendar} Snapshot Time
     * @param totalSnapshotsFound {@link Integer} Total Snapshots Found
     * @param commandIndex {@link Integer} Command Index
     * @param totalNumberOfCommands {@link Integer} Total Number Of Commands
     * @param snapshotCause {@link Integer} Snapshot Cause
     * @param snapshotPayloadType {@link Integer} Snapshot Payload Type
     * @param snapshotPayload {@link Integer} Snapshot Payload
     */
    public PublishPrepaySnapshot(
            Integer snapshotId,
            Calendar snapshotTime,
            Integer totalSnapshotsFound,
            Integer commandIndex,
            Integer totalNumberOfCommands,
            Integer snapshotCause,
            Integer snapshotPayloadType,
            Integer snapshotPayload) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.snapshotId = snapshotId;
        this.snapshotTime = snapshotTime;
        this.totalSnapshotsFound = totalSnapshotsFound;
        this.commandIndex = commandIndex;
        this.totalNumberOfCommands = totalNumberOfCommands;
        this.snapshotCause = snapshotCause;
        this.snapshotPayloadType = snapshotPayloadType;
        this.snapshotPayload = snapshotPayload;
    }

    /**
     * Gets Snapshot ID.
     *
     * @return the Snapshot ID
     */
    public Integer getSnapshotId() {
        return snapshotId;
    }

    /**
     * Sets Snapshot ID.
     *
     * @param snapshotId the Snapshot ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotId(final Integer snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * Gets Snapshot Time.
     *
     * @return the Snapshot Time
     */
    public Calendar getSnapshotTime() {
        return snapshotTime;
    }

    /**
     * Sets Snapshot Time.
     *
     * @param snapshotTime the Snapshot Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotTime(final Calendar snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    /**
     * Gets Total Snapshots Found.
     *
     * @return the Total Snapshots Found
     */
    public Integer getTotalSnapshotsFound() {
        return totalSnapshotsFound;
    }

    /**
     * Sets Total Snapshots Found.
     *
     * @param totalSnapshotsFound the Total Snapshots Found
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTotalSnapshotsFound(final Integer totalSnapshotsFound) {
        this.totalSnapshotsFound = totalSnapshotsFound;
    }

    /**
     * Gets Command Index.
     *
     * @return the Command Index
     */
    public Integer getCommandIndex() {
        return commandIndex;
    }

    /**
     * Sets Command Index.
     *
     * @param commandIndex the Command Index
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCommandIndex(final Integer commandIndex) {
        this.commandIndex = commandIndex;
    }

    /**
     * Gets Total Number Of Commands.
     *
     * @return the Total Number Of Commands
     */
    public Integer getTotalNumberOfCommands() {
        return totalNumberOfCommands;
    }

    /**
     * Sets Total Number Of Commands.
     *
     * @param totalNumberOfCommands the Total Number Of Commands
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTotalNumberOfCommands(final Integer totalNumberOfCommands) {
        this.totalNumberOfCommands = totalNumberOfCommands;
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
     * Gets Snapshot Payload.
     *
     * @return the Snapshot Payload
     */
    public Integer getSnapshotPayload() {
        return snapshotPayload;
    }

    /**
     * Sets Snapshot Payload.
     *
     * @param snapshotPayload the Snapshot Payload
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotPayload(final Integer snapshotPayload) {
        this.snapshotPayload = snapshotPayload;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(snapshotId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(snapshotTime, ZclDataType.UTCTIME);
        serializer.serialize(totalSnapshotsFound, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(commandIndex, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(totalNumberOfCommands, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(snapshotCause, ZclDataType.BITMAP_32_BIT);
        serializer.serialize(snapshotPayloadType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(snapshotPayload, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        snapshotId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        snapshotTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        totalSnapshotsFound = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        commandIndex = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        totalNumberOfCommands = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        snapshotCause = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
        snapshotPayloadType = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        snapshotPayload = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(305);
        builder.append("PublishPrepaySnapshot [");
        builder.append(super.toString());
        builder.append(", snapshotId=");
        builder.append(snapshotId);
        builder.append(", snapshotTime=");
        builder.append(snapshotTime);
        builder.append(", totalSnapshotsFound=");
        builder.append(totalSnapshotsFound);
        builder.append(", commandIndex=");
        builder.append(commandIndex);
        builder.append(", totalNumberOfCommands=");
        builder.append(totalNumberOfCommands);
        builder.append(", snapshotCause=");
        builder.append(snapshotCause);
        builder.append(", snapshotPayloadType=");
        builder.append(snapshotPayloadType);
        builder.append(", snapshotPayload=");
        builder.append(snapshotPayload);
        builder.append(']');
        return builder.toString();
    }

}
