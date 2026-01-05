/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.main;

import java.util.concurrent.Future;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.groups.ZigBeeGroup;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupCommand;

/**
 * ZigBee API. This API is experimental and under development.
 *
 * @deprecated
 *             This interface will be removed in (near) future. Users should use the {@link ZigBeeNetworkManager} and
 *             interfaces in the {@link ZclCluster}, {@link ZigBeeEndpoint}, {@link ZigBeeNode} (etc) classes as it
 *             provides a more object oriented interface.
 *
 * @author Tommi S.E. Laukkanen
 * @author Chris Jackson
 */
@Deprecated
public class ZigBeeApi {
    /**
     * The ZigBee Network Manager
     */
    private ZigBeeNetworkManager networkManager;

    /**
     * Default constructor inheritance.
     */
    public ZigBeeApi() {
    }

    /**
     * Constructor for setting the ZCL API.
     *
     * @param network the ZCL API
     */
    public ZigBeeApi(final ZigBeeNetworkManager networkManager) {
        this.networkManager = networkManager;
    }

    /**
     * Gets group by network address.
     *
     * @param groupId the group ID
     * @return the ZigBee group or null if no exists with given group ID.
     */
    public ZigBeeGroup getGroup(final int groupId) {
        return networkManager.getGroup(groupId);
    }

    /**
     * Switches destination on.
     *
     * @param destination the {@link ZigBeeAddress}
     * @return the command result future.
     */
    public Future<CommandResult> on(final ZigBeeAddress destination) {
        if (!(destination instanceof ZigBeeEndpointAddress)) {
            return null;
        }
        ZigBeeEndpointAddress endpointAddress = (ZigBeeEndpointAddress) destination;
        ZigBeeEndpoint endpoint = networkManager.getNode(endpointAddress.getAddress())
                .getEndpoint(endpointAddress.getEndpoint());
        if (endpoint == null) {
            return null;
        }
        ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint.getInputCluster(ZclOnOffCluster.CLUSTER_ID);
        return cluster.onCommand();
    }

    /**
     * Switches destination off.
     *
     * @param destination the {@link ZigBeeAddress}
     * @return the command result future.
     */
    public Future<CommandResult> off(final ZigBeeAddress destination) {
        if (!(destination instanceof ZigBeeEndpointAddress)) {
            return null;
        }
        ZigBeeEndpointAddress endpointAddress = (ZigBeeEndpointAddress) destination;
        ZigBeeEndpoint endpoint = networkManager.getNode(endpointAddress.getAddress())
                .getEndpoint(endpointAddress.getEndpoint());
        if (endpoint == null) {
            return null;
        }
        ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint.getInputCluster(ZclOnOffCluster.CLUSTER_ID);
        return cluster.offCommand();
    }

    /**
     * Moves device level.
     *
     * @param destination the {@link ZigBeeAddress}
     * @param level the level
     * @param time the transition time
     * @return the command result future.
     */
    public Future<CommandResult> level(final ZigBeeAddress destination, final double level, final double time) {

        int l = (int) (level * 254);
        if (l > 254) {
            l = 254;
        }
        if (l < 0) {
            l = 0;
        }

        if (!(destination instanceof ZigBeeEndpointAddress)) {
            return null;
        }
        ZigBeeEndpointAddress endpointAddress = (ZigBeeEndpointAddress) destination;
        ZigBeeEndpoint endpoint = networkManager.getNode(endpointAddress.getAddress())
                .getEndpoint(endpointAddress.getEndpoint());
        if (endpoint == null) {
            return null;
        }
        ZclLevelControlCluster cluster = (ZclLevelControlCluster) endpoint
                .getInputCluster(ZclLevelControlCluster.CLUSTER_ID);
        return cluster.moveToLevelWithOnOffCommand(l, (int) (time * 10));
    }

    /**
     * Adds group membership to device.
     *
     * @param device the device
     * @param groupId the group ID
     * @param groupName the group name
     * @return the command result future
     */
    public Future<CommandResult> addMembership(final ZigBeeEndpoint device, final int groupId, final String groupName) {
        final AddGroupCommand command = new AddGroupCommand();
        command.setGroupId(groupId);
        command.setGroupName(groupName);

        command.setDestinationAddress(device.getEndpointAddress());

        return networkManager.sendTransaction(command, new ZclTransactionMatcher());
    }

    /**
     * Views group membership from device.
     *
     * @param device the device
     * @param groupId the group ID
     * @return the command result future
     */
    public Future<CommandResult> viewMembership(final ZigBeeEndpoint device, final int groupId) {
        final ViewGroupCommand command = new ViewGroupCommand();
        command.setGroupId(groupId);

        command.setDestinationAddress(device.getEndpointAddress());

        return networkManager.sendTransaction(command, new ZclTransactionMatcher());
    }

    /**
     * Removes group membership from device.
     *
     * @param device the device
     * @param groupId the group ID
     * @return the command result future
     */
    public Future<CommandResult> removeMembership(final ZigBeeEndpoint device, final int groupId) {
        final RemoveGroupCommand command = new RemoveGroupCommand();
        command.setGroupId(groupId);

        command.setDestinationAddress(device.getEndpointAddress());

        return networkManager.sendTransaction(command, new ZclTransactionMatcher());
    }
}
