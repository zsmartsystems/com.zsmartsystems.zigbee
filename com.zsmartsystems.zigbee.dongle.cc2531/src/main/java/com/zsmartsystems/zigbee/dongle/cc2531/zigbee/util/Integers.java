/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 CNR-ISTI, http://isti.cnr.it
   Institute of Information Science and Technologies
   of the Italian National Research Council


   See the NOTICE file distributed with this work for additional
   information regarding copyright ownership

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/

package com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util;

/**
 * An utility class that contains method for:<br>
 * - creating <b>long</b>, <b>int</b>, and <b>short</b> from <code>int[]</code><br>
 * - extracting a byte configuration from a:<b>long</b>, <b>int</b>, and <b>short</b>
 *
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano "Kismet" Lenzi</a>
 */
public class Integers {
    private Integers() {
    }

    /**
     * @param x the long containing the data
     * @param n the index of the byte to return
     * @return return the n-th byte as int of the representation of x thus it returns always value between 0 and 255
     */
    public static final int getByteAsInteger(final long x, final int n) {
        switch (n) {
            case 0:
                return (int) (x & 0x00000000000000FFL);
            case 1:
                return (int) ((x & 0x000000000000FF00L) >> 8);
            case 2:
                return (int) ((x & 0x0000000000FF0000L) >> 16);
            case 3:
                return (int) ((x & 0x00000000FF000000L) >> 24);
            case 4:
                return (int) ((x & 0x000000FF00000000L) >> 32);
            case 5:
                return (int) ((x & 0x0000FF0000000000L) >> 40);
            case 6:
                return (int) ((x & 0x00FF000000000000L) >> 48);
            case 7:
                return (int) (((x & 0xFF00000000000000L) >> 56) & 0xFF);
            default:
                throw new IllegalArgumentException("long is reppresented as 8 bytes,"
                        + " hence value of n must be between 0 and 3: you have tryed to use " + n);
        }
    }

    /**
     * @param x the int containing the data
     * @param n the index of the byte to return
     * @return return the n-th byte as int of the representation of x thus it returns always value between 0 and 255
     * @since 0.2.0
     */
    public static final int getByteAsInteger(final int x, final int n) {
        switch (n) {
            case 0:
                return x & 0x000000FF;
            case 1:
                return (x & 0x0000FF00) >> 8;
            case 2:
                return (x & 0x00FF0000) >> 16;
            case 3:
                return (x & 0xFF000000) >> 24;
            default:
                throw new IllegalArgumentException("long is reppresented as 8 bytes,"
                        + " hence value of n must be between 0 and 3: you have tryed to use " + n);
        }
    }

    /**
     * @param x the short containing the data
     * @param n the index of the byte to return
     * @return return the n-th byte as int of the representation of x it returns always value between 0 and 255
     */
    public static final int getByteAsInteger(final short x, final int n) {
        switch (n) {
            case 0:
                return (x & 0x00FF);
            case 1:
                return ((x & 0xFF00) >> 8);
            default:
                throw new IllegalArgumentException("short is represented as 2 bytes,"
                        + " hence value of n must be between 0 and 1: you have tryed to use " + n);
        }
    }

    /**
     * @param values the int[] array containing the data
     * @param msb the index of the most significant byte
     * @param lsb the index of the least significant byte
     * @return the short decoded from the int[]
     */
    public static short shortFromInts(int[] values, int msb, int lsb) {
        int value = (values[msb] << 8) + values[lsb];
        return (short) value;
    }

    /**
     * @param values the byte[] array containing the data
     * @param msb the index of the most significant byte
     * @param lsb the index of the least significant byte
     * @return the long decoded from the byte[]
     */
    public static long longFromInts(byte[] values, int msb, int lsb) {
        long result = (values[msb] & 0xFF);
        if (msb < lsb) {
            for (int i = msb + 1; i <= lsb; i++) {
                result = (result << 8) + (values[i] & 0xFF);
            }
        } else {
            for (int i = msb - 1; i >= lsb; i--) {
                result = (result << 8) + (values[i] & 0xFF);
            }
        }
        return result;
    }

    /**
     * @param values the int[] array containing the data
     * @param msb the index of the most significant byte
     * @param lsb the index of the least significant byte
     * @return the long decoded from the int[]
     */
    public static long longFromInts(int[] values, int msb, int lsb) {
        long result = (values[msb] & 0xFF);
        if (msb < lsb) {
            for (int i = msb + 1; i <= lsb; i++) {
                result = (result << 8) + (values[i] & 0xFF);
            }
        } else {
            for (int i = msb - 1; i >= lsb; i--) {
                result = (result << 8) + (values[i] & 0xFF);
            }
        }
        return result;
    }
}
