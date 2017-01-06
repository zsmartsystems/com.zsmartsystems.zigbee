package com.zsmartsystems.zigbee.zcl.clusters.levelcontrol;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Step (with On/Off) Command value object class.
 * </p>
 * <p>
 * Cluster: <b>Level Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Level Control cluster.
 * </p>
 * <p>
 * This cluster provides an interface for controlling a characteristic of a device that
 * can be set to a level, for example the brightness of a light, the degree of closure of
 * a door, or the power output of a heater.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class StepWithOnOffCommand extends ZclCommand {
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
     * Default constructor setting the command type field.
     */
    public StepWithOnOffCommand() {
        genericCommand = false;
        clusterId = 8;
        commandId = 6;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public StepWithOnOffCommand(final Map<Integer, Object> fields) {
        this();
        stepMode = (Integer) fields.get(0);
        stepSize = (Integer) fields.get(1);
        transitionTime = (Integer) fields.get(2);
    }

    /**
     * Gets Step mode.
     * @return the Step mode
     */
    public Integer getStepMode() {
        return stepMode;
    }

    /**
     * Sets Step mode.
     * @param stepMode the Step mode
     */
    public void setStepMode(final Integer stepMode) {
        this.stepMode = stepMode;
    }

    /**
     * Gets Step size.
     * @return the Step size
     */
    public Integer getStepSize() {
        return stepSize;
    }

    /**
     * Sets Step size.
     * @param stepSize the Step size
     */
    public void setStepSize(final Integer stepSize) {
        this.stepSize = stepSize;
    }

    /**
     * Gets Transition time.
     * @return the Transition time
     */
    public Integer getTransitionTime() {
        return transitionTime;
    }

    /**
     * Sets Transition time.
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
        builder.append(", ");
        builder.append("stepMode = ");
        builder.append(stepMode);
        builder.append(", ");
        builder.append("stepSize = ");
        builder.append(stepSize);
        builder.append(", ");
        builder.append("transitionTime = ");
        builder.append(transitionTime);
        return builder.toString();
    }

}
