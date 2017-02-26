package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 * @author Chris Jackson
 */
public class EzspMessageSentHandlerTest extends EzspFrameTest {

    @Test
    public void testReceive1() {
        EzspMessageSentHandler messageSentHandler = new EzspMessageSentHandler(
                getPacketData("01 90 3F 00 00 00 00 01 00 00 00 00 00 01 00 00 45 00 00 00"));

        assertTrue(messageSentHandler.isResponse());
        assertEquals(EmberStatus.EMBER_SUCCESS, messageSentHandler.getStatus());
        assertEquals(0, messageSentHandler.getMessageContents().length);
    }
}
