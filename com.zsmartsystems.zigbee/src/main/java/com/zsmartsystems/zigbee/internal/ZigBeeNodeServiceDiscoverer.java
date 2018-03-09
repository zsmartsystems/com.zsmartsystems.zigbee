/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeBroadcastDestination;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsRequest;
import com.zsmartsystems.zigbee.zdo.command.ActiveEndpointsResponse;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.IeeeAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingResponse;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressRequest;
import com.zsmartsystems.zigbee.zdo.command.NetworkAddressResponse;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.NodeDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.PowerDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorRequest;
import com.zsmartsystems.zigbee.zdo.command.SimpleDescriptorResponse;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;

/**
 * This class contains methods for discovering the services and features of a {@link ZigBeeNode}. All discovery methods
 * are private and the class is utilised by calling {@link #startDiscovery(Set)} with a set of
 * {@link #NodeDiscoveryState} for the stages wishing to be discovered or updated.
 * <p>
 * A single worker thread is ensured - if the thread is already active when {@link #startDiscovery(Set)} is called, the
 * new tasks will be added to the existing task queue if they are not already in the queue. If the worker thread is not
 * running, it will be started.
 * <p>
 * This class provides a centralised helper, used for discovering and updating information about the {@link ZigBeeNode}
 * <p>
 * A random exponential backoff is used for retries to reduce congestion.
 * <p>
 * Once the discovery update is complete the {@link ZigBeeNetworkManager#updateNode(ZigBeeNode)} is called to alert
 * users.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNodeServiceDiscoverer {
    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNodeServiceDiscoverer.class);

    /**
     * The {@link ZigBeeNetworkManager}.
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * The {@link ZigBeeNode}.
     */
    private final ZigBeeNode node;

    /**
     * Default maximum number of retries to perform
     */
    private final int DEFAULT_MAX_BACKOFF = 12;

    /**
     * Default period between retries
     */
    private final int DEFAULT_RETRY_PERIOD = 2100;

    /**
     * A random jitter will be added to the retry time for each device to avoid any sort of synchronisation
     */
    private final int RETRY_RANDOM_TIME = 250;

    /**
     * The maximum number of retries to perform before failing the request
     */
    private int maxBackoff = DEFAULT_MAX_BACKOFF;

    /**
     * The minimum number of milliseconds to wait before retrying the request
     */
    private int retryPeriod = DEFAULT_RETRY_PERIOD;

    /**
     * Flag to indicate if the device supports the {@link ManagementLqiRequest}
     * This is updated to false if the device responds with {@link ZdoStatus#NOT_SUPPORTED}
     */
    private boolean supportsManagementLqi = true;

    /**
     * Flag to indicate if the device supports the {@link ManagementRoutingRequest}.
     * This is updated to false if the device responds with {@link ZdoStatus#NOT_SUPPORTED}
     */
    private boolean supportsManagementRouting = true;

    /**
     * The task being run
     */
    private ScheduledFuture<?> futureTask;

    /**
     *
     *
     */
    public enum NodeDiscoveryTask {
        NWK_ADDRESS,
        ASSOCIATED_NODES,
        NODE_DESCRIPTOR,
        POWER_DESCRIPTOR,
        ACTIVE_ENDPOINTS,
        NEIGHBORS,
        ROUTES
    }

    /**
     * The list of tasks we need to complete
     */
    private final Queue<NodeDiscoveryTask> discoveryTasks = new LinkedList<NodeDiscoveryTask>();

    /**
     * Creates the discovery class
     *
     * @param networkManager the {@link ZigBeeNetworkManager} for the network
     * @param node the {@link ZigBeeNode} whose services we want to discover
     */
    public ZigBeeNodeServiceDiscoverer(ZigBeeNetworkManager networkManager, ZigBeeNode node) {
        this.networkManager = networkManager;
        this.node = node;

        retryPeriod = DEFAULT_RETRY_PERIOD + new Random().nextInt(RETRY_RANDOM_TIME);
    }

    /**
     * Performs node service discovery. This discovers node level attributes such as the endpoints and
     * descriptors, as well as updating routing and neighbor tables - as needed.
     * <p>
     * If any of the tasks requested are already in the queue, they will not be added again.
     * <p>
     * If the worker thread is not running, it will be started.
     *
     * @param newTasks a set of {@link NodeDiscoveryTask}s to be performed
     */
    public void startDiscovery(Set<NodeDiscoveryTask> newTasks) {
        // Tasks are managed in a queue. The worker thread will only remove the task from the queue once the task is
        // complete. When no tasks are left in the queue, the worker thread will exit.
        synchronized (discoveryTasks) {
            // Remove any tasks that we know are not supported by this device
            if (!supportsManagementLqi && newTasks.contains(NodeDiscoveryTask.NEIGHBORS)) {
                newTasks.remove(NodeDiscoveryTask.NEIGHBORS);
            }
            if (!supportsManagementRouting && newTasks.contains(NodeDiscoveryTask.ROUTES)) {
                newTasks.remove(NodeDiscoveryTask.ROUTES);
            }

            // Make sure there are still tasks to perform
            if (newTasks.isEmpty()) {
                logger.debug("{}: Node SVC Discovery has no tasks to perform", node.getIeeeAddress());
                return;
            }

            boolean startWorker = discoveryTasks.isEmpty();

            // Add new tasks, avoiding any duplication
            for (NodeDiscoveryTask newTask : newTasks) {
                if (!discoveryTasks.contains(newTask)) {
                    discoveryTasks.add(newTask);
                }
            }

            if (!startWorker) {
                logger.debug("{}: Node SVC Discovery already scheduled or running", node.getIeeeAddress());
                return;
            }
        }

        logger.debug("{}: Node SVC Discovery scheduled {}", node.getIeeeAddress(), discoveryTasks);
        final Runnable runnable = new NodeServiceDiscoveryTask();

        futureTask = networkManager.scheduleTask(runnable, new Random().nextInt(retryPeriod));
    }

    /**
     * @return the maxBackoff
     */
    public int getMaxBackoff() {
        return maxBackoff;
    }

    /**
     * @param maxBackoff the maxBackoff to set
     */
    public void setMaxBackoff(int maxBackoff) {
        this.maxBackoff = maxBackoff;
    }

    /**
     * Get node descriptor
     *
     * @return true if the message was processed ok
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean requestNetworkAddress() throws InterruptedException, ExecutionException {
        NetworkAddressRequest networkAddressRequest = new NetworkAddressRequest();
        networkAddressRequest.setIeeeAddr(node.getIeeeAddress());
        networkAddressRequest.setRequestType(0);
        networkAddressRequest.setStartIndex(0);
        networkAddressRequest
                .setDestinationAddress(new ZigBeeEndpointAddress(ZigBeeBroadcastDestination.BROADCAST_RX_ON.getKey()));

        CommandResult response = networkManager.unicast(networkAddressRequest, networkAddressRequest).get();
        final NetworkAddressResponse networkAddressResponse = (NetworkAddressResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery NetworkAddressRequest returned {}", node.getNetworkAddress(),
                networkAddressResponse);
        if (networkAddressResponse == null) {
            return false;
        }

        if (networkAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
            node.setNetworkAddress(networkAddressResponse.getNwkAddrRemoteDev());

            return true;
        }

        return false;
    }

    /**
     * Get Node Network address and the list of associated devices
     *
     * @return true if the message was processed ok
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean requestAssociatedNodes() throws InterruptedException, ExecutionException {
        Integer startIndex = 0;
        int totalAssociatedDevices = 0;
        Set<Integer> associatedDevices = new HashSet<Integer>();

        do {
            // Request extended response, to get associated list
            final IeeeAddressRequest ieeeAddressRequest = new IeeeAddressRequest();
            ieeeAddressRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
            ieeeAddressRequest.setRequestType(1);
            ieeeAddressRequest.setStartIndex(startIndex);
            ieeeAddressRequest.setNwkAddrOfInterest(node.getNetworkAddress());
            CommandResult response = networkManager.unicast(ieeeAddressRequest, ieeeAddressRequest).get();

            final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
            logger.debug("{}: Node SVC Discovery IeeeAddressResponse returned {}", node.getIeeeAddress(),
                    ieeeAddressResponse);
            if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                associatedDevices.addAll(ieeeAddressResponse.getNwkAddrAssocDevList());

                startIndex += ieeeAddressResponse.getNwkAddrAssocDevList().size();
                totalAssociatedDevices = ieeeAddressResponse.getNwkAddrAssocDevList().size();
            }
        } while (startIndex < totalAssociatedDevices);

        node.setAssociatedDevices(associatedDevices);

        return true;
    }

    /**
     * Get node descriptor
     *
     * @return true if the message was processed ok
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean requestNodeDescriptor() throws InterruptedException, ExecutionException {
        final NodeDescriptorRequest nodeDescriptorRequest = new NodeDescriptorRequest();
        nodeDescriptorRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
        nodeDescriptorRequest.setNwkAddrOfInterest(node.getNetworkAddress());

        CommandResult response = networkManager.unicast(nodeDescriptorRequest, nodeDescriptorRequest).get();
        final NodeDescriptorResponse nodeDescriptorResponse = (NodeDescriptorResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery NodeDescriptorResponse returned {}", node.getIeeeAddress(),
                nodeDescriptorResponse);
        if (nodeDescriptorResponse == null) {
            return false;
        }

        if (nodeDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
            node.setNodeDescriptor(nodeDescriptorResponse.getNodeDescriptor());

            return true;
        }

        return false;
    }

    /**
     * Get node power descriptor
     *
     * @return true if the message was processed ok, or if the end device does not support the power descriptor
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean requestPowerDescriptor() throws InterruptedException, ExecutionException {
        final PowerDescriptorRequest powerDescriptorRequest = new PowerDescriptorRequest();
        powerDescriptorRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
        powerDescriptorRequest.setNwkAddrOfInterest(node.getNetworkAddress());

        CommandResult response = networkManager.unicast(powerDescriptorRequest, powerDescriptorRequest).get();
        final PowerDescriptorResponse powerDescriptorResponse = (PowerDescriptorResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery PowerDescriptorResponse returned {}", node.getIeeeAddress(),
                powerDescriptorResponse);
        if (powerDescriptorResponse == null) {
            return false;
        }

        if (powerDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
            node.setPowerDescriptor(powerDescriptorResponse.getPowerDescriptor());

            return true;
        } else if (powerDescriptorResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
            return true;
        }

        return false;
    }

    /**
     * Get the active endpoints for a node
     *
     * @return true if the message was processed ok
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean requestActiveEndpoints() throws InterruptedException, ExecutionException {
        final ActiveEndpointsRequest activeEndpointsRequest = new ActiveEndpointsRequest();
        activeEndpointsRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
        activeEndpointsRequest.setNwkAddrOfInterest(node.getNetworkAddress());

        CommandResult response = networkManager.unicast(activeEndpointsRequest, activeEndpointsRequest).get();
        final ActiveEndpointsResponse activeEndpointsResponse = (ActiveEndpointsResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery ActiveEndpointsResponse returned {}", node.getIeeeAddress(), response);
        if (activeEndpointsResponse == null) {
            return false;
        }

        // Get the simple descriptors for all endpoints
        List<ZigBeeEndpoint> endpoints = new ArrayList<ZigBeeEndpoint>();
        for (final int endpointId : activeEndpointsResponse.getActiveEpList()) {
            ZigBeeEndpoint endpoint = getSimpleDescriptor(endpointId);
            if (endpoint == null) {
                return false;
            }

            endpoints.add(endpoint);
        }

        // All endpoints have been received, so add them to the node
        for (ZigBeeEndpoint endpoint : endpoints) {
            node.addEndpoint(endpoint);
        }

        return true;
    }

    /**
     * Get node neighbor table by making a {@link ManagementLqiRequest} call.
     *
     * @return list of {@link NeighborTable} if the request was processed ok, null otherwise
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean requestNeighborTable() throws InterruptedException, ExecutionException {
        // Start index for the list is 0
        int startIndex = 0;
        int totalNeighbors = 0;
        Set<NeighborTable> neighbors = new HashSet<NeighborTable>();
        do {
            final ManagementLqiRequest neighborRequest = new ManagementLqiRequest();
            neighborRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
            neighborRequest.setStartIndex(startIndex);

            CommandResult response = networkManager.unicast(neighborRequest, neighborRequest).get();
            final ManagementLqiResponse neighborResponse = response.getResponse();
            logger.debug("{}: Node SVC Discovery ManagementLqiRequest response {}", node.getIeeeAddress(), response);
            if (neighborResponse == null) {
                return false;
            }

            if (neighborResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                supportsManagementLqi = false;
                return true;
            } else if (neighborResponse.getStatus() != ZdoStatus.SUCCESS) {
                return false;
            }

            // Some devices may report the number of entries as the total number they can maintain.
            // To avoid a loop, we need to check if there's any response.
            if (neighborResponse.getNeighborTableList().size() == 0) {
                break;
            }

            // Save the neighbors
            neighbors.addAll(neighborResponse.getNeighborTableList());

            // Continue with next request
            startIndex += neighborResponse.getNeighborTableList().size();
            totalNeighbors = neighborResponse.getNeighborTableEntries();
        } while (startIndex < totalNeighbors);

        node.setNeighbors(neighbors);

        return true;
    }

    /**
     * Get node routing table by making a {@link ManagementRoutingRequest} request
     *
     * @return list of {@link RoutingTable} if the request was processed ok, null otherwise
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private boolean requestRoutingTable() throws InterruptedException, ExecutionException {
        // Start index for the list is 0
        int startIndex = 0;
        int totalRoutes = 0;
        Set<RoutingTable> routes = new HashSet<RoutingTable>();
        do {
            final ManagementRoutingRequest routeRequest = new ManagementRoutingRequest();
            routeRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
            routeRequest.setStartIndex(startIndex);

            CommandResult response = networkManager.unicast(routeRequest, routeRequest).get();
            final ManagementRoutingResponse routingResponse = response.getResponse();
            logger.debug("{}: Node SVC Discovery ManagementRoutingRequest returned {}", node.getIeeeAddress(),
                    response);
            if (routingResponse == null) {
                return false;
            }

            if (routingResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                supportsManagementRouting = false;
                return true;
            } else if (routingResponse.getStatus() != ZdoStatus.SUCCESS) {
                return false;
            }

            // Save the routes
            routes.addAll(routingResponse.getRoutingTableList());

            // Continue with next request
            startIndex += routingResponse.getRoutingTableList().size();
            totalRoutes = routingResponse.getRoutingTableEntries();
        } while (startIndex < totalRoutes);

        node.setRoutes(routes);

        return true;
    }

    /**
     * Get the simple descriptor for an endpoint and create the {@link ZigBeeEndpoint}
     *
     * @param endpointId the endpoint id to request
     * @return the newly created {@link ZigBeeEndpoint} for the endpoint, or null on error
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private ZigBeeEndpoint getSimpleDescriptor(final int endpointId) throws InterruptedException, ExecutionException {
        final SimpleDescriptorRequest simpleDescriptorRequest = new SimpleDescriptorRequest();
        simpleDescriptorRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
        simpleDescriptorRequest.setNwkAddrOfInterest(node.getNetworkAddress());
        simpleDescriptorRequest.setEndpoint(endpointId);

        CommandResult response = networkManager.unicast(simpleDescriptorRequest, simpleDescriptorRequest).get();

        final SimpleDescriptorResponse simpleDescriptorResponse = (SimpleDescriptorResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery SimpleDescriptorResponse returned {}", node.getIeeeAddress(),
                simpleDescriptorResponse);
        if (simpleDescriptorResponse == null) {
            return null;
        }

        if (simpleDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
            ZigBeeEndpoint endpoint = new ZigBeeEndpoint(networkManager, node, endpointId);
            SimpleDescriptor simpleDescriptor = simpleDescriptorResponse.getSimpleDescriptor();
            endpoint.setProfileId(simpleDescriptor.getProfileId());
            endpoint.setDeviceId(simpleDescriptor.getDeviceId());
            endpoint.setDeviceVersion(simpleDescriptor.getDeviceVersion());
            endpoint.setInputClusterIds(simpleDescriptor.getInputClusterList());
            endpoint.setOutputClusterIds(simpleDescriptor.getOutputClusterList());

            return endpoint;
        }

        return null;
    }

    private class NodeServiceDiscoveryTask implements Runnable {
        private int retryCnt = 0;
        private int retryMin = 0;

        @Override
        public void run() {
            try {
                logger.debug("{}: Node SVC Discovery running", node.getIeeeAddress());
                NodeDiscoveryTask discoveryTask;

                synchronized (discoveryTasks) {
                    discoveryTask = discoveryTasks.peek();
                }
                if (discoveryTask == null) {
                    logger.debug("{}: Node SVC Discovery complete", node.getIeeeAddress());
                    networkManager.updateNode(node);
                    return;
                }

                boolean success = false;
                switch (discoveryTask) {
                    case NWK_ADDRESS:
                        success = requestNetworkAddress();
                        break;
                    case NODE_DESCRIPTOR:
                        success = requestNodeDescriptor();
                        break;
                    case POWER_DESCRIPTOR:
                        success = requestPowerDescriptor();
                        break;
                    case ACTIVE_ENDPOINTS:
                        success = requestActiveEndpoints();
                        break;
                    case ASSOCIATED_NODES:
                        success = requestAssociatedNodes();
                        break;
                    case NEIGHBORS:
                        success = requestNeighborTable();
                        break;
                    case ROUTES:
                        success = requestRoutingTable();
                        break;
                    default:
                        logger.debug("{}: Node SVC Discovery unknown task: {}", node.getIeeeAddress(), discoveryTask);
                        break;
                }

                int retryDelay = 0;
                if (success) {
                    synchronized (discoveryTasks) {
                        discoveryTasks.remove(discoveryTask);
                    }
                    logger.debug("{}: Node SVC Discovery request {} successful. Advanced to {}.", node.getIeeeAddress(),
                            discoveryTask, discoveryTasks.peek());

                    retryCnt = 0;
                } else {
                    retryCnt++;
                    retryMin = retryCnt / 4;

                    // We failed with the last request. Wait a bit then retry.
                    retryDelay = (new Random().nextInt(retryCnt) + 1 + retryMin) * retryPeriod;
                    logger.debug("{}: Node SVC Discovery request {} failed. Retry {}, wait {}ms before retry.",
                            node.getIeeeAddress(), discoveryTask, retryCnt, retryDelay);
                }

                // Reschedule the task
                futureTask = networkManager.rescheduleTask(futureTask, this, retryDelay);
            } catch (InterruptedException e) {
                // Eat me
            } catch (Exception e) {
                logger.error("{}: Node SVC Discovery exception: ", node.getIeeeAddress(), e);
            }
        }
    }
}
