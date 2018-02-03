/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionRequest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspSingleResponseTransaction;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.transaction.EzspTransaction;

/**
 *
 * @author Chris Jackson
 *
 */
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
