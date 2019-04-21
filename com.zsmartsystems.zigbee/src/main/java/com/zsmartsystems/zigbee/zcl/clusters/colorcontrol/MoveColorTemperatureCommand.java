/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Move Color Temperature Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x4B is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * The Move Color Temperature command allows the color temperature of a lamp to be moved at a
 * specified rate.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class MoveColorTemperatureCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x4B;

    /**
     * Move Mode command message field.
     */
    private Integer moveMode;

    /**
     * Rate command message field.
     */
    private Integer rate;

    /**
     * Color Temperature Minimum command message field.
     */
    private Integer colorTemperatureMinimum;

    /**
     * Color Temperature Maximum command message field.
     */
    private Integer colorTemperatureMaximum;

    /**
     * Default constructor.
     */
    public MoveColorTemperatureCommand() {
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

    /**
     * Gets Color Temperature Minimum.
     *
     * @return the Color Temperature Minimum
     */
    public Integer getColorTemperatureMinimum() {
        return colorTemperatureMinimum;
    }

    /**
     * Sets Color Temperature Minimum.
     *
     * @param colorTemperatureMinimum the Color Temperature Minimum
     */
    public void setColorTemperatureMinimum(final Integer colorTemperatureMinimum) {
        this.colorTemperatureMinimum = colorTemperatureMinimum;
    }

    /**
     * Gets Color Temperature Maximum.
     *
     * @return the Color Temperature Maximum
     */
    public Integer getColorTemperatureMaximum() {
        return colorTemperatureMaximum;
    }

    /**
     * Sets Color Temperature Maximum.
     *
     * @param colorTemperatureMaximum the Color Temperature Maximum
     */
    public void setColorTemperatureMaximum(final Integer colorTemperatureMaximum) {
        this.colorTemperatureMaximum = colorTemperatureMaximum;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(moveMode, ZclDataType.BITMAP_8_BIT);
        serializer.serialize(rate, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(colorTemperatureMinimum, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.serialize(colorTemperatureMaximum, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        moveMode = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
        rate = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        colorTemperatureMinimum = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        colorTemperatureMaximum = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(168);
        builder.append("MoveColorTemperatureCommand [");
        builder.append(super.toString());
        builder.append(", moveMode=");
        builder.append(moveMode);
        builder.append(", rate=");
        builder.append(rate);
        builder.append(", colorTemperatureMinimum=");
        builder.append(colorTemperatureMinimum);
        builder.append(", colorTemperatureMaximum=");
        builder.append(colorTemperatureMaximum);
        builder.append(']');
        return builder.toString();
    }

}
