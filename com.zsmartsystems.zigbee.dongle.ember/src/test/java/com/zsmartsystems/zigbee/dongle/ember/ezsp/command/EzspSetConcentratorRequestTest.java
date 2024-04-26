/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberConcentratorType;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspSetConcentratorRequestTest extends EzspFrameTest {
    @Test
    public void testEnabled() throws Exception {
        EzspFrame.setEzspVersion(4);
        EzspSetConcentratorRequest request = new EzspSetConcentratorRequest();
        TestUtilities.setField(EzspFrame.class, request, "sequenceNumber", 52);
        request.setConcentratorType(EmberConcentratorType.EMBER_HIGH_RAM_CONCENTRATOR);
        request.setMinTime(60);
        request.setMaxTime(3600);
        request.setMaxHops(0);
        request.setEnable(true);
        request.setDeliveryFailureThreshold(8);
        request.setRouteErrorThreshold(8);
        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("34 00 10 01 F9 FF 3C 00 10 0E 08 08 00"), request.serialize()));
    }

    @Test
    public void testDisabled() throws Exception {
        EzspFrame.setEzspVersion(4);
        EzspSetConcentratorRequest request = new EzspSetConcentratorRequest();
        TestUtilities.setField(EzspFrame.class, request, "sequenceNumber", 52);
        request.setConcentratorType(EmberConcentratorType.EMBER_LOW_RAM_CONCENTRATOR);
        request.setMinTime(60);
        request.setMaxTime(3600);
        request.setMaxHops(0);
        request.setEnable(false);
        request.setDeliveryFailureThreshold(8);
        request.setRouteErrorThreshold(8);
        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("34 00 10 00 F8 FF 3C 00 10 0E 08 08 00"), request.serialize()));
    }
}
