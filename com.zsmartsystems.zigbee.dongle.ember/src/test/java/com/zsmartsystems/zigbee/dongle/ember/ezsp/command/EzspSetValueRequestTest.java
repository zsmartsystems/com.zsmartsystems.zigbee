/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspValueId;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspSetValueRequestTest extends EzspFrameTest {
    @Test
    public void testSetValue() throws Exception {
        EzspFrame.setEzspVersion(4);
        EzspSetValueRequest request = new EzspSetValueRequest();
        TestUtilities.setField(EzspFrame.class, request, "sequenceNumber", 2);
        request.setValueId(EzspValueId.EZSP_VALUE_APS_FRAME_COUNTER);
        request.setValue(new int[] { 1, 2, 3, 4 });
        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("02 00 AB 24 04 01 02 03 04"), request.serialize()));
    }
}
