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
 * Move (with On/Off) Command value object class.
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
public class MoveWithOnOffCommand extends ZclCommand {
    /**
     * Move mode command message field.
     */
    private Integer moveMode;

    /**
     * Rate command message field.
     */
    private Integer rate;

    /**
     * Default constructor setting the command type field.
     */
    public MoveWithOnOffCommand() {
        genericCommand = false;
        clusterId = 8;
        commandId = 5;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public MoveWithOnOffCommand(final Map<Integer, Object> fields) {
        this();
        moveMode = (Integer) fields.get(0);
        rate = (Integer) fields.get(1);
    }

    /**
     * Gets Move mode.
     * @return the Move mode
     */
    public Integer getMoveMode() {
        return moveMode;
    }

    /**
     * Sets Move mode.
     * @param moveMode the Move mode
     */
    public void setMoveMode(final Integer moveMode) {
        this.moveMode = moveMode;
    }

    /**
     * Gets Rate.
     * @return the Rate
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * Sets Rate.
     * @param rate the Rate
     */
    public void setRate(final Integer rate) {
        this.rate = rate;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(moveMode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(rate, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        moveMode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        rate = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("moveMode = ");
        builder.append(moveMode);
        builder.append(", ");
        builder.append("rate = ");
        builder.append(rate);
        return builder.toString();
    }

}
