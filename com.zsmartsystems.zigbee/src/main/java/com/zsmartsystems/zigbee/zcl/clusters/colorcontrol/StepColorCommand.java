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
 * Step Color Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-04-13T17:16:42Z")
public class StepColorCommand extends ZclCommand {
    /**
     * StepX command message field.
     */
    private Integer stepX;

    /**
     * StepY command message field.
     */
    private Integer stepY;

    /**
     * Transition time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public StepColorCommand() {
        genericCommand = false;
        clusterId = 768;
        commandId = 9;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets StepX.
     *
     * @return the StepX
     */
    public Integer getStepX() {
        return stepX;
    }

    /**
     * Sets StepX.
     *
     * @param stepX the StepX
     */
    public void setStepX(final Integer stepX) {
        this.stepX = stepX;
    }

    /**
     * Gets StepY.
     *
     * @return the StepY
     */
    public Integer getStepY() {
        return stepY;
    }

    /**
     * Sets StepY.
     *
     * @param stepY the StepY
     */
    public void setStepY(final Integer stepY) {
        this.stepY = stepY;
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
        serializer.serialize(stepX, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(stepY, ZclDataType.SIGNED_16_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        stepX = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        stepY = (Integer) deserializer.deserialize(ZclDataType.SIGNED_16_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(103);
        builder.append("StepColorCommand [");
        builder.append(super.toString());
        builder.append(", stepX=");
        builder.append(stepX);
        builder.append(", stepY=");
        builder.append(stepY);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(']');
        return builder.toString();
    }

}
