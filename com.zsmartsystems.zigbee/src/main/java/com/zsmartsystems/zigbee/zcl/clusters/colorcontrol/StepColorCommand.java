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
 * Step Color Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class StepColorCommand extends ZclCommand {
    /**
     * Step X command message field.
     */
    private Integer stepX;

    /**
     * Step Y command message field.
     */
    private Integer stepY;

    /**
     * Transition Time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public StepColorCommand() {
        genericCommand = false;
        clusterId = 0x0300;
        commandId = 9;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Step X.
     *
     * @return the Step X
     */
    public Integer getStepX() {
        return stepX;
    }

    /**
     * Sets Step X.
     *
     * @param stepX the Step X
     */
    public void setStepX(final Integer stepX) {
        this.stepX = stepX;
    }

    /**
     * Gets Step Y.
     *
     * @return the Step Y
     */
    public Integer getStepY() {
        return stepY;
    }

    /**
     * Sets Step Y.
     *
     * @param stepY the Step Y
     */
    public void setStepY(final Integer stepY) {
        this.stepY = stepY;
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
