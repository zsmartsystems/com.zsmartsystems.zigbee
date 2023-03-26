/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.groups;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.ZclTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.ZclGroupsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupResponse;

/**
 * ZigBee group definition. This maintains the group ID and label, along with the group members.
 * <p>
 * Note that the group label is limited to 16 characters as per the ZigBee specification
 *
 * @author Chris Jackson
 */
public class ZigBeeGroup implements Comparable<Object> {
    static final int MAX_LABEL_LENGTH = 16;

    static final int GROUP_ID_MIN = 0x0000;
    static final int GROUP_ID_MAX = 0xFFF7;

    /**
     * The logger.
     */
    private final Logger logger = LoggerFactory.getLogger(ZigBeeGroup.class);

    /**
     * The {@link ZigBeeNetworkManager} that manages the network
     */
    final ZigBeeNetworkManager networkManager;

    /**
     * The group ID.
     */
    private int groupId;

    /**
     * The group label.
     */
    private String label;

    /**
     * Set of members of this group
     */
    private Set<ZigBeeGroupMember> members = new HashSet<>();

    /**
     * Constructor which sets group ID.
     *
     * @param networkManager the {@link ZigBeeNetworkManager} that manages the groups on the network
     * @param groupId the group ID
     */
    public ZigBeeGroup(final ZigBeeNetworkManager networkManager, final int groupId) {
        this.networkManager = networkManager;
        setGroupId(groupId);
    }

    /**
     * Constructor which sets group ID and label.
     *
     * @param networkManager the {@link ZigBeeNetworkManager} that manages the groups on the network
     * @param groupId the group ID
     * @param label the group label. Note that label is limited to 16 characters long.
     */
    public ZigBeeGroup(final ZigBeeNetworkManager networkManager, final int groupId, final String label) {
        this.networkManager = networkManager;
        setGroupId(groupId);
        setLabel(label);
    }

    /**
     * Gets the {@link ZigBeeGroupAddress} of this group
     *
     * @return the {@link ZigBeeGroupAddress} of this group
     */
    public ZigBeeGroupAddress getAddress() {
        return new ZigBeeGroupAddress(groupId);
    }

    /**
     * Gets group ID.
     *
     * @return the group ID.
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Sets group ID.
     *
     * @param groupId the group ID
     */
    public void setGroupId(final int groupId) {
        if (groupId < GROUP_ID_MIN || groupId > GROUP_ID_MAX) {
            throw (new IllegalArgumentException("Group ID must be between 0000 and FFF7"));
        }
        this.groupId = groupId;
    }

    /**
     * Gets group label.
     *
     * @return the group label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets group label.
     *
     * @param label the group label. Note that label is limited to 16 characters long.
     */
    public void setLabel(final String label) {
        if (label.length() <= MAX_LABEL_LENGTH) {
            this.label = label;
        } else {
            this.label = label.substring(0, MAX_LABEL_LENGTH);
        }
    }

    /**
     * Gets the set of {@link ZigBeeEndpoint}s that constitute the members of the group.
     * <p>
     * Note that this method will only return a {@link ZigBeeEndpoint} for nodes that are known in the network. If there
     * are other addresses configured in a group for devices that are not known by the framework, then they remain
     * hidden and you should use the {@link # getMemberAddresses} method to get all configured addresses.
     *
     * @return the set of {@link ZigBeeEndpoint}s that constitute the members of the group
     */
    public Set<ZigBeeEndpoint> getMembers() {
        Set<ZigBeeEndpoint> endpoints = new HashSet<>();
        for (ZigBeeGroupMember member : members) {
            ZigBeeNode node = networkManager.getNode(member.getAddress());
            if (node == null) {
                continue;
            }
            ZigBeeEndpoint endpoint = node.getEndpoint(member.getEndpointId());
            if (endpoint == null) {
                continue;
            }
            endpoints.add(endpoint);
        }
        return endpoints;
    }

    /**
     * Gets the set of {@link ZigBeeGroupMember}s that constitute the members of the group.
     * <p>
     * This method will return all configured addresses even if the device is unknown by the framework.
     *
     * @return the set of {@link ZigBeeGroupMember}s that constitute the members of the group
     */
    public Set<ZigBeeGroupMember> getMemberAddresses() {
        return members;
    }

