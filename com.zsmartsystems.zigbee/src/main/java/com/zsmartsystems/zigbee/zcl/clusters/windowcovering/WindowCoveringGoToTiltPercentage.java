/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.windowcovering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Window Covering Go To Tilt Percentage value object class.
 * <p>
 * Cluster: <b>Window Covering</b>. Command ID 0x08 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Window Covering cluster.
 * <p>
 * Goto the specified tilt percentage
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-06T18:45:28Z")
public class WindowCoveringGoToTiltPercentage extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0102;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x08;

    /**
     * Percentage Tilt Value command message field.
     */
    private Integer percentageTiltValue;

    /**
     * Default constructor.
     */
    public WindowCoveringGoToTiltPercentage() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Percentage Tilt Value.
     *
     * @return the Percentage Tilt Value
     */
    public Integer getPercentageTiltValue() {
        return percentageTiltValue;
    }

    /**
     * Sets Percentage Tilt Value.
     *
     * @param percentageTiltValue the Percentage Tilt Value
     * @return the WindowCoveringGoToTiltPercentage command
     */
    public WindowCoveringGoToTiltPercentage setPercentageTiltValue(final Integer percentageTiltValue) {
        this.percentageTiltValue = percentageTiltValue;
        return this;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(percentageTiltValue, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        percentageTiltValue = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(74);
        builder.append("WindowCoveringGoToTiltPercentage [");
        builder.append(super.toString());
        builder.append(", percentageTiltValue=");
        builder.append(percentageTiltValue);
        builder.append(']');
        return builder.toString();
    }

}
