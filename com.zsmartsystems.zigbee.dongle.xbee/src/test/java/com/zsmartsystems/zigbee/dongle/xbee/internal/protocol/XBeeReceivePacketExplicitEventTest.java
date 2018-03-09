/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeReceivePacketExplicitEventTest extends XBeeFrameBaseTest {
    @Test
    public void test() {
        XBeeReceivePacketExplicitEvent event = new XBeeReceivePacketExplicitEvent();
        event.deserialize(
                getPacketData("00 18 91 00 13 A2 00 40 52 2B AA 7D 84 E0 E0 22 11 C1 05 02 52 78 44 61 74 61 52"));
        System.out.println(event);
        assertEquals(0x91, event.getFrameType());
        assertEquals(Integer.valueOf(0x7D84), event.getNetworkAddress());
        assertEquals(Integer.valueOf(224), event.getSourceEndpoint());
        assertEquals(Integer.valueOf(224), event.getDestinationEndpoint());
        assertEquals(Integer.valueOf(0x2211), event.getClusterId());
        assertEquals(Integer.valueOf(0xC105), event.getProfileId());
        assertEquals(ReceiveOptions.PACKET_BROADCAST, event.getReceiveOptions());
        assertEquals(new IeeeAddress("0013A20040522BAA"), event.getIeeeAddress());
        assertTrue(Arrays.equals(getPacketData("52 78 44 61 74 61"), event.getData()));
    }

}
