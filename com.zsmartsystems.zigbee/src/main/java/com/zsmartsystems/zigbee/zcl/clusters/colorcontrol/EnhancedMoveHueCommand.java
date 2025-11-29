/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
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
 * Enhanced Move Hue Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x41 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * The Enhanced Move to Hue command allows lamps to be moved in a smooth continuous transition
 * from their current hue to a target hue.
 * <p>
 * On receipt of this command, a device shall set the ColorMode attribute to 0x00 and set the
 * EnhancedColorMode attribute to the value 0x03. The device shall then move from its current
 * enhanced hue in an up or down direction in a continuous fashion.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class EnhancedMoveHueCommand extends ZclColorControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x41;

    /**
     * Move Mode command message field.
     */
    private Integer moveMode;

    /**
     * Rate command message field.
     */
    private Integer rate;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public EnhancedMoveHueCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param moveMode {@link Integer} Move Mode
     * @param rate {@link Integer} Rate
     */
    public EnhancedMoveHueCommand(
            Integer moveMode,
            Integer rate) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.moveMode = moveMode;
        this.rate = rate;
    }

    /**
     * Gets Move Mode.
     *
     * @return the Move Mode
     */
    public Integer getMoveMode() {
        return moveMode;
    }

    /**
     * Sets Move Mode.
     *
     * @param moveMode the Move Mode
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setMoveMode(final Integer moveMode) {
        this.moveMode = moveMode;
    }

    /**
     * Gets Rate.
     *
     * @return the Rate
     */
    public Integer getRate() {
        return rate;
    }

    /**
     * Sets Rate.
     *
     * @param rate the Rate
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setRate(final Integer rate) {
        this.rate = rate;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(moveMode, ZclDataType.ENUMERATION_8_BIT);
        serializer.serialize(rate, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        moveMode = deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        rate = deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(77);
        builder.append("EnhancedMoveHueCommand [");
        builder.append(super.toString());
        builder.append(", moveMode=");
        builder.append(moveMode);
        builder.append(", rate=");
        builder.append(rate);
        builder.append(']');
        return builder.toString();
    }

}
