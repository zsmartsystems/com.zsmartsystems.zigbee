/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaswd;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Squawk value object class.
 * <p>
 * Cluster: <b>IAS WD</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS WD cluster.
 * <p>
 * This command uses the WD capabilities to emit a quick audible/visible pulse called a
 * "squawk". The squawk command has no effect if the WD is currently active (warning in
 * progress).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class Squawk extends ZclCommand {
    /**
     * Squawk Info command message field.
     */
    private Integer squawkInfo;

    /**
     * Default constructor.
     */
    public Squawk() {
        genericCommand = false;
        clusterId = 0x0502;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Squawk Info.
     *
     * @return the Squawk Info
     */
    public Integer getSquawkInfo() {
        return squawkInfo;
    }

    /**
     * Sets Squawk Info.
     *
     * @param squawkInfo the Squawk Info
     */
    public void setSquawkInfo(final Integer squawkInfo) {
        this.squawkInfo = squawkInfo;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(squawkInfo, ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        squawkInfo = (Integer) deserializer.deserialize(ZclDataType.BITMAP_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(39);
        builder.append("Squawk [");
        builder.append(super.toString());
        builder.append(", squawkInfo=");
        builder.append(squawkInfo);
        builder.append(']');
        return builder.toString();
    }

}
