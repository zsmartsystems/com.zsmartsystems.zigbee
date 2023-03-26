/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

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
public class TelegesisReceiveUnicastEventTest extends TelegesisFrameBaseTest {
    @Test
    public void testRemoteAddress() {
        TelegesisReceiveUnicastEvent event = new TelegesisReceiveUnicastEvent();
        event.deserialize(stringToIntArray("UCAST:000D6F000005A666,04=test"));
        System.out.println(event);
        assertEquals(new IeeeAddress("000D6F000005A666"), event.getRemoteAddress());
        assertTrue(Arrays.equals(stringToIntArray("test"), event.getMessageData()));
    }

    @Test
    public void testRemoteAddressRssiLqi() {
        TelegesisReceiveUnicastEvent event = new TelegesisReceiveUnicastEvent();
        event.deserialize(stringToIntArray("UCAST:000D6F000005A666,04=test,A0,45"));
        System.out.println(event);
        assertEquals(new IeeeAddress("000D6F000005A666"), event.getRemoteAddress());
        assertTrue(Arrays.equals(stringToIntArray("test"), event.getMessageData()));
        assertEquals(Integer.valueOf(160), event.getRssi());
        assertEquals(Integer.valueOf(69), event.getLqi());
    }

    @Test
    public void testDataOnly() {
        TelegesisReceiveUnicastEvent event = new TelegesisReceiveUnicastEvent();
        event.deserialize(stringToIntArray("UCAST:04=test"));
        System.out.println(event);
        assertEquals(null, event.getRemoteAddress());
        assertTrue(Arrays.equals(stringToIntArray("test"), event.getMessageData()));
    }

    @Test
    public void testDataRssiLqi() {
        TelegesisReceiveUnicastEvent event = new TelegesisReceiveUnicastEvent();
        event.deserialize(stringToIntArray("UCAST:04=test,A0,45"));
        System.out.println(event);
        assertEquals(null, event.getRemoteAddress());
        assertTrue(Arrays.equals(stringToIntArray("test"), event.getMessageData()));
        assertEquals(Integer.valueOf(160), event.getRssi());
        assertEquals(Integer.valueOf(69), event.getLqi());
    }
}
