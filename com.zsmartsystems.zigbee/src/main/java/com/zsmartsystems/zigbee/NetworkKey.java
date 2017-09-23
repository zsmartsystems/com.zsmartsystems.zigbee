/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Represents a 128 bit ZigBee network key
 *
 * @author Chris Jackson
 *
 */
public class NetworkKey {
    private int[] key;

    /**
     * Default constructor. Creates a network key of 0
     */
    public NetworkKey() {
        this.key = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }

    /**
     * Create an {@link NetworkKey} from a {@link String}
     *
     * @param key the key as a {@link String}
     */
    public NetworkKey(String key) {
        if (key.length() != 32) {
            throw new InvalidParameterException("NetworkKey array length must be 16 hex bytes");
        }

        this.key = new int[16];

        for (int cnt = 0; cnt < 16; cnt++) {
            this.key[cnt] = Integer.parseInt(key.substring(cnt * 2, (cnt * 2) + 2), 16);
        }
    }

    /**
     * Create a {@link NetworkKey} from an int array
     *
     * @param key the key as an int array. Array length must be 16.
     * @throws InvalidParameterException
     */
    public NetworkKey(int[] key) {
        if (key == null) {
            this.key = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            return;
        }
        if (key.length != 16) {
            throw new InvalidParameterException("NetworkKey array length must be 16 hex bytes");
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
        NetworkKey other = (NetworkKey) obj;
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
}
