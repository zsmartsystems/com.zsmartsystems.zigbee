package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspStackStatusHandlerResponseTest extends EzspFrameTest {
    @Test
    public void testStackHandler() {
        EzspStackStatusHandlerResponse response = new EzspStackStatusHandlerResponse(getPacketData("03 90 19 90"));
        assertEquals(EzspStackStatusHandlerResponse.FRAME_ID, response.getFrameId());
        assertEquals(EmberStatus.EMBER_NETWORK_UP, response.getStatus());
        assertEquals(3, response.getSequenceNumber());
    }
}
