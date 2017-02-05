package com.zsmartsystems.zigbee.serialization;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

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
            case BITMAP_16_BIT:
            case SIGNED_16_BIT_INTEGER:
            case UNSIGNED_16_BIT_INTEGER:
            case ENUMERATION_16_BIT:
                final short shortValue = ((Number) data).shortValue();
                buffer[length++] = shortValue & 0xFF;
                buffer[length++] = (shortValue >> 8) & 0xFF;
                break;
            case DATA_8_BIT:
            case BITMAP_8_BIT:
            case SIGNED_8_BIT_INTEGER:
            case UNSIGNED_8_BIT_INTEGER:
            case ENUMERATION_8_BIT:
                final byte byteValue = ((Number) data).byteValue();
                buffer[length++] = byteValue & 0xFF;
                break;
            case IEEE_ADDRESS:
                long longVal = ((IeeeAddress) data).getLong();
                buffer[length++] = (int) (longVal & 0xFF);
                buffer[length++] = (int) ((longVal >> 8) & 0xFF);
                buffer[length++] = (int) ((longVal >> 16) & 0xFF);
                buffer[length++] = (int) ((longVal >> 24) & 0xFF);
                buffer[length++] = (int) ((longVal >> 32) & 0xFF);
                buffer[length++] = (int) ((longVal >> 40) & 0xFF);
                buffer[length++] = (int) ((longVal >> 48) & 0xFF);
                buffer[length++] = (int) ((longVal >> 56) & 0xFF);
                break;
            case N_X_ATTRIBUTE_IDENTIFIER:
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
                break;
            case N_X_UNSIGNED_8_BIT_INTEGER:
                break;
            case N_X_WRITE_ATTRIBUTE_RECORD:
                break;
            case N_X_WRITE_ATTRIBUTE_STATUS_RECORD:
                break;
            case CHARACTER_STRING:
            case OCTET_STRING:
                final String str = (String) data;
                buffer[length++] = ((byte) (str.length() & (0xFF)));
                for (int strByte : str.getBytes()) {
                    buffer[length++] = strByte;
                }
                break;
            case SIGNED_32_BIT_INTEGER:
                final int intValue = (Integer) data;
                buffer[length++] = intValue & 0xFF;
                buffer[length++] = (intValue >> 8) & 0xFF;
                buffer[length++] = (intValue >> 16) & 0xFF;
                buffer[length++] = (intValue >> 24) & 0xFF;
                break;
            case UNSIGNED_32_BIT_INTEGER:
                final int uintValue = (Integer) data;
                buffer[length++] = uintValue & 0xFF;
                buffer[length++] = (uintValue >> 8) & 0xFF;
                buffer[length++] = (uintValue >> 16) & 0xFF;
                buffer[length++] = (uintValue >> 24) & 0xFF;
                break;
            case UTCTIME:
                break;
            default:
                throw new IllegalArgumentException("No reader defined in " + ZigBeeDeserializer.class.getSimpleName()
                        + " for " + type.toString() + " (" + type.getId() + ")");
        }
    }
}
