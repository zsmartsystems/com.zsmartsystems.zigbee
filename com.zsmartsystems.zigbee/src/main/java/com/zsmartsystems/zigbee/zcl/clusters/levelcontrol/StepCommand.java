package com.zsmartsystems.zigbee.zcl.clusters.levelcontrol;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Step Command value object class.
 * <p>
 * Cluster: <b>Level Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Level Control cluster.
 * <p>
 * This cluster provides an interface for controlling a characteristic of a device that
 * can be set to a level, for example the brightness of a light, the degree of closure of
 * a door, or the power output of a heater.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class StepCommand extends ZclCommand {
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
    public StepCommand() {
        genericCommand = false;
        clusterId = 8;
        commandId = 2;
        commandDirection = true;
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
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        stepMode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        stepSize = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", stepMode=");
        builder.append(stepMode);
        builder.append(", stepSize=");
        builder.append(stepSize);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        return builder.toString();
    }

}
