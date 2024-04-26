/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.ember.ezsp.command;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrame;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.EzspFrameTest;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspAddEndpointResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspAddEndpointResponseTest extends EzspFrameTest {
    @Test
    public void testVersionError() {
        EzspAddEndpointResponse response = new EzspAddEndpointResponse(4, getPacketData("02 80 02 36"));
        System.out.println(response);

        assertEquals(2, response.getSequenceNumber());
        assertEquals(true, response.isResponse());
        assertEquals(EzspAddEndpointResponse.FRAME_ID, response.getFrameId());
        assertEquals(EzspStatus.EZSP_ERROR_INVALID_VALUE, response.getStatus());
    }
}
