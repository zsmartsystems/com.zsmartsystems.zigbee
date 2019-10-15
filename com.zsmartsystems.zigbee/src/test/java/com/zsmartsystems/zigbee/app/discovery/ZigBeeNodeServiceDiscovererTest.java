/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeNodeServiceDiscoverer.NodeDiscoveryTask;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionFuture;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zdo.ZdoCommandType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingResponse;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.CurrentPowerModeType;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNodeServiceDiscovererTest {
    static final int TIMEOUT = 5000;

    ZigBeeNetworkManager networkManager;
    ArgumentCaptor<ZigBeeNode> nodeCapture;
    Map<Integer, ZigBeeCommand> responses = new HashMap<Integer, ZigBeeCommand>();

    @Before
    public void setupTest() {
        networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        nodeCapture = ArgumentCaptor.forClass(ZigBeeNode.class);

        Mockito.doAnswer(new Answer<Future<CommandResult>>() {
            @Override
            public Future<CommandResult> answer(InvocationOnMock invocation) {
                ZigBeeCommand command = (ZigBeeCommand) invocation.getArguments()[0];

                ZigBeeTransactionFuture commandFuture = new ZigBeeTransactionFuture();
                CommandResult result = new CommandResult(responses.get(command.getClusterId()));
                commandFuture.set(result);
                return commandFuture;
            }
        }).when(networkManager).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class),
                ArgumentMatchers.any(ZigBeeTransactionMatcher.class));

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                Runnable runnable = (Runnable) invocation.getArguments()[0];
                new Thread(runnable).start();
                return null;
            }
        }).when(networkManager).scheduleTask(ArgumentMatchers.any(Runnable.class), ArgumentMatchers.any(long.class));

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                Runnable runnable = (Runnable) invocation.getArguments()[1];
                new Thread(runnable).start();
                return null;
            }
        }).when(networkManager).rescheduleTask(ArgumentMatchers.any(), ArgumentMatchers.any(Runnable.class),
                ArgumentMatchers.any(long.class));

        Mockito.when(networkManager.getLocalNwkAddress()).thenReturn(0);
    }

    @Test
    public void test() throws Exception {
        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        ZigBeeNodeServiceDiscoverer discoverer = new ZigBeeNodeServiceDiscoverer(networkManager, node);

        TestUtilities.setField(ZigBeeNodeServiceDiscoverer.class, discoverer, "retryPeriod", 1);

        NodeDescriptor initialNodeDescriptor = Mockito.mock(NodeDescriptor.class);
        Mockito.when(initialNodeDescriptor.getLogicalType()).thenReturn(LogicalType.UNKNOWN);
        Mockito.when(node.getNodeDescriptor()).thenReturn(initialNodeDescriptor);

        PowerDescriptor initialPowerDescriptor = Mockito.mock(PowerDescriptor.class);
        Mockito.when(initialPowerDescriptor.getCurrentPowerMode()).thenReturn(CurrentPowerModeType.UNKNOWN);
        Mockito.when(node.getPowerDescriptor()).thenReturn(initialPowerDescriptor);

        Mockito.when(node.getNetworkAddress()).thenReturn(123);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        discoverer.setMaxBackoff(10);
        assertEquals(10, discoverer.getMaxBackoff());

        assertTrue(discoverer.getTasks().isEmpty());

        assertEquals(node, discoverer.getNode());

        // Add all the required responses to a list
        NetworkAddressResponse nwkResponse = new NetworkAddressResponse();
        nwkResponse.setStatus(ZdoStatus.SUCCESS);
        nwkResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        nwkResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        nwkResponse.setNwkAddrRemoteDev(123);
        responses.put(ZdoCommandType.NETWORK_ADDRESS_REQUEST.getClusterId(), nwkResponse);

        IeeeAddressResponse ieeeResponse = new IeeeAddressResponse();
        ieeeResponse.setStatus(ZdoStatus.SUCCESS);
        ieeeResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        ieeeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        ieeeResponse.setIeeeAddrRemoteDev(new IeeeAddress("1234567890ABCDEF"));
        responses.put(ZdoCommandType.IEEE_ADDRESS_REQUEST.getClusterId(), ieeeResponse);

        NodeDescriptorResponse nodeResponse = new NodeDescriptorResponse();
        nodeResponse.setStatus(ZdoStatus.SUCCESS);
        nodeResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        nodeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        nodeResponse.setNwkAddrOfInterest(0);
        NodeDescriptor nodeDescriptor = new NodeDescriptor();
        nodeResponse.setNodeDescriptor(nodeDescriptor);
        responses.put(ZdoCommandType.NODE_DESCRIPTOR_REQUEST.getClusterId(), nodeResponse);

        PowerDescriptorResponse powerResponse = new PowerDescriptorResponse();
        powerResponse.setStatus(ZdoStatus.SUCCESS);
        powerResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        powerResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        powerResponse.setNwkAddrOfInterest(0);
        PowerDescriptor powerDescriptor = new PowerDescriptor();
        powerResponse.setPowerDescriptor(powerDescriptor);
        responses.put(ZdoCommandType.POWER_DESCRIPTOR_REQUEST.getClusterId(), powerResponse);

        ActiveEndpointsResponse endpointsResponse = new ActiveEndpointsResponse();
        endpointsResponse.setStatus(ZdoStatus.SUCCESS);
        endpointsResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        endpointsResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        endpointsResponse.setNwkAddrOfInterest(0);
        List<Integer> activeEpList = new ArrayList<Integer>();
        activeEpList.add(1);
        endpointsResponse.setActiveEpList(activeEpList);
        responses.put(ZdoCommandType.ACTIVE_ENDPOINTS_REQUEST.getClusterId(), endpointsResponse);

        SimpleDescriptorResponse simpleResponse = new SimpleDescriptorResponse();
        simpleResponse.setStatus(ZdoStatus.SUCCESS);
        simpleResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        simpleResponse.setDestinationAddress(new ZigBeeEndpointAddress(0, 1));
        simpleResponse.setNwkAddrOfInterest(0);
        SimpleDescriptor simpleDescriptor = new SimpleDescriptor();
        simpleDescriptor.setDeviceId(0);
        simpleDescriptor.setDeviceVersion(0);
        simpleDescriptor.setEndpoint(1);
        simpleDescriptor.setProfileId(0x104);
        List<Integer> inputClusterList = new ArrayList<Integer>();
        List<Integer> outputClusterList = new ArrayList<Integer>();
        simpleDescriptor.setInputClusterList(inputClusterList);
        simpleDescriptor.setOutputClusterList(outputClusterList);
        simpleResponse.setSimpleDescriptor(simpleDescriptor);
        responses.put(ZdoCommandType.SIMPLE_DESCRIPTOR_REQUEST.getClusterId(), simpleResponse);

        ManagementLqiResponse lqiRequest = new ManagementLqiResponse();
        lqiRequest.setStatus(ZdoStatus.SUCCESS);
        lqiRequest.setSourceAddress(new ZigBeeEndpointAddress(123));
        lqiRequest.setDestinationAddress(new ZigBeeEndpointAddress(0));
        lqiRequest.setStartIndex(0);
        lqiRequest.setNeighborTableEntries(0);
        lqiRequest.setNeighborTableList(new ArrayList<NeighborTable>());
        responses.put(ZdoCommandType.MANAGEMENT_LQI_REQUEST.getClusterId(), lqiRequest);

        ManagementRoutingResponse routingResponse = new ManagementRoutingResponse();
        routingResponse.setStatus(ZdoStatus.SUCCESS);
        routingResponse.setSourceAddress(new ZigBeeEndpointAddress(123));
        routingResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        routingResponse.setStartIndex(0);
        routingResponse.setRoutingTableList(new ArrayList<RoutingTable>());
        routingResponse.setRoutingTableEntries(0);
        responses.put(ZdoCommandType.MANAGEMENT_ROUTING_REQUEST.getClusterId(), routingResponse);

        discoverer.startDiscovery();

        // Then wait for the node to be updated
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).updateNode(nodeCapture.capture());

        ZigBeeNode discoveredNode = nodeCapture.getValue();
        assertEquals(new IeeeAddress("1234567890ABCDEF"), discoveredNode.getIeeeAddress());
        assertEquals(Integer.valueOf(123), discoveredNode.getNetworkAddress());
        ZigBeeEndpoint endpoint = discoveredNode.getEndpoint(1);
        assertEquals(1, endpoint.getEndpointId());
        assertEquals(node, endpoint.getParentNode());
    }

    @Test
    public void testLocal() throws Exception {
        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        ZigBeeNodeServiceDiscoverer discoverer = new ZigBeeNodeServiceDiscoverer(networkManager, node);

        TestUtilities.setField(ZigBeeNodeServiceDiscoverer.class, discoverer, "retryPeriod", 1);

        NodeDescriptor initialNodeDescriptor = Mockito.mock(NodeDescriptor.class);
        Mockito.when(initialNodeDescriptor.getLogicalType()).thenReturn(LogicalType.UNKNOWN);
        Mockito.when(node.getNodeDescriptor()).thenReturn(initialNodeDescriptor);

        PowerDescriptor initialPowerDescriptor = Mockito.mock(PowerDescriptor.class);
        Mockito.when(initialPowerDescriptor.getCurrentPowerMode()).thenReturn(CurrentPowerModeType.UNKNOWN);
        Mockito.when(node.getPowerDescriptor()).thenReturn(initialPowerDescriptor);

        // Use node 0 and we should not try and get the local endpoints
        Mockito.when(node.getNetworkAddress()).thenReturn(0);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        ScheduledFuture<?> futureTask = Mockito.mock(ScheduledFuture.class);
        TestUtilities.setField(ZigBeeNodeServiceDiscoverer.class, discoverer, "futureTask", futureTask);

        discoverer.startDiscovery();

        Mockito.verify(futureTask, Mockito.times(1)).cancel(true);

        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).updateNode(nodeCapture.capture());

        assertNotNull(discoverer.getLastDiscoveryStarted());
        assertNotNull(discoverer.getLastDiscoveryCompleted());

        TestUtilities.setField(ZigBeeNodeServiceDiscoverer.class, discoverer, "futureTask",
                Mockito.mock(ScheduledFuture.class));
        discoverer.stopDiscovery();
    }

    @Test
    public void updateMesh() throws Exception {
        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        ZigBeeNodeServiceDiscoverer discoverer = new ZigBeeNodeServiceDiscoverer(networkManager, node);
        discoverer.setUpdateMeshTasks(
                Arrays.asList(new NodeDiscoveryTask[] { NodeDiscoveryTask.ROUTES, NodeDiscoveryTask.NEIGHBORS }));

        Mockito.when(
                networkManager.scheduleTask(ArgumentMatchers.any(Runnable.class), ArgumentMatchers.any(long.class)))
                .thenReturn(null);
        TestUtilities.setField(ZigBeeNodeServiceDiscoverer.class, discoverer, "retryPeriod", 1000);

        NodeDescriptor initialNodeDescriptor = Mockito.mock(NodeDescriptor.class);
        Mockito.when(initialNodeDescriptor.getLogicalType()).thenReturn(LogicalType.END_DEVICE);
        Mockito.when(node.getLogicalType()).thenAnswer(x -> {
            if (node.getNodeDescriptor() == null) {
                return LogicalType.UNKNOWN;
            } else {
                return node.getNodeDescriptor().getLogicalType();
            }
        });

        PowerDescriptor initialPowerDescriptor = Mockito.mock(PowerDescriptor.class);
        Mockito.when(initialPowerDescriptor.getCurrentPowerMode()).thenReturn(CurrentPowerModeType.UNKNOWN);
        Mockito.when(node.getPowerDescriptor()).thenReturn(initialPowerDescriptor);

        Mockito.when(node.getNetworkAddress()).thenReturn(1);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        ScheduledFuture<?> futureTask = Mockito.mock(ScheduledFuture.class);
        TestUtilities.setField(ZigBeeNodeServiceDiscoverer.class, discoverer, "futureTask", futureTask);

        discoverer.updateMesh();
        assertEquals(LogicalType.UNKNOWN, discoverer.getNode().getLogicalType());
        assertFalse(discoverer.getTasks().contains(NodeDiscoveryTask.ROUTES));
        assertFalse(discoverer.getTasks().contains(NodeDiscoveryTask.NEIGHBORS));

        Mockito.when(node.getNodeDescriptor()).thenReturn(initialNodeDescriptor);

        discoverer.updateMesh();
        assertEquals(LogicalType.END_DEVICE, discoverer.getNode().getLogicalType());
        assertFalse(discoverer.getTasks().contains(NodeDiscoveryTask.ROUTES));
        assertFalse(discoverer.getTasks().contains(NodeDiscoveryTask.NEIGHBORS));

        Mockito.when(initialNodeDescriptor.getLogicalType()).thenReturn(LogicalType.ROUTER);
        discoverer.updateMesh();
        assertTrue(discoverer.getTasks().contains(NodeDiscoveryTask.ROUTES));
        assertTrue(discoverer.getTasks().contains(NodeDiscoveryTask.NEIGHBORS));

        discoverer.getTasks().clear();

        discoverer.setUpdateMeshTasks(Arrays.asList(new NodeDiscoveryTask[] { NodeDiscoveryTask.NEIGHBORS }));

        discoverer.updateMesh();
        assertFalse(discoverer.getTasks().contains(NodeDiscoveryTask.ROUTES));
        assertTrue(discoverer.getTasks().contains(NodeDiscoveryTask.NEIGHBORS));

        discoverer.stopDiscovery();
    }
}
