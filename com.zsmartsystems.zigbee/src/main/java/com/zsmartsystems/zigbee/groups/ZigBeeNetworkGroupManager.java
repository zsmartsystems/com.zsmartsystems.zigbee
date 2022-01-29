/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zcl.clusters.ZclGroupsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;
import com.zsmartsystems.zigbee.zdo.field.NodeDescriptor.MacCapabilitiesType;

/**
 * A class to centrally manage multicast groups within the network. Groups are managed in a central way as they span
 * across multiple devices and multiple endpoints. Setting groups by directly interacting with the Groups cluster in an
 * endpoint should be avoided in order to ensure that the group members are fully maintained here.
 * <p>
 * The ZigBee specification provides the capability for group addressing. That is, any endpoint on any device
 * may be assigned to one or more groups, each labelled with a 16-bit identifier (0x0001 – 0xFFF7), which acts
 * for all intents and purposes like a network address. Once a group is established, frames sent having a DstAddrMode
 * of 0x01, denoting group addressing, will be delivered to every endpoint assigned to the group address named in the
 * DstAddr parameter of the outgoing frame on every device in the network for which there are such endpoints.
 * <p>
 * Commands are defined here for discovering the group membership of a device, adding a group, removing a
 * group and removing all groups.
 * <p>
 * The group cluster allows application entities to store a name string for each group to which they are
 * assigned and to report that name string in response to a client request.
 * <p>
 * Note that configuration of group addresses for outgoing commands is achieved using the APS binding
 * mechanisms, and is not part of the group management.
 * <p>
 * As multicasts are made on a broadcast to all devices for which macRxOnWhenIdle = TRUE, sleeping end
 * devices will not be able to benefit from the features of the Groups and Scenes server Cluster.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeNetworkGroupManager {
    private static final String DATASTORE_KEY_GROUPS = "groups";

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeNetworkGroupManager.class);

    /**
     * The groups in the ZigBee network.
     */
    private final Map<Integer, ZigBeeGroup> networkGroups = new TreeMap<>();

    /**
     * The {@link ZigBeeNetworkManager} to which this group manager belongs
     */
    private final ZigBeeNetworkManager networkManager;

    public enum GroupSynchronizationMethod {
        /**
         * Reads the group information from all devices, and sets the framework to reflect the device configuration.
         * <p>
         * This should normally only be used when initialising a new network as it will overwrite the framework view of
         * the group membership.
         */
        GET,

        /**
         * The groups within the network, as read back from devices, and the group configuration in the framework are
         * merged. Any devices that are not configured with a group member that is set in the framework will be updated.
         * If a group member is unable to be set due to the device not supporting groups, or having insufficient space
         * in the group table, the membership will be removed from the framework.
         * <p>
         * This ensures that the information in the device and framework are consistent while not removing any members
         * that are already set. This may be useful if other controllers are active in the network.
         */
        MERGE,

        /**
         * Overwrites all group membership configuration on devices within the network with the configuration set in the
         * framework. This will remove any members from groups that were not known by the framework.
         */
        SET
    }

    public ZigBeeNetworkGroupManager(ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    public void initialize() {
        networkManager.readNetworkDataStore(DATASTORE_KEY_GROUPS);
    }

    public void shutdown() {
        Set<ZigBeeGroupDao> daoSet = new HashSet<>();
        for (ZigBeeGroup group : getAll()) {
            daoSet.add(group.getDao());
        }
        networkManager.writeNetworkDataStore(DATASTORE_KEY_GROUPS, daoSet);
    }

    /**
     * Creates or returns a {@link ZigBeeGroup}. If the group already exists, the existing {@link ZigBeeGroup} is
     * returned, otherwise a new {@link ZigBeeGroup} is created and returned.
     *
     * @param groupId the group ID to add or get
     * @return the {@link ZigBeeGroup}
     */
    public ZigBeeGroup add(final int groupId) {
        synchronized (networkGroups) {
            if (networkGroups.containsKey(groupId)) {
                return networkGroups.get(groupId);
            }
            ZigBeeGroup group = new ZigBeeGroup(networkManager, groupId);
            networkGroups.put(group.getGroupId(), group);
            return group;
        }
    }

    /**
     * Gets a {@link ZigBeeGroup} given the group ID
     *
     * @param groupId the ID of the group to return
     * @return the {@link ZigBeeGroup} or null if the group ID is not known
     */
    public ZigBeeGroup get(final int groupId) {
        synchronized (networkGroups) {
            return networkGroups.get(groupId);
        }
    }

    /**
     * Removes a group given the group ID.
     *
     * @param groupId the ID of the group to remove
     * @return the {@link ZigBeeGroup} that was removed, or null if the group was not found
     */
    public ZigBeeGroup delete(final int groupId) {
        ZigBeeGroup group = get(groupId);
        if (group == null) {
            return null;
        }

        synchronized (networkGroups) {
            networkGroups.remove(groupId);
        }

        // Loop over the group and remove all members
        Set<ZigBeeEndpoint> members = group.getMembers();
        for (ZigBeeEndpoint address : members) {
            group.removeMember(address);
        }

        return group;
    }

    /**
     * Returns the collection of {@link ZigBeeGroup} known in the network.
     *
     * @return the Collection of {@link ZigBeeGroup}
     */
    public Collection<ZigBeeGroup> getAll() {
        synchronized (networkGroups) {
            return networkGroups.values();
        }
    }

    /**
     * Synchronises the groups across the network. The method to be used for synchronisation is defined in
     * {@link GroupSynchronizationMethod} enumeration.
     *
     * @param syncMethod the {@link GroupSynchronizationMethod} to use when synchronizing the group members
     * @return {@link Future} providing the {@link ZigBeeStatus} of the result
     */
    public Future<ZigBeeStatus> synchronize(GroupSynchronizationMethod syncMethod) {
        RunnableFuture<ZigBeeStatus> future = new FutureTask<ZigBeeStatus>(new Callable<ZigBeeStatus>() {
            @Override
            public ZigBeeStatus call() {
                // TODO
                Map<ZigBeeEndpointAddress, ZigBeeGroupResponse> newGroups;

                // If we are just getting the network configuration, or are merging the network configuration into the
                // framework, then get all known group information first.
                if (syncMethod == GroupSynchronizationMethod.MERGE || syncMethod == GroupSynchronizationMethod.GET) {
                    try {
                        newGroups = getGroups().get();
                    } catch (InterruptedException | ExecutionException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        return ZigBeeStatus.FAILURE;
                    }
                } else {
                    newGroups = new ConcurrentHashMap<>();
                }

                Map<Integer, ZigBeeGroup> localNetworkGroups = new TreeMap<>();

                // Synchronise this to prevent simultaneous changes while we're updating
                synchronized (networkGroups) {
                    // Create the network view from what we've received from the devices

                    // Start from the existing view if this is a MERGE
                    if (syncMethod == GroupSynchronizationMethod.MERGE) {
                        localNetworkGroups.putAll(networkGroups);
                    }

                    // Merge in the existing network view if needed
                    if (syncMethod == GroupSynchronizationMethod.MERGE
                            || syncMethod == GroupSynchronizationMethod.GET) {
                        for (Entry<ZigBeeEndpointAddress, ZigBeeGroupResponse> groupEntry : newGroups.entrySet()) {
                            ZigBeeEndpointAddress address = groupEntry.getKey();
                            ZigBeeGroupResponse response = groupEntry.getValue();

                            for (Entry<Integer, String> groupResponseEntry : response.getGroups().entrySet()) {
                                ZigBeeGroup currentGroup = localNetworkGroups.get(groupResponseEntry.getKey());
                                if (currentGroup == null) {
                                    currentGroup = new ZigBeeGroup(networkManager, groupResponseEntry.getKey());
                                    localNetworkGroups.put(groupResponseEntry.getKey(), currentGroup);
                                }
                                ZigBeeNode node = networkManager.getNode(address.getAddress());
                                if (node == null) {
                                    continue;
                                }
                                ZigBeeEndpoint endpoint = node.getEndpoint(address.getEndpoint());
                                currentGroup.setMember(endpoint);

                                if (currentGroup.getLabel() == null && !groupResponseEntry.getValue().isEmpty()) {
                                    currentGroup.setLabel(groupResponseEntry.getValue());
                                }
                            }
                        }
                    }

                    // Write out the configuration to the devices
                    if (syncMethod == GroupSynchronizationMethod.MERGE
                            || syncMethod == GroupSynchronizationMethod.SET) {
                    }

                    networkGroups.clear();
                    networkGroups.putAll(localNetworkGroups);
                }
                return ZigBeeStatus.SUCCESS;
            }
        });

        // Start the thread to execute it
        new Thread(future).start();
        return future;
    }

    /**
     * Gets all groups on all endpoints known in the network.
     * <p>
     * This method will attempt to read the group tables of all known devices within the network and will create a
     * {@link ZigBeeGroup} for all known groups.
     * <p>
     * This method will spawn a separate thread that will run in the background. Once complete the Future will be
     * completed and will return a list of groups configured for each node in the network.
     *
     * @return Future with a {@link Map} containing the {@link ZigBeeEndpointAddress} and {@link ZigBeeGroupResponse}
     *         containing the groups for the endpoint
     */
    public Future<Map<ZigBeeEndpointAddress, ZigBeeGroupResponse>> getGroups() {
        RunnableFuture<Map<ZigBeeEndpointAddress, ZigBeeGroupResponse>> future = new FutureTask<Map<ZigBeeEndpointAddress, ZigBeeGroupResponse>>(
                new Callable<Map<ZigBeeEndpointAddress, ZigBeeGroupResponse>>() {
                    @Override
                    public Map<ZigBeeEndpointAddress, ZigBeeGroupResponse> call() throws Exception {
                        Map<ZigBeeEndpointAddress, ZigBeeGroupResponse> groups = new ConcurrentHashMap<>();

                        for (ZigBeeNode node : networkManager.getNodes()) {
                            // Only nodes that always listen can participate in multicasting
                            if (node.isReceiverOnWhenIdle() == null || !node.isReceiverOnWhenIdle()) {
                                logger.debug("{}: Not discovering groups as RECEIVER_ON_WHEN_IDLE check failed",
                                        node.getIeeeAddress());
                                continue;
                            }

                            for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                                try {
                                    Future<ZigBeeGroupResponse> future = getGroups(endpoint);
                                    ZigBeeGroupResponse response = future.get();
                                    logger.debug("{}: Endpoint {} group request completed with response {}",
                                            node.getIeeeAddress(), endpoint.getEndpointId(), response.getStatus());
                                    if (response.getStatus() == ZigBeeStatus.SUCCESS) {
                                        groups.put(endpoint.getEndpointAddress(), response);
                                    }
                                } catch (InterruptedException | ExecutionException e) {
                                }
                            }
                        }

                        return groups;
                    }
                });

        // Start the thread to execute it
        new Thread(future).start();
        return future;
    }

    /**
     * Request the group table for this endpoint.
     * <p>
     * This method returns a future to a {@link ZigBeeGroupResponse} containing the result.
     * <p>
     * Note that some endpoints will not support groups and the method will return ZigBeeStatus.UNSUPPORTED
     *
     * @return {@link Future} returning a {@link ZigBeeGroupResponse}
     */
    public Future<ZigBeeGroupResponse> getGroups(ZigBeeEndpoint endpoint) {
        logger.debug("{}: Starting update of groups for endpoint {}", endpoint.getIeeeAddress(),
                endpoint.getEndpointId());
        RunnableFuture<ZigBeeGroupResponse> future = new FutureTask<ZigBeeGroupResponse>(
                new Callable<ZigBeeGroupResponse>() {
                    @Override
                    public ZigBeeGroupResponse call() throws Exception {
                        ZigBeeGroupResponse response = new ZigBeeGroupResponse();

                        final ZclGroupsCluster cluster = (ZclGroupsCluster) endpoint
                                .getInputCluster(ZclGroupsCluster.CLUSTER_ID);
                        if (cluster == null) {
                            logger.debug("{}: Groups server unsupported on endpoint {}", endpoint.getIeeeAddress(),
                                    endpoint.getEndpointId());
                            response.setStatus(ZigBeeStatus.UNSUPPORTED);
                            return response;
                        }

                        boolean nameSupport = (int) (cluster.getAttribute(ZclGroupsCluster.ATTR_NAMESUPPORT)
                                .readValue(Long.MAX_VALUE)) != 0;
                        logger.debug("{}: Groups server on endpoint {} name support is {}", endpoint.getIeeeAddress(),
                                endpoint.getEndpointId(), nameSupport);
                        try {
                            GetGroupMembershipCommand getMembership = new GetGroupMembershipCommand(
                                    Collections.emptyList());
                            Future<CommandResult> membershipFuture = cluster.sendCommand(getMembership);
                            CommandResult membershipResult = membershipFuture.get();
                            if (!membershipResult.isSuccess()) {
                                logger.debug("{}: Group update failed on endpoint {}", endpoint.getIeeeAddress(),
                                        endpoint.getEndpointId());
                                response.setStatus(ZigBeeStatus.FAILURE);
                                return response;
                            }
                            if (!(membershipResult.getResponse() instanceof GetGroupMembershipResponse)) {
                                logger.debug("{}: Group update on endpoint {} failed with bad response {}",
                                        endpoint.getIeeeAddress(), endpoint.getEndpointId(),
                                        membershipResult.getResponse());
                                response.setStatus(ZigBeeStatus.BAD_RESPONSE);
                                return response;
                            }

                            GetGroupMembershipResponse membershipResponse = membershipResult.getResponse();
                            logger.debug("{}: Groups response on endpoint {}: {}", endpoint.getIeeeAddress(),
                                    endpoint.getEndpointId(), membershipResponse);

                            response.setCapacity(membershipResponse.getCapacity());
                            if (nameSupport) {
                                for (int groupId : membershipResponse.getGroupList()) {
                                    logger.debug("{}: Requesting group ID {} on endpoint {}", endpoint.getIeeeAddress(),
                                            groupId, endpoint.getEndpointId());
                                    Future<CommandResult> groupFuture = cluster
                                            .sendCommand(new ViewGroupCommand(groupId));
                                    CommandResult groupResult = groupFuture.get();
                                    if (groupResult.isSuccess()
                                            && groupResult.getResponse() instanceof ViewGroupResponse
                                            && ((ViewGroupResponse) groupResult.getResponse()).getGroupName() != null) {
                                        response.addGroup(groupId,
                                                ((ViewGroupResponse) groupResult.getResponse()).getGroupName());
                                    } else {
                                        response.addGroup(groupId);
                                    }
                                }
                            } else {
                                for (int groupId : membershipResponse.getGroupList()) {
                                    response.addGroup(groupId);
                                }
                            }

                            response.setStatus(ZigBeeStatus.SUCCESS);
                            return response;
                        } catch (InterruptedException | ExecutionException e) {
                            response.setStatus(ZigBeeStatus.FAILURE);
                            return response;
                        }
                    }
                });

        // Start the thread to execute it
        new Thread(future).start();
        return future;
    }
}
