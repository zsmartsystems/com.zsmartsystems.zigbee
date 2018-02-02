/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspStartScanResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspFrame.setEzspVersion(4);
        EzspVersionResponse version = new EzspVersionResponse(getPacketData("03 80 00 04 02 00 58"));

        assertEquals(3, version.getSequenceNumber());
        assertEquals(true, version.isResponse());
        assertEquals(0, version.getFrameId());
        assertEquals(4, version.getProtocolVersion());
        assertEquals(2, version.getStackType());
        assertEquals(22528, version.getStackVersion());
    }

    @Test
    public void testVersionError() {
        EzspStartScanResponse response = new EzspStartScanResponse(getPacketData("03 80 1A 35"));

        assertEquals(3, response.getSequenceNumber());
        assertEquals(true, response.isResponse());
        assertEquals(EzspStartScanResponse.FRAME_ID, response.getFrameId());
        assertEquals(EmberStatus.EMBER_MAC_INVALID_CHANNEL_MASK, response.getStatus());
    }
}
