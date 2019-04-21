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
 * Step Color Temperature Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x4C is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * The Step Color Temperature command allows the color temperature of a lamp to be stepped with a
 * specified step size.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class StepColorTemperatureCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x4C;

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
     * Color Temperature Minimum command message field.
     */
    private Integer colorTemperatureMinimum;

    /**
     * Color Temperature Maximum command message field.
     */
    private Integer colorTemperatureMaximum;

    /**
     * Default constructor.
     */
    public StepColorTemperatureCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
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

    /**
     * Gets Color Temperature Minimum.
     *
     * @return the Color Temperature Minimum
     */
    public Integer getColorTemperatureMinimum() {
        return colorTemperatureMinimum;
    }

    /**
     * Sets Color Temperature Minimum.
     *
     * @param colorTemperatureMinimum the Color Temperature Minimum
     */
    public void setColorTemperatureMinimum(final Integer colorTemperatureMinimum) {
        this.colorTemperatureMinimum = colorTemperatureMinimum;
    }

    /**
     * Gets Color Temperature Maximum.
     *
     * @return the Color Temperature Maximum
     */
    public Integer getColorTemperatureMaximum() {
        return colorTemperatureMaximum;
    }

    /**
     * Sets Color Temperature Maximum.
     *
     * @param colorTemperatureMaximum the Color Temperature Maximum
     */
    public void setColorTemperatureMaximum(final Integer colorTemperatureMaximum) {
        this.colorTemperatureMaximum = colorTemperatureMaximum;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(stepMode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(stepSize, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(colorTemperatureMinimum, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(colorTemperatureMaximum, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        stepMode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        stepSize = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        colorTemperatureMinimum = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        colorTemperatureMaximum = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(206);
        builder.append("StepColorTemperatureCommand [");
        builder.append(super.toString());
        builder.append(", stepMode=");
        builder.append(stepMode);
        builder.append(", stepSize=");
        builder.append(stepSize);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(", colorTemperatureMinimum=");
        builder.append(colorTemperatureMinimum);
        builder.append(", colorTemperatureMaximum=");
        builder.append(colorTemperatureMaximum);
        builder.append(']');
        return builder.toString();
    }

}
