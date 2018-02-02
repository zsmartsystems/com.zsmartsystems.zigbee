/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.command;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.EzspFrameTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspAddEndpointRequestTest extends EzspFrameTest {
    @Test
    public void testAddEndpointRequest() {
        EzspFrame.setEzspVersion(4);
        int[] clusters = new int[] { 0, 1, 6 };
        EzspAddEndpointRequest request = new EzspAddEndpointRequest();
        request.setAppFlags(0);
        request.setDeviceId(0);
        request.setEndpoint(1);
        request.setInputClusterList(clusters);
        request.setOutputClusterList(clusters);
        request.setProfileId(0x104);
        request.setSequenceNumber(2);

        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("02 00 02 01 04 01 00 00 00 03 03 00 00 01 00 06 00 00 00 01 00 06 00"),
                request.serialize()));
    }

    @Test
    public void testAddEndpointRequestNoClusters() {
        EzspFrame.setEzspVersion(4);
        int[] clusters = new int[] {};
        EzspAddEndpointRequest request = new EzspAddEndpointRequest();
        request.setDeviceId(0);
        request.setEndpoint(1);
        request.setInputClusterList(clusters);
        request.setOutputClusterList(clusters);
        request.setProfileId(0x104);
        request.setSequenceNumber(170);

        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("AA 00 02 01 04 01 00 00 00 00 00"), request.serialize()));
    }
}
