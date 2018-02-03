/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ash.AshFrameData;

public class AshFrameTest {

    @Test
    public void TestPacket_DataStuffed() {
        int[] buffer = new int[] { 0x7d, 0x3a, 0x43, 0xa1, 0xfa, 0x54, 0x0a, 0x15, 0xc9, 0x89 };

        final AshFrame packet = AshFrame.createFromInput(buffer);
        assertNotNull(packet);
        assertTrue(packet instanceof AshFrameData);

        AshFrameData dataPacket = (AshFrameData) packet;
        assertEquals(1, dataPacket.getFrmNum());
        assertEquals(2, dataPacket.getAckNum());
        assertEquals(true, dataPacket.getReTx());
    }

}
