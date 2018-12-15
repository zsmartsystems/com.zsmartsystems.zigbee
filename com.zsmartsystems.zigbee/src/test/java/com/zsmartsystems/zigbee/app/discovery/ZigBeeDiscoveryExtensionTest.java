/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDiscoveryExtensionTest {
    @Test
    public void test() {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);

        ZigBeeDiscoveryExtension extension = new ZigBeeDiscoveryExtension();
        extension.setUpdatePeriod(0);

        assertEquals(ZigBeeStatus.SUCCESS, extension.extensionInitialize(networkManager));
        extension.extensionStartup();

        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .addNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .addCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));

        extension.extensionShutdown();

        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeNetworkNodeListener(ArgumentMatchers.any(ZigBeeNetworkNodeListener.class));
        Mockito.verify(networkManager, Mockito.timeout(1000).atLeast(1))
                .removeCommandListener(ArgumentMatchers.any(ZigBeeCommandListener.class));

        assertEquals(0, extension.getUpdatePeriod());
    }
}
