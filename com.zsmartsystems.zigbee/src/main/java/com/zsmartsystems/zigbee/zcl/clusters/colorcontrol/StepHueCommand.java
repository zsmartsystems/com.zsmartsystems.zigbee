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
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Step Hue Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-13T17:16:42Z")
public class StepHueCommand extends ZclCommand {
    /**
     * Step mode command message field.
     */
    private Integer stepMode;

    /**
     * Step size command message field.
     */
    private Integer stepSize;

    /**
     * Transition time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public StepHueCommand() {
        genericCommand = false;
        clusterId = 768;
        commandId = 2;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Step mode.
     *
     * @return the Step mode
     */
    public Integer getStepMode() {
        return stepMode;
    }

    /**
     * Sets Step mode.
     *
     * @param stepMode the Step mode
     */
    public void setStepMode(final Integer stepMode) {
        this.stepMode = stepMode;
    }

    /**
     * Gets Step size.
     *
     * @return the Step size
     */
    public Integer getStepSize() {
        return stepSize;
    }

    /**
     * Sets Step size.
     *
     * @param stepSize the Step size
     */
    public void setStepSize(final Integer stepSize) {
        this.stepSize = stepSize;
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
        final StringBuilder builder = new StringBuilder(107);
        builder.append("StepHueCommand [");
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
