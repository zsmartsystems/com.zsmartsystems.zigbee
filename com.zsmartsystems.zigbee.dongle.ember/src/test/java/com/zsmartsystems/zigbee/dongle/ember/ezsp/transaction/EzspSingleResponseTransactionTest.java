package com.zsmartsystems.zigbee.dongle.ember.ezsp.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;

public class EzspSingleResponseTransactionTest extends EzspFrameTest {
    @Test
    public void testResponseMatches() {
        EzspVersionRequest version = new EzspVersionRequest();
        version.setSequenceNumber(3);
        version.setDesiredProtocolVersion(4);

        EzspTransaction versionTransaction = new EzspSingleResponseTransaction(version, EzspVersionResponse.class);

        EzspVersionResponse versionResponse = new EzspVersionResponse(getPacketData("03 80 00 04 02 00 58"));

        assertTrue(versionTransaction.isMatch(versionResponse));

        versionTransaction.getRequest();
        assertEquals(1, versionTransaction.getResponses().size());
        assertNotNull(versionTransaction.getResponses());
        assertEquals(versionTransaction.getResponses().get(0), versionTransaction.getResponse());
    }

    @Test
    public void testResponseMatchFails() {
        EzspVersionRequest version = new EzspVersionRequest();
        version.setSequenceNumber(4);
        version.setDesiredProtocolVersion(4);

        EzspTransaction versionTransaction = new EzspSingleResponseTransaction(version, EzspVersionResponse.class);

        EzspVersionResponse versionResponse = new EzspVersionResponse(getPacketData("03 80 00 04 02 00 58"));

        assertFalse(versionTransaction.isMatch(versionResponse));
        assertNull(versionTransaction.getResponse());
        assertNull(versionTransaction.getResponses());
    }
}
