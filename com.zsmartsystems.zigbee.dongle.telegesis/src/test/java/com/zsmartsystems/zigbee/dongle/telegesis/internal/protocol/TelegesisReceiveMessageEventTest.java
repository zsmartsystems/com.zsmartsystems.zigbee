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

/**
 *
 * @author Chris Jackson
 *
 */
public class TelegesisReceiveMessageEventTest extends TelegesisFrameBaseTest {
    @Test
    public void testMessage1() {
        TelegesisReceiveMessageEvent event = new TelegesisReceiveMessageEvent();
        event.deserialize(new int[] { 0x52, 0x58, 0x3A, 0x30, 0x30, 0x30, 0x30, 0x2C, 0x30, 0x30, 0x30, 0x30, 0x2C,
                0x30, 0x30, 0x2C, 0x30, 0x30, 0x2C, 0x38, 0x30, 0x30, 0x31, 0x2C, 0x30, 0x44, 0x3A, 0x00, 0x00, 0x62,
                0x39, 0x05, 0x0D, 0x00, 0x6F, 0x0D, 0x00, 0x00, 0x00, 0x00, 0x2C, 0x30, 0x30, 0x2C, 0x46, 0x46 });
        System.out.println(event);
        assertEquals(Integer.valueOf(32769), event.getClusterId());
        assertEquals(Integer.valueOf(0), event.getSourceEp());
        assertEquals(Integer.valueOf(0), event.getRssi());
        assertEquals(Integer.valueOf(255), event.getLqi());
    }

    @Test
    public void testMessage2() {
        TelegesisReceiveMessageEvent event = new TelegesisReceiveMessageEvent();
        event.deserialize(new int[] { 0x52, 0x58, 0x3A, 0x44, 0x46, 0x46, 0x39, 0x2C, 0x30, 0x31, 0x30, 0x34, 0x2C,
                0x30, 0x30, 0x2C, 0x30, 0x33, 0x2C, 0x30, 0x30, 0x30, 0x36, 0x2C, 0x30, 0x33, 0x3A, 0x18, 0x28, 0x01,
                0x2C, 0x2D, 0x34, 0x35, 0x2C, 0x46, 0x46 });
        System.out.println(event);
        assertEquals(Integer.valueOf(57337), event.getNetworkAddress());
        assertEquals(Integer.valueOf(6), event.getClusterId());
        assertEquals(Integer.valueOf(3), event.getSourceEp());
        assertEquals(Integer.valueOf(-69), event.getRssi());
        assertEquals(Integer.valueOf(255), event.getLqi());
    }

}
