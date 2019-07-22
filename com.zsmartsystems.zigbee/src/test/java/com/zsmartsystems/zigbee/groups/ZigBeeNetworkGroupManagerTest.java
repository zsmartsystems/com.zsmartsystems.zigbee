/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransaction;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionFuture;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclGroupsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.MacCapabilitiesType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkGroupManagerTest {

    @Test
    public void updateGroups() throws Exception {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeNetworkGroupManager groupManager = new ZigBeeNetworkGroupManager(networkManager);

        Set<ZigBeeNode> nodes = new HashSet<>();
        Collection<ZigBeeEndpoint> endpoints = new HashSet<>();
        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getNetworkAddress()).thenReturn(1);
        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        endpoints.add(endpoint);
        Mockito.when(node.getEndpoints()).thenReturn(endpoints);
        ZclCluster cluster = new ZclGroupsCluster(endpoint);
        Mockito.when(endpoint.getInputCluster(ArgumentMatchers.anyInt())).thenReturn(cluster);
        nodes.add(node);

        Mockito.when(networkManager.getNodes()).thenReturn(nodes);

        Queue<ZigBeeCommand> responses = new LinkedList<>();
        Mockito.doAnswer(new Answer<Future<CommandResult>>() {
            @Override
            public Future<CommandResult> answer(InvocationOnMock invocation) {
                ZigBeeTransactionFuture commandFuture = new ZigBeeTransactionFuture(
                        Mockito.mock(ZigBeeTransaction.class));
                CommandResult result = new CommandResult(responses.poll());
                commandFuture.set(result);
                return commandFuture;
            }
        }).when(networkManager).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class),
                ArgumentMatchers.any(ZigBeeTransactionMatcher.class));

        ZigBeeNode node2 = new ZigBeeNode(networkManager, new IeeeAddress("1234567890"), 1);

        endpoint = new ZigBeeEndpoint(node2, 1);

        List<Integer> groupList = new ArrayList<>();
        groupList.add(1);
        groupList.add(3);
        GetGroupMembershipResponse groupsResponse = new GetGroupMembershipResponse();
        groupsResponse.setGroupList(groupList);
        responses.add(groupsResponse);

        ViewGroupResponse groupResponse = new ViewGroupResponse();
        groupResponse.setGroupId(1);
        groupResponse.setGroupName("Label 1");
        responses.add(groupResponse);

        groupResponse = new ViewGroupResponse();
        groupResponse.setGroupId(3);
        responses.add(groupResponse);

        // Without the RECEIVER_ON_WHEN_IDLE an empty map (nodeDescriptor is null)
        Future<Map<ZigBeeEndpointAddress, ZigBeeGroupResponse>> future = groupManager.getGroups();
        assertEquals(0, future.get().size());

        // Now test with empty descriptor
        NodeDescriptor nodeDescriptor = Mockito.mock(NodeDescriptor.class);
        Set<MacCapabilitiesType> capabilities = new HashSet<>();
        Mockito.when(nodeDescriptor.getMacCapabilities()).thenReturn(capabilities);
        Mockito.when(node.getNodeDescriptor()).thenReturn(nodeDescriptor);

        // Without the RECEIVER_ON_WHEN_IDLE an empty map
        future = groupManager.getGroups();
        assertEquals(0, future.get().size());

        // First attempt without the cluster will be UNSUPPORTED
        capabilities.add(MacCapabilitiesType.RECEIVER_ON_WHEN_IDLE);
        future = groupManager.getGroups();
        assertEquals(ZigBeeStatus.UNSUPPORTED, future.get());

        endpoint.addInputCluster(new ZclGroupsCluster(endpoint));
        future = groupManager.getGroups();
        assertEquals(ZigBeeStatus.SUCCESS, future.get());

        Iterator<ZigBeeGroup> iterator = groupManager.getAll().iterator();
        ZigBeeGroup group = iterator.next();

        assertEquals(2, groupManager.getAll().size());
        assertEquals(1, group.getGroupId());
        assertEquals("Label 1", group.getLabel());

        group = iterator.next();
        assertEquals(3, group.getGroupId());
        assertEquals("", group.getLabel());
    }
}
