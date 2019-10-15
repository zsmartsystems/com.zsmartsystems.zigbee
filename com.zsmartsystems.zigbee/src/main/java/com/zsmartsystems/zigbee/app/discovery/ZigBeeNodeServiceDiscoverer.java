/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.app.discovery;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
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
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.CurrentPowerModeType;
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
 * A random exponential backoff is used for retries to reduce congestion. If the device replies that a command is not
 * supported, then this will not be issued again on subsequent requests.
 * <p>
 * Once the discovery update is complete the {@link ZigBeeNetworkManager#addNode(ZigBeeNode)} is called to alert
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
     * The original {@link ZigBeeNode}. This is a reference to the node in the {@link ZigBeeNetworkManager}
     */
    private final ZigBeeNode node;

    /**
     * The {@link ZigBeeNode} that we are updating. All changes are made here so that we are not changing the data in
     * the {@link ZigBeeNetworkManager} directly. This ensures that change detection is working correctly.
     */
    private ZigBeeNode updatedNode;

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
     * Record of the last time we started a service discovery or update
     */
    private Calendar lastDiscoveryStarted;

    /**
     * Record of the last time we completed a service discovery or update
     */
    private Calendar lastDiscoveryCompleted;

    /**
     * List of tasks to be completed during a mesh update
     */
    private List<NodeDiscoveryTask> meshUpdateTasks = Collections.emptyList();

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

    private boolean closed = false;

    /**
     * Creates the discoverer
     *
     * @param networkManager the {@link ZigBeeNetworkManager} for the network
     * @param node the {@link ZigBeeNode} whose services we want to discover
     */
    public ZigBeeNodeServiceDiscoverer(ZigBeeNetworkManager networkManager, ZigBeeNode node) {
        this.networkManager = networkManager;
        this.node = node;

        retryPeriod = DEFAULT_RETRY_PERIOD + new Random().nextInt(RETRY_RANDOM_TIME);

        logger.debug("{}: Node SVC Discovery: created discoverer", node.getIeeeAddress());
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
    private void startDiscovery(Set<NodeDiscoveryTask> newTasks) {
        // Tasks are managed in a queue. The worker thread will only remove the task from the queue once the task is
        // complete. When no tasks are left in the queue, the worker thread will exit.
        synchronized (discoveryTasks) {
            logger.debug("{}: Node SVC Discovery: starting new tasks {}", node.getIeeeAddress(), newTasks);

            // Remove router/coordinator-only tasks if the device is possibly an end node.
            final boolean isPossibleEndDevice = isPossibleEndDevice();
            if ((!supportsManagementLqi || isPossibleEndDevice)) {
                newTasks.remove(NodeDiscoveryTask.NEIGHBORS);
            }
            if ((!supportsManagementRouting || isPossibleEndDevice)) {
                newTasks.remove(NodeDiscoveryTask.ROUTES);
            }

            // Make sure there are still tasks to perform
            if (newTasks.isEmpty()) {
                logger.debug("{}: Node SVC Discovery: has no new tasks to perform", node.getIeeeAddress());
                return;
            }

            // Check the current list of tasks to decide if we need to start the worker
            // This prevents restarting if we didn't add new tasks, which might overload the system
            boolean startWorker = discoveryTasks.isEmpty() || (futureTask == null);

            // Add new tasks, avoiding any duplication
            for (NodeDiscoveryTask newTask : newTasks) {
                if (!discoveryTasks.contains(newTask)) {
                    discoveryTasks.add(newTask);
                }
            }

            if (!startWorker) {
                logger.debug("{}: Node SVC Discovery: already scheduled or running", node.getIeeeAddress());
            } else {
                // Create a new node to store the data from this update.
                // We set the network address so that we can detect the change later if needed.
                updatedNode = new ZigBeeNode(networkManager, node.getIeeeAddress(), node.getNetworkAddress());
                lastDiscoveryStarted = Calendar.getInstance();
            }

            logger.debug("{}: Node SVC Discovery: scheduled {}", node.getIeeeAddress(), discoveryTasks);
            final Runnable runnable = new NodeServiceDiscoveryTask();

            if (futureTask != null) {
                futureTask.cancel(true);
            }
            futureTask = networkManager.scheduleTask(runnable, new Random().nextInt(retryPeriod));
        }
    }

    /**
     * Stops service discovery and removes any scheduled tasks
     */
    public void stopDiscovery() {
        closed = true;
        synchronized (discoveryTasks) {
            discoveryTasks.clear();
        }
        if (futureTask != null) {
            futureTask.cancel(true);
        }
        logger.debug("{}: Node SVC Discovery: stopped", node.getIeeeAddress());
    }

    /**
     * Gets the maximum backoff counter. If this number of retries are sent, the request will fail.
     *
     * @return the maxBackoff
     */
    public int getMaxBackoff() {
        return maxBackoff;
    }

    /**
     * Sets the maximum backoff counter. If this number of retries are sent, the request will fail.
     *
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
        networkAddressRequest.setDestinationAddress(
                new ZigBeeEndpointAddress(ZigBeeBroadcastDestination.BROADCAST_ALL_DEVICES.getKey()));

        CommandResult response = networkManager.sendTransaction(networkAddressRequest, networkAddressRequest).get();
        final NetworkAddressResponse networkAddressResponse = (NetworkAddressResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery: NetworkAddressRequest returned {}", node.getIeeeAddress(),
                networkAddressResponse);
        if (networkAddressResponse == null) {
            return false;
        }

        if (networkAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
            if (updatedNode.setNetworkAddress(networkAddressResponse.getNwkAddrRemoteDev())) {
                networkManager.updateNode(updatedNode);
            }

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
        int startIndex = 0;
        int totalAssociatedDevices = 0;
        Set<Integer> associatedDevices = new HashSet<Integer>();

        do {
            // Request extended response, to get associated list
            final IeeeAddressRequest ieeeAddressRequest = new IeeeAddressRequest();
            ieeeAddressRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
            ieeeAddressRequest.setRequestType(1);
            ieeeAddressRequest.setStartIndex(startIndex);
            ieeeAddressRequest.setNwkAddrOfInterest(node.getNetworkAddress());
            CommandResult response = networkManager.sendTransaction(ieeeAddressRequest, ieeeAddressRequest).get();

            final IeeeAddressResponse ieeeAddressResponse = response.getResponse();
            logger.debug("{}: Node SVC Discovery: IeeeAddressResponse returned {}", node.getIeeeAddress(),
                    ieeeAddressResponse);
            if (ieeeAddressResponse != null && ieeeAddressResponse.getStatus() == ZdoStatus.SUCCESS) {
                associatedDevices.addAll(ieeeAddressResponse.getNwkAddrAssocDevList());

                startIndex += ieeeAddressResponse.getNwkAddrAssocDevList().size();
                totalAssociatedDevices = ieeeAddressResponse.getNwkAddrAssocDevList().size();
            }
        } while (startIndex < totalAssociatedDevices);

        updatedNode.setAssociatedDevices(associatedDevices);

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

        CommandResult response = networkManager.sendTransaction(nodeDescriptorRequest, nodeDescriptorRequest).get();
        final NodeDescriptorResponse nodeDescriptorResponse = (NodeDescriptorResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery: NodeDescriptorResponse returned {}", node.getIeeeAddress(),
                nodeDescriptorResponse);
        if (nodeDescriptorResponse == null) {
            return false;
        }

        if (nodeDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
            updatedNode.setNodeDescriptor(nodeDescriptorResponse.getNodeDescriptor());

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

        CommandResult response = networkManager.sendTransaction(powerDescriptorRequest, powerDescriptorRequest).get();
        final PowerDescriptorResponse powerDescriptorResponse = (PowerDescriptorResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery: PowerDescriptorResponse returned {}", node.getIeeeAddress(),
                powerDescriptorResponse);
        if (powerDescriptorResponse == null) {
            return false;
        }

        if (powerDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
            updatedNode.setPowerDescriptor(powerDescriptorResponse.getPowerDescriptor());

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

        CommandResult response = networkManager.sendTransaction(activeEndpointsRequest, activeEndpointsRequest).get();
        final ActiveEndpointsResponse activeEndpointsResponse = (ActiveEndpointsResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery: ActiveEndpointsResponse returned {}", node.getIeeeAddress(), response);
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
            updatedNode.addEndpoint(endpoint);
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
        if (!supportsManagementLqi) {
            return true;
        }
        // Start index for the list is 0
        int startIndex = 0;
        int totalNeighbors = 0;
        Set<NeighborTable> neighbors = new HashSet<NeighborTable>();
        do {
            final ManagementLqiRequest neighborRequest = new ManagementLqiRequest();
            neighborRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
            neighborRequest.setStartIndex(startIndex);

            CommandResult response = networkManager.sendTransaction(neighborRequest, neighborRequest).get();
            final ManagementLqiResponse neighborResponse = response.getResponse();
            logger.debug("{}: Node SVC Discovery: ManagementLqiRequest response {}", node.getIeeeAddress(), response);
            if (neighborResponse == null) {
                return false;
            }

            if (neighborResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                logger.debug("{}: Node SVC Discovery: ManagementLqiRequest not supported", node.getIeeeAddress());
                supportsManagementLqi = false;
                return true;
            } else if (neighborResponse.getStatus() != ZdoStatus.SUCCESS) {
                logger.debug("{}: Node SVC Discovery: ManagementLqiRequest failed", node.getIeeeAddress());
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

        logger.debug("{}: Node SVC Discovery: ManagementLqiRequest complete [{} neighbors]", node.getIeeeAddress(),
                neighbors.size());
        updatedNode.setNeighbors(neighbors);

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
        if (!supportsManagementRouting) {
            return true;
        }
        // Start index for the list is 0
        int startIndex = 0;
        int totalRoutes = 0;
        Set<RoutingTable> routes = new HashSet<RoutingTable>();
        do {
            final ManagementRoutingRequest routeRequest = new ManagementRoutingRequest();
            routeRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));
            routeRequest.setStartIndex(startIndex);

            CommandResult response = networkManager.sendTransaction(routeRequest, routeRequest).get();
            final ManagementRoutingResponse routingResponse = response.getResponse();
            logger.debug("{}: Node SVC Discovery: ManagementRoutingRequest returned {}", node.getIeeeAddress(),
                    response);
            if (routingResponse == null) {
                return false;
            }

            if (routingResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                logger.debug("{}: Node SVC Discovery ManagementRoutingRequest not supported", node.getIeeeAddress());
                supportsManagementRouting = false;
                return true;
            } else if (routingResponse.getStatus() != ZdoStatus.SUCCESS) {
                logger.debug("{}: Node SVC Discovery: ManagementRoutingRequest failed", node.getIeeeAddress());
                return false;
            }

            // Save the routes
            routes.addAll(routingResponse.getRoutingTableList());

            // Continue with next request
            startIndex += routingResponse.getRoutingTableList().size();
            totalRoutes = routingResponse.getRoutingTableEntries();
        } while (startIndex < totalRoutes);

        logger.debug("{}: Node SVC Discovery: ManagementLqiRequest complete [{} routes]", node.getIeeeAddress(),
                routes.size());
        updatedNode.setRoutes(routes);

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

        CommandResult response = networkManager.sendTransaction(simpleDescriptorRequest, simpleDescriptorRequest).get();

        final SimpleDescriptorResponse simpleDescriptorResponse = (SimpleDescriptorResponse) response.getResponse();
        logger.debug("{}: Node SVC Discovery: SimpleDescriptorResponse returned {}", node.getIeeeAddress(),
                simpleDescriptorResponse);
        if (simpleDescriptorResponse == null) {
            return null;
        }

        if (simpleDescriptorResponse.getStatus() == ZdoStatus.SUCCESS) {
            ZigBeeEndpoint endpoint = new ZigBeeEndpoint(node, endpointId);
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
                NodeDiscoveryTask discoveryTask;
                synchronized (discoveryTasks) {
                    discoveryTask = discoveryTasks.peek();
                }
                if (discoveryTask == null) {
                    lastDiscoveryCompleted = Calendar.getInstance();
                    logger.debug("{}: Node SVC Discovery: complete", node.getIeeeAddress());
                    networkManager.updateNode(updatedNode);
                    return;
                }
                logger.debug("{}: Node SVC Discovery: running {}", node.getIeeeAddress(), discoveryTask);

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
                        logger.debug("{}: Node SVC Discovery: unknown task: {}", node.getIeeeAddress(), discoveryTask);
                        break;
                }

                if (closed) {
                    logger.debug("{}: Node SVC Discovery: closing scheduler thread", node.getIeeeAddress());
                    return;
                }

                retryCnt++;
                int retryDelay = 0;
                if (success) {
                    synchronized (discoveryTasks) {
                        discoveryTasks.remove(discoveryTask);
                    }
                    logger.debug("{}: Node SVC Discovery: request {} successful. Advancing to {}.",
                            node.getIeeeAddress(), discoveryTask, discoveryTasks.peek());
                    retryCnt = 0;
                } else if (retryCnt > maxBackoff) {
                    logger.debug("{}: Node SVC Discovery: request {} failed after {} attempts.", node.getIeeeAddress(),
                            discoveryTask, retryCnt);
                    synchronized (discoveryTasks) {
                        discoveryTasks.remove(discoveryTask);
                    }

                    retryCnt = 0;
                } else {
                    retryMin = retryCnt / 4;

                    // We failed with the last request. Wait a bit then retry.
                    retryDelay = (new Random().nextInt(retryCnt) + 1 + retryMin) * retryPeriod;
                    logger.debug("{}: Node SVC Discovery: request {} failed. Retry {}, wait {}ms before retry.",
                            node.getIeeeAddress(), discoveryTask, retryCnt, retryDelay);
                }

                // Reschedule the task
                futureTask = networkManager.rescheduleTask(futureTask, this, retryDelay);
            } catch (InterruptedException e) {
                // Eat me
            } catch (Exception e) {
                logger.error("{}: Node SVC Discovery: exception: ", node.getIeeeAddress(), e);
            }
        }
    }

    /**
     * Starts service discovery for the node.
     */
    public void startDiscovery() {
        logger.debug("{}: Node SVC Discovery: start discovery", node.getIeeeAddress());
        Set<NodeDiscoveryTask> tasks = new HashSet<NodeDiscoveryTask>();

        // Always request the network address unless this is our local node - in case it's changed
        if (!networkManager.getLocalNwkAddress().equals(node.getNetworkAddress())) {
            tasks.add(NodeDiscoveryTask.NWK_ADDRESS);
        }

        if (node.getNodeDescriptor() == null || node.getNodeDescriptor().getLogicalType() == LogicalType.UNKNOWN) {
            tasks.add(NodeDiscoveryTask.NODE_DESCRIPTOR);
        }

        if (node.getPowerDescriptor() == null
                || node.getPowerDescriptor().getCurrentPowerMode() == CurrentPowerModeType.UNKNOWN) {
            tasks.add(NodeDiscoveryTask.POWER_DESCRIPTOR);
        }

        if (node.getEndpoints().size() == 0 && !networkManager.getLocalNwkAddress().equals(node.getNetworkAddress())) {
            tasks.add(NodeDiscoveryTask.ACTIVE_ENDPOINTS);
        }

        startDiscovery(tasks);
    }

    /**
     * Starts service discovery to update the mesh. If the node is known to be a router or a coordinator, this adds the
     * {@link NodeDiscoveryTask#NEIGHBORS} and {@link NodeDiscoveryTask#ROUTES} tasks to the task list.
     */
    public void updateMesh() {
        if (isPossibleEndDevice()) {
            logger.debug("{}: Node SVC Discovery: Update mesh not performed for possible end device",
                    node.getIeeeAddress());

        } else {
            logger.debug("{}: Node SVC Discovery: Update mesh", node.getIeeeAddress());
            startDiscovery(new HashSet<>(meshUpdateTasks));
        }
    }

    /**
     * Is the node possibly an endNode?
     *
     * @return true if the device is not known to not be an end-device
     */
    private boolean isPossibleEndDevice() {
        switch (node.getLogicalType()) {
            case ROUTER:
            case COORDINATOR:
                return false;
        }
        return true;
    }

    /**
     * Gets the collection of {@link NodeDiscoveryTask}s that are currently outstanding for this discoverer
     *
     * @return collection of {@link NodeDiscoveryTask}s
     */
    public Collection<NodeDiscoveryTask> getTasks() {
        return discoveryTasks;
    }

    /**
     * Gets the {@link ZigBeeNode} to which this service discoverer is associated
     *
     * @return the {@link ZigBeeNode}
     */
    public ZigBeeNode getNode() {
        return node;
    }

    /**
     * Gets the time the last discovery was started.
     *
     * @return the {@link Calendar} that the last discovery was started
     */
    public Calendar getLastDiscoveryStarted() {
        return lastDiscoveryStarted;
    }

    /**
     * Gets the time the last discovery was completed.
     *
     * @return the {@link Calendar} that the last discovery was completed
     */
    public Calendar getLastDiscoveryCompleted() {
        return lastDiscoveryCompleted;
    }

    /**
     * Updates the list of tasks to be completed with the mesh update is executed
     *
     * @param tasks a List of {@link NodeDiscoveryTask}s to execute as part of the mesh update
     */
    public void setUpdateMeshTasks(List<NodeDiscoveryTask> tasks) {
        meshUpdateTasks = tasks;
    }
}
