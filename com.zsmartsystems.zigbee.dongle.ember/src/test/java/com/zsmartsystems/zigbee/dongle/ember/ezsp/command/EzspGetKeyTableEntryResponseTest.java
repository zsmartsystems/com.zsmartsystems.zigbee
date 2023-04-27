/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspGetKeyTableEntryResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspFrame.setEzspVersion(4);
        EzspGetKeyTableEntryResponse response = new EzspGetKeyTableEntryResponse(getPacketData(
                "8A 80 FF 00 71 B6 00 00 06 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 20 00 00 00 6C F2 00 20 00"));
        System.out.println(response);

        assertEquals(true, response.isResponse());
        assertEquals(EzspGetKeyTableEntryResponse.FRAME_ID, response.getFrameId());
        assertEquals(EmberStatus.EMBER_TABLE_ENTRY_ERASED, response.getStatus());
        assertEquals(0, response.getKeyStruct().getIncomingFrameCounter());
        assertEquals(0, response.getKeyStruct().getOutgoingFrameCounter());
    }
}
