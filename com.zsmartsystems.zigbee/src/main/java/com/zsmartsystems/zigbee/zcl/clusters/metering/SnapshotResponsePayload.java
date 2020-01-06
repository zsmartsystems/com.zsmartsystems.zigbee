/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.metering;
import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeSerializable;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Snapshot Response Payload structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class SnapshotResponsePayload implements ZigBeeSerializable {
    /**
     * Snapshot Schedule ID structure field.
     */
    private Integer snapshotScheduleId;

    /**
     * Snapshot Schedule Confirmation structure field.
     */
    private Integer snapshotScheduleConfirmation;



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
     * @return the SnapshotResponsePayload command
     */
    public SnapshotResponsePayload setSnapshotScheduleId(final Integer snapshotScheduleId) {
        this.snapshotScheduleId = snapshotScheduleId;
        return this;
    }

    /**
     * Gets Snapshot Schedule Confirmation.
     *
     * @return the Snapshot Schedule Confirmation
     */
    public Integer getSnapshotScheduleConfirmation() {
        return snapshotScheduleConfirmation;
    }

    /**
     * Sets Snapshot Schedule Confirmation.
     *
     * @param snapshotScheduleConfirmation the Snapshot Schedule Confirmation
     * @return the SnapshotResponsePayload command
     */
    public SnapshotResponsePayload setSnapshotScheduleConfirmation(final Integer snapshotScheduleConfirmation) {
        this.snapshotScheduleConfirmation = snapshotScheduleConfirmation;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(snapshotScheduleId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(snapshotScheduleConfirmation, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        snapshotScheduleId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        snapshotScheduleConfirmation = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(112);
        builder.append("SnapshotResponsePayload [");
        builder.append(super.toString());
        builder.append(", snapshotScheduleId=");
        builder.append(snapshotScheduleId);
        builder.append(", snapshotScheduleConfirmation=");
        builder.append(snapshotScheduleConfirmation);
        builder.append(']');
        return builder.toString();
    }
}
