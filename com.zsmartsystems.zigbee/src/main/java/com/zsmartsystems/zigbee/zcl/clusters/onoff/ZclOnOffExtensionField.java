/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>On/Off</b> cluster {@link ExtensionFieldSet} implementation for use with scenes.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2021-01-20T21:25:14Z")
public class ZclOnOffExtensionField extends ExtensionFieldSet {

    /**
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     */
    private Boolean onOff;

    /**
     * Default constructor to create a On/Off {@link ExtensionFieldSet}.
     *
     * @param onOff {@link Boolean} On Off
     */
    public ZclOnOffExtensionField(
            Boolean onOff) {
        clusterId = ZclOnOffCluster.CLUSTER_ID;

        this.onOff = onOff;
    }

    /**
     * The OnOff attribute has the following values: 0 = Off, 1 = On
     *
     * @return the On Off
     */
    public Boolean getOnOff() {
        return onOff;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(1, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(onOff, ZclDataType.BOOLEAN);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        clusterId = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        int size = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (size >= 1) {
            onOff = (Boolean) deserializer.readZigBeeType(ZclDataType.BOOLEAN);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(100);
        builder.append("ZclOnOffExtensionField [clusterId=");
        builder.append(clusterId);
        builder.append(", onOff=");
        builder.append(onOff);
        builder.append(']');
        return builder.toString();
    }
}
