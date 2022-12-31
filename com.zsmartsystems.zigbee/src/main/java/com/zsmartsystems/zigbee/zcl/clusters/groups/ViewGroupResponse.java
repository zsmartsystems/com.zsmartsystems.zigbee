/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.groups;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * View Group Response value object class.
 * <p>
 * Cluster: <b>Groups</b>. Command ID 0x01 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * <p>
 * The view group response command is sent by the groups cluster server in response to a view
 * group command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-29T21:34:54Z")
public class ViewGroupResponse extends ZclGroupsCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0004;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Status command message field.
     */
    private ZclStatus status;

    /**
     * Group ID command message field.
     */
    private Integer groupId;

    /**
     * Group Name command message field.
     */
    private String groupName;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ViewGroupResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param status {@link ZclStatus} Status
     * @param groupId {@link Integer} Group ID
     * @param groupName {@link String} Group Name
     */
    public ViewGroupResponse(
            ZclStatus status,
            Integer groupId,
            String groupName) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.status = status;
        this.groupId = groupId;
        this.groupName = groupName;
    }

    /**
     * Gets Status.
     *
     * @return the Status
     */
    public ZclStatus getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStatus(final ZclStatus status) {
        this.status = status;
    }

    /**
     * Gets Group ID.
     *
     * @return the Group ID
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Sets Group ID.
     *
     * @param groupId the Group ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupId(final Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets Group Name.
     *
     * @return the Group Name
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets Group Name.
     *
     * @param groupName the Group Name
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupName(final String groupName) {
        this.groupName = groupName;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ZCL_STATUS);
        serializer.serialize(groupId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(groupName, ZclDataType.CHARACTER_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (ZclStatus) deserializer.deserialize(ZclDataType.ZCL_STATUS);
        groupId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        groupName = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(102);
        builder.append("ViewGroupResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(", groupName=");
        builder.append(groupName);
        builder.append(']');
        return builder.toString();
    }

}
