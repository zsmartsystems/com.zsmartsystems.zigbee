/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.levelcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Move (with On/Off) Command value object class.
 * <p>
 * Cluster: <b>Level Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Level Control cluster.
 * <p>
 * This cluster provides an interface for controlling a characteristic of a device that
 * can be set to a level, for example the brightness of a light, the degree of closure of
 * a door, or the power output of a heater.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
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
     * Default constructor.
     */
    public MoveWithOnOffCommand() {
        genericCommand = false;
        clusterId = 8;
        commandId = 5;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Move mode.
     *
     * @return the Move mode
     */
    public Integer getMoveMode() {
        return moveMode;
    }

    /**
     * Sets Move mode.
     *
     * @param moveMode the Move mode
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
        serializer.serialize(rate, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        moveMode = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
        rate = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(75);
        builder.append("MoveWithOnOffCommand [");
        builder.append(super.toString());
        builder.append(", moveMode=");
        builder.append(moveMode);
        builder.append(", rate=");
        builder.append(rate);
        builder.append(']');
        return builder.toString();
    }

}
