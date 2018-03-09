/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupIfIdentifyingCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.AddGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveAllGroupsCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.RemoveGroupResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupCommand;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import javax.annotation.Generated;

/**
 * <b>Groups</b> cluster implementation (<i>Cluster ID 0x0004</i>).
 * <p>
 * The ZigBee specification provides the capability for group addressing. That is,
 * any endpoint on any device may be assigned to one or more groups, each labeled
 * with a 16-bit identifier (0x0001 – 0xfff7), which acts for all intents and purposes
 * like a network address. Once a group is established, frames, sent using the
 * APSDE-DATA.request primitive and having a DstAddrMode of 0x01, denoting
 * group addressing, will be delivered to every endpoint assigned to the group
 * address named in the DstAddr parameter of the outgoing APSDE-DATA.request
 * primitive on every device in the network for which there are such endpoints.
 * <p>
 * Management of group membership on each device and endpoint is implemented
 * by the APS, but the over-the-air messages that allow for remote management and
 * commissioning of groups are defined here in the cluster library on the theory that,
 * while the basic group addressing facilities are integral to the operation of the
 * stack, not every device will need or want to implement this management cluster.
 * Furthermore, the placement of the management commands here allows developers
 * of proprietary profiles to avoid implementing the library cluster but still exploit
 * group addressing
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ZclGroupsCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0004;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Groups";

    // Attribute initialisation
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<Integer, ZclAttribute>(0);

        return attributeMap;
    }

    /**
     * Default constructor to create a Groups cluster.
     *
     * @param zigbeeManager {@link ZigBeeNetworkManager}
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint}
     */
    public ZclGroupsCluster(final ZigBeeNetworkManager zigbeeManager, final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeManager, zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * The Add Group Command
     *
     * @param groupId {@link Integer} Group ID
     * @param groupName {@link String} Group Name
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> addGroupCommand(Integer groupId, String groupName) {
        AddGroupCommand command = new AddGroupCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setGroupName(groupName);

        return send(command);
    }

    /**
     * The View Group Command
     *
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> viewGroupCommand(Integer groupId) {
        ViewGroupCommand command = new ViewGroupCommand();

        // Set the fields
        command.setGroupId(groupId);

        return send(command);
    }

    /**
     * The Get Group Membership Command
     *
     * @param groupCount {@link Integer} Group count
     * @param groupList {@link List<Integer>} Group list
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getGroupMembershipCommand(Integer groupCount, List<Integer> groupList) {
        GetGroupMembershipCommand command = new GetGroupMembershipCommand();

        // Set the fields
        command.setGroupCount(groupCount);
        command.setGroupList(groupList);

        return send(command);
    }

    /**
     * The Remove Group Command
     *
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> removeGroupCommand(Integer groupId) {
        RemoveGroupCommand command = new RemoveGroupCommand();

        // Set the fields
        command.setGroupId(groupId);

        return send(command);
    }

    /**
     * The Remove All Groups Command
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> removeAllGroupsCommand() {
        RemoveAllGroupsCommand command = new RemoveAllGroupsCommand();

        return send(command);
    }

    /**
     * The Add Group If Identifying Command
     *
     * @param groupId {@link Integer} Group ID
     * @param groupName {@link String} Group Name
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> addGroupIfIdentifyingCommand(Integer groupId, String groupName) {
        AddGroupIfIdentifyingCommand command = new AddGroupIfIdentifyingCommand();

        // Set the fields
        command.setGroupId(groupId);
        command.setGroupName(groupName);

        return send(command);
    }

    /**
     * The Add Group Response
     *
     * @param status {@link Integer} Status
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> addGroupResponse(Integer status, Integer groupId) {
        AddGroupResponse command = new AddGroupResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);

        return send(command);
    }

    /**
     * The View Group Response
     *
     * @param status {@link Integer} Status
     * @param groupId {@link Integer} Group ID
     * @param groupName {@link String} Group Name
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> viewGroupResponse(Integer status, Integer groupId, String groupName) {
        ViewGroupResponse command = new ViewGroupResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);
        command.setGroupName(groupName);

        return send(command);
    }

    /**
     * The Get Group Membership Response
     *
     * @param capacity {@link Integer} Capacity
     * @param groupCount {@link Integer} Group count
     * @param groupList {@link List<Integer>} Group list
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getGroupMembershipResponse(Integer capacity, Integer groupCount, List<Integer> groupList) {
        GetGroupMembershipResponse command = new GetGroupMembershipResponse();

        // Set the fields
        command.setCapacity(capacity);
        command.setGroupCount(groupCount);
        command.setGroupList(groupList);

        return send(command);
    }

    /**
     * The Remove Group Response
     *
     * @param status {@link Integer} Status
     * @param groupId {@link Integer} Group ID
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> removeGroupResponse(Integer status, Integer groupId) {
        RemoveGroupResponse command = new RemoveGroupResponse();

        // Set the fields
        command.setStatus(status);
        command.setGroupId(groupId);

        return send(command);
    }

    @Override
    public ZclCommand getCommandFromId(int commandId) {
        switch (commandId) {
            case 0: // ADD_GROUP_COMMAND
                return new AddGroupCommand();
            case 1: // VIEW_GROUP_COMMAND
                return new ViewGroupCommand();
            case 2: // GET_GROUP_MEMBERSHIP_COMMAND
                return new GetGroupMembershipCommand();
            case 3: // REMOVE_GROUP_COMMAND
                return new RemoveGroupCommand();
            case 4: // REMOVE_ALL_GROUPS_COMMAND
                return new RemoveAllGroupsCommand();
            case 5: // ADD_GROUP_IF_IDENTIFYING_COMMAND
                return new AddGroupIfIdentifyingCommand();
            default:
                return null;
        }
    }

    @Override
    public ZclCommand getResponseFromId(int commandId) {
        switch (commandId) {
            case 0: // ADD_GROUP_RESPONSE
                return new AddGroupResponse();
            case 1: // VIEW_GROUP_RESPONSE
                return new ViewGroupResponse();
            case 2: // GET_GROUP_MEMBERSHIP_RESPONSE
                return new GetGroupMembershipResponse();
            case 3: // REMOVE_GROUP_RESPONSE
                return new RemoveGroupResponse();
            default:
                return null;
        }
    }
}
