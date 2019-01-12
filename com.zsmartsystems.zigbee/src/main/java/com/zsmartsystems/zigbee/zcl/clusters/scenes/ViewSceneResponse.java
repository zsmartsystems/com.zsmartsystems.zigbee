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
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

import java.util.List;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;

/**
 * View Scene Response value object class.
 * <p>
 * Cluster: <b>Scenes</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Scenes cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-13T17:16:42Z")
public class ViewSceneResponse extends ZclCommand {
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
     * Transition time command message field.
     */
    private Integer transitionTime;

    /**
     * Scene Name command message field.
     */
    private String sceneName;

    /**
     * Extension field sets command message field.
     */
    private List<ExtensionFieldSet> extensionFieldSets;

    /**
     * Default constructor.
     */
    public ViewSceneResponse() {
        genericCommand = false;
        clusterId = 5;
        commandId = 1;
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
     * Gets Transition time.
     *
     * @return the Transition time
     */
    public Integer getTransitionTime() {
        return transitionTime;
    }

    /**
     * Sets Transition time.
     *
     * @param transitionTime the Transition time
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
     * Gets Extension field sets.
     *
     * @return the Extension field sets
     */
    public List<ExtensionFieldSet> getExtensionFieldSets() {
        return extensionFieldSets;
    }

    /**
     * Sets Extension field sets.
     *
     * @param extensionFieldSets the Extension field sets
     */
    public void setExtensionFieldSets(final List<ExtensionFieldSet> extensionFieldSets) {
        this.extensionFieldSets = extensionFieldSets;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(status, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(groupId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneId, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneName, ZclDataType.CHARACTER_STRING);
        serializer.serialize(extensionFieldSets, ZclDataType.N_X_EXTENSION_FIELD_SET);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        status = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        groupId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneId = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneName = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
        extensionFieldSets = (List<ExtensionFieldSet>) deserializer.deserialize(ZclDataType.N_X_EXTENSION_FIELD_SET);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(201);
        builder.append("ViewSceneResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
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
