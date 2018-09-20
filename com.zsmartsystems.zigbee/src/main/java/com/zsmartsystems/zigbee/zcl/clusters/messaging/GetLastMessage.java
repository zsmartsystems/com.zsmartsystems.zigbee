/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.messaging;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ZigBeeUtcTime;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Last Message value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command ID 0x00 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * On receipt of this command, the device shall send a Display Message or Display Protected
 * Message command as appropriate. A ZCL Default Response with status NOT_FOUND shall be
 * returned if no message is available.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T21:19:38Z")
public class GetLastMessage extends ZclMessagingCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0703;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Message ID command message field.
     */
    private Integer messageId;

    /**
     * Message Control command message field.
     */
    private Integer messageControl;

    /**
     * Start Time command message field.
     */
    private ZigBeeUtcTime startTime;

    /**
     * Duration In Minutes command message field.
     */
    private Integer durationInMinutes;

    /**
     * Message command message field.
     */
    private String message;

    /**
     * Optional Extended Message Control command message field.
     */
    private Integer optionalExtendedMessageControl;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetLastMessage() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param messageId {@link Integer} Message ID
     * @param messageControl {@link Integer} Message Control
     * @param startTime {@link ZigBeeUtcTime} Start Time
     * @param durationInMinutes {@link Integer} Duration In Minutes
     * @param message {@link String} Message
     * @param optionalExtendedMessageControl {@link Integer} Optional Extended Message Control
     */
    public GetLastMessage(
            Integer messageId,
            Integer messageControl,
            ZigBeeUtcTime startTime,
            Integer durationInMinutes,
            String message,
            Integer optionalExtendedMessageControl) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.messageId = messageId;
        this.messageControl = messageControl;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
        this.message = message;
        this.optionalExtendedMessageControl = optionalExtendedMessageControl;
    }

    /**
     * Gets Message ID.
     *
     * @return the Message ID
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * Sets Message ID.
     *
     * @param messageId the Message ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMessageId(final Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets Message Control.
     *
     * @return the Message Control
     */
    public Integer getMessageControl() {
        return messageControl;
    }

    /**
     * Sets Message Control.
     *
     * @param messageControl the Message Control
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMessageControl(final Integer messageControl) {
        this.messageControl = messageControl;
    }

    /**
     * Gets Start Time.
     *
     * @return the Start Time
     */
    public ZigBeeUtcTime getStartTime() {
        return startTime;
    }

    /**
     * Sets Start Time.
     *
     * @param startTime the Start Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartTime(final ZigBeeUtcTime startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets Duration In Minutes.
     *
     * @return the Duration In Minutes
     */
    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    /**
     * Sets Duration In Minutes.
     *
     * @param durationInMinutes the Duration In Minutes
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDurationInMinutes(final Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    /**
     * Gets Message.
     *
     * @return the Message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets Message.
     *
     * @param message the Message
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets Optional Extended Message Control.
     *
     * @return the Optional Extended Message Control
     */
    public Integer getOptionalExtendedMessageControl() {
        return optionalExtendedMessageControl;
    }

    /**
     * Sets Optional Extended Message Control.
     *
     * @param optionalExtendedMessageControl the Optional Extended Message Control
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOptionalExtendedMessageControl(final Integer optionalExtendedMessageControl) {
        this.optionalExtendedMessageControl = optionalExtendedMessageControl;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(messageId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(messageControl, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(durationInMinutes, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(message, ZclDataType.CHARACTER_STRING);
        serializer.serialize(optionalExtendedMessageControl, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        messageId = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        messageControl = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        startTime = deserializer.deserialize(ZclDataType.UTCTIME);
        durationInMinutes = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        message = deserializer.deserialize(ZclDataType.CHARACTER_STRING);
        optionalExtendedMessageControl = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(223);
        builder.append("GetLastMessage [");
        builder.append(super.toString());
        builder.append(", messageId=");
        builder.append(messageId);
        builder.append(", messageControl=");
        builder.append(messageControl);
        builder.append(", startTime=");
        builder.append(startTime);
        builder.append(", durationInMinutes=");
        builder.append(durationInMinutes);
        builder.append(", message=");
        builder.append(message);
        builder.append(", optionalExtendedMessageControl=");
        builder.append(optionalExtendedMessageControl);
        builder.append(']');
        return builder.toString();
    }

}
