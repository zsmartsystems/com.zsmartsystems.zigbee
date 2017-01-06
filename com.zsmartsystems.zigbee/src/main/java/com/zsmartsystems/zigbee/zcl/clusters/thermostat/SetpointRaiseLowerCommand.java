package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * Setpoint Raise/Lower Command value object class.
 * </p>
 * <p>
 * Cluster: <b>Thermostat</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Thermostat cluster.
 * </p>
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 * </p>
 */
public class SetpointRaiseLowerCommand extends ZclCommand {
    /**
     * Mode command message field.
     */
    private Integer mode;

    /**
     * Amount command message field.
     */
    private Integer amount;

    /**
     * Default constructor setting the command type field.
     */
    public SetpointRaiseLowerCommand() {
        genericCommand = false;
        clusterId = 513;
        commandId = 0;
        commandDirection = true;
    }

    /**
     * Constructor copying field values from command message.
     *
     * @param fields a {@link Map} containing the value {@link Object}s
     */
    public SetpointRaiseLowerCommand(final Map<Integer, Object> fields) {
        this();
        mode = (Integer) fields.get(0);
        amount = (Integer) fields.get(1);
    }

    /**
     * Gets Mode.
     * @return the Mode
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * Sets Mode.
     * @param mode the Mode
     */
    public void setMode(final Integer mode) {
        this.mode = mode;
    }

    /**
     * Gets Amount.
     * @return the Amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets Amount.
     * @param amount the Amount
     */
    public void setAmount(final Integer amount) {
        this.amount = amount;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(mode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(amount, ZclDataType.SIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        mode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        amount = (Integer) deserializer.deserialize(ZclDataType.SIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append(", ");
        builder.append("mode = ");
        builder.append(mode);
        builder.append(", ");
        builder.append("amount = ");
        builder.append(amount);
        return builder.toString();
    }

}
