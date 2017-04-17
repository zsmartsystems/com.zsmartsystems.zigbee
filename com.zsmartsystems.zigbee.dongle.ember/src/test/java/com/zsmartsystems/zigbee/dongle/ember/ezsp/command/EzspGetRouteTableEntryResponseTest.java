package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberRouteTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspGetRouteTableEntryResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspGetRouteTableEntryResponse response = new EzspGetRouteTableEntryResponse(
                getPacketData("28 80 7B 00 FF FF 00 00 03 00 00 00"));

        assertEquals(true, response.isResponse());
        assertEquals(EzspGetRouteTableEntryResponse.FRAME_ID, response.getFrameId());
        assertEquals(EmberStatus.EMBER_SUCCESS, response.getStatus());
        EmberRouteTableEntry route = response.getValue();
        assertNotNull(route);
        assertEquals(3, route.getStatus());
        assertEquals(65535, route.getDestination());
    }
}
