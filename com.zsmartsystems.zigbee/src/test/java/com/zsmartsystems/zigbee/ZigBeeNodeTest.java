package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.zdo.descriptors.NeighborTable;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor.CurrentPowerModeType;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor.PowerLevelType;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor.PowerSourceType;
import com.zsmartsystems.zigbee.zdo.descriptors.RoutingTable;
import com.zsmartsystems.zigbee.zdo.descriptors.RoutingTable.DiscoveryState;

public class ZigBeeNodeTest {
    @Test
    public void testSetIeeeAddress() {
        ZigBeeNode node = new ZigBeeNode(null);
        node.setIeeeAddress(new IeeeAddress("17880100dc880b"));
        assertEquals(new IeeeAddress("17880100dc880b"), node.getIeeeAddress());
    }

    @Test
    public void testSetPowerDescriptor() {
        PowerDescriptor descriptor = new PowerDescriptor(1, 2, 4, 0xc);
        ZigBeeNode node = new ZigBeeNode(null);
        node.setPowerDescriptor(descriptor);
        assertEquals(CurrentPowerModeType.RECEIVER_ON_PERIODICALLY, node.getPowerDescriptor().getCurrentPowerMode());
        assertEquals(PowerSourceType.DISPOSABLE_BATTERY, node.getPowerDescriptor().getCurrentPowerSource());
        assertEquals(PowerLevelType.FULL, node.getPowerDescriptor().getPowerLevel());
    }

    @Test
    public void testNeighborTableUpdate() {
        ZigBeeNode node = new ZigBeeNode(null);
        List<NeighborTable> neighbors;

        NeighborTable neighbor1 = new NeighborTable();
        neighbor1.setNetworkAddress(12345);
        neighbor1.setExtendedAddress(new IeeeAddress("123456789"));

        NeighborTable neighbor2 = new NeighborTable();
        neighbor2.setNetworkAddress(12345);
        neighbor2.setExtendedAddress(new IeeeAddress("123456789"));

        NeighborTable neighbor3 = new NeighborTable();
        neighbor3.setNetworkAddress(54321);
        neighbor3.setExtendedAddress(new IeeeAddress("987654321"));

        assertFalse(node.setNeighbors(null));

        neighbors = new ArrayList<NeighborTable>();
        neighbors.add(neighbor1);
        assertTrue(node.setNeighbors(neighbors));

        neighbors = new ArrayList<NeighborTable>();
        neighbors.add(neighbor2);
        assertFalse(node.setNeighbors(neighbors));

        neighbors = new ArrayList<NeighborTable>();
        neighbors.add(neighbor3);
        neighbors.add(neighbor1);
        assertTrue(node.setNeighbors(neighbors));

        neighbors = new ArrayList<NeighborTable>();
        neighbors.add(neighbor1);
        neighbors.add(neighbor3);
        assertFalse(node.setNeighbors(neighbors));

        assertEquals(2, node.getNeighbors().size());
        assertTrue(node.setNeighbors(null));
        assertEquals(0, node.getNeighbors().size());
    }

    @Test
    public void testRoutingTableUpdate() {
        ZigBeeNode node = new ZigBeeNode(null);
        List<RoutingTable> routes;

        RoutingTable route1 = new RoutingTable();
        route1.setDestinationAddress(12345);
        route1.setNextHopAddress(98765);
        route1.setStatus(DiscoveryState.ACTIVE);
        route1.setRouteRecordRequired(false);

        RoutingTable route2 = new RoutingTable();
        route2.setDestinationAddress(12345);
        route2.setNextHopAddress(98765);
        route2.setStatus(DiscoveryState.ACTIVE);
        route2.setRouteRecordRequired(false);

        RoutingTable route3 = new RoutingTable();
        route3.setDestinationAddress(12345);
        route3.setNextHopAddress(98765);
        route3.setStatus(DiscoveryState.INACTIVE);
        route3.setRouteRecordRequired(false);

        RoutingTable route4 = new RoutingTable();
        route4.setDestinationAddress(54321);
        route4.setNextHopAddress(98765);
        route4.setStatus(DiscoveryState.INACTIVE);
        route4.setRouteRecordRequired(false);

        assertFalse(node.setRoutes(null));

        routes = new ArrayList<RoutingTable>();
        routes.add(route1);
        assertTrue(node.setRoutes(routes));

        routes = new ArrayList<RoutingTable>();
        routes.add(route2);
        assertFalse(node.setRoutes(routes));

        routes = new ArrayList<RoutingTable>();
        routes.add(route3);
        assertTrue(node.setRoutes(routes));

        routes = new ArrayList<RoutingTable>();
        routes.add(route1);
        routes.add(route4);
        assertTrue(node.setRoutes(routes));

        assertEquals(2, node.getRoutes().size());
        assertTrue(node.setRoutes(null));
        assertEquals(0, node.getRoutes().size());
    }

}