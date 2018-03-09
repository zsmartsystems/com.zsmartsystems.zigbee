/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoIeeeAddress;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZDO_IEEE_ADDR_RSP_Test extends Cc2351TestPacket {

    @Test
    public void testReceive1() {
        ZToolPacket data = getPacket("FE 11 45 81 00 14 D4 F1 02 00 4B 12 00 00 00 00 02 8F 22 2A 2F 15");

        ZigBeeApsFrame apsFrame = ZdoIeeeAddress.create(data);

        assertEquals(0x0000, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(getPacketData("00 00 14 D4 F1 02 00 4B 12 00 00 00 02 00 8F 22 2A 2F"),
                apsFrame.getPayload()));
    }

    @Test
    public void testReceive2() {
        ZToolPacket data = getPacket("FE 0F 45 81 00 86 06 00 00 00 EE 1F 00 21 A4 00 01 00 00 3E");

        ZigBeeApsFrame apsFrame = ZdoIeeeAddress.create(data);

        assertEquals(42017, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(
                Arrays.equals(getPacketData("00 00 86 06 00 00 00 EE 1F 00 21 A4 01 00 00 00"), apsFrame.getPayload()));
    }

}
