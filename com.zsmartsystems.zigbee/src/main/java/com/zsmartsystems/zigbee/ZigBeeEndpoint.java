/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclServer;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * ZigBee endpoint. An endpoint is a virtual entity contained within a {@link ZigBeeNode}. The {@link ZigBeeNode} is the
 * physical device and can contain up to 254 endpoints.
 *
 * @author Chris Jackson
 */
public class ZigBeeEndpoint implements ZigBeeCommandListener {
    /**
     * The {@link Logger}.
     */
    private final static Logger logger = LoggerFactory.getLogger(ZigBeeEndpoint.class);

    /**
     * The {@link ZigBeeNetworkManager} that manages this node
     */
    private final ZigBeeNetworkManager networkManager;

    /**
     * Link to the parent {@link ZigBeeNode} to which this device belongs
     */
    private final ZigBeeNode node;

    /**
     * The endpoint number for this endpoint. Applications shall only use endpoints 1-254. Endpoints 241-254 shall be
     * used only with the approval of the ZigBee Alliance.
     */
    private int endpointId;

    /**
     * The profile ID.
     */
    private int profileId;

    /**
     * The device ID. Specifies the device description supported on this endpoint. Device description identifiers shall
     * be obtained from the ZigBee Alliance.
     */
    private int deviceId;

    /**
     * The device version.
     */
    private int deviceVersion;

    /**
     * List of input clusters supported by the endpoint
     */
    private final Map<Integer, ZclCluster> inputClusters = new ConcurrentHashMap<Integer, ZclCluster>();

    /**
     * List of output clusters supported by the endpoint
     */
    private final Map<Integer, ZclCluster> outputClusters = new ConcurrentHashMap<Integer, ZclCluster>();

    /**
     * Map of {@link ZigBeeServer}s that are available to this endpoint. Servers are added
     * with the {@link #addServer(ZigBeeServer server)} method and can be retrieved with the
     * {@link #getServer(int clusterId)} method.
     */
    private final Map<Integer, ZclServer> servers = new ConcurrentHashMap<Integer, ZclServer>();

    /**
     * Constructor
     *
     * @param networkManager the {@link ZigBeeNetworkManager} to which the device belongs
     * @param node the parent {@link ZigBeeNode}
     * @param endpoint the endpoint number within the {@link ZigBeeNode}
     */
    public ZigBeeEndpoint(ZigBeeNetworkManager networkManager, ZigBeeNode node, int endpoint) {
        this.networkManager = networkManager;
        this.node = node;
        this.endpointId = endpoint;
        networkManager.addCommandListener(this);
    }

    @Override
    protected void finalize() {
        networkManager.removeCommandListener(this);
    }

    /**
     * Gets the device ID.
     *
     * @return the device ID
     */
    public int getDeviceId() {
        return deviceId;
    }

    /**
     * Sets the device ID.
     *
     * @param deviceId the device ID.
     */
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * Gets device version.
     *
     * @return the device version
     */
    public int getDeviceVersion() {
        return deviceVersion;
    }

    /**
     * Sets device version.
     *
     * @param deviceVersion the device version
     */
    public void setDeviceVersion(int deviceVersion) {
        this.deviceVersion = deviceVersion;
    }

    /**
     * Gets end point.
     *
     * @return the end point
     */
    public int getEndpointId() {
        return endpointId;
    }

    /**
     * Gets input cluster IDs. This lists the IDs of all clusters the device
     * supports as a server.
     *
     * @return the {@link Collection} of input cluster IDs
     */
    public Collection<Integer> getInputClusterIds() {
        return inputClusters.keySet();
    }

    /**
     * Gets an input cluster
     *
     * @deprecated Use {@link #getInputCluster}
     * @param clusterId
     *            the cluster number
     * @return the cluster or null if cluster is not found
     */
    @Deprecated
    public ZclCluster getCluster(int clusterId) {
        return getInputCluster(clusterId);
    }

