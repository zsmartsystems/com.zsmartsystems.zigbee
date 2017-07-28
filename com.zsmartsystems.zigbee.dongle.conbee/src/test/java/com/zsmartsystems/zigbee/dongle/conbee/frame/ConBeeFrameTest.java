package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeFrameTest {
    @Test
    public void testCreate() {
        ConBeeFrame frame = ConBeeFrame
                .create(new int[] { 0x07, 0x00, 0x00, 0x08, 0x00, 0xA2, 0x00, 0x00, 0x4F, 0xFF });

        assertNotNull(frame);
        assertTrue(frame instanceof ConBeeDeviceStateResponse);
        assertEquals(0, frame.getSequence());
    }
}
