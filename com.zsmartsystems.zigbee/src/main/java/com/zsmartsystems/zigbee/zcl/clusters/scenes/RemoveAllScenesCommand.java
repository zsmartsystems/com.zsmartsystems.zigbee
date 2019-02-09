/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.scenes;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Remove All Scenes Command value object class.
 * <p>
 * Cluster: <b>Scenes</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Scenes cluster.
 * <p>
 * The Remove All Scenes may be addressed to a single device or to a group.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RemoveAllScenesCommand extends ZclCommand {
    /**
     * Group ID command message field.
     */
    private Integer groupId;

    /**
     * Default constructor.
     */
    public RemoveAllScenesCommand() {
        genericCommand = false;
        clusterId = 0x0005;
        commandId = 3;
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
        final StringBuilder builder = new StringBuilder(52);
        builder.append("RemoveAllScenesCommand [");
        builder.append(super.toString());
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(']');
        return builder.toString();
    }

}