    /**
     * Gets an input cluster
     *
     * @param clusterId the cluster number
     * @return the {@link ZclCluster} or null if cluster is not found
     */
    public ZclCluster getInputCluster(int clusterId) {
        return inputClusters.get(clusterId);
    }

    /**
     * Gets an output cluster
     *
     * @param clusterId the cluster number
     * @return the {@link ZclCluster} or null if cluster is not found
     */
    public ZclCluster getOutputCluster(int clusterId) {
        return outputClusters.get(clusterId);
    }

    /**
     * Sets input cluster IDs.
     *
     * @param inputClusterIds
     *            the input cluster IDs
     */
    public void setInputClusterIds(List<Integer> inputClusterIds) {
        this.inputClusters.clear();

        logger.debug("{}: Setting input clusters {}", getEndpointAddress(), inputClusterIds);

        updateClusters(inputClusters, inputClusterIds, true);
    }

    /**
     * Gets the {@link IeeeAddress} for this endpoint from it's parent {@link ZigBeeNode}
     *
     * @return the node {@link IeeeAddress}
     */
    public IeeeAddress getIeeeAddress() {
        return node.getIeeeAddress();
    }

    /**
     * Gets the device address
     *
     * @return the {@link ZigBeeEndpointAddress}
     */
    public ZigBeeEndpointAddress getEndpointAddress() {
        return new ZigBeeEndpointAddress(node.getNetworkAddress(), endpointId);
    }

    /**
     * Gets output cluster IDs. This provides the IDs of all clusters the device
     * supports as a client.
     *
     * @return the {@link Collection} of output cluster IDs
     */
    public Collection<Integer> getOutputClusterIds() {
        return outputClusters.keySet();
    }

    /**
     * Sets output cluster IDs.
     *
     * @param outputClusterIds
     *            the output cluster IDs
     */
    public void setOutputClusterIds(List<Integer> outputClusterIds) {
        this.outputClusters.clear();

        logger.debug("{}: Setting output clusters {}", getEndpointAddress(), outputClusterIds);

        updateClusters(outputClusters, outputClusterIds, false);
    }

    /**
     * Gets a cluster from the input or output cluster list depending on the command {@link ZclCommandDirection} for a
     * received command.
     * <p>
     * If commandDirection is {@link ZclCommandDirection#CLIENT_TO_SERVER} then the cluster comes from the output
     * cluster list.
     * If commandDirection is {@link ZclCommandDirection#SERVER_TO_CLIENT} then the cluster comes from the input
     * cluster list.
     *
     * @param clusterId the cluster ID to get
     * @param direction the {@link ZclCommandDirection}
     * @return the {@link ZclCluster} or null if the cluster is not known
     */
    private ZclCluster getReceiveCluster(int clusterId, ZclCommandDirection direction) {
        if (direction == ZclCommandDirection.CLIENT_TO_SERVER) {
            return getOutputCluster(clusterId);
        } else {
            return getInputCluster(clusterId);
        }
    }

    private void updateClusters(Map<Integer, ZclCluster> clusters, List<Integer> newList, boolean isInput) {
        // Get a list any clusters that are no longer in the list
        List<Integer> removeIds = new ArrayList<Integer>();
        for (ZclCluster cluster : clusters.values()) {
            if (newList.contains(cluster.getClusterId())) {
                // The existing cluster is in the new list, so no need to remove it
                continue;
            }

            removeIds.add(cluster.getClusterId());
        }

        // Remove clusters no longer in use
        for (int id : removeIds) {
            logger.debug("{}: Removing cluster {}", getEndpointAddress(), id);
            clusters.remove(id);
        }

        // Add any missing clusters into the list
        for (int id : newList) {
            if (!clusters.containsKey(id)) {
                // Get the cluster type
                ZclClusterType clusterType = ZclClusterType.getValueById(id);
                ZclCluster clusterClass = null;
                if (clusterType == null) {
                    // Unsupported cluster
                    logger.debug("{}: Unsupported cluster {}", getEndpointAddress(), id);
                    continue;
                }

                // Create a cluster class
                Constructor<? extends ZclCluster> constructor;
                try {
                    constructor = clusterType.getClusterClass().getConstructor(ZigBeeNetworkManager.class,
                            ZigBeeEndpoint.class);
                    clusterClass = constructor.newInstance(networkManager, this);
                } catch (Exception e) {
                    logger.debug("{}: Error instantiating cluster {}", getEndpointAddress(), clusterType);
                }
                if (isInput) {
                    logger.debug("{}: Setting cluster {} as server", getEndpointAddress(), clusterType);
                    clusterClass.setServer();
                } else {
                    logger.debug("{}: Setting cluster {} as client", getEndpointAddress(), clusterType);
                    clusterClass.setClient();
                }

                // Add to our list of clusters
                clusters.put(id, clusterClass);
            }
        }
    }

