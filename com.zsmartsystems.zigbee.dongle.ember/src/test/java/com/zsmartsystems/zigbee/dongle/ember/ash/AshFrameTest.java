package com.zsmartsystems.zigbee.dongle.ember.ash;

import static org.junit.Assert.*;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameData;

public class AshFrameTest {

    @Test
    public void TestPacket_DataStuffed() {
        int[] buffer = new int[] { 0x7d, 0x3a, 0x43, 0xa1, 0xfa, 0x54, 0x0a, 0x15, 0xc9, 0x89 };

        final AshFrame packet = AshFrame.createFromInput(buffer, buffer.length);
        assertNotNull(packet);
        assertTrue(packet instanceof AshFrameData);

        AshFrameData dataPacket = (AshFrameData) packet;
        assertEquals(1, dataPacket.getFrmNum());
        assertEquals(2, dataPacket.getAckNum());
        assertEquals(true, dataPacket.getReTx());
    }

}
