/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531.network.packet.af;

import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolCMD;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.DoubleByte;
import com.zsmartsystems.zigbee.dongle.cc2531.zigbee.util.Integers;

/**
 * AF_DATA_REQUEST_EXT message implementation according to Texas Instruments CC2530-ZNP specification.
 */
public class AF_DATA_REQUEST_EXT extends ZToolPacket {

    public AF_DATA_REQUEST_EXT(int groupdId, short srcEndPoint, int j, int k, byte bitmapOpt, byte radius,
            int[] payload) {

        if (payload.length > 230) {
            throw new IllegalArgumentException("Payload is too big, maxium is 230");
        }

        int[] framedata = new int[payload.length + 20];
        framedata[0] = 0x01; // Destination address mode 1 (group addressing)
        framedata[1] = Integers.getByteAsInteger(groupdId, 0); // Source address
        framedata[2] = Integers.getByteAsInteger(groupdId, 1); // Source address
        framedata[3] = 0x00; // Source address
        framedata[4] = 0x00; // Source address
        framedata[5] = 0x00; // Source address
        framedata[6] = 0x00; // Source address
        framedata[7] = 0x00; // Source address
        framedata[8] = 0x00; // Source address
        framedata[9] = 0x00; // Destination Endpoint
        framedata[10] = 0x00; // Destination PAN ID
        framedata[11] = 0x00; // Destination PAN ID
        framedata[12] = srcEndPoint & 0xFF;
        framedata[13] = Integers.getByteAsInteger(j, 0);
        framedata[14] = Integers.getByteAsInteger(j, 1);
        framedata[15] = k & 0xFF;
        framedata[16] = bitmapOpt & 0xFF;
        framedata[17] = radius & 0xFF;
        framedata[18] = Integers.getByteAsInteger(payload.length, 0);
        framedata[19] = Integers.getByteAsInteger(payload.length, 1);
        for (int i = 0; i < payload.length; i++) {
            framedata[20 + i] = payload[i];
        }
        super.buildPacket(new DoubleByte(ZToolCMD.AF_DATA_REQUEST_EXT), framedata);
    }

}
