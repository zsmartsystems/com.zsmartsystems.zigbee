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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class StepColorTemperatureCommand extends ZclColorControlCommand {
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
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public StepColorTemperatureCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param stepMode {@link Integer} Step Mode
     * @param stepSize {@link Integer} Step Size
     * @param transitionTime {@link Integer} Transition Time
     * @param colorTemperatureMinimum {@link Integer} Color Temperature Minimum
     * @param colorTemperatureMaximum {@link Integer} Color Temperature Maximum
     */
    public StepColorTemperatureCommand(
            Integer stepMode,
            Integer stepSize,
            Integer transitionTime,
            Integer colorTemperatureMinimum,
            Integer colorTemperatureMaximum) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.stepMode = stepMode;
        this.stepSize = stepSize;
        this.transitionTime = transitionTime;
        this.colorTemperatureMinimum = colorTemperatureMinimum;
        this.colorTemperatureMaximum = colorTemperatureMaximum;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        stepMode = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        stepSize = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        transitionTime = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        colorTemperatureMinimum = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        colorTemperatureMaximum = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
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
