/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
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
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionFuture;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zdo.ZdoCommandType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkDiscovererTest {
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
        }).when(networkManager).executeTask(ArgumentMatchers.any(Runnable.class));
    }

    @Test
    public void testNormal() {
        // Add all the required responses to a list
        IeeeAddressResponse ieeeResponse = new IeeeAddressResponse();
        ieeeResponse.setStatus(ZdoStatus.SUCCESS);
        ieeeResponse.setSourceAddress(new ZigBeeEndpointAddress(0));
        ieeeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        ieeeResponse.setIeeeAddrRemoteDev(new IeeeAddress("1234567890ABCDEF"));
        responses.put(ZdoCommandType.IEEE_ADDRESS_REQUEST.getClusterId(), ieeeResponse);

        ZigBeeNetworkDiscoverer discoverer = new ZigBeeNetworkDiscoverer(networkManager);

        discoverer.setRetryPeriod(1);
        discoverer.startup();

        // Check it registers listeners
        Mockito.verify(networkManager).addCommandListener(discoverer);
        Mockito.verify(networkManager).addAnnounceListener(discoverer);

        // Then wait for the node to be added
        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).addNode(nodeCapture.capture());

        ZigBeeNode node = nodeCapture.getValue();
        assertNotNull(node);
        assertEquals(Integer.valueOf(0), node.getNetworkAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), node.getIeeeAddress());
        assertEquals(0, node.getEndpoints().size());
    }

    @Test
    public void testNodeAddressUpdate() {
        IeeeAddress ieeeAddress = new IeeeAddress("123456890ABCDEF");

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);// new ZigBeeNode(networkManager, ieeeAddress);
        Mockito.doReturn(node).when(networkManager).getNode(ArgumentMatchers.any(IeeeAddress.class));

        DeviceAnnounce announce = new DeviceAnnounce();
        announce.setIeeeAddr(ieeeAddress);
        announce.setNwkAddrOfInterest(12345);

        ZigBeeNetworkDiscoverer discoverer = new ZigBeeNetworkDiscoverer(networkManager);
        discoverer.setRetryPeriod(0);
        discoverer.setRequeryPeriod(0);
        discoverer.setRetryCount(0);

        discoverer.commandReceived(announce);
        Mockito.verify(node, Mockito.times(1)).setNetworkAddress(ArgumentMatchers.anyInt());

        ZigBeeEndpointAddress address = Mockito.mock(ZigBeeEndpointAddress.class);
        Mockito.when(address.getAddress()).thenReturn(12345);
        ZclCommand zclCommand = Mockito.mock(ZclCommand.class);
        Mockito.when(zclCommand.getSourceAddress()).thenReturn(address);
        discoverer.commandReceived(zclCommand);
    }
}
