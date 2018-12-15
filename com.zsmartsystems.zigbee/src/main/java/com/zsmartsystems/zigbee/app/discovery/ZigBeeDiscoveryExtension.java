/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveResponse;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressRequest;

/**
 * This class implements a {@link ZigBeeNetworkExtension} to perform network discovery and monitoring.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDiscoveryExtension
        implements ZigBeeNetworkExtension, ZigBeeNetworkNodeListener, ZigBeeCommandListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeDiscoveryExtension.class);

    /**
     * The ZigBee network {@link ZigBeeNetworkDiscoverer}. The discover is
     * responsible for monitoring the network for new devices and the initial
     * interrogation of their capabilities.
     */
    private ZigBeeNetworkDiscoverer networkDiscoverer;

    /**
     *
     */
    private final Map<IeeeAddress, ZigBeeNodeServiceDiscoverer> nodeDiscovery = new ConcurrentHashMap<>();

    /**
     * Refresh period for the mesh update - in seconds
     */
    private int updatePeriod;

    private ScheduledFuture<?> futureTask = null;

    private ZigBeeNetworkManager networkManager;

    private boolean extensionStarted = false;

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus extensionStartup() {
        logger.debug("DISCOVERY Extension: Startup");

        networkManager.addNetworkNodeListener(this);
        networkManager.addCommandListener(this);

        networkDiscoverer = new ZigBeeNetworkDiscoverer(networkManager);
        networkDiscoverer.startup();

        if (updatePeriod != 0) {
            startScheduler(10);
        }

        extensionStarted = true;

        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public void extensionShutdown() {
        networkManager.removeNetworkNodeListener(this);
        networkManager.removeCommandListener(this);

        if (networkDiscoverer != null) {
            networkDiscoverer.shutdown();
        }

        extensionStarted = false;

        if (futureTask != null) {
            futureTask.cancel(true);
        }
        logger.debug("DISCOVERY Extension: Shutdown");
    }

    /**
     * Sets the update period for the mesh update service. This is the number of seconds between
     * subsequent mesh updates. Setting the period to 0 will disable mesh updates.
     *
     * @param updatePeriod number of seconds between mesh updates. Setting to 0 will stop updates.
     */
    public void setUpdatePeriod(final int updatePeriod) {
        this.updatePeriod = updatePeriod;

        if (!extensionStarted) {
            return;
        }

        logger.debug("DISCOVERY Extension: Set mesh update interval to {} seconds", updatePeriod);

        if (updatePeriod == 0) {
            stopScheduler();
            return;
        }

        startScheduler(updatePeriod);
    }

    /**
     * Gets the current period at which the mesh data is being updated (in seconds). A return value of 0 indicates that
     * automatic updates are currently disabled.
     *
     * @return number of seconds between mesh updates. 0 indicates no automatic updates.
     *
     */
    public int getUpdatePeriod() {
        return updatePeriod;
    }

    /**
     * Performs an immediate refresh of the network. Subsequent updates are performed at the current update rate, and
     * the timer is restarted from the time of calling this method.
     */
    public void refresh() {
        logger.debug("DISCOVERY Extension: Start mesh update task with interval of {} seconds", updatePeriod);

        // Delay the start slightly to allow any further processing to complete.
        // Also allows successive responses to filter through without retriggering an update.
        startScheduler(10);
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        if (nodeDiscovery.containsKey(node.getIeeeAddress())) {
            return;
        }

        logger.debug("DISCOVERY Extension: Adding discoverer for {}", node.getIeeeAddress());

        ZigBeeNodeServiceDiscoverer nodeDiscoverer = new ZigBeeNodeServiceDiscoverer(networkManager, node);
        nodeDiscovery.put(node.getIeeeAddress(), nodeDiscoverer);
        nodeDiscoverer.startDiscovery();
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        // Not used
    }

    @Override
    public void nodeRemoved(ZigBeeNode node) {
        logger.debug("DISCOVERY Extension: Removing discoverer for {}", node.getIeeeAddress());
        nodeDiscovery.remove(node.getIeeeAddress());
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        // Listen for specific commands that may indicate that the mesh has changed
        if (command instanceof ManagementLeaveResponse || command instanceof DeviceAnnounce) {
            logger.debug("DISCOVERY Extension: Mesh related command received. Triggering mesh update.");
            refresh();
        }
    }

    /**
     * Starts a discovery on a node.
     *
     * @param nodeAddress the network address of the node to discover
     */
    public void rediscoverNode(final int nodeAddress) {
        networkDiscoverer.rediscoverNode(nodeAddress);
    }

    /**
     * Starts a discovery on a node. This will send a {@link NetworkAddressRequest} as a broadcast and will receive
     * the response to trigger a full discovery.
     *
     * @param ieeeAddress the {@link IeeeAddress} of the node to discover
     */
    public void rediscoverNode(final IeeeAddress ieeeAddress) {
        networkDiscoverer.rediscoverNode(ieeeAddress);
    }

    private void stopScheduler() {
        if (futureTask != null) {
            futureTask.cancel(true);
            futureTask = null;
        }
    }

    private void startScheduler(int initialPeriod) {
        stopScheduler();

        Runnable meshUpdateThread = new Runnable() {
            @Override
            public void run() {
                logger.debug("DISCOVERY Extension: Starting mesh update");
                for (ZigBeeNodeServiceDiscoverer node : nodeDiscovery.values()) {
                    logger.debug("DISCOVERY Extension: Starting mesh update for {}", node.getNode().getIeeeAddress());
                    node.updateMesh();
                }
            }
        };

        futureTask = networkManager.scheduleTask(meshUpdateThread, initialPeriod, updatePeriod * 1000);
    }

    /**
     * Gets the Collection of {@link ZigBeeNodeServiceDiscoverer} for all nodes
     * 
     * @return Collection of {@link ZigBeeNodeServiceDiscoverer}
     */
    public Collection<ZigBeeNodeServiceDiscoverer> getNodeDiscoverers() {
        return nodeDiscovery.values();
    }

}
