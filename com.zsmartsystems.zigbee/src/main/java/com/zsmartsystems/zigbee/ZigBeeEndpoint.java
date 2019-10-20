/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.app.ZigBeeApplication;
import com.zsmartsystems.zigbee.database.ZclClusterDao;
import com.zsmartsystems.zigbee.database.ZigBeeEndpointDao;
import com.zsmartsystems.zigbee.transaction.ZigBeeTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclCustomCluster;
import com.zsmartsystems.zigbee.zcl.clusters.general.DefaultResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * ZigBee endpoint. An endpoint is a virtual entity contained within a {@link ZigBeeNode}. The {@link ZigBeeNode} is the
 * physical device and can contain up to 254 endpoints.
 *
 * @author Chris Jackson
 */
public class ZigBeeEndpoint {
    /**
     * The {@link Logger}.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeEndpoint.class);

    /**
     * Link to the parent {@link ZigBeeNode} to which this endpoint belongs
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
     * Map of {@link ZigBeeApplication}s that are available to this endpoint. Applications are added
     * with the {@link #addApplication(ZigBeeApplication application)} method and can be retrieved with the
     * {@link #getApplication(int clusterId)} method.
     */
    private final Map<Integer, ZigBeeApplication> applications = new ConcurrentHashMap<Integer, ZigBeeApplication>();

