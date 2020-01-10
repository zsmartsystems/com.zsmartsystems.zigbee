/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-10T12:07:00Z")
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
     */
    public CopySceneCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
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
     */
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
     */
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
     */
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
     */
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
        mode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        groupIdFrom = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneIdFrom = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        groupIdTo = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        sceneIdTo = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
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
