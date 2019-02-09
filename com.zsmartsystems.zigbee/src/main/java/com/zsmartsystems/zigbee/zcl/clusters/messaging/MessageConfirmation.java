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
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Message Confirmation value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * The Message Confirmation command provides an indication that a Utility Customer has
 * acknowledged and/or accepted the contents of a previously sent message. Enhanced Message
 * Confirmation commands shall contain an answer of ‘NO’, ‘YES’ and/or a message confirmation
 * string.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class MessageConfirmation extends ZclCommand {
    /**
     * Message ID command message field.
     * <p>
     * A unique unsigned 32-bit number identifier for the message being confirmed.
     */
    private Integer messageId;

    /**
     * Confirmation Time command message field.
     * <p>
     * UTCTime of user confirmation of message.
     */
    private Calendar confirmationTime;

    /**
     * Message Confirmation Control command message field.
     * <p>
     * An 8-bit BitMap field indicating the simple confirmation that is contained within the
     * response.
     */
    private Integer messageConfirmationControl;

    /**
     * Message Confirmation Response command message field.
     * <p>
     * A ZCL Octet String containing the message to be returned. The first Octet indicates
     * length. The string shall be encoded in the UTF-8 format. If this optional field is not
     * available, a default value of 0x00 shall be used.
     */
    private ByteArray messageConfirmationResponse;

    /**
     * Default constructor.
     */
    public MessageConfirmation() {
        genericCommand = false;
        clusterId = 0x0703;
        commandId = 1;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Message ID.
     * <p>
     * A unique unsigned 32-bit number identifier for the message being confirmed.
     *
     * @return the Message ID
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * Sets Message ID.
     * <p>
     * A unique unsigned 32-bit number identifier for the message being confirmed.
     *
     * @param messageId the Message ID
     */
    public void setMessageId(final Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets Confirmation Time.
     * <p>
     * UTCTime of user confirmation of message.
     *
     * @return the Confirmation Time
     */
    public Calendar getConfirmationTime() {
        return confirmationTime;
    }

    /**
     * Sets Confirmation Time.
     * <p>
     * UTCTime of user confirmation of message.
     *
     * @param confirmationTime the Confirmation Time
     */
    public void setConfirmationTime(final Calendar confirmationTime) {
        this.confirmationTime = confirmationTime;
    }

    /**
     * Gets Message Confirmation Control.
     * <p>
     * An 8-bit BitMap field indicating the simple confirmation that is contained within the
     * response.
     *
     * @return the Message Confirmation Control
     */
    public Integer getMessageConfirmationControl() {
        return messageConfirmationControl;
    }

    /**
     * Sets Message Confirmation Control.
     * <p>
     * An 8-bit BitMap field indicating the simple confirmation that is contained within the
     * response.
     *
     * @param messageConfirmationControl the Message Confirmation Control
     */
    public void setMessageConfirmationControl(final Integer messageConfirmationControl) {
        this.messageConfirmationControl = messageConfirmationControl;
    }

    /**
     * Gets Message Confirmation Response.
     * <p>
     * A ZCL Octet String containing the message to be returned. The first Octet indicates
     * length. The string shall be encoded in the UTF-8 format. If this optional field is not
     * available, a default value of 0x00 shall be used.
     *
     * @return the Message Confirmation Response
     */
    public ByteArray getMessageConfirmationResponse() {
        return messageConfirmationResponse;
    }

    /**
     * Sets Message Confirmation Response.
     * <p>
     * A ZCL Octet String containing the message to be returned. The first Octet indicates
     * length. The string shall be encoded in the UTF-8 format. If this optional field is not
     * available, a default value of 0x00 shall be used.
     *
     * @param messageConfirmationResponse the Message Confirmation Response
     */
    public void setMessageConfirmationResponse(final ByteArray messageConfirmationResponse) {
        this.messageConfirmationResponse = messageConfirmationResponse;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(messageId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(confirmationTime, ZclDataType.UTCTIME);
        serializer.serialize(messageConfirmationControl, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(messageConfirmationResponse, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        messageId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        confirmationTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
        messageConfirmationControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        messageConfirmationResponse = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(180);
        builder.append("MessageConfirmation [");
        builder.append(super.toString());
        builder.append(", messageId=");
        builder.append(messageId);
        builder.append(", confirmationTime=");
        builder.append(confirmationTime);
        builder.append(", messageConfirmationControl=");
        builder.append(messageConfirmationControl);
        builder.append(", messageConfirmationResponse=");
        builder.append(messageConfirmationResponse);
        builder.append(']');
        return builder.toString();
    }

}
