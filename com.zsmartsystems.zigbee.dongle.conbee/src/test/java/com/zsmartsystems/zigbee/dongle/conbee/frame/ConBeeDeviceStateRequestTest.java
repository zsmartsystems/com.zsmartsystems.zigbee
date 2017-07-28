package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeDeviceStateRequestTest {
    @Test
    public void doRequest() {
        ConBeeDeviceStateRequest request = new ConBeeDeviceStateRequest();
        request.setSequence(0);
        assertTrue(Arrays.equals(new int[] { 0x07, 0x00, 0x00, 0x08, 0x00, 0x00, 0x00, 0x00, 0xF1, 0xFF },
                request.getOutputBuffer()));
    }
}
