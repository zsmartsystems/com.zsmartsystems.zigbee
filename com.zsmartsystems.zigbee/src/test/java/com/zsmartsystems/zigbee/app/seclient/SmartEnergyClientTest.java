/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkState;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclMeteringCluster;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.MatchDescriptorResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class SmartEnergyClientTest {
    private final static int TIMEOUT = 5000;

    @Test
    public void testStartupShutdown() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        Mockito.when(node.getNodeState()).thenReturn(ZigBeeNodeState.ONLINE);

        ZigBeeNode cbkeNode = Mockito.mock(ZigBeeNode.class);
        Mockito.when(cbkeNode.getIeeeAddress()).thenReturn(new IeeeAddress("1111111111111111"));
        Mockito.when(cbkeNode.getNodeState()).thenReturn(ZigBeeNodeState.ONLINE);
        Collection<ZigBeeEndpoint> endpoints = new HashSet<>();
        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        endpoints.add(endpoint);
        Mockito.when(cbkeNode.getEndpoints()).thenReturn(endpoints);
        ZclKeyEstablishmentCluster cluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        Mockito.when(endpoint.getInputCluster(ZclKeyEstablishmentCluster.CLUSTER_ID)).thenReturn(cluster);

        ZigBeeCbkeProvider cbkeProvider = Mockito.mock(ZigBeeCbkeProvider.class);
        SmartEnergyClient extension = new SmartEnergyClient(cbkeProvider);
        assertEquals(cbkeProvider, extension.getCbkeProvider());

        extension.setCryptoSuite(ZigBeeCryptoSuites.ECC_163K1);

        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionStartup());
        assertEquals(ZigBeeStatus.INVALID_STATE, extension.extensionStartup());

        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .addNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .addCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .addNetworkStateListener(ArgumentMatchers.any(ZigBeeNetworkStateListener.class));

        extension.nodeAdded(node);
        extension.nodeAdded(cbkeNode);
        TestUtilities.getField(SmartEnergyClient.class, extension, "cbkeClientRegistry");

        extension.extensionShutdown();

        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeNetworkStateListener(ArgumentMatchers.any(ZigBeeNetworkStateListener.class));
    }

    @Test
    public void keyEstablishmentCallback() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        Mockito.when(networkManager.getNotificationService()).thenReturn(new NotificationService());
        SmartEnergyClient extension = new SmartEnergyClient(Mockito.mock(ZigBeeCbkeProvider.class));
        SmartEnergyStatusCallback listener = Mockito.mock(SmartEnergyStatusCallback.class);
        extension.addListener(listener);

        ArgumentCaptor<Long> timerCaptor = ArgumentCaptor.forClass(Long.class);
        Mockito.when(networkManager.scheduleTask(ArgumentMatchers.any(), timerCaptor.capture()))
                .thenReturn(Mockito.mock(ScheduledFuture.class));
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionStartup());

        TestUtilities.setField(SmartEnergyClient.class, extension, "seState",
                SmartEnergyClientState.PERFORM_KEY_ESTABLISHMENT);
        assertEquals(SmartEnergyClientState.PERFORM_KEY_ESTABLISHMENT, extension.getDiscoveryState());

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(networkManager.getNode(0)).thenReturn(node);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        Mockito.when(node.getNodeState()).thenReturn(ZigBeeNodeState.ONLINE);
        extension.nodeAdded(node);

        extension.keyEstablishmentCallback(ZigBeeStatus.SUCCESS, 0);
        Mockito.verify(listener, Mockito.timeout(TIMEOUT)).sepStatusUpdate(ZigBeeSepClientStatus.INITIALIZING);
        assertEquals(SmartEnergyClientState.DISCOVER_METERING_SERVERS, extension.getDiscoveryState());
        assertEquals(1, timerCaptor.getAllValues().size());

        extension.keyEstablishmentCallback(ZigBeeStatus.FATAL_ERROR, 0);
        Mockito.verify(listener, Mockito.timeout(TIMEOUT)).sepStatusUpdate(ZigBeeSepClientStatus.FATAL_ERROR);
        // NO timer is scheduled as this was FATAL
        assertEquals(1, timerCaptor.getAllValues().size());

        extension.keyEstablishmentCallback(ZigBeeStatus.FAILURE, 23);
        Mockito.verify(listener, Mockito.timeout(TIMEOUT).times(2)).sepStatusUpdate(ZigBeeSepClientStatus.INITIALIZING);
        assertEquals(SmartEnergyClientState.PERFORM_KEY_ESTABLISHMENT, extension.getDiscoveryState());
        // We have 2 timers scheduled - the regular state change, and then the one requested from the remote
        assertEquals(3, timerCaptor.getAllValues().size());

        extension.removeListener(listener);
    }

    @Test
    public void networkStateUpdated() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());

        SmartEnergyClient extension = new SmartEnergyClient(Mockito.mock(ZigBeeCbkeProvider.class));
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        Mockito.when(networkManager.getNotificationService()).thenReturn(new NotificationService());
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));

        SmartEnergyStatusCallback listener = Mockito.mock(SmartEnergyStatusCallback.class);
        extension.addListener(listener);

        extension.networkStateUpdated(ZigBeeNetworkState.ONLINE);

        extension.networkStateUpdated(ZigBeeNetworkState.OFFLINE);
    }

    @Test
    public void timer_DISCOVER_KEY_ESTABLISHMENT_CLUSTER() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());

        checkMatchDescriptorRequest(SmartEnergyClientState.DISCOVER_KEY_ESTABLISHMENT_CLUSTER, 0x0000,
                ZclKeyEstablishmentCluster.CLUSTER_ID);
    }

    @Test
    public void timer_DISCOVER_METERING_SERVERS() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());

        checkMatchDescriptorRequest(SmartEnergyClientState.DISCOVER_METERING_SERVERS,
                ZigBeeBroadcastDestination.BROADCAST_RX_ON.getKey(), ZclMeteringCluster.CLUSTER_ID);
    }

    private void checkMatchDescriptorRequest(SmartEnergyClientState state, int destination, int clusterId)
            throws Exception {
        SmartEnergyClient extension = new SmartEnergyClient(Mockito.mock(ZigBeeCbkeProvider.class));
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));
        ArgumentCaptor<Long> timerCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<ZigBeeCommand> commandCaptor = ArgumentCaptor.forClass(ZigBeeCommand.class);

        Mockito.when(networkManager.scheduleTask(ArgumentMatchers.argThat(new ArgumentMatcher<Runnable>() {
            boolean doOnce = true;

            @Override
            public boolean matches(Runnable argument) {
                if (!doOnce) {
                    return true;
                }
                doOnce = false;
                Runnable runnable = argument;
                try {
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
                }
                return true;
            }
        }), timerCaptor.capture())).thenReturn(Mockito.mock(ScheduledFuture.class));

        TestUtilities.setField(SmartEnergyClient.class, extension, "seState", state);
        TestUtilities.invokeMethod(SmartEnergyClient.class, extension, "timerStart", int.class, 123456);

        Mockito.verify(networkManager, Mockito.timeout(TIMEOUT).times(1)).sendTransaction(commandCaptor.capture());
        assertEquals(Long.valueOf(30000), timerCaptor.getAllValues().get(0));
        assertTrue(commandCaptor.getValue() instanceof MatchDescriptorRequest);
        MatchDescriptorRequest matchRequest = (MatchDescriptorRequest) commandCaptor.getValue();
        System.out.println(matchRequest);
        assertEquals(destination, matchRequest.getDestinationAddress().getAddress());
        assertEquals(Integer.valueOf(0x0109), matchRequest.getProfileId());
        assertTrue(matchRequest.getInClusterList().contains(clusterId));
    }

    @Test
    public void timer_PERFORM_KEY_ESTABLISHMENT() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());

        SmartEnergyClient extension = new SmartEnergyClient(Mockito.mock(ZigBeeCbkeProvider.class));
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));
        ArgumentCaptor<Long> timerCaptor = ArgumentCaptor.forClass(Long.class);

        TestUtilities.setField(SmartEnergyClient.class, extension, "trustCenterKeyEstablishmentEndpoint", 1);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(networkManager.getNode(0)).thenReturn(node);
        ZigBeeEndpoint endpoint = Mockito.mock(ZigBeeEndpoint.class);
        Mockito.when(endpoint.getEndpointAddress()).thenReturn(new ZigBeeEndpointAddress(0, 1));
        Mockito.when(node.getEndpoint(1)).thenReturn(endpoint);
        ZclKeyEstablishmentCluster cluster = Mockito.mock(ZclKeyEstablishmentCluster.class);
        Mockito.when(endpoint.getInputCluster(ZclKeyEstablishmentCluster.CLUSTER_ID)).thenReturn(cluster);

        Mockito.when(networkManager.scheduleTask(ArgumentMatchers.argThat(new ArgumentMatcher<Runnable>() {
            boolean doOnce = true;

            @Override
            public boolean matches(Runnable argument) {
                if (!doOnce) {
                    return true;
                }
                doOnce = false;
                Runnable runnable = argument;
                try {
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
                }
                return true;
            }
        }), timerCaptor.capture())).thenReturn(Mockito.mock(ScheduledFuture.class));

        ZclKeyEstablishmentClient cbkeClient = Mockito.mock(ZclKeyEstablishmentClient.class);
        Map<ZigBeeEndpointAddress, ZclKeyEstablishmentClient> clientRegistry = new HashMap<>();
        clientRegistry.put(new ZigBeeEndpointAddress(0, 1), cbkeClient);
        TestUtilities.setField(SmartEnergyClient.class, extension, "cbkeClientRegistry", clientRegistry);

        TestUtilities.setField(SmartEnergyClient.class, extension, "seState",
                SmartEnergyClientState.PERFORM_KEY_ESTABLISHMENT);
        TestUtilities.invokeMethod(SmartEnergyClient.class, extension, "timerStart", int.class, 123456);

        Mockito.verify(cbkeClient, Mockito.timeout(TIMEOUT)).start();

        // Make sure we don't start a time - the only captured timer is the one we started in the test!
        assertEquals(1, timerCaptor.getAllValues().size());
        assertEquals(Long.valueOf(123456), timerCaptor.getAllValues().get(0));
    }

    @Test
    public void MatchDescriptorResponse_DISCOVER_KEY_ESTABLISHMENT_CLUSTER() throws Exception {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());

        SmartEnergyClient extension = new SmartEnergyClient(Mockito.mock(ZigBeeCbkeProvider.class));
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        Mockito.when(networkManager.getNode(0)).thenReturn(node);

        TestUtilities.setField(SmartEnergyClient.class, extension, "seState",
                SmartEnergyClientState.DISCOVER_KEY_ESTABLISHMENT_CLUSTER);

        MatchDescriptorResponse matchResponse = Mockito.mock(MatchDescriptorResponse.class);
        Mockito.when(matchResponse.getStatus()).thenReturn(ZdoStatus.SUCCESS);
        ZigBeeAddress sourceAddress = Mockito.mock(ZigBeeAddress.class);
        Mockito.when(sourceAddress.getAddress()).thenReturn(0);
        Mockito.when(matchResponse.getSourceAddress()).thenReturn(sourceAddress);

        List<Integer> matchList = Arrays.asList(1);
        Mockito.when(matchResponse.getMatchList()).thenReturn(matchList);
        extension.commandReceived(matchResponse);

        assertEquals(SmartEnergyClientState.PERFORM_KEY_ESTABLISHMENT, extension.getDiscoveryState());
    }

}
