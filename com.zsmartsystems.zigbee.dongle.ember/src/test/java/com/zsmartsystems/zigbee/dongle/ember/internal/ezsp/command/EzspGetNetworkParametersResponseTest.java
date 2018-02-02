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
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberJoinMethod;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberNodeType;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EzspGetNetworkParametersResponseTest extends EzspFrameTest {
    @Test
    public void testVersionError() {
        EzspFrame.setEzspVersion(4);
        EzspGetNetworkParametersResponse response = new EzspGetNetworkParametersResponse(
                getPacketData("05 80 28 00 01 EF CB B1 57 A8 CC C6 D7 05 C8 00 0B 00 00 00 00 00 F8 FF 07"));

        assertEquals(5, response.getSequenceNumber());
        assertEquals(true, response.isResponse());
        assertEquals(EzspGetNetworkParametersResponse.FRAME_ID, response.getFrameId());
        assertEquals(EmberStatus.EMBER_SUCCESS, response.getStatus());
        assertEquals(EmberNodeType.EMBER_COORDINATOR, response.getNodeType());

        EmberNetworkParameters networkParameters = response.getParameters();
        assertEquals(11, networkParameters.getRadioChannel());
        assertEquals(0, networkParameters.getRadioTxPower());
        assertEquals(EmberJoinMethod.EMBER_USE_MAC_ASSOCIATION, networkParameters.getJoinMethod());
        assertEquals(0, networkParameters.getNwkManagerId());
        assertEquals(134215680, networkParameters.getChannels());
    }
}
