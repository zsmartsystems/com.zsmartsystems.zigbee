/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Setpoint Raise/Lower Command value object class.
 * <p>
 * Cluster: <b>Thermostat</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Thermostat cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class SetpointRaiseLowerCommand extends ZclThermostatCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0201;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Mode command message field.
     */
    private Integer mode;

    /**
     * Amount command message field.
     */
    private Integer amount;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetpointRaiseLowerCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param mode {@link Integer} Mode
     * @param amount {@link Integer} Amount
     */
    public SetpointRaiseLowerCommand(
            Integer mode,
            Integer amount) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.mode = mode;
        this.amount = amount;
    }

    /**
     * Gets Mode.
     *
     * @return the Mode
     */
    public Integer getMode() {
        return mode;
    }

    /**
     * Sets Mode.
     *
     * @param mode the Mode
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMode(final Integer mode) {
        this.mode = mode;
    }

    /**
     * Gets Amount.
     *
     * @return the Amount
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * Sets Amount.
     *
     * @param amount the Amount
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
        mode = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        amount = deserializer.deserialize(ZclDataType.SIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(78);
        builder.append("SetpointRaiseLowerCommand [");
        builder.append(super.toString());
        builder.append(", mode=");
        builder.append(mode);
        builder.append(", amount=");
        builder.append(amount);
        builder.append(']');
        return builder.toString();
    }

}