    /**
     * Set a {@link ZigBeeEndpoint} as a member of the group. This will not update the groups within the device, but
     * will just update the local list.
     *
     * @param endpoint the {@link ZigBeeEndpoint} to add as he group member
     * @return true if this set did not already contain the specified element
     */
    public boolean setMember(ZigBeeEndpoint endpoint) {
        ZigBeeGroupMember address = new ZigBeeGroupMember(endpoint.getIeeeAddress(), endpoint.getEndpointId());
        return members.add(address);
    }

    /**
     * Add a {@link ZigBeeEndpoint} as a member of the group. This will update the groups within the device.
     * <p>
     * Note that this is a blocking call and will not return until after the command response from the remote device.
     *
     * @param endpoint the {@link ZigBeeEndpoint} to add as he group member
     * @return {@link ZigBeeStatus} containing the status of the operation
     */
    public ZigBeeStatus addMember(ZigBeeEndpoint endpoint) {
        ZigBeeGroupMember address = new ZigBeeGroupMember(endpoint.getIeeeAddress(), endpoint.getEndpointId());
        ZigBeeStatus result = addGroup(address);
        if (result == ZigBeeStatus.SUCCESS) {
            members.add(address);
        }
        return result;
    }

    /**
     * Removes a {@link ZigBeeEndpoint} as a member of the group
     * <p>
     * Note that this is a blocking call and will not return until after the command response from the remote device.
     *
     * @param endpoint the {@link ZigBeeEndpoint} to remove from the group
     * @return {@link ZigBeeStatus} containing the status of the operation
     */
    public ZigBeeStatus removeMember(ZigBeeEndpoint endpoint) {
        ZigBeeGroupMember address = new ZigBeeGroupMember(endpoint.getIeeeAddress(), endpoint.getEndpointId());
        ZigBeeStatus result = removeGroup(address);
        if (result == ZigBeeStatus.SUCCESS) {
            members.remove(address);
        }
        return result;
    }

    private ZigBeeStatus addGroup(ZigBeeGroupMember address) {
        ZclGroupsCluster cluster = getGroupsCluster(address);
        if (cluster == null) {
            return ZigBeeStatus.UNSUPPORTED;
        }

        CommandResult cmdResult;
        try {
            cmdResult = cluster.sendCommand(new AddGroupCommand(groupId, (label == null ? "" : label))).get();
            if (cmdResult.isTimeout()) {
                logger.debug("{}: Unable to add group {} - timeout", address.getAddress(), groupId);
                return ZigBeeStatus.NO_RESPONSE;
            }
            ZclStatus zclStatus;
            if (cmdResult.isError()) {
                zclStatus = ZclStatus.UNKNOWN;
            } else {
                zclStatus = ((AddGroupResponse) cmdResult.getResponse()).getStatus();
            }
            if (zclStatus != ZclStatus.SUCCESS) {
                logger.debug("{}: Unable to add group {} - error {}", address.getAddress(), groupId, zclStatus);
                switch (zclStatus) {
                    case INSUFFICIENT_SPACE:
                        return ZigBeeStatus.NO_RESOURCES;
                    default:
                        return ZigBeeStatus.FAILURE;
                }
            }

            logger.debug("{}: Added group {} successfully", address.getAddress(), groupId);
            return ZigBeeStatus.SUCCESS;
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("{}: Interrupted adding group {}", address.getAddress(), groupId);
            return ZigBeeStatus.FAILURE;
        }
    }

