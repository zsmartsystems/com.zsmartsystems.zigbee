/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Represents a 64 bit extended PAN Id
 *
 * @author Chris Jackson
 *
 */
public class ExtendedPanId {
    private int[] panId;

    /**
     * Default constructor. Creates a PAN Id of 0
     */
    public ExtendedPanId() {
        this.panId = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
    }

    /**
     * Create an {@link ExtendedPanId} from a {@link BigInteger}
     *
     * @param panId the panId as a {@link BigInteger}
     */
    public ExtendedPanId(BigInteger panId) {
        this.panId = new int[8];

        long longVal = panId.longValue();

        this.panId[0] = (int) (longVal & 0xff);
        this.panId[1] = (int) ((longVal >> 8) & 0xff);
        this.panId[2] = (int) ((longVal >> 16) & 0xff);
        this.panId[3] = (int) ((longVal >> 24) & 0xff);
        this.panId[4] = (int) ((longVal >> 32) & 0xff);
        this.panId[5] = (int) ((longVal >> 40) & 0xff);
        this.panId[6] = (int) ((longVal >> 48) & 0xff);
        this.panId[7] = (int) ((longVal >> 56) & 0xff);
    }

    /**
     * Create an {@link ExtendedPanId} from a {@link String} defined in hexadecimal notation.
     *
     * @param panId the panId as a {@link String}
     */
    public ExtendedPanId(String panId) {
        this(new BigInteger(panId, 16));
    }

    /**
     * Create an {@link ExtendedPanId} from an int array
     *
     * @param panId the panId as an int array. Array length must be 8.
     * @throws InvalidParameterException
     */
    public ExtendedPanId(int[] panId) {
        if (panId == null) {
            this.panId = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
            return;
        }
        if (panId.length != 8) {
            throw new InvalidParameterException("ExtendedPanId array length must be 8");
        }
        this.panId = panId;
    }

    /**
     * Gets the ExtendedPanId as an integer array with length 8
     *
     * @return int array of panId
     */
    public int[] getValue() {
        return panId;
    }

    /**
     * Check if the ExtendedPanId is valid. This checks the length of the ID, and checks
     * it is not 0000000000000000 or FFFFFFFFFFFFFFFF.
     *
     * @return true if the extended PAN ID is valid
     */
    public boolean isValid() {
        if (panId == null || panId.length != 8) {
            return false;
        }

        int cnt0 = 0;
        int cntF = 0;
        for (int val : panId) {
            if (val == 0x00) {
                cnt0++;
            }
            if (val == 0xFF) {
                cntF++;
            }
        }

        return !(cnt0 == 8 || cntF == 8);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(panId);
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
        ExtendedPanId other = (ExtendedPanId) obj;
        return Arrays.equals(panId, other.panId);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int cnt = 7; cnt >= 0; cnt--) {
            builder.append(String.format("%02X", panId[cnt]));
        }

        return builder.toString();
    }
}
