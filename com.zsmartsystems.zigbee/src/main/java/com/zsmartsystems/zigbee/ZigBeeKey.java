/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.Arrays;

/**
 * Represents a 128 bit ZigBee key
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeKey {
    private int[] key;

    /**
     * Default constructor. Creates a network key of 0
     */
    public ZigBeeKey() {
        this.key = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }

    /**
     * Create an {@link ZigBeeKey} from a {@link String}. The string must contain 32 hexadecimal numbers
     * to make up a 16 byte key. Numbers can be formatted in various ways -:
     * <ul>
     * <li>1234ABCD ...
     * <li>12 34 AB CD ...
     * <li>12,34,AB,CD ...
     * <li>0x12 0x34 0xAB 0xCD ...
     * <li>0x12,0x34,0xAB,0xCD ...
     * </ul>
     *
     * @param key the key as a {@link String}
     * @throws IllegalArgumentException
     */
    public ZigBeeKey(String keyString) {
        if (keyString == null) {
            throw new IllegalArgumentException("Key string must not be null");
        }

        String hexString = keyString.replace("0x", "");
        hexString = hexString.replace(",", "");
        hexString = hexString.replace(" ", "");

        if (hexString.length() != 32) {
            throw new IllegalArgumentException("Key string must contain an array of 32 hexadecimal numbers");
        }

        this.key = new int[16];
        char enc[] = hexString.toCharArray();
        for (int i = 0; i < enc.length; i += 2) {
            StringBuilder curr = new StringBuilder(2);
            curr.append(enc[i]).append(enc[i + 1]);
            this.key[i / 2] = Integer.parseInt(curr.toString(), 16);
        }
    }

    /**
     * Create a {@link ZigBeeKey} from an int array
     *
     * @param key the key as an int array. Array length must be 16.
     * @throws IllegalArgumentException
     */
    public ZigBeeKey(int[] key) {
        if (key == null) {
            this.key = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            return;
        }
        if (key.length != 16) {
            throw new IllegalArgumentException("NetworkKey array length must be 16 hex bytes");
        }
        this.key = key;
    }

    /**
     * Gets the Network Key as an integer array with length 16
     *
     * @return int array of key
     */
    public int[] getValue() {
        return key;
    }

    /**
     * Check if the NetworkKey is valid. This checks the length of the ID, and checks
     * it is not 00000000000000000000000000000000.
     *
     * @return true if the key is valid
     */
    public boolean isValid() {
        if (key == null || key.length != 16) {
            return false;
        }

        int cnt0 = 0;
        for (int val : key) {
            if (val == 0x00) {
                cnt0++;
            }
        }

        return (cnt0 != 16);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(key);
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
        ZigBeeKey other = (ZigBeeKey) obj;
        return Arrays.equals(key, other.key);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int cnt = 0; cnt < 16; cnt++) {
            builder.append(String.format("%02X", key[cnt]));
        }

        return builder.toString();
    }

    /**
     * Create a random key
     *
     * @return {@link ZigBeeKey} containing a random 128 bit key
     */
    public static ZigBeeKey createRandom() {
        int key[] = new int[16];
        for (int cnt = 0; cnt < 16; cnt++) {
            key[cnt] = (int) Math.floor((Math.random() * 255));
        }

        return new ZigBeeKey(key);
    }

}
