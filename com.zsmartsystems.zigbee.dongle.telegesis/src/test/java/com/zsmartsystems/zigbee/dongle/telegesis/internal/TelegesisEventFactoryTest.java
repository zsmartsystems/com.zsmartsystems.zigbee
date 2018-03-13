/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisDeviceLeftNetworkEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisEvent;
import com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol.TelegesisFrameBaseTest;

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisEventFactoryTest extends TelegesisFrameBaseTest {

    @Test
    public void testGetEvent() {
        TelegesisEvent event = TelegesisEventFactory
                .getTelegesisFrame(stringToIntArray("NODELEFT:1234,1234567890ABCDEF"));

        assertTrue(event instanceof TelegesisDeviceLeftNetworkEvent);
    }

    @Test
    public void testGetUnknownEvent() {
        TelegesisEvent event = TelegesisEventFactory
                .getTelegesisFrame(stringToIntArray("UNKNOWN:1234,1234567890ABCDEF"));

        assertNull(event);
    }

    @Test
    public void testEventHash() {
        TelegesisEvent event = TelegesisEventFactory
                .getTelegesisFrame(stringToIntArray("LEFTNODE:1234,1234567890ABCDEF"));

        assertNull(event);
    }

    @Test
    public void testCorruptMessage1() {
        TelegesisEvent event = TelegesisEventFactory.getTelegesisFrame(
                new int[] { 0x52, 0x58, 0x3A, 0x33, 0x44, 0x45, 0x42, 0x2C, 0x30, 0x93, 0x05, 0x00, 0xEA, 0x11 });

        assertNull(event);
    }

    @Test
    public void testCorruptMessage2() {
        TelegesisEvent event = TelegesisEventFactory.getTelegesisFrame(stringToIntArray("SR:00,001788011"));

        assertNull(event);
    }
}
