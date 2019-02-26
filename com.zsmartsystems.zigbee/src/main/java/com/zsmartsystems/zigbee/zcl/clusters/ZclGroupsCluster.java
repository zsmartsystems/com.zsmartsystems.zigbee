/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
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
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * <b>Groups</b> cluster implementation (<i>Cluster ID 0x0004</i>).
 * <p>
 * The ZigBee specification provides the capability for group addressing. That is, any
 * endpoint on any device may be assigned to one or more groups, each labeled with a 16-bit
 * identifier (0x0001 – 0xfff7), which acts for all intents and purposes like a network
 * address. Once a group is established, frames, sent using the APSDE-DATA.request primitive
 * and having a DstAddrMode of 0x01, denoting group addressing, will be delivered to every
 * endpoint assigned to the group address named in the DstAddr parameter of the outgoing
 * APSDE-DATA.request primitive on every device in the network for which there are such
 * endpoints.
 * <p>
 * Management of group membership on each device and endpoint is implemented by the APS, but the
 * over-the-air messages that allow for remote management and commissioning of groups are
 * defined here in the cluster library on the theory that, while the basic group addressing
 * facilities are integral to the operation of the stack, not every device will need or want to
 * implement this management cluster. Furthermore, the placement of the management commands
 * here allows developers of proprietary profiles to avoid implementing the library cluster
 * but still exploit group addressing
 * <p>
 * In order to ensure that only authorized devices are able to set up groups (particularly if
 * application link keys are to be used) the following approach should be employed. The
 * security Permissions Configuration Table provides a mechanism by which certain commands
 * can be restricted to specified authorized devices. Configuration of groups via the Groups
 * cluster should use the ApplicationSettings permissions entry of this table to specify from
 * which devices group configuration commands may be received, and whether a link key is
 * required.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-26T20:57:36Z")
public class ZclGroupsCluster extends ZclCluster {
    /**
     * The ZigBee Cluster Library Cluster ID
     */
    public static final int CLUSTER_ID = 0x0004;

    /**
     * The ZigBee Cluster Library Cluster Name
     */
    public static final String CLUSTER_NAME = "Groups";

    // Attribute constants
    /**
     * The most significant bit of the NameSupport attribute indicates whether or not group
     * names are supported. A value of 1 indicates that they are supported, and a value of 0
     * indicates that they are not supported.
     */
    public static final int ATTR_NAMESUPPORT = 0x0000;

    @Override
    protected Map<Integer, ZclAttribute> initializeAttributes() {
        Map<Integer, ZclAttribute> attributeMap = new ConcurrentHashMap<>(1);

        attributeMap.put(ATTR_NAMESUPPORT, new ZclAttribute(ZclClusterType.GROUPS, ATTR_NAMESUPPORT, "Name Support", ZclDataType.BITMAP_8_BIT, true, true, false, false));

        return attributeMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeServerCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(4);

        commandMap.put(0x0000, AddGroupResponse.class);
        commandMap.put(0x0001, ViewGroupResponse.class);
        commandMap.put(0x0002, GetGroupMembershipResponse.class);
        commandMap.put(0x0003, RemoveGroupResponse.class);

        return commandMap;
    }

    @Override
    protected Map<Integer, Class<? extends ZclCommand>> initializeClientCommands() {
        Map<Integer, Class<? extends ZclCommand>> commandMap = new ConcurrentHashMap<>(6);

        commandMap.put(0x0000, AddGroupCommand.class);
        commandMap.put(0x0001, ViewGroupCommand.class);
        commandMap.put(0x0002, GetGroupMembershipCommand.class);
        commandMap.put(0x0003, RemoveGroupCommand.class);
        commandMap.put(0x0004, RemoveAllGroupsCommand.class);
        commandMap.put(0x0005, AddGroupIfIdentifyingCommand.class);

        return commandMap;
    }

    /**
     * Default constructor to create a Groups cluster.
     *
     * @param zigbeeEndpoint the {@link ZigBeeEndpoint} this cluster is contained within
     */
    public ZclGroupsCluster(final ZigBeeEndpoint zigbeeEndpoint) {
        super(zigbeeEndpoint, CLUSTER_ID, CLUSTER_NAME);
    }

