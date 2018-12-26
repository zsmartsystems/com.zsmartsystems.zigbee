/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import static org.junit.Assert.assertEquals;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDiscoveryExtensionTest {
    @Test
    public void test() throws Exception {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        Mockito.when(node.getNodeState()).thenReturn(ZigBeeNodeState.ONLINE);

        ZigBeeDiscoveryExtension extension = Mockito.spy(ZigBeeDiscoveryExtension.class);
        Mockito.doNothing().when(extension).startDiscovery(node);
        Mockito.doNothing().when(extension).stopDiscovery(node);
        Mockito.doNothing().when(extension).startScheduler(ArgumentMatchers.any(int.class));

        extension.setUpdatePeriod(0);

        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));
        extension.extensionStartup();

        extension.setUpdatePeriod(0);
        Mockito.verify(extension, Mockito.times(1)).stopScheduler();
        assertEquals(0, extension.getUpdatePeriod());

        extension.setUpdatePeriod(1);
        Mockito.verify(extension, Mockito.times(1)).startScheduler(1);
        assertEquals(1, extension.getUpdatePeriod());

        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .addNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .addCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));

        extension.nodeAdded(node);
        Mockito.verify(extension, Mockito.times(1)).startDiscovery(node);

        Map<IeeeAddress, ZigBeeNodeServiceDiscoverer> nodeDiscovery = new ConcurrentHashMap<>();
        nodeDiscovery.put(node.getIeeeAddress(), Mockito.mock(ZigBeeNodeServiceDiscoverer.class));
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

        extension.extensionShutdown();

        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));
    }
}
