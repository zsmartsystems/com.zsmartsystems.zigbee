/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspNetworkFoundHandlerTest extends EzspFrameTest {
    @Test
    public void testReceive() {
        EzspFrame.setEzspVersion(4);

        EzspNetworkFoundHandler handler = new EzspNetworkFoundHandler(
                getPacketData("56 90 FF 00 1B 11 8B D9 63 08 1D DC 2D C4 F2 46 00 02 01 FF C3"));
        System.out.println(handler);

        assertEquals(0x1B, handler.getFrameId());
        assertTrue(handler.isResponse());
        assertEquals(255, handler.getLastHopLqi());
        assertEquals(-61, handler.getLastHopRssi());
        assertEquals(false, handler.getNetworkFound().getAllowingJoin());
        assertEquals(2, handler.getNetworkFound().getStackProfile());
        assertEquals(1, handler.getNetworkFound().getNwkUpdateId());
        assertEquals(55691, handler.getNetworkFound().getPanId());
        assertEquals(new ExtendedPanId(new int[] { 0x63, 0x08, 0x1D, 0xDC, 0x2D, 0xC4, 0xF2, 0x46 }),
                handler.getNetworkFound().getExtendedPanId());
    }

}
