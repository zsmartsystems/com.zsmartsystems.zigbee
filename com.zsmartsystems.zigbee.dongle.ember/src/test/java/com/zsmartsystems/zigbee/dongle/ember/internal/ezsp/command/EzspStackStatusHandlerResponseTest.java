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
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspStackStatusHandlerResponseTest extends EzspFrameTest {
    @Test
    public void testStackHandler() {
        EzspFrame.setEzspVersion(4);

        EzspStackStatusHandler response = new EzspStackStatusHandler(getPacketData("03 90 19 90"));
        assertEquals(EzspStackStatusHandler.FRAME_ID, response.getFrameId());
        assertEquals(EmberStatus.EMBER_NETWORK_UP, response.getStatus());
        assertEquals(3, response.getSequenceNumber());
    }
}
