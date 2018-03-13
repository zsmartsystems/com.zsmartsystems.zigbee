/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.field;

import java.util.Arrays;

/**
 * Byte Array field.
 *
 * @author Chris Jackson
 */
public class ByteArray {
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
        return Arrays.equals(value, other.value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(120);
        builder.append("ByteArray: value=");
        boolean first = true;
        for (byte val : value) {
            if (!first) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", val & 0xFF));
        }

        return builder.toString();
    }
}
