/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Color Loop Set Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x44 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * The Color Loop Set command allows a color loop to be activated such that the color lamp cycles
 * through its range of hues.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class ColorLoopSetCommand extends ZclColorControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x44;

    /**
     * Update Flags command message field.
     */
    private Integer updateFlags;

    /**
     * Action command message field.
     */
    private Integer action;

    /**
     * Direction command message field.
     */
    private Integer direction;

    /**
     * Transition Time command message field.
     */
    private Integer transitionTime;

    /**
     * Start Hue command message field.
     */
    private Integer startHue;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public ColorLoopSetCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param updateFlags {@link Integer} Update Flags
     * @param action {@link Integer} Action
     * @param direction {@link Integer} Direction
     * @param transitionTime {@link Integer} Transition Time
     * @param startHue {@link Integer} Start Hue
     */
    public ColorLoopSetCommand(
            Integer updateFlags,
            Integer action,
            Integer direction,
            Integer transitionTime,
            Integer startHue) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.updateFlags = updateFlags;
        this.action = action;
        this.direction = direction;
        this.transitionTime = transitionTime;
        this.startHue = startHue;
    }

    /**
     * Gets Update Flags.
     *
     * @return the Update Flags
     */
    public Integer getUpdateFlags() {
        return updateFlags;
    }

    /**
     * Sets Update Flags.
     *
     * @param updateFlags the Update Flags
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setUpdateFlags(final Integer updateFlags) {
        this.updateFlags = updateFlags;
    }

    /**
     * Gets Action.
     *
     * @return the Action
     */
    public Integer getAction() {
        return action;
    }

    /**
     * Sets Action.
     *
     * @param action the Action
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setAction(final Integer action) {
        this.action = action;
    }

    /**
     * Gets Direction.
     *
     * @return the Direction
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * Sets Direction.
     *
     * @param direction the Direction
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setDirection(final Integer direction) {
        this.direction = direction;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setTransitionTime(final Integer transitionTime) {
        this.transitionTime = transitionTime;
    }

    /**
     * Gets Start Hue.
     *
     * @return the Start Hue
     */
    public Integer getStartHue() {
        return startHue;
    }

    /**
     * Sets Start Hue.
     *
     * @param startHue the Start Hue
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setStartHue(final Integer startHue) {
        this.startHue = startHue;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(updateFlags, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(action, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(direction, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(startHue, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        updateFlags = deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        action = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        direction = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        transitionTime = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        startHue = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(170);
        builder.append("ColorLoopSetCommand [");
        builder.append(super.toString());
        builder.append(", updateFlags=");
        builder.append(updateFlags);
        builder.append(", action=");
        builder.append(action);
        builder.append(", direction=");
        builder.append(direction);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(", startHue=");
        builder.append(startHue);
        builder.append(']');
        return builder.toString();
    }

}
