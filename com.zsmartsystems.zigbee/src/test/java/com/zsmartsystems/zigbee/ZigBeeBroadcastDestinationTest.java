/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ZigBeeBroadcastDestinationTest {

    @Test
    public void getDestination() {
        ZigBeeBroadcastDestination destination = ZigBeeBroadcastDestination.getBroadcastDestination(0);
        assertEquals(null, destination);

        destination = ZigBeeBroadcastDestination.getBroadcastDestination(0xFFFC);
        assertEquals(ZigBeeBroadcastDestination.BROADCAST_ROUTERS_AND_COORD, destination);

        destination = ZigBeeBroadcastDestination.getBroadcastDestination(0xFFFB);
        assertEquals(ZigBeeBroadcastDestination.BROADCAST_LOW_POWER_ROUTERS, destination);
    }
}
