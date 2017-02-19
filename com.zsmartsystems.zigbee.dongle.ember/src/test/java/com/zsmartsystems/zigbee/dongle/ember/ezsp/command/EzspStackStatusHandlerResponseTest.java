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
        EzspStackStatusHandler response = new EzspStackStatusHandler(getPacketData("03 90 19 90"));
        assertEquals(EzspStackStatusHandler.FRAME_ID, response.getFrameId());
        assertEquals(EmberStatus.EMBER_NETWORK_UP, response.getStatus());
        assertEquals(3, response.getSequenceNumber());
    }
}
