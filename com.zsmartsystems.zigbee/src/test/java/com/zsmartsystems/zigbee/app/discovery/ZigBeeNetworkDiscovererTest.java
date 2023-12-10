/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

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
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNodeStatus;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransaction;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionFuture;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommandType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkDiscovererTest {
    static final int TIMEOUT = 5000;

    private ZigBeeNetworkManager networkManager;
    private ArgumentCaptor<ZigBeeNode> nodeCapture;
    private Map<Integer, ZigBeeCommand> responses = new HashMap<>();

    @Before
    public void setupTest() {
        networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        nodeCapture = ArgumentCaptor.forClass(ZigBeeNode.class);

        Mockito.doAnswer(new Answer<Future<CommandResult>>() {
            @Override
            public Future<CommandResult> answer(InvocationOnMock invocation) {
                ZigBeeCommand command = (ZigBeeCommand) invocation.getArguments()[0];

                ZigBeeTransactionFuture commandFuture = new ZigBeeTransactionFuture(
                        Mockito.mock(ZigBeeTransaction.class));
                CommandResult result = new CommandResult(responses.get(command.getClusterId()));
                commandFuture.set(result);
                return commandFuture;
            }
        }).when(networkManager).sendTransaction(ArgumentMatchers.any(ZigBeeCommand.class));

        Mockito.doAnswer(new Answer<Future<CommandResult>>() {
            @Override
            public Future<CommandResult> answer(InvocationOnMock invocation) {
                ZigBeeCommand command = (ZigBeeCommand) invocation.getArguments()[0];

                ZigBeeTransactionFuture commandFuture = new ZigBeeTransactionFuture(
                        Mockito.mock(ZigBeeTransaction.class));
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
        }).when(networkManager).executeTask(ArgumentMatchers.any(Runnable.class));
    }

    @Test
    public void testNormal() {
        // Add all the required responses to a list
        List<Integer> remotes = new ArrayList<>();
        remotes.add(1);
        IeeeAddressResponse ieeeResponse = new IeeeAddressResponse(ZdoStatus.SUCCESS,
                new IeeeAddress("1234567890ABCDEF"), 0, 0, remotes);
        ieeeResponse.setSourceAddress(new ZigBeeEndpointAddress(0));
        ieeeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        responses.put(ZdoCommandType.IEEE_ADDRESS_REQUEST.getClusterId(), ieeeResponse);

        ZigBeeNetworkDiscoverer discoverer = new ZigBeeNetworkDiscoverer(networkManager);

        ZigBeeNode node0 = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node0.getIeeeAddress()).thenReturn(new IeeeAddress("0000000000000000"));

        ZigBeeNode node1 = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node1.getIeeeAddress()).thenReturn(new IeeeAddress("1111111111111111"));

        Mockito.doAnswer(new Answer<ZigBeeNode>() {
            private int count0 = 0;
            private int count1 = 0;

            @Override
            public ZigBeeNode answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                Integer nodeId = (Integer) args[0];

                if (nodeId == 0) {
                    if (count0++ == 0) {
                        return null;
                    }
                    return node0;
                } else {
                    if (count1++ == 0) {
                        return null;
                    }
                    return node1;
                }
            }
        }).when(networkManager).getNode(ArgumentMatchers.anyInt());

        discoverer.setRetryPeriod(Integer.MAX_VALUE);
        discoverer.startup();

        // Check it registers listeners
        Mockito.verify(networkManager).addCommandListener(discoverer);
        Mockito.verify(networkManager).addAnnounceListener(discoverer);

        // Then wait for the nodes to be added
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).updateNode(nodeCapture.capture());

        ZigBeeNode node = nodeCapture.getValue();
        assertNotNull(node);
        assertEquals(Integer.valueOf(0), node.getNetworkAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), node.getIeeeAddress());
        assertEquals(0, node.getEndpoints().size());

        discoverer.setRetryCount(0);
        discoverer.setRequeryPeriod(0);
        discoverer.shutdown();
    }

    @Test
    public void testNodeAddressUpdate() {
        IeeeAddress ieeeAddress = new IeeeAddress("123456890ABCDEF");

        DeviceAnnounce announce = new DeviceAnnounce(12345, ieeeAddress, null);
        announce.setSourceAddress(new ZigBeeEndpointAddress(1));

        ZigBeeNetworkDiscoverer discoverer = new ZigBeeNetworkDiscoverer(networkManager);
        discoverer.setRetryPeriod(0);
        discoverer.setRequeryPeriod(0);
        discoverer.setRetryCount(0);

        discoverer.commandReceived(announce);
        Mockito.verify(networkManager, Mockito.times(1)).updateNode(ArgumentMatchers.any());

        ZigBeeEndpointAddress address = Mockito.mock(ZigBeeEndpointAddress.class);
        Mockito.when(address.getAddress()).thenReturn(12345);
        ZclCommand zclCommand = Mockito.mock(ZclCommand.class);
        Mockito.when(zclCommand.getSourceAddress()).thenReturn(address);
        discoverer.commandReceived(zclCommand);
    }

    @Test
    public void deviceStatusUpdate() {
        ZigBeeNetworkDiscoverer discoverer = new ZigBeeNetworkDiscoverer(networkManager);
        discoverer.setRetryPeriod(0);
        discoverer.setRequeryPeriod(0);
        discoverer.setRetryCount(0);

        discoverer.deviceStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN, 2222, new IeeeAddress("1111111111111111"), null);

        Mockito.verify(networkManager, Mockito.times(1)).updateNode(ArgumentMatchers.any());
    }

    @Test
    public void rediscoverNodeByEui() throws Exception {
        ZigBeeNetworkDiscoverer discoverer = new ZigBeeNetworkDiscoverer(networkManager);

        IeeeAddressResponse ieeeResponse = new IeeeAddressResponse(ZdoStatus.SUCCESS,
                new IeeeAddress("1111111111111111"), 1111, null, null);
        ieeeResponse.setSourceAddress(new ZigBeeEndpointAddress(1111));
        ieeeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        responses.put(ZdoCommandType.IEEE_ADDRESS_REQUEST.getClusterId(), ieeeResponse);

        Map<Integer, Long> discoveryStartTime = new HashMap<>();
        discoveryStartTime.put(1111, Long.MAX_VALUE);
        TestUtilities.setField(ZigBeeNetworkDiscoverer.class, discoverer, "initialized", true);
        TestUtilities.setField(ZigBeeNetworkDiscoverer.class, discoverer, "discoveryStartTime", discoveryStartTime);
        discoverer.rediscoverNode(1111);

        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).updateNode(nodeCapture.capture());

        ZigBeeNode node = nodeCapture.getValue();
        assertNotNull(node);
        assertEquals(Integer.valueOf(1111), node.getNetworkAddress());
        assertEquals(new IeeeAddress("1111111111111111"), node.getIeeeAddress());
        assertEquals(0, node.getEndpoints().size());
    }

    @Test
    public void rediscoverNodeByNwk() throws Exception {
        ZigBeeNetworkDiscoverer discoverer = new ZigBeeNetworkDiscoverer(networkManager);

        NetworkAddressResponse nwkResponse = new NetworkAddressResponse(ZdoStatus.SUCCESS,
                new IeeeAddress("1111111111111111"), 1111, null, null);
        nwkResponse.setSourceAddress(new ZigBeeEndpointAddress(1111));
        nwkResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        responses.put(ZdoCommandType.NETWORK_ADDRESS_REQUEST.getClusterId(), nwkResponse);

        Map<Integer, Long> discoveryStartTime = new HashMap<>();
        discoveryStartTime.put(1111, Long.MAX_VALUE);
        TestUtilities.setField(ZigBeeNetworkDiscoverer.class, discoverer, "initialized", true);
        TestUtilities.setField(ZigBeeNetworkDiscoverer.class, discoverer, "discoveryStartTime", discoveryStartTime);
        discoverer.rediscoverNode(new IeeeAddress("1111111111111111"));

        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).updateNode(nodeCapture.capture());

        ZigBeeNode node = nodeCapture.getValue();
        assertNotNull(node);
        assertEquals(Integer.valueOf(1111), node.getNetworkAddress());
        assertEquals(new IeeeAddress("1111111111111111"), node.getIeeeAddress());
        assertEquals(0, node.getEndpoints().size());
    }
}
