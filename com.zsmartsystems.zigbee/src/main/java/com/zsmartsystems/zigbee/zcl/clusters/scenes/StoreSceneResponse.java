package com.zsmartsystems.zigbee.zcl.clusters.scenes;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Store Scene Response value object class.
 * </p>
 * <p>
 * Cluster: <b>Scenes</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Scenes cluster.
 * </p>
 * <p>
 * The scenes cluster provides attributes and commands for setting up and recalling
 * scenes. Each scene corresponds to a set of stored values of specified attributes for
 * one or more clusters on the same end point as the scenes cluster.
 * <br>
 * In most cases scenes are associated with a particular group ID. Scenes may also
 * exist without a group, in which case the value 0x0000 replaces the group ID. Note
 * that extra care is required in these cases to avoid a scene ID collision, and that
 * commands related to scenes without a group may only be unicast, i.e.: they may
 * not be multicast or broadcast.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class StoreSceneResponse extends ZclCommand {
    /**
     * Status command message field.
     */
    private Integer status;

    /**
     * Group ID command message field.
     */
    private Integer groupId;

    /**
     * Scene ID command message field.
     */
    private Integer sceneId;

    /**
     * Default constructor setting the command type field.
     */
    public StoreSceneResponse() {
        genericCommand = false;
        clusterId = 5;
        commandId = 4;
        commandDirection = false;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public StoreSceneResponse(final Map<Integer, Object> fields) {
        this();
        status = (Integer) fields.get(0);
        groupId = (Integer) fields.get(1);
        sceneId = (Integer) fields.get(2);
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
     * Gets Scene ID.
     * @return the Scene ID
     */
    public Integer getSceneId() {
        return sceneId;
    }

    /**
     * Sets Scene ID.
     * @param sceneId the Scene ID
     */
    public void setSceneId(final Integer sceneId) {
        this.sceneId = sceneId;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(groupId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        groupId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("status = ");
        builder.append(status);
        builder.append(", ");
        builder.append("groupId = ");
        builder.append(groupId);
        builder.append(", ");
        builder.append("sceneId = ");
        builder.append(sceneId);
        return builder.toString();
    }

}
