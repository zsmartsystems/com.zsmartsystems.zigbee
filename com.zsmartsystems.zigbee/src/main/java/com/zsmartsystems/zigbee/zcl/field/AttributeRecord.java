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
     */
    private boolean direction;
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
     * Is direction?
     *
     * @return the direction
     */
    public boolean isDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(direction, ZclDataType.BOOLEAN);
        serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        direction = (boolean) deserializer.readZigBeeType(ZclDataType.BOOLEAN);
        attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        return "Attribute Record " + ", direction=" + direction + ", attributeIdentifier=" + attributeIdentifier;
    }
}
