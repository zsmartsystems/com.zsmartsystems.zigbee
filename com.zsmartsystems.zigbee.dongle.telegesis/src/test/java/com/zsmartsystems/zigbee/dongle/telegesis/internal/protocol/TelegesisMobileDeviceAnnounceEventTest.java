/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisMobileDeviceAnnounceEventTest extends TelegesisFrameBaseTest {
    @Test
    public void testBasic() {
        TelegesisMobileDeviceAnnounceEvent event = new TelegesisMobileDeviceAnnounceEvent();
        event.deserialize(stringToIntArray("MED:1234567890ABCDEF,9876"));
        System.out.println(event);
        assertEquals(new IeeeAddress("1234567890ABCDEF"), event.getIeeeAddress());
        assertEquals(Integer.valueOf(0x9876), event.getNetworkAddress());
        assertNull(event.getRssi());
        assertNull(event.getLqi());
    }

    @Test
    public void testExtended() {
        TelegesisMobileDeviceAnnounceEvent event = new TelegesisMobileDeviceAnnounceEvent();
        event.deserialize(stringToIntArray("MED:1234567890ABCDEF,9876,-44,AA"));
        System.out.println(event);
        assertEquals(new IeeeAddress("1234567890ABCDEF"), event.getIeeeAddress());
        assertEquals(Integer.valueOf(0x9876), event.getNetworkAddress());
        assertEquals(Integer.valueOf(-68), event.getRssi());
        assertEquals(Integer.valueOf(0xAA), event.getLqi());
    }
}
