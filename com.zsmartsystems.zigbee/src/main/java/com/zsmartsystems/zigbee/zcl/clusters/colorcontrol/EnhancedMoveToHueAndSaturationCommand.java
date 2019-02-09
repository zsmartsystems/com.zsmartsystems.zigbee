/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Enhanced Move To Hue And Saturation Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
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
     * Transition Time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public EnhancedMoveToHueAndSaturationCommand() {
        genericCommand = false;
        clusterId = 0x0300;
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
