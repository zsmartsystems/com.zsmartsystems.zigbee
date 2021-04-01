/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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

/**
 *
 * @author Chris Jackson
 *
 */
public class XBeeDetailedVersionResponseTest extends XBeeFrameBaseTest {
    @Test
    public void testResponse() {
        XBeeDetailedVersionResponse event = new XBeeDetailedVersionResponse();
        event.deserialize(getPacketData(
                "00 8B 88 06 56 4C 00 58 42 65 65 2D 50 52 4F 20 53 32 43 20 52 45 4C 45 3A 20 34 30 36 30 0D 42 75 69 6C 64 3A 20 53 65 70 20 32 32 20 32 30 31 37 20 30 38 3A 35 36 3A 34 35 0D 53 74 61 63 6B 3A 20 35 36 30 31 20 48 57 3A 20 32 44 34 35 20 4D 66 67 53 74 72 3A 20 30 30 31 32 46 46 30 30 46 46 46 46 46 46 46 46 0D 42 6F 6F 74 6C 6F 61 64 65 72 3A 20 34 36 43 36 20 43 6F 6D 70 69 6C 65 72 3A 20 37 30 33 30 30 30 31 0D 00 FD"));
        System.out.println(event);
        assertEquals(Integer.valueOf(6), event.getFrameId());
        assertEquals(CommandStatus.OK, event.getCommandStatus());
        assertTrue(Arrays.equals(getPacketData(
                "58 42 65 65 2D 50 52 4F 20 53 32 43 20 52 45 4C 45 3A 20 34 30 36 30 0D 42 75 69 6C 64 3A 20 53 65 70 20 32 32 20 32 30 31 37 20 30 38 3A 35 36 3A 34 35 0D 53 74 61 63 6B 3A 20 35 36 30 31 20 48 57 3A 20 32 44 34 35 20 4D 66 67 53 74 72 3A 20 30 30 31 32 46 46 30 30 46 46 46 46 46 46 46 46 0D 42 6F 6F 74 6C 6F 61 64 65 72 3A 20 34 36 43 36 20 43 6F 6D 70 69 6C 65 72 3A 20 37 30 33 30 30 30 31 0D 00"),
                event.getVersionInfo()));
    }
}
