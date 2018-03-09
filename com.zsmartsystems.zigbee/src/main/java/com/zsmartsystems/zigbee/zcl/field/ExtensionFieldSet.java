/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Scene Extension Field.
 *
 * @author Chris Jackson
 */
public class ExtensionFieldSet implements ZclListItemField {
    /**
     * The cluster id.
     */
    private int clusterId;

    /**
     * The data length.
     */
    // private int length;

    /**
     * The extension data.
     */
    private int[] data;

    /**
     * Gets cluster ID
     *
     * @return the cluster ID
     */
    public int getClusterId() {
        return clusterId;
    }

    /**
     * Sets cluster ID
     *
     * @param clusterId the cluster ID
     */
    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public int[] getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(data, ZclDataType.UNSIGNED_8_BIT_INTEGER_ARRAY);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        clusterId = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        data = (int[]) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER_ARRAY);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(100);
        builder.append("ExtensionFieldSet [clusterId=");
        builder.append(clusterId);
        builder.append(", data=");
        for (int value : data) {
            builder.append(String.format("%02X ", value));
        }
        builder.append(']');
        return builder.toString();
    }
}
