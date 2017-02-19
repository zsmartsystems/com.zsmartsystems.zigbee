package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspLookupEui64ByNodeIdResponseTest extends EzspFrameTest {
    @Test
    public void testVersionError() {
        EzspLookupEui64ByNodeIdResponse response = new EzspLookupEui64ByNodeIdResponse(
                getPacketData("05 80 61 00 BF 32 17 00 00 A3 22 00"));

        assertEquals(5, response.getSequenceNumber());
        assertEquals(true, response.isResponse());
        assertEquals(EzspLookupEui64ByNodeIdResponse.FRAME_ID, response.getFrameId());
        assertEquals(new IeeeAddress("0022A300001732BF"), response.getEui64());
        assertEquals(EmberStatus.EMBER_SUCCESS, response.getStatus());
    }
}
