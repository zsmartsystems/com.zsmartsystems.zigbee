/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Ignore;
import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * @author Chris Jackson
 */
@Ignore
public class EzspGpepIncomingMessageHandlerTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
//        EzspFrame.setEzspVersion(4);
//        EzspGpepIncomingMessageHandler incomingMessageHandler = new EzspGpepIncomingMessageHandler(
//                getPacketData(
//                        "90 01 C5 7F D7 54 00 14 8A 70 01 14 8A 70 01 54 02 01 00 00 54 07 00 00 65 2C 15 E8 75 FF 00"));
//
//        System.out.println(incomingMessageHandler);
//
//        assertEquals(0xC5, incomingMessageHandler.getFrameId());
//        assertFalse(incomingMessageHandler.isResponse());
//        assertEquals(EmberStatus.EMBER_UNPROCESSED, incomingMessageHandler.getStatus());
    }
}
