package com.zsmartsystems.zigbee.serialization;

import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * The interface for serialization of a ZCL frame to array of integers
 *
 * @author Chris Jackson
 */
public interface ZigBeeSerializer {

    /**
     * @param data {@link Object} containing the value to append
     * @param type {@link ZclDataType} to select of data has to be appended
     */
    public void appendZigBeeType(Object data, ZclDataType type);

    /**
     * @return a copy of the payload
     */
    public int[] getPayload();
}
