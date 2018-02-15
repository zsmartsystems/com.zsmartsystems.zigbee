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

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;

/**
 * @author Chris Jackson
 */
public class EzspIncomingRouteRecordHandlerTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspFrame.setEzspVersion(4);
        EzspIncomingRouteRecordHandler incomingMessageHandler = new EzspIncomingRouteRecordHandler(
                getPacketData("90 90 59 82 A2 53 C1 01 02 01 88 17 00 FF B0 02 0F 47 6B 82"));
        System.out.println(incomingMessageHandler);
        assertEquals(0x59, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(2, incomingMessageHandler.getRelayList().length);
        assertEquals(0x470F, incomingMessageHandler.getRelayList()[0]);
        assertEquals(0x826B, incomingMessageHandler.getRelayList()[1]);
        assertEquals(255, incomingMessageHandler.getLastHopLqi());
        assertEquals(-80, incomingMessageHandler.getLastHopRssi());
    }

}
