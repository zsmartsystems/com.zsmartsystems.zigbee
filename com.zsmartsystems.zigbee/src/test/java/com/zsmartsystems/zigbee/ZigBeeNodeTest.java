/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.CurrentPowerModeType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.PowerLevelType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.PowerSourceType;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable.DiscoveryState;

public class ZigBeeNodeTest {
    @Test
    public void testAddDescriptors() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress());

        // Not null by default
        assertNotNull(node.getNodeDescriptor());
        assertNotNull(node.getPowerDescriptor());

        node.setPowerDescriptor(null);
        assertEquals(null, node.getPowerDescriptor());
        node.setNodeDescriptor(null);
        assertEquals(null, node.getPowerDescriptor());

        System.out.println(node.toString());
    }

    @Test
    public void testSetIeeeAddress() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress("17880100dc880b"));
        assertEquals(new IeeeAddress("17880100dc880b"), node.getIeeeAddress());

        System.out.println(node.toString());
    }

    @Test
    public void testSetPowerDescriptor() {
        PowerDescriptor descriptor = new PowerDescriptor(1, 2, 4, 0xc);
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress());
        node.setPowerDescriptor(descriptor);
        assertEquals(CurrentPowerModeType.RECEIVER_ON_PERIODICALLY, node.getPowerDescriptor().getCurrentPowerMode());
        assertEquals(PowerSourceType.DISPOSABLE_BATTERY, node.getPowerDescriptor().getCurrentPowerSource());
        assertEquals(PowerLevelType.FULL, node.getPowerDescriptor().getPowerLevel());
    }

    private NeighborTable getNeighborTable(Integer networkAddress, String ieeeAddressString, Integer lqi) {
        NeighborTable neighbor = new NeighborTable();
        try {
            IeeeAddress ieeeAddress = new IeeeAddress(ieeeAddressString);

            Field fieldNetworkAddress = NeighborTable.class.getDeclaredField("networkAddress");
            fieldNetworkAddress.setAccessible(true);
            fieldNetworkAddress.set(neighbor, networkAddress);

            Field fieldExtendedAddress = NeighborTable.class.getDeclaredField("extendedAddress");
            fieldExtendedAddress.setAccessible(true);
            fieldExtendedAddress.set(neighbor, ieeeAddress);

            Field fieldLqi = NeighborTable.class.getDeclaredField("lqi");
            fieldLqi.setAccessible(true);
            fieldLqi.set(neighbor, lqi);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return neighbor;
    }

    @Test
    public void testNeighborTableUpdate() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress());
        Set<NeighborTable> neighbors;

        NeighborTable neighbor1 = getNeighborTable(12345, "123456789", 0);
        NeighborTable neighbor2 = getNeighborTable(12345, "123456789", 0);
        NeighborTable neighbor3 = getNeighborTable(54321, "987654321", 0);

        neighbors = new HashSet<NeighborTable>();
        neighbors.add(neighbor1);
        assertTrue(node.setNeighbors(neighbors));

        neighbors = new HashSet<NeighborTable>();
        neighbors.add(neighbor2);
        assertFalse(node.setNeighbors(neighbors));

        neighbors = new HashSet<NeighborTable>();
        neighbors.add(neighbor3);
        neighbors.add(neighbor1);
        assertTrue(node.setNeighbors(neighbors));

        neighbors = new HashSet<NeighborTable>();
        neighbors.add(neighbor1);
        neighbors.add(neighbor3);
        assertFalse(node.setNeighbors(neighbors));

        assertEquals(2, node.getNeighbors().size());
    }

    @Test
    public void testRoutingTableUpdate() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress());
        Set<RoutingTable> routes;

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

        routes = new HashSet<RoutingTable>();
        routes.add(route1);
        assertTrue(node.setRoutes(routes));

        routes = new HashSet<RoutingTable>();
        routes.add(route2);
        assertFalse(node.setRoutes(routes));

        routes = new HashSet<RoutingTable>();
        routes.add(route3);
        assertTrue(node.setRoutes(routes));

        routes = new HashSet<RoutingTable>();
        routes.add(route1);
        routes.add(route4);
        assertTrue(node.setRoutes(routes));

        assertEquals(2, node.getRoutes().size());

        assertTrue(node.setRoutes(null));
        assertEquals(0, node.getRoutes().size());
    }

    @Test
    public void testDeviceTypes() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress());
        assertFalse(node.isFullFuntionDevice());
        assertFalse(node.isReducedFuntionDevice());
        assertFalse(node.isPrimaryTrustCenter());
        assertFalse(node.isSecurityCapable());

        assertEquals(LogicalType.UNKNOWN, node.getLogicalType());

        NodeDescriptor nodeDescriptor = new NodeDescriptor();
        node.setNodeDescriptor(nodeDescriptor);

        assertFalse(node.isFullFuntionDevice());
        assertFalse(node.isReducedFuntionDevice());
        assertFalse(node.isPrimaryTrustCenter());
        assertFalse(node.isSecurityCapable());

        nodeDescriptor = new NodeDescriptor(0, 0, 0xff, false, 0, 0, 0xff, 0, false, 0);
        node.setNodeDescriptor(nodeDescriptor);
        assertNotNull(node.getNodeDescriptor());

        assertTrue(node.isFullFuntionDevice());
        assertFalse(node.isReducedFuntionDevice());
        assertTrue(node.isPrimaryTrustCenter());
        assertTrue(node.isSecurityCapable());

        nodeDescriptor = new NodeDescriptor(0, 0, 0x00, false, 0, 0, 0xff, 0, false, 0);
        node.setNodeDescriptor(nodeDescriptor);

        assertFalse(node.isFullFuntionDevice());
        assertTrue(node.isReducedFuntionDevice());

        assertEquals(LogicalType.COORDINATOR, node.getLogicalType());
    }

    @Test
    public void testLastUpdate() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress());
        assertNull(node.getLastUpdateTime());
        node.setLastUpdateTime();
        assertNotNull(node.getLastUpdateTime());
    }

    @Test
    public void testAssociatedDevices() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress());

        // Check list is empty to start
        assertNotNull(node.getAssociatedDevices());
        assertEquals(0, node.getAssociatedDevices().size());

        // Add 3 nodes
        Set<Integer> associatedDevices = new HashSet<Integer>();
        associatedDevices.add(1);
        associatedDevices.add(2);
        associatedDevices.add(3);
        boolean changed = node.setAssociatedDevices(associatedDevices);
        assertTrue(changed);
        assertEquals(3, node.getAssociatedDevices().size());

        // Remove 1 node
        associatedDevices = new HashSet<Integer>();
        associatedDevices.add(1);
        associatedDevices.add(3);
        changed = node.setAssociatedDevices(associatedDevices);
        assertTrue(changed);
        assertEquals(2, node.getAssociatedDevices().size());

        Integer[] devices = new Integer[2];
        node.getAssociatedDevices().toArray(devices);
        assertEquals(Integer.valueOf(1), devices[0]);
        assertEquals(Integer.valueOf(3), devices[1]);

        // Add the same list and make sure it shows no change
        changed = node.setAssociatedDevices(associatedDevices);
        assertFalse(changed);

        // Add a new node
        associatedDevices = new HashSet<Integer>();
        associatedDevices.add(1);
        associatedDevices.add(3);
        associatedDevices.add(4);
        changed = node.setAssociatedDevices(associatedDevices);
        assertTrue(changed);
        assertEquals(3, node.getAssociatedDevices().size());

        devices = new Integer[3];
        node.getAssociatedDevices().toArray(devices);
        assertEquals(Integer.valueOf(1), devices[0]);
        assertEquals(Integer.valueOf(3), devices[1]);
        assertEquals(Integer.valueOf(4), devices[2]);

        // Keep number the same, but change the list
        associatedDevices = new HashSet<Integer>();
        associatedDevices.add(2);
        associatedDevices.add(3);
        associatedDevices.add(4);
        changed = node.setAssociatedDevices(associatedDevices);
        assertTrue(changed);
        assertEquals(3, node.getAssociatedDevices().size());

        devices = new Integer[3];
        node.getAssociatedDevices().toArray(devices);
        assertEquals(Integer.valueOf(2), devices[0]);
        assertEquals(Integer.valueOf(3), devices[1]);
        assertEquals(Integer.valueOf(4), devices[2]);
    }

    private NeighborTable getNeighborTable(int[] packet) {
        DefaultDeserializer deserializer = new DefaultDeserializer(packet);

        NeighborTable neighbor = new NeighborTable();
        neighbor.deserialize(deserializer);

        return neighbor;
    }

    @Test
    public void testUpdated() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress("1234567890"));
        ZigBeeNode newNode = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress("1234567890"));
        ZigBeeNode invalidNode = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class),
                new IeeeAddress("ABCDEF1234567890"));
        node.setNetworkAddress(1234);
        newNode.setNetworkAddress(1234);

        assertFalse(node.updateNode(invalidNode));

        assertFalse(node.updateNode(newNode));

        newNode.setNetworkAddress(5678);
        assertTrue(node.updateNode(newNode));

        Set<Integer> associated = new HashSet<Integer>();
        associated.add(1);
        associated.add(2);
        associated.add(3);
        node.setAssociatedDevices(associated);
        associated = new HashSet<Integer>();
        associated.add(1);
        associated.add(2);
        associated.add(3);
        newNode.setAssociatedDevices(associated);
        assertFalse(node.updateNode(newNode));

        associated = new HashSet<Integer>();
        associated.add(3);
        associated.add(2);
        associated.add(1);
        newNode.setAssociatedDevices(associated);
        assertFalse(node.updateNode(newNode));

        associated = new HashSet<Integer>();
        associated.add(1);
        associated.add(3);
        newNode.setAssociatedDevices(associated);
        assertTrue(node.updateNode(newNode));

        Set<NeighborTable> neighbors = new HashSet<NeighborTable>();
        neighbors.add(getNeighborTable(new int[] { 0xB1, 0x68, 0xDE, 0x3A, 0x00, 0x00, 0x00, 0x00, 0x86, 0x06, 0x00,
                0x00, 0x00, 0xEE, 0x1F, 0x00, 0xA9, 0x44, 0x25, 0x02, 0x0F, 0xE2 }));
        node.setNeighbors(neighbors);

        neighbors = new HashSet<NeighborTable>();
        neighbors.add(getNeighborTable(new int[] { 0xB1, 0x68, 0xDE, 0x3A, 0x00, 0x00, 0x00, 0x00, 0x86, 0x06, 0x00,
                0x00, 0x00, 0xEE, 0x1F, 0x00, 0xA9, 0x44, 0x25, 0x02, 0x0F, 0xE2 }));
        newNode.setNeighbors(neighbors);
        assertFalse(node.updateNode(newNode));

        neighbors = new HashSet<NeighborTable>();
        neighbors.add(getNeighborTable(new int[] { 0xB1, 0x68, 0xDE, 0x3A, 0x00, 0x00, 0x00, 0x00, 0x86, 0x06, 0x00,
                0x00, 0x00, 0xEE, 0x1F, 0x00, 0xA9, 0x44, 0x25, 0x02, 0x0F, 0xE2 }));
        neighbors.add(getNeighborTable(new int[] { 0xB1, 0x68, 0xDE, 0x3A, 0x00, 0x00, 0x00, 0x00, 0x84, 0x06, 0x00,
                0x00, 0x00, 0xEE, 0x1F, 0x00, 0xA9, 0x44, 0x25, 0x02, 0x0F, 0xE2 }));
        newNode.setNeighbors(neighbors);
        assertTrue(node.updateNode(newNode));

        neighbors = new HashSet<NeighborTable>();
        neighbors.add(getNeighborTable(new int[] { 0xB1, 0x68, 0xDE, 0x3A, 0x00, 0x00, 0x00, 0x00, 0x86, 0x06, 0x00,
                0x00, 0x00, 0xEE, 0x1F, 0x00, 0xA9, 0x44, 0x25, 0x02, 0x0F, 0xE2 }));
        neighbors.add(getNeighborTable(new int[] { 0xB0, 0x68, 0xDE, 0x3A, 0x00, 0x00, 0x00, 0x00, 0x84, 0x06, 0x00,
                0x00, 0x00, 0xEE, 0x1F, 0x00, 0xA9, 0x44, 0x25, 0x02, 0x0F, 0xE2 }));
        newNode.setNeighbors(neighbors);
        assertTrue(node.updateNode(newNode));

        newNode.setNeighbors(neighbors);
        assertFalse(node.updateNode(newNode));

        assertEquals(2, node.getNeighbors().size());
        assertTrue(node.setNeighbors(null));
        assertEquals(0, node.getNeighbors().size());
    }

    @Test
    public void isDiscovered() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress("1234567890"));
        assertFalse(node.isDiscovered());

        NodeDescriptor descriptor = new NodeDescriptor(0, 3333, 74, true, 6666, 0, 6, 4444, true, 8);
        node.setNodeDescriptor(descriptor);
        assertFalse(node.isDiscovered());

        node.addEndpoint(new ZigBeeEndpoint(node, 1));
        assertTrue(node.isDiscovered());
    }

    @Test
    public void commandReceived() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress("1234567890"));
        node.setNetworkAddress(12345);

        ZigBeeEndpoint endpoint1 = Mockito.mock(ZigBeeEndpoint.class);
        Mockito.when(endpoint1.getEndpointId()).thenReturn(1);
        ZigBeeEndpoint endpoint2 = Mockito.mock(ZigBeeEndpoint.class);
        Mockito.when(endpoint2.getEndpointId()).thenReturn(2);

        node.addEndpoint(endpoint1);
        node.addEndpoint(endpoint2);

        ZigBeeEndpointAddress sourceAddress = Mockito.mock(ZigBeeEndpointAddress.class);
        Mockito.when(sourceAddress.getAddress()).thenReturn(12345);
        Mockito.when(sourceAddress.getEndpoint()).thenReturn(1);

        ZigBeeEndpointAddress invalidSourceAddress = Mockito.mock(ZigBeeEndpointAddress.class);
        Mockito.when(invalidSourceAddress.getAddress()).thenReturn(1234);
        Mockito.when(invalidSourceAddress.getEndpoint()).thenReturn(1);
        ZclCommand zigbeeCommand = Mockito.mock(ZclCommand.class);
        Mockito.when(zigbeeCommand.getSourceAddress()).thenReturn(invalidSourceAddress);
        node.commandReceived(zigbeeCommand);
        Mockito.verify(endpoint1, Mockito.times(0)).commandReceived(ArgumentMatchers.any(ZclCommand.class));
        Mockito.verify(endpoint2, Mockito.times(0)).commandReceived(ArgumentMatchers.any(ZclCommand.class));

        ZigBeeAddress zigbeeAddress = Mockito.mock(ZigBeeAddress.class);
        Mockito.when(zigbeeAddress.getAddress()).thenReturn(124);
        ZigBeeCommand zigbeeCommandInvalidAddressCmd = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(zigbeeCommandInvalidAddressCmd.getSourceAddress()).thenReturn(zigbeeAddress);
        node.commandReceived(zigbeeCommandInvalidAddressCmd);
        Mockito.verify(endpoint1, Mockito.times(0)).commandReceived(ArgumentMatchers.any(ZclCommand.class));
        Mockito.verify(endpoint2, Mockito.times(0)).commandReceived(ArgumentMatchers.any(ZclCommand.class));

        ZclCommand unicast = Mockito.mock(ZclCommand.class);
        ZigBeeEndpointAddress unicastDestination = Mockito.mock(ZigBeeEndpointAddress.class);
        Mockito.when(unicastDestination.getAddress()).thenReturn(123);
        Mockito.when(unicastDestination.getEndpoint()).thenReturn(1);
        Mockito.when(unicast.getSourceAddress()).thenReturn(sourceAddress);
        Mockito.when(unicast.getDestinationAddress()).thenReturn(unicastDestination);

        node.commandReceived(unicast);
        Mockito.verify(endpoint1, Mockito.times(1)).commandReceived(unicast);
        Mockito.verify(endpoint2, Mockito.times(0)).commandReceived(unicast);
    }

    @Test
    public void setNodeState() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class), new IeeeAddress("1234567890"));

        assertFalse(node.setNodeState(ZigBeeNodeState.UNKNOWN));
        assertTrue(node.setNodeState(ZigBeeNodeState.ONLINE));
        assertTrue(node.setNodeState(ZigBeeNodeState.OFFLINE));
        assertFalse(node.setNodeState(ZigBeeNodeState.OFFLINE));
    }
}