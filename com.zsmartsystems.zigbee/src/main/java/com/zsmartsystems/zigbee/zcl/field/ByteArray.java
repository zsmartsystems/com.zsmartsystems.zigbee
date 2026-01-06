/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
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
     * Constructor taking part of an existing integer array
     *
     * @param payload the existing integer array
     * @param start the start offset of the array (inclusive)
     * @param finish the end offset of the array (exclusive)
     */
    public ByteArray(int[] payload, int start, int finish) {
        value = new byte[finish - start];
        int outCnt = 0;
        for (int cnt = start; cnt < finish; cnt++) {
            value[outCnt++] = (byte) (payload[cnt] & 0xFF);
        }
    }

    /**
     * Constructor taking an existing integer array
     *
     * @param payload the existing integer array
     */
    public ByteArray(int[] payload) {
        this(payload, 0, payload.length);
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
     * Gets the byte array as an array of integers
     *
     * @return the integer array
     */
    public int[] getAsIntArray() {
        int[] intArray = new int[value.length];
        for (int cnt = 0; cnt < value.length; cnt++) {
            intArray[cnt] = value[cnt] & 0xFF;
        }

        return intArray;
    }

    /**
     * Gets a segment of the byte array as an array of integers
     *
     * @param start the start offset of the range to return (inclusive)
     * @param finish the end offset of the range to return (exclusive)
     * @return the integer array containing the requested segment
     */
    public int[] getAsIntArray(int start, int finish) {
        int intArray[] = new int[finish - start];
        int outCnt = 0;
        for (int cnt = start; cnt < finish; cnt++) {
            intArray[outCnt++] = value[cnt] & 0xFF;
        }

        return intArray;
    }

    /**
     * Get the length of the underlying byte array
     *
     * @return the length of the data in the array
     */
    public int size() {
        return value.length;
    }

    /**
     * Sets the byte array value.
     *
     * @param value the value as a byte array
     */
    public void set(byte[] value) {
        this.value = value;
    }

    /**
     * Sets the byte array value from an integer array.
     *
     * @param value the value as an integer array
     */
    public void set(int[] value) {
        this.value = new byte[value.length];
        int outCnt = 0;
        for (int intValue : value) {
            this.value[outCnt++] = (byte) (intValue & 0xFF);
        }
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
        builder.append("ByteArray [value=");
        boolean first = true;
        for (byte val : value) {
            if (!first) {
                builder.append(' ');
            }
            first = false;
            builder.append(String.format("%02X", val & 0xFF));
        }
        builder.append(']');

        return builder.toString();
    }

}
