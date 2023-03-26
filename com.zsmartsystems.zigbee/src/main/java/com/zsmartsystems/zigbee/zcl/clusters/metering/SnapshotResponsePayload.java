/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-12T12:02:05Z")
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
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default contructor and setters.
     */
    @Deprecated
    public SnapshotResponsePayload() {
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param snapshotScheduleId {@link Integer} Snapshot Schedule ID
     * @param snapshotScheduleConfirmation {@link Integer} Snapshot Schedule Confirmation
     */
    public SnapshotResponsePayload(
            Integer snapshotScheduleId,
            Integer snapshotScheduleConfirmation) {

        this.snapshotScheduleId = snapshotScheduleId;
        this.snapshotScheduleConfirmation = snapshotScheduleConfirmation;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSnapshotScheduleConfirmation(final Integer snapshotScheduleConfirmation) {
        this.snapshotScheduleConfirmation = snapshotScheduleConfirmation;
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
