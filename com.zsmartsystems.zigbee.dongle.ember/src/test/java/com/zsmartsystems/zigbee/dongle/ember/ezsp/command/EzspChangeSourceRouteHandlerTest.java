/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspChangeSourceRouteHandler;

/**
 * @author Chris Jackson
 */
public class EzspChangeSourceRouteHandlerTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspFrame.setEzspVersion(4);
        EzspChangeSourceRouteHandler incomingMessageHandler = new EzspChangeSourceRouteHandler(
                getPacketData("1A 90 FF 00 C4 CA 09 00 00 01"));
        System.out.println(incomingMessageHandler);

        assertEquals(0xC4, incomingMessageHandler.getFrameId());
        assertTrue(incomingMessageHandler.isResponse());
        assertEquals(2506, incomingMessageHandler.getNewChildId());
        assertEquals(0, incomingMessageHandler.getNewParentId());
        assertEquals(true, incomingMessageHandler.getOurChild());
    }

}
