/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspIncomingSenderEui64Handler;

/**
 * @author Chris Jackson
 */
public class EzspIncomingSenderEui64HandlerTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspFrame.setEzspVersion(4);
        EzspIncomingSenderEui64Handler incomingMessageHandler = new EzspIncomingSenderEui64Handler(
                getPacketData("1B 94 FF 00 62 E0 45 BE 0B 00 6F 0D 00"));
        System.out.println(incomingMessageHandler);

        assertEquals(0x62, incomingMessageHandler.getFrameId());
        assertEquals(new IeeeAddress("000D6F000BBE45E0"), incomingMessageHandler.getSenderEui64());
    }

}
