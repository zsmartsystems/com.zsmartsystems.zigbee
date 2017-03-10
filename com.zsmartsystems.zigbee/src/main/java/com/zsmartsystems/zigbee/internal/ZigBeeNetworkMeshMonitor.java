package com.zsmartsystems.zigbee.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingResponse;
import com.zsmartsystems.zigbee.zdo.descriptors.NeighborTable;
import com.zsmartsystems.zigbee.zdo.descriptors.RoutingTable;

/**
 * {@link ZigBeeNetworkMeshMonitor} is used to walk through the network getting information about the mesh network.
 * <p>
 * The class uses the following ZDO commands to get neighbor information and link quality information -:
 * <ul>
 * <li>{@link ManagementLqiRequest}
 * <li>{@link ManagementRoutingRequest}
 * </ul>
 * It will always start from the coordinator and walk through the network requesting the data from all neighbors of each
 * node. A node will only be interrogated once per cycle and the update period can be set with the
 * {@link #setUpdatePeriod(int)} method.
 * <p>
 * This class is thread safe.
 *
 * @author Chris Jackson
 */
public class ZigBeeNetworkMeshMonitor {
    /**
     * The logger.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeNetworkMeshMonitor.class);

    /**
     * Maximum number of retries to perform
     */
    private static final int RETRY_COUNT = 3;

    /**
     * Period between retries
     */
    // private static final int RETRY_PERIOD = 1500;

    /**
     * The ZigBee command interface.
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * A map of the received IEEE addresses mapped to the network address.
     */
    private Set<Integer> nodesInProgress = Collections.synchronizedSet(new HashSet<Integer>());

    /**
     * The main thread for the update task
     */
    private Runnable meshUpdateThread = null;

    /**
     * Executor service to execute update threads. We use a {@link Executors.newFixedThreadPool}
     * to provide a fixed number of threads as otherwise this could result in a large number of
     * simultaneous threads in large networks.
     */
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

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

    /**
     * Starts up ZigBee mesh update service.
     *
     * @param updatePeriod number of seconds between mesh updates
     */
    public void startup(final int updatePeriod) {
        logger.debug("Starting mesh update task with interval of {} seconds", updatePeriod);
        nodesInProgress = Collections.synchronizedSet(new HashSet<Integer>());

        meshUpdateThread = new Runnable() {
            @Override
            public void run() {
                // Start discovery from root node.
                startMeshUpdate(0);
            }
        };

        futureTask = scheduler.scheduleAtFixedRate(meshUpdateThread, updatePeriod, updatePeriod, TimeUnit.SECONDS);
    }

    /**
     * Sets the update period for the mesh update service. This is the number of seconds between
     * subsequent mesh updates.
     *
     * @param updatePeriod number of seconds between mesh updates
     */
    public void setUpdatePeriod(final int updatePeriod) {
        logger.debug("Restarting mesh update task with interval of {} seconds", updatePeriod);
        if (futureTask != null) {
            futureTask.cancel(true);
        }

        futureTask = scheduler.scheduleAtFixedRate(meshUpdateThread, updatePeriod, updatePeriod, TimeUnit.SECONDS);
    }

    /**
     * Shuts down ZigBee mesh update service.
     */
    public void shutdown() {
        logger.debug("Stopping mesh update task");

        if (futureTask != null) {
            futureTask.cancel(true);
        }
    }

