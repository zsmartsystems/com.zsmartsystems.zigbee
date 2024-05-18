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
 * Set RFID Code value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x16 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Set an ID for RFID access into the lock.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public class SetRfidCode extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x16;

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
     * RFID Code command message field.
     */
    private ByteArray rfidCode;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetRfidCode() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param userId {@link Integer} User ID
     * @param userStatus {@link Integer} User Status
     * @param userType {@link Integer} User Type
     * @param rfidCode {@link ByteArray} RFID Code
     */
    public SetRfidCode(
            Integer userId,
            Integer userStatus,
            Integer userType,
            ByteArray rfidCode) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.userId = userId;
        this.userStatus = userStatus;
        this.userType = userType;
        this.rfidCode = rfidCode;
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
     * Gets RFID Code.
     *
     * @return the RFID Code
     */
    public ByteArray getRfidCode() {
        return rfidCode;
    }

    /**
     * Sets RFID Code.
     *
     * @param rfidCode the RFID Code
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRfidCode(final ByteArray rfidCode) {
        this.rfidCode = rfidCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(userId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(userStatus, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(userType, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(rfidCode, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        userId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        userStatus = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        userType = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        rfidCode = deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(126);
        builder.append("SetRfidCode [");
        builder.append(super.toString());
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", userStatus=");
        builder.append(userStatus);
        builder.append(", userType=");
        builder.append(userType);
        builder.append(", rfidCode=");
        builder.append(rfidCode);
        builder.append(']');
        return builder.toString();
    }

}
