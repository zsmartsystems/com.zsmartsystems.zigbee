/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.zsmartsystems.zigbee.internal.ClusterMatcher;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;

/**
 *
 * @author Chris Jackson
 *
 */
public class ClusterMatcherTest {
    ArgumentCaptor<ZigBeeCommand> mockedCommandCaptor = ArgumentCaptor.forClass(ZigBeeCommand.class);

    private ClusterMatcher getMatcher() {
        ZigBeeNetworkManager mockedNetworkManager = Mockito.mock(ZigBeeNetworkManager.class);
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

        ClusterMatcher matcher = new ClusterMatcher(mockedNetworkManager);

        return matcher;
    }

    @Test
    public void testMatcherNoMatch() {
        ClusterMatcher matcher = getMatcher();
        matcher.addCluster(0x500);

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        MatchDescriptorRequest request = new MatchDescriptorRequest();
        request.setProfileId(0x104);
        request.setInClusterList(clusterListIn);
        request.setOutClusterList(clusterListOut);

        matcher.commandReceived(request);
        assertEquals(0, mockedCommandCaptor.getAllValues().size());
    }

    @Test
    public void testMatcherMatchIn() {
        ClusterMatcher matcher = getMatcher();
        matcher.addCluster(0x500);
        matcher.addCluster(0x600);

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        clusterListIn.add(0x500);
        MatchDescriptorRequest request = new MatchDescriptorRequest();
        request.setProfileId(0x104);
        request.setInClusterList(clusterListIn);
        request.setOutClusterList(clusterListOut);

        matcher.commandReceived(request);
        assertEquals(1, mockedCommandCaptor.getAllValues().size());
    }

    @Test
    public void testMatcherMatchOut() {
        ClusterMatcher matcher = getMatcher();
        matcher.addCluster(0x500);
        matcher.addCluster(0x600);

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        clusterListOut.add(0x500);
        MatchDescriptorRequest request = new MatchDescriptorRequest();
        request.setProfileId(0x104);
        request.setInClusterList(clusterListIn);
        request.setOutClusterList(clusterListOut);

        matcher.commandReceived(request);
        assertEquals(1, mockedCommandCaptor.getAllValues().size());
    }

    @Test
    public void testMatcherMatchInOut() {
        ClusterMatcher matcher = getMatcher();
        matcher.addCluster(0x500);
        matcher.addCluster(0x600);

        List<Integer> clusterListIn = new ArrayList<Integer>();
        List<Integer> clusterListOut = new ArrayList<Integer>();
        clusterListIn.add(0x500);
        clusterListOut.add(0x500);
        MatchDescriptorRequest request = new MatchDescriptorRequest();
        request.setProfileId(0x104);
        request.setInClusterList(clusterListIn);
        request.setOutClusterList(clusterListOut);

        matcher.commandReceived(request);
        assertEquals(1, mockedCommandCaptor.getAllValues().size());
    }
}
