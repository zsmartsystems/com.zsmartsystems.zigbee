/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetKeyTableEntryRequest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspGetKeyTableEntryRequestTest extends EzspFrameTest {
    @Test
    public void testAddEndpointRequest() {
        EzspFrame.setEzspVersion(4);
        EzspGetKeyTableEntryRequest request = new EzspGetKeyTableEntryRequest();
        request.setIndex(0);
        request.setSequenceNumber(2);
        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("02 00 71 00"), request.serialize()));
    }
}
