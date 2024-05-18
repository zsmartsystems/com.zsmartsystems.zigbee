/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
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
 * Programming Event Notification value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x21 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * The door lock server sends out a programming event notification whenever a programming
 * event takes place on the door lock.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:41:29Z")
public class ProgrammingEventNotification extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x21;

    /**
     * Program Event Source command message field.
     */
    private Integer programEventSource;

    /**
     * Program Event Code command message field.
     */
    private Integer programEventCode;

    /**
     * User ID command message field.
     */
    private Integer userId;

    /**
     * PIN command message field.
     */
    private ByteArray pin;

    /**
     * User Type command message field.
     */
    private Integer userType;

    /**
     * User Status command message field.
     */
    private Integer userStatus;

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
    public ProgrammingEventNotification() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param programEventSource {@link Integer} Program Event Source
     * @param programEventCode {@link Integer} Program Event Code
     * @param userId {@link Integer} User ID
     * @param pin {@link ByteArray} PIN
     * @param userType {@link Integer} User Type
     * @param userStatus {@link Integer} User Status
     * @param localTime {@link Integer} Local Time
     * @param data {@link String} Data
     */
    public ProgrammingEventNotification(
            Integer programEventSource,
            Integer programEventCode,
            Integer userId,
            ByteArray pin,
            Integer userType,
            Integer userStatus,
            Integer localTime,
            String data) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.programEventSource = programEventSource;
        this.programEventCode = programEventCode;
        this.userId = userId;
        this.pin = pin;
        this.userType = userType;
        this.userStatus = userStatus;
        this.localTime = localTime;
        this.data = data;
    }

    /**
     * Gets Program Event Source.
     *
     * @return the Program Event Source
     */
    public Integer getProgramEventSource() {
        return programEventSource;
    }

    /**
     * Sets Program Event Source.
     *
     * @param programEventSource the Program Event Source
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setProgramEventSource(final Integer programEventSource) {
        this.programEventSource = programEventSource;
    }

    /**
     * Gets Program Event Code.
     *
     * @return the Program Event Code
     */
    public Integer getProgramEventCode() {
        return programEventCode;
    }

    /**
     * Sets Program Event Code.
     *
     * @param programEventCode the Program Event Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setProgramEventCode(final Integer programEventCode) {
        this.programEventCode = programEventCode;
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
     * Gets User Type.
     *
     * @return the User Type
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * Sets User Type.
     *
     * @param userType the User Type
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setUserType(final Integer userType) {
        this.userType = userType;
    }

    /**
     * Gets User Status.
     *
     * @return the User Status
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * Sets User Status.
     *
     * @param userStatus the User Status
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setUserStatus(final Integer userStatus) {
        this.userStatus = userStatus;
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
        serializer.serialize(programEventSource, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(programEventCode, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(userId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(pin, ZclDataType.OCTET_STRING);
        serializer.serialize(userType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(userStatus, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(localTime, ZclDataType.UNSIGNED_32_BIT_INTEGER);
        serializer.serialize(data, ZclDataType.CHARACTER_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        programEventSource = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        programEventCode = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        userId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        pin = deserializer.deserialize(ZclDataType.OCTET_STRING);
        userType = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        userStatus = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        localTime = deserializer.deserialize(ZclDataType.UNSIGNED_32_BIT_INTEGER);
        data = deserializer.deserialize(ZclDataType.CHARACTER_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(265);
        builder.append("ProgrammingEventNotification [");
        builder.append(super.toString());
        builder.append(", programEventSource=");
        builder.append(programEventSource);
        builder.append(", programEventCode=");
        builder.append(programEventCode);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", pin=");
        builder.append(pin);
        builder.append(", userType=");
        builder.append(userType);
        builder.append(", userStatus=");
        builder.append(userStatus);
        builder.append(", localTime=");
        builder.append(localTime);
        builder.append(", data=");
        builder.append(data);
        builder.append(']');
        return builder.toString();
    }

}
