/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeEvent;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeOtaFirmwareUpdateStatusEvent;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.XBeeReceivePacketExplicitEvent;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeEventFactoryTest {
    protected int[] getPacketData(String stringData) {
        String split[] = stringData.split(" ");

        int[] response = new int[split.length];
        int cnt = 0;
        for (String val : split) {
            response[cnt++] = Integer.parseInt(val, 16);
        }

        return response;
    }

    @Test
    public void testBootloaderEvent() {
        int[] data = getPacketData("00 16 A0 00 13 A2 00 41 62 F6 1A 00 00 01 40 00 00 00 00 00 00 00 FF FF B8");
        XBeeEvent frame = XBeeEventFactory.getXBeeFrame(data);
        assertTrue(frame instanceof XBeeOtaFirmwareUpdateStatusEvent);
        System.out.println(frame);

        XBeeOtaFirmwareUpdateStatusEvent event = (XBeeOtaFirmwareUpdateStatusEvent) frame;
        assertEquals(Integer.valueOf(0), event.getBlockNumber());
        assertEquals(new IeeeAddress("0013A2004162F61A"), event.getIeeeAddress());
        assertEquals(Integer.valueOf(0), event.getNetworkAddress());

    }

    @Test
    public void testGetEvent() {
        int[] data = getPacketData(
                "00 1A 91 00 17 88 01 02 13 65 36 F7 7B 02 01 00 01 01 04 41 18 7C 01 21 00 00 20 C8 C4");
        XBeeEvent frame = XBeeEventFactory.getXBeeFrame(data);
        assertTrue(frame instanceof XBeeReceivePacketExplicitEvent);
        System.out.println(frame);

        XBeeReceivePacketExplicitEvent event = (XBeeReceivePacketExplicitEvent) frame;
        assertEquals(Integer.valueOf(1), event.getClusterId());
        assertEquals(Integer.valueOf(1), event.getDestinationEndpoint());
        assertEquals(Integer.valueOf(63355), event.getNetworkAddress());
        assertEquals(Integer.valueOf(0x104), event.getProfileId());
    }

}
