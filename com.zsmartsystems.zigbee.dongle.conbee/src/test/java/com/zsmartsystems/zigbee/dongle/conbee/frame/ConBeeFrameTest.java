package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeFrameTest {
    @Test
    public void testCreateDeviceStateResponse() {
        ConBeeFrame frame = ConBeeFrame
                .create(new int[] { 0x07, 0x00, 0x00, 0x08, 0x00, 0xA2, 0x00, 0x00, 0x4F, 0xFF });
        System.out.println(frame);

        assertNotNull(frame);
        assertTrue(frame instanceof ConBeeDeviceStateResponse);
        assertEquals(0, frame.getSequence());
    }

    @Test
    public void testCreateEnqueueSendDataResponse() {
        ConBeeFrame frame = ConBeeFrame
                .create(new int[] { 0x12, 0x0D, 0x00, 0x09, 0x00, 0x02, 0x00, 0x22, 0x00, 0xB4, 0xFF });
        System.out.println(frame);

        assertNotNull(frame);
        assertTrue(frame instanceof ConBeeEnqueueSendDataResponse);
        assertEquals(13, frame.getSequence());
    }

    @Test
    public void testCreateUnknownResponse() {
        // This appears to be a valid frame, but undocumented command type!
        ConBeeFrame frame = ConBeeFrame.create(new int[] { 0x0E, 0x0E, 0x00, 0x07, 0x00, 0xA6, 0x00, 0x37, 0xFF });

        assertNull(frame);
    }

}
