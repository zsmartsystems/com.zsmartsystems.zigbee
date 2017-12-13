/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
 * Attribute Information field.
 *
 * @author Chris Jackson
 */
public class AttributeInformation implements ZclListItemField, Comparable<AttributeInformation> {
    /**
     * The ZigBee attribute data type.
     */
    private ZclDataType dataType;

    /**
     * The ZigBee attribute identifier number within the cluster.
     */
    private int identifier;

    /**
     * Gets attribute data type.
     *
     * @return the attribute {@link ZclDataType}
     */
    public ZclDataType getDataType() {
        return dataType;
    }

    /**
     * Sets attribute data type.
     *
     * @param dataType the attribute data type
     */
    public void setDataType(ZclDataType dataType) {
        this.dataType = dataType;
    }

    /**
     * Gets attribute identifier.
     *
     * @return the attribute identifier
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     * Sets attribute identifier
     *
     * @param identifier the attribute
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(identifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(dataType, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        identifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        dataType = (ZclDataType) deserializer.readZigBeeType(ZclDataType.ZIGBEE_DATA_TYPE);
    }

    @Override
    public int compareTo(AttributeInformation other) {
        return Integer.compare(identifier, other.getIdentifier());
    }

    @Override
    public String toString() {
        return "Attribute Information [dataType=" + dataType + ", identifier=" + identifier + "]";
    }

}
