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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReportAttributesCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;

/**
 * ZigBee endpoint. An endpoint is a virtual entity contained within a {@link ZigBeeNode}. The {@link ZigBeeNode} is the
 * physical device and can contain up to 254 endpoints.
 *
 * @author Chris Jackson
 */
public class ZigBeeEndpoint implements CommandListener {
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
     * Total list of clusters supported by the endpoint
     */
    private final Map<Integer, ZclCluster> clusters = new HashMap<Integer, ZclCluster>();

    /**
     * Input cluster IDs
     */
    private final List<Integer> inputClusterIds = new ArrayList<Integer>();

    /**
     * Output cluster IDs
     */
    private final List<Integer> outputClusterIds = new ArrayList<Integer>();

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
     * @return the input cluster IDs
     */
    public List<Integer> getInputClusterIds() {
        return inputClusterIds;
    }

    /**
     * Gets an input cluster
     *
     * @param clusterId
     *            the cluster number
     * @return the cluster or null if cluster is not found
     */
    public ZclCluster getCluster(int clusterId) {
        return clusters.get(clusterId);
    }

    /**
     * Sets input cluster IDs.
     *
     * @param inputClusterIds
     *            the input cluster IDs
     */
    public void setInputClusterIds(List<Integer> inputClusterIds) {
        this.inputClusterIds.clear();
        this.inputClusterIds.addAll(inputClusterIds);

        logger.debug("{}: Setting input clusters {}", getEndpointAddress(), inputClusterIds);

        updateClusters(inputClusterIds, true);
    }

    /**
     * Gets the {@link IeeeAddress} for this endpoint from it's parant {@link ZigBeeNode}
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
     * @return the output cluster IDs
     */
    public List<Integer> getOutputClusterIds() {
        return outputClusterIds;
    }

    /**
     * Sets output cluster IDs.
     *
     * @param outputClusterIds
     *            the output cluster IDs
     */
    public void setOutputClusterIds(List<Integer> outputClusterIds) {
        this.outputClusterIds.clear();
        this.outputClusterIds.addAll(outputClusterIds);

        logger.debug("{}: Setting output clusters {}", getEndpointAddress(), outputClusterIds);

        updateClusters(outputClusterIds, false);
    }

    private void updateClusters(List<Integer> newList, boolean isInput) {
        // Get a list any clusters that are no longer in the list
        List<Integer> removeIds = new ArrayList<Integer>();
        for (ZclCluster cluster : clusters.values()) {
            if (newList.contains(cluster.getClusterId())) {
                // The existing cluster is in the new list, so no need to remove it
                continue;
            }
            if (isInput && !cluster.isServer()) {
                cluster.setServer(false);
            }
            if (isInput && !cluster.isClient()) {
                cluster.setClient(false);
            }

            // If the cluster is not a server or client, then remove it
            if (!cluster.isClient() && !cluster.isServer()) {
                removeIds.add(cluster.getClusterId());
            }
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
                    clusterClass.setServer(true);
                } else {
                    logger.debug("{}: Setting cluster {} as client", getEndpointAddress(), clusterType);
                    clusterClass.setClient(true);
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

    @Override
    public void commandReceived(ZigBeeCommand command) {
        if (!command.getSourceAddress().equals(getEndpointAddress())) {
            return;
        }

        if (command instanceof ReportAttributesCommand) {
            ReportAttributesCommand attributeCommand = (ReportAttributesCommand) command;

            // Get the cluster
            ZclCluster cluster = getCluster(attributeCommand.getClusterId());
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
            ZclCluster cluster = getCluster(attributeCommand.getClusterId());
            if (cluster == null) {
                logger.debug("{}: Cluster {} not found for attribute report", getEndpointAddress(),
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
