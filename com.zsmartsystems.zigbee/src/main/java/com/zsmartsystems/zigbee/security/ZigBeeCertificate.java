/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;

/**
 * Provides a base implementation of the {@link ZigBeeCertificate}
 *
 * @author Chris Jackson
 *
 */
public abstract class ZigBeeCertificate {
    protected ZigBeeCryptoSuites cryptoSuite;

    /**
     * Gets the {@link ZigBeeCryptoSuites} that is applicable for this certificate
     *
     * @return the {@link ZigBeeCryptoSuites}
     */
    public ZigBeeCryptoSuites getCryptoSuite() {
        return cryptoSuite;
    }

    abstract public IeeeAddress getIssuer();

    abstract public IeeeAddress getSubject();

    /**
     * Converts a hex string array into an integer array
     *
     * @param hexString a string of data to convert to an integer array
     * @return the integer array representation of the string array
     */
    protected int[] hexStringToIntArray(String hexString) {
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException(
                    "Certificate string '" + hexString + "' must be even number of bytes long.");
        }

        int[] output = new int[hexString.length() / 2];
        char enc[] = hexString.toCharArray();
        for (int i = 0; i < enc.length - 1; i += 2) {
            StringBuilder curr = new StringBuilder(2);
            curr.append(enc[i]).append(enc[i + 1]);
            output[i / 2] = Integer.parseInt(curr.toString(), 16);
        }

        return output;
    }

    protected int[] reverseCopy(int[] input) {
        int[] output = new int[input.length];

        int inCnt = 0;
        for (int outCnt = input.length - 1; outCnt >= 0; outCnt--) {
            output[outCnt] = input[inCnt];
            inCnt++;
        }
        return output;
    }

    protected void appendArray(StringBuilder builder, int[] array) {
        boolean first = true;
        for (int val : array) {
            if (!first) {
                builder.append(' ');
            }
            first = false;
            builder.append(String.format("%02X", val));
        }
    }

    /**
     * Returns a {@link ByteArray} containing the certificate data
     *
     * @return a {@link ByteArray} containing the certificate data
     */
    public abstract ByteArray getByteArray();
}
