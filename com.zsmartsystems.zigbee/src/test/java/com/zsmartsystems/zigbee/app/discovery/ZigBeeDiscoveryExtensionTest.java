/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeNodeServiceDiscoverer.NodeDiscoveryTask;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveResponse;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressRequest;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDiscoveryExtensionTest {
    final static int TIMEOUT = 5000;

    @Test
    public void test() throws Exception {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        Mockito.doAnswer(new Answer<Future<CommandResult>>() {
            @Override
            public Future<CommandResult> answer(InvocationOnMock invocation)
                    throws InterruptedException, ExecutionException {
                Runnable runnable = (Runnable) invocation.getArguments()[0];
                new Thread(runnable).start();
                return null;
            }
        }).when(networkManager).executeTask(ArgumentMatchers.any(Runnable.class));

        Future<CommandResult> future = Mockito.mock(Future.class);
        CommandResult cmdResult = Mockito.mock(CommandResult.class);
        Mockito.when(cmdResult.isError()).thenReturn(true);
        Mockito.when(future.get()).thenReturn(cmdResult);
        Mockito.when(networkManager.sendTransaction(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(future);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        Mockito.when(node.getNodeState()).thenReturn(ZigBeeNodeState.ONLINE);

        ZigBeeDiscoveryExtension extension = Mockito.spy(ZigBeeDiscoveryExtension.class);
        Mockito.doNothing().when(extension).startDiscovery(node);
        Mockito.doNothing().when(extension).stopDiscovery(node);
        Mockito.doNothing().when(extension).startScheduler(ArgumentMatchers.any(int.class));

        extension.setUpdateMeshTasks(
                Arrays.asList(new NodeDiscoveryTask[] { NodeDiscoveryTask.ROUTES, NodeDiscoveryTask.NEIGHBORS }));

        extension.setUpdateMeshPeriod(0);

        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionStartup());
        assertEquals(ZigBeeStatus.INVALID_STATE, extension.extensionStartup());

        extension.setUpdateMeshPeriod(0);
        Mockito.verify(extension, Mockito.times(1)).stopScheduler();
        assertEquals(0, extension.getUpdateMeshPeriod());

        extension.setUpdateMeshPeriod(1);
        Mockito.verify(extension, Mockito.times(1)).startScheduler(1);
        assertEquals(1, extension.getUpdateMeshPeriod());

        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).atLeast(1))
                .addNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).atLeast(1))
                .addCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));

        extension.nodeAdded(node);
        Mockito.verify(extension, Mockito.times(1)).startDiscovery(node);

        Map<IeeeAddress, ZigBeeNodeServiceDiscoverer> nodeDiscovery = new ConcurrentHashMap<>();
        ZigBeeNodeServiceDiscoverer nodeServiceDiscoverer = Mockito.mock(ZigBeeNodeServiceDiscoverer.class);
        Mockito.when(nodeServiceDiscoverer.isFinished()).thenReturn(true);
        Mockito.when(nodeServiceDiscoverer.isSuccessful()).thenReturn(true);
        nodeDiscovery.put(node.getIeeeAddress(), nodeServiceDiscoverer);
        TestUtilities.setField(ZigBeeDiscoveryExtension.class, extension, "nodeDiscovery", nodeDiscovery);

        assertEquals(1, extension.getNodeDiscoverers().size());

        // Add again and make sure we don't call startDiscovery again
        extension.nodeAdded(node);
        Mockito.verify(extension, Mockito.times(1)).startDiscovery(node);

        // Updated node when ONLINE should not do anything
        extension.nodeUpdated(node);
        Mockito.verify(extension, Mockito.times(1)).startDiscovery(node);
        Mockito.verify(extension, Mockito.times(0)).stopDiscovery(node);

        // Updated node when OFFLINE should stop discovery
        Mockito.when(node.getNodeState()).thenReturn(ZigBeeNodeState.OFFLINE);
        extension.nodeUpdated(node);
        Mockito.verify(extension, Mockito.times(1)).startDiscovery(node);
        Mockito.verify(extension, Mockito.times(1)).stopDiscovery(node);

        // Remove node should also stopDiscovery
        extension.nodeRemoved(node);
        Mockito.verify(extension, Mockito.times(1)).startDiscovery(node);
        Mockito.verify(extension, Mockito.times(2)).stopDiscovery(node);

        nodeDiscovery.clear();

        // Updated node when ONLINE and node not known should start discovery
        Mockito.when(node.getNodeState()).thenReturn(ZigBeeNodeState.ONLINE);
        extension.nodeUpdated(node);
        Mockito.verify(extension, Mockito.times(2)).startDiscovery(node);
        Mockito.verify(extension, Mockito.times(2)).stopDiscovery(node);

        ManagementLeaveResponse leave = Mockito.mock(ManagementLeaveResponse.class);
        extension.commandReceived(leave);
        Mockito.verify(extension, Mockito.times(1)).refresh();

        DeviceAnnounce announce = Mockito.mock(DeviceAnnounce.class);
        extension.commandReceived(announce);
        Mockito.verify(extension, Mockito.times(2)).refresh();

        MatchDescriptorRequest nothing = Mockito.mock(MatchDescriptorRequest.class);
        extension.commandReceived(nothing);
        Mockito.verify(extension, Mockito.times(2)).refresh();

        extension.rediscoverNode(1);
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).atLeast(1)).sendTransaction(
                ArgumentMatchers.any(IeeeAddressRequest.class), ArgumentMatchers.any(IeeeAddressRequest.class));

        extension.rediscoverNode(new IeeeAddress("1234567890ABCDEF"));
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).atLeast(1)).sendTransaction(
                ArgumentMatchers.any(NetworkAddressRequest.class), ArgumentMatchers.any(NetworkAddressRequest.class));

        extension.extensionShutdown();

        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).atLeast(1))
                .removeNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).atLeast(1))
                .removeCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));
    }
}
