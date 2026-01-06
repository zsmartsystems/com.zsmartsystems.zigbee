/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.groups;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Group Membership Response value object class.
 * <p>
 * Cluster: <b>Groups</b>. Command ID 0x02 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * <p>
 * The get group membership response command is sent by the groups cluster server in response to
 * a get group membership command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-21T21:27:10Z")
public class GetGroupMembershipResponse extends ZclGroupsCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0004;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Capacity command message field.
     */
    private Integer capacity;

    /**
     * Group List command message field.
     */
    private List<Integer> groupList;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetGroupMembershipResponse() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param capacity {@link Integer} Capacity
     * @param groupList {@link List<Integer>} Group List
     */
    public GetGroupMembershipResponse(
            Integer capacity,
            List<Integer> groupList) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.capacity = capacity;
        this.groupList = groupList;
    }

    /**
     * Gets Capacity.
     *
     * @return the Capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets Capacity.
     *
     * @param capacity the Capacity
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets Group List.
     *
     * @return the Group List
     */
    public List<Integer> getGroupList() {
        return groupList;
    }

    /**
     * Sets Group List.
     *
     * @param groupList the Group List
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupList(final List<Integer> groupList) {
        this.groupList = groupList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(capacity, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupList.size(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < groupList.size(); cnt++) {
            serializer.serialize(groupList.get(cnt), ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        // Create lists
        groupList = new ArrayList<Integer>();

        capacity = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        Integer groupCount = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (groupCount != null) {
            for (int cnt = 0; cnt < groupCount; cnt++) {
                groupList.add(deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(116);
        builder.append("GetGroupMembershipResponse [");
        builder.append(super.toString());
        builder.append(", capacity=");
        builder.append(capacity);
        builder.append(", groupList=");
        builder.append(groupList);
        builder.append(']');
        return builder.toString();
    }

}
