/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
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
 * Enhanced Move To Hue And Saturation Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x43 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * The Enhanced Move to Hue and Saturation command allows lamps to be moved in a smooth
 * continuous transition from their current hue to a target hue and from their current
 * saturation to a target saturation.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class EnhancedMoveToHueAndSaturationCommand extends ZclColorControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x43;

    /**
     * Enhanced Hue command message field.
     */
    private Integer enhancedHue;

    /**
     * Saturation command message field.
     */
    private Integer saturation;

    /**
     * Transition Time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public EnhancedMoveToHueAndSaturationCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param enhancedHue {@link Integer} Enhanced Hue
     * @param saturation {@link Integer} Saturation
     * @param transitionTime {@link Integer} Transition Time
     */
    public EnhancedMoveToHueAndSaturationCommand(
            Integer enhancedHue,
            Integer saturation,
            Integer transitionTime) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.enhancedHue = enhancedHue;
        this.saturation = saturation;
        this.transitionTime = transitionTime;
    }

    /**
     * Gets Enhanced Hue.
     *
     * @return the Enhanced Hue
     */
    public Integer getEnhancedHue() {
        return enhancedHue;
    }

    /**
     * Sets Enhanced Hue.
     *
     * @param enhancedHue the Enhanced Hue
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setEnhancedHue(final Integer enhancedHue) {
        this.enhancedHue = enhancedHue;
    }

    /**
     * Gets Saturation.
     *
     * @return the Saturation
     */
    public Integer getSaturation() {
        return saturation;
    }

    /**
     * Sets Saturation.
     *
     * @param saturation the Saturation
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setSaturation(final Integer saturation) {
        this.saturation = saturation;
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

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(enhancedHue, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(saturation, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        enhancedHue = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        saturation = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        transitionTime = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(135);
        builder.append("EnhancedMoveToHueAndSaturationCommand [");
        builder.append(super.toString());
        builder.append(", enhancedHue=");
        builder.append(enhancedHue);
        builder.append(", saturation=");
        builder.append(saturation);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(']');
        return builder.toString();
    }

}
