/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
 * Window Covering Go To Tilt Value value object class.
 * <p>
 * Cluster: <b>Window Covering</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Window Covering cluster.
 * <p>
 * Goto the specified tilt value
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T17:03:43Z")
public class WindowCoveringGoToTiltValue extends ZclCommand {
    /**
     * Tilt Value command message field.
     */
    private Integer tiltValue;

    /**
     * Default constructor.
     */
    public WindowCoveringGoToTiltValue() {
        genericCommand = false;
        clusterId = 0x0102;
        commandId = 7;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Tilt Value.
     *
     * @return the Tilt Value
     */
    public Integer getTiltValue() {
        return tiltValue;
    }

    /**
     * Sets Tilt Value.
     *
     * @param tiltValue the Tilt Value
     */
    public void setTiltValue(final Integer tiltValue) {
        this.tiltValue = tiltValue;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(tiltValue, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        tiltValue = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(59);
        builder.append("WindowCoveringGoToTiltValue [");
        builder.append(super.toString());
        builder.append(", tiltValue=");
        builder.append(tiltValue);
        builder.append(']');
        return builder.toString();
    }

}
