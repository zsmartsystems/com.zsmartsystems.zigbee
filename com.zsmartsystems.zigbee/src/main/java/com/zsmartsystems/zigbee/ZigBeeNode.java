/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.database.ZigBeeEndpointDao;
import com.zsmartsystems.zigbee.database.ZigBeeNodeDao;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ManagementBindRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementBindResponse;
import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.ServerCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;
import com.zsmartsystems.zigbee.zdo.field.SimpleDescriptor;

/**
 * Defines a ZigBee Node. A node is a physical entity on the network and will
 * contain one or more {@link ZigBeeEndpoint}s.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNode implements ZigBeeCommandListener {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNode.class);

    /**
     * The extended {@link IeeeAddress} for the node. The {@link IeeeAddress} is always fixed for a node and may not
     * change. The {@link IeeeAddress} is universally unique.
     */
    private IeeeAddress ieeeAddress;

    /**
     * The 16 bit network address for the node. The network address may change if the device connects to a different
     * parent, or there is a network address conflict.
     */
    private Integer networkAddress;

    /**
     * The {@link NodeDescriptor} for the node.
     */
    private NodeDescriptor nodeDescriptor;

    /**
     * The {@link PowerDescriptor} for the node.
     */
    private PowerDescriptor powerDescriptor;

    /**
     * The time the node information was last updated. This is set from the mesh update class when it the
     * updates neighbor table, routing table etc.
     */
    private Date lastUpdateTime = null;

    /**
     * List of associated devices for the node, specified in a {@link List} {@link Integer}.
     */
    private final Set<Integer> associatedDevices = new HashSet<Integer>();

    /**
     * List of neighbors for the node, specified in a {@link NeighborTable}
     */
    private final Set<NeighborTable> neighbors = new HashSet<NeighborTable>();

    /**
     * List of routes within the node, specified in a {@link RoutingTable}
     */
    private final Set<RoutingTable> routes = new HashSet<RoutingTable>();

    /**
     * List of binding records
     */
    private final Set<BindingTable> bindingTable = new HashSet<BindingTable>();

    /**
     * List of endpoints this node exposes
     */
    private final Map<Integer, ZigBeeEndpoint> endpoints = new ConcurrentHashMap<Integer, ZigBeeEndpoint>();

    /**
     * The endpoint listeners of the ZigBee network. Registered listeners will be
     * notified of additions, deletions and changes to {@link ZigBeeEndpoint}s.
     */
    private List<ZigBeeNetworkEndpointListener> endpointListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeNetworkEndpointListener>());

    /**
     * The {@link ZigBeeNetwork} that manages this node.
     */
    private final ZigBeeNetwork network;

    private ZigBeeNodeState nodeState = ZigBeeNodeState.UNKNOWN;

    public enum ZigBeeNodeState {
        /**
         * Node state is not currently known.
         */
        UNKNOWN,

        /**
         * The node is online and believed to be connected to the network.
         */
        ONLINE,

        /**
         * The node is offline - it has left the network.
         */
        OFFLINE
    }

    /**
     * Constructor
     *
     * @param network the {@link ZigBeeNetwork}
     * @param ieeeAddress the {@link IeeeAddress} of the node
     * @throws {@link IllegalArgumentException} if ieeeAddress is null
     */
    public ZigBeeNode(ZigBeeNetwork network, IeeeAddress ieeeAddress) {
        if (ieeeAddress == null) {
            throw new IllegalArgumentException("IeeeAddress can't be null when creating ZigBeeNode");
        }

        this.network = network;
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * Constructor
     *
     * @param network the {@link ZigBeeNetwork}
     * @param ieeeAddress the {@link IeeeAddress} of the node
     * @param networkAddress the network address of the node
     * @throws {@link IllegalArgumentException} if ieeeAddress is null
     */
    public ZigBeeNode(ZigBeeNetwork network, IeeeAddress ieeeAddress, Integer networkAddress) {
        this(network, ieeeAddress);

        this.networkAddress = networkAddress;
    }

    /**
     * Called when the node is removed from the network.
     */
    public void shutdown() {
    }

    /**
     * Gets the {@link IeeeAddress} of the node
     *
     * @return the {@link IeeeAddress} of the node
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * Sets the 16 bit network address of the node.
     *
     * @param networkAddress the new NWK address for the node
     * @return true if the new NWK address is different from the original value
     */
    public boolean setNetworkAddress(Integer networkAddress) {
        boolean changed = this.networkAddress == null || (!this.networkAddress.equals(networkAddress));
        this.networkAddress = networkAddress;
        return changed;
    }

    /**
     * Gets the 16 bit network address of the node.
     *
     * @return networkAddress the current NWK address for the node
     */
    public Integer getNetworkAddress() {
        return networkAddress;
    }

    /**
     * Sets the {@link NodeDescriptor} for this node.
     *
     * @param nodeDescriptor the new {@link NodeDescriptor}
     */
    public void setNodeDescriptor(NodeDescriptor nodeDescriptor) {
        this.nodeDescriptor = nodeDescriptor;
    }

    /**
     * Gets the {@link NodeDescriptor} for this node.
     *
     * @return nodeDescriptor the new {@link NodeDescriptor}
     */
    public NodeDescriptor getNodeDescriptor() {
        return nodeDescriptor;
    }

    /**
     * Sets the nodes {@link PowerDescriptor}
     *
     * @param powerDescriptor the {@link PowerDescriptor}
     */
    public void setPowerDescriptor(PowerDescriptor powerDescriptor) {
        this.powerDescriptor = powerDescriptor;
    }

    /**
     * Gets the nodes {@link PowerDescriptor}
     *
     * @return the {@link PowerDescriptor} or null if not set
     */
    public PowerDescriptor getPowerDescriptor() {
        return powerDescriptor;
    }

    /**
     * Enables or disables nodes to join to this node.
     * <p>
     * Nodes can only join the network when joining is enabled. It is not advised to leave joining enabled permanently
     * since it allows nodes to join the network without the installer knowing.
     *
     * @param duration sets the duration of the join enable. Setting this to 0 disables joining. Setting to a value
     *            greater than 255 seconds will permanently enable joining.
     */
    public void permitJoin(final int duration) {
        final ManagementPermitJoiningRequest command = new ManagementPermitJoiningRequest();

        if (duration > 255) {
            command.setPermitDuration(255);
        } else {
            command.setPermitDuration(duration);
        }

        command.setTcSignificance(true);
        command.setDestinationAddress(new ZigBeeEndpointAddress(0));
        command.setSourceAddress(new ZigBeeEndpointAddress(0));

        network.sendTransaction(command);
    }

    /**
     * Enables or disables nodes to join to this node.
     * <p>
     * Nodes can only join the network when joining is enabled. It is not advised to leave joining enabled permanently
     * since it allows nodes to join the network without the installer knowing.
     *
     * @param enable if true joining is enabled, otherwise it is disabled
     */
    public void permitJoin(final boolean enable) {
        if (enable) {
            permitJoin(0xFF);
        } else {
            permitJoin(0);
        }
    }

    /**
     * Returns true if the node is a Full Function Device. Returns false if not an FFD or logical type is unknown.
     * <p>
     * A FFD (Full Function Device) is a device that has full levels of functionality.
     * It can be used for sending and receiving data, but it can also route data from other nodes.
     * FFDs are Coordinators and Routers
     *
     * @return true if the device is a Full Function Device. Returns false if not an FFD or logical type is unknown.
     */
    public boolean isFullFuntionDevice() {
        if (nodeDescriptor == null) {
            return false;
        }
        return nodeDescriptor.getMacCapabilities().contains(MacCapabilitiesType.FULL_FUNCTION_DEVICE);
    }

    /**
     * Returns true if the node is a Reduced Function Device. Returns false if not an RFD or logical type is unknown.
     * <p>
     * An RFD (Reduced Function Device) is a device that has a reduced level of functionality.
     * Typically it is an end node which may be typically a sensor or switch. RFDs can only talk to FFDs
     * as they contain no routing functionality. These devices can be very low power devices because they
     * do not need to route other traffic and they can be put into a sleep mode when they are not in use.
     *
     * @return true if the device is a Reduced Function Device
     */
    public boolean isReducedFuntionDevice() {
        if (nodeDescriptor == null) {
            return false;
        }
        return nodeDescriptor.getMacCapabilities().contains(MacCapabilitiesType.REDUCED_FUNCTION_DEVICE);
    }

    public boolean isSecurityCapable() {
        if (nodeDescriptor == null) {
            return false;
        }
        return nodeDescriptor.getMacCapabilities().contains(MacCapabilitiesType.SECURITY_CAPABLE);
    }

    public boolean isPrimaryTrustCenter() {
        if (nodeDescriptor == null) {
            return false;
        }
        return nodeDescriptor.getServerCapabilities().contains(ServerCapabilitiesType.PRIMARY_TRUST_CENTER);
    }

    /**
     * Gets the {@link LogicalType} of the node.
     * <p>
     * Possible types are -:
     * <ul>
     * <li>{@link LogicalType#COORDINATOR}
     * <li>{@link LogicalType#ROUTER}
     * <li>{@link LogicalType#END_DEVICE}
     * <li>{@link LogicalType#UNKNOWN}
     * <ul>
     *
     * @return the {@link LogicalType} of the node
     */
    public LogicalType getLogicalType() {
        if (nodeDescriptor == null) {
            return LogicalType.UNKNOWN;
        }
        return nodeDescriptor.getLogicalType();
    }

    private void setBindingTable(List<BindingTable> bindingTable) {
        synchronized (this.bindingTable) {
            this.bindingTable.clear();
            this.bindingTable.addAll(bindingTable);
            logger.debug("{}: Binding table updated: {}", ieeeAddress, bindingTable);
        }
    }

    /**
     * Gets the current binding table for the device. Note that this doesn't retrieve the table from the device - to do
     * this use the {@link #updateBindingTable()} method.
     *
     * @return {@link Set} of {@link BindingTable} for the device
     */
    public Set<BindingTable> getBindingTable() {
        synchronized (bindingTable) {
            return new HashSet<BindingTable>(bindingTable);
        }
    }

    /**
     * Request an update of the binding table for this node.
     * <p>
     * This method returns a future to a {@link ZigBeeStatus}. Upon success the caller should call
     * {@link #getBindingTable()}
     * <p>
     * Note that some devices will not support this command and the method will return ZigBeeStatus.UNSUPPORTED
     *
     * @return {@link Future} returning a {@link ZigBeeStatus}
     */
    public Future<ZigBeeStatus> updateBindingTable() {
        RunnableFuture<ZigBeeStatus> future = new FutureTask<ZigBeeStatus>(new Callable<ZigBeeStatus>() {
            @Override
            public ZigBeeStatus call() throws Exception {
                int index = 0;
                int tableSize = 0;
                List<BindingTable> bindingTable = new ArrayList<>();

                do {
                    ManagementBindRequest bindingRequest = new ManagementBindRequest();
                    bindingRequest.setDestinationAddress(new ZigBeeEndpointAddress(getNetworkAddress()));
                    bindingRequest.setStartIndex(index);

                    CommandResult result = network.sendTransaction(bindingRequest, new ManagementBindRequest()).get();
                    if (result.isTimeout()) {
                        return ZigBeeStatus.FAILURE;
                    }

                    ManagementBindResponse response = (ManagementBindResponse) result.getResponse();

                    // Some devices do not support reading the binding table
                    if (response.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                        return ZigBeeStatus.UNSUPPORTED;
                    }

                    if (response.getStartIndex() == index) {
                        tableSize = response.getBindingTableEntries();
                        index += response.getBindingTableList().size();
                        bindingTable.addAll(response.getBindingTableList());
                    }
                } while (index < tableSize);

                setBindingTable(bindingTable);
                return ZigBeeStatus.SUCCESS;
            }
        });

        // start the thread to execute it
        new Thread(future, "UpdateBindingTable" + getIeeeAddress()).start();
        return future;
    }

    /**
     * Gets the a {@link Collection} of {@link ZigBeeEndpoint}s this node provides
     *
     * @return {@link Collection} of {@link ZigBeeEndpoint}s supported by the node
     */
    public Collection<ZigBeeEndpoint> getEndpoints() {
        return endpoints.values();
    }

    /**
     * Gets an endpoint given the {@link ZigBeeAddress} address.
     *
     * @param endpointId the endpoint ID to get
     * @return the {@link ZigBeeEndpoint}
     */
    public ZigBeeEndpoint getEndpoint(final int endpointId) {
        synchronized (endpoints) {
            return endpoints.get(endpointId);
        }
    }

    /**
     * Adds an endpoint to the node
     *
     * @param endpoint the {@link ZigBeeEndpoint} to add
     */
    public void addEndpoint(final ZigBeeEndpoint endpoint) {
        synchronized (endpoints) {
            endpoints.put(endpoint.getEndpointId(), endpoint);
        }
        synchronized (this) {
            for (final ZigBeeNetworkEndpointListener listener : endpointListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        listener.deviceAdded(endpoint);
                    }
                });
            }
        }
    }

    /**
     * Updates an endpoint information in the node
     *
     * @param endpoint the {@link ZigBeeEndpoint} to update
     */
    public void updateEndpoint(final ZigBeeEndpoint endpoint) {
        synchronized (endpoints) {
            endpoints.put(endpoint.getEndpointId(), endpoint);
        }
        synchronized (this) {
            for (final ZigBeeNetworkEndpointListener listener : endpointListeners) {
                NotificationService.execute(new Runnable() {
                    @Override
                    public void run() {
                        listener.deviceUpdated(endpoint);
                    }
                });
            }
        }
    }

    /**
     * Removes endpoint by network address.
     *
     * @param endpointId the network address
     */
    public void removeEndpoint(final int endpointId) {
        final ZigBeeEndpoint endpoint;
        synchronized (endpoints) {
            endpoint = endpoints.remove(endpointId);
        }
        synchronized (this) {
            if (endpoint != null) {
                for (final ZigBeeNetworkEndpointListener listener : endpointListeners) {
                    NotificationService.execute(new Runnable() {
                        @Override
                        public void run() {
                            listener.deviceRemoved(endpoint);
                        }
                    });
                }
            }
        }
    }

    /**
     * Adds a {@link ZigBeeNetworkEndpointListener} to the node. The listener will be notified of any addition or
     * removal of endpoints in the node.
     *
     * @param networkDeviceListener the {@link ZigBeeNetworkEndpointListener} to add
     */
    public void addNetworkEndpointListener(final ZigBeeNetworkEndpointListener networkDeviceListener) {
        synchronized (this) {
            final List<ZigBeeNetworkEndpointListener> modifiedListeners = new ArrayList<ZigBeeNetworkEndpointListener>(
                    endpointListeners);
            modifiedListeners.add(networkDeviceListener);
            endpointListeners = Collections.unmodifiableList(modifiedListeners);
        }
    }

    /**
     * Removes the {@link ZigBeeNetworkEndpointListener}
     *
     * @param networkDeviceListener the {@link ZigBeeNetworkEndpointListener} to remove
     */
    public void removeNetworkEndpointListener(final ZigBeeNetworkEndpointListener networkDeviceListener) {
        synchronized (this) {
            final List<ZigBeeNetworkEndpointListener> modifiedListeners = new ArrayList<ZigBeeNetworkEndpointListener>(
                    endpointListeners);
            modifiedListeners.remove(networkDeviceListener);
            endpointListeners = Collections.unmodifiableList(modifiedListeners);
        }
    }

    /**
     * Get the list of neighbors as a {@link NeighborTable}
     *
     * @return current {@link Set} of neighbors as a {@link NeighborTable}
     */
    public Set<NeighborTable> getNeighbors() {
        synchronized (neighbors) {
            return new HashSet<NeighborTable>(neighbors);
        }
    }

    /**
     * Set the list of neighbors as a {@link NeighborTable}.
     * <p>
     * This method checks to see if there have been "significant" changes to the neighbors list so that we can avoid
     * bothering higher layers if nothing noteworthy has changed.
     *
     * @param neighbors list of neighbors as a {@link NeighborTable}. Setting to null will remove all neighbors.
     * @return true if the neighbor table was updated
     */
    public boolean setNeighbors(Set<NeighborTable> neighbors) {
        if (this.neighbors.equals(neighbors)) {
            logger.debug("{}: Neighbor table unchanged", ieeeAddress);
            return false;
        }

        synchronized (this.neighbors) {
            this.neighbors.clear();
            if (neighbors != null) {
                this.neighbors.addAll(neighbors);
            }
        }
        logger.debug("{}: Neighbor table updated: {}", ieeeAddress, neighbors);

        return true;
    }

    /**
     * Get the list of associated devices as a {@link List} of {@link Integer}
     *
     * @return current list of associated devices as a {@link Set} of {@link Integer}
     */
    public Set<Integer> getAssociatedDevices() {
        synchronized (associatedDevices) {
            return new HashSet<Integer>(associatedDevices);
        }
    }

    /**
     * Set the list of associated devices.
     * <p>
     * This method checks to see if there have been "significant" changes to the neighbors list so that we can avoid
     * bothering higher layers if nothing noteworthy has changed.
     *
     * @param associatedDevices list of associated devices
     * @return true if the associated device list was updated
     */
    public boolean setAssociatedDevices(Set<Integer> associatedDevices) {
        if (this.associatedDevices.equals(associatedDevices)) {
            logger.debug("{}: Associated devices table unchanged", ieeeAddress);
            return false;
        }

        synchronized (this.associatedDevices) {
            this.associatedDevices.clear();
            this.associatedDevices.addAll(associatedDevices);
        }
        logger.debug("{}: Associated devices table updated: {}", ieeeAddress, associatedDevices);

        return true;
    }

    /**
     * Get the list of routes as a {@link RoutingTable}
     *
     * @return {@link Set} of routes as a {@link RoutingTable}
     */
    public Collection<RoutingTable> getRoutes() {
        synchronized (routes) {
            return new HashSet<RoutingTable>(routes);
        }
    }

    /**
     * Set the list of routes as a {@link RoutingTable}
     * <p>
     * This method checks to see if there have been "significant" changes to the route list so that we can avoid
     * bothering higher layers if nothing noteworthy has changed.
     *
     * @param routes list of routes as a {@link RoutingTable}. Setting to null will remove all routes.
     * @return true if the route table was updated
     */
    public boolean setRoutes(Set<RoutingTable> routes) {
        logger.debug("{}: Routing table NEW: {}", ieeeAddress, routes);
        logger.debug("{}: Routing table OLD: {}", ieeeAddress, this.routes);
        if (this.routes.equals(routes)) {
            logger.debug("{}: Routing table unchanged", ieeeAddress);
            return false;
        }

        synchronized (this.routes) {
            this.routes.clear();
            if (routes != null) {
                this.routes.addAll(routes);
            }
        }
        logger.debug("{}: Routing table updated: {}", ieeeAddress, routes);

        return true;
    }

    /**
     * Sets the last update time to the current time.
     * This should be set when important node information is updated such as route tables, neighbor information etc
     */
    public void setLastUpdateTime() {
        lastUpdateTime = new Date();
    }

    /**
     * Return the last update time. This is the last time that important node information was updated such as route
     * tables, neighbor information etc
     *
     * @return the last time the node data was updated as a {@link Date}
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        // This gets called for all received commands
        // Check if it's our address
        if (!(command instanceof ZclCommand) || networkAddress == null
                || command.getSourceAddress().getAddress() != networkAddress) {
            return;
        }

        logger.trace("{}: ZigBeeNode.commandReceived({})", ieeeAddress, command);

        ZclCommand zclCommand = (ZclCommand) command;
        ZigBeeEndpointAddress endpointAddress = (ZigBeeEndpointAddress) zclCommand.getSourceAddress();

        ZigBeeEndpoint endpoint = endpoints.get(endpointAddress.getEndpoint());
        if (endpoint == null) {
            logger.debug("{}: Endpoint {} not found for received node command", ieeeAddress,
                    endpointAddress.getEndpoint());
            DefaultResponse response = ZclCluster.createDefaultResponse(zclCommand, ZclStatus.UNSUPPORTED_CLUSTER);
            if (response != null) {
                sendTransaction(response);
            }
            return;
        }

        endpoint.commandReceived(zclCommand);
    }

    /**
     * Checks if basic device discovery is complete. This ensures that we have received the {@link NodeDescriptor} and
     * the {@link SimpleDescriptor} so that we know the endpoints.
     *
     * @return true if basic device information is known
     */
    public boolean isDiscovered() {
        return nodeDescriptor != null && nodeDescriptor.getLogicalType() != LogicalType.UNKNOWN
                && endpoints.size() != 0;
    }

    /**
     * Updates the node. This will copy data from another node into this node. Updated elements are checked for equality
     * and the method will only return true if the node data has been changed.
     *
     * @param node the {@link ZigBeeNode} that contains the newer node data.
     * @return true if there were changes made as a result of the update
     */
    protected boolean updateNode(ZigBeeNode node) {
        if (!node.getIeeeAddress().equals(ieeeAddress)) {
            logger.debug("{}: Ieee address inconsistent during update <>{}", ieeeAddress, node.getIeeeAddress());
            return false;
        }

        boolean updated = false;

        if (node.getNetworkAddress() != null
                && (networkAddress == null || !networkAddress.equals(node.getNetworkAddress()))) {
            logger.debug("{}: Network address updated from {} to {}", ieeeAddress, networkAddress,
                    node.getNetworkAddress());
            updated = true;
            networkAddress = node.getNetworkAddress();
        }

        if (node.getNodeDescriptor() != null
                && (nodeDescriptor == null || !nodeDescriptor.equals(node.getNodeDescriptor()))) {
            logger.debug("{}: Node descriptor updated", ieeeAddress);
            updated = true;
            nodeDescriptor = node.getNodeDescriptor();
        }

        if (node.getPowerDescriptor() != null
                && (powerDescriptor == null || !powerDescriptor.equals(node.getPowerDescriptor()))) {
            logger.debug("{}: Power descriptor updated", ieeeAddress);
            updated = true;
            powerDescriptor = node.getPowerDescriptor();
        }

        synchronized (associatedDevices) {
            if (!associatedDevices.equals(node.getAssociatedDevices())) {
                logger.debug("{}: Associated devices updated", ieeeAddress);
                updated = true;
                associatedDevices.clear();
                associatedDevices.addAll(node.getAssociatedDevices());
            }
        }

        synchronized (bindingTable) {
            if (!bindingTable.equals(node.getBindingTable())) {
                logger.debug("{}: Binding table updated", ieeeAddress);
                updated = true;
                bindingTable.clear();
                bindingTable.addAll(node.getBindingTable());
            }
        }

        synchronized (neighbors) {
            if (!neighbors.equals(node.getNeighbors())) {
                logger.debug("{}: Neighbors updated", ieeeAddress);
                updated = true;
                neighbors.clear();
                neighbors.addAll(node.getNeighbors());
            }
        }

        synchronized (routes) {
            if (!routes.equals(node.getRoutes())) {
                logger.debug("{}: Routes updated", ieeeAddress);
                updated = true;
                routes.clear();
                routes.addAll(node.getRoutes());
            }
        }

        // Update endpoints.
        // We need to remember that with some discovery systems, clusters may be added over multiple updates.
        // This requires that we work through the endpoints and update clusters as required.
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            ZigBeeEndpoint currentEndpoint = endpoints.get(endpoint.getEndpointId());
            if (currentEndpoint == null) {
                endpoints.put(endpoint.getEndpointId(), endpoint);
                logger.debug("{}: Endpoint {} added", ieeeAddress, endpoint.getEndpointId());
                updated = true;
            } else if (currentEndpoint.updateEndpoint(endpoint)) {
                logger.debug("{}: Endpoint {} updated", ieeeAddress, endpoint.getEndpointId());
                updated = true;
            }
        }

        return updated;
    }

    /**
     * Gets a {@link ZigBeeNodeDao} representing the node
     *
     * @return the {@link ZigBeeNodeDao}
     */
    public ZigBeeNodeDao getDao() {
        ZigBeeNodeDao dao = new ZigBeeNodeDao();

        dao.setIeeeAddress(ieeeAddress);
        dao.setNetworkAddress(getNetworkAddress());
        dao.setNodeDescriptor(nodeDescriptor);
        dao.setPowerDescriptor(powerDescriptor);
        dao.setBindingTable(bindingTable);

        List<ZigBeeEndpointDao> endpointDaoList = new ArrayList<ZigBeeEndpointDao>();
        for (ZigBeeEndpoint endpoint : endpoints.values()) {
            endpointDaoList.add(endpoint.getDao());
        }
        dao.setEndpoints(endpointDaoList);

        return dao;
    }

    public void setDao(ZigBeeNodeDao dao) {
        ieeeAddress = dao.getIeeeAddress();
        setNetworkAddress(dao.getNetworkAddress());
        setNodeDescriptor(dao.getNodeDescriptor());
        setPowerDescriptor(dao.getPowerDescriptor());
        if (dao.getBindingTable() != null) {
            bindingTable.addAll(dao.getBindingTable());
        }

        for (ZigBeeEndpointDao endpointDao : dao.getEndpoints()) {
            ZigBeeEndpoint endpoint = new ZigBeeEndpoint(this, endpointDao.getEndpointId());
            endpoint.setDao(endpointDao);
            endpoints.put(endpoint.getEndpointId(), endpoint);
        }
    }

    /**
     * Sends ZigBee command without waiting for response.
     *
     * @param command the {@link ZigBeeCommand} to send
     */
    public void sendTransaction(ZigBeeCommand command) {
        network.sendTransaction(command);
    }

    /**
     * Sends {@link ZigBeeCommand} command and uses the {@link ZigBeeTransactionMatcher} to match the response.
     *
     * @param command the {@link ZigBeeCommand} to send
     * @param responseMatcher the {@link ZigBeeTransactionMatcher} used to match the response to the request
     * @return the {@link CommandResult} future.
     */
    public Future<CommandResult> sendTransaction(ZigBeeCommand command, ZigBeeTransactionMatcher responseMatcher) {
        return network.sendTransaction(command, responseMatcher);
    }

    /**
     * Set the node {@link ZigBeeNodeState}
     *
     * @param state the new {@link ZigBeeNodeState} for this node
     * @return true if the node state changed
     */
    public boolean setNodeState(ZigBeeNodeState state) {
        if (nodeState.equals(state)) {
            return false;
        }
        logger.debug("{}: Node state updated from {} to {}", ieeeAddress, nodeState, state);
        nodeState = state;
        return true;
    }

    /**
     * Gets the current {@link ZigBeeNodeState} for the node
     *
     * @return the current {@link ZigBeeNodeState} for the node
     */
    public ZigBeeNodeState getNodeState() {
        return nodeState;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(100);

        builder.append("ZigBeeNode [state=");
        builder.append(nodeState);
        builder.append(", IEEE=");
        builder.append(ieeeAddress);
        if (networkAddress == null) {
            builder.append("NWK=----");
        } else {
            builder.append(String.format(", NWK=%04X", networkAddress));
        }

        if (nodeDescriptor != null) {
            builder.append(", Type=");
            builder.append(nodeDescriptor.getLogicalType());
        }

        builder.append(']');

        return builder.toString();
    }

}
