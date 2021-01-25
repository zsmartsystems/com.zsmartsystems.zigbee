/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetKeyResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberKeyType;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspGetKeyResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspFrame.setEzspVersion(4);
        EzspGetKeyResponse response = new EzspGetKeyResponse(getPacketData(
                "60 80 FF 00 6A 00 03 00 03 AA AA AA AA AA AA AA AA AA AA AA AA AA AA AA AA 24 10 04 00 00 00 00 00 00 00 00 00 00 00 00 00 00"));
        System.out.println(response);

        assertEquals(true, response.isResponse());
        assertEquals(EzspGetKeyResponse.FRAME_ID, response.getFrameId());
        assertEquals(EmberStatus.EMBER_SUCCESS, response.getStatus());
        assertTrue(Arrays.equals(new int[] { 0xAA, 0xAA, 0xAA, 0xAA, 0xAA, 0xAA, 0xAA, 0xAA, 0xAA, 0xAA, 0xAA, 0xAA,
                0xAA, 0xAA, 0xAA, 0xAA }, response.getKeyStruct().getKey().getContents()));
        assertEquals(0, response.getKeyStruct().getIncomingFrameCounter());
        assertEquals(266276, response.getKeyStruct().getOutgoingFrameCounter());
        assertEquals(EmberKeyType.EMBER_CURRENT_NETWORK_KEY, response.getKeyStruct().getType());
    }
}
