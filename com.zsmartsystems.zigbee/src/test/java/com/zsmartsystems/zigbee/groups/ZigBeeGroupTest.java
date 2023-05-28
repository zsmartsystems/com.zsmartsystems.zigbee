/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclGroupsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ZclGroupsCommand;

/**
 * Tests for {@link ZigBeeGroup}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeGroupTest {

    @Test
    public void testGroupAddress() {
        ZigBeeGroup group1 = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);

        ZigBeeGroup group2 = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);
        assertTrue(group1.equals(group2));
        assertEquals(0, group1.compareTo(group2));
        assertEquals(1, group1.getGroupId());
        assertEquals(1, group1.getAddress().getAddress());

        // Test that label doesn't matter
        group2 = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1, "Test Label");
        assertFalse(group1.equals(group2));
        assertEquals("Test Label", group2.getLabel());

        group2 = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);
        assertEquals(1, group1.getGroupId());
        group2.setLabel("Test Label");
        assertEquals("Test Label", group2.getLabel());

        group2.setLabel("12345678901234567890");
        assertEquals("1234567890123456", group2.getLabel());

        group2.setGroupId(2);
        assertEquals(1, group1.compareTo(group2));

        System.out.println(group2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGroupIdTooLow() {
        ZigBeeGroup group = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);

        group.setGroupId(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGroupIdTooHigh() {
        ZigBeeGroup group = new ZigBeeGroup(Mockito.mock(ZigBeeNetworkManager.class), 1);

        group.setGroupId(0xfff8);
    }

    @Test
    public void addMember() throws InterruptedException, ExecutionException {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);

        ZigBeeGroup group = new ZigBeeGroup(networkManager, 1);
        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);

        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        Mockito.when(endpoint.getIeeeAddress()).thenReturn(ieeeAddress);
        Mockito.when(endpoint.getEndpointId()).thenReturn(1);
        assertEquals(ZigBeeStatus.UNSUPPORTED, group.addMember(endpoint));

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getEndpoint(1)).thenReturn(endpoint);
        Mockito.when(networkManager.getNode(ieeeAddress)).thenReturn(node);

        ZclGroupsCluster cluster = Mockito.mock(ZclGroupsCluster.class);
        Mockito.when(endpoint.getInputCluster(ZclGroupsCluster.CLUSTER_ID)).thenReturn(cluster);

        @SuppressWarnings("unchecked")
        Future<CommandResult> future = Mockito.mock(Future.class);
        CommandResult response = Mockito.mock(CommandResult.class);

        AddGroupResponse addGroupResponse = Mockito.mock(AddGroupResponse.class);
        Mockito.when(addGroupResponse.getStatus()).thenReturn(ZclStatus.SUCCESS);
        Mockito.when(response.getResponse()).thenReturn(addGroupResponse);

        Mockito.when(cluster.sendCommand(ArgumentMatchers.any(ZclGroupsCommand.class))).thenReturn(future);
        Mockito.when(future.get()).thenReturn(response);

        Mockito.when(response.isError()).thenReturn(true);
        Mockito.when(addGroupResponse.getStatus()).thenReturn(ZclStatus.ABORT);
        assertEquals(ZigBeeStatus.FAILURE, group.addMember(endpoint));

        Mockito.when(response.isError()).thenReturn(false);
        Mockito.when(addGroupResponse.getStatus()).thenReturn(ZclStatus.ABORT);
        assertEquals(ZigBeeStatus.FAILURE, group.addMember(endpoint));

        Mockito.when(addGroupResponse.getStatus()).thenReturn(ZclStatus.INSUFFICIENT_SPACE);
        assertEquals(ZigBeeStatus.NO_RESOURCES, group.addMember(endpoint));
    }

    @Test
    public void removeMember() throws InterruptedException, ExecutionException {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);

        ZigBeeGroup group = new ZigBeeGroup(networkManager, 1);
        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);

        IeeeAddress ieeeAddress = new IeeeAddress("1234567890ABCDEF");
        Mockito.when(endpoint.getIeeeAddress()).thenReturn(ieeeAddress);
        Mockito.when(endpoint.getEndpointId()).thenReturn(1);
        assertEquals(ZigBeeStatus.UNSUPPORTED, group.removeMember(endpoint));

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getEndpoint(1)).thenReturn(endpoint);
        Mockito.when(networkManager.getNode(ieeeAddress)).thenReturn(node);

        ZclGroupsCluster cluster = Mockito.mock(ZclGroupsCluster.class);
        Mockito.when(endpoint.getInputCluster(ZclGroupsCluster.CLUSTER_ID)).thenReturn(cluster);

        @SuppressWarnings("unchecked")
        Future<CommandResult> future = Mockito.mock(Future.class);
        CommandResult response = Mockito.mock(CommandResult.class);

        RemoveGroupResponse removeGroupResponse = Mockito.mock(RemoveGroupResponse.class);
        Mockito.when(removeGroupResponse.getStatus()).thenReturn(ZclStatus.SUCCESS);
        Mockito.when(response.getResponse()).thenReturn(removeGroupResponse);

        Mockito.when(response.getStatusCode()).thenReturn(0);

        Mockito.when(cluster.sendCommand(ArgumentMatchers.any(ZclGroupsCommand.class))).thenReturn(future);
        Mockito.when(future.get()).thenReturn(response);

        assertEquals(ZigBeeStatus.SUCCESS, group.removeMember(endpoint));

        Mockito.when(response.isError()).thenReturn(true);
        Mockito.when(response.getStatusCode()).thenReturn(99);
        assertEquals(ZigBeeStatus.FAILURE, group.removeMember(endpoint));
    }
}
