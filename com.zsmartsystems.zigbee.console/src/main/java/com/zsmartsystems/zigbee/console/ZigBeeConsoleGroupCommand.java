/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.groups.ZigBeeGroup;
import com.zsmartsystems.zigbee.groups.ZigBeeGroupResponse;
import com.zsmartsystems.zigbee.groups.ZigBeeNetworkGroupManager;
import com.zsmartsystems.zigbee.groups.ZigBeeNetworkGroupManager.GroupSynchronizationMethod;

/**
 * Manage groups
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleGroupCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "group";
    }

    @Override
    public String getDescription() {
        return "Manage groups";
    }

    @Override
    public String getSyntax() {
        return "READ|SYNC|CREATE|DELETE|ADD|REMOVE [GROUPID|ENDPOINT|NAME]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length == 1) {
            printGroups(out, networkManager.getGroups());
            return;
        }

        ZigBeeNetworkGroupManager groupManager = networkManager.getGroupManager();

        switch (args[1].toUpperCase()) {
            case "READ":
                cmdRead(networkManager, groupManager, args, out);
                break;
            case "SYNC":
                cmdSync(networkManager, groupManager, args, out);
                break;
            case "CREATE":
                cmdCreate(networkManager, args, out);
                break;
            case "DELETE":
                cmdDelete(networkManager, args, out);
                break;
            case "ADD":
                cmdAdd(networkManager, args, out);
                break;
            case "REMOVE":
                cmdRemove(networkManager, args, out);
                break;
            default:
                throw new IllegalArgumentException("Unknown option " + args[1].toUpperCase());
        }
    }

    private void printGroups(PrintStream out, Collection<ZigBeeGroup> groups) {
        out.println("Group  ID    Label             | Members [hex]");
        for (ZigBeeGroup group : groups) {
            out.print(String.format("%5d  %04X  %-16s  |", group.getGroupId(), group.getGroupId(),
                    group.getLabel() == null ? "" : group.getLabel()));
            for (ZigBeeEndpoint member : group.getMembers()) {
                out.print(" " + member.getEndpointAddress().toString());
            }
            out.println();
        }
    }

    private void cmdRead(ZigBeeNetworkManager networkManager, ZigBeeNetworkGroupManager groupManager, String[] args,
            PrintStream out) throws InterruptedException, ExecutionException {
        Map<ZigBeeEndpointAddress, ZigBeeGroupResponse> response;
        if (args.length >= 3) {
            ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[2]);
            ZigBeeGroupResponse endpointResponse = groupManager.getGroups(endpoint).get();
            response = new HashMap<>();
            response.put(endpoint.getEndpointAddress(), endpointResponse);
        } else {
            response = groupManager.getGroups().get();
        }

        if (response == null || response.isEmpty()) {
            out.println("No data returned from group command");
            return;
        }
        for (Entry<ZigBeeEndpointAddress, ZigBeeGroupResponse> endpoints : response.entrySet()) {
            ZigBeeEndpointAddress address = endpoints.getKey();
            Map<Integer, String> groups = endpoints.getValue().getGroups();
            out.println("Groups for endpoint " + address + ":");
            out.println("    Response        : " + endpoints.getValue().getStatus());
            out.println("    Number of groups: " + groups.size() + '/' + endpoints.getValue().getCapacity());
            for (Entry<Integer, String> groupResponse : groups.entrySet()) {
                out.println(String.format("    Group %04X, %s", groupResponse.getKey(), groupResponse.getValue()));
            }
        }
    }

    private void cmdCreate(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        int groupId = parseInteger(args[2]);

        ZigBeeGroup group = networkManager.addGroup(groupId);

        if (args.length >= 4) {
            group.setLabel(args[3]);
        }

        out.println("Created " + group);
    }

    private void cmdSync(ZigBeeNetworkManager networkManager, ZigBeeNetworkGroupManager groupManager, String[] args,
            PrintStream out) {
        GroupSynchronizationMethod method = GroupSynchronizationMethod.GET;
        if (args.length > 2) {
            method = GroupSynchronizationMethod.valueOf(args[2]);
        }

        ZigBeeStatus status;
        try {
            status = groupManager.synchronize(method).get();
            out.println("Group sysnchronization with mode " + method + " completed with status " + status);
            printGroups(out, groupManager.getAll());
        } catch (InterruptedException | ExecutionException e) {
            out.println("Group sysnchronization with mode " + method + " failed!");
        }
    }

    private void cmdDelete(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        int groupId = parseInteger(args[2]);

        ZigBeeGroup group = networkManager.deleteGroup(groupId);
        if (group == null) {
            out.println("Group " + groupId + " was not found.");
            return;
        }
        out.println("Deleted " + group);
    }

    private void cmdAdd(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        int groupId = parseInteger(args[2]);
        ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[3]);

        ZigBeeGroup group = networkManager.getGroup(groupId);
        if (group == null) {
            out.println("Group " + groupId + " was not found.");
            return;
        }
        group.addMember(endpoint);
        out.println("Group updated " + group);
    }

    private void cmdRemove(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        int groupId = parseInteger(args[2]);
        ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[3]);

        ZigBeeGroup group = networkManager.getGroup(groupId);
        if (group == null) {
            out.println("Group " + groupId + " was not found.");
            return;
        }
        group.removeMember(endpoint);
        out.println("Group updated " + group);
    }

}
