package com.zsmartsystems.zigbee.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
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
 * It will always start from the coordinator and walk through the network requesting the data from all known nodes.
 * A node will only be interrogated once per cycle and the update period can be set with the
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
    private static final int RETRIES_COUNT = 3;

    /**
     * Period between retries
     */
    private static final int RETRIES_PERIOD = 1500;

    /**
     * The ZigBee command interface.
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * A map of the received IEEE addresses mapped to the network address.
     */
    private Set<Integer> nodesInProgress = Collections.synchronizedSet(new HashSet<Integer>());

    /**
     * Executor service to execute update threads. We use a {@link Executors.newFixedThreadPool}
     * to provide a fixed number of threads as otherwise this could result in a large number of
     * simultaneous threads in large networks.
     */
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    /**
     * Number of seconds between mesh updates
     */
    private int updatePeriod;

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
        nodesInProgress = Collections.synchronizedSet(new HashSet<Integer>());

        this.updatePeriod = updatePeriod;

        // Start discovery from root node.
        // startMeshUpdate(0);
    }

    /**
     * Sets the update period for the mesh update service. This is the number of seconds between
     * subsequent mesh updates.
     *
     * @param updatePeriod number of seconds between mesh updates
     */
    public void setUpdatePeriod(final int updatePeriod) {
        this.updatePeriod = updatePeriod;
    }

    /**
     * Shuts down ZigBee mesh update service.
     */
    public void shutdown() {
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
                return;
            }
            nodesInProgress.add(nodeNetworkAddress);
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                logger.debug("Starting mesh update for {}", nodeNetworkAddress);
                int retries = 0;
                boolean success;
                success = getNeighborTable(nodeNetworkAddress);

                if (success) {
                }

                // We failed with the last request. Wait a bit then retry
                try {
                    Thread.sleep(RETRIES_PERIOD);
                } catch (InterruptedException e) {
                    return;
                }

                logger.debug("Ending node discovery for {}", nodeNetworkAddress);
            }
        });
    }

    /**
     * Get Node IEEE address
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     */
    private boolean getNeighborTable(final int networkAddress) {
        try {
            // Start index for the list is 0
            int startIndex = 0;
            Map<Integer, NeighborTable> neighbors = new HashMap<Integer, NeighborTable>();
            boolean complete = false;
            while (!complete) {
                final ManagementLqiRequest neighborRequest = new ManagementLqiRequest();
                neighborRequest.setDestinationAddress(new ZigBeeDeviceAddress(networkAddress));
                neighborRequest.setStartIndex(startIndex);
                CommandResult response = networkManager.unicast(neighborRequest, neighborRequest).get();

                final ManagementLqiResponse neighborResponse = response.getResponse();
                if (neighborResponse != null && neighborResponse.getStatus() == 0) {
                    // Did we receive any neighbors - if not, we're done
                    if (neighborResponse.getNeighborTableListCount() == 0) {

                        return true;
                    }

                    // Save the neighbors
                    for (NeighborTable neighbor : neighborResponse.getNeighborTableList()) {
                        neighbors.put(neighbor.getNetworkAddress(), neighbor);
                    }

                    // Continue with next request;
                    startIndex += neighborResponse.getNeighborTableListCount();
                } else {
                    // TODO: Retries?
                    logger.debug("ManagementLqiRequest for {} returned {}", networkAddress, neighborResponse);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in getNeighbourTable", e);
        }

        return false;
    }

    /**
     * Get node descriptor
     *
     * @param networkAddress the network address of the node
     * @return true if the message was processed ok
     */
    private boolean getRoutingTable(final int networkAddress) {
        try {
            // Start index for the list is 0
            int startIndex = 0;
            Map<Integer, RoutingTable> routes = new HashMap<Integer, RoutingTable>();
            boolean complete = false;
            while (!complete) {
                final ManagementRoutingRequest routeRequest = new ManagementRoutingRequest();
                routeRequest.setDestinationAddress(new ZigBeeDeviceAddress(networkAddress));
                routeRequest.setStartIndex(startIndex);
                CommandResult response = networkManager.unicast(routeRequest, routeRequest).get();

                final ManagementRoutingResponse routingResponse = response.getResponse();
                if (routingResponse != null && routingResponse.getStatus() == 0) {
                    // Did we receive any neighbors - if not, we're done
                    if (routingResponse.getRoutingTableListCount() == 0) {

                        return true;
                    }

                    // Save the neighbors
                    for (RoutingTable route : routingResponse.getRoutingTableList()) {
                        routes.put(route.getDestinationAddress(), route);
                    }

                    // Continue with next request;
                    startIndex += routingResponse.getRoutingTableListCount();
                } else {
                    logger.debug("ManagementRoutingRequest for {} returned {}", networkAddress, routingResponse);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Error in getRoutingTable", e);
        }

        return false;
    }

}
