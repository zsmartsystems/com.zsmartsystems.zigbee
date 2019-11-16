/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkState;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;
import com.zsmartsystems.zigbee.security.ZigBeeCryptoSuites;
import com.zsmartsystems.zigbee.zcl.clusters.ZclKeyEstablishmentCluster;

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
        SmartEnergyClient extension = new SmartEnergyClient(Mockito.mock(ZigBeeCbkeProvider.class));
        SmartEnergyStatusCallback listener = Mockito.mock(SmartEnergyStatusCallback.class);
        extension.addListener(listener);

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

        extension.keyEstablishmentCallback(ZigBeeStatus.FATAL_ERROR, 0);
        Mockito.verify(listener, Mockito.timeout(TIMEOUT)).sepStatusUpdate(ZigBeeSepClientStatus.FATAL_ERROR);

        extension.removeListener(listener);
    }

    @Test
    public void networkStateUpdated() {
        System.out.println("--- " + Thread.currentThread().getStackTrace()[1].getMethodName());

        SmartEnergyClient extension = new SmartEnergyClient(Mockito.mock(ZigBeeCbkeProvider.class));
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));

        SmartEnergyStatusCallback listener = Mockito.mock(SmartEnergyStatusCallback.class);
        extension.addListener(listener);

        extension.networkStateUpdated(ZigBeeNetworkState.ONLINE);

        extension.networkStateUpdated(ZigBeeNetworkState.OFFLINE);
    }
}
