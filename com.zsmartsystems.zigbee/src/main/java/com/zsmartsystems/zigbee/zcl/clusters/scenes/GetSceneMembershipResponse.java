package com.zsmartsystems.zigbee.zcl.clusters.scenes;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.Unsigned8BitInteger;

/**
 * Get Scene Membership Response value object class.
 * <p>
 * Cluster: <b>Scenes</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Scenes cluster.
 * <p>
 * The scenes cluster provides attributes and commands for setting up and recalling
 * scenes. Each scene corresponds to a set of stored values of specified attributes for
 * one or more clusters on the same end point as the scenes cluster.
 * <p>
 * In most cases scenes are associated with a particular group ID. Scenes may also
 * exist without a group, in which case the value 0x0000 replaces the group ID. Note
 * that extra care is required in these cases to avoid a scene ID collision, and that
 * commands related to scenes without a group may only be unicast, i.e.: they may
 * not be multicast or broadcast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
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
     * Scene count command message field.
     */
    private Integer sceneCount;

    /**
     * Scene list command message field.
     */
    private List<Unsigned8BitInteger> sceneList;

    /**
     * Default constructor.
     */
    public GetSceneMembershipResponse() {
        genericCommand = false;
        clusterId = 5;
        commandId = 5;
        commandDirection = false;
    }

    /**
     * Gets Status.
     * @return the Status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Sets Status.
     * @param status the Status
     */
    public void setStatus(final Integer status) {
        this.status = status;
    }

    /**
     * Gets Capacity.
     * @return the Capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets Capacity.
     * @param capacity the Capacity
     */
    public void setCapacity(final Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets Group ID.
     * @return the Group ID
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * Sets Group ID.
     * @param groupId the Group ID
     */
    public void setGroupId(final Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets Scene count.
     * @return the Scene count
     */
    public Integer getSceneCount() {
        return sceneCount;
    }

    /**
     * Sets Scene count.
     * @param sceneCount the Scene count
     */
    public void setSceneCount(final Integer sceneCount) {
        this.sceneCount = sceneCount;
    }

    /**
     * Gets Scene list.
     * @return the Scene list
     */
    public List<Unsigned8BitInteger> getSceneList() {
        return sceneList;
    }

    /**
     * Sets Scene list.
     * @param sceneList the Scene list
     */
    public void setSceneList(final List<Unsigned8BitInteger> sceneList) {
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
        sceneList = (List<Unsigned8BitInteger>) deserializer.deserialize(ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
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
        return builder.toString();
    }

}
