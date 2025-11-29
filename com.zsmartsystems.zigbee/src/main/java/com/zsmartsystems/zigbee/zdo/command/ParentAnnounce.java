/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoRequest;
import com.zsmartsystems.zigbee.zdo.field.ParentAnnounceChildInfo;

/**
 * Parent Announce value object class.
 * <p>
 * <p>
 * The Parent_annce is provided to enable ZigBee routers (including the coordinator) on the
 * network to notify other ZigBee routers about all the end devices known to the local device.
 * This command provides a means to resolve conflicts more quickly than aging out the child,
 * when multiple routers purport to be the active parent of a particular end-device. The
 * command may be broadcast from one router to all routers and the coordinator using the
 * broadcast address 0xFFFC or unicast from one router to another router.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-07-28T09:52:40Z")
public class ParentAnnounce extends ZdoRequest {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x001F;

    /**
     * Child Info List command message field.
     */
    private List<ParentAnnounceChildInfo> childInfoList;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ParentAnnounce() {
        clusterId = CLUSTER_ID;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param childInfoList {@link List<ParentAnnounceChildInfo>} Child Info List
     */
    public ParentAnnounce(
            List<ParentAnnounceChildInfo> childInfoList) {

        clusterId = CLUSTER_ID;

        this.childInfoList = childInfoList;
    }

    /**
     * Gets Child Info List.
     *
     * @return the Child Info List
     */
    public List<ParentAnnounceChildInfo> getChildInfoList() {
        return childInfoList;
    }

    /**
     * Sets Child Info List.
     *
     * @param childInfoList the Child Info List
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setChildInfoList(final List<ParentAnnounceChildInfo> childInfoList) {
        this.childInfoList = childInfoList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(childInfoList.size(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int cnt = 0; cnt < childInfoList.size(); cnt++) {
            serializer.serialize(childInfoList.get(cnt), ZclDataType.PARENT_ANNOUNCE_CHILD_INFO);
        }
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        // Create lists
        childInfoList = new ArrayList<ParentAnnounceChildInfo>();

        Integer numberOfChildren = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (numberOfChildren != null) {
            for (int cnt = 0; cnt < numberOfChildren; cnt++) {
                childInfoList.add(deserializer.deserialize(ZclDataType.PARENT_ANNOUNCE_CHILD_INFO));
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(86);
        builder.append("ParentAnnounce [");
        builder.append(super.toString());
        builder.append(", childInfoList=");
        builder.append(childInfoList);
        builder.append(']');
        return builder.toString();
    }

}
