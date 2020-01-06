/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.groups;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Add Group Response value object class.
 * <p>
 * Cluster: <b>Groups</b>. Command ID 0x00 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * <p>
 * The add group response is sent by the groups cluster server in response to an add group
 * command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class AddGroupResponse extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0004;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Group ID command message field.
     */
    private Integer groupId;

    /**
     * Default constructor.
     */
    public AddGroupResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Status.
     *
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     *
     * @param status the Status
     * @return the AddGroupResponse command
     */
    public AddGroupResponse setStatus(final Integer status) {
        this.status = status;
        return this;
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
     * @return the AddGroupResponse command
     */
    public AddGroupResponse setGroupId(final Integer groupId) {
        this.groupId = groupId;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(groupId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        groupId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(72);
        builder.append("AddGroupResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(']');
        return builder.toString();
    }

}
