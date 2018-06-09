/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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

import com.zsmartsystems.zigbee.dao.ZigBeeEndpointDao;
import com.zsmartsystems.zigbee.dao.ZigBeeNodeDao;
import com.zsmartsystems.zigbee.internal.NotificationService;
import com.zsmartsystems.zigbee.internal.ZigBeeNodeServiceDiscoverer;
import com.zsmartsystems.zigbee.internal.ZigBeeNodeServiceDiscoverer.NodeDiscoveryTask;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
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
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor.CurrentPowerModeType;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;

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
     * The extended {@link IeeeAddress} for the node
     */
    private IeeeAddress ieeeAddress;

    /**
     * The 16 bit network address for the node
     */
    private Integer networkAddress;

    /**
     * The {@link NodeDescriptor} for the node
     */
    private NodeDescriptor nodeDescriptor = new NodeDescriptor();

    /**
     * The {@link PowerDescriptor} for the node
     */
    private PowerDescriptor powerDescriptor = new PowerDescriptor();

    /**
     * The time the node information was last updated. This is set from the mesh update class when it the
     * updates neighbor table, routing table etc.
     */
    private Date lastUpdateTime = null;

    /**
     * List of associated devices for the node, specified in a {@link List} {@link Integer}
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
     * The node service discoverer that is responsible for the discovery of services, and periodic update or routes and
     * Neighbors
     */
    private final ZigBeeNodeServiceDiscoverer serviceDiscoverer;

    /**
     * The endpoint listeners of the ZigBee network. Registered listeners will be
     * notified of additions, deletions and changes to {@link ZigBeeEndpoint}s.
     */
    private List<ZigBeeNetworkEndpointListener> endpointListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeNetworkEndpointListener>());

    /**
     * The {@link ZigBeeNetworkManager} that manages this node
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * Constructor
     *
     * @param networkManager the {@link ZigBeeNetworkManager}
     * @param ieeeAddress the {@link IeeeAddress} of the node
     * @throws {@link IllegalArgumentException} if ieeeAddress is null
     */
    public ZigBeeNode(ZigBeeNetworkManager networkManager, IeeeAddress ieeeAddress) {
        if (ieeeAddress == null) {
            throw new IllegalArgumentException("IeeeAddress can't be null when creating ZigBeeNode");
        }

        this.networkManager = networkManager;
        this.ieeeAddress = ieeeAddress;
        this.serviceDiscoverer = new ZigBeeNodeServiceDiscoverer(networkManager, this);

        networkManager.addCommandListener(this);
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
     * @param networkAddress
     */
    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
    }

    /**
     * Gets the 16 bit network address of the node.
     *
     * @return networkAddress
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

        networkManager.sendCommand(command);
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
     * <ul>
     *
     * @return the {@link LogicalType} of the node
     */
    public LogicalType getLogicalType() {
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
     * This method returns a future to a boolean. Upon success the caller should call {@link #getBindingTable()}
     *
     * @return {@link Future} returning a {@link Boolean}
     */
    public Future<Boolean> updateBindingTable() {
        RunnableFuture<Boolean> future = new FutureTask<Boolean>(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                int index = 0;
                int tableSize = 0;
                List<BindingTable> bindingTable = new ArrayList<BindingTable>();

                do {
                    ManagementBindRequest bindingRequest = new ManagementBindRequest();
                    bindingRequest.setDestinationAddress(new ZigBeeEndpointAddress(networkAddress));
                    bindingRequest.setStartIndex(index);

                    CommandResult result = networkManager.unicast(bindingRequest, new ManagementBindRequest()).get();
                    if (result.isError()) {
                        return false;
                    }

                    ManagementBindResponse response = (ManagementBindResponse) result.getResponse();
                    if (response.getStartIndex() == index) {
                        tableSize = response.getBindingTableEntries();
                        index += response.getBindingTableList().size();
                        bindingTable.addAll(response.getBindingTableList());
                    }
                } while (index < tableSize);

                setBindingTable(bindingTable);
                return true;
            }
        });

        // start the thread to execute it
        new Thread(future).start();
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

    public void addNetworkEndpointListener(final ZigBeeNetworkEndpointListener networkDeviceListener) {
        synchronized (this) {
            final List<ZigBeeNetworkEndpointListener> modifiedListeners = new ArrayList<ZigBeeNetworkEndpointListener>(
                    endpointListeners);
            modifiedListeners.add(networkDeviceListener);
            endpointListeners = Collections.unmodifiableList(modifiedListeners);
        }
    }

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
     * @param neighbors list of neighbors as a {@link NeighborTable}. Setting to null will remove all neighbors.
     * @return true if the neighbor table was updated
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
        if (command.getSourceAddress().getAddress() != networkAddress) {
            return;
        }

        if (!(command instanceof ZclCommand)) {
            return;
        }

        ZclCommand zclCommand = (ZclCommand) command;
        ZigBeeEndpointAddress endpointAddress = (ZigBeeEndpointAddress) zclCommand.getSourceAddress();

        ZigBeeEndpoint endpoint = endpoints.get(endpointAddress.getEndpoint());
        if (endpoint == null) {
            return;
        }

        endpoint.commandReceived(zclCommand);
    }

    /**
     * Starts service discovery for the node.
     */
    public void startDiscovery() {
        Set<NodeDiscoveryTask> tasks = new HashSet<NodeDiscoveryTask>();

        // Always request the network address - in case it's changed
        tasks.add(NodeDiscoveryTask.NWK_ADDRESS);

        if (nodeDescriptor.getLogicalType() == LogicalType.UNKNOWN) {
            tasks.add(NodeDiscoveryTask.NODE_DESCRIPTOR);
        }

        if (powerDescriptor.getCurrentPowerMode() == CurrentPowerModeType.UNKNOWN) {
            tasks.add(NodeDiscoveryTask.POWER_DESCRIPTOR);
        }

        if (endpoints.size() == 0 && networkAddress != 0) {
            tasks.add(NodeDiscoveryTask.ACTIVE_ENDPOINTS);
        }

        tasks.add(NodeDiscoveryTask.NEIGHBORS);

        serviceDiscoverer.startDiscovery(tasks);
    }

    /**
     * Starts service discovery for the node in order to update the mesh
     */
    public void updateMesh() {
        Set<NodeDiscoveryTask> tasks = new HashSet<NodeDiscoveryTask>();

        tasks.add(NodeDiscoveryTask.NEIGHBORS);

        if (nodeDescriptor.getLogicalType() != LogicalType.END_DEVICE) {
            tasks.add(NodeDiscoveryTask.ROUTES);
        }

        serviceDiscoverer.startDiscovery(tasks);
    }

    /**
     * Checks if basic device discovery is complete.
     *
     * @return true if basic device information is known
     */
    public boolean isDiscovered() {
        return nodeDescriptor.getLogicalType() != LogicalType.UNKNOWN && endpoints.size() != 0;
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
            return false;
        }

        boolean updated = false;

        if (!networkAddress.equals(node.getNetworkAddress())) {
            updated = true;
            networkAddress = node.getNetworkAddress();
        }

        if (!nodeDescriptor.equals(node.getNodeDescriptor())) {
            updated = true;
            nodeDescriptor = node.getNodeDescriptor();
        }

        if (!powerDescriptor.equals(node.getPowerDescriptor())) {
            updated = true;
            powerDescriptor = node.getPowerDescriptor();
        }

        synchronized (associatedDevices) {
            if (!associatedDevices.equals(node.getAssociatedDevices())) {
                updated = true;
                associatedDevices.clear();
                associatedDevices.addAll(node.getAssociatedDevices());
            }
        }

        synchronized (bindingTable) {
            if (!bindingTable.equals(node.getBindingTable())) {
                updated = true;
                bindingTable.clear();
                bindingTable.addAll(node.getBindingTable());
            }
        }

        synchronized (neighbors) {
            if (!neighbors.equals(node.getNeighbors())) {
                updated = true;
                neighbors.clear();
                neighbors.addAll(node.getNeighbors());
            }
        }

        synchronized (routes) {
            if (!routes.equals(node.getRoutes())) {
                updated = true;
                routes.clear();
                routes.addAll(node.getRoutes());
            }
        }

        // TODO: How to deal with endpoints

        return updated;
    }

    /**
     * Gets a {@link ZigBeeNodeDao} representing the node
     *
     * @return the {@link ZigBeeNodeDao}
     */
    public ZigBeeNodeDao getDao() {
        ZigBeeNodeDao dao = new ZigBeeNodeDao();

        dao.setIeeeAddress(ieeeAddress.toString());
        dao.setNetworkAddress(networkAddress);
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
        ieeeAddress = new IeeeAddress(dao.getIeeeAddress());
        networkAddress = dao.getNetworkAddress();
        nodeDescriptor = dao.getNodeDescriptor();
        powerDescriptor = dao.getPowerDescriptor();
        if (dao.getBindingTable() != null) {
            bindingTable.addAll(dao.getBindingTable());
        }

        for (ZigBeeEndpointDao endpointDao : dao.getEndpoints()) {
            ZigBeeEndpoint endpoint = new ZigBeeEndpoint(networkManager, this, endpointDao.getEndpointId());
            endpoint.setDao(endpointDao);
            endpoints.put(endpoint.getEndpointId(), endpoint);
        }
    }

    @Override
    public String toString() {
        if (nodeDescriptor == null) {
            return "ZigBeeNode [IEEE=" + ieeeAddress + ", NWK=" + String.format("%04X", networkAddress) + "]";
        }

        return "ZigBeeNode [IEEE=" + ieeeAddress + ", NWK=" + String.format("%04X", networkAddress) + ", Type="
                + nodeDescriptor.getLogicalType() + "]";
    }

}
