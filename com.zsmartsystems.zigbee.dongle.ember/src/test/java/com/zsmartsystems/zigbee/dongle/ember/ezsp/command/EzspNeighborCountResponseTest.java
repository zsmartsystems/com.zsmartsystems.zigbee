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

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNeighborCountResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspNeighborCountResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspNeighborCountResponse response = new EzspNeighborCountResponse(4, getPacketData("28 80 7A 01"));
        System.out.println(response);

        assertEquals(true, response.isResponse());
        assertEquals(EzspNeighborCountResponse.FRAME_ID, response.getFrameId());
        assertEquals(1, response.getValue());
    }
}
