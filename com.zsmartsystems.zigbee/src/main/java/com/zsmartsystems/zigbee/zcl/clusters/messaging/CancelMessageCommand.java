/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.messaging;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Cancel Message Command value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * The Cancel Message command provides the ability to cancel the sending or acceptance of
 * previously sent messages. When this message is received the recipient device has the option
 * of clearing any display or user interfaces it supports, or has the option of logging the
 * message for future reference.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class CancelMessageCommand extends ZclCommand {
    /**
     * Message ID command message field.
     * <p>
     * A unique unsigned 32-bit number identifier for the message being cancelled. It’s
     * expected the value contained in this field is a unique number managed by upstream
     * systems or a UTC based time stamp (UTCTime data type) identifying when the message was
     * originally issued.
     */
    private Integer messageId;

    /**
     * Message Control command message field.
     * <p>
     * This field is deprecated and should be set to 0x00.
     */
    private Integer messageControl;

    /**
     * Default constructor.
     */
    public CancelMessageCommand() {
        genericCommand = false;
        clusterId = 0x0703;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Message ID.
     * <p>
     * A unique unsigned 32-bit number identifier for the message being cancelled. It’s
     * expected the value contained in this field is a unique number managed by upstream
     * systems or a UTC based time stamp (UTCTime data type) identifying when the message was
     * originally issued.
     *
     * @return the Message ID
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * Sets Message ID.
     * <p>
     * A unique unsigned 32-bit number identifier for the message being cancelled. It’s
     * expected the value contained in this field is a unique number managed by upstream
     * systems or a UTC based time stamp (UTCTime data type) identifying when the message was
     * originally issued.
     *
     * @param messageId the Message ID
     */
    public void setMessageId(final Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * Gets Message Control.
     * <p>
     * This field is deprecated and should be set to 0x00.
     *
     * @return the Message Control
     */
    public Integer getMessageControl() {
        return messageControl;
    }

    /**
     * Sets Message Control.
     * <p>
     * This field is deprecated and should be set to 0x00.
     *
     * @param messageControl the Message Control
     */
    public void setMessageControl(final Integer messageControl) {
        this.messageControl = messageControl;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(messageId, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(messageControl, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        messageId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        messageControl = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(86);
        builder.append("CancelMessageCommand [");
        builder.append(super.toString());
        builder.append(", messageId=");
        builder.append(messageId);
        builder.append(", messageControl=");
        builder.append(messageControl);
        builder.append(']');
        return builder.toString();
    }

}
