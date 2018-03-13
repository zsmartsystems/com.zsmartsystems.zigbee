/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeRouteRecordEventTest extends XBeeFrameBaseTest {
    @Test
    public void test() {
        XBeeRouteRecordEvent event = new XBeeRouteRecordEvent();
        event.deserialize(getPacketData("00 13 A1 00 13 A2 00 40 40 11 22 33 44 01 03 EE FF CC DD AA BB 80"));
        System.out.println(event);
        assertEquals(0xA1, event.getFrameType());
        assertEquals(Integer.valueOf(0x3344), event.getNetworkAddress());
        assertEquals(ReceiveOptions.PACKET_ACKNOWLEDGED, event.getReceiveOptions());
        assertEquals(new IeeeAddress("0013A20040401122"), event.getIeeeAddress());
        assertEquals(3, event.getAddressList().length);
    }

}
