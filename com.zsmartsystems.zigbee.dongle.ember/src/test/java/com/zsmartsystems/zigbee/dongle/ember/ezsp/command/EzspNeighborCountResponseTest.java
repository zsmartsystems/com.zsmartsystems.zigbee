package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspNeighborCountResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspNeighborCountResponse response = new EzspNeighborCountResponse(getPacketData("28 80 7A 01"));

        assertEquals(true, response.isResponse());
        assertEquals(EzspNeighborCountResponse.FRAME_ID, response.getFrameId());
        assertEquals(1, response.getValue());
    }
}
