package com.zsmartsystems.zigbee.dongle.conbee.frame;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ConBeeEnqueueSendDataRequestTest {
    @Test
    public void doRequest() {
        ConBeeEnqueueSendDataRequest request = new ConBeeEnqueueSendDataRequest();
        System.out.println(request);

        request.setSequence(0x11);
        request.setRequestId(0x22);
        request.setDestinationAddress(new ZigBeeDeviceAddress(0x9876, 0x54));
        request.setDestinationAddressMode(ConBeeAddressMode.NWK);
        request.setProfileId(0x4444);
        request.setRadius(0x1F);
        request.setSourceEndpoint(0x33);
        request.setAdsuData(new int[] { 0xAA, 0xBB, 0xCC, 0xDD, 0xEE, 0xFF });
        System.out.println(request);

        assertTrue(Arrays.equals(
                new int[] { 0x12, 0x11, 0x00, 0x1B, 0x00, 0x14, 0x00, 0x22, 0x00, 0x02, 0x76, 0x98, 0x54, 0x44, 0x44,
                        0x00, 0x00, 0x33, 0x06, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff, 0x04, 0x1f, 0x49, 0xf8 },
                request.getOutputBuffer()));
    }
}
