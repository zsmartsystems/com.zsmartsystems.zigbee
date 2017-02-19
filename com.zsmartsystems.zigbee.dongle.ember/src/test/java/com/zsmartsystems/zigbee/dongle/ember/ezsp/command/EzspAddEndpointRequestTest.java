package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspAddEndpointRequestTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        int[] clusters = new int[] { 0, 1, 6 };
        EzspAddEndpointRequest request = new EzspAddEndpointRequest();
        request.setAppFlags(0);
        request.setDeviceId(0);
        request.setEndpoint(0);
        request.setInputClusterList(clusters);
        request.setOutputClusterList(clusters);
        request.setProfileId(0x104);
        request.setSequenceNumber(0);

        int[] x = request.serialize();

        // assertTrue(Arrays.equals(getPacketData("00 00 00 04"), request.serialize()));
    }
}