    /**
     * Gets profile ID.
     *
     * @return the profile ID.
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * Sets the profile ID
     *
     * @param profileId the profile ID
     */
    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    /**
     * Gets the parent {@link ZigBeeNode} in which this endpoint is situated
     *
     * @return the parent {@link ZigBeeNode}
     */
    public ZigBeeNode getParentNode() {
        return node;
    }

    /**
     * Adds a server and makes it available to this endpoint.
     * The cluster used by the server must be in the output clusters list and this will be passed to the
     * {@link ZigBeeServer#serverStartup()) method to start the server.
     *
     * @param server the new {@link ZigBeeServer}
     */
    public void addServer(ZclServer server) {
        servers.put(server.getClusterId(), server);
        server.serverStartup(outputClusters.get(server.getClusterId()));
    }

    /**
     * Gets the server associated with the clusterId. Returns null if there is no server linked to the requested cluster
     *
     * @param clusterId
     * @return the {@link ZigBeeServer}
     */
    public ZclServer getServer(int clusterId) {
        return servers.get(clusterId);
    }

    @Override
    public void commandReceived(ZigBeeCommand command) {
        if (!command.getSourceAddress().equals(getEndpointAddress())) {
            return;
        }

        // Pass all commands received from this device to any registered servers
        synchronized (servers) {
            for (ZclServer server : servers.values()) {
                server.commandReceived(command);
            }
        }

        if (command instanceof ReportAttributesCommand) {
            ReportAttributesCommand attributeCommand = (ReportAttributesCommand) command;

            // Get the cluster
            ZclCluster cluster = getReceiveCluster(attributeCommand.getClusterId(),
                    attributeCommand.getCommandDirection());
            if (cluster == null) {
                logger.debug("{}: Cluster {} not found for attribute report", getEndpointAddress(),
                        attributeCommand.getClusterId());
                return;
            }

            // Pass the reports to the cluster
            cluster.handleAttributeReport(attributeCommand.getReports());
            return;
        }

        if (command instanceof ReadAttributesResponse) {
            ReadAttributesResponse attributeCommand = (ReadAttributesResponse) command;

            // Get the cluster
            ZclCluster cluster = getReceiveCluster(attributeCommand.getClusterId(),
                    attributeCommand.getCommandDirection());
            if (cluster == null) {
                logger.debug("{}: Cluster {} not found for attribute response", getEndpointAddress(),
                        attributeCommand.getClusterId());
                return;
            }

            attributeCommand.getRecords();
            // Pass the reports to the cluster
            cluster.handleAttributeStatus(attributeCommand.getRecords());
            return;
        }
    }

    @Override
    public String toString() {
        return "ZigBeeDevice [networkAddress=" + getEndpointAddress().toString() + ", profileId="
                + String.format("%04X", profileId) + ", deviceId=" + deviceId + ", deviceVersion=" + deviceVersion
                + ", inputClusterIds=" + getInputClusterIds().toString() + ", outputClusterIds="
                + getOutputClusterIds().toString() + "]";
    }
}