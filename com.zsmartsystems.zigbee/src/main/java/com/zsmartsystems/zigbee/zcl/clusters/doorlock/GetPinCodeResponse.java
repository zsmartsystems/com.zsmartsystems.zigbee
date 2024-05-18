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
 * Get PIN Code Response value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x06 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Returns the PIN for the specified user ID.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public class GetPinCodeResponse extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x06;

    /**
     * User ID command message field.
     */
    private Integer userId;

    /**
     * User Status command message field.
     */
    private Integer userStatus;

    /**
     * User Type command message field.
     */
    private Integer userType;

    /**
     * Code command message field.
     */
    private ByteArray code;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetPinCodeResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param userId {@link Integer} User ID
     * @param userStatus {@link Integer} User Status
     * @param userType {@link Integer} User Type
     * @param code {@link ByteArray} Code
     */
    public GetPinCodeResponse(
            Integer userId,
            Integer userStatus,
            Integer userType,
            ByteArray code) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.userId = userId;
        this.userStatus = userStatus;
        this.userType = userType;
        this.code = code;
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
     * Gets Code.
     *
     * @return the Code
     */
    public ByteArray getCode() {
        return code;
    }

    /**
     * Sets Code.
     *
     * @param code the Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCode(final ByteArray code) {
        this.code = code;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(userId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(userStatus, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(userType, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(code, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        userId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        userStatus = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        userType = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        code = deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(129);
        builder.append("GetPinCodeResponse [");
        builder.append(super.toString());
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", userStatus=");
        builder.append(userStatus);
        builder.append(", userType=");
        builder.append(userType);
        builder.append(", code=");
        builder.append(code);
        builder.append(']');
        return builder.toString();
    }

}
