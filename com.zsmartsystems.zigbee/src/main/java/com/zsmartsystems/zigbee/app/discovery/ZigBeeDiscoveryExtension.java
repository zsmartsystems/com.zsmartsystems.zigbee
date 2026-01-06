/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeCommand;
import com.zsmartsystems.zigbee.ZigBeeCommandListener;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNetworkNodeListener;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeNode.ZigBeeNodeState;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.app.ZigBeeNetworkExtension;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeNodeServiceDiscoverer.NodeDiscoveryTask;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveResponse;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressRequest;

/**
 * This class implements a {@link ZigBeeNetworkExtension} to perform network discovery and monitoring.
 * <p>
 * The periodic mesh update will periodically request information from the node so as to keep information updated. The
 * tasks may be specified from the list of {@link NodeDiscoveryTask}s, and in general it may be expected to update the
 * routing information (with {@link NodeDiscoveryTask#ROUTES}) and the neighbour information (with
 * {@link NodeDiscoveryTask#NEIGHBORS}). In some networks, it may be advantageous not to update all information as it
 * may place a high load on the network.
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
     * The ZigBee network {@link ZigBeeNetworkDiscoverer}. The discover is responsible for monitoring the network for
     * new devices and the initial interrogation of their capabilities.
     */
    private ZigBeeNetworkDiscoverer networkDiscoverer;

    /**
     * Map of {@link ZigBeeNodeServiceDiscoverer} for each node
     */
    private final Map<IeeeAddress, ZigBeeNodeServiceDiscoverer> nodeDiscovery = new ConcurrentHashMap<>();

    /**
     * Refresh period for the mesh update - in seconds
     */
    private int updatePeriod;

    /**
     * If set to true, the discovery task will perform a rediscovery if changes such as devices leaving and joining are
     * detected
     */
    private boolean updateOnChange = false;

    private ScheduledFuture<?> futureTask = null;

    private ZigBeeNetworkManager networkManager;

    private boolean extensionStarted = false;

    /**
     * List of tasks to be completed during a mesh update.
     * We want to get the neighbors and routes so we have visibility of the mesh. We also default to requesting the
     * network address to ensure that it hasn't changed.
     */
    private List<NodeDiscoveryTask> meshUpdateTasks = Arrays
            .asList(new NodeDiscoveryTask[] { NodeDiscoveryTask.NWK_ADDRESS, NodeDiscoveryTask.NEIGHBORS,
                    NodeDiscoveryTask.ROUTES });

    @Override
    public ZigBeeStatus extensionInitialize(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
        return ZigBeeStatus.SUCCESS;
    }

    @Override
    public ZigBeeStatus extensionStartup() {
        if (extensionStarted) {
            logger.debug("DISCOVERY Extension: Already started");
            return ZigBeeStatus.INVALID_STATE;
        }
        logger.debug("DISCOVERY Extension: Startup");

        networkManager.addNetworkNodeListener(this);

        if (updateOnChange) {
            networkManager.addCommandListener(this);
        }

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

        if (updateOnChange) {
            networkManager.removeCommandListener(this);
        }

        stopScheduler();

        if (networkDiscoverer != null) {
            networkDiscoverer.shutdown();
        }

        synchronized (nodeDiscovery) {
            for (ZigBeeNodeServiceDiscoverer nodeDiscoverer : nodeDiscovery.values()) {
                nodeDiscoverer.stopDiscovery();
            }
        }

        extensionStarted = false;

        logger.debug("DISCOVERY Extension: Shutdown");
    }

    /**
     * Sets the update period for the mesh update service. This is the number of seconds between
     * subsequent mesh updates. Setting the period to 0 will disable mesh updates.
     *
     * @param updatePeriod number of seconds between mesh updates. Setting to 0 will stop updates.
     * @deprecated User {@link setUpdateMeshPeriod} instead
     */
    @Deprecated
    public void setUpdatePeriod(final int updatePeriod) {
        setUpdateMeshPeriod(updatePeriod);
    }

    /**
     * Sets the update period for the mesh update service. This is the number of seconds between
     * subsequent mesh updates. Setting the period to 0 will disable mesh updates.
     *
     * @param updatePeriod number of seconds between mesh updates. Setting to 0 will stop updates.
     */
    public void setUpdateMeshPeriod(final int updatePeriod) {
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
     * The mesh can be set to update when there are changes to the network - eg devices leaving or returning.
     *
     * @param updateOnChange true to perform a mesh update when changes are detected
     */
    public void setUpdateOnChange(boolean updateOnChange) {
        if (this.updateOnChange == updateOnChange) {
            return;
        }
        if (updateOnChange) {
            networkManager.addCommandListener(this);
        } else {
            networkManager.removeCommandListener(this);
        }
        this.updateOnChange = updateOnChange;
    }

    /**
     * Updates the list of tasks to be completed when the mesh update is executed. The change will take effect at the
     * next update.
     *
     * @param tasks a List of {@link NodeDiscoveryTask}s to execute as part of the mesh update
     */
    public void setUpdateMeshTasks(List<NodeDiscoveryTask> tasks) {
        meshUpdateTasks = tasks;
    }

    /**
     * Gets the current period at which the mesh data is being updated (in seconds). A return value of 0 indicates that
     * automatic updates are currently disabled.
     *
     * @return number of seconds between mesh updates. 0 indicates no automatic updates.
     *
     */
    public int getUpdateMeshPeriod() {
        return updatePeriod;
    }

    /**
     * Gets the current period at which the mesh data is being updated (in seconds). A return value of 0 indicates that
     * automatic updates are currently disabled.
     *
     * @return number of seconds between mesh updates. 0 indicates no automatic updates.
     * @deprecated User {@link getUpdateMeshPeriod} instead
     */
    @Deprecated
    public int getUpdatePeriod() {
        return getUpdateMeshPeriod();
    }

    /**
     * Performs an immediate refresh of the network mesh information. Subsequent updates are performed at the current
     * update rate, and the timer is restarted from the time of calling this method.
     */
    public void refresh() {
        logger.debug("DISCOVERY Extension: Start mesh update task with interval of {} seconds", updatePeriod);

        // Delay the start slightly to allow any further processing to complete.
        // Also allows successive responses to filter through without retriggering an update.
        startScheduler(10);
    }

    @Override
    public void nodeAdded(ZigBeeNode node) {
        synchronized (nodeDiscovery) {
            startDiscoveryIfNecessary(node);
        }
    }

    @Override
    public void nodeUpdated(ZigBeeNode node) {
        synchronized (nodeDiscovery) {
            // We need to handle the cases where the node changes to ONLINE, or to OFFLINE
            if (node.getNodeState() == ZigBeeNodeState.ONLINE) {
                // If the state is ONLINE, then ensure discovery is running
                startDiscoveryIfNecessary(node);
            } else if (node.getNodeState() != ZigBeeNodeState.ONLINE
                    && nodeDiscovery.containsKey(node.getIeeeAddress())) {
                // If state is not ONLINE, then stop discovery
                stopDiscovery(node);
            }
        }
    }

    @Override
    public void nodeRemoved(ZigBeeNode node) {
        logger.debug("{}: DISCOVERY Extension: Removing discoverer", node.getIeeeAddress());
        stopDiscovery(node);
    }

    private void startDiscoveryIfNecessary(ZigBeeNode node) {
        ZigBeeNodeServiceDiscoverer nodeDiscoverer = nodeDiscovery.get(node.getIeeeAddress());
        // either there is no node discoverer or it has finished its tasks unsuccessfully
        if (nodeDiscoverer == null || nodeDiscoverer.isFinished() && !nodeDiscoverer.isSuccessful()) {
            logger.debug("{}: DISCOVERY Extension: Adding discoverer for node", node.getIeeeAddress());
            startDiscovery(node);
        } else if (!nodeDiscoverer.isFinished()) {
            // kill old node discoverer and create a new one
            logger.debug("{}: DISCOVERY Extension: Creating new discoverer for node", node.getIeeeAddress());
            stopDiscovery(node);
            startDiscovery(node);
        }
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
     * Starts a discovery on a node. This will send a {@link NetworkAddressRequest} as a unicast, and if no response is
     * received, as a broadcast.
     * When it receives the response it will trigger a full discovery.
     *
     * @param ieeeAddress the {@link IeeeAddress} of the node to discover
     */
    public void rediscoverNode(final IeeeAddress ieeeAddress) {
        networkDiscoverer.rediscoverNode(ieeeAddress);
    }

    protected void startDiscovery(ZigBeeNode node) {
        synchronized (nodeDiscovery) {
            ZigBeeNodeServiceDiscoverer nodeDiscoverer = new ZigBeeNodeServiceDiscoverer(networkManager, node);
            nodeDiscoverer.setUpdateMeshTasks(meshUpdateTasks);
            nodeDiscovery.put(node.getIeeeAddress(), nodeDiscoverer);
            nodeDiscoverer.startDiscovery();
        }
    }

    protected void stopDiscovery(ZigBeeNode node) {
        ZigBeeNodeServiceDiscoverer discoverer = nodeDiscovery.remove(node.getIeeeAddress());
        if (discoverer != null) {
            discoverer.stopDiscovery();
        }
    }

    protected void stopScheduler() {
        if (futureTask != null) {
            futureTask.cancel(true);
            futureTask = null;
        }
    }

    protected void startScheduler(int initialPeriod) {
        stopScheduler();

        futureTask = networkManager.scheduleTask(this::meshUpdateThread, initialPeriod,
                TimeUnit.SECONDS.toMillis(updatePeriod));
    }

    private void meshUpdateThread() {
        synchronized (nodeDiscovery) {
            logger.debug("DISCOVERY Extension: Starting mesh update");
            for (ZigBeeNodeServiceDiscoverer node : nodeDiscovery.values()) {
                logger.debug("{}: DISCOVERY Extension: Starting mesh update", node.getNode().getIeeeAddress());
                node.setUpdateMeshTasks(meshUpdateTasks);
                node.updateMesh();
            }
        }
    };

    /**
     * Gets the Collection of {@link ZigBeeNodeServiceDiscoverer}s for all nodes
     *
     * @return Collection of {@link ZigBeeNodeServiceDiscoverer}
     */
    public Collection<ZigBeeNodeServiceDiscoverer> getNodeDiscoverers() {
        synchronized (nodeDiscovery) {
            return nodeDiscovery.values();
        }
    }

}
