/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisReceiveMessageEventTest extends TelegesisFrameBaseTest {
    @Test
    public void testRemoteAddress() {
        TelegesisReceiveMessageEvent event = new TelegesisReceiveMessageEvent();
        event.deserialize(new int[] { 0x52, 0x58, 0x3A, 0x30, 0x30, 0x30, 0x30, 0x2C, 0x30, 0x30, 0x30, 0x30, 0x2C,
                0x30, 0x30, 0x2C, 0x30, 0x30, 0x2C, 0x38, 0x30, 0x30, 0x31, 0x2C, 0x30, 0x44, 0x3A, 0x00, 0x00, 0x62,
                0x39, 0x05, 0x0D, 0x00, 0x6F, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x2C, 0x30, 0x30, 0x2C, 0x46, 0x46 });
        System.out.println(event);
        // assertEquals(Integer.valueOf(0x1234), event.getNetworkAddress());
        // assertEquals(new IeeeAddress("1234567890ABCDEF"), event.getIeeeAddress());
    }
}
