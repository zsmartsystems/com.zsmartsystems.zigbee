/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Enhanced Move To Hue and Saturation Command value object class.
 * <p>
 * Cluster: <b>Color control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color control cluster.
 * <p>
 * This cluster provides an interface for changing the color of a light. Color is
 * specified according to the Commission Internationale de l'Éclairage (CIE)
 * specification CIE 1931 Color Space, [B4]. Color control is carried out in terms of
 * x,y values, as defined by this specification.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class EnhancedMoveToHueAndSaturationCommand extends ZclCommand {
    /**
     * Hue command message field.
     */
    private Integer hue;

    /**
     * Saturation command message field.
     */
    private Integer saturation;

    /**
     * Transition time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public EnhancedMoveToHueAndSaturationCommand() {
        genericCommand = false;
        clusterId = 768;
        commandId = 66;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Hue.
     *
     * @return the Hue
     */
    public Integer getHue() {
        return hue;
    }

    /**
     * Sets Hue.
     *
     * @param hue the Hue
     */
    public void setHue(final Integer hue) {
        this.hue = hue;
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
     */
    public void setSaturation(final Integer saturation) {
        this.saturation = saturation;
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

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(hue, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(saturation, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        hue = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        saturation = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(127);
        builder.append("EnhancedMoveToHueAndSaturationCommand [");
        builder.append(super.toString());
        builder.append(", hue=");
        builder.append(hue);
        builder.append(", saturation=");
        builder.append(saturation);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(']');
        return builder.toString();
    }

}