    /**
     * Starts a mesh update for the specified node.
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
                logger.debug("Mesh update for {} already in progress", nodeNetworkAddress);
                return;
            }
            nodesInProgress.add(nodeNetworkAddress);
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                logger.debug("Starting mesh update for {}", nodeNetworkAddress);

                boolean updateRequired = false;
                ZigBeeNode node = networkManager.getNode(nodeNetworkAddress);
                if (node == null) {
                    logger.debug("ZigBee node {} not found during mesh update", nodeNetworkAddress);

                    synchronized (nodesInProgress) {
                        nodesInProgress.remove(nodeNetworkAddress);
                    }

                    return;
                }

                List<NeighborTable> neighbors = null;
                List<RoutingTable> routes = null;

                neighbors = getNeighborTable(nodeNetworkAddress);

                // Only check the routing table for full function devices (ie routers and coordinators)
                if (node.isFullFuntionDevice()) {
                    routes = getRoutingTable(nodeNetworkAddress);
                } else {
                    logger.debug("Not updating routing table for {}: type is {}", nodeNetworkAddress,
                            node.getLogicalType());
                }

                if (node.setNeighbors(neighbors)) {
                    updateRequired = true;
                }

                if (node.setRoutes(routes)) {
                    updateRequired = true;
                }

                // If the data has changed, then notify everyone.
                if (updateRequired) {
                    networkManager.updateNode(node);
                }

                // Set the last update time even if the data hasn't changed.
                // This signals to the higher layer that things are still moving.
                node.setLastUpdateTime();

                synchronized (nodesInProgress) {
                    nodesInProgress.remove(nodeNetworkAddress);
                }

                logger.debug("Ending mesh update for {}", nodeNetworkAddress);
            }
        });
    }

    /**
     * Get Node IEEE address
     *
     * @param networkAddress the network address of the node
     * @return list of {@link NeighborTable} if the request was processed ok, null otherwise
     */
    private List<NeighborTable> getNeighborTable(final int networkAddress) {
        try {
            // Start index for the list is 0
            int retries = 0;
            int startIndex = 0;
            int totalNeighbors = 0;
            List<NeighborTable> neighbors = new ArrayList<NeighborTable>();
            do {
                final ManagementLqiRequest neighborRequest = new ManagementLqiRequest();
                neighborRequest.setDestinationAddress(new ZigBeeDeviceAddress(networkAddress));
                neighborRequest.setStartIndex(startIndex);
                CommandResult response = networkManager.unicast(neighborRequest, neighborRequest).get();

                final ManagementLqiResponse neighborResponse = response.getResponse();
                if (neighborResponse != null && neighborResponse.getStatus() == 0) {
                    // Save the neighbors
                    for (NeighborTable neighbor : neighborResponse.getNeighborTableList()) {
                        neighbors.add(neighbor);
                    }

                    // Continue with next request;
                    startIndex += neighborResponse.getNeighborTableListCount();
                    totalNeighbors = neighborResponse.getNeighborTableEntries();
                } else {
                    logger.debug("ManagementLqiRequest for {} returned {}", networkAddress, neighborResponse);

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
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in getNeighbourTable", e);
        }

        return null;
    }

    /**
     * Get node descriptor
     *
     * @param networkAddress the network address of the node
     * @return list of {@link RoutingTable} if the request was processed ok, null otherwise
     */
    private List<RoutingTable> getRoutingTable(final int networkAddress) {
        try {
            // Start index for the list is 0
            int retries = 0;
            int startIndex = 0;
            int totalRoutes = 0;
            List<RoutingTable> routes = new ArrayList<RoutingTable>();
            do {
                final ManagementRoutingRequest routeRequest = new ManagementRoutingRequest();
                routeRequest.setDestinationAddress(new ZigBeeDeviceAddress(networkAddress));
                routeRequest.setStartIndex(startIndex);
                CommandResult response = networkManager.unicast(routeRequest, routeRequest).get();
                final ManagementRoutingResponse routingResponse = response.getResponse();
                if (routingResponse != null && routingResponse.getStatus() == 0) {
                    // Save the neighbors
                    for (RoutingTable route : routingResponse.getRoutingTableList()) {
                        routes.add(route);
                    }

                    // Continue with next request;
                    startIndex += routingResponse.getRoutingTableListCount();
                    totalRoutes = routingResponse.getRoutingTableEntries();
                } else {
                    logger.debug("ManagementRoutingRequest for {} returned {}", networkAddress, routingResponse);

                    if (retries++ >= RETRY_COUNT) {
                        return null;
                    }
                    continue;
                }
            } while (startIndex < totalRoutes);

            return routes;
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in getRoutingTable", e);
        }

        return null;
    }

}
