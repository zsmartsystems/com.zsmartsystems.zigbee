/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.cc2531;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeApsFrame;
import com.zsmartsystems.zigbee.dongle.cc2531.frame.ZdoManagementLqi;
import com.zsmartsystems.zigbee.dongle.cc2531.network.packet.ZToolPacket;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZDO_MGMT_LQI_RSP_Test extends Cc2351TestPacket {

    @Test
    public void testReceive() {
        ZToolPacket data = getPacket(
                "FE 32 45 B1 00 00 00 02 00 02 14 D4 F1 02 00 4B 12 00 0B 88 DC 00 01 88 17 00 8F 22 15 02 01 3B 14 D4 F1 02 00 4B 12 00 EC A1 A5 01 00 8D 15 00 35 38 15 02 01 58 B5");

        ZigBeeApsFrame apsFrame = ZdoManagementLqi.create(data);

        assertEquals(0x0000, apsFrame.getSourceAddress());
        assertEquals(0, apsFrame.getProfile());
        assertEquals(0, apsFrame.getDestinationEndpoint());
        assertTrue(Arrays.equals(
                getPacketData(
                        "00 00 02 00 02 14 D4 F1 02 00 4B 12 00 0B 88 DC 00 01 88 17 00 8F 22 15 02 01 3B 14 D4 F1 02 00 4B 12 00 EC A1 A5 01 00 8D 15 00 35 38 15 02 01 58"),
                apsFrame.getPayload()));
    }

}
