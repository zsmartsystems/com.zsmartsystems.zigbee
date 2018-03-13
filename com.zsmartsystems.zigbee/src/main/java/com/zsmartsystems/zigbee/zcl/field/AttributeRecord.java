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
 * Read Attribute Status Record field.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class AttributeRecord implements ZclListItemField {
    /**
     * The direction.
     * <p>
     * The direction field specifies whether values of the attribute are reported (0x00), or
     * whether reports of the attribute are received (0x01).
     *
     */
    private int direction;
    /**
     * The attribute identifier.
     */
    private int attributeIdentifier;

    /**
     * Gets attribute identifier.
     *
     * @return the attribute identifier
     */
    public int getAttributeIdentifier() {
        return attributeIdentifier;
    }

    /**
     * Sets attribute identifier.
     *
     * @param attributeIdentifier the attribute identifier
     */
    public void setAttributeIdentifier(int attributeIdentifier) {
        this.attributeIdentifier = attributeIdentifier;
    }

    /**
     * Gets the direction
     * <p>
     * The direction field specifies whether values of the attribute are reported (0x00), or
     * whether reports of the attribute are received (0x01).
     *
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     * <p>
     * The direction field specifies whether values of the attribute are reported (0x00), or
     * whether reports of the attribute are received (0x01).
     *
     * @param direction the direction
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(direction, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        direction = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        return "Attribute Record[ direction=" + direction + ", attributeIdentifier=" + attributeIdentifier + "]";
    }
}
