/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a 64 bit IEEE network address
 *
 * @author Chris Jackson
 *
 */
public class IeeeAddress implements Comparable<IeeeAddress> {
    private int[] address;

    /**
     * Default constructor. Creates an address 0
     */
    public IeeeAddress() {
        this.address = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
    }

    /**
     * Create an {@link IeeeAddress} from a {@link BigInteger}
     *
     * @param address the address as a {@link BigInteger}
     */
    public IeeeAddress(BigInteger address) {
        this();

        setAddress(address.longValue());
    }

    /**
     * Create an {@link IeeeAddress} from a {@link String}
     *
     * @param address the address as a {@link String}
     * @throws IllegalArgumentException
     */
    public IeeeAddress(String address) {
        this();
        try {
            setAddress(new BigInteger(address, 16).longValue());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("IeeeAddress string must contain valid hexadecimal value");
        }
    }

    /**
     * Create an {@link IeeeAddress} from an int array
     *
     * @param address the address as an int array. Array length must be 8.
     * @throws IllegalArgumentException
     */
    public IeeeAddress(int[] address) {
        if (address.length != 8) {
            throw new IllegalArgumentException("IeeeAddress array length must be 8");
        }
        this.address = Arrays.copyOf(address, 8);
    }

    /**
     * Gets the IeeeAddress as an integer array with length 8
     *
     * @return int array of address
     */
    public int[] getValue() {
        return address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address[0], address[1], address[2], address[3], address[4], address[5], address[6],
                address[7]);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!IeeeAddress.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final IeeeAddress other = (IeeeAddress) obj;
        for (int cnt = 0; cnt < 8; cnt++) {
            if (other.getValue()[cnt] != address[cnt]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int cnt = 7; cnt >= 0; cnt--) {
            builder.append(String.format("%02X", address[cnt]));
        }

        return builder.toString();
    }

    @Override
    public int compareTo(IeeeAddress other) {
        if (other == null) {
            return -1;
        }
        if (!IeeeAddress.class.isAssignableFrom(other.getClass())) {
            return -1;
        }
        for (int cnt = 0; cnt < 8; cnt++) {
            if (other.getValue()[cnt] == address[cnt]) {
                continue;
            }

            return other.getValue()[cnt] < address[cnt] ? 1 : -1;
        }
        return 0;
    }

    private void setAddress(long longVal) {
        this.address[0] = (int) (longVal & 0xff);
        this.address[1] = (int) ((longVal >> 8) & 0xff);
        this.address[2] = (int) ((longVal >> 16) & 0xff);
        this.address[3] = (int) ((longVal >> 24) & 0xff);
        this.address[4] = (int) ((longVal >> 32) & 0xff);
        this.address[5] = (int) ((longVal >> 40) & 0xff);
        this.address[6] = (int) ((longVal >> 48) & 0xff);
        this.address[7] = (int) ((longVal >> 56) & 0xff);

    }
}
