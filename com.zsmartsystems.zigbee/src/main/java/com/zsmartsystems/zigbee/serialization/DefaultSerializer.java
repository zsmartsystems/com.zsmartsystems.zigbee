/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.field.ExtensionFieldSet;
import com.zsmartsystems.zigbee.zcl.field.ZclArrayList;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;

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
            throw new IllegalArgumentException("You cannot append null data to a stream");
        }

        switch (type) {
            case BOOLEAN:
                buffer[length++] = (Boolean) data ? 1 : 0;
                break;
            case NWK_ADDRESS:
            case DATA_16_BIT:
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
                List<ExtensionFieldSet> extensionFieldSets = (List<ExtensionFieldSet>) data;
                for (ExtensionFieldSet extensionFieldSet : extensionFieldSets) {
                    extensionFieldSet.serialize(this);
                }
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
            case RAW_OCTET:
                final ByteArray rawArray = (ByteArray) data;
                for (byte arrayByte : rawArray.get()) {
                    buffer[length++] = arrayByte & 0xFF;
                }
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
            case LONG_OCTET_STRING:
                final ByteArray longArray = (ByteArray) data;
                buffer[length++] = ((byte) (longArray.size() & 0xFF));
                buffer[length++] = (longArray.size() >> 8) & 0xFF;
                for (byte arrayByte : longArray.get()) {
                    buffer[length++] = arrayByte;
                }
                break;
            case SECURITY_KEY:
                final ZigBeeKey securityKey = (ZigBeeKey) data;
                for (int arrayInt : securityKey.getValue()) {
                    buffer[length++] = arrayInt;
                }
                break;
            case BITMAP_24_BIT:
            case SIGNED_24_BIT_INTEGER:
            case UNSIGNED_24_BIT_INTEGER:
                final int uint24Value = (Integer) data;
                buffer[length++] = uint24Value & 0xFF;
                buffer[length++] = (uint24Value >> 8) & 0xFF;
                buffer[length++] = (uint24Value >> 16) & 0xFF;
                break;
            case ENUMERATION_32_BIT:
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
            case FLOAT_32_BIT:
                final Float float32 = ((Double) data).floatValue();
                final int float32Value = Float.floatToRawIntBits(float32);
                buffer[length++] = float32Value & 0xFF;
                buffer[length++] = (float32Value >> 8) & 0xFF;
                buffer[length++] = (float32Value >> 16) & 0xFF;
                buffer[length++] = (float32Value >> 24) & 0xFF;
                break;
            case ORDERED_SEQUENCE_ARRAY:
                ZclArrayList zclArray = (ZclArrayList) data;
                buffer[length++] = zclArray.getDataType().getId();
                buffer[length++] = zclArray.size();
                buffer[length++] = 0;
                for (Object value : zclArray) {
                    appendZigBeeType(value, zclArray.getDataType());
                }
                break;
            case BINDING_TABLE:
                BindingTable bindingTable = (BindingTable) data;
                bindingTable.serialize(this);
                break;

            default:
                throw new IllegalArgumentException("No writer defined in " + ZigBeeDeserializer.class.getSimpleName()
                        + " for " + type.toString() + String.format(" (0x%02X)", type.getId()));
        }
    }
}
