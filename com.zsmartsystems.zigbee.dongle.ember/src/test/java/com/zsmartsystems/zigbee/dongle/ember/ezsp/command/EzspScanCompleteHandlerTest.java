/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspScanCompleteHandlerTest extends EzspFrameTest {
    @Test
    public void testReceive() {
        EzspFrame.setEzspVersion(4);

        EzspScanCompleteHandler handler = new EzspScanCompleteHandler(getPacketData("4E 90 FF 00 1C FF 00"));
        System.out.println(handler);

        assertEquals(0x1C, handler.getFrameId());
        assertTrue(handler.isResponse());
        assertEquals(255, handler.getChannel());
        assertEquals(EmberStatus.EMBER_SUCCESS, handler.getStatus());
    }

}
