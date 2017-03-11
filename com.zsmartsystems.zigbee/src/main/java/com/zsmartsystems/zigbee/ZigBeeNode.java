package com.zsmartsystems.zigbee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zsmartsystems.zigbee.zdo.command.ManagementPermitJoiningRequest;
import com.zsmartsystems.zigbee.zdo.descriptors.NeighborTable;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.LogicalType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.MacCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.descriptors.NodeDescriptor.ServerCapabilitiesType;
import com.zsmartsystems.zigbee.zdo.descriptors.PowerDescriptor;
import com.zsmartsystems.zigbee.zdo.descriptors.RoutingTable;

/**
 * Defines a ZigBee Node. A node is a physical entity on the network and will
 * contain one or more {@link ZigBeeDevice}s.
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
     * The {@link NodeDescriptor} for the device
     */
    private NodeDescriptor nodeDescriptor;

    /**
     * The {@link PowerDescriptor} for the device
     */
    private PowerDescriptor powerDescriptor;

    /**
     * The time the node information was last updated. This is set from the mesh update class when it the
     * updates neighbor table, routing table etc.
     */
    private Date lastUpdateTime = null;

    /**
     * List of neighbors for the device, specified in a {@link NeighborTable}
     */
    private final List<NeighborTable> neighbors = new ArrayList<NeighborTable>();

    /**
     * List of routes within the device, specified in a {@link RoutingTable}
     */
    private final List<RoutingTable> routes = new ArrayList<RoutingTable>();

    /**
     * The network manager that manages this device
     */
    private final ZigBeeNetworkManager networkManager;

    public ZigBeeNode(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Sets the {@link IeeeAddress} of the device
     *
     * param ieeeAddress the {@link IeeeAddress} of the device
     */
    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    /**
     * Gets the {@link IeeeAddress} of the device
     *
     * @return the {@link IeeeAddress} of the device
     */
    public IeeeAddress getIeeeAddress() {
        return ieeeAddress;
    }

    /**
     * Sets the 16 bit network address of the device.
     *
     * @param networkAddress
     */
    public void setNetworkAddress(Integer networkAddress) {
        this.networkAddress = networkAddress;
    }

    /**
     * Gets the 16 bit network address of the device.
     *
     * @return networkAddress
     */
    public Integer getNetworkAddress() {
        return networkAddress;
    }

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
     * Enables or disables devices to join to this node.
     * <p>
     * Devices can only join the network when joining is enabled. It is not advised to leave joining enabled permanently
     * since it allows devices to join the network without the installer knowing.
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
        command.setDestinationAddress(
                new ZigBeeDeviceAddress(ZigBeeBroadcastDestination.BROADCAST_ROUTERS_AND_COORD.getKey()));
        command.setSourceAddress(new ZigBeeDeviceAddress(0));

        try {
            networkManager.sendCommand(command);
        } catch (final ZigBeeException e) {
            throw new ZigBeeApiException("Error sending permit join command.", e);
        }
    }

    /**
     * Enables or disables devices to join to this node.
     * <p>
     * Devices can only join the network when joining is enabled. It is not advised to leave joining enabled permanently
     * since it allows devices to join the network without the installer knowing.
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
     * Returns true if the device is a Full Function Device. Returns false if not an FFD or logical type is unknown.
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
     * Returns true if the device is a Reduced Function Device. Returns false if not an RFD or logical type is unknown.
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
     * Gets the {@link LogicalType} of the device.
     * <p>
     * Possible types are -:
     * <ul>
     * <li>{@link LogicalType#COORDINATOR}
     * <li>{@link LogicalType#ROUTER}
     * <li>{@link LogicalType#END_DEVICE}
     * <ul>
     *
     * @return the {@link LogicalType} of the device
     */
    public LogicalType getLogicalType() {
        return nodeDescriptor.getLogicalType();
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
            return "IEEE=" + ieeeAddress + ", NWK=" + String.format("%04X", networkAddress);
        }
        return "IEEE=" + ieeeAddress + ", NWK=" + String.format("%04X", networkAddress) + ", Type="
                + nodeDescriptor.getLogicalType();
    }
}
