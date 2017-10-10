/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.field.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.ServerCapabilitiesType;

/**
 * Defines a ZigBee Node. A node is a physical entity on the network and will
 * contain one or more {@link ZigBeeEndpoint}s.
 * <p>
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNode {
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
    private NodeDescriptor nodeDescriptor;

    /**
     * The {@link PowerDescriptor} for the node
     */
    private PowerDescriptor powerDescriptor;

    /**
     * A flag indicating if this node is configured to allow joining
     */
    private boolean joiningEnabled = false;

    /**
     * The time the node information was last updated. This is set from the mesh update class when it the
     * updates neighbor table, routing table etc.
     */
    private Date lastUpdateTime = null;

    /**
     * List of associated devices for the node, specified in a {@link List} {@link Integer}
     */
    private final List<Integer> associatedDevices = new ArrayList<Integer>();

    /**
     * List of neighbors for the node, specified in a {@link NeighborTable}
     */
    private final List<NeighborTable> neighbors = new ArrayList<NeighborTable>();

    /**
     * List of routes within the node, specified in a {@link RoutingTable}
     */
    private final List<RoutingTable> routes = new ArrayList<RoutingTable>();

    /**
     * List of endpoints this node exposes
     */
    private final Map<Integer, ZigBeeEndpoint> endpoints = new HashMap<Integer, ZigBeeEndpoint>();

    /**
     * The endpoint listeners of the ZigBee network. Registered listeners will be
     * notified of additions, deletions and changes to {@link ZigBeeEndpoint}s.
     */
    private List<ZigBeeNetworkEndpointListener> endpointListeners = Collections
            .unmodifiableList(new ArrayList<ZigBeeNetworkEndpointListener>());

    /**
     * The network manager that manages this node
     */
    private final ZigBeeNetworkManager networkManager;

    public ZigBeeNode(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Sets the {@link IeeeAddress} of the node
     *
     * param ieeeAddress the {@link IeeeAddress} of the node
     */
    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
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

    public NodeDescriptor getNodeDescriptor() {
        return nodeDescriptor;
    }

    public void setPowerDescriptor(PowerDescriptor powerDescriptor) {
        this.powerDescriptor = powerDescriptor;
    }

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

        try {
            networkManager.sendCommand(command);
        } catch (final ZigBeeException e) {
            throw new ZigBeeApiException("Error sending permit join command.", e);
        }
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


    /**
     * Request an update of the binding table for this node
     * TODO: This needs to handle the response and further requests if required to complete the table
     */
    public void updateBindingTable() {
        ManagementBindRequest bindingRequest = new ManagementBindRequest();
        bindingRequest.setDestinationAddress(new ZigBeeDeviceAddress(networkAddress));
        bindingRequest.setStartIndex(0);
        networkManager.unicast(bindingRequest, new ZdoResponseMatcher());
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
            endpoints.put(endpoint.getEndpoint(), endpoint);
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
            endpoints.put(endpoint.getEndpoint(), endpoint);
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
     * @param networkAddress the network address
     */
    public void removeDevice(final ZigBeeAddress networkAddress) {
        final ZigBeeEndpoint endpoint;
        synchronized (endpoints) {
            endpoint = endpoints.remove(networkAddress);
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
     * Return a {@link List} of {@link ZigBeeEndpoint}s known on this node
     *
     * @return {@link List} of {@link ZigBeeEndpoint}s
     */
    public List<ZigBeeEndpoint> getDevices() {
        synchronized (endpoints) {
            return new ArrayList<ZigBeeEndpoint>(endpoints.values());
        }
    }

    /**
     * Get the list of neighbors as a {@link NeighborTable}
     *
     * @return current list of neighbors as a {@link NeighborTable}
     */
    public List<NeighborTable> getNeighbors() {
        return neighbors;
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
    public boolean setNeighbors(List<NeighborTable> neighbors) {
        boolean changes = false;
        synchronized (this.neighbors) {
            if (neighbors == null) {
                if (this.neighbors.size() != 0) {
                    this.neighbors.clear();
                    changes = true;
                }
            } else if (this.neighbors.size() != neighbors.size()) {
                changes = true;
            } else {
                for (NeighborTable neighbor : this.neighbors) {
                    if (!neighbors.contains(neighbor)) {
                        changes = true;
                        break;
                    }
                }
            }

            // Update the list if needed
            if (changes) {
                this.neighbors.clear();
                if (neighbors != null) {
                    this.neighbors.addAll(neighbors);
                }
            }
        }

        return changes;
    }

    /**
     * Get the list of associated devices as a {@link List} of {@link Integer}
     *
     * @return current list of associated devices as a {@link List} of {@link Integer}
     */
    public List<Integer> getAssociatedDevices() {
        return associatedDevices;
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
    public boolean setAssociatedDevices(List<Integer> associatedDevices) {
        boolean changes = false;
        synchronized (this.associatedDevices) {
            if (associatedDevices == null) {
                if (this.associatedDevices.size() != 0) {
                    this.associatedDevices.clear();
                    changes = true;
                }
            } else if (this.associatedDevices.size() != associatedDevices.size()) {
                changes = true;
            } else {
                for (Integer neighbor : this.associatedDevices) {
                    if (!associatedDevices.contains(neighbor)) {
                        changes = true;
                        break;
                    }
                }
            }

            // Update the list if needed
            if (changes) {
                this.associatedDevices.clear();
                if (associatedDevices != null) {
                    this.associatedDevices.addAll(associatedDevices);
                }
            }
        }

        return changes;
    }

    /**
     * Get the list of routes as a {@link RoutingTable}
     *
     * @return list of routes as a {@link RoutingTable}
     */
    public List<RoutingTable> getRoutes() {
        return routes;
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
    public boolean setRoutes(List<RoutingTable> routes) {
        boolean changes = false;
        synchronized (this.routes) {
            if (routes == null) {
                if (this.routes.size() != 0) {
                    this.routes.clear();
                    changes = true;
                }
            } else if (this.routes.size() != routes.size()) {
                changes = true;
            } else {
                for (RoutingTable route : this.routes) {
                    if (!routes.contains(route)) {
                        changes = true;
                        break;
                    }
                }
            }

            // Update the list if needed
            if (changes) {
                this.routes.clear();
                if (routes != null) {
                    this.routes.addAll(routes);
                }
            }
        }

        return changes;
    }

    /**
     * Sets the joining status of this node. Note that this is not (currently) linked to the join command that is sent -
     * it is a status point that is set from the node beacons.
     *
     * @return true if the joining status changed.
     */
    public boolean setJoining(boolean joiningEnabled) {
        boolean changed;

        changed = this.joiningEnabled != joiningEnabled;
        this.joiningEnabled = joiningEnabled;
        return changed;
    }

    /**
     * Gets the joining status of this node. Note that this is not (currently) linked to the join command that is sent -
     * it is a status point that is set from the node beacons.
     *
     * @return true if the node is currently configured to allow joining
     */
    public boolean isJoiningEnabled() {
        return joiningEnabled;
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
    public String toString() {
        if (nodeDescriptor == null) {
            return "ZigBeeNode [IEEE=" + ieeeAddress + ", NWK=" + String.format("%04X", networkAddress) + "]";
        }

        return "ZigBeeNode [IEEE=" + ieeeAddress + ", NWK=" + String.format("%04X", networkAddress) + ", Type="
                + nodeDescriptor.getLogicalType() + "]";
    }
}
