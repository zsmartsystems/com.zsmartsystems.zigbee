/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisDeviceLeftNetworkEventTest extends TelegesisFrameBaseTest {
    @Test
    public void testRemoteAddress() {
        TelegesisDeviceLeftNetworkEvent event = new TelegesisDeviceLeftNetworkEvent();
        event.deserialize(stringToIntArray("NODELEFT:1234,1234567890ABCDEF"));
        System.out.println(event);
        assertEquals(Integer.valueOf(0x1234), event.getNetworkAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), event.getIeeeAddress());
    }
}
