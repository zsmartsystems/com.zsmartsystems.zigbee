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

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspNeighborCountResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspFrame.setEzspVersion(4);
        EzspNeighborCountResponse response = new EzspNeighborCountResponse(getPacketData("28 80 7A 01"));

        assertEquals(true, response.isResponse());
        assertEquals(EzspNeighborCountResponse.FRAME_ID, response.getFrameId());
        assertEquals(1, response.getValue());
    }
}
