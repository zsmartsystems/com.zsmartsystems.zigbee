/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
/*
   Copyright 2008-2013 Andrew Rapp, http://code.google.com/p/xbee-api/

   Copyright 2008-2013 ITACA-TSB, http://www.tsb.upv.es/
   Instituto Tecnologico de Aplicaciones de Comunicacion
   Avanzadas - Grupo Tecnologias para la Salud y el
   Bienestar (TSB)


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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:andrew.rapp@gmail.com">Andrew Rapp</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author Chris Jackson
 */
public class ByteUtils {

    private final static Logger logger = LoggerFactory.getLogger(ByteUtils.class);

    private ByteUtils() {
    }

    /**
     * There is a slight problem with this method that you might have noticed; a Java int is signed, so we can't make
     * use of the 32nd bit. This means we this method does not support a four byte value with msb greater than 01111111
     * ((2^7-1) or 127).
     * <p/>
     * TODO use long instead of int to support 4 bytes values. note that long assignments are not atomic.
     */
    public static int convertMultiByteToInt(int[] bytes) {

        if (bytes.length > 4) {
            throw new NumberFormatException("Value too big");
        } else if (bytes.length == 4 && ((bytes[0] & 0x80) == 0x80)) {
            // 0x80 == 10000000, 0x7e == 01111111
            throw new NumberFormatException("Java int can't support a four byte value, with msb byte greater than 7e");
        }

        int val = 0;

        for (int i = 0; i < bytes.length; i++) {

            if (bytes[i] > 0xFF) {
                throw new NumberFormatException("Values exceeds byte range: " + bytes[i]);
            }

            if (i == (bytes.length - 1)) {
                val += bytes[i];
            } else {
                val += bytes[i] << ((bytes.length - i - 1) * 8);
            }
        }

        return val;
    }

    public static long convertMultiByteToLong(byte[] bytes) {

        if (bytes.length > 8) {
            throw new IllegalArgumentException("too many bytes can't be converted to long");
        } else if (bytes.length == 8 && ((bytes[0] & 0x80) == 0x80)) {
            // 0x80 == 10000000, 0x7e == 01111111
            throw new NumberFormatException("Java long can't support a 8 bytes value, where msb byte greater than 7e");
        }

        long val = 0;

        for (int i = 0; i < bytes.length; i++) {
            val += 0x000000FF & bytes[i];
            val = val << 8;
        }

        return val;
    }

    public static int[] convertLongtoMultiByte(long val) {

        int size;

        if ((val >> 56) > 0) {
            size = 8;
        } else if ((val >> 48) > 0) {
            size = 7;
        } else if ((val >> 40) > 0) {
            size = 6;
        } else if ((val >> 32) > 0) {
            size = 5;
        } else if ((val >> 24) > 0) {
            size = 4;
        } else if ((val >> 16) > 0) {
            size = 3;
        } else if ((val >> 8) > 0) {
            size = 2;
        } else {
            size = 1;
        }

        int[] data = new int[size];

        for (int i = 0; i < size; i++) {
            data[i] = (int) ((val >> (size - i - 1) * 8) & 0xFF);
        }

        return data;
    }

    public final static String toBase16(final int[] arr) {
        return toBase16(arr, 0, arr.length);
    }

    private final static String toBase16(final int[] arr, final int start, final int end) {

        StringBuffer sb = new StringBuffer();

        for (int i = start; i < end; i++) {
            sb.append(toBase16(arr[i]));

            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    public static String toBase16(byte[] arr) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < arr.length; i++) {
            sb.append(toBase16(arr[i]));
            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    private static String padBase2(String s) {

        for (int i = s.length(); i < 8; i++) {
            s = "0" + s;
        }

        return s;
    }

    /**
     * @param b the int value to check if it contains a byte representable value
     * @return true if the value of the int could be expressed with 8 bits
     */
    public static boolean isByteValue(int b) {
        final boolean valid = ((b & 0xffffff00) == 0 || (b & 0xffffff00) == 0xffffff00);
        if (logger.isTraceEnabled() && valid && (b < -128 || b > 127)) {
            logger.trace(
                    "The value {} ({}) is considered a byte because only the 8 least significant bits are set"
                            + ", but its value is outside signed byte that is between -128 and 127",
                    b, Integer.toHexString(b));
        }
        return valid;
    }

    public static String toBase16(int b) {
        if (!isByteValue(b)) {
            throw new IllegalArgumentException(
                    "Error converting " + b + " input value to hex string it is larger than a byte");
        }
        if (b < 0) {
            return Integer.toHexString(b).substring(6).toUpperCase();
        } else if (b < 0x10) {
            return "0" + Integer.toHexString(b).toUpperCase();
        } else {
            return Integer.toHexString(b).toUpperCase();
        }
    }

    private static String toBase2(int b) {

        if (b > 0xff) {
            throw new IllegalArgumentException("input value is larger than a byte");
        }

        return padBase2(Integer.toBinaryString(b));
    }

    public static String formatByte(int b) {
        return "base10=" + Integer.toString(b) + ",base16=" + toBase16(b) + ",base2=" + toBase2(b);
    }

}
