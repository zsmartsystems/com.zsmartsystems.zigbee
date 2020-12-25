/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.groups;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Group Membership Command value object class.
 * <p>
 * Cluster: <b>Groups</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * <p>
 * The get group membership command allows the sending device to inquire about the group
 * membership of the receiving device and endpoint in a number of ways.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetGroupMembershipCommand extends ZclGroupsCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0004;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Group Count command message field.
     */
    private Integer groupCount;

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
    public GetGroupMembershipCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param groupCount {@link Integer} Group Count
     * @param groupList {@link List<Integer>} Group List
     */
    public GetGroupMembershipCommand(
            Integer groupCount,
            List<Integer> groupList) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.groupCount = groupCount;
        this.groupList = groupList;
    }

    /**
     * Gets Group Count.
     *
     * @return the Group Count
     */
    public Integer getGroupCount() {
        return groupCount;
    }

    /**
     * Sets Group Count.
     *
     * @param groupCount the Group Count
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupCount(final Integer groupCount) {
        this.groupCount = groupCount;
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
        serializer.serialize(groupCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupList, ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        groupCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupList = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(87);
        builder.append("GetGroupMembershipCommand [");
        builder.append(super.toString());
        builder.append(", groupCount=");
        builder.append(groupCount);
        builder.append(", groupList=");
        builder.append(groupList);
        builder.append(']');
        return builder.toString();
    }

}
