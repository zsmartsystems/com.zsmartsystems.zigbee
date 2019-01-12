/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.main;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Future;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeGroupAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclTransactionMatcher;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.SquawkCommand;
import com.zsmartsystems.zigbee.zcl.clusters.iaswd.StartWarningCommand;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelWithOnOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;

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
    public ZigBeeGroupAddress getGroup(final int groupId) {
        return networkManager.getGroup(groupId);
    }

    /**
     * Gets all groups.
     *
     * @return list of groups.
     */
    public List<ZigBeeGroupAddress> getGroups() {
        return networkManager.getGroups();
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
        final OffCommand command = new OffCommand();
        return networkManager.send(destination, command);
    }

    /**
     * Colors device light.
     *
     * @param destination the {@link ZigBeeAddress}
     * @param red the red component [0..1]
     * @param green the green component [0..1]
     * @param blue the blue component [0..1]
     * @param time the in seconds
     * @return the command result future.
     */
    public Future<CommandResult> color(final ZigBeeAddress destination, final double red, final double green,
            final double blue, double time) {
        final MoveToColorCommand command = new MoveToColorCommand();

        final Cie cie = Cie.rgb2cie(red, green, blue);

        int x = (int) (cie.x * 65536);
        int y = (int) (cie.y * 65536);
        if (x > 65279) {
            x = 65279;
        }
        if (y > 65279) {
            y = 65279;
        }

        command.setColorX(x);
        command.setColorY(y);
        command.setTransitionTime((int) (time * 10));

        return networkManager.send(destination, command);
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

        final MoveToLevelWithOnOffCommand command = new MoveToLevelWithOnOffCommand();

        int l = (int) (level * 254);
        if (l > 254) {
            l = 254;
        }
        if (l < 0) {
            l = 0;
        }

        command.setLevel(l);
        command.setTransitionTime((int) (time * 10));

        return networkManager.send(destination, command);
    }

    /**
     * This command uses the WD capabilities to emit a quick audible/visible pulse called a "squawk".
     *
     * @param destination the {@link ZigBeeAddress}
     * @param mode the mode
     * @param strobe the strobe
     * @param level the level
     * @return the command result future
     */
    public Future<CommandResult> squawk(final ZigBeeAddress destination, final int mode, final int strobe,
            final int level) {
        final SquawkCommand command = new SquawkCommand();

        final int header = (level << 6) | (strobe << 4) | mode;

        command.setHeader(header);

        return networkManager.send(destination, command);
    }

    /**
     * Starts warning.
     *
     * @param destination the {@link ZigBeeAddress}
     * @param mode the mode
     * @param strobe the strobe
     * @param duration the duration
     * @return the command result future
     */
    public Future<CommandResult> warn(final ZigBeeAddress destination, final int mode, final int strobe,
            final int duration) {
        final StartWarningCommand command = new StartWarningCommand();

        final int header = (strobe << 4) | mode;
        command.setHeader(header);
        command.setWarningDuration(duration);

        return networkManager.send(destination, command);
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
     * Gets group memberships from device.
     *
     * @param device the device
     * @return the command result future
     */
    public Future<CommandResult> getGroupMemberships(final ZigBeeEndpoint device) {
        final GetGroupMembershipCommand command = new GetGroupMembershipCommand();

        command.setGroupCount(0);
        command.setGroupList(Collections.<Integer> emptyList());
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
