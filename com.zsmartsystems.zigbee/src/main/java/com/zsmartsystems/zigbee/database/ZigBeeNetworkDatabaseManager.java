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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeExecutors;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;

/**
 * This class implements the management functions for the network database. The network database persists data about the
 * nodes that are currently part of the network between restarts of the system.
 * <p>
 * The database manager will not normally write data to the data store immediately. It will instead defer the write for
 * the {@link #deferredWriteTime}. If there are many consecutive writes that continue to retrigger the deferred timer,
 * then after {@link #deferredWriteTimeout} period, the node will be written to the data store.
 * <p>
 * All writes to the {@link ZigBeeDataStore} are managed through a single thread scheduler to ensure that only a single
 * write is in progress at once. This allows the data store to be kept simple and ensures writes don't get queued thus
 * causing performance issues or multiple threads to be executed.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkDatabaseManager implements ZigBeeNetworkNodeListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkDatabaseManager.class);

    /**
     * The default time (in milliseconds) to defer writes. This will prevent multiple writes to a single node within
     * this period.
     */
    private final int DEFERRED_WRITE_DEFAULT = 250;

    /**
     * The default timeout after which continued retriggers of the deferred timer will force a write.
     */
    private final int DEFERRED_WRITE_TIMEOUT = 1000;

    /**
     * The maximum time that the user can set the deferred timeout to.
     */
    private final int DEFERRED_WRITE_TIMEOUT_MAX = 10000;

    /**
     * The time to wait for all threads to shutdown in milliseconds
     */
    private final int SHUTDOWN_TIMEOUT = 3000;

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
    private int deferredWriteTime = DEFERRED_WRITE_DEFAULT;

    /**
     * The maximum amount of time we will defer writes (in nanoseconds)
     */
    private long deferredWriteTimeout = TimeUnit.MILLISECONDS.toNanos(DEFERRED_WRITE_TIMEOUT);

    /**
     * Map of deferred write futures for each node
     */
    private final Map<IeeeAddress, ScheduledFuture<?>> deferredWriteFutures = new ConcurrentHashMap<>();

    /**
     * Map of the first time we deferred the write on the node
     */
    private final Map<IeeeAddress, Long> deferredWriteTimes = new ConcurrentHashMap<>();

    /**
     * Single thread scheduler to ensure single writes within the data store
     */
    private ScheduledExecutorService executorService = ZigBeeExecutors.newScheduledThreadPool(1, "DatabaseManager");

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
        if (deferredWriteTime > DEFERRED_WRITE_TIMEOUT_MAX) {
            this.deferredWriteTime = DEFERRED_WRITE_TIMEOUT_MAX;
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
        if (deferredWriteMax > DEFERRED_WRITE_TIMEOUT_MAX) {
            this.deferredWriteTimeout = TimeUnit.MILLISECONDS.toNanos(DEFERRED_WRITE_TIMEOUT_MAX);
            return;
        }
        // Note that internally this is stored in nanoseconds
        this.deferredWriteTimeout = TimeUnit.MILLISECONDS.toNanos(deferredWriteMax);
    }

    /**
     * Clears all data from the data store. This may be used when initialising a network to remove any previous data.
     */
    public void clear() {
        if (dataStore == null) {
            logger.debug("Data store: Undefined so network is not cleared.");
            return;
        }

        logger.debug("Data store:  Clearing all nodes.");
        Set<IeeeAddress> nodes = dataStore.readNetworkNodes();
        for (IeeeAddress nodeAddress : nodes) {
            dataStore.removeNode(nodeAddress);
        }
    }

    /**
     * Starts the database manager. This will call the {@link ZigBeeNetworkDataStore} to retrieve the list of nodes, and
     * then read all the nodes from the store, adding them to the {@link ZigBeeNetworkManager}.
     */
    public void startup() {
        if (dataStore == null) {
            logger.debug("Data store: Undefined so network is not restored.");
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
            logger.debug("{}: Data store: Node was restored.", nodeAddress);
            networkManager.updateNode(node);
        }

        networkManager.addNetworkNodeListener(this);
    }

    /**
     * Shuts down the database. This ensures that any outstanding writes are closed, thus ensuring the current state of
     * the network is saved.
     */
    public void shutdown() {
        logger.debug("Data store: Shutdown");
        networkManager.removeNetworkNodeListener(this);
        executorService.shutdown();
        try {
            executorService.awaitTermination(SHUTDOWN_TIMEOUT, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            logger.debug("Data store: Shutdown did not complete all tasks.");
        }
        executorService.shutdownNow();
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        nodeUpdated(node);
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
        int deferredDelay = deferredWriteTime;

        synchronized (deferredWriteFutures) {
            if (deferredWriteFutures.containsKey(node.getIeeeAddress())) {
                // Cancel the currently scheduled write
                deferredWriteFutures.get(node.getIeeeAddress()).cancel(false);

                if (deferredWriteTimes.get(node.getIeeeAddress()) != null
                        && deferredWriteTimes.get(node.getIeeeAddress()) < System.nanoTime()) {
                    logger.debug("{}: Data store: Maximum deferred time reached.", node.getIeeeAddress());

                    // Run the write immediately.
                    // This is still run through the scheduler to ensure we don't make
                    // simultaneous calls to the data store.
                    deferredDelay = 0;
                }
            } else {
                // First deferred write - save the time
                deferredWriteTimes.put(node.getIeeeAddress(), System.nanoTime() + deferredWriteTimeout);
            }

            logger.debug("{}: Data store: Deferring write for {}ms.", node.getIeeeAddress(), deferredDelay);

            CommitNodeTask commitTask = new CommitNodeTask(node);
            deferredWriteFutures.put(node.getIeeeAddress(),
                    executorService.schedule(commitTask, deferredDelay, TimeUnit.MILLISECONDS));
        }
    }

    private class CommitNodeTask implements Runnable {
        private ZigBeeNode node;

        CommitNodeTask(ZigBeeNode node) {
            this.node = node;
        }

        @Override
        public void run() {
            writeNode(node);
        }
    }

    private void writeNode(ZigBeeNode node) {
        logger.debug("{}: Data store: Writing node.", node.getIeeeAddress());
        synchronized (deferredWriteFutures) {
            deferredWriteTimes.remove(node.getIeeeAddress());
            deferredWriteFutures.remove(node.getIeeeAddress());
        }

        dataStore.writeNode(node.getDao());
    }

}
