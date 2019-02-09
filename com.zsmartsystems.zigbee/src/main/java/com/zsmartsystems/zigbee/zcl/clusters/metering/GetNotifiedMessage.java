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
 * Get Notified Message value object class.
 * <p>
 * Cluster: <b>Metering</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Metering cluster.
 * <p>
 * The GetNotifiedMessage command is used only when a BOMD is being mirrored. This command
 * provides a method for the BOMD to notify the Mirror message queue that it wants to receive
 * commands that the Mirror has queued. The Notification flags set within the command shall
 * inform the mirror of the commands that the BOMD is requesting.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetNotifiedMessage extends ZclCommand {
    /**
     * Notification Scheme command message field.
     * <p>
     * An unsigned 8-bit integer that allows for the pre-loading of the Notification Flags bit
     * mapping to ZCL or Smart Energy Standard commands.
     */
    private Integer notificationScheme;

    /**
     * Notification Flag Attribute ID command message field.
     * <p>
     * An unsigned 16-bit integer that denotes the attribute ID of the notification flag (1-8)
     * that is included in this command.
     */
    private Integer notificationFlagAttributeId;

    /**
     * Notification Flags N command message field.
     * <p>
     * The Notification Flags attribute/parameter indicating the command being requested.
     */
    private Integer notificationFlagsN;

    /**
     * Default constructor.
     */
    public GetNotifiedMessage() {
        genericCommand = false;
        clusterId = 0x0702;
        commandId = 11;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Notification Scheme.
     * <p>
     * An unsigned 8-bit integer that allows for the pre-loading of the Notification Flags bit
     * mapping to ZCL or Smart Energy Standard commands.
     *
     * @return the Notification Scheme
     */
    public Integer getNotificationScheme() {
        return notificationScheme;
    }

    /**
     * Sets Notification Scheme.
     * <p>
     * An unsigned 8-bit integer that allows for the pre-loading of the Notification Flags bit
     * mapping to ZCL or Smart Energy Standard commands.
     *
     * @param notificationScheme the Notification Scheme
     */
    public void setNotificationScheme(final Integer notificationScheme) {
        this.notificationScheme = notificationScheme;
    }

    /**
     * Gets Notification Flag Attribute ID.
     * <p>
     * An unsigned 16-bit integer that denotes the attribute ID of the notification flag (1-8)
     * that is included in this command.
     *
     * @return the Notification Flag Attribute ID
     */
    public Integer getNotificationFlagAttributeId() {
        return notificationFlagAttributeId;
    }

    /**
     * Sets Notification Flag Attribute ID.
     * <p>
     * An unsigned 16-bit integer that denotes the attribute ID of the notification flag (1-8)
     * that is included in this command.
     *
     * @param notificationFlagAttributeId the Notification Flag Attribute ID
     */
    public void setNotificationFlagAttributeId(final Integer notificationFlagAttributeId) {
        this.notificationFlagAttributeId = notificationFlagAttributeId;
    }

    /**
     * Gets Notification Flags N.
     * <p>
     * The Notification Flags attribute/parameter indicating the command being requested.
     *
     * @return the Notification Flags N
     */
    public Integer getNotificationFlagsN() {
        return notificationFlagsN;
    }

    /**
     * Sets Notification Flags N.
     * <p>
     * The Notification Flags attribute/parameter indicating the command being requested.
     *
     * @param notificationFlagsN the Notification Flags N
     */
    public void setNotificationFlagsN(final Integer notificationFlagsN) {
        this.notificationFlagsN = notificationFlagsN;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(notificationScheme, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(notificationFlagAttributeId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(notificationFlagsN, ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        notificationScheme = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        notificationFlagAttributeId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        notificationFlagsN = (Integer) deserializer.deserialize(ZclDataType.BITMAP_32_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(144);
        builder.append("GetNotifiedMessage [");
        builder.append(super.toString());
        builder.append(", notificationScheme=");
        builder.append(notificationScheme);
        builder.append(", notificationFlagAttributeId=");
        builder.append(notificationFlagAttributeId);
        builder.append(", notificationFlagsN=");
        builder.append(notificationFlagsN);
        builder.append(']');
        return builder.toString();
    }

}
