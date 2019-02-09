/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.pollcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Long Poll Interval Command value object class.
 * <p>
 * Cluster: <b>Poll Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Poll Control cluster.
 * <p>
 * The Set Long Poll Interval command is used to set the Read Only LongPollInterval attribute.
 * <br> When the Poll Control Server receives the Set Long Poll Interval Command, it should
 * check its internal minimal limit and the attributes relationship if the new Long Poll
 * Interval is acceptable. If the new value is acceptable, the new value shall be saved to the
 * LongPollInterval attribute. If the new value is not acceptable, the Poll Control Server
 * shall send a default response of INVALID_VALUE and the LongPollInterval attribute value is
 * not updated.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class SetLongPollIntervalCommand extends ZclCommand {
    /**
     * New Long Poll Interval command message field.
     */
    private Integer newLongPollInterval;

    /**
     * Default constructor.
     */
    public SetLongPollIntervalCommand() {
        genericCommand = false;
        clusterId = 0x0020;
        commandId = 2;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets New Long Poll Interval.
     *
     * @return the New Long Poll Interval
     */
    public Integer getNewLongPollInterval() {
        return newLongPollInterval;
    }

    /**
     * Sets New Long Poll Interval.
     *
     * @param newLongPollInterval the New Long Poll Interval
     */
    public void setNewLongPollInterval(final Integer newLongPollInterval) {
        this.newLongPollInterval = newLongPollInterval;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(newLongPollInterval, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        newLongPollInterval = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(68);
        builder.append("SetLongPollIntervalCommand [");
        builder.append(super.toString());
        builder.append(", newLongPollInterval=");
        builder.append(newLongPollInterval);
        builder.append(']');
        return builder.toString();
    }

}
