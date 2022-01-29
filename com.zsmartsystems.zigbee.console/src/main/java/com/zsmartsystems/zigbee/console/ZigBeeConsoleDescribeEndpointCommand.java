/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeDeviceType;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclGroupsCluster;
import com.zsmartsystems.zigbee.zcl.clusters.groups.GetGroupMembershipResponse;
import com.zsmartsystems.zigbee.zcl.clusters.groups.ViewGroupResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleDescribeEndpointCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "endpoint";
    }

    @Override
    public String getDescription() {
        return "Provides detailed information about an endpoint.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);

        ZigBeeProfileType profile = ZigBeeProfileType.getByValue(endpoint.getProfileId());
        ZigBeeDeviceType device = ZigBeeDeviceType.getByValue(ZigBeeProfileType.getByValue(endpoint.getProfileId()),
                endpoint.getDeviceId());

        Map<Integer, String> groups = new TreeMap<>();
        GetGroupMembershipResponse groupMembership = null;
        if (endpoint.getParentNode().isFullFuntionDevice()) {
            // Groups really only work for FFDs as the group commands are sent to the RX On when idle broadcast address
            ZclGroupsCluster groupsCluster = (ZclGroupsCluster) endpoint.getInputCluster(ZclGroupsCluster.CLUSTER_ID);
            if (groupsCluster != null) {
                CommandResult groupsResponse = groupsCluster.getGroupMembershipCommand(Collections.emptyList()).get();
                if (groupsResponse.getResponse() instanceof GetGroupMembershipResponse) {
                    groupMembership = groupsResponse.getResponse();

                    for (int groupId : groupMembership.getGroupList()) {
                        Future<CommandResult> groupFuture = groupsCluster.viewGroupCommand(groupId);
                        CommandResult groupResult = groupFuture.get();
                        String label;
                        if (groupResult.isSuccess() && groupResult.getResponse() instanceof ViewGroupResponse
                                && ((ViewGroupResponse) groupResult.getResponse()).getGroupName() != null) {
                            label = ((ViewGroupResponse) groupResult.getResponse()).getGroupName();
                        } else {
                            label = "";
                        }
                        groups.put(groupId, label);
                    }
                }
            }
        }

        out.println("IEEE Address      : " + endpoint.getIeeeAddress());
        out.println("Network Address   : " + endpoint.getParentNode().getNetworkAddress());
        out.println("Endpoint          : " + endpoint.getEndpointId());
        out.println("Device Profile    : " + String.format("0x%04X, ", endpoint.getProfileId())
                + (profile == null ? "Unknown" : profile.toString()));
        out.println("Device Type       : " + String.format("0x%04X, ", endpoint.getDeviceId())
                + (device == null ? "Unknown" : device.toString()));
        out.println("Device Version    : " + endpoint.getDeviceVersion());
        out.println("Input Clusters    : (Server)");
        printClusters(endpoint, true, out);
        out.println("Output Clusters   : (Client)");
        printClusters(endpoint, false, out);

        if (groupMembership != null) {
            if (groupMembership.getCapacity() < 0xFE) {
                out.println("Groups Supported  : "
                        + (groupMembership.getGroupList().size() + groupMembership.getCapacity()));
            }
            out.println("Groups Configured : " + groupMembership.getGroupList().size());
            for (Entry<Integer, String> group : groups.entrySet()) {
                out.println("                  : " + String.format("%04X  %s", group.getKey(), group.getValue()));
            }
        }
    }

    private void printClusters(final ZigBeeEndpoint endpoint, final boolean input, final PrintStream out) {
        Collection<Integer> clusters;
        if (input) {
            clusters = endpoint.getInputClusterIds();
        } else {
            clusters = endpoint.getOutputClusterIds();
        }

        Map<Integer, ZclCluster> clusterTree = new TreeMap<Integer, ZclCluster>();
        for (Integer clusterId : clusters) {
            ZclCluster cluster;
            if (input) {
                cluster = endpoint.getInputCluster(clusterId);
            } else {
                cluster = endpoint.getOutputCluster(clusterId);
            }
            clusterTree.put(cluster.getClusterId(), cluster);
        }

        for (ZclCluster cluster : clusterTree.values()) {
            out.println("   " + printClusterId(cluster.getClusterId()) + " " + cluster.getClusterName());
            out.println("     - APS Security " + (cluster.getApsSecurityRequired() ? "en" : "dis") + "abled");
            printAttributes(cluster, out);
        }
    }

    private void printAttributes(final ZclCluster cluster, final PrintStream out) {
        Map<Integer, ZclAttribute> attributeTree = new TreeMap<Integer, ZclAttribute>();
        for (ZclAttribute attribute : cluster.getAttributes()) {
            attributeTree.put(attribute.getId(), attribute);
        }

        for (ZclAttribute attribute : attributeTree.values()) {
            out.println(String.format("        %s   %5d %s%s%s %s %-40s %s %s",
                    (cluster.getSupportedAttributes().contains(attribute.getId()) ? "S" : "U"), attribute.getId(),
                    (attribute.isReadable() ? "r" : "-"), (attribute.isWritable() ? "w" : "-"),
                    (attribute.isReportable() ? "s" : "-"), printZclDataType(attribute.getDataType()),
                    attribute.getName(),
                    (attribute.getLastValue() == null ? "" : attribute.getLastReportTime().getTime()),
                    (attribute.getLastValue() == null ? "" : attribute.getLastValue())));
        }
    }
}