    /**
     * Get the <i>Name Support</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The most significant bit of the NameSupport attribute indicates whether or not group
     * names are supported. A value of 1 indicates that they are supported, and a value of 0
     * indicates that they are not supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> getNameSupportAsync() {
        return read(attributes.get(ATTR_NAMESUPPORT));
    }

    /**
     * Synchronously get the <i>Name Support</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The most significant bit of the NameSupport attribute indicates whether or not group
     * names are supported. A value of 1 indicates that they are supported, and a value of 0
     * indicates that they are not supported.
     * <p>
     * This method can return cached data if the attribute has already been received.
     * The parameter <i>refreshPeriod</i> is used to control this. If the attribute has been received
     * within <i>refreshPeriod</i> milliseconds, then the method will immediately return the last value
     * received. If <i>refreshPeriod</i> is set to 0, then the attribute will always be updated.
     * <p>
     * This method will block until the response is received or a timeout occurs unless the current value is returned.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param refreshPeriod the maximum age of the data (in milliseconds) before an update is needed
     * @return the {@link Integer} attribute value, or null on error
     */
    public Integer getNameSupport(final long refreshPeriod) {
        if (attributes.get(ATTR_NAMESUPPORT).isLastValueCurrent(refreshPeriod)) {
            return (Integer) attributes.get(ATTR_NAMESUPPORT).getLastValue();
        }

        return (Integer) readSync(attributes.get(ATTR_NAMESUPPORT));
    }

    /**
     * Set reporting for the <i>Name Support</i> attribute [attribute ID <b>0x0000</b>].
     * <p>
     * The most significant bit of the NameSupport attribute indicates whether or not group
     * names are supported. A value of 1 indicates that they are supported, and a value of 0
     * indicates that they are not supported.
     * <p>
     * The attribute is of type {@link Integer}.
     * <p>
     * The implementation of this attribute by a device is MANDATORY
     *
     * @param minInterval minimum reporting period
     * @param maxInterval maximum reporting period
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> setNameSupportReporting(final int minInterval, final int maxInterval) {
        return setReporting(attributes.get(ATTR_NAMESUPPORT), minInterval, maxInterval);
    }

    /**
     * The Add Group Command
     * <p>
     * The Add Group command allows the sending device to add group membership in a particular
     * group for one or more endpoints on the receiving device.
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
     * <p>
     * The view group command allows the sending device to request that the receiving entity or
     * entities respond with a view group response command containing the application name
     * string for a particular group.
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
     * <p>
     * The get group membership command allows the sending device to inquire about the group
     * membership of the receiving device and endpoint in a number of ways.
     *
     * @param groupCount {@link Integer} Group Count
     * @param groupList {@link List<Integer>} Group List
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
     * <p>
     * The remove group command allows the sender to request that the receiving entity or
     * entities remove their membership, if any, in a particular group.
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
     * <p>
     * The remove all groups command allows the sending device to direct the receiving entity
     * or entities to remove all group associations.
     *
     * @return the {@link Future<CommandResult>} command result future
     */
    public Future<CommandResult> removeAllGroupsCommand() {
        return send(new RemoveAllGroupsCommand());
    }

    /**
     * The Add Group If Identifying Command
     * <p>
     * The add group if identifying command allows the sending device to add group membership
     * in a particular group for one or more endpoints on the receiving device, on condition
     * that it is identifying itself. Identifying functionality is controlled using the
     * identify cluster.
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
     * <p>
     * The add group response is sent by the groups cluster server in response to an add group
     * command.
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
     * <p>
     * The view group response command is sent by the groups cluster server in response to a view
     * group command.
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
     * <p>
     * The get group membership response command is sent by the groups cluster server in
     * response to a get group membership command.
     *
     * @param capacity {@link Integer} Capacity
     * @param groupCount {@link Integer} Group Count
     * @param groupList {@link List<Integer>} Group List
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
     * <p>
     * The remove group response command is generated by an application entity in response to
     * the receipt of a remove group command.
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
}
