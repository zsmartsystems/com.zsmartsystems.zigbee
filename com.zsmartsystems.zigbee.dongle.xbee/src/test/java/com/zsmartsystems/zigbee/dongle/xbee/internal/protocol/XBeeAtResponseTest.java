/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeAtResponseTest extends XBeeFrameBaseTest {
    @Test
    public void testNoData() {
        XBeeAtResponse event = new XBeeAtResponse();
        event.deserialize(getPacketData("00 05 88 01 42 44 00 F0"));
        System.out.println(event);
        assertEquals(Integer.valueOf(0x01), event.getFrameId());
        assertEquals(0x88, event.getFrameType());
        assertEquals(CommandStatus.OK, event.getCommandStatus());
        assertNull(event.getCommandData());
    }

    @Test
    public void testData() {
        XBeeAtResponse event = new XBeeAtResponse();
        event.deserialize(getPacketData("00 05 88 01 42 44 00 01 02 03 F0"));
        System.out.println(event);
        assertEquals(Integer.valueOf(0x01), event.getFrameId());
        assertEquals(0x88, event.getFrameType());
        assertEquals(CommandStatus.OK, event.getCommandStatus());
        assertNotNull(event.getCommandData());
        assertEquals(3, event.getCommandData().length);
    }
}
