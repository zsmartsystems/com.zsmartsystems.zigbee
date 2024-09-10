/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.zsmartsystems.zigbee.ZigBeeDeviceType;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;

/**
 * Lists the devices in the network
 *
 * @author Chris Jackson
 * @author Henning Sudbrock - add manufacturer and model info
 */
public class ZigBeeConsoleNodeListCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getCommand() {
        return "nodes";
    }

    @Override
    public String getDescription() {
        return "Lists the known nodes in the network.";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        final Set<ZigBeeNode> nodes = networkManager.getNodes();
        final List<Integer> nodeIds = new ArrayList<>();

        for (ZigBeeNode node : nodes) {
            nodeIds.add(node.getNetworkAddress());
        }

        Collections.sort(nodeIds);
        String tableHeader = String.format("%-7s  %-4s  %-16s  %-12s  %-10s  %-3s  %-25s  %-35s  %-20s  %-15s",
                "Network", "Addr", "IEEE Address", "Logical Type", "State", "EP", "Profile", "Device Type",
                "Manufacturer", "Model");

        out.println("Total known nodes in network: " + nodes.size());
        out.println(tableHeader);

        for (Integer nodeId : nodeIds) {
            printNode(networkManager.getNode(nodeId), out);
        }
    }

    private void printNode(ZigBeeNode node, PrintStream out) {
        String nodeInfo = String.format("%7d  %04X  %-16s  %-12s  %-10s", node.getNetworkAddress(),
                node.getNetworkAddress(), node.getIeeeAddress(), node.getLogicalType(), node.getNodeState());
        String nodeInfoPadding = String.format("%7s  %4s  %16s  %12s  %10s", "", "", "", "", "");

        List<ZigBeeEndpoint> endpoints = new ArrayList<>(node.getEndpoints());
        Collections.sort(endpoints, (ep1, ep2) -> ep1.getEndpointId() - ep2.getEndpointId());

        boolean first = true;
        for (ZigBeeEndpoint endpoint : endpoints) {
            String profileType;
            if (ZigBeeProfileType.getByValue(endpoint.getProfileId()) == null) {
                profileType = String.format("%04X", endpoint.getProfileId());
            } else {
                profileType = ZigBeeProfileType.getByValue(endpoint.getProfileId()).toString();
            }
            String deviceType;
            if (ZigBeeDeviceType.getByValue(endpoint.getDeviceId()) == null
                    || ZigBeeProfileType.getByValue(endpoint.getProfileId()) == null) {
                deviceType = String.format("%04X", endpoint.getDeviceId());
            } else {
                deviceType = ZigBeeDeviceType
                        .getByValue(ZigBeeProfileType.getByValue(endpoint.getProfileId()), endpoint.getDeviceId())
                        .toString();
            }
            boolean showManufacturerAndModel = endpoint.getParentNode().getNetworkAddress() != 0;
            String endpointInfo = String.format("%3d  %-25s  %-35s  %-20s  %-15s", endpoint.getEndpointId(),
                    profileType, deviceType, showManufacturerAndModel ? getManufacturer(endpoint) : "",
                    showManufacturerAndModel ? getModel(endpoint) : "");

            String tableLine = String.format("%s %s", first ? nodeInfo : nodeInfoPadding, endpointInfo);
            out.println(tableLine);

            first = false;
        }

        // Print the node information if there are no known endpoints
        if (first) {
            out.println(nodeInfo);
        }
    }

    private String getManufacturer(ZigBeeEndpoint endpoint) {
        ZclBasicCluster cluster = getBasicCluster(endpoint);
        ZclAttribute attribute = cluster != null ? cluster.getAttribute(ZclBasicCluster.ATTR_MANUFACTURERNAME) : null;
        Object lastValue = attribute != null ? attribute.getLastValue() : null;
        return lastValue != null ? lastValue.toString() : "";
    }

    private String getModel(ZigBeeEndpoint endpoint) {
        ZclBasicCluster cluster = getBasicCluster(endpoint);
        ZclAttribute attribute = cluster != null ? cluster.getAttribute(ZclBasicCluster.ATTR_MODELIDENTIFIER) : null;
        Object lastValue = attribute != null ? attribute.getLastValue() : null;
        return lastValue != null ? lastValue.toString() : "";
    }

    private ZclBasicCluster getBasicCluster(ZigBeeEndpoint endpoint) {
        ZclCluster cluster = endpoint.getInputCluster(0);
        if (cluster instanceof ZclBasicCluster) {
            return (ZclBasicCluster) cluster;
        } else {
            return null;
        }
    }

}
