/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeBroadcastDestinationTest {

    @Test
    public void getDestination() {
        ZigBeeBroadcastDestination destination = ZigBeeBroadcastDestination.getBroadcastDestination(0);
        assertEquals(null, destination);

        destination = ZigBeeBroadcastDestination.getBroadcastDestination(0xFFFC);
        assertEquals(ZigBeeBroadcastDestination.BROADCAST_ROUTERS_AND_COORD, destination);

        destination = ZigBeeBroadcastDestination.getBroadcastDestination(0xFFFB);
        assertEquals(ZigBeeBroadcastDestination.BROADCAST_LOW_POWER_ROUTERS, destination);

        assertEquals(0xFFFF, ZigBeeBroadcastDestination.BROADCAST_ALL_DEVICES.getKey());

        assertTrue(ZigBeeBroadcastDestination.isBroadcast(0xfff8));
        assertTrue(ZigBeeBroadcastDestination.isBroadcast(0xffff));
        assertFalse(ZigBeeBroadcastDestination.isBroadcast(0xfff7));
        assertFalse(ZigBeeBroadcastDestination.isBroadcast(0x10000));
    }
}
