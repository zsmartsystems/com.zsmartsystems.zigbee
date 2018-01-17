/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.DeviceAnnounce;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementLeaveResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingResponse;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable.NeighborTableJoining;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;

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
 * table when a non existant node is requested. Nodes in the neighbour table should be reliable as they actually known
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
     * Maximum number of retries to perform
     */
    private static final int RETRY_COUNT = 3;

    /**
     * The ZigBee command interface.
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * A map of the received node addresses that have already undergone an update in this cycle.
     */
    private Set<Integer> nodesInProgress = Collections.synchronizedSet(new HashSet<Integer>());

    /**
     * Refresh period for the mesh update - in seconds
     */
    private int updatePeriod;

    /**
     * Scheduler to run the service
     */
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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
                // Reset the node list
                synchronized (nodesInProgress) {
                    nodesInProgress = Collections.synchronizedSet(new HashSet<Integer>());
                }

                // Start discovery from root node.
                startMeshUpdate(0);
            }
        };

        futureTask = scheduler.scheduleAtFixedRate(meshUpdateThread, initialPeriod, updatePeriod, TimeUnit.SECONDS);
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
        startScheduler(3);
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

    /**
     * Starts a mesh update for the specified node.
     * <p>
     * The update will be scheduled for execution to avoid too many tasks running at once that may swamp the network.
     *
     * @param networkAddress network address of the node to update
     */
    private void startMeshUpdate(final int nodeNetworkAddress) {
        // Remember that we've started this node.
        // A node may appear multiple times as we walk through the mesh and we
        // only want to request a single update per node, per mesh update to
        // avoid loops.
        synchronized (nodesInProgress) {
            if (nodesInProgress.contains(nodeNetworkAddress)) {
                return;
            }
            nodesInProgress.add(nodeNetworkAddress);
        }

        networkManager.executeTask(new Runnable() {
            @Override
            public void run() {
                logger.debug("{}: Starting mesh update", nodeNetworkAddress);

                ZigBeeNode node = networkManager.getNode(nodeNetworkAddress);
                if (node == null) {
                    logger.debug("{}: ZigBee node not found during mesh update", nodeNetworkAddress);

                    // Notify that this is a new node so we can try and discover it
                    // TODO: Remove - devices should only join through the TC
                    networkManager.nodeStatusUpdate(ZigBeeNodeStatus.UNSECURED_JOIN, nodeNetworkAddress, null);

                    return;
                }

                try {
                    boolean update = false;

                    Set<Integer> associations = null;
                    Set<NeighborTable> neighbors = null;
                    Set<RoutingTable> routes = null;

                    // We request the neighbor table for all devices
                    neighbors = getNeighborTable(nodeNetworkAddress);
                    if (neighbors == null) {
                        logger.debug("{}: Neighbors not returned: skipping further updates.", nodeNetworkAddress);
                    } else if (node.isReducedFuntionDevice()) {
                        logger.debug("{}: Not updating routing table: type is {}", nodeNetworkAddress,
                                node.getLogicalType());
                    } else {
                        // Only check the associations and routing table for full function devices
                        // ie routers and coordinators.
                        associations = getAssociatedNodes(nodeNetworkAddress);
                        if (node.setAssociatedDevices(associations)) {
                            update = true;
                        }
                        routes = getRoutingTable(nodeNetworkAddress);
                        if (node.setRoutes(routes)) {
                            update = true;
                        }
                    }

                    if (node.setNeighbors(neighbors)) {
                        update = true;
                    }

                    // Set the last update time so long as we've received at least one response.
                    // This signals to the higher layer that things are still moving.
                    if (neighbors != null || routes != null || associations != null) {
                        node.setLastUpdateTime();

                        // We received a response so even if there were no updates, we notify the listeners
                        // so they know the time is updated.
                        update = true;
                    }

                    if (update) {
                        // Notify everyone.
                        networkManager.updateNode(node);
                    }

                    logger.debug("{}: Ending mesh update. Updated={}", nodeNetworkAddress, update);
                } catch (InterruptedException | ExecutionException e) {
                    logger.debug("{}: Mesh update exception: ", nodeNetworkAddress, e);
                }
            }
        });
    }

    /**
     * This method returns the devices associated to a node. It uses the {@link IeeeAddressRequest} call to request the
     * extended information which includes associated devices.
     *
     * @param networkAddress the network address of the node
     * @return the list of addresses of associated nodes
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private Set<Integer> getAssociatedNodes(final int networkAddress) throws InterruptedException, ExecutionException {
        // Start index for the list is 0
        int retries = 0;
        int startIndex = 0;
        int totalNodes = 0;

        Set<Integer> nodes = new HashSet<Integer>();
        do {
            // Request extended response, start index for associated list is 0
            final IeeeAddressRequest ieeeAddressRequest = new IeeeAddressRequest();
            ieeeAddressRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            ieeeAddressRequest.setRequestType(1);
            ieeeAddressRequest.setStartIndex(startIndex);
            ieeeAddressRequest.setNwkAddrOfInterest(networkAddress);
            CommandResult response = networkManager.unicast(ieeeAddressRequest, ieeeAddressRequest).get();

            final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
            if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                // Loop over all this nodes neighbors and start an update from them
                for (final int deviceNetworkAddress : ieeeAddressResponse.getNwkAddrAssocDevList()) {
                    startMeshUpdate(deviceNetworkAddress);
                }
                nodes.addAll(ieeeAddressResponse.getNwkAddrAssocDevList());

                // Continue with next request
                // TODO: Differentiate between total devices, and devices in this request
                startIndex += ieeeAddressResponse.getNwkAddrAssocDevList().size();
                totalNodes = ieeeAddressResponse.getNwkAddrAssocDevList().size();
            } else {
                logger.debug("{}: Ieee Address request returned {}", networkAddress, ieeeAddressResponse);

                if (retries++ >= RETRY_COUNT) {
                    return null;
                }
                continue;
            }
        } while (startIndex < totalNodes);

        return nodes;
    }

    /**
     * Get node neighbor table by making a {@link ManagementLqiRequest} call.
     *
     * @param networkAddress the network address of the node
     * @return list of {@link NeighborTable} if the request was processed ok, null otherwise
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private Set<NeighborTable> getNeighborTable(final int networkAddress)
            throws InterruptedException, ExecutionException {
        // Start index for the list is 0
        int retries = 0;
        int startIndex = 0;
        int totalNeighbors = 0;
        Set<NeighborTable> neighbors = new HashSet<NeighborTable>();
        do {
            final ManagementLqiRequest neighborRequest = new ManagementLqiRequest();
            neighborRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            neighborRequest.setStartIndex(startIndex);
            CommandResult response = networkManager.unicast(neighborRequest, neighborRequest).get();

            final ManagementLqiResponse neighborResponse = response.getResponse();
            if (neighborResponse != null && neighborResponse.getStatus() == ZdoStatus.SUCCESS) {
                // Save the neighbors
                neighbors.addAll(neighborResponse.getNeighborTableList());
                for (NeighborTable neighbor : neighborResponse.getNeighborTableList()) {
                    if (neighbor.getPermitJoining() != NeighborTableJoining.UNKNOWN) {
                        // Update the nodes "join enabled" flag
                        ZigBeeNode node = networkManager.getNode(neighbor.getExtendedAddress());
                        if (node != null) {
                            node.setJoining(neighbor.getPermitJoining() == NeighborTableJoining.ENABLED);
                        }
                    }
                }

                // Continue with next request
                startIndex += neighborResponse.getNeighborTableList().size();
                totalNeighbors = neighborResponse.getNeighborTableEntries();
            } else {
                logger.debug("{}: ManagementLqiRequest returned {}", networkAddress, neighborResponse);

                if (retries++ >= RETRY_COUNT) {
                    return null;
                }
                continue;
            }
        } while (startIndex < totalNeighbors);

        // Loop over all this nodes neighbors and start an update from them
        for (NeighborTable neighbor : neighbors) {
            startMeshUpdate(neighbor.getNetworkAddress());
        }

        return neighbors;
    }

    /**
     * Get node routing table by making a {@link ManagementRoutingRequest} request
     *
     * @param networkAddress the network address of the node
     * @return list of {@link RoutingTable} if the request was processed ok, null otherwise
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private Set<RoutingTable> getRoutingTable(final int networkAddress)
            throws InterruptedException, ExecutionException {
        // Start index for the list is 0
        int retries = 0;
        int startIndex = 0;
        int totalRoutes = 0;
        Set<RoutingTable> routes = new HashSet<RoutingTable>();
        do {
            final ManagementRoutingRequest routeRequest = new ManagementRoutingRequest();
            routeRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
            routeRequest.setStartIndex(startIndex);
            CommandResult response = networkManager.unicast(routeRequest, routeRequest).get();
            final ManagementRoutingResponse routingResponse = response.getResponse();
            if (routingResponse != null && routingResponse.getStatus() == ZdoStatus.SUCCESS) {
                // Save the routes
                routes.addAll(routingResponse.getRoutingTableList());

                // Continue with next request
                startIndex += routingResponse.getRoutingTableList().size();
                totalRoutes = routingResponse.getRoutingTableEntries();
            } else {
                logger.debug("{}: ManagementRoutingRequest returned {}", networkAddress, routingResponse);

                if (retries++ >= RETRY_COUNT) {
                    return null;
                }
                continue;
            }
        } while (startIndex < totalRoutes);

        return routes;
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