    /**
     * Constructor
     *
     * @param node the parent {@link ZigBeeNode}
     * @param endpoint the endpoint number within the {@link ZigBeeNode}
     */
    public ZigBeeEndpoint(ZigBeeNode node, int endpoint) {
        this.node = node;
        this.endpointId = endpoint;
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
     * Adds a specific {@link ZclCluster} to the input clusters in this endpoint.
     *
     * @param cluster the {@link ZclCluster} to add
     * @return true if the cluster was added, false if there was an error (eg the cluster was already included in the
     *         endpoint)
     */
    public boolean addInputCluster(ZclCluster cluster) {
        if (inputClusters.containsKey(cluster.getClusterId())
                && !(inputClusters.get(cluster.getClusterId()) instanceof ZclCustomCluster)) {
            return false;
        }

        inputClusters.put(cluster.getClusterId(), cluster);
        return true;
    }

    /**
     * Gets an input cluster
     *
     * @deprecated Use {@link #getInputCluster}
     * @param clusterId the cluster number
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
     * Sets input cluster IDs. This will add any new clusters in the list, and remove any that are no longer in the
     * list.
     *
     * @param inputClusterIds the input cluster IDs
     */
    public void setInputClusterIds(List<Integer> inputClusterIds) {
        inputClusters.clear();

        logger.debug("{}: Setting input clusters {}", getEndpointAddress(), printClusterList(inputClusterIds));

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
     * Gets the endpoint address
     *
     * @return the {@link ZigBeeEndpointAddress}
     */
    public ZigBeeEndpointAddress getEndpointAddress() {
        return new ZigBeeEndpointAddress(node.getNetworkAddress(), endpointId);
    }

    /**
     * Gets output cluster IDs. This provides the IDs of all clusters the endpoint
     * supports as a client.
     *
     * @return the {@link Collection} of output cluster IDs
     */
    public Collection<Integer> getOutputClusterIds() {
        return outputClusters.keySet();
    }

    /**
     * Sets output cluster IDs. This will add any new clusters in the list, and remove any that are no longer in the
     * list.
     *
     * @param outputClusterIds the output cluster IDs
     */
    public void setOutputClusterIds(List<Integer> outputClusterIds) {
        outputClusters.clear();

        logger.debug("{}: Setting output clusters {}", getEndpointAddress(), printClusterList(outputClusterIds));

        updateClusters(outputClusters, outputClusterIds, false);
    }

    /**
     * Adds a specific {@link ZclCluster} to the output clusters in this endpoint.
     *
     * @param cluster the {@link ZclCluster} to add
     * @return true if the cluster was added, false if there was an error (eg the cluster was already included in the
     *         endpoint)
     */
    public boolean addOutputCluster(ZclCluster cluster) {
        if (outputClusters.containsKey(cluster.getClusterId())
                && !(outputClusters.get(cluster.getClusterId()) instanceof ZclCustomCluster)) {
            return false;
        }

        cluster.setClient();
        outputClusters.put(cluster.getClusterId(), cluster);
        return true;
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

    private ZclCluster getClusterClass(int clusterId) {
        ZclClusterType clusterType = ZclClusterType.getValueById(clusterId);
        if (clusterType == null) {
            // Unsupported cluster
            logger.debug("{}: Unsupported cluster {} - using ZclCustomCluster", getEndpointAddress(),
                    String.format("%04X", clusterId));
            return new ZclCustomCluster(this, clusterId, "");
        }

        // Create a cluster class
        ZclCluster cluster = null;
        Constructor<? extends ZclCluster> constructor;
        try {
            constructor = clusterType.getClusterClass().getConstructor(ZigBeeEndpoint.class);
            cluster = constructor.newInstance(this);
        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            logger.debug("{}: Error instantiating cluster {}", getEndpointAddress(), clusterType);
            return null;
        }

        return cluster;
    }

    private void updateClusters(Map<Integer, ZclCluster> clusters, List<Integer> newList, boolean isInput) {
        // Get a list any clusters that are no longer in the list
        List<Integer> removeIds = new ArrayList<>();
        for (ZclCluster cluster : clusters.values()) {
            if (newList.contains(cluster.getClusterId())) {
                // The existing cluster is in the new list, so no need to remove it
                continue;
            }

            removeIds.add(cluster.getClusterId());
        }

        // Remove clusters no longer in use
        for (int id : removeIds) {
            logger.debug("{}: Removing cluster {}", getEndpointAddress(), String.format("%04X", id));
            clusters.remove(id);
        }

        // Add any missing clusters into the list
        for (int id : newList) {
            if (!clusters.containsKey(id)) {
                // Get the cluster type
                ZclCluster clusterClass = getClusterClass(id);
                if (clusterClass == null) {
                    logger.debug("{}: Cluster {} not created", getEndpointAddress(), String.format("%04X", id));
                    continue;
                }

                if (isInput) {
                    logger.debug("{}: Setting server cluster {} {}", getEndpointAddress(),
                            String.format("%04X", clusterClass.getClusterId()), clusterClass.getClusterName());
                    clusterClass.setServer();
                } else {
                    logger.debug("{}: Setting client cluster {} {}", getEndpointAddress(),
                            String.format("%04X", clusterClass.getClusterId()), clusterClass.getClusterName());
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
     * Adds an application and makes it available to this endpoint.
     * The cluster used by the server must be in the output clusters list and this will be passed to the
     * {@link ZclApplication#serverStartup()) method to start the application.
     *
     * @param application the new {@link ZigBeeApplication}
     * @return the {@link ZigBeeStatus} of the call. {@link ZigBeeStatus.SUCCESS} if the application was registered and
     *         started, and {@link ZigBeeStatus.INVALID_STATE} if an application is already registered to the cluster
     */
    public ZigBeeStatus addApplication(ZigBeeApplication application) {
        if (applications.get(application.getClusterId()) != null) {
            return ZigBeeStatus.INVALID_STATE;
        }
        applications.put(application.getClusterId(), application);
        ZclCluster cluster = outputClusters.get(application.getClusterId());
        if (cluster == null) {
            cluster = inputClusters.get(application.getClusterId());
        }
        return application.appStartup(cluster);
    }

    /**
     * Gets the application associated with the clusterId. Returns null if there is no server linked to the requested
     * cluster
     *
     * @param clusterId the cluster ID of the application to retrieve
     * @return the {@link ZigBeeApplication} registered to the clusterId or null if no application is registered
     */
    public ZigBeeApplication getApplication(int clusterId) {
        return applications.get(clusterId);
    }

    /**
     * Incoming command handler. The endpoint will process any commands addressed to this endpoint ID and pass to
     * clusters and applications
     *
     * @param command the {@link ZclCommand} received
     */
    public void commandReceived(ZclCommand command) {
        if (!command.getSourceAddress().equals(getEndpointAddress())) {
            return;
        }
        logger.trace("{}: ZigBeeEndpoint.commandReceived({})", getEndpointAddress(), command);

        // Get the cluster
        ZclCluster cluster = getReceiveCluster(command.getClusterId(), command.getCommandDirection());
        if (cluster == null) {
            logger.debug("{}: Cluster {} not found for received endpoint command", getEndpointAddress(),
                    String.format("%04X", command.getClusterId()));
            DefaultResponse response = ZclCluster.createDefaultResponse(command, ZclStatus.UNSUPPORTED_CLUSTER);
            if (response != null) {
                sendTransaction(response);
            }
            return;
        }

        cluster.handleCommand(command);
    }

    /**
     * Updates the endpoint with information in the provided endpoint.
     * <p>
     * Clusters will only be added to the endpoint since this allows device discovery to progressivelymissing
     *
     * @param endpoint the endpoint from which to update this endpoint
     * @return true if the endpoint was updated
     */
    public boolean updateEndpoint(ZigBeeEndpoint endpoint) {
        if (!(endpoint.getIeeeAddress().equals(getIeeeAddress()) && endpoint.getEndpointId() == getEndpointId())) {
            logger.debug("{}: Updating endpoint from {} not allowed", getIeeeAddress(), endpoint.getIeeeAddress());
            return false;
        }

        boolean updated = false;
        logger.debug("{}: Updating endpoint {}", getIeeeAddress(), getEndpointId());

        if (!endpoint.getInputClusterIds().equals(getInputClusterIds())) {
            for (Integer clusterId : endpoint.getInputClusterIds()) {
                if (!inputClusters.containsKey(clusterId)) {
                    logger.debug("{}: Adding input cluster {}", getEndpointAddress(), String.format("%04X", clusterId));

                    inputClusters.put(clusterId, endpoint.getInputCluster(clusterId));
                    updated = true;
                }
            }
        }
        if (!endpoint.getOutputClusterIds().equals(getOutputClusterIds())) {
            for (Integer clusterId : endpoint.getOutputClusterIds()) {
                if (!outputClusters.containsKey(clusterId)) {
                    logger.debug("{}: Adding output cluster {}", getEndpointAddress(),
                            String.format("%04X", clusterId));

                    outputClusters.put(clusterId, endpoint.getOutputCluster(clusterId));
                    updated = true;
                }
            }
        }

        return updated;
    }

    /**
     * Gets a {@link ZigBeeEndpointDao} used for serialisation of the {@link ZigBeeEndpoint}
     *
     * @return the {@link ZigBeeEndpointDao}
     */
    public ZigBeeEndpointDao getDao() {
        ZigBeeEndpointDao dao = new ZigBeeEndpointDao();

        dao.setEndpointId(endpointId);
        dao.setProfileId(profileId);
        dao.setDeviceId(deviceId);
        dao.setDeviceVersion(deviceVersion);

        List<ZclClusterDao> clusters;

        clusters = new ArrayList<ZclClusterDao>();
        for (ZclCluster cluster : inputClusters.values()) {
            clusters.add(cluster.getDao());
        }
        dao.setInputClusters(clusters);

        clusters = new ArrayList<ZclClusterDao>();
        for (ZclCluster cluster : outputClusters.values()) {
            clusters.add(cluster.getDao());
        }
        dao.setOutputClusters(clusters);

        return dao;
    }

    public void setDao(ZigBeeEndpointDao dao) {
        endpointId = dao.getEndpointId();
        if (dao.getProfileId() != null) {
            profileId = dao.getProfileId();
        }
        if (dao.getDeviceId() != null) {
            deviceId = dao.getDeviceId();
        }
        if (dao.getDeviceVersion() != null) {
            deviceVersion = dao.getDeviceVersion();
        }

        if (dao.getInputClusterIds() != null) {
            for (ZclClusterDao clusterDao : dao.getInputClusters()) {
                ZclCluster cluster = getClusterClass(clusterDao.getClusterId());
                if (cluster != null) {
                    cluster.setDao(clusterDao);
                    inputClusters.put(clusterDao.getClusterId(), cluster);
                } else {
                    logger.debug("Unknown input cluster found with id={}, will skip it",
                            String.format("%04X", clusterDao.getClusterId()));
                }
            }
        }
        if (dao.getOutputClusterIds() != null) {
            for (ZclClusterDao clusterDao : dao.getOutputClusters()) {
                ZclCluster cluster = getClusterClass(clusterDao.getClusterId());
                if (cluster != null) {
                    cluster.setDao(clusterDao);
                    outputClusters.put(clusterDao.getClusterId(), cluster);
                } else {
                    logger.debug("Unknown output cluster found with id={}, will skip it",
                            String.format("%04X", clusterDao.getClusterId()));
                }
            }
        }
    }

    /**
     * Sends ZigBee command without waiting for response.
     *
     * @param command the {@link ZigBeeCommand} to send
     */
    public void sendTransaction(ZigBeeCommand command) {
        command.setDestinationAddress(getEndpointAddress());
        node.sendTransaction(command);
    }

    /**
     * Sends {@link ZigBeeCommand} command and uses the {@link ZigBeeTransactionMatcher} to match the response.
     *
     * @param command the {@link ZigBeeCommand} to send
     * @param responseMatcher the {@link ZigBeeTransactionMatcher} used to match the response to the request
     * @return the {@link CommandResult} future.
     */
    public Future<CommandResult> sendTransaction(ZigBeeCommand command, ZigBeeTransactionMatcher responseMatcher) {
        command.setDestinationAddress(getEndpointAddress());
        return node.sendTransaction(command, responseMatcher);
    }

    @Override
    public String toString() {
        return "ZigBeeEndpoint [networkAddress=" + getEndpointAddress().toString() + ", profileId="
                + String.format("%04X", profileId) + ", deviceId=" + deviceId + ", deviceVersion=" + deviceVersion
                + ", inputClusterIds=" + getInputClusterIds().toString() + ", outputClusterIds="
                + getOutputClusterIds().toString() + "]";
    }

    private String printClusterList(List<Integer> clusterIds) {
        StringBuilder builder = new StringBuilder(clusterIds.size() * 6 + 4);

        builder.append('[');
        for (int clusterId : clusterIds) {
            if (builder.length() > 1) {
                builder.append(", ");
            }
            builder.append(String.format("%04X", clusterId));
        }
        builder.append(']');

        return builder.toString();
    }
}
