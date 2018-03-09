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
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoSimpleDescriptor;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZDO_SIMPLE_DESC_RSP_Test extends Cc2351TestPacket {

    @Test
    public void testReceive1() {
        ZToolPacket data = getPacket(
                "FE 2E 45 84 00 00 00 00 00 28 01 04 01 00 00 00 00 10 00 00 01 00 02 00 03 00 04 00 05 00 06 00 07 00 08 00 09 00 0F 00 0A 00 0C 00 15 00 00 01 01 01 CF");

        ZigBeeApsFrame apsFrame = ZdoSimpleDescriptor.create(data);

        assertEquals(0x0000, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(
                getPacketData(
                        "00 00 00 00 28 01 04 01 00 00 00 00 10 00 00 01 00 02 00 03 00 04 00 05 00 06 00 07 00 08 00 09 00 0F 00 0A 00 0C 00 15 00 00 01 01 01"),
                apsFrame.getPayload()));
    }

    @Test
    public void testReceive2() {
        ZToolPacket data = getPacket(
                "FE 18 45 84 21 A4 00 21 A4 12 02 04 01 01 00 00 02 00 00 03 00 03 05 00 06 00 08 00 C4");

        ZigBeeApsFrame apsFrame = ZdoSimpleDescriptor.create(data);

        assertEquals(42017, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(getPacketData("A4 00 21 A4 12 02 04 01 01 00 00 02 00 00 03 00 03 05 00 06 00 08 00"),
                apsFrame.getPayload()));
    }

}
