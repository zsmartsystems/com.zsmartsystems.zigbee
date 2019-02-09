/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Take Snapshot Response value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is generated in response to a TakeSnapshot command, and is sent to confirm
 * whether the requested snapshot has been accepted and successfully taken.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class TakeSnapshotResponse extends ZclCommand {
    /**
     * Snapshot ID command message field.
     * <p>
     * Unique identifier allocated by the device creating the snapshot. The value contained
     * in this field indicates the TakeSnapshot command for which this response is generated.
     */
    private Integer snapshotId;

    /**
     * Snapshot Confirmation command message field.
     * <p>
     * This is the acknowledgment from the device that it can support this required type of
     * snapshot.
     */
    private Integer snapshotConfirmation;

    /**
     * Default constructor.
     */
    public TakeSnapshotResponse() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 5;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Snapshot ID.
     * <p>
     * Unique identifier allocated by the device creating the snapshot. The value contained
     * in this field indicates the TakeSnapshot command for which this response is generated.
     *
     * @return the Snapshot ID
     */
    public Integer getSnapshotId() {
        return snapshotId;
    }

    /**
     * Sets Snapshot ID.
     * <p>
     * Unique identifier allocated by the device creating the snapshot. The value contained
     * in this field indicates the TakeSnapshot command for which this response is generated.
     *
     * @param snapshotId the Snapshot ID
     */
    public void setSnapshotId(final Integer snapshotId) {
        this.snapshotId = snapshotId;
    }

    /**
     * Gets Snapshot Confirmation.
     * <p>
     * This is the acknowledgment from the device that it can support this required type of
     * snapshot.
     *
     * @return the Snapshot Confirmation
     */
    public Integer getSnapshotConfirmation() {
        return snapshotConfirmation;
    }

    /**
     * Sets Snapshot Confirmation.
     * <p>
     * This is the acknowledgment from the device that it can support this required type of
     * snapshot.
     *
     * @param snapshotConfirmation the Snapshot Confirmation
     */
    public void setSnapshotConfirmation(final Integer snapshotConfirmation) {
        this.snapshotConfirmation = snapshotConfirmation;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(snapshotId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(snapshotConfirmation, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        snapshotId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        snapshotConfirmation = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(93);
        builder.append("TakeSnapshotResponse [");
        builder.append(super.toString());
        builder.append(", snapshotId=");
        builder.append(snapshotId);
        builder.append(", snapshotConfirmation=");
        builder.append(snapshotConfirmation);
        builder.append(']');
        return builder.toString();
    }

}
