/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Publish Snapshot value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is generated in response to a GetSnapshot command. It is used to return a single
 * snapshot to the client.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class PublishSnapshot extends ZclCommand {
    /**
     * Snapshot ID command message field.
     * <p>
     * Unique identifier allocated by the device creating the snapshot.
     */
    private Integer snapshotId;

    /**
     * Snapshot Time command message field.
     * <p>
     * This is a 32 bit value (in UTC Time) representing the time at which the data snapshot was
     * taken.
     */
    private Calendar snapshotTime;

    /**
     * Total Snapshots Found command message field.
     * <p>
     * An 8-bit Integer indicating the number of snapshots found, based on the search criteria
     * defined in the associated GetSnapshot command. If the value is greater than 1, the
     * client is able to request the next snapshot by incrementing the Snapshot Offset field in
     * an otherwise repeated GetSnapshot command.
     */
    private Integer totalSnapshotsFound;

    /**
     * Command Index command message field.
     * <p>
     * The CommandIndex is used to count the payload fragments in the case where the entire
     * payload (snapshot) does not fit into one message. The CommandIndex starts at 0 and is
     * incremented for each fragment belonging to the same command.
     */
    private Integer commandIndex;

    /**
     * Total Number Of Commands command message field.
     * <p>
     * In the case where the entire payload (snapshot) does not fit into one message, the Total
     * Number of Commands field indicates the total number of sub-commands that will be
     * returned.
     */
    private Integer totalNumberOfCommands;

    /**
     * Snapshot Cause command message field.
     * <p>
     * A 32-bit BitMap indicating the cause of the snapshot.
     */
    private Integer snapshotCause;

    /**
     * Snapshot Payload Type command message field.
     * <p>
     * The SnapshotPayloadType is an 8-bit enumerator defining the format of the
     * SnapshotSubPayload in this message. The server selects the SnapshotPayloadType
     * based on the charging scheme in use.
     * <p>
     * If the snapshot is taken by the server due to a change of Tariff Information (cause = 3)
     * which involves a change in charging scheme then two snapshots shall be taken, the first
     * according to the charging scheme being dismissed, the second to the scheme being
     * introduced.
     */
    private Integer snapshotPayloadType;

    /**
     * Snapshot Payload command message field.
     * <p>
     * The format of the SnapshotSub-Payload differs depending on the SnapshotPayloadType,
     * as shown below. Note that, where the entire payload (snapshot) does not fit into one
     * message, only the leading (non-Sub-Payload) fields of the Snapshot payload are
     * repeated in each command; the SnapshotSub-Payload is divided over the required number
     * of commands.
     */
    private Integer snapshotPayload;

    /**
     * Default constructor.
     */
    public PublishSnapshot() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 6;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Snapshot ID.
     * <p>
     * Unique identifier allocated by the device creating the snapshot.
     *
     * @return the Snapshot ID
     */
    public Integer getSnapshotId() {
        return snapshotId;
    }

    /**
     * Sets Snapshot ID.
     * <p>
     * Unique identifier allocated by the device creating the snapshot.
     *
     * @param snapshotId the Snapshot ID
     */
    public void setSnapshotId(final Integer snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * Gets Snapshot Time.
     * <p>
     * This is a 32 bit value (in UTC Time) representing the time at which the data snapshot was
     * taken.
     *
     * @return the Snapshot Time
     */
    public Calendar getSnapshotTime() {
        return snapshotTime;
    }

    /**
     * Sets Snapshot Time.
     * <p>
     * This is a 32 bit value (in UTC Time) representing the time at which the data snapshot was
     * taken.
     *
     * @param snapshotTime the Snapshot Time
     */
    public void setSnapshotTime(final Calendar snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    /**
     * Gets Total Snapshots Found.
     * <p>
     * An 8-bit Integer indicating the number of snapshots found, based on the search criteria
     * defined in the associated GetSnapshot command. If the value is greater than 1, the
     * client is able to request the next snapshot by incrementing the Snapshot Offset field in
     * an otherwise repeated GetSnapshot command.
     *
     * @return the Total Snapshots Found
     */
    public Integer getTotalSnapshotsFound() {
        return totalSnapshotsFound;
    }

    /**
     * Sets Total Snapshots Found.
     * <p>
     * An 8-bit Integer indicating the number of snapshots found, based on the search criteria
     * defined in the associated GetSnapshot command. If the value is greater than 1, the
     * client is able to request the next snapshot by incrementing the Snapshot Offset field in
     * an otherwise repeated GetSnapshot command.
     *
     * @param totalSnapshotsFound the Total Snapshots Found
     */
    public void setTotalSnapshotsFound(final Integer totalSnapshotsFound) {
        this.totalSnapshotsFound = totalSnapshotsFound;
    }

    /**
     * Gets Command Index.
     * <p>
     * The CommandIndex is used to count the payload fragments in the case where the entire
     * payload (snapshot) does not fit into one message. The CommandIndex starts at 0 and is
     * incremented for each fragment belonging to the same command.
     *
     * @return the Command Index
     */
    public Integer getCommandIndex() {
        return commandIndex;
    }

    /**
     * Sets Command Index.
     * <p>
     * The CommandIndex is used to count the payload fragments in the case where the entire
     * payload (snapshot) does not fit into one message. The CommandIndex starts at 0 and is
     * incremented for each fragment belonging to the same command.
     *
     * @param commandIndex the Command Index
     */
    public void setCommandIndex(final Integer commandIndex) {
        this.commandIndex = commandIndex;
    }

    /**
     * Gets Total Number Of Commands.
     * <p>
     * In the case where the entire payload (snapshot) does not fit into one message, the Total
     * Number of Commands field indicates the total number of sub-commands that will be
     * returned.
     *
     * @return the Total Number Of Commands
     */
    public Integer getTotalNumberOfCommands() {
        return totalNumberOfCommands;
    }

    /**
     * Sets Total Number Of Commands.
     * <p>
     * In the case where the entire payload (snapshot) does not fit into one message, the Total
     * Number of Commands field indicates the total number of sub-commands that will be
     * returned.
     *
     * @param totalNumberOfCommands the Total Number Of Commands
     */
    public void setTotalNumberOfCommands(final Integer totalNumberOfCommands) {
        this.totalNumberOfCommands = totalNumberOfCommands;
    }

    /**
     * Gets Snapshot Cause.
     * <p>
     * A 32-bit BitMap indicating the cause of the snapshot.
     *
     * @return the Snapshot Cause
     */
    public Integer getSnapshotCause() {
        return snapshotCause;
    }

    /**
     * Sets Snapshot Cause.
     * <p>
     * A 32-bit BitMap indicating the cause of the snapshot.
     *
     * @param snapshotCause the Snapshot Cause
     */
    public void setSnapshotCause(final Integer snapshotCause) {
        this.snapshotCause = snapshotCause;
    }

    /**
     * Gets Snapshot Payload Type.
     * <p>
     * The SnapshotPayloadType is an 8-bit enumerator defining the format of the
     * SnapshotSubPayload in this message. The server selects the SnapshotPayloadType
     * based on the charging scheme in use.
     * <p>
     * If the snapshot is taken by the server due to a change of Tariff Information (cause = 3)
     * which involves a change in charging scheme then two snapshots shall be taken, the first
     * according to the charging scheme being dismissed, the second to the scheme being
     * introduced.
     *
     * @return the Snapshot Payload Type
     */
    public Integer getSnapshotPayloadType() {
        return snapshotPayloadType;
    }

    /**
     * Sets Snapshot Payload Type.
     * <p>
     * The SnapshotPayloadType is an 8-bit enumerator defining the format of the
     * SnapshotSubPayload in this message. The server selects the SnapshotPayloadType
     * based on the charging scheme in use.
     * <p>
     * If the snapshot is taken by the server due to a change of Tariff Information (cause = 3)
     * which involves a change in charging scheme then two snapshots shall be taken, the first
     * according to the charging scheme being dismissed, the second to the scheme being
     * introduced.
     *
     * @param snapshotPayloadType the Snapshot Payload Type
     */
    public void setSnapshotPayloadType(final Integer snapshotPayloadType) {
        this.snapshotPayloadType = snapshotPayloadType;
    }

    /**
     * Gets Snapshot Payload.
     * <p>
     * The format of the SnapshotSub-Payload differs depending on the SnapshotPayloadType,
     * as shown below. Note that, where the entire payload (snapshot) does not fit into one
     * message, only the leading (non-Sub-Payload) fields of the Snapshot payload are
     * repeated in each command; the SnapshotSub-Payload is divided over the required number
     * of commands.
     *
     * @return the Snapshot Payload
     */
    public Integer getSnapshotPayload() {
        return snapshotPayload;
    }

    /**
     * Sets Snapshot Payload.
     * <p>
     * The format of the SnapshotSub-Payload differs depending on the SnapshotPayloadType,
     * as shown below. Note that, where the entire payload (snapshot) does not fit into one
     * message, only the leading (non-Sub-Payload) fields of the Snapshot payload are
     * repeated in each command; the SnapshotSub-Payload is divided over the required number
     * of commands.
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
        final StringBuilder builder = new StringBuilder(299);
        builder.append("PublishSnapshot [");
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
