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
 * Step Saturation Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class StepSaturationCommand extends ZclCommand {
    /**
     * Step Mode command message field.
     */
    private Integer stepMode;

    /**
     * Step Size command message field.
     */
    private Integer stepSize;

    /**
     * Transition Time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public StepSaturationCommand() {
        genericCommand = false;
        clusterId = 0x0300;
        commandId = 5;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Step Mode.
     *
     * @return the Step Mode
     */
    public Integer getStepMode() {
        return stepMode;
    }

    /**
     * Sets Step Mode.
     *
     * @param stepMode the Step Mode
     */
    public void setStepMode(final Integer stepMode) {
        this.stepMode = stepMode;
    }

    /**
     * Gets Step Size.
     *
     * @return the Step Size
     */
    public Integer getStepSize() {
        return stepSize;
    }

    /**
     * Sets Step Size.
     *
     * @param stepSize the Step Size
     */
    public void setStepSize(final Integer stepSize) {
        this.stepSize = stepSize;
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
        serializer.serialize(stepMode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(stepSize, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        stepMode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        stepSize = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(114);
        builder.append("StepSaturationCommand [");
        builder.append(super.toString());
        builder.append(", stepMode=");
        builder.append(stepMode);
        builder.append(", stepSize=");
        builder.append(stepSize);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(']');
        return builder.toString();
    }

}
