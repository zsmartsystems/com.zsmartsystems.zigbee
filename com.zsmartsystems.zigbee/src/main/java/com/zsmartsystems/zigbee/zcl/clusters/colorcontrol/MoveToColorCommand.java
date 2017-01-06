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
 * Move to Color Command value object class.
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
public class MoveToColorCommand extends ZclCommand {
    /**
     * ColorX command message field.
     */
    private Integer colorX;

    /**
     * ColorY command message field.
     */
    private Integer colorY;

    /**
     * Transition time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor setting the command type field.
     */
    public MoveToColorCommand() {
        genericCommand = false;
        clusterId = 768;
        commandId = 7;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public MoveToColorCommand(final Map<Integer, Object> fields) {
        this();
        colorX = (Integer) fields.get(0);
        colorY = (Integer) fields.get(1);
        transitionTime = (Integer) fields.get(2);
    }

    /**
     * Gets ColorX.
     * @return the ColorX
     */
    public Integer getColorX() {
        return colorX;
    }

    /**
     * Sets ColorX.
     * @param colorX the ColorX
     */
    public void setColorX(final Integer colorX) {
        this.colorX = colorX;
    }

    /**
     * Gets ColorY.
     * @return the ColorY
     */
    public Integer getColorY() {
        return colorY;
    }

    /**
     * Sets ColorY.
     * @param colorY the ColorY
     */
    public void setColorY(final Integer colorY) {
        this.colorY = colorY;
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
        serializer.serialize(colorX, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(colorY, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        colorX = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        colorY = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("colorX = ");
        builder.append(colorX);
        builder.append(", ");
        builder.append("colorY = ");
        builder.append(colorY);
        builder.append(", ");
        builder.append("transitionTime = ");
        builder.append(transitionTime);
        return builder.toString();
    }

}
