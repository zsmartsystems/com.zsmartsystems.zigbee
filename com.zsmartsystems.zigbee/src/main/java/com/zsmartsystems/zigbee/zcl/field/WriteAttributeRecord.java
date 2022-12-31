/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
 * Write Attribute Record field.
 *
 * @author Chris Jackson
 */
public class WriteAttributeRecord implements ZclListItemField {
    /**
     * The attribute identifier.
     */
    private int attributeIdentifier;

    /**
     * The attribute {@link ZclDataType}.
     */
    private ZclDataType attributeDataType;

    /**
     * The attribute data value.
     */
    private Object attributeValue;

    /**
     * Gets attribute {@link ZclDataType}.
     *
     * @return the attribute {@link ZclDataType}
     */
    public ZclDataType getAttributeDataType() {
        return attributeDataType;
    }

    /**
     * Sets attribute {@link ZclDataType}
     *
     * @param attributeDataType the attribute {@link ZclDataType}
     */
    public void setAttributeDataType(ZclDataType attributeDataType) {
        this.attributeDataType = attributeDataType;
    }

    /**
     * Gets attribute identifier
     *
     * @return the attribute identifier
     */
    public int getAttributeIdentifier() {
        return attributeIdentifier;
    }

    /**
     * Sets attribute identifier
     *
     * @param attributeIdentifier the attribute identifier
     */
    public void setAttributeIdentifier(int attributeIdentifier) {
        this.attributeIdentifier = attributeIdentifier;
    }

    /**
     * Gets attribute value.
     *
     * @return the attribute value
     */
    public Object getAttributeValue() {
        return attributeValue;
    }

    /**
     * Sets attribute value.
     *
     * @param attributeValue the attribute value
     */
    public void setAttributeValue(Object attributeValue) {
        this.attributeValue = attributeValue;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(attributeDataType.getId(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(attributeValue, attributeDataType);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        attributeDataType = ZclDataType.getType((int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER));
        attributeValue = deserializer.readZigBeeType(attributeDataType);
    }

    @Override
    public String toString() {
        return "WriteAttributeRecord [attributeDataType=" + attributeDataType + ", attributeIdentifier="
                + attributeIdentifier + ", attributeValue=" + attributeValue + "]";
    }
}
