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

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Big Endian container for 64-bit XBee Address
 * <p/>
 * See device addressing in manual p.32
 *
 * @author <a href="mailto:andrew.rapp@gmail.com">Andrew Rapp</a>
 * @author <a href="mailto:alfiva@aaa.upv.es">Alvaro Fides Valero</a>
 * @author <a href="mailto:stefano.lenzi@isti.cnr.it">Stefano Lenzi</a>
 */
public class ZToolAddress64 extends ZToolAddress {

    // private final static Logger log = Logger.getLogger(ZToolAddress64.class);

    // broadcast address 0x000000ff
    public static final ZToolAddress64 BROADCAST = new ZToolAddress64(
            new byte[] { 0, 0, 0, 0, 0, 0, (byte) 0xff, (byte) 0xff });
    public static final ZToolAddress64 ZNET_COORDINATOR = new ZToolAddress64(new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 });

    private byte[] address;

    /**
     * Parses an 64-bit XBee address from a string representation
     * Must be in the format "## ## ## ## ## ## ## ##" (i.e. don't use 0x prefix)
     *
     * @param addressStr
     */
    public ZToolAddress64(String addressStr) {
        StringTokenizer st = new StringTokenizer(addressStr, " ");

        address = new byte[8];

        for (int i = 0; i < address.length; i++) {
            String byteStr = st.nextToken();
            address[i] = (byte) (Integer.parseInt(byteStr, 16) & 0xFF);

            // log.debug("byte is " + ByteUtils.toBase16(address[i]) + " at pos " + i);
        }
    }

    public ZToolAddress64(long ieee) {
        address = new byte[8];
        for (int i = address.length - 1; i >= 0; i--) {
            address[i] = (byte) ieee;
            ieee = ieee >> 8;
        }
    }

    /**
     * Creates a 64-bit address
     *
     * @param b1 MSB
     * @param b2
     * @param b3
     * @param b4
     * @param b5
     * @param b6
     * @param b7
     * @param b8 LSB
     */
    public ZToolAddress64(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        address = new byte[8];

        address[0] = b1;
        address[1] = b2;
        address[2] = b3;
        address[3] = b4;
        address[4] = b5;
        address[5] = b6;
        address[6] = b7;
        address[7] = b8;
    }

    public ZToolAddress64(byte[] address) {
        this.address = Arrays.copyOf(address, address.length);
    }

    public ZToolAddress64() {
        address = new byte[8];
    }

    public void setAddress(byte[] address) {
        this.address = Arrays.copyOf(address, address.length);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof ZToolAddress64) {
            ZToolAddress64 ieee = (ZToolAddress64) o;
            if (ieee.address == this.address) {
                return true;
            }
            for (int i = 0; i < this.address.length; i++) {
                if (ieee.address[i] != this.address[i]) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public long getLong() {
        return Integers.longFromInts(address, 0, 7);
    }

    @Override
    public byte[] getAddress() {
        return Arrays.copyOf(address, address.length);
    }
}
