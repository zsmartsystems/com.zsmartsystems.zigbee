/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.database;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;

/**
 * This class implements the management functions for the network database. The network database persists data about the
 * nodes that are currently part of the network between restarts of the system.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkDatabaseManager implements ZigBeeNetworkNodeListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkDatabaseManager.class);

    private final int DEFERRED_WRITE_DEF = 1000;
    private final int DEFERRED_WRITE_MAX = 10000;

    /**
     * the {@link ZigBeeNetworkDataStore} that will be used to store the data
     */
    private ZigBeeNetworkDataStore dataStore;

    /**
     * The {@link ZigBeeNetworkManager} that this database is linked to
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The time to defer writes. If the node is not updated for this number of milliseconds, the data will be written.
     */
    private int deferredWriteTime = 250;

    /**
     * The maximum amount of time we will defer writes (in nanoseconds)
     */
    private long deferredWriteMax = TimeUnit.MILLISECONDS.toNanos(DEFERRED_WRITE_DEF);

    /**
     * Map of deferred write futures for each node
     */
    private Map<IeeeAddress, ScheduledFuture<?>> deferredWriteFutures = new ConcurrentHashMap<>();

    /**
     * Map of the first time we deferred the write on the node
     */
    private Map<IeeeAddress, Long> deferredWriteTimes = new ConcurrentHashMap<>();

    /**
     * Creates the database manager
     *
     * @param networkManager the {@link ZigBeeNetworkManager} to which this database is linked
     */
    public ZigBeeNetworkDatabaseManager(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Sets the data store to be used for persisting the network state
     *
     * @param dataStore the {@link ZigBeeNetworkDataStore} to use to persist the network nodes
     */
    public void setDataStore(ZigBeeNetworkDataStore dataStore) {
        this.dataStore = dataStore;
    }

    /**
     * Sets the deferred write timer. This is used to avoid writing to the data store too often, which may occur during
     * network discovery when a node is being updated often
     *
     * @param deferredWriteTime the number of milliseconds to wait before writing to the store
     */
    public void setDeferredWriteTime(int deferredWriteTime) {
        if (deferredWriteTime > DEFERRED_WRITE_MAX) {
            this.deferredWriteTime = DEFERRED_WRITE_MAX;
            return;
        }
        this.deferredWriteTime = deferredWriteTime;
    }

    /**
     * Sets the maximum deferred write timer. This will ensure that the data store is updated periodically in the event
     * that continuous updates are received for a node.
     *
     * @param deferredWriteMax the maximum number of milliseconds that writes will be deferred
     */
    public void setMaxDeferredWriteTime(int deferredWriteMax) {
        if (deferredWriteMax > DEFERRED_WRITE_MAX) {
            this.deferredWriteMax = TimeUnit.MILLISECONDS.toNanos(DEFERRED_WRITE_MAX);
            return;
        }
        // Note that internally this is stored in nanoseconds
        this.deferredWriteMax = TimeUnit.MILLISECONDS.toNanos(deferredWriteMax);
    }

    /**
     * Starts the database manager. This will call the {@link ZigBeeNetworkDataStore} to retrieve the list of nodes, and
     * then read all the nodes from the store, adding them to the {@link ZigBeeNetworkManager}.
     */
    public void startup() {
        if (dataStore == null) {
            logger.debug("Data store: undefined so no network is restored.");
            return;
        }

        Set<IeeeAddress> nodes = dataStore.readNetworkNodes();
        for (IeeeAddress nodeAddress : nodes) {
            ZigBeeNode node = new ZigBeeNode(networkManager, nodeAddress);
            ZigBeeNodeDao nodeDao = dataStore.readNode(nodeAddress);
            if (nodeDao == null) {
                logger.debug("{}: Data store: Node was not found in database.", nodeAddress);
                continue;
            }
            node.setDao(nodeDao);
            networkManager.addNode(node);
        }

        networkManager.addNetworkNodeListener(this);
    }

    /**
     * Shuts down the database. This ensures that any outstanding writes are closed, thus ensuring the current state of
     * the network is saved.
     */
    public void shutdown() {
        logger.debug("Data store: shutting down.");
        networkManager.removeNetworkNodeListener(this);
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        if (dataStore == null) {
            return;
        }

        // for(int node :networkManager.getNodes()) {
        // }
        // dataStore.writeNetworkNodes();
        saveNode(node);
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        if (dataStore == null) {
            return;
        }

        saveNode(node);
    }

    @Override
    public void nodeRemoved(ZigBeeNode node) {
        if (dataStore == null) {
            return;
        }

        dataStore.removeNode(node.getIeeeAddress());
    }

    private void saveNode(ZigBeeNode node) {
        if (deferredWriteTime == 0) {
            writeNode(node.getDao());
            return;
        }

        if (deferredWriteFutures.containsKey(node.getIeeeAddress())) {
            deferredWriteFutures.get(node.getIeeeAddress()).cancel(false);

            if (deferredWriteTimes.get(node.getIeeeAddress()) != null
                    && deferredWriteTimes.get(node.getIeeeAddress()) < System.nanoTime()) {
                logger.debug("{}: Data store: Maximum deferred time reached.", node.getIeeeAddress());
                writeNode(node.getDao());
                return;
            }
        } else {
            // First deferred write - save the time
            deferredWriteTimes.put(node.getIeeeAddress(), System.nanoTime() + deferredWriteMax);
        }

        CommitNodeTask commitTask = new CommitNodeTask(node.getDao());
        deferredWriteFutures.put(node.getIeeeAddress(), networkManager.scheduleTask(commitTask, deferredWriteTime));
    }

    private class CommitNodeTask implements Runnable {
        private ZigBeeNodeDao node;

        CommitNodeTask(ZigBeeNodeDao node) {
            this.node = node;
        }

        @Override
        public void run() {
            writeNode(node);
        }
    }

    private void writeNode(ZigBeeNodeDao node) {
        deferredWriteTimes.remove(node.getIeeeAddress());
        deferredWriteFutures.remove(node.getIeeeAddress());
        dataStore.writeNode(node);
    }

}
