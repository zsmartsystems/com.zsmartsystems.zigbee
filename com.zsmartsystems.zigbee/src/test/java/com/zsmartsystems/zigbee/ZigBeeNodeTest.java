/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;
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
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));
        node.setPowerDescriptor(null);
        assertEquals(null, node.getPowerDescriptor());

        System.out.println(node.toString());
    }

    @Test
    public void testSetIeeeAddress() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));
        node.setIeeeAddress(new IeeeAddress("17880100dc880b"));
        assertEquals(new IeeeAddress("17880100dc880b"), node.getIeeeAddress());

        System.out.println(node.toString());
    }

    @Test
    public void testSetPowerDescriptor() {
        PowerDescriptor descriptor = new PowerDescriptor(1, 2, 4, 0xc);
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));
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
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return neighbor;
    }

    @Test
    public void testNeighborTableUpdate() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));
        List<NeighborTable> neighbors;

        NeighborTable neighbor1 = getNeighborTable(12345, "123456789", 0);
        NeighborTable neighbor2 = getNeighborTable(12345, "123456789", 0);
        NeighborTable neighbor3 = getNeighborTable(54321, "987654321", 0);

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
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));
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

    @Test
    public void testDeviceTypes() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));
        assertFalse(node.isFullFuntionDevice());
        assertFalse(node.isReducedFuntionDevice());
        assertFalse(node.isPrimaryTrustCenter());
        assertFalse(node.isSecurityCapable());

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
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));
        assertNull(node.getLastUpdateTime());
        node.setLastUpdateTime();
        assertNotNull(node.getLastUpdateTime());
    }

    @Test
    public void testJoiningEnabled() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));

        node.setJoining(true);
        assertTrue(node.isJoiningEnabled());
    }

    @Test
    public void testAddServer() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));

        ZigBeeServer server = Mockito.mock(ZigBeeServer.class);
        Mockito.when(server.getClusterId()).thenReturn(123);

        assertNull(node.getServer(123));
        node.addServer(server);
        assertEquals(server, node.getServer(123));
    }

    @Test
    public void testMatchDescriptor() {
        ZigBeeNetworkManager mockedNetworkManager = Mockito.mock(ZigBeeNetworkManager.class);

        ZigBeeNode node = new ZigBeeNode(mockedNetworkManager);
        ZigBeeAddress sourceAddress = new ZigBeeDeviceAddress(1234, 56);
        node.setNetworkAddress(sourceAddress.getAddress());

        ArgumentCaptor<Command> mockedCommandCaptor = ArgumentCaptor.forClass(Command.class);
        ArgumentCaptor<Command> mockedServerCommandCaptor = ArgumentCaptor.forClass(Command.class);

        try {
            Mockito.doAnswer(new Answer<Integer>() {
                @Override
                public Integer answer(InvocationOnMock invocation) {
                    return 0;
                }
            }).when(mockedNetworkManager).sendCommand(mockedCommandCaptor.capture());
        } catch (ZigBeeException e) {
            e.printStackTrace();
        }

        ZigBeeServer server = Mockito.mock(ZigBeeServer.class);
        Mockito.when(server.getClusterId()).thenReturn(123);
        node.addServer(server);

        Mockito.doNothing().when(server).commandReceived(mockedServerCommandCaptor.capture());

        List<Integer> outClusterList = new ArrayList<Integer>();
        outClusterList.add(123);
        MatchDescriptorRequest matchDescriptor = new MatchDescriptorRequest();
        matchDescriptor.setSourceAddress(sourceAddress);
        matchDescriptor.setProfileId(0x104);
        matchDescriptor.setOutClusterList(outClusterList);
        node.commandReceived(matchDescriptor);

        assertEquals(1, mockedCommandCaptor.getAllValues().size());

        Command command = mockedCommandCaptor.getValue();

        assertEquals(Integer.valueOf(32774), command.getClusterId());
        assertTrue(command instanceof MatchDescriptorResponse);

        MatchDescriptorResponse response = (MatchDescriptorResponse) command;
        assertEquals(new ZigBeeDeviceAddress(1234, 56), response.getDestinationAddress());
        assertEquals(1, response.getMatchList().size());
        assertEquals(Integer.valueOf(1), response.getMatchList().get(0));

        // Make sure the command was passed to the server
        assertEquals(1, mockedServerCommandCaptor.getAllValues().size());
    }

    @Test
    public void testAssociatedDevices() {
        ZigBeeNode node = new ZigBeeNode(Mockito.mock(ZigBeeNetworkManager.class));

        // Check list is empty to start
        assertNotNull(node.getAssociatedDevices());
        assertEquals(0, node.getAssociatedDevices().size());

        // Add 3 nodes
        List<Integer> associatedDevices = new ArrayList<Integer>();
        associatedDevices.add(1);
        associatedDevices.add(2);
        associatedDevices.add(3);
        boolean changed = node.setAssociatedDevices(associatedDevices);
        assertTrue(changed);
        assertEquals(3, node.getAssociatedDevices().size());

        // Remove 1 node
        associatedDevices = new ArrayList<Integer>();
        associatedDevices.add(1);
        associatedDevices.add(3);
        changed = node.setAssociatedDevices(associatedDevices);
        assertTrue(changed);
        assertEquals(2, node.getAssociatedDevices().size());
        assertEquals(Integer.valueOf(1), node.getAssociatedDevices().get(0));
        assertEquals(Integer.valueOf(3), node.getAssociatedDevices().get(1));

        // Add the same list and make sure it shows no change
        changed = node.setAssociatedDevices(associatedDevices);
        assertFalse(changed);

        // Add a new node
        associatedDevices = new ArrayList<Integer>();
        associatedDevices.add(1);
        associatedDevices.add(3);
        associatedDevices.add(4);
        changed = node.setAssociatedDevices(associatedDevices);
        assertTrue(changed);
        assertEquals(3, node.getAssociatedDevices().size());
        assertEquals(Integer.valueOf(1), node.getAssociatedDevices().get(0));
        assertEquals(Integer.valueOf(3), node.getAssociatedDevices().get(1));
        assertEquals(Integer.valueOf(4), node.getAssociatedDevices().get(2));

        // Keep number the same, but change the list
        associatedDevices = new ArrayList<Integer>();
        associatedDevices.add(2);
        associatedDevices.add(3);
        associatedDevices.add(4);
        changed = node.setAssociatedDevices(associatedDevices);
        assertTrue(changed);
        assertEquals(3, node.getAssociatedDevices().size());
        assertEquals(Integer.valueOf(2), node.getAssociatedDevices().get(0));
        assertEquals(Integer.valueOf(3), node.getAssociatedDevices().get(1));
        assertEquals(Integer.valueOf(4), node.getAssociatedDevices().get(2));

        // Set to null
        changed = node.setAssociatedDevices(null);
        assertTrue(changed);
        assertNotNull(node.getAssociatedDevices());
        assertEquals(0, node.getAssociatedDevices().size());
    }

}
