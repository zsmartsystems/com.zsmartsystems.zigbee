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
 * Display Message Command value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class DisplayMessageCommand extends ZclCommand {
    /**
     * Message ID command message field.
     * <p>
     * A unique unsigned 32-bit number identifier for this message. It’s expected the value
     * contained in this field is a unique number managed by upstream systems or a UTC based time
     * stamp (UTCTime data type) identifying when the message was issued.
     */
    private Integer messageId;

    /**
     * Message Control command message field.
     * <p>
     * An 8-bit BitMap field indicating control information related to the message.
     */
    private Integer messageControl;

    /**
     * Start Time command message field.
     * <p>
     * A UTCTime field to denote the time at which the message becomes valid. A Start Time of
     * 0x00000000 is a special time denoting “now.” If the device would send an event with a
     * Start Time of now, adjust the Duration In Minutes field to correspond to the remainder of
     * the event.
     */
    private Calendar startTime;

    /**
     * Duration In Minutes command message field.
     * <p>
     * An unsigned 16-bit field is used to denote the amount of time in minutes after the Start
     * Time during which the message is displayed. A Maximum value of 0xFFFF means “until
     * changed”.
     */
    private Integer durationInMinutes;

    /**
     * Message command message field.
     * <p>
     * A ZCL String containing the message to be delivered. The String shall be encoded in the
     * UTF-8 format. Devices will have the ability to choose the methods for managing messages
     * that are larger than can be displayed (truncation, scrolling, etc.). For supporting
     * larger messages sent over the network, both devices must agree upon a common
     * Fragmentation ASDU Maximum Incoming Transfer Size. Any message that needs truncation
     * shall truncate on a UTF-8 character boundary. The SE secure payload is 59 bytes for the
     * Message field in a non- fragmented, non-source routed Display Message packet (11 bytes
     * for other Display Message fields). Devices using fragmentation can send a message
     * larger than this. Reserving bytes for source route will reduce this.
     */
    private String message;

    /**
     * Extended Message Control command message field.
     * <p>
     * An 8-bit BitMap field indicating additional control and status information for a given
     * message.
     */
    private Integer extendedMessageControl;

    /**
     * Default constructor.
     */
    public DisplayMessageCommand() {
        genericCommand = false;
        clusterId = 0x0703;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Message ID.
     * <p>
     * A unique unsigned 32-bit number identifier for this message. It’s expected the value
     * contained in this field is a unique number managed by upstream systems or a UTC based time
     * stamp (UTCTime data type) identifying when the message was issued.
     *
     * @return the Message ID
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * Sets Message ID.
     * <p>
     * A unique unsigned 32-bit number identifier for this message. It’s expected the value
     * contained in this field is a unique number managed by upstream systems or a UTC based time
     * stamp (UTCTime data type) identifying when the message was issued.
     *
     * @param messageId the Message ID
     */
    public void setMessageId(final Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets Message Control.
     * <p>
     * An 8-bit BitMap field indicating control information related to the message.
     *
     * @return the Message Control
     */
    public Integer getMessageControl() {
        return messageControl;
    }

    /**
     * Sets Message Control.
     * <p>
     * An 8-bit BitMap field indicating control information related to the message.
     *
     * @param messageControl the Message Control
     */
    public void setMessageControl(final Integer messageControl) {
        this.messageControl = messageControl;
    }

    /**
     * Gets Start Time.
     * <p>
     * A UTCTime field to denote the time at which the message becomes valid. A Start Time of
     * 0x00000000 is a special time denoting “now.” If the device would send an event with a
     * Start Time of now, adjust the Duration In Minutes field to correspond to the remainder of
     * the event.
     *
     * @return the Start Time
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Sets Start Time.
     * <p>
     * A UTCTime field to denote the time at which the message becomes valid. A Start Time of
     * 0x00000000 is a special time denoting “now.” If the device would send an event with a
     * Start Time of now, adjust the Duration In Minutes field to correspond to the remainder of
     * the event.
     *
     * @param startTime the Start Time
     */
    public void setStartTime(final Calendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets Duration In Minutes.
     * <p>
     * An unsigned 16-bit field is used to denote the amount of time in minutes after the Start
     * Time during which the message is displayed. A Maximum value of 0xFFFF means “until
     * changed”.
     *
     * @return the Duration In Minutes
     */
    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    /**
     * Sets Duration In Minutes.
     * <p>
     * An unsigned 16-bit field is used to denote the amount of time in minutes after the Start
     * Time during which the message is displayed. A Maximum value of 0xFFFF means “until
     * changed”.
     *
     * @param durationInMinutes the Duration In Minutes
     */
    public void setDurationInMinutes(final Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    /**
     * Gets Message.
     * <p>
     * A ZCL String containing the message to be delivered. The String shall be encoded in the
     * UTF-8 format. Devices will have the ability to choose the methods for managing messages
     * that are larger than can be displayed (truncation, scrolling, etc.). For supporting
     * larger messages sent over the network, both devices must agree upon a common
     * Fragmentation ASDU Maximum Incoming Transfer Size. Any message that needs truncation
     * shall truncate on a UTF-8 character boundary. The SE secure payload is 59 bytes for the
     * Message field in a non- fragmented, non-source routed Display Message packet (11 bytes
     * for other Display Message fields). Devices using fragmentation can send a message
     * larger than this. Reserving bytes for source route will reduce this.
     *
     * @return the Message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets Message.
     * <p>
     * A ZCL String containing the message to be delivered. The String shall be encoded in the
     * UTF-8 format. Devices will have the ability to choose the methods for managing messages
     * that are larger than can be displayed (truncation, scrolling, etc.). For supporting
     * larger messages sent over the network, both devices must agree upon a common
     * Fragmentation ASDU Maximum Incoming Transfer Size. Any message that needs truncation
     * shall truncate on a UTF-8 character boundary. The SE secure payload is 59 bytes for the
     * Message field in a non- fragmented, non-source routed Display Message packet (11 bytes
     * for other Display Message fields). Devices using fragmentation can send a message
     * larger than this. Reserving bytes for source route will reduce this.
     *
     * @param message the Message
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets Extended Message Control.
     * <p>
     * An 8-bit BitMap field indicating additional control and status information for a given
     * message.
     *
     * @return the Extended Message Control
     */
    public Integer getExtendedMessageControl() {
        return extendedMessageControl;
    }

    /**
     * Sets Extended Message Control.
     * <p>
     * An 8-bit BitMap field indicating additional control and status information for a given
     * message.
     *
     * @param extendedMessageControl the Extended Message Control
     */
    public void setExtendedMessageControl(final Integer extendedMessageControl) {
        this.extendedMessageControl = extendedMessageControl;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(messageId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(messageControl, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(startTime, ZclDataType.UTCTIME);
        serializer.serialize(durationInMinutes, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(message, ZclDataType.CHARACTER_STRING);
        serializer.serialize(extendedMessageControl, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        messageId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        messageControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        startTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        durationInMinutes = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        message = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
        extendedMessageControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(222);
        builder.append("DisplayMessageCommand [");
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
        builder.append(", extendedMessageControl=");
        builder.append(extendedMessageControl);
        builder.append(']');
        return builder.toString();
    }

}
