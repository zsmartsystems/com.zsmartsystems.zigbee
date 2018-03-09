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
public class XBeeReceivePacketEventTest extends XBeeFrameBaseTest {
    @Test
    public void test() {
        XBeeReceivePacketEvent event = new XBeeReceivePacketEvent();
        event.deserialize(getPacketData("00 12 90 00 13 A2 00 40 52 2B AA 7D 84 01 52 78 44 61 74 61 0D"));
        System.out.println(event);
        assertEquals(0x90, event.getFrameType());
        assertEquals(Integer.valueOf(0x7D84), event.getNetworkAddress());
        assertEquals(ReceiveOptions.PACKET_ACKNOWLEDGED, event.getReceiveOptions());
        assertEquals(new IeeeAddress("0013A20040522BAA"), event.getIeeeAddress());
        assertTrue(Arrays.equals(getPacketData("52 78 44 61 74 61"), event.getData()));
    }

}
