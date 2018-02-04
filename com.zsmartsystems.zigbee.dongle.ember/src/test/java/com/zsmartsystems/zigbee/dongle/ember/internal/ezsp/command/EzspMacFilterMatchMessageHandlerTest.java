/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspMacFilterMatchMessageHandlerTest extends EzspFrameTest {
    @Test
    public void testReceive() {
        EzspFrame.setEzspVersion(4);

        EzspMacFilterMatchMessageHandler response = new EzspMacFilterMatchMessageHandler(getPacketData(
                "0F 90 46 01 80 C1 B8 21 01 C8 4A FF FF FF FF 9C 05 23 3F FF E7 0F 00 FF FF 0B 00 0B 00 10 5E C0 11 00 00 20 02 5C 37 02 12"));

        assertEquals(true, response.isResponse());
        assertEquals(EzspMacFilterMatchMessageHandler.FRAME_ID, response.getFrameId());
        assertEquals(1, response.getFilterIndexMatch());
        assertEquals(193, response.getLastHopLqi());
        assertEquals(-72, response.getLastHopRssi());
        assertTrue(Arrays.equals(new int[] { 0x01, 0xC8, 0x4A, 0xFF, 0xFF, 0xFF, 0xFF, 0x9C, 0x05, 0x23, 0x3F, 0xFF,
                0xE7, 0x0F, 0x00, 0xFF, 0xFF, 0x0B, 0x00, 0x0B, 0x00, 0x10, 0x5E, 0xC0, 0x11, 0x00, 0x00, 0x20, 0x02,
                0x5C, 0x37, 0x02, 0x12 }, response.getMessageContents()));
    }
}
