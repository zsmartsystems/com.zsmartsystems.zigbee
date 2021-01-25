/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.basic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;

/**
 * Tests for the {@link ZclBasicServerExtension}
 *
 * @author Chris Jackson
 */
public class ZigBeeBasicServerExtensionTest {

    @Test
    public void test() {
        ArgumentCaptor<ZigBeeCommand> commandListener = ArgumentCaptor.forClass(ZigBeeCommand.class);
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        Mockito.when(networkManager.sendCommand(commandListener.capture())).thenReturn(true);

        ZigBeeBasicServerExtension basicServer = new ZigBeeBasicServerExtension();
        assertEquals(ZigBeeStatus.SUCCESS, basicServer.extensionInitialize(networkManager));
        assertEquals(ZigBeeStatus.SUCCESS, basicServer.extensionStartup());
        Mockito.verify(networkManager, Mockito.times(1)).addSupportedServerCluster(ZclBasicCluster.CLUSTER_ID);
        Mockito.verify(networkManager, Mockito.times(1)).addNetworkNodeListener(basicServer);

        assertTrue(basicServer.setAttribute(ZclBasicCluster.ATTR_MODELIDENTIFIER, "ModelId"));
        assertTrue(basicServer.setAttribute(ZclBasicCluster.ATTR_MANUFACTURERNAME, "ZSmartSystems"));
        assertFalse(basicServer.setAttribute(65535, 0));

        ZigBeeNode node = new ZigBeeNode(networkManager, Mockito.mock(IeeeAddress.class));
        ZigBeeEndpoint endpoint1 = new ZigBeeEndpoint(node, 1);
        ZclBasicCluster cluster = new ZclBasicCluster(endpoint1);
        endpoint1.addOutputCluster(cluster);
        ZigBeeEndpoint endpoint2 = new ZigBeeEndpoint(node, 2);
        node.addEndpoint(endpoint1);
        node.addEndpoint(endpoint2);
        Set<ZigBeeNode> nodes = new HashSet<>();
        nodes.add(node);

        assertFalse(cluster.getLocalAttribute(ZclBasicCluster.ATTR_MODELIDENTIFIER).isImplemented());
        assertFalse(cluster.getLocalAttribute(ZclBasicCluster.ATTR_MANUFACTURERNAME).isImplemented());
        assertFalse(cluster.getLocalAttribute(ZclBasicCluster.ATTR_ZCLVERSION).isImplemented());
        assertFalse(cluster.getLocalAttribute(ZclBasicCluster.ATTR_POWERSOURCE).isImplemented());

        Mockito.when(networkManager.getNodes()).thenReturn(nodes);
        basicServer.nodeAdded(node);

        assertTrue(cluster.getLocalAttribute(ZclBasicCluster.ATTR_MODELIDENTIFIER).isImplemented());
        assertTrue(cluster.getLocalAttribute(ZclBasicCluster.ATTR_MANUFACTURERNAME).isImplemented());
        assertTrue(cluster.getLocalAttribute(ZclBasicCluster.ATTR_ZCLVERSION).isImplemented());
        assertTrue(cluster.getLocalAttribute(ZclBasicCluster.ATTR_POWERSOURCE).isImplemented());

        basicServer.extensionShutdown();
    }
}
