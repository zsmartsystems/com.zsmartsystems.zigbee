/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ash;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

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

    @Test
    public void testLoopback() {
        AshFrame outFrame = new AshFrameData(new int[] { 0x18, 0x00, 0xFF, 0x00, 0x34, 0x00, 0x87, 0x36, 0x00, 0x00,
                0x21, 0x00, 0x00, 0x00, 0x40, 0x11, 0x00, 0x00, 0x18, 0x18, 0x16, 0x00, 0xA5, 0xFE, 0x09, 0x01, 0x00,
                0x5B, 0xFD, 0x24, 0x01, 0x0F, 0x00, 0x03, 0x90, 0x9D, 0x38, 0xFE, 0xFF, 0x57, 0x0B, 0x00, 0x01 });

        outFrame.setFrmNum(5);
        outFrame.setAckNum(0);
        int[] outBuffer = outFrame.getOutputBuffer();

        // Remove the end flag
        outBuffer = Arrays.copyOfRange(outBuffer, 0, outBuffer.length - 1);

        AshFrame inFrame = AshFrame.createFromInput(outBuffer);

        assertEquals(5, inFrame.getFrmNum());
        assertEquals(0, inFrame.getAckNum());
    }

}
