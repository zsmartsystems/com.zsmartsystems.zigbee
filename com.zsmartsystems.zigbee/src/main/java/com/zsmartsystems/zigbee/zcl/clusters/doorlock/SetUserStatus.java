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
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set User Status value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x09 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Get the status of a user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public class SetUserStatus extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x09;

    /**
     * User ID command message field.
     */
    private Integer userId;

    /**
     * User Status command message field.
     */
    private Integer userStatus;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetUserStatus() {
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
     */
    public SetUserStatus(
            Integer userId,
            Integer userStatus) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.userId = userId;
        this.userStatus = userStatus;
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

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(userId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(userStatus, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        userId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        userStatus = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(72);
        builder.append("SetUserStatus [");
        builder.append(super.toString());
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", userStatus=");
        builder.append(userStatus);
        builder.append(']');
        return builder.toString();
    }

}
