package com.zsmartsystems.zigbee.zcl.field;

import java.util.Arrays;

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
public class ExtensionFieldSet implements ZclListItemField {
    /**
     * The cluster id.
     */
    private int clusterId;

    /**
     * The data length.
     */
    private int length;

    /**
     * The data.
     */
    private byte[] data;

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
    public byte[] getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * Gets data length.
     *
     * @return the data length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets data length.
     *
     * @param length the data length
     */
    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(clusterId, ZclDataType.UNSIGNED_16_BIT_INTEGER);
        serializer.appendZigBeeType(length, ZclDataType.UNSIGNED_8_BIT_INTEGER);
        for (int i = 0; i < length; i++) {
            serializer.appendZigBeeType(data[i], ZclDataType.UNSIGNED_8_BIT_INTEGER);
        }
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        clusterId = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_16_BIT_INTEGER);
        length = (int) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER);
        data = new byte[length];
        for (int i = 0; i < length; i++) {
            data[i] = ((Number) deserializer.readZigBeeType(ZclDataType.UNSIGNED_8_BIT_INTEGER)).byteValue();
        }
    }

    @Override
    public String toString() {
        return "Extension Field Set " + "clusterId=" + clusterId + ", length=" + length + ", data="
                + Arrays.toString(data);
    }
}
