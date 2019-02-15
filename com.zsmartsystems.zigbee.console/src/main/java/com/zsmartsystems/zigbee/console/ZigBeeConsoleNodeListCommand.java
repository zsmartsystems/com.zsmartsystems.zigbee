/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
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
        return "nodelist";
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
        String tableHeader = String.format("%7s | %4s | %17s | %13s | %3s | %25s | %15s | %15s | %15s", "Network",
                "Addr", "IEEE Address", "Logical Type", "EP", "Profile", "Device Type", "Manufacturer", "Model");
        out.println(tableHeader);
        out.println(createPadding(tableHeader.length(), '-'));

        for (Integer nodeId : nodeIds) {
            printNode(networkManager.getNode(nodeId), out);
            out.println(createPadding(tableHeader.length(), '-'));
        }
    }

    private void printNode(ZigBeeNode node, PrintStream out) {
        String nodeInfo = String.format("%7d | %04X | %17s | %13s |", node.getNetworkAddress(),
                node.getNetworkAddress(), node.getIeeeAddress(), node.getLogicalType());
        String nodeInfoPadding = String.format("%7s | %4s | %17s | %13s |", "", "", "", "");

        List<ZigBeeEndpoint> endpoints = new ArrayList<>(node.getEndpoints());
        Collections.sort(endpoints, (ep1, ep2) -> ep1.getEndpointId() - ep2.getEndpointId());

        boolean first = true;
        for (ZigBeeEndpoint endpoint : endpoints) {
            boolean showManufacturerAndModel = endpoint.getParentNode().getNetworkAddress() != 0;
            String endpointInfo = String.format("%3d | %25s | %15s | %15s | %15s", endpoint.getEndpointId(),
                    ZigBeeProfileType.getByValue(endpoint.getProfileId()),
                    ZigBeeDeviceType.getByValue(endpoint.getDeviceId()),
                    showManufacturerAndModel ? getManufacturer(endpoint) : "",
                    showManufacturerAndModel ? getModel(endpoint) : "");

            String tableLine = String.format("%s %s", first ? nodeInfo : nodeInfoPadding, endpointInfo);
            out.println(tableLine);

            first = false;
        }
    }

    private String getManufacturer(ZigBeeEndpoint endpoint) {
        ZclBasicCluster cluster = getBasicCluster(endpoint);
        return cluster != null ? cluster.getManufacturerName(Long.MAX_VALUE) : "";
    }

    private String getModel(ZigBeeEndpoint endpoint) {
        ZclBasicCluster cluster = getBasicCluster(endpoint);
        return cluster != null ? cluster.getModelIdentifier(Long.MAX_VALUE) : "";
    }

    private ZclBasicCluster getBasicCluster(ZigBeeEndpoint endpoint) {
        ZclCluster cluster = endpoint.getInputCluster(0);
        if (cluster instanceof ZclBasicCluster) {
            return (ZclBasicCluster) cluster;
        } else {
            return null;
        }
    }

    private String createPadding(int length, char padChar) {
        return String.format("%0" + length + "d", 0).replace('0', padChar);
    }
}
