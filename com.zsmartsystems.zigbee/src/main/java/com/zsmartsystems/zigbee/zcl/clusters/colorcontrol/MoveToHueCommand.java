package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Move to Hue Command value object class.
 * <p>
 * Cluster: <b>Color control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color control cluster.
 * <p>
 * This cluster provides an interface for changing the color of a light. Color is
 * specified according to the Commission Internationale de l'Éclairage (CIE)
 * specification CIE 1931 Color Space, [B4]. Color control is carried out in terms of
 * x,y values, as defined by this specification.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class MoveToHueCommand extends ZclCommand {
    /**
     * Hue command message field.
     */
    private Integer hue;

    /**
     * Direction command message field.
     */
    private Integer direction;

    /**
     * Transition time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public MoveToHueCommand() {
        genericCommand = false;
        clusterId = 768;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Gets Hue.
     * @return the Hue
     */
    public Integer getHue() {
        return hue;
    }

    /**
     * Sets Hue.
     * @param hue the Hue
     */
    public void setHue(final Integer hue) {
        this.hue = hue;
    }

    /**
     * Gets Direction.
     * @return the Direction
     */
    public Integer getDirection() {
        return direction;
    }

    /**
     * Sets Direction.
     * @param direction the Direction
     */
    public void setDirection(final Integer direction) {
        this.direction = direction;
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
        serializer.serialize(hue, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(direction, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        hue = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        direction = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", hue=");
        builder.append(hue);
        builder.append(", direction=");
        builder.append(direction);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        return builder.toString();
    }

}
