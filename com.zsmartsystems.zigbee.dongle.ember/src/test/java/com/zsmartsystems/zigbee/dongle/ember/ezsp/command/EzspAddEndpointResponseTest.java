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
public class EzspAddEndpointResponseTest extends EzspFrameTest {
    @Test
    public void testVersionError() {
        EzspAddEndpointResponse response = new EzspAddEndpointResponse(getPacketData("02 80 02 36"));

        assertEquals(2, response.getSequenceNumber());
        assertEquals(true, response.isResponse());
        assertEquals(EzspAddEndpointResponse.FRAME_ID, response.getFrameId());
        assertEquals(EzspStatus.EZSP_ERROR_INVALID_VALUE, response.getStatus());
    }
}
