/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.seclient;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkStateListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.security.ZigBeeCbkeProvider;

/**
 *
 * @author Chris Jackson
 *
 */
public class SmartEnergyClientTest {
    @Test
    public void testStartupShutdown() throws Exception {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));
        Mockito.when(node.getNodeState()).thenReturn(ZigBeeNodeState.ONLINE);

        SmartEnergyClient extension = new SmartEnergyClient(Mockito.mock(ZigBeeCbkeProvider.class));

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

        extension.extensionShutdown();

        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeNetworkStateListener(ArgumentMatchers.any(ZigBeeNetworkStateListener.class));
    }
}
