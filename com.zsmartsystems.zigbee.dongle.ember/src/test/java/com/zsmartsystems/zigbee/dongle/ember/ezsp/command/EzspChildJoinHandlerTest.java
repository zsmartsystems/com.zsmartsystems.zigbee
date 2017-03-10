package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNodeType;

/**
 * @author Chris Jackson
 */
public class EzspChildJoinHandlerTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspChildJoinHandler handler = new EzspChildJoinHandler(
                getPacketData("0B 90 23 00 00 95 87 F9 41 F6 02 00 4B 12 00 04"));

        assertTrue(handler.isResponse());
        assertEquals(0, handler.getIndex());
        assertEquals(34709, handler.getChildId());
        assertEquals(false, handler.getJoining());
        assertEquals(new IeeeAddress("00124B0002F641F9"), handler.getChildEui64());
        assertEquals(EmberNodeType.EMBER_SLEEPY_END_DEVICE, handler.getChildType());
    }
}
