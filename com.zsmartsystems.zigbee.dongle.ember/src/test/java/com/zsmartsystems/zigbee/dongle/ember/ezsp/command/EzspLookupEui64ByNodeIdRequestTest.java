package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspLookupEui64ByNodeIdRequestTest extends EzspFrameTest {
    @Test
    public void testLookupAddress() {
        EzspLookupEui64ByNodeIdRequest request = new EzspLookupEui64ByNodeIdRequest();
        request.setNodeId(0);
        request.setSequenceNumber(5);

        assertTrue(Arrays.equals(getPacketData("05 00 61 00 00"), request.serialize()));
    }
}
