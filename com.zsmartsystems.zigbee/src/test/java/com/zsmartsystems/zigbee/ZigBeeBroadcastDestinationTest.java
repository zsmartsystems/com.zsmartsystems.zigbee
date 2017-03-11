package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ZigBeeBroadcastDestinationTest {

    @Test
    public void getDestination() {
        ZigBeeBroadcastDestination destination = ZigBeeBroadcastDestination.getBroadcastDestination(0);
        assertEquals(ZigBeeBroadcastDestination.BROADCAST_ALL_DEVICES, destination);

        destination = ZigBeeBroadcastDestination.getBroadcastDestination(0xFFFC);
        assertEquals(ZigBeeBroadcastDestination.BROADCAST_ROUTERS_AND_COORD, destination);

        destination = ZigBeeBroadcastDestination.getBroadcastDestination(0xFFFB);
        assertEquals(ZigBeeBroadcastDestination.BROADCAST_LOW_POWER_ROUTERS, destination);
    }
}
