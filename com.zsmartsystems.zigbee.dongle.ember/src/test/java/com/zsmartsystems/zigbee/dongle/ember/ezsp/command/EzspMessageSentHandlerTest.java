package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberApsOption;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberOutgoingMessageType;
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

    @Test
    public void testReceive2() {
        EzspMessageSentHandler messageSentHandler = new EzspMessageSentHandler(
                getPacketData("04 90 3F 00 00 00 00 00 04 00 00 00 40 11 00 00 78 04 00 00"));

        System.out.println(messageSentHandler);
        assertTrue(messageSentHandler.isResponse());
        assertEquals(EmberStatus.EMBER_SUCCESS, messageSentHandler.getStatus());
        assertEquals(0, messageSentHandler.getMessageContents().length);
        assertEquals(0, messageSentHandler.getIndexOrDestination());
        assertEquals(4, messageSentHandler.getMessageTag());
        assertEquals(EmberOutgoingMessageType.EMBER_OUTGOING_DIRECT, messageSentHandler.getType());

        EmberApsFrame apsFrame = messageSentHandler.getApsFrame();
        assertNotNull(apsFrame);
        assertEquals(3, apsFrame.getOptions().size());
        assertTrue(apsFrame.getOptions().contains(EmberApsOption.EMBER_APS_OPTION_RETRY));
        assertTrue(apsFrame.getOptions().contains(EmberApsOption.EMBER_APS_OPTION_ENABLE_ROUTE_DISCOVERY));
        assertTrue(apsFrame.getOptions().contains(EmberApsOption.EMBER_APS_OPTION_ENABLE_ADDRESS_DISCOVERY));
        assertEquals(4, apsFrame.getClusterId());
        assertEquals(0, apsFrame.getProfileId());
        assertEquals(120, apsFrame.getSequence());
    }
}
