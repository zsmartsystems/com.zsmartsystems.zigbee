/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ClusterMatcherTest {
    ArgumentCaptor<ZigBeeCommand> mockedCommandCaptor = ArgumentCaptor.forClass(ZigBeeCommand.class);
    ZigBeeNetworkManager mockedNetworkManager;

    private ClusterMatcher getMatcher() {
        mockedNetworkManager = Mockito.mock(ZigBeeNetworkManager.class);
        Mockito.doAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) {
                return 0;
            }
        }).when(mockedNetworkManager).sendTransaction(mockedCommandCaptor.capture());

        ClusterMatcher matcher = new ClusterMatcher(mockedNetworkManager, 1, 0x104);

        Mockito.verify(mockedNetworkManager, Mockito.atLeastOnce()).addCommandListener(matcher);

        return matcher;
    }

    @Test
    public void testMatcherNoMatch() {
        ClusterMatcher matcher = getMatcher();
        matcher.addClientCluster(0x500);

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        MatchDescriptorRequest request = new MatchDescriptorRequest(
                0,
                0x104,
                clusterListIn,
                clusterListOut);
        request.setSourceAddress(new ZigBeeEndpointAddress(1234, 5));

        matcher.commandReceived(request);
        assertEquals(0, mockedCommandCaptor.getAllValues().size());

        matcher.shutdown();
        Mockito.verify(mockedNetworkManager, Mockito.atLeastOnce()).removeCommandListener(matcher);
    }

    @Test
    public void testMatcherNoAddressMatch() {
        ClusterMatcher matcher = getMatcher();
        matcher.addClientCluster(0x500);

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        MatchDescriptorRequest request = new MatchDescriptorRequest(1, 0x104, clusterListIn, clusterListOut);
        request.setSourceAddress(new ZigBeeEndpointAddress(1234, 5));

        matcher.commandReceived(request);
        assertEquals(0, mockedCommandCaptor.getAllValues().size());
    }

    @Test
    public void testMatcherBroadcast() {
        ClusterMatcher matcher = getMatcher();
        matcher.addServerCluster(0x500);
        assertTrue(matcher.isServerSupported(0x500));
        assertFalse(matcher.isClientSupported(0x500));

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        clusterListIn.add(0x500);
        int requestTransactionId = 5;
        MatchDescriptorRequest request = new MatchDescriptorRequest(0xFFFF, 0x104, clusterListIn, clusterListOut);
        request.setTransactionId(requestTransactionId);
        request.setSourceAddress(new ZigBeeEndpointAddress(1234, 5));

        matcher.commandReceived(request);
        assertEquals(1, mockedCommandCaptor.getAllValues().size());
        MatchDescriptorResponse response = (MatchDescriptorResponse) mockedCommandCaptor.getValue();
        assertEquals(1234, response.getDestinationAddress().getAddress());
        assertEquals(requestTransactionId, response.getTransactionId().intValue());
    }

    @Test
    public void testMatcherMatchIn() {
        ClusterMatcher matcher = getMatcher();
        matcher.addClientCluster(0x500);
        matcher.addServerCluster(0x600);
        assertTrue(matcher.isServerSupported(0x600));
        assertTrue(matcher.isClientSupported(0x500));

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        clusterListIn.add(0x600);
        MatchDescriptorRequest request = new MatchDescriptorRequest(0, 0x104, clusterListIn, clusterListOut);
        request.setTransactionId(5);
        request.setSourceAddress(new ZigBeeEndpointAddress(1234, 5));

        matcher.commandReceived(request);
        assertEquals(1, mockedCommandCaptor.getAllValues().size());
    }

    @Test
    public void testMatcherMatchOut() {
        ClusterMatcher matcher = getMatcher();
        matcher.addClientCluster(0x500);
        matcher.addServerCluster(0x600);

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        clusterListOut.add(0x500);
        MatchDescriptorRequest request = new MatchDescriptorRequest(0, 0x104, clusterListIn, clusterListOut);
        request.setTransactionId(5);
        request.setSourceAddress(new ZigBeeEndpointAddress(1234, 5));

        matcher.commandReceived(request);
        assertEquals(1, mockedCommandCaptor.getAllValues().size());
    }

    @Test
    public void testMatcherMatchInOut() {
        ClusterMatcher matcher = getMatcher();
        matcher.addClientCluster(0x500);
        matcher.addServerCluster(0x600);

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        clusterListIn.add(0x500);
        clusterListOut.add(0x500);
        int requestTransactionId = 5;
        MatchDescriptorRequest request = new MatchDescriptorRequest(0, 0x104, clusterListIn, clusterListOut);
        request.setTransactionId(requestTransactionId);
        request.setSourceAddress(new ZigBeeEndpointAddress(1234, 5));

        matcher.commandReceived(request);
        assertEquals(1, mockedCommandCaptor.getAllValues().size());
        MatchDescriptorResponse response = (MatchDescriptorResponse) mockedCommandCaptor.getValue();
        assertEquals(1234, response.getDestinationAddress().getAddress());
        assertEquals(Integer.valueOf(0), response.getNwkAddrOfInterest());
        assertEquals(requestTransactionId, response.getTransactionId().intValue());
    }
}
