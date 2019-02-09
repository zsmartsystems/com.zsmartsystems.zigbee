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
 * Publish Prepay Snapshot value object class.
 * <p>
 * Cluster: <b>Prepayment</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Prepayment cluster.
 * <p>
 * FIXME: This command is generated in response to a GetPrepaySnapshot command. It is used to
 * return a single snapshot to the client.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class PublishPrepaySnapshot extends ZclCommand {
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
     */
    public PublishPrepaySnapshot() {
        genericCommand = false;
        clusterId = 0x0705;
        commandId = 1;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
     */
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
