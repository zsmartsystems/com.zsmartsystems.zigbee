/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * View Group Command value object class.
 * <p>
 * Cluster: <b>Groups</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * <p>
 * The view group command allows the sending device to request that the receiving entity or
 * entities respond with a view group response command containing the application name string
 * for a particular group.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class ViewGroupCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0004;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Group ID command message field.
     */
    private Integer groupId;

    /**
     * Default constructor.
     */
    public ViewGroupCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
    public void setGroupId(final Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(groupId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        groupId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(46);
        builder.append("ViewGroupCommand [");
        builder.append(super.toString());
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(']');
        return builder.toString();
    }

}
