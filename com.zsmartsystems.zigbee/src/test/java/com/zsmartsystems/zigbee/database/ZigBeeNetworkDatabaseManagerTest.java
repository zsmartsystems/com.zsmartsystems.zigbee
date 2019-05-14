/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.database;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.TestUtilities;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkDatabaseManagerTest {
    private int TIMEOUT = 5000;

    @Test
    public void test() throws Exception {
        ZigBeeNetworkManager networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        ZigBeeNetworkDatabaseManager databaseManager = new ZigBeeNetworkDatabaseManager(networkManager);

        Mockito.when(networkManager.scheduleTask(ArgumentMatchers.argThat(new ArgumentMatcher<Runnable>() {
            @Override
            public boolean matches(Runnable argument) {
                Runnable runnable = argument;
                try {
                    runnable.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
                }
                return true;
            }
        }), ArgumentMatchers.anyLong())).thenReturn(Mockito.mock(ScheduledFuture.class));

        // Data Store not set so it will not do anything
        databaseManager.startup();
        Mockito.verify(networkManager, Mockito.times(0)).addNetworkNodeListener(databaseManager);

        List<ZigBeeEndpointDao> endpointDaoList = new ArrayList<>();
        ZigBeeNodeDao nodeDao = new ZigBeeNodeDao();
        nodeDao.setIeeeAddress(new IeeeAddress("1234567890ABCDEF"));
        nodeDao.setEndpoints(endpointDaoList);
        nodeDao.setNetworkAddress(Integer.valueOf(1));
        nodeDao.setBindingTable(new HashSet<BindingTable>());
        nodeDao.setNodeDescriptor(Mockito.mock(NodeDescriptor.class));
        nodeDao.setPowerDescriptor(Mockito.mock(PowerDescriptor.class));

        Set<IeeeAddress> nodes = new HashSet<>();
        nodes.add(new IeeeAddress("1234567890ABCDEF"));
        nodes.add(new IeeeAddress("0000000000000000"));
        ZigBeeNetworkDataStore dataStore = Mockito.mock(ZigBeeNetworkDataStore.class);
        Mockito.when(dataStore.readNetworkNodes()).thenReturn(nodes);
        Mockito.when(dataStore.readNode(new IeeeAddress("1234567890ABCDEF"))).thenReturn(nodeDao);

        ZigBeeNode node = Mockito.mock(ZigBeeNode.class);
        Mockito.when(node.getDao()).thenReturn(nodeDao);
        Mockito.when(node.getIeeeAddress()).thenReturn(new IeeeAddress("1234567890ABCDEF"));

        // No data store - make sure nothing happens
        databaseManager.nodeAdded(node);
        databaseManager.nodeUpdated(node);
        databaseManager.nodeRemoved(node);

        databaseManager.setDataStore(dataStore);

        databaseManager.startup();
        Mockito.verify(networkManager, Mockito.times(1)).addNetworkNodeListener(databaseManager);
        Mockito.verify(networkManager, Mockito.times(1)).addNode(ArgumentMatchers.any(ZigBeeNode.class));

        databaseManager.setDeferredWriteTime(0);

        databaseManager.setMaxDeferredWriteTime(Integer.MAX_VALUE);
        databaseManager.setMaxDeferredWriteTime(1000);

        databaseManager.nodeAdded(node);
        Mockito.verify(dataStore, Mockito.times(1)).writeNode(nodeDao);

        databaseManager.setDeferredWriteTime(1);

        databaseManager.nodeUpdated(node);
        Mockito.verify(dataStore, Mockito.timeout(TIMEOUT).times(2)).writeNode(nodeDao);

        Map<IeeeAddress, ScheduledFuture<?>> deferredWriteFutures = new ConcurrentHashMap<>();
        Map<IeeeAddress, Long> deferredWriteTimes = new ConcurrentHashMap<>();

        deferredWriteTimes.put(new IeeeAddress("1234567890ABCDEF"), Long.valueOf(0));
        deferredWriteFutures.put(new IeeeAddress("1234567890ABCDEF"), Mockito.mock(ScheduledFuture.class));

        TestUtilities.setField(ZigBeeNetworkDatabaseManager.class, databaseManager, "deferredWriteTimes",
                deferredWriteTimes);
        TestUtilities.setField(ZigBeeNetworkDatabaseManager.class, databaseManager, "deferredWriteFutures",
                deferredWriteFutures);
        networkManager = Mockito.mock(ZigBeeNetworkManager.class);
        TestUtilities.setField(ZigBeeNetworkDatabaseManager.class, databaseManager, "networkManager", networkManager);

        Mockito.when(networkManager.scheduleTask(ArgumentMatchers.any(Runnable.class), ArgumentMatchers.anyLong()))
                .thenReturn(Mockito.mock(ScheduledFuture.class));

        databaseManager.nodeUpdated(node);
        Mockito.verify(dataStore, Mockito.times(3)).writeNode(nodeDao);

        databaseManager.nodeRemoved(node);
        Mockito.verify(dataStore, Mockito.times(1)).removeNode(new IeeeAddress("1234567890ABCDEF"));

        databaseManager.shutdown();
        Mockito.verify(networkManager, Mockito.times(1)).removeNetworkNodeListener(databaseManager);
    }
}
