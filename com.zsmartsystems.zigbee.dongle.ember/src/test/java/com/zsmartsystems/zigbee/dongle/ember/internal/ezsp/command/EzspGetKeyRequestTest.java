/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberKeyType;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspGetKeyRequestTest extends EzspFrameTest {
    @Test
    public void testAddEndpointRequest() {
        EzspFrame.setEzspVersion(4);
        EzspGetKeyRequest request = new EzspGetKeyRequest();
        request.setKeyType(EmberKeyType.EMBER_APPLICATION_LINK_KEY);
        request.setSequenceNumber(2);
        System.out.println(request);

        assertTrue(Arrays.equals(getPacketData("02 00 6A 05"), request.serialize()));
    }
}
