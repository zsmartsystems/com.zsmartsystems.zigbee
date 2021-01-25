/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.pollcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Set Short Poll Interval Command value object class.
 * <p>
 * Cluster: <b>Poll Control</b>. Command ID 0x03 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Poll Control cluster.
 * <p>
 * The Set Short Poll Interval command is used to set the Read Only ShortPollInterval
 * attribute. <br> When the Poll Control Server receives the Set Short Poll Interval Command,
 * it should check its internal minimal limit and the attributes relationship if the new Short
 * Poll Interval is acceptable. If the new value is acceptable, the new value shall be saved to
 * the ShortPollInterval attribute. If the new value is not acceptable, the Poll Control
 * Server shall send a default response of INVALID_VALUE and the ShortPollInterval attribute
 * value is not updated.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class SetShortPollIntervalCommand extends ZclPollControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0020;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

    /**
     * New Short Poll Interval command message field.
     */
    private Integer newShortPollInterval;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public SetShortPollIntervalCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param newShortPollInterval {@link Integer} New Short Poll Interval
     */
    public SetShortPollIntervalCommand(
            Integer newShortPollInterval) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;

        this.newShortPollInterval = newShortPollInterval;
    }

    /**
     * Gets New Short Poll Interval.
     *
     * @return the New Short Poll Interval
     */
    public Integer getNewShortPollInterval() {
        return newShortPollInterval;
    }

    /**
     * Sets New Short Poll Interval.
     *
     * @param newShortPollInterval the New Short Poll Interval
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
    public void setNewShortPollInterval(final Integer newShortPollInterval) {
        this.newShortPollInterval = newShortPollInterval;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(newShortPollInterval, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        newShortPollInterval = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(70);
        builder.append("SetShortPollIntervalCommand [");
        builder.append(super.toString());
        builder.append(", newShortPollInterval=");
        builder.append(newShortPollInterval);
        builder.append(']');
        return builder.toString();
    }

}
