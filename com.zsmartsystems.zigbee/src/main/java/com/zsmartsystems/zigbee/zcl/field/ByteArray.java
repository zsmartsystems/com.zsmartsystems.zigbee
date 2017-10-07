/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import java.util.Arrays;

import com.zsmartsystems.zigbee.serialization.ZigBeeDeserializer;
import com.zsmartsystems.zigbee.serialization.ZigBeeSerializer;
import com.zsmartsystems.zigbee.zcl.ZclListItemField;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Byte Array field.
 *
 * @author Chris Jackson
 */
public class ByteArray implements ZclListItemField {
    /**
     * The base byte array.
     */
    private byte[] value;

    /**
     * Constructor taking a byte[] array
     */
    public ByteArray(byte[] array) {
        value = array;
    }

    /**
     * Gets the byte array value.
     *
     * @return the value
     */
    public byte[] get() {
        return value;
    }

    /**
     * Get the length of the underlying byte array
     *
     * @return the length of the data in the array
     */
    public int size() {
        if (value == null) {
            return 0;
        }
        return value.length;
    }

    /**
     * Sets the byte array value.
     *
     * @param value the value
     */
    public void set(byte[] value) {
        this.value = value;
    }

    @Override
    public void serialize(final ZigBeeSerializer serializer) {
        serializer.appendZigBeeType(value, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZigBeeDeserializer deserializer) {
        value = (byte[]) deserializer.readZigBeeType(ZclDataType.BYTE_ARRAY);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(value);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ByteArray other = (ByteArray) obj;
        if (!Arrays.equals(value, other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Unsigned 16 Bit Integer: value=" + value;
    }
}
