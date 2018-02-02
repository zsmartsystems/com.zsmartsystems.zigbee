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
public class EzspSetConfigurationValueResponseTest extends EzspFrameTest {
    @Test
    public void testVersion() {
        EzspFrame.setEzspVersion(4);

        EzspSetConfigurationValueResponse response = new EzspSetConfigurationValueResponse(
                getPacketData("02 80 53 00"));

        assertEquals(2, response.getSequenceNumber());
        assertEquals(true, response.isResponse());
        assertEquals(EzspSetConfigurationValueResponse.FRAME_ID, response.getFrameId());
        assertEquals(EzspStatus.EZSP_SUCCESS, response.getStatus());
    }
}
