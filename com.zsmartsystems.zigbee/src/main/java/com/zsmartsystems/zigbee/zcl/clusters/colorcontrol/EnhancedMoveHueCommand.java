/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
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
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class EnhancedMoveHueCommand extends ZclCommand {
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
     */
    public EnhancedMoveHueCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
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
     */
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
     */
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
        moveMode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        rate = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
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
