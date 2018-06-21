/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspLookupEui64ByNodeIdRequest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspLookupEui64ByNodeIdRequestTest extends EzspFrameTest {
    @Test
    public void testLookupAddress() {
        EzspFrame.setEzspVersion(4);
        EzspLookupEui64ByNodeIdRequest request = new EzspLookupEui64ByNodeIdRequest();
        request.setNodeId(0);
        request.setSequenceNumber(5);
        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("05 00 61 00 00"), request.serialize()));
    }
}
