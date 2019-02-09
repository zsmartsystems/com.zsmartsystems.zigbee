/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.scenes;

import java.util.List;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Scene Membership Response value object class.
 * <p>
 * Cluster: <b>Scenes</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Scenes cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:19:25Z")
public class GetSceneMembershipResponse extends ZclCommand {
    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Capacity command message field.
     */
    private Integer capacity;

    /**
     * Group ID command message field.
     */
    private Integer groupId;

    /**
     * Scene Count command message field.
     */
    private Integer sceneCount;

    /**
     * Scene List command message field.
     */
    private List<Integer> sceneList;

    /**
     * Default constructor.
     */
    public GetSceneMembershipResponse() {
        genericCommand = false;
        clusterId = 0x0005;
        commandId = 5;
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
     */
    public void setStatus(final Integer status) {
        this.status = status;
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

    /**
     * Gets Scene Count.
     *
     * @return the Scene Count
     */
    public Integer getSceneCount() {
        return sceneCount;
    }

    /**
     * Sets Scene Count.
     *
     * @param sceneCount the Scene Count
     */
    public void setSceneCount(final Integer sceneCount) {
        this.sceneCount = sceneCount;
    }

    /**
     * Gets Scene List.
     *
     * @return the Scene List
     */
    public List<Integer> getSceneList() {
        return sceneList;
    }

    /**
     * Sets Scene List.
     *
     * @param sceneList the Scene List
     */
    public void setSceneList(final List<Integer> sceneList) {
        this.sceneList = sceneList;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(capacity, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneCount, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(sceneList, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        capacity = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        sceneList = (List<Integer>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(169);
        builder.append("GetSceneMembershipResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", capacity=");
        builder.append(capacity);
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(", sceneCount=");
        builder.append(sceneCount);
        builder.append(", sceneList=");
        builder.append(sceneList);
        builder.append(']');
        return builder.toString();
    }

}