    private ZigBeeStatus removeGroup(ZigBeeGroupMember address) {
        ZclGroupsCluster cluster = getGroupsCluster(address);
        if (cluster == null) {
            return ZigBeeStatus.UNSUPPORTED;
        }

        CommandResult cmdResult;
        try {
            cmdResult = cluster.sendCommand(new RemoveGroupCommand(groupId)).get();
            if (cmdResult.isTimeout()) {
                logger.debug("{}: Unable to remove group {} - timeout", address.getAddress(), groupId);
                return ZigBeeStatus.NO_RESPONSE;
            }
            ZclStatus zclStatus;
            if (cmdResult.isError()) {
                zclStatus = ZclStatus.UNKNOWN;
            } else {
                zclStatus = ((RemoveGroupResponse) cmdResult.getResponse()).getStatus();
            }
            if (zclStatus != ZclStatus.SUCCESS) {
                logger.debug("{}: Unable to remove group {} - error {}", address.getAddress(), groupId, zclStatus);
                switch (zclStatus) {
                    default:
                        return ZigBeeStatus.FAILURE;
                }
            }

            logger.debug("{}: Removed group {} successfully", address.getAddress(), groupId);
            return ZigBeeStatus.SUCCESS;
        } catch (InterruptedException | ExecutionException e) {
            logger.debug("{}: Interrupted removing group {}", address.getAddress(), groupId);
            return ZigBeeStatus.FAILURE;
        }
    }

    private ZclGroupsCluster getGroupsCluster(ZigBeeGroupMember address) {
        ZigBeeNode node = networkManager.getNode(address.getAddress());
        if (node == null) {
            logger.debug("{}: Unable to find node while maninging group", address.getAddress());
            return null;
        }

        ZigBeeEndpoint endpoint = node.getEndpoint(address.getEndpointId());
        if (endpoint == null) {
            logger.debug("{}: Unable to find endpoint {} while maninging group", address.getAddress(),
                    address.getEndpointId());
            return null;
        }

        ZclGroupsCluster cluster = (ZclGroupsCluster) endpoint.getInputCluster(ZclGroupsCluster.CLUSTER_ID);
        if (cluster == null) {
            logger.debug("{}: Unable to find Groups Cluster on endpoint {} while maninging group", address.getAddress(),
                    address.getEndpointId());
            return null;
        }

        return cluster;
    }

    /**
     * Sends a {@link ZclCommand} and returns the {@link Future} to the result which will complete when the remote
     * device response is received, or the request times out.
     * <p>
     * It should be noted that multicast APS commands are broadcast on the Zigbee network, and as such there are
     * restrictions on the number of multicast commands that can be sent at once. In general, a Zigbee network will
     * limit the number of outstanding broadcasts to approximately one every second. It should be noted that this is the
     * total number of broadcasts - not just the broadcasts sent from the coordinator or a specific device, and will
     * therefore include ZDO messages for address resolution, etc.
     * <p>
     * Note: Currently this will only send commands to servers.
     *
     * @param command the {@link ZclCommand} to send
     * @return the command result future
     */
    public Future<CommandResult> sendCommand(ZclCommand command) {
        command.setApsSecurity(false);
        command.setDestinationAddress(new ZigBeeGroupAddress(groupId));

        return networkManager.sendTransaction(command, new ZclTransactionMatcher());
    }

    public ZigBeeGroupDao getDao() {
        ZigBeeGroupDao dao = new ZigBeeGroupDao();
        dao.setGroupId(groupId);
        dao.setLabel(label);
        dao.setMemberAddresses(members);
        return dao;
    }

    public void setDao(ZigBeeGroupDao dao) {
        this.groupId = dao.getGroupId();
        this.label = dao.getLabel();
        this.members = dao.getMemberAddresses();
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }

    @Override
    public int compareTo(Object that) {
        if (this == that) {
            return 0;
        }
        if (!(that instanceof ZigBeeGroup)) {
            return -1;
        }

        ZigBeeGroup thatAddr = (ZigBeeGroup) that;

        if (thatAddr.getGroupId() == getGroupId()) {
            return 0;
        }

        return (thatAddr.getGroupId() < getGroupId()) ? -1 : 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!ZigBeeGroup.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final ZigBeeGroup other = (ZigBeeGroup) obj;
        if (other.getGroupId() != getGroupId()) {
            return false;
        }

        if (getLabel() == null && other.getLabel() == null) {
            return true;
        }
        if (getLabel() == null) {
            return false;
        }
        return getLabel().equals(other.getLabel());
    }

    @Override
    public String toString() {
        return "ZigBeeGroup [groupId=" + groupId + ", label=" + label + ", members=" + members + "]";
    }

}