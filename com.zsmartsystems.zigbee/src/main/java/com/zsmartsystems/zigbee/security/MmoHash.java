/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ZigBee Cryptographic Hash Function, described in ZigBee specification sections B.1.3 and B.6.
 * <p>
 * This is a Matyas-Meyer-Oseas hash function using the AES-128 cipher. Input may be any length.
 * <p>
 * MMO Hash function is used for generating link keys from install codes for ZigBee 3 and Smart Energy.
 *
 * @author Chris Jackson
 *
 */
public class MmoHash {
    private final Logger logger = LoggerFactory.getLogger(MmoHash.class);

    /**
     * Internal hash.
     * Using a byte array internally as it's required by the cryptographic methods
     */
    private byte[] hash;

    private static final int ZIGBEE_BLOCKSIZE = 16;
    private static final String AES = "AES";

    /**
     * Creates a hash of the data using the Matyas-Meyer-Oseas hash function
     *
     * @param input array to create hash from
     */
    public MmoHash(int[] input) {
        hash = new byte[ZIGBEE_BLOCKSIZE];

        updateHash(input);
    }

    /**
     * Creates a hash of the data using the Matyas-Meyer-Oseas hash function
     *
     * @param input array to create hash from
     */
    public MmoHash(String input) {
        hash = new byte[ZIGBEE_BLOCKSIZE];

        updateHash(input);
    }

    /**
     * Update the hash with the input data
     *
     * @param input string of data to update with
     */
    public void updateHash(String input) {
        String hexString = input.replaceAll("[:\\- ]", "");

        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("Code string must contain an even number of hexadecimal numbers");
        }

        int[] code = new int[hexString.length() / 2];
        char enc[] = hexString.toCharArray();
        for (int i = 0; i < enc.length; i += 2) {
            StringBuilder curr = new StringBuilder(2);
            curr.append(enc[i]).append(enc[i + 1]);
            code[i / 2] = Integer.parseInt(curr.toString(), 16);
        }

        updateHash(code);
    }

    /**
     * Update the hash with the input data
     *
     * @param input array of data to update with
     */
    public void updateHash(int[] input) {
        byte[] output = new byte[ZIGBEE_BLOCKSIZE];
        byte[] cipher_in = new byte[ZIGBEE_BLOCKSIZE];

        int inputCnt = 0;
        int outputCnt = 0;
        while (inputCnt < input.length) {
            cipher_in[outputCnt++] = (byte) input[inputCnt++];

            if (outputCnt >= ZIGBEE_BLOCKSIZE) {
                // End of the block
                output = doBlock(output, cipher_in);

                outputCnt = 0;
            }
        }

        cipher_in[outputCnt++] = (byte) 0x80;

        // Pad with 0 until we're 16 bits (2 bytes) from the end
        while (outputCnt != ZIGBEE_BLOCKSIZE - 2) {
            if (outputCnt >= ZIGBEE_BLOCKSIZE) {
                // End of the block
                output = doBlock(output, cipher_in);

                outputCnt = 0;
            }
            cipher_in[outputCnt++] = 0x00;
        }

        // Add the 'n'-bit representation of 'l' to the end of the block.
        cipher_in[outputCnt++] = (byte) ((input.length * 8) >> 8);
        cipher_in[outputCnt] = (byte) ((input.length * 8) >> 0);

        hash = doBlock(output, cipher_in);
    }

    /**
     * Gets the current value of the hash
     *
     * @return integer array containing the hash value
     */
    public int[] getHash() {
        int[] outArray = new int[ZIGBEE_BLOCKSIZE];

        // Copy back to an int array
        for (int cnt = 0; cnt < ZIGBEE_BLOCKSIZE; cnt++) {
            outArray[cnt] = hash[cnt] & 0xFF;
        }

        return outArray;
    }

    private byte[] doBlock(byte[] keyBytes, byte[] inBytes) {
        try {
            Cipher cipher;
            Key key = new SecretKeySpec(keyBytes, AES);
            cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] output = cipher.doFinal(inBytes, 0, ZIGBEE_BLOCKSIZE);

            for (int cnt = 0; cnt < ZIGBEE_BLOCKSIZE; cnt++) {
                output[cnt] ^= inBytes[cnt];
            }

            return output;
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException e) {
            logger.error("Error hashing MMO block", e);
        }

        return new byte[ZIGBEE_BLOCKSIZE];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        int cntDelim = 0;
        for (int cnt = 0; cnt < hash.length; cnt++) {
            int value = hash[cnt];
            if (cntDelim++ == 2) {
                builder.append(':');
                cntDelim = 1;
            }
            builder.append(String.format("%02X", (value & 0xFF)));
        }

        return builder.toString();
    }
}
