/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.messaging;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Last Message value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * On receipt of this command, the device shall send a Display Message or Display Protected
 * Message command as appropriate. A ZCL Default Response with status NOT_FOUND shall be
 * returned if no message is available.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetLastMessage extends ZclCommand {
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
    private Calendar startTime;

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
     */
    public GetLastMessage() {
        genericCommand = false;
        clusterId = 0x0703;
        commandId = 0;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     */
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
     */
    public void setMessageControl(final Integer messageControl) {
        this.messageControl = messageControl;
    }

    /**
     * Gets Start Time.
     *
     * @return the Start Time
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Sets Start Time.
     *
     * @param startTime the Start Time
     */
    public void setStartTime(final Calendar startTime) {
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
     */
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
     */
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
     */
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
        messageId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        messageControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        durationInMinutes = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        message = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
        optionalExtendedMessageControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
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
