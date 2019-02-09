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
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Add Scene Command value object class.
 * <p>
 * Cluster: <b>Scenes</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Scenes cluster.
 * <p>
 * The Add Scene command shall be addressed to a single device (not a group).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T19:19:25Z")
public class AddSceneCommand extends ZclCommand {
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
     */
    private Integer transitionTime;

    /**
     * Scene Name command message field.
     */
    private String sceneName;

    /**
     * Extension Field Sets command message field.
     */
    private List<ExtensionFieldSet> extensionFieldSets;

    /**
     * Default constructor.
     */
    public AddSceneCommand() {
        genericCommand = false;
        clusterId = 0x0005;
        commandId = 0;
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
     */
    public void setSceneId(final Integer sceneId) {
        this.sceneId = sceneId;
    }

    /**
     * Gets Transition Time.
     *
     * @return the Transition Time
     */
    public Integer getTransitionTime() {
        return transitionTime;
    }

    /**
     * Sets Transition Time.
     *
     * @param transitionTime the Transition Time
     */
    public void setTransitionTime(final Integer transitionTime) {
        this.transitionTime = transitionTime;
    }

    /**
     * Gets Scene Name.
     *
     * @return the Scene Name
     */
    public String getSceneName() {
        return sceneName;
    }

    /**
     * Sets Scene Name.
     *
     * @param sceneName the Scene Name
     */
    public void setSceneName(final String sceneName) {
        this.sceneName = sceneName;
    }

    /**
     * Gets Extension Field Sets.
     *
     * @return the Extension Field Sets
     */
    public List<ExtensionFieldSet> getExtensionFieldSets() {
        return extensionFieldSets;
    }

    /**
     * Sets Extension Field Sets.
     *
     * @param extensionFieldSets the Extension Field Sets
     */
    public void setExtensionFieldSets(final List<ExtensionFieldSet> extensionFieldSets) {
        this.extensionFieldSets = extensionFieldSets;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(groupId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneName, ZclDataType.CHARACTER_STRING);
        serializer.serialize(extensionFieldSets, ZclDataType.N_X_EXTENSION_FIELD_SET);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        groupId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneName = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
        extensionFieldSets = (List<ExtensionFieldSet>) deserializer.deserialize(ZclDataType.N_X_EXTENSION_FIELD_SET);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(173);
        builder.append("AddSceneCommand [");
        builder.append(super.toString());
        builder.append(", groupId=");
        builder.append(groupId);
        builder.append(", sceneId=");
        builder.append(sceneId);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(", sceneName=");
        builder.append(sceneName);
        builder.append(", extensionFieldSets=");
        builder.append(extensionFieldSets);
        builder.append(']');
        return builder.toString();
    }

}
