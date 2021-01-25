/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
    protected int clusterId;

    /**
     * The extension data.
     */
    private ByteArray data;

    public ExtensionFieldSet() {
    }

    /**
     * Constructor taking the cluster ID and data
     *
     * @param clusterId the cluster ID for this extension field set
     * @param data the data relevant to this cluster as a {@link ByteArray}
     */
    public ExtensionFieldSet(int clusterId, ByteArray data) {
        this.clusterId = clusterId;
        this.data = data;
    }

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
    public ByteArray getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data as a {@link ByteArray}
     */
    public void setData(ByteArray data) {
        this.data = data;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(data, ZclDataType.BYTE_ARRAY);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        clusterId = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        data = (ByteArray) deserializer.readZigBeeType(ZclDataType.BYTE_ARRAY);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(100);
        builder.append("ExtensionFieldSet [clusterId=");
        builder.append(String.format("%04X", clusterId));
        builder.append(", data=");
        builder.append(data);
        builder.append(']');
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + clusterId;
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ExtensionFieldSet other = (ExtensionFieldSet) obj;
        if (clusterId != other.clusterId) {
            return false;
        }
        if (data == null) {
            if (other.data != null) {
                return false;
            }
        } else if (!data.equals(other.data)) {
            return false;
        }
        return true;
    }
}
