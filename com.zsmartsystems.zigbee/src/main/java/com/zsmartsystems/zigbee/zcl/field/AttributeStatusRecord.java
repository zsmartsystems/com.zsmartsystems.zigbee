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
public class AttributeStatusRecord implements ZclListItemField {
    /**
     * The status.
     */
    private int status;
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
     * @param attributeIdentifier the attribute identifier.
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

    /**
     * Gets status.
     *
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (status != 0) {
            serializer.appendZigBeeType(direction, ZclDataType.BOOLEAN);
            serializer.appendZigBeeType(attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        status = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (status != 0) {
            direction = (boolean) deserializer.readZigBeeType(ZclDataType.BOOLEAN);
            attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        }
    }

    @Override
    public String toString() {
        return "Attribute Status Record: status=" + status + ", direction=" + direction + ", attributeIdentifier="
                + attributeIdentifier;
    }
}
