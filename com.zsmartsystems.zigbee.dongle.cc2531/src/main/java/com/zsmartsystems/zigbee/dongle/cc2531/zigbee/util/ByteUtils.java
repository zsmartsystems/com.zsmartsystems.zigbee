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
            throw new RuntimeException("too big");
        } else if (bytes.length == 4 && ((bytes[0] & 0x80) == 0x80)) {
            // 0x80 == 10000000, 0x7e == 01111111
            throw new RuntimeException("Java int can't support a four byte value, with msb byte greater than 7e");
        }

        int val = 0;

        for (int i = 0; i < bytes.length; i++) {

            if (bytes[i] > 0xFF) {
                throw new RuntimeException("Values exceeds byte range: " + bytes[i]);
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
            throw new RuntimeException("Java long can't support a 8 bytes value, where msb byte greater than 7e");
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

    public final static String toBase16(final int[] arr, final int start, final int end) {

        StringBuffer sb = new StringBuffer();

        for (int i = start; i < end; i++) {
            sb.append(toBase16(arr[i]));

            if (i < arr.length - 1) {
                sb.append(" ");
            }
        }

        return sb.toString();
    }

    /**
     * @param bytes the String representing the bytes in hex form
     * @return the formatted bytes
     * @since 0.6.0 - Revision 60
     */
    public static int[] fromBase16toIntArray(String bytes) {
        final String PATTERN = "\\s*((0x[0-9a-f]{2}|[0-9a-f]{2})\\s*)+";
        bytes = bytes.toLowerCase();
        if (!bytes.matches(PATTERN)) {
            throw new IllegalArgumentException("Unable to parse " + bytes + " doesn't match regex " + PATTERN);
        }
        String[] singleBytes = bytes.split("\\s+");
        String item;
        int[] values = new int[singleBytes.length];
        for (int i = 0; i < singleBytes.length; i++) {
            item = singleBytes[i];
            if (item.length() == 0) {
                continue;
            }

            if (item.startsWith("0x")) {
                item = item.substring(2);
            }

            values[i] = (Integer.parseInt(item, 16) & 0xFF);
        }
        return values;
    }

    /**
     * This method return a <code>byte[]</code> from a <code>String</code>. It support the format<br>
     * of the {@link #toBase16(byte[])} and in general it supports the following {@link java.util.regex.Pattern}:<br>
     *
     * <pre>
     * \s*((0x[0-9a-f]{2}|[0-9a-f]{2})\s*)+
     * </pre>
     *
     * <b>Exmaple:</b>
     *
     * <pre>
     * 0x23 0xab 0xfE 0xDD
     * 0x23 0xab 0xfe 0xdd
     * 0x23 ab 0xfE DD
     * </pre>
     *
     * <b>NOTE</b><br>
     * The main difference with {@link #fromBase16toIntArray(String)} is that the data returned<br>
     * is signed, so values goes from <code>-128 to 127</code>
     *
     * @param bytes the String representing the bytes in hex form
     * @return the formatted bytes
     * @since 0.6.0 - Revision 60
     */
    public static byte[] fromBase16toByteArray(String bytes) {
        int[] values = fromBase16toIntArray(bytes);
        byte[] returns = new byte[values.length];
        for (int i = 0; i < values.length; i++) {
            returns[i] = (byte) (values[i] & 0xFF);
        }
        return returns;
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
     * Returns true if the bit is on (1) at the specified position
     * Position range: 1-8
     */
    public static boolean getBit(int b, int position) {

        if (position < 1 || position > 8) {
            throw new IllegalArgumentException("Position is out of range");
        }

        if (b > 0xff) {
            throw new IllegalArgumentException("input value is larger than a byte");
        }

        if (((b >> (--position)) & 0x1) == 0x1) {
            return true;
        }

        return false;
    }

    /**
     * @param b the int value to check if it contains a byte representable value
     * @return true if the value of the int could be expressed with 8 bits
     * @since 0.8.0
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
        } else if (b >= 0x10) {
            return Integer.toHexString(b).toUpperCase();
        } else {
            throw new IllegalArgumentException("Unable to recognize the value " + b);
        }
    }

    public static String toBase2(int b) {

        if (b > 0xff) {
            throw new IllegalArgumentException("input value is larger than a byte");
        }

        return padBase2(Integer.toBinaryString(b));
    }

    public static String formatByte(int b) {
        return "base10=" + Integer.toString(b) + ",base16=" + toBase16(b) + ",base2=" + toBase2(b);
    }

    public static byte unsignedByteFromInt(int i) {
        return (byte) (i > 127 ? i - 256 : i); // This is an explicit form of the conversion java performs anyway
    }

    public static int intFromUnsignedByte(byte b) {
        return (b < 0 ? b + 256 : b);
    }
}
