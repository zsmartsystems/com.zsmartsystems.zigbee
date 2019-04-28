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
 * Take Snapshot value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command ID 0x05 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * This command is used to instruct the cluster server to take a single snapshot.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class TakeSnapshot extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0702;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x05;

    /**
     * Snapshot Cause command message field.
     * <p>
     * A 32-bit BitMap indicating the cause of the snapshot. Note that the Manually Triggered
     * from Client flag shall additionally be set for all Snapshots triggered in this manner.
     */
    private Integer snapshotCause;

    /**
     * Default constructor.
     */
    public TakeSnapshot() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Snapshot Cause.
     * <p>
     * A 32-bit BitMap indicating the cause of the snapshot. Note that the Manually Triggered
     * from Client flag shall additionally be set for all Snapshots triggered in this manner.
     *
     * @return the Snapshot Cause
     */
    public Integer getSnapshotCause() {
        return snapshotCause;
    }

    /**
     * Sets Snapshot Cause.
     * <p>
     * A 32-bit BitMap indicating the cause of the snapshot. Note that the Manually Triggered
     * from Client flag shall additionally be set for all Snapshots triggered in this manner.
     *
     * @param snapshotCause the Snapshot Cause
     */
    public void setSnapshotCause(final Integer snapshotCause) {
        this.snapshotCause = snapshotCause;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(snapshotCause, ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        snapshotCause = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(48);
        builder.append("TakeSnapshot [");
        builder.append(super.toString());
        builder.append(", snapshotCause=");
        builder.append(snapshotCause);
        builder.append(']');
        return builder.toString();
    }

}
