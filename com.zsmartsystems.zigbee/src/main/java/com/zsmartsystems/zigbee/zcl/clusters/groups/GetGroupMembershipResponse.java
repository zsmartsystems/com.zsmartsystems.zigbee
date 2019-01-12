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
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

import java.util.List;

/**
 * Get Group Membership Response value object class.
 * <p>
 * Cluster: <b>Groups</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-13T17:16:42Z")
public class GetGroupMembershipResponse extends ZclCommand {
    /**
     * Capacity command message field.
     */
    private Integer capacity;

    /**
     * Group count command message field.
     */
    private Integer groupCount;

    /**
     * Group list command message field.
     */
    private List<Integer> groupList;

    /**
     * Default constructor.
     */
    public GetGroupMembershipResponse() {
        genericCommand = false;
        clusterId = 4;
        commandId = 2;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
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
     */
    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets Group count.
     *
     * @return the Group count
     */
    public Integer getGroupCount() {
        return groupCount;
    }

    /**
     * Sets Group count.
     *
     * @param groupCount the Group count
     */
    public void setGroupCount(final Integer groupCount) {
        this.groupCount = groupCount;
    }

    /**
     * Gets Group list.
     *
     * @return the Group list
     */
    public List<Integer> getGroupList() {
        return groupList;
    }

    /**
     * Sets Group list.
     *
     * @param groupList the Group list
     */
    public void setGroupList(final List<Integer> groupList) {
        this.groupList = groupList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(capacity, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupList, ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        capacity = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupList = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(116);
        builder.append("GetGroupMembershipResponse [");
        builder.append(super.toString());
        builder.append(", capacity=");
        builder.append(capacity);
        builder.append(", groupCount=");
        builder.append(groupCount);
        builder.append(", groupList=");
        builder.append(groupList);
        builder.append(']');
        return builder.toString();
    }

}
