/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;

/**
 * Tests for {@link ZigBeeNetworkGroupManager}
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkGroupManagerTest {

    @Test
    public void initialize() {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeNetworkGroupManager groupManager = new ZigBeeNetworkGroupManager(networkManager);

        assertTrue(groupManager.getAll().isEmpty());
        groupManager.initialize();
        assertTrue(groupManager.getAll().isEmpty());

        Set<ZigBeeGroupDao> daoSet = new HashSet<>();
        daoSet.add(new ZigBeeGroupDao());

        Mockito.when(networkManager.readNetworkDataStore(any())).thenReturn(daoSet);

        assertTrue(groupManager.getAll().isEmpty());
        groupManager.initialize();
        assertEquals(1, groupManager.getAll().size());
    }
}
