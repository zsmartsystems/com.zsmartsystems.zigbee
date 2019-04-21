/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 *  structure implementation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class NotificationCommandSubPayload implements ZigBeeSerializable {
    /**
     * Cluster ID structure field.
     * <p>
     * An unsigned 16-bit integer that denotes the Cluster ID of the Notification flag that
     * will be configured for this Notification scheme.
     */
    private Integer clusterId;

    /**
     * Manufacturer Code structure field.
     * <p>
     * An unsigned 16-bit integer that denotes the Manufacturer Code to be used with these
     * command IDs, that are configured for this Notification flag within this Notification
     * scheme.
     */
    private Integer manufacturerCode;

    /**
     * Number Of Commands structure field.
     * <p>
     * An unsigned 8-bit integer that indicates the number of command identifiers contained
     * within this sub payload.
     */
    private Integer numberOfCommands;

    /**
     * Command IDs structure field.
     * <p>
     * An unsigned 8-bit integer that denotes the command that is to be used. The command ID
     * should be used with the cluster ID to reference the command(s).
     */
    private Integer commandIds;



    /**
     * Gets Cluster ID.
     * <p>
     * An unsigned 16-bit integer that denotes the Cluster ID of the Notification flag that
     * will be configured for this Notification scheme.
     *
     * @return the Cluster ID
     */
    public Integer getClusterId() {
        return clusterId;
    }

    /**
     * Sets Cluster ID.
     * <p>
     * An unsigned 16-bit integer that denotes the Cluster ID of the Notification flag that
     * will be configured for this Notification scheme.
     *
     * @param clusterId the Cluster ID
     */
    public void setClusterId(final Integer clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Gets Manufacturer Code.
     * <p>
     * An unsigned 16-bit integer that denotes the Manufacturer Code to be used with these
     * command IDs, that are configured for this Notification flag within this Notification
     * scheme.
     *
     * @return the Manufacturer Code
     */
    public Integer getManufacturerCode() {
        return manufacturerCode;
    }

    /**
     * Sets Manufacturer Code.
     * <p>
     * An unsigned 16-bit integer that denotes the Manufacturer Code to be used with these
     * command IDs, that are configured for this Notification flag within this Notification
     * scheme.
     *
     * @param manufacturerCode the Manufacturer Code
     */
    public void setManufacturerCode(final Integer manufacturerCode) {
        this.manufacturerCode = manufacturerCode;
    }

    /**
     * Gets Number Of Commands.
     * <p>
     * An unsigned 8-bit integer that indicates the number of command identifiers contained
     * within this sub payload.
     *
     * @return the Number Of Commands
     */
    public Integer getNumberOfCommands() {
        return numberOfCommands;
    }

    /**
     * Sets Number Of Commands.
     * <p>
     * An unsigned 8-bit integer that indicates the number of command identifiers contained
     * within this sub payload.
     *
     * @param numberOfCommands the Number Of Commands
     */
    public void setNumberOfCommands(final Integer numberOfCommands) {
        this.numberOfCommands = numberOfCommands;
    }

    /**
     * Gets Command IDs.
     * <p>
     * An unsigned 8-bit integer that denotes the command that is to be used. The command ID
     * should be used with the cluster ID to reference the command(s).
     *
     * @return the Command IDs
     */
    public Integer getCommandIds() {
        return commandIds;
    }

    /**
     * Sets Command IDs.
     * <p>
     * An unsigned 8-bit integer that denotes the command that is to be used. The command ID
     * should be used with the cluster ID to reference the command(s).
     *
     * @param commandIds the Command IDs
     */
    public void setCommandIds(final Integer commandIds) {
        this.commandIds = commandIds;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(manufacturerCode, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(numberOfCommands, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(commandIds, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        clusterId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        manufacturerCode = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        numberOfCommands = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        commandIds = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(163);
        builder.append("NotificationCommandSubPayload [");
        builder.append(super.toString());
        builder.append(", clusterId=");
        builder.append(clusterId);
        builder.append(", manufacturerCode=");
        builder.append(manufacturerCode);
        builder.append(", numberOfCommands=");
        builder.append(numberOfCommands);
        builder.append(", commandIds=");
        builder.append(commandIds);
        builder.append(']');
        return builder.toString();
    }
}
