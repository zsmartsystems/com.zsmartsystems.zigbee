/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.serialization.DefaultDeserializer;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransaction;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionFuture;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zdo.ZdoCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommandType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ManagementBindResponse;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;
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
    static final int TIMEOUT = 5000;

    @Test
    public void testAddDescriptors() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress(), 1);

        assertEquals(Integer.valueOf(1), node.getNetworkAddress());

        // Null by default
        assertNull(node.getNodeDescriptor());
        assertNull(node.getPowerDescriptor());

        assertEquals(LogicalType.UNKNOWN, node.getLogicalType());

        node.setPowerDescriptor(new PowerDescriptor());
        assertNotNull(node.getPowerDescriptor());
        node.setNodeDescriptor(new NodeDescriptor());
        assertNotNull(node.getNodeDescriptor());

        System.out.println(node.toString());
    }

    @Test
    public void testSetIeeeAddress() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("17880100dc880b"));
        assertEquals(new IeeeAddress("17880100dc880b"), node.getIeeeAddress());

        System.out.println(node.toString());
    }

    @Test
    public void testSetPowerDescriptor() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        PowerDescriptor descriptor = new PowerDescriptor(1, 2, 4, 0xc);
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress());
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
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress());
        Set<NeighborTable> neighbors;

        assertTrue(node.getNeighbors().isEmpty());

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
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress());
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
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress());
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
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress());
        assertNull(node.getLastUpdateTime());
        node.setLastUpdateTime();
        assertNotNull(node.getLastUpdateTime());
    }

    @Test
    public void testAssociatedDevices() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress());

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
    public void testUpdated() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        ZigBeeNode newNode = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        ZigBeeNode invalidNode = new ZigBeeNode(getMocketNetworkManager(),
                new IeeeAddress("ABCDEF1234567890"));
        assertTrue(node.setNetworkAddress(1234));
        assertTrue(newNode.setNetworkAddress(1234));

        assertFalse(node.updateNode(invalidNode));

        assertFalse(node.updateNode(newNode));

        assertNull(node.getNodeDescriptor());
        newNode = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        NodeDescriptor nodeDescriptor = Mockito.mock(NodeDescriptor.class);
        Mockito.when(nodeDescriptor.getLogicalType()).thenReturn(LogicalType.COORDINATOR);
        newNode.setNodeDescriptor(nodeDescriptor);
        assertTrue(node.updateNode(newNode));
        assertNotNull(node.getNodeDescriptor());

        assertNull(node.getPowerDescriptor());
        newNode = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        PowerDescriptor powerDescriptor = Mockito.mock(PowerDescriptor.class);
        newNode.setPowerDescriptor(powerDescriptor);
        assertTrue(node.updateNode(newNode));
        assertNotNull(node.getPowerDescriptor());

        Integer oldNwkAddress = newNode.getNetworkAddress();
        assertTrue(newNode.setNetworkAddress(5678));
        assertNotEquals(oldNwkAddress, newNode.getNetworkAddress());
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

        newNode = new ZigBeeNode(getMocketNetworkManager(), node.getIeeeAddress());
        ZigBeeEndpoint endpoint = new ZigBeeEndpoint(newNode, 1);
        newNode.addEndpoint(endpoint);
        System.out.println(newNode);
        assertTrue(node.updateNode(newNode));
        assertFalse(node.updateNode(newNode));
        assertEquals(1, node.getEndpoints().size());

        endpoint = new ZigBeeEndpoint(newNode, 2);
        newNode.addEndpoint(endpoint);
        System.out.println(newNode);
        assertTrue(node.updateNode(newNode));
        assertFalse(node.updateNode(newNode));
        assertEquals(2, node.getEndpoints().size());

        Set<BindingTable> bindingTable = new HashSet<BindingTable>();
        bindingTable.add(new BindingTable());
        assertEquals(0, node.getBindingTable().size());
        newNode = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        TestUtilities.setField(ZigBeeNode.class, newNode, "bindingTable", bindingTable);
        assertFalse(node.updateNode(newNode));
        assertEquals(0, node.getBindingTable().size());

        TestUtilities.setField(ZigBeeNode.class, newNode, "bindingTableSet", true);
        assertTrue(node.updateNode(newNode));
        assertEquals(1, node.getBindingTable().size());

        newNode = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        assertFalse(node.updateNode(newNode));
        assertEquals(1, node.getBindingTable().size());

        Set<RoutingTable> routeTable = new HashSet<RoutingTable>();
        routeTable.add(new RoutingTable());
        assertEquals(0, node.getRoutes().size());
        newNode = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        newNode.setRoutes(routeTable);
        assertTrue(node.updateNode(newNode));
        assertEquals(1, node.getRoutes().size());

        newNode = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        newNode.setNodeState(ZigBeeNodeState.ONLINE);
        assertTrue(node.updateNode(newNode));
        newNode.setNodeState(ZigBeeNodeState.UNKNOWN);
        assertFalse(node.updateNode(newNode));
    }

    @Test
    public void isDiscovered() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));
        ZigBeeNetworkEndpointListener listener = Mockito.mock(ZigBeeNetworkEndpointListener.class);
        node.addNetworkEndpointListener(listener);

        assertFalse(node.isDiscovered());
        assertNotNull(node.getEndpoints());

        NodeDescriptor descriptor = new NodeDescriptor(0, 3333, 74, true, 6666, 0, 6, 4444, true, 8);
        node.setNodeDescriptor(descriptor);
        assertFalse(node.isDiscovered());

        ZigBeeEndpoint endpoint = new ZigBeeEndpoint(node, 1);
        node.addEndpoint(endpoint);
        assertTrue(node.isDiscovered());
        Mockito.verify(listener, Mockito.timeout(TIMEOUT)).deviceAdded(endpoint);

        assertEquals(endpoint, node.getEndpoint(1));
        assertEquals(1, node.getEndpoints().size());

        node.removeEndpoint(1);
        assertEquals(0, node.getEndpoints().size());
        Mockito.verify(listener, Mockito.timeout(TIMEOUT)).deviceRemoved(endpoint);

        node.removeNetworkEndpointListener(listener);
    }

    @Test
    public void commandReceived() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNetworkManager networkManager = getMocketNetworkManager();
        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress("1234567890"));
        assertTrue(node.setNetworkAddress(12345));

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
        node.commandReceived(zigbeeCommand, null, null);
        Mockito.verify(endpoint1, Mockito.times(0)).commandReceived(ArgumentMatchers.any(ZclCommand.class));
        Mockito.verify(endpoint2, Mockito.times(0)).commandReceived(ArgumentMatchers.any(ZclCommand.class));

        ZigBeeAddress zigbeeAddress = Mockito.mock(ZigBeeAddress.class);
        Mockito.when(zigbeeAddress.getAddress()).thenReturn(124);
        ZigBeeCommand zigbeeCommandInvalidAddressCmd = Mockito.mock(ZigBeeCommand.class);
        Mockito.when(zigbeeCommandInvalidAddressCmd.getSourceAddress()).thenReturn(zigbeeAddress);
        node.commandReceived(zigbeeCommandInvalidAddressCmd, null, null);
        Mockito.verify(endpoint1, Mockito.times(0)).commandReceived(ArgumentMatchers.any(ZclCommand.class));
        Mockito.verify(endpoint2, Mockito.times(0)).commandReceived(ArgumentMatchers.any(ZclCommand.class));

        ZclCommand unicast = Mockito.mock(ZclCommand.class);
        ZigBeeEndpointAddress unicastDestination = Mockito.mock(ZigBeeEndpointAddress.class);
        Mockito.when(unicastDestination.getAddress()).thenReturn(123);
        Mockito.when(unicastDestination.getEndpoint()).thenReturn(1);
        Mockito.when(unicast.getSourceAddress()).thenReturn(sourceAddress);
        Mockito.when(unicast.getDestinationAddress()).thenReturn(unicastDestination);
        Mockito.when(unicast.getClusterId()).thenReturn(1);
        Mockito.when(unicast.getTransactionId()).thenReturn(123);
        Mockito.when(unicast.getCommandId()).thenReturn(99);

        node.commandReceived(unicast, null, null);
        Mockito.verify(endpoint1, Mockito.times(1)).commandReceived(unicast);
        Mockito.verify(endpoint2, Mockito.times(0)).commandReceived(unicast);

        assertNull(node.getLinkQualityStatistics().getLastReceivedLqi());
        assertNull(node.getLinkQualityStatistics().getLastReceivedRssi());

        Mockito.when(sourceAddress.getEndpoint()).thenReturn(10);
        node.commandReceived(unicast, 1, 2);
        ArgumentCaptor<ZigBeeCommand> commandCapture = ArgumentCaptor.forClass(ZigBeeCommand.class);
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).sendTransaction(commandCapture.capture());
        ZigBeeCommand response = commandCapture.getValue();
        assertTrue(response instanceof DefaultResponse);
        System.out.println(response);
        DefaultResponse defaultResponse = (DefaultResponse) response;
        assertEquals(Integer.valueOf(1), defaultResponse.getClusterId());
        assertEquals(Integer.valueOf(99), defaultResponse.getCommandIdentifier());
        assertEquals(ZclStatus.UNSUPPORTED_CLUSTER, defaultResponse.getStatusCode());
        assertEquals(Integer.valueOf(123), defaultResponse.getTransactionId());

        assertEquals(Integer.valueOf(2), node.getLinkQualityStatistics().getLastReceivedLqi());
        assertEquals(Integer.valueOf(1), node.getLinkQualityStatistics().getLastReceivedRssi());

        ZdoCommand zdoCommand = Mockito.mock(ZdoCommand.class);
        ZigBeeAddress zdoSource = Mockito.mock(ZigBeeAddress.class);
        Mockito.when(zdoSource.getAddress()).thenReturn(12345);
        Mockito.when(zdoCommand.getSourceAddress()).thenReturn(zdoSource);
        node.commandReceived(zdoCommand, null, null);
    }

    @Test
    public void setNodeState() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNode node = new ZigBeeNode(getMocketNetworkManager(), new IeeeAddress("1234567890"));

        assertFalse(node.setNodeState(ZigBeeNodeState.UNKNOWN));
        assertTrue(node.setNodeState(ZigBeeNodeState.ONLINE));
        assertEquals(ZigBeeNodeState.ONLINE, node.getNodeState());
        assertTrue(node.setNodeState(ZigBeeNodeState.OFFLINE));
        assertFalse(node.setNodeState(ZigBeeNodeState.OFFLINE));
        assertEquals(ZigBeeNodeState.OFFLINE, node.getNodeState());
    }

    @Test
    public void updateBindingTable() throws InterruptedException, ExecutionException {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNetworkManager networkManager = getMocketNetworkManager();
        Map<Integer, ZigBeeCommand> responses = new HashMap<Integer, ZigBeeCommand>();

        Mockito.doAnswer(new Answer<Future<CommandResult>>() {
            @Override
            public Future<CommandResult> answer(InvocationOnMock invocation) {
                ZigBeeCommand command = (ZigBeeCommand) invocation.getArguments()[0];

                ZigBeeTransactionFuture commandFuture = new ZigBeeTransactionFuture(
                        Mockito.mock(ZigBeeTransaction.class));
                CommandResult result = new CommandResult(ZigBeeStatus.SUCCESS, responses.get(command.getClusterId()));
                commandFuture.set(result);
                return commandFuture;
            }
        }).when(networkManager).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class),
                ArgumentMatchers.any(ZigBeeTransactionMatcher.class));

        ZigBeeNode node = new ZigBeeNode(networkManager, new IeeeAddress("1234567890"));
        assertTrue(node.setNetworkAddress(1));

        ManagementBindResponse nodeResponse = new ManagementBindResponse(ZdoStatus.NOT_SUPPORTED, null, null, null);
        nodeResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        nodeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        responses.put(ZdoCommandType.MANAGEMENT_BIND_REQUEST.getClusterId(), nodeResponse);
        Future<ZigBeeStatus> future = node.updateBindingTable();
        assertEquals(ZigBeeStatus.UNSUPPORTED, future.get());

        nodeResponse = new ManagementBindResponse(ZdoStatus.NOT_PERMITTED, null, null, null);
        nodeResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        nodeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        responses.put(ZdoCommandType.MANAGEMENT_BIND_REQUEST.getClusterId(), nodeResponse);
        future = node.updateBindingTable();
        assertEquals(ZigBeeStatus.INVALID_STATE, future.get());

        nodeResponse = new ManagementBindResponse(ZdoStatus.NO_DESCRIPTOR, null, null, null);
        nodeResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        nodeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        responses.put(ZdoCommandType.MANAGEMENT_BIND_REQUEST.getClusterId(), nodeResponse);
        future = node.updateBindingTable();
        assertEquals(ZigBeeStatus.FAILURE, future.get());
    }

    private ZigBeeNetworkManager getMocketNetworkManager() {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        Mockito.when(networkManager.getNotificationService()).thenReturn(new NotificationService());
        return networkManager;
    }
}