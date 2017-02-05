package com.zsmartsystems.zigbee.zcl.field;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Read Attribute Status Record field.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
public class ReadAttributeStatusRecord implements ZclListItemField {
    /**
     * The attribute identifier.
     */
    private int attributeIdentifier;
    /**
     * The status.
     */
    private int status;
    /**
     * The attribute data type.
     */
    private ZclDataType attributeDataType;
    /**
     * The attribute data type.
     */
    private Object attributeValue;

    /**
     * Gets attribute data type.
     *
     * @return the attribute data type
     */
    public ZclDataType getAttributeDataType() {
        return attributeDataType;
    }

    /**
     * Sets attribute data type.
     *
     * @param attributeDataType the attribute data type as {@link ZclDataType}
     */
    public void setAttributeDataType(ZclDataType attributeDataType) {
        this.attributeDataType = attributeDataType;
    }

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
        serializer.appendZigBeeType((short) attributeIdentifier, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType((byte) status, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType((byte) attributeDataType.getId(), ZclDataType.UNSIGNED_8_BIT_INTEGER);
        serializer.appendZigBeeType(attributeValue, attributeDataType);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        attributeIdentifier = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        status = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        if (ZclStatus.getStatus((byte) status).equals(ZclStatus.SUCCESS)) {
            attributeDataType = ZclDataType
                    .getType((int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER));
            attributeValue = deserializer.readZigBeeType(attributeDataType);
        }
    }

    @Override
    public String toString() {
        return "Read Attribute Status Record: attributeDataType=" + attributeDataType + ", attributeIdentifier="
                + attributeIdentifier + ", status=" + status + ", attributeValue=" + attributeValue;
    }
}
