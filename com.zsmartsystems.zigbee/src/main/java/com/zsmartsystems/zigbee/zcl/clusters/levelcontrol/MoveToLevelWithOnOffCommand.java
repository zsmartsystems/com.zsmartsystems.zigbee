/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.levelcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Move To Level (with On/Off) Command value object class.
 * <p>
 * Cluster: <b>Level Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Level Control cluster.
 * <p>
 * On receipt of this command, a device shall move from its current level to the value given in the
 * Level field. The meaning of ‘level’ is device dependent –e.g., for a light it may mean
 * brightness level.The movement shall be as continuous as technically practical, i.e., not a
 * step function, and the time taken to move to the new level shall be equal to the value of the
 * Transition time field, in tenths of a second, or as close to this as the device is able.If the
 * Transition time field takes the value 0xffff then the time taken to move to the new level shall
 * instead be determined by the OnOffTransitionTimeattribute. If OnOffTransitionTime,
 * which is an optional attribute, is not present, the device shall move to its new level as fast
 * as it is able.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class MoveToLevelWithOnOffCommand extends ZclCommand {
    /**
     * Level command message field.
     */
    private Integer level;

    /**
     * Transition Time command message field.
     */
    private Integer transitionTime;

    /**
     * Default constructor.
     */
    public MoveToLevelWithOnOffCommand() {
        genericCommand = false;
        clusterId = 0x0008;
        commandId = 4;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Level.
     *
     * @return the Level
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * Sets Level.
     *
     * @param level the Level
     */
    public void setLevel(final Integer level) {
        this.level = level;
    }

    /**
     * Gets Transition Time.
     *
     * @return the Transition Time
     */
    public Integer getTransitionTime() {
        return transitionTime;
    }

    /**
     * Sets Transition Time.
     *
     * @param transitionTime the Transition Time
     */
    public void setTransitionTime(final Integer transitionTime) {
        this.transitionTime = transitionTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(level, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.serialize(transitionTime, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        level = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        transitionTime = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(89);
        builder.append("MoveToLevelWithOnOffCommand [");
        builder.append(super.toString());
        builder.append(", level=");
        builder.append(level);
        builder.append(", transitionTime=");
        builder.append(transitionTime);
        builder.append(']');
        return builder.toString();
    }

}
