/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspLookupEui64ByNodeIdRequestTest extends EzspFrameTest {
    @Test
    public void testLookupAddress() throws Exception {
        EzspLookupEui64ByNodeIdRequest request = new EzspLookupEui64ByNodeIdRequest();
        request.setNodeId(0);
        TestUtilities.setField(EzspFrame.class, request, "sequenceNumber", 5);
        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("05 00 61 00 00"), request.serialize(4)));
    }
}
