package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Attribute Identifier field.
 * 
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class AttributeIdentifier implements ZclListItemField {
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
     * Sets attribute.
     *
     * @param attributeIdentifier the attribute identifier
     */
    public void setAttributeIdentifier(int attributeIdentifier) {
        this.attributeIdentifier = attributeIdentifier;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        return "Attribute Identifier " + ", attributeIdentifier=" + attributeIdentifier;
    }
}
