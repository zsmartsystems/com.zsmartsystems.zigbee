package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspVersionRequestTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspVersionRequest version = new EzspVersionRequest();
        version.setDesiredProtocolVersion(4);
        version.setSequenceNumber(0);

        assertTrue(Arrays.equals(getPacketData("00 00 00 04"), version.serialize()));
    }
}
