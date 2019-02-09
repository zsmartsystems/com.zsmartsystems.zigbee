/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.CancelAllMessages;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.CancelAllMessagesCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.CancelMessageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.DisplayMessageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.DisplayProtectedMessageCommand;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.GetLastMessage;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.GetMessageCancellation;
import com.zsmartsystems.zigbee.zcl.clusters.messaging.MessageConfirmation;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * <b>Messaging</b> cluster implementation (<i>Cluster ID 0x0703</i>).
 * <p>
 * This cluster provides an interface for passing text messages between ZigBee devices.
 * Messages are expected to be delivered via the ESI and then unicast to all individually
 * registered devices implementing the Messaging Cluster on the ZigBee network, or just made
 * available to all devices for later pickup. Nested and overlapping messages are not allowed.
 * The current active message will be replaced if a new message is received by the ESI.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ZclMessagingCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0703;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Messaging";

    // Attribute initialisation
    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(0);

        return attributeMap;
    }

    /**
     * Default constructor to create a Messaging cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclMessagingCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * The Display Message Command
     *
     * @param messageId {@link Integer} Message ID
     * @param messageControl {@link Integer} Message Control
     * @param startTime {@link Calendar} Start Time
     * @param durationInMinutes {@link Integer} Duration In Minutes
     * @param message {@link String} Message
     * @param extendedMessageControl {@link Integer} Extended Message Control
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> displayMessageCommand(Integer messageId, Integer messageControl, Calendar startTime, Integer durationInMinutes, String message, Integer extendedMessageControl) {
        DisplayMessageCommand command = new DisplayMessageCommand();

        // Set the fields
        command.setMessageId(messageId);
        command.setMessageControl(messageControl);
        command.setStartTime(startTime);
        command.setDurationInMinutes(durationInMinutes);
        command.setMessage(message);
        command.setExtendedMessageControl(extendedMessageControl);

        return send(command);
    }

    /**
     * The Cancel Message Command
     * <p>
     * The Cancel Message command provides the ability to cancel the sending or acceptance of
     * previously sent messages. When this message is received the recipient device has the
     * option of clearing any display or user interfaces it supports, or has the option of
     * logging the message for future reference.
     *
     * @param messageId {@link Integer} Message ID
     * @param messageControl {@link Integer} Message Control
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> cancelMessageCommand(Integer messageId, Integer messageControl) {
        CancelMessageCommand command = new CancelMessageCommand();

        // Set the fields
        command.setMessageId(messageId);
        command.setMessageControl(messageControl);

        return send(command);
    }

    /**
     * The Display Protected Message Command
     * <p>
     * The Display Protected Message command is for use with messages that are protected by a
     * password or PIN
     *
     * @param messageId {@link Integer} Message ID
     * @param messageControl {@link Integer} Message Control
     * @param startTime {@link Calendar} Start Time
     * @param durationInMinutes {@link Integer} Duration In Minutes
     * @param message {@link String} Message
     * @param extendedMessageControl {@link Integer} Extended Message Control
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> displayProtectedMessageCommand(Integer messageId, Integer messageControl, Calendar startTime, Integer durationInMinutes, String message, Integer extendedMessageControl) {
        DisplayProtectedMessageCommand command = new DisplayProtectedMessageCommand();

        // Set the fields
        command.setMessageId(messageId);
        command.setMessageControl(messageControl);
        command.setStartTime(startTime);
        command.setDurationInMinutes(durationInMinutes);
        command.setMessage(message);
        command.setExtendedMessageControl(extendedMessageControl);

        return send(command);
    }

    /**
     * The Cancel All Messages Command
     * <p>
     * The Cancel All Messages command indicates to a CLIENT | device that it should cancel all
     * display messages currently held by it.
     *
     * @param implementationTime {@link Calendar} Implementation Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> cancelAllMessagesCommand(Calendar implementationTime) {
        CancelAllMessagesCommand command = new CancelAllMessagesCommand();

        // Set the fields
        command.setImplementationTime(implementationTime);

        return send(command);
    }

    /**
     * The Get Last Message
     * <p>
     * On receipt of this command, the device shall send a Display Message or Display Protected
     * Message command as appropriate. A ZCL Default Response with status NOT_FOUND shall be
     * returned if no message is available.
     *
     * @param messageId {@link Integer} Message ID
     * @param messageControl {@link Integer} Message Control
     * @param startTime {@link Calendar} Start Time
     * @param durationInMinutes {@link Integer} Duration In Minutes
     * @param message {@link String} Message
     * @param optionalExtendedMessageControl {@link Integer} Optional Extended Message Control
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getLastMessage(Integer messageId, Integer messageControl, Calendar startTime, Integer durationInMinutes, String message, Integer optionalExtendedMessageControl) {
        GetLastMessage command = new GetLastMessage();

        // Set the fields
        command.setMessageId(messageId);
        command.setMessageControl(messageControl);
        command.setStartTime(startTime);
        command.setDurationInMinutes(durationInMinutes);
        command.setMessage(message);
        command.setOptionalExtendedMessageControl(optionalExtendedMessageControl);

        return send(command);
    }

    /**
     * The Message Confirmation
     * <p>
     * The Message Confirmation command provides an indication that a Utility Customer has
     * acknowledged and/or accepted the contents of a previously sent message. Enhanced
     * Message Confirmation commands shall contain an answer of ‘NO’, ‘YES’ and/or a message
     * confirmation string.
     *
     * @param messageId {@link Integer} Message ID
     * @param confirmationTime {@link Calendar} Confirmation Time
     * @param messageConfirmationControl {@link Integer} Message Confirmation Control
     * @param messageConfirmationResponse {@link ByteArray} Message Confirmation Response
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> messageConfirmation(Integer messageId, Calendar confirmationTime, Integer messageConfirmationControl, ByteArray messageConfirmationResponse) {
        MessageConfirmation command = new MessageConfirmation();

        // Set the fields
        command.setMessageId(messageId);
        command.setConfirmationTime(confirmationTime);
        command.setMessageConfirmationControl(messageConfirmationControl);
        command.setMessageConfirmationResponse(messageConfirmationResponse);

        return send(command);
    }

    /**
     * The Get Message Cancellation
     * <p>
     * This command initiates the return of the first (and maybe only) Cancel All Messages
     * command held on the associated server, and which has an implementation time equal to or
     * later than the value indicated in the payload.
     *
     * @param earliestImplementationTime {@link Calendar} Earliest Implementation Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getMessageCancellation(Calendar earliestImplementationTime) {
        GetMessageCancellation command = new GetMessageCancellation();

        // Set the fields
        command.setEarliestImplementationTime(earliestImplementationTime);

        return send(command);
    }

    /**
     * The Cancel All Messages
     * <p>
     * The CancelAllMessages command indicates to a client device that it should cancel all
     * display messages currently held by it.
     *
     * @param implementationDateTime {@link Calendar} Implementation Date Time
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> cancelAllMessages(Calendar implementationDateTime) {
        CancelAllMessages command = new CancelAllMessages();

        // Set the fields
        command.setImplementationDateTime(implementationDateTime);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0x00: // DISPLAY_MESSAGE_COMMAND
                return new DisplayMessageCommand();
            case 0x01: // CANCEL_MESSAGE_COMMAND
                return new CancelMessageCommand();
            case 0x02: // DISPLAY_PROTECTED_MESSAGE_COMMAND
                return new DisplayProtectedMessageCommand();
            case 0x03: // CANCEL_ALL_MESSAGES_COMMAND
                return new CancelAllMessagesCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0x00: // GET_LAST_MESSAGE
                return new GetLastMessage();
            case 0x01: // MESSAGE_CONFIRMATION
                return new MessageConfirmation();
            case 0x02: // GET_MESSAGE_CANCELLATION
                return new GetMessageCancellation();
            case 0x03: // CANCEL_ALL_MESSAGES
                return new CancelAllMessages();
            default:
                return null;
        }
    }
}
