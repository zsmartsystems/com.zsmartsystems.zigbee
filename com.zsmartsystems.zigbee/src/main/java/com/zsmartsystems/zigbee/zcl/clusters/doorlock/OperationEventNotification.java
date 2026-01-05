/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Operation Event Notification value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x20 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * The door lock server sends out operation event notification when the event is triggered by
 * the various event sources. The specific operation event will only be sent out if the
 * associated bitmask is enabled in the various attributes in the Event Masks Attribute Set.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:41:29Z")
public class OperationEventNotification extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x20;

    /**
     * Operation Event Source command message field.
     */
    private Integer operationEventSource;

    /**
     * Operation Event Code command message field.
     */
    private Integer operationEventCode;

    /**
     * User ID command message field.
     */
    private Integer userId;

    /**
     * PIN command message field.
     */
    private ByteArray pin;

    /**
     * Local Time command message field.
     */
    private Integer localTime;

    /**
     * Data command message field.
     */
    private String data;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public OperationEventNotification() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param operationEventSource {@link Integer} Operation Event Source
     * @param operationEventCode {@link Integer} Operation Event Code
     * @param userId {@link Integer} User ID
     * @param pin {@link ByteArray} PIN
     * @param localTime {@link Integer} Local Time
     * @param data {@link String} Data
     */
    public OperationEventNotification(
            Integer operationEventSource,
            Integer operationEventCode,
            Integer userId,
            ByteArray pin,
            Integer localTime,
            String data) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.operationEventSource = operationEventSource;
        this.operationEventCode = operationEventCode;
        this.userId = userId;
        this.pin = pin;
        this.localTime = localTime;
        this.data = data;
    }

    /**
     * Gets Operation Event Source.
     *
     * @return the Operation Event Source
     */
    public Integer getOperationEventSource() {
        return operationEventSource;
    }

    /**
     * Sets Operation Event Source.
     *
     * @param operationEventSource the Operation Event Source
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOperationEventSource(final Integer operationEventSource) {
        this.operationEventSource = operationEventSource;
    }

    /**
     * Gets Operation Event Code.
     *
     * @return the Operation Event Code
     */
    public Integer getOperationEventCode() {
        return operationEventCode;
    }

    /**
     * Sets Operation Event Code.
     *
     * @param operationEventCode the Operation Event Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setOperationEventCode(final Integer operationEventCode) {
        this.operationEventCode = operationEventCode;
    }

    /**
     * Gets User ID.
     *
     * @return the User ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets User ID.
     *
     * @param userId the User ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets PIN.
     *
     * @return the PIN
     */
    public ByteArray getPin() {
        return pin;
    }

    /**
     * Sets PIN.
     *
     * @param pin the PIN
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setPin(final ByteArray pin) {
        this.pin = pin;
    }

    /**
     * Gets Local Time.
     *
     * @return the Local Time
     */
    public Integer getLocalTime() {
        return localTime;
    }

    /**
     * Sets Local Time.
     *
     * @param localTime the Local Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setLocalTime(final Integer localTime) {
        this.localTime = localTime;
    }

    /**
     * Gets Data.
     *
     * @return the Data
     */
    public String getData() {
        return data;
    }

    /**
     * Sets Data.
     *
     * @param data the Data
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setData(final String data) {
        this.data = data;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(operationEventSource, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(operationEventCode, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(userId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(pin, ZclDataType.OCTET_STRING);
        serializer.serialize(localTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(data, ZclDataType.CHARACTER_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        operationEventSource = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        operationEventCode = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        userId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        pin = deserializer.deserialize(ZclDataType.OCTET_STRING);
        localTime = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        data = deserializer.deserialize(ZclDataType.CHARACTER_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(209);
        builder.append("OperationEventNotification [");
        builder.append(super.toString());
        builder.append(", operationEventSource=");
        builder.append(operationEventSource);
        builder.append(", operationEventCode=");
        builder.append(operationEventCode);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", pin=");
        builder.append(pin);
        builder.append(", localTime=");
        builder.append(localTime);
        builder.append(", data=");
        builder.append(data);
        builder.append(']');
        return builder.toString();
    }

}
