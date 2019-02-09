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
 * Window Covering Go To Lift Value value object class.
 * <p>
 * Cluster: <b>Window Covering</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Window Covering cluster.
 * <p>
 * Goto the specified lift value
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T17:03:43Z")
public class WindowCoveringGoToLiftValue extends ZclCommand {
    /**
     * Lift Value command message field.
     */
    private Integer liftValue;

    /**
     * Default constructor.
     */
    public WindowCoveringGoToLiftValue() {
        genericCommand = false;
        clusterId = 0x0102;
        commandId = 4;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Lift Value.
     *
     * @return the Lift Value
     */
    public Integer getLiftValue() {
        return liftValue;
    }

    /**
     * Sets Lift Value.
     *
     * @param liftValue the Lift Value
     */
    public void setLiftValue(final Integer liftValue) {
        this.liftValue = liftValue;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(liftValue, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        liftValue = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(59);
        builder.append("WindowCoveringGoToLiftValue [");
        builder.append(super.toString());
        builder.append(", liftValue=");
        builder.append(liftValue);
        builder.append(']');
        return builder.toString();
    }

}
