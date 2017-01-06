package com.zsmartsystems.zigbee.serialization;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * The interface for deserialization of ZCL frame fields on array of integers
 *
 * @author Chris Jackson
 */
public interface ZigBeeDeserializer {

    public boolean endOfStream();

    public Object readZigBeeType(ZclDataType type);

    public int getPosition();

    public void skip(int bytes);

    int getSize();
}
