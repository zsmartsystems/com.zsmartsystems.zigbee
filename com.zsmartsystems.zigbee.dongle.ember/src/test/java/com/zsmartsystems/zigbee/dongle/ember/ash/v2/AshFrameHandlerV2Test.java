package com.zsmartsystems.zigbee.dongle.ember.ash.v2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrame;
import com.zsmartsystems.zigbee.dongle.ember.ash.AshFrameData;

/**
 *
 * @author Chris Jackson
 *
 */
public class AshFrameHandlerV2Test {
    @Test
    public void TestPacket_DataStuffed() {
        int[] buffer = new int[] { 0x7d, 0x3a, 0x43, 0xa1, 0xfa, 0x54, 0x0a, 0x15, 0xc9, 0x89 };

        AshFrameHandlerV2 frameHandler = new AshFrameHandlerV2(null);
        final AshFrame packet = frameHandler.createAshFrame(buffer);
        // final AshFrame packet = AshFrame.createFromInput(buffer);
        assertNotNull(packet);
        assertTrue(packet instanceof AshFrameData);

        AshFrameData dataPacket = (AshFrameData) packet;
        assertEquals(1, dataPacket.getFrmNum());
        assertEquals(2, dataPacket.getAckNum());
        assertEquals(true, dataPacket.getReTx());
    }
}
