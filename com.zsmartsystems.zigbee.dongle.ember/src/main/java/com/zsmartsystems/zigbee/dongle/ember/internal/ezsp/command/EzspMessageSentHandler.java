/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberOutgoingMessageType;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;

/**
 * Class to implement the Ember EZSP command <b>messageSentHandler</b>.
 * <p>
 * A callback indicating the stack has completed sending a message.
 * <p>
 * This class provides methods for processing EZSP commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class EzspMessageSentHandler extends EzspFrameResponse {
    public static int FRAME_ID = 0x3F;

    /**
     * The type of message sent.
     * <p>
     * EZSP type is <i>EmberOutgoingMessageType</i> - Java type is {@link EmberOutgoingMessageType}
     */
    private EmberOutgoingMessageType type;

    /**
     * The destination to which the message was sent, for direct unicasts, or the address table or
     * binding index for other unicasts. The value is unspecified for multicasts and broadcasts.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     */
    private int indexOrDestination;

    /**
     * The APS frame for the message.
     * <p>
     * EZSP type is <i>EmberApsFrame</i> - Java type is {@link EmberApsFrame}
     */
    private EmberApsFrame apsFrame;

    /**
     * The value supplied by the Host in the ezspSendUnicast, ezspSendBroadcast or
     * ezspSendMulticast command.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     */
    private int messageTag;

    /**
     * An EmberStatus value of EMBER_SUCCESS if an ACK was received from the destination or
     * EMBER_DELIVERY_FAILED if no ACK was received.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     */
    private EmberStatus status;

    /**
     * The unicast message supplied by the Host. The message contents are only included here if the
     * decision for the messageContentsInCallback policy is
     * messageTagAndContentsInCallback.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     */
    private int[] messageContents;

    /**
     * Response and Handler constructor
     */
    public EzspMessageSentHandler(int[] inputBuffer) {
        // Super creates deserializer and reads header fields
        super(inputBuffer);

        // Deserialize the fields
        type = deserializer.deserializeEmberOutgoingMessageType();
        indexOrDestination = deserializer.deserializeUInt16();
        apsFrame = deserializer.deserializeEmberApsFrame();
        messageTag = deserializer.deserializeUInt8();
        status = deserializer.deserializeEmberStatus();
        int messageLength = deserializer.deserializeUInt8();
        messageContents= deserializer.deserializeUInt8Array(messageLength);
    }

    /**
     * The type of message sent.
     * <p>
     * EZSP type is <i>EmberOutgoingMessageType</i> - Java type is {@link EmberOutgoingMessageType}
     *
     * @return the current type as {@link EmberOutgoingMessageType}
     */
    public EmberOutgoingMessageType getType() {
        return type;
    }

    /**
     * The type of message sent.
     *
     * @param type the type to set as {@link EmberOutgoingMessageType}
     */
    public void setType(EmberOutgoingMessageType type) {
        this.type = type;
    }

    /**
     * The destination to which the message was sent, for direct unicasts, or the address table or
     * binding index for other unicasts. The value is unspecified for multicasts and broadcasts.
     * <p>
     * EZSP type is <i>uint16_t</i> - Java type is {@link int}
     *
     * @return the current indexOrDestination as {@link int}
     */
    public int getIndexOrDestination() {
        return indexOrDestination;
    }

    /**
     * The destination to which the message was sent, for direct unicasts, or the address table or
     * binding index for other unicasts. The value is unspecified for multicasts and broadcasts.
     *
     * @param indexOrDestination the indexOrDestination to set as {@link int}
     */
    public void setIndexOrDestination(int indexOrDestination) {
        this.indexOrDestination = indexOrDestination;
    }

    /**
     * The APS frame for the message.
     * <p>
     * EZSP type is <i>EmberApsFrame</i> - Java type is {@link EmberApsFrame}
     *
     * @return the current apsFrame as {@link EmberApsFrame}
     */
    public EmberApsFrame getApsFrame() {
        return apsFrame;
    }

    /**
     * The APS frame for the message.
     *
     * @param apsFrame the apsFrame to set as {@link EmberApsFrame}
     */
    public void setApsFrame(EmberApsFrame apsFrame) {
        this.apsFrame = apsFrame;
    }

    /**
     * The value supplied by the Host in the ezspSendUnicast, ezspSendBroadcast or
     * ezspSendMulticast command.
     * <p>
     * EZSP type is <i>uint8_t</i> - Java type is {@link int}
     *
     * @return the current messageTag as {@link int}
     */
    public int getMessageTag() {
        return messageTag;
    }

    /**
     * The value supplied by the Host in the ezspSendUnicast, ezspSendBroadcast or
     * ezspSendMulticast command.
     *
     * @param messageTag the messageTag to set as {@link int}
     */
    public void setMessageTag(int messageTag) {
        this.messageTag = messageTag;
    }

    /**
     * An EmberStatus value of EMBER_SUCCESS if an ACK was received from the destination or
     * EMBER_DELIVERY_FAILED if no ACK was received.
     * <p>
     * EZSP type is <i>EmberStatus</i> - Java type is {@link EmberStatus}
     *
     * @return the current status as {@link EmberStatus}
     */
    public EmberStatus getStatus() {
        return status;
    }

    /**
     * An EmberStatus value of EMBER_SUCCESS if an ACK was received from the destination or
     * EMBER_DELIVERY_FAILED if no ACK was received.
     *
     * @param status the status to set as {@link EmberStatus}
     */
    public void setStatus(EmberStatus status) {
        this.status = status;
    }

    /**
     * The unicast message supplied by the Host. The message contents are only included here if the
     * decision for the messageContentsInCallback policy is
     * messageTagAndContentsInCallback.
     * <p>
     * EZSP type is <i>uint8_t[]</i> - Java type is {@link int[]}
     *
     * @return the current messageContents as {@link int[]}
     */
    public int[] getMessageContents() {
        return messageContents;
    }

    /**
     * The unicast message supplied by the Host. The message contents are only included here if the
     * decision for the messageContentsInCallback policy is
     * messageTagAndContentsInCallback.
     *
     * @param messageContents the messageContents to set as {@link int[]}
     */
    public void setMessageContents(int[] messageContents) {
        this.messageContents = messageContents;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("EzspMessageSentHandler [type=");
        builder.append(type);
        builder.append(", indexOrDestination=");
        builder.append(indexOrDestination);
        builder.append(", apsFrame=");
        builder.append(apsFrame);
        builder.append(", messageTag=");
        builder.append(messageTag);
        builder.append(", status=");
        builder.append(status);
        builder.append(", messageContents=");
        for (int c = 0; c < messageContents.length; c++) {
            if (c > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", messageContents[c]));
        }
        builder.append(']');
        return builder.toString();
    }
}
