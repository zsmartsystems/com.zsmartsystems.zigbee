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
 * Copy Scene Command value object class.
 * <p>
 * Cluster: <b>Scenes</b>. Command ID 0x42 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Scenes cluster.
 * <p>
 * The Copy Scene command allows a device to efficiently copy scenes from one group/scene
 * identifier pair to another group/scene identifier pair.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class CopySceneCommand extends ZclScenesCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0005;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x42;

    /**
     * Mode command message field.
     */
    private Integer mode;

    /**
     * Group ID From command message field.
     */
    private Integer groupIdFrom;

    /**
     * Scene ID From command message field.
     */
    private Integer sceneIdFrom;

    /**
     * Group ID To command message field.
     */
    private Integer groupIdTo;

    /**
     * Scene ID To command message field.
     */
    private Integer sceneIdTo;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public CopySceneCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param mode {@link Integer} Mode
     * @param groupIdFrom {@link Integer} Group ID From
     * @param sceneIdFrom {@link Integer} Scene ID From
     * @param groupIdTo {@link Integer} Group ID To
     * @param sceneIdTo {@link Integer} Scene ID To
     */
    public CopySceneCommand(
            Integer mode,
            Integer groupIdFrom,
            Integer sceneIdFrom,
            Integer groupIdTo,
            Integer sceneIdTo) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.mode = mode;
        this.groupIdFrom = groupIdFrom;
        this.sceneIdFrom = sceneIdFrom;
        this.groupIdTo = groupIdTo;
        this.sceneIdTo = sceneIdTo;
    }

    /**
     * Gets Mode.
     *
     * @return the Mode
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * Sets Mode.
     *
     * @param mode the Mode
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMode(final Integer mode) {
        this.mode = mode;
    }

    /**
     * Gets Group ID From.
     *
     * @return the Group ID From
     */
    public Integer getGroupIdFrom() {
        return groupIdFrom;
    }

    /**
     * Sets Group ID From.
     *
     * @param groupIdFrom the Group ID From
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupIdFrom(final Integer groupIdFrom) {
        this.groupIdFrom = groupIdFrom;
    }

    /**
     * Gets Scene ID From.
     *
     * @return the Scene ID From
     */
    public Integer getSceneIdFrom() {
        return sceneIdFrom;
    }

    /**
     * Sets Scene ID From.
     *
     * @param sceneIdFrom the Scene ID From
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSceneIdFrom(final Integer sceneIdFrom) {
        this.sceneIdFrom = sceneIdFrom;
    }

    /**
     * Gets Group ID To.
     *
     * @return the Group ID To
     */
    public Integer getGroupIdTo() {
        return groupIdTo;
    }

    /**
     * Sets Group ID To.
     *
     * @param groupIdTo the Group ID To
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setGroupIdTo(final Integer groupIdTo) {
        this.groupIdTo = groupIdTo;
    }

    /**
     * Gets Scene ID To.
     *
     * @return the Scene ID To
     */
    public Integer getSceneIdTo() {
        return sceneIdTo;
    }

    /**
     * Sets Scene ID To.
     *
     * @param sceneIdTo the Scene ID To
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSceneIdTo(final Integer sceneIdTo) {
        this.sceneIdTo = sceneIdTo;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(mode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(groupIdFrom, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneIdFrom, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(groupIdTo, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(sceneIdTo, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        mode = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        groupIdFrom = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneIdFrom = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupIdTo = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneIdTo = deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(163);
        builder.append("CopySceneCommand [");
        builder.append(super.toString());
        builder.append(", mode=");
        builder.append(mode);
        builder.append(", groupIdFrom=");
        builder.append(groupIdFrom);
        builder.append(", sceneIdFrom=");
        builder.append(sceneIdFrom);
        builder.append(", groupIdTo=");
        builder.append(groupIdTo);
        builder.append(", sceneIdTo=");
        builder.append(sceneIdTo);
        builder.append(']');
        return builder.toString();
    }

}
