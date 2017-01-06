package com.zsmartsystems.zigbee.zcl;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;

/**
 * ZclField class for non primitive field types.
 *
 * @author Tommi S.E Laukkanen
 */
public interface ZclListItemField {
    /**
     * Serializes the field.
     * 
     * @param serializer the serializer
     */
    void serialize(ZigBeeSerializer serializer);

    /**
     * Deserializes the field.
     * 
     * @param deserializer the deserializer.
     */
    void deserialize(ZigBeeDeserializer deserializer);
}
