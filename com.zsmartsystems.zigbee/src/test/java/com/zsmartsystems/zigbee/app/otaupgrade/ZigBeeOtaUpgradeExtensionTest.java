/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.otaupgrade;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeApplication;
import com.zsmartsystems.zigbee.app.otaserver.ZclOtaUpgradeServer;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaUpgradeExtension;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeOtaUpgradeExtensionTest {
    @Test
    public void extensionInitialize() {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeOtaUpgradeExtension extension = new ZigBeeOtaUpgradeExtension();

        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));
        Mockito.verify(networkManager, Mockito.times(1)).addSupportedServerCluster(ZclOtaUpgradeCluster.CLUSTER_ID);
        Mockito.verify(networkManager, Mockito.times(1)).addNetworkNodeListener(extension);

        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionStartup());

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getEndpoints()).thenReturn(Collections.emptyList());
        extension.nodeAdded(node);

        Collection<ZigBeeEndpoint> endpoints = new ArrayList<>();
        ZigBeeEndpoint endpoint1 = Mockito.mock(ZigBeeEndpoint.class);
        Mockito.when(endpoint1.getApplication(ZclOtaUpgradeCluster.CLUSTER_ID)).thenReturn(null);
        endpoints.add(endpoint1);
        Mockito.when(node.getEndpoints()).thenReturn(endpoints);
        extension.nodeAdded(node);

        ZclCluster cluster = Mockito.mock(ZclCluster.class);
        Mockito.when(cluster.getClusterId()).thenReturn(ZclOtaUpgradeCluster.CLUSTER_ID);
        Mockito.when(endpoint1.getOutputCluster(ZclOtaUpgradeCluster.CLUSTER_ID)).thenReturn(cluster);
        extension.nodeAdded(node);
        Mockito.verify(endpoint1, Mockito.times(1)).addApplication(ArgumentMatchers.any(ZclOtaUpgradeServer.class));

        ZigBeeApplication application = Mockito.mock(ZigBeeApplication.class);
        ZigBeeEndpoint endpoint2 = Mockito.mock(ZigBeeEndpoint.class);
        Mockito.when(endpoint2.getApplication(ZclOtaUpgradeCluster.CLUSTER_ID)).thenReturn(application);
        endpoints.add(endpoint2);
        extension.nodeAdded(node);

        extension.extensionShutdown();
        Mockito.verify(networkManager, Mockito.times(1)).removeNetworkNodeListener(extension);
    }
}
