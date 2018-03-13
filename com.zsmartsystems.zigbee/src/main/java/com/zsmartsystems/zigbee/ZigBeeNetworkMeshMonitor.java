/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingRequest;

/**
 * {@link ZigBeeNetworkMeshMonitor} is used to walk through the network getting information about the mesh network.
 * <p>
 * The class uses the following ZDO commands to get neighbor information and link quality information -:
 * <ul>
 * <li>{@link ManagementLqiRequest}
 * <li>{@link ManagementRoutingRequest} (only for ROUTER or COORDINATOR)
 * </ul>
 * It will always start from the coordinator and walk through the network requesting the data from all neighbors
 * of each node. A node will only be interrogated once per cycle and the update period can be set with the
 * {@link #setUpdatePeriod(int)} method.
 * <p>
 * Routes are not traversed - only neighbours. This is to avoid erroneous destinations that may end up in the routing
 * table when a non existent node is requested. Nodes in the neighbour table should be reliable as they actually known
 * by the node.
 * <p>
 * This class is thread safe.
 *
 * @author Chris Jackson
 */
public class ZigBeeNetworkMeshMonitor implements ZigBeeCommandListener {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkMeshMonitor.class);

    /**
     * The ZigBee command interface.
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * Refresh period for the mesh update - in seconds
     */
    private int updatePeriod;

    private ScheduledFuture<?> futureTask = null;

    /**
     * Discovers ZigBee network state.
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     */
    public ZigBeeNetworkMeshMonitor(final ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    private void startScheduler(int initialPeriod) {
        if (futureTask != null) {
            futureTask.cancel(true);
        }

        Runnable meshUpdateThread = new Runnable() {
            @Override
            public void run() {
                for (ZigBeeNode node : networkManager.getNodes()) {
                    node.updateMesh();
                }
            }
        };

        futureTask = networkManager.scheduleTask(meshUpdateThread, initialPeriod, updatePeriod * 1000);
    }

    /**
     * Starts up ZigBee mesh update service.
     *
     * @param updatePeriod number of seconds between mesh updates
     */
    public void startup(final int updatePeriod) {
        this.updatePeriod = updatePeriod;
        logger.debug("Starting mesh update task with interval of {} seconds", updatePeriod);

        networkManager.addCommandListener(this);

        startScheduler(10);
    }

    /**
     * Sets the update period for the mesh update service. This is the number of seconds between
     * subsequent mesh updates.
     *
     * @param updatePeriod number of seconds between mesh updates
     */
    public void setUpdatePeriod(final int updatePeriod) {
        this.updatePeriod = updatePeriod;

        logger.debug("Restarting mesh update task with interval of {} seconds", updatePeriod);
        startScheduler(updatePeriod);
    }

    /**
     * Performs an immediate refresh of the network. Subsequent updates are performed at the current update rate, and
     * the timer is restarted from the time of calling this method.
     */
    public void refresh() {
        logger.debug("Refreshing mesh update task with interval of {} seconds", updatePeriod);

        // Delay the start slightly to allow any further processing to complete.
        // Also allows successive responses to filter through without retriggering an update.
        startScheduler(10);
    }

    /**
     * Shuts down ZigBee mesh update service.
     */
    public void shutdown() {
        logger.debug("Stopping mesh update task");

        networkManager.removeCommandListener(this);

        if (futureTask != null) {
            futureTask.cancel(true);
        }
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        // Listen for specific commands that may indicate that the mesh has changed
        if (command instanceof ManagementLeaveResponse || command instanceof DeviceAnnounce) {
            logger.debug("Mesh related command received. Triggering mesh update.");
            refresh();
        }
    }
}
