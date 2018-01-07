/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

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
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.CommandResultFuture;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportState;
import com.zsmartsystems.zigbee.zdo.ZdoCommandType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkDiscovererTest {
    ZigBeeNetworkManager networkManager;
    ArgumentCaptor<ZigBeeNode> nodeCapture;
    // ArgumentCaptor<ZigBeeCommand> commandCapture;
    // ArgumentCaptor<CommandResponseMatcher> matcherCapture;
    Map<Integer, ZigBeeCommand> responses = new HashMap<Integer, ZigBeeCommand>();

    @Before
    public void setupTest() {
        networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        nodeCapture = ArgumentCaptor.forClass(ZigBeeNode.class);
        // commandCapture = ArgumentCaptor.forClass(ZigBeeCommand.class);
        // matcherCapture = ArgumentCaptor.forClass(CommandResponseMatcher.class);

        // Mockito.when(networkManager.unicast(commandCapture.capture(), matcherCapture.capture())).thenReturn(null);

        Mockito.doAnswer(new Answer<Future<CommandResult>>() {
            @Override
            public Future<CommandResult> answer(InvocationOnMock invocation) {
                ZigBeeCommand command = (ZigBeeCommand) invocation.getArguments()[0];

                CommandResultFuture commandFuture = new CommandResultFuture(networkManager);
                CommandResult result = new CommandResult(responses.get(command.getClusterId()));
                commandFuture.set(result);
                return commandFuture;
            }
        }).when(networkManager).unicast(org.mockito.Matchers.any(ZigBeeCommand.class),
                org.mockito.Matchers.any(ZigBeeTransactionMatcher.class));

        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) {
                Runnable runnable = (Runnable) invocation.getArguments()[0];
                new Thread(runnable).start();
                return null;
            }
        }).when(networkManager).executeTask(org.mockito.Matchers.any(Runnable.class));
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

        NodeDescriptorResponse nodeResponse = new NodeDescriptorResponse();
        nodeResponse.setStatus(ZdoStatus.SUCCESS);
        nodeResponse.setSourceAddress(new ZigBeeEndpointAddress(0));
        nodeResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        nodeResponse.setNwkAddrOfInterest(0);
        NodeDescriptor nodeDescriptor = new NodeDescriptor();
        nodeResponse.setNodeDescriptor(nodeDescriptor);
        responses.put(ZdoCommandType.NODE_DESCRIPTOR_REQUEST.getClusterId(), nodeResponse);

        PowerDescriptorResponse powerResponse = new PowerDescriptorResponse();
        powerResponse.setStatus(ZdoStatus.SUCCESS);
        powerResponse.setSourceAddress(new ZigBeeEndpointAddress(0));
        powerResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        powerResponse.setNwkAddrOfInterest(0);
        PowerDescriptor powerDescriptor = new PowerDescriptor();
        powerResponse.setPowerDescriptor(powerDescriptor);
        responses.put(ZdoCommandType.POWER_DESCRIPTOR_REQUEST.getClusterId(), powerResponse);

        ActiveEndpointsResponse endpointsResponse = new ActiveEndpointsResponse();
        endpointsResponse.setStatus(ZdoStatus.SUCCESS);
        endpointsResponse.setSourceAddress(new ZigBeeEndpointAddress(0));
        endpointsResponse.setDestinationAddress(new ZigBeeEndpointAddress(0));
        endpointsResponse.setNwkAddrOfInterest(0);
        List<Integer> activeEpList = new ArrayList<Integer>();
        activeEpList.add(1);
        endpointsResponse.setActiveEpList(activeEpList);
        responses.put(ZdoCommandType.ACTIVE_ENDPOINTS_REQUEST.getClusterId(), endpointsResponse);

        SimpleDescriptorResponse simpleResponse = new SimpleDescriptorResponse();
        simpleResponse.setStatus(ZdoStatus.SUCCESS);
        simpleResponse.setSourceAddress(new ZigBeeEndpointAddress(0));
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

        ZigBeeNetworkDiscoverer discoverer = new ZigBeeNetworkDiscoverer(networkManager);

        discoverer.setRetryPeriod(1);
        discoverer.startup();

        // Check that the listener registers for startup notifications
        Mockito.verify(networkManager).addNetworkStateListener(discoverer);

        // Put the system online so it initialises
        discoverer.networkStateUpdated(ZigBeeTransportState.ONLINE);

        // Check it registers listeners
        Mockito.verify(networkManager).addCommandListener(discoverer);
        Mockito.verify(networkManager).addAnnounceListener(discoverer);

        // Then wait for the node to be added
        Mockito.verify(networkManager, Mockito.timeout(5000).times(1)).addNode(nodeCapture.capture());

        ZigBeeNode node = nodeCapture.getValue();
        assertNotNull(node);
        assertEquals(Integer.valueOf(0), node.getNetworkAddress());
        assertEquals(new IeeeAddress("1234567890ABCDEF"), node.getIeeeAddress());
        assertEquals(1, node.getEndpoints().size());
    }
}
