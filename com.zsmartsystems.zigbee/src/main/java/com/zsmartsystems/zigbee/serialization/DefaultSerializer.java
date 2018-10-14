/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serialization;

import java.util.Arrays;
import java.util.List;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * The implementation of the {@link ZigBeeSerializer}.
 * <p>
 * Serializes data in a standard binary format.
 *
 * @author Chris Jackson
 */
public class DefaultSerializer implements ZigBeeSerializer {
    private int[] buffer = new int[131];
    private int length = 0;

    @Override
    public int[] getPayload() {
        return Arrays.copyOfRange(buffer, 0, length);
    }

    @Override
    public void appendZigBeeType(Object data, ZclDataType type) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("You can not append null data to a stream");
        }

        switch (type) {
            case BOOLEAN:
                buffer[length++] = (Boolean) data ? 1 : 0;
                break;
            case NWK_ADDRESS:
            case BITMAP_16_BIT:
            case SIGNED_16_BIT_INTEGER:
            case UNSIGNED_16_BIT_INTEGER:
            case ENUMERATION_16_BIT:
            case CLUSTERID:
                final short shortValue = ((Number) data).shortValue();
                buffer[length++] = shortValue & 0xFF;
                buffer[length++] = (shortValue >> 8) & 0xFF;
                break;
            case ENDPOINT:
            case DATA_8_BIT:
            case BITMAP_8_BIT:
            case SIGNED_8_BIT_INTEGER:
            case UNSIGNED_8_BIT_INTEGER:
            case ENUMERATION_8_BIT:
                final byte byteValue = ((Number) data).byteValue();
                buffer[length++] = byteValue & 0xFF;
                break;
            case EXTENDED_PANID:
                int[] panId = ((ExtendedPanId) data).getValue();
                buffer[length++] = panId[0];
                buffer[length++] = panId[1];
                buffer[length++] = panId[2];
                buffer[length++] = panId[3];
                buffer[length++] = panId[4];
                buffer[length++] = panId[5];
                buffer[length++] = panId[6];
                buffer[length++] = panId[7];
                break;
            case IEEE_ADDRESS:
                int[] address = ((IeeeAddress) data).getValue();
                buffer[length++] = address[0];
                buffer[length++] = address[1];
                buffer[length++] = address[2];
                buffer[length++] = address[3];
                buffer[length++] = address[4];
                buffer[length++] = address[5];
                buffer[length++] = address[6];
                buffer[length++] = address[7];
                break;
            case N_X_ATTRIBUTE_INFORMATION:
                break;
            case N_X_ATTRIBUTE_RECORD:
                break;
            case N_X_ATTRIBUTE_REPORT:
                break;
            case N_X_ATTRIBUTE_REPORTING_CONFIGURATION_RECORD:
                break;
            case N_X_ATTRIBUTE_SELECTOR:
                break;
            case N_X_ATTRIBUTE_STATUS_RECORD:
                break;
            case N_X_EXTENSION_FIELD_SET:
                break;
            case N_X_NEIGHBORS_INFORMATION:
                break;
            case N_X_READ_ATTRIBUTE_STATUS_RECORD:
                break;
            case N_X_UNSIGNED_16_BIT_INTEGER:
                List<Integer> intArray16 = (List<Integer>) data;
                buffer[length++] = intArray16.size();
                for (int value : intArray16) {
                    buffer[length++] = value & 0xFF;
                    buffer[length++] = (value >> 8) & 0xFF;
                }
                break;
            case N_X_UNSIGNED_8_BIT_INTEGER:
                List<Integer> intArrayNX8 = (List<Integer>) data;
                buffer[length++] = intArrayNX8.size();
                for (int value : intArrayNX8) {
                    buffer[length++] = value & 0xFF;
                }
                break;
            case UNSIGNED_8_BIT_INTEGER_ARRAY:
                int[] intArrayN8 = (int[]) data;
                for (int value : intArrayN8) {
                    buffer[length++] = value & 0xFF;
                }
                break;
            case X_UNSIGNED_8_BIT_INTEGER:
                List<Integer> intArrayX8 = (List<Integer>) data;
                for (int value : intArrayX8) {
                    buffer[length++] = value & 0xFF;
                }
                break;
            case N_X_ATTRIBUTE_IDENTIFIER:
                List<Integer> intArrayX16 = (List<Integer>) data;
                for (int value : intArrayX16) {
                    buffer[length++] = value & 0xFF;
                    buffer[length++] = (value >> 8) & 0xFF;
                }
                break;
            case N_X_WRITE_ATTRIBUTE_RECORD:
                break;
            case N_X_WRITE_ATTRIBUTE_STATUS_RECORD:
                break;
            case OCTET_STRING:
                final ByteArray array = (ByteArray) data;
                buffer[length++] = ((byte) (array.size() & 0xFF));
                for (byte arrayByte : array.get()) {
                    buffer[length++] = arrayByte;
                }
                break;
            case CHARACTER_STRING:
                final String str = (String) data;
                buffer[length++] = ((byte) (str.length() & 0xFF));
                for (int strByte : str.getBytes()) {
                    buffer[length++] = strByte;
                }
                break;
            case UNSIGNED_24_BIT_INTEGER:
                final int uint24Value = (Integer) data;
                buffer[length++] = uint24Value & 0xFF;
                buffer[length++] = (uint24Value >> 8) & 0xFF;
                buffer[length++] = (uint24Value >> 16) & 0xFF;
                break;
            case SIGNED_32_BIT_INTEGER:
                final int intValue = (Integer) data;
                buffer[length++] = intValue & 0xFF;
                buffer[length++] = (intValue >> 8) & 0xFF;
                buffer[length++] = (intValue >> 16) & 0xFF;
                buffer[length++] = (intValue >> 24) & 0xFF;
                break;
            case BITMAP_32_BIT:
            case UNSIGNED_32_BIT_INTEGER:
                final int uint32Value = (Integer) data;
                buffer[length++] = uint32Value & 0xFF;
                buffer[length++] = (uint32Value >> 8) & 0xFF;
                buffer[length++] = (uint32Value >> 16) & 0xFF;
                buffer[length++] = (uint32Value >> 24) & 0xFF;
                break;
            case UNSIGNED_48_BIT_INTEGER:
                final long uint48Value = (Long) data;
                buffer[length++] = (int) (uint48Value & 0xFF);
                buffer[length++] = (int) ((uint48Value >> 8) & 0xFF);
                buffer[length++] = (int) ((uint48Value >> 16) & 0xFF);
                buffer[length++] = (int) ((uint48Value >> 24) & 0xFF);
                buffer[length++] = (int) ((uint48Value >> 32) & 0xFF);
                buffer[length++] = (int) ((uint48Value >> 40) & 0xFF);
                break;
            case UTCTIME:
                break;
            case ZDO_STATUS:
                buffer[length++] = ((ZdoStatus) data).getId();
                break;
            case ZCL_STATUS:
                buffer[length++] = ((ZclStatus) data).getId();
                break;
            case BYTE_ARRAY:
                final ByteArray byteArray = (ByteArray) data;
                buffer[length++] = byteArray.size();
                for (byte valByte : byteArray.get()) {
                    buffer[length++] = valByte & 0xff;
                }
                break;
            case ZIGBEE_DATA_TYPE:
                buffer[length++] = ((ZclDataType) data).getId();
                break;
            default:
                throw new IllegalArgumentException("No writer defined in " + ZigBeeDeserializer.class.getSimpleName()
                        + " for " + type.toString() + " (" + type.getId() + ")");
        }
    }
}
