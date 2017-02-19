package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspSetConfigurationValueResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspSetConfigurationValueResponse response = new EzspSetConfigurationValueResponse(
                getPacketData("02 80 53 00"));

        assertEquals(2, response.getSequenceNumber());
        assertEquals(true, response.isResponse());
        assertEquals(EzspSetConfigurationValueResponse.FRAME_ID, response.getFrameId());
        assertEquals(EzspStatus.EZSP_SUCCESS, response.getStatus());
    }
}
