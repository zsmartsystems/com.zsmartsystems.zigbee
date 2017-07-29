package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeEnqueueSendDataResponseTest {
    @Test
    public void doRequest() {
        ConBeeEnqueueSendDataResponse response = new ConBeeEnqueueSendDataResponse(
                new int[] { 0x12, 0x0D, 0x00, 0x09, 0x00, 0x02, 0x00, 0x22, 0x00, 0xB4, 0xFF });
        System.out.print(response);

        assertEquals(13, response.getSequence());
        assertEquals(ConBeeStatus.SUCCESS, response.getStatus());
        assertEquals(ConBeeNetworkState.NET_CONNECTED, response.getDeviceState().getNetworkState());
        assertFalse(response.getDeviceState().isDataConfirm());
        assertFalse(response.getDeviceState().isDataIndication());
        assertFalse(response.getDeviceState().isDataRequest());
        assertFalse(response.getDeviceState().isConfigChanged());
    }
}
