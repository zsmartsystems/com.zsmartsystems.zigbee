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
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspAddEndpointResponseTest extends EzspFrameTest {
    @Test
    public void testVersionError() {
        EzspFrame.setEzspVersion(4);
        EzspAddEndpointResponse response = new EzspAddEndpointResponse(getPacketData("02 80 02 36"));

        assertEquals(2, response.getSequenceNumber());
        assertEquals(true, response.isResponse());
        assertEquals(EzspAddEndpointResponse.FRAME_ID, response.getFrameId());
        assertEquals(EzspStatus.EZSP_ERROR_INVALID_VALUE, response.getStatus());
    }
}
