/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.scenes;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Recall Scene Command value object class.
 * <p>
 * Cluster: <b>Scenes</b>. Command ID 0x05 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Scenes cluster.
 * <p>
 * The Recall Scene command may be addressed to a single device or to a group.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-02-12T14:13:22Z")
public class RecallSceneCommand extends ZclScenesCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0005;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x05;

    /**
     * Group ID command message field.
     */
    private Integer groupId;

    /**
     * Scene ID command message field.
     */
    private Integer sceneId;

    /**
     * Transition Time command message field.
     * <p>
     * If the Transition Time field is present in the command payload and its value is not equal
     * to 0xFFFF, this field shall indicate the transition time in 1/10ths of a second. In all
     * other cases (command payload field not present or value equal to 0xFFFF), The scene
     * transition time field of the Scene Table entry shall indicate the transition time. The
     * transition time determines how long the transition takes from the old cluster state to
     * the new cluster state.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public RecallSceneCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param groupId {@link Integer} Group ID
     * @param sceneId {@link Integer} Scene ID
     * @param transitionTime {@link Integer} Transition Time
     */
    public RecallSceneCommand(
            Integer groupId,
            Integer sceneId,
            Integer transitionTime) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.groupId = groupId;
        this.sceneId = sceneId;
        this.transitionTime = transitionTime;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupId(final Integer groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets Scene ID.
     *
     * @return the Scene ID
     */
    public Integer getSceneId() {
        return sceneId;
    }

    /**
     * Sets Scene ID.
     *
     * @param sceneId the Scene ID
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSceneId(final Integer sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * Gets Transition Time.
     * <p>
     * If the Transition Time field is present in the command payload and its value is not equal
     * to 0xFFFF, this field shall indicate the transition time in 1/10ths of a second. In all
     * other cases (command payload field not present or value equal to 0xFFFF), The scene
     * transition time field of the Scene Table entry shall indicate the transition time. The
     * transition time determines how long the transition takes from the old cluster state to
     * the new cluster state.
     *
     * @return the Transition Time
     */
    public Integer getTransitionTime() {
        return transitionTime;
    }

    /**
     * Sets Transition Time.
     * <p>
     * If the Transition Time field is present in the command payload and its value is not equal
     * to 0xFFFF, this field shall indicate the transition time in 1/10ths of a second. In all
     * other cases (command payload field not present or value equal to 0xFFFF), The scene
     * transition time field of the Scene Table entry shall indicate the transition time. The
     * transition time determines how long the transition takes from the old cluster state to
     * the new cluster state.
     *
     * @param transitionTime the Transition Time
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTransitionTime(final Integer transitionTime) {
        this.transitionTime = transitionTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(groupId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        groupId = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneId = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        transitionTime = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(109);
        builder.append("RecallSceneCommand [");
        builder.append(super.toString());
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(", sceneId=");
        builder.append(sceneId);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(']');
        return builder.toString();
    }

}
