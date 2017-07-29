package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeDeviceStateResponseTest {
    @Test
    public void doRequest() {
        ConBeeDeviceStateResponse response = new ConBeeDeviceStateResponse(
                new int[] { 0x07, 0x00, 0x00, 0x08, 0x00, 0xA2, 0x00, 0x00, 0x4F, 0xFF });
        System.out.print(response);

        assertEquals(0, response.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, response.getStatus());
        assertEquals(ConBeeNetworkState.NET_CONNECTED, response.getDeviceState().getNetworkState());
        assertFalse(response.getDeviceState().isDataConfirm());
        assertFalse(response.getDeviceState().isDataIndication());
        assertTrue(response.getDeviceState().isDataRequest());
        assertFalse(response.getDeviceState().isConfigChanged());
    }
}
