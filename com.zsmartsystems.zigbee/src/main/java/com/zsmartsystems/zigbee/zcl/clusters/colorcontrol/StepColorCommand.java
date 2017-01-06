package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Step Color Command value object class.
 * </p>
 * <p>
 * Cluster: <b>Color control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color control cluster.
 * </p>
 * <p>
 * This cluster provides an interface for changing the color of a light. Color is
 * specified according to the Commission Internationale de l'Éclairage (CIE)
 * specification CIE 1931 Color Space, [B4]. Color control is carried out in terms of
 * x,y values, as defined by this specification.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
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
     * Default constructor setting the command type field.
     */
    public StepColorCommand() {
        genericCommand = false;
        clusterId = 768;
        commandId = 9;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public StepColorCommand(final Map<Integer, Object> fields) {
        this();
        stepX = (Integer) fields.get(0);
        stepY = (Integer) fields.get(1);
        transitionTime = (Integer) fields.get(2);
    }

    /**
     * Gets StepX.
     * @return the StepX
     */
    public Integer getStepX() {
        return stepX;
    }

    /**
     * Sets StepX.
     * @param stepX the StepX
     */
    public void setStepX(final Integer stepX) {
        this.stepX = stepX;
    }

    /**
     * Gets StepY.
     * @return the StepY
     */
    public Integer getStepY() {
        return stepY;
    }

    /**
     * Sets StepY.
     * @param stepY the StepY
     */
    public void setStepY(final Integer stepY) {
        this.stepY = stepY;
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
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("stepX = ");
        builder.append(stepX);
        builder.append(", ");
        builder.append("stepY = ");
        builder.append(stepY);
        builder.append(", ");
        builder.append("transitionTime = ");
        builder.append(transitionTime);
        return builder.toString();
    }

}
