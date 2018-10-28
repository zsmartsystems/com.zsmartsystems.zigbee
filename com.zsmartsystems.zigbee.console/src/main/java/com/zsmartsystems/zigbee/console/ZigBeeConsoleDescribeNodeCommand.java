/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleDescribeNodeCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "node";
    }

    @Override
    public String getDescription() {
        return "Provides detailed information about a node.";
    }

    @Override
    public String getSyntax() {
        return "NODEID";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeNode node = getNode(networkManager, args[1]);

        List<Integer> endpointIds = new ArrayList<Integer>();
        for (final ZigBeeEndpoint endpoint : node.getEndpoints()) {
            endpointIds.add(endpoint.getEndpointId());
        }
        Collections.sort(endpointIds);

        out.println("IEEE Address     : " + node.getIeeeAddress());
        out.println("Network Address  : " + node.getNetworkAddress());
        out.println("Node Descriptor  : " + node.getNodeDescriptor());
        out.println("Power Descriptor : " + node.getPowerDescriptor());
        out.println("Associations     : " + node.getAssociatedDevices().toString());
        out.println("Endpoints        : ");
        for (Integer endpointId : endpointIds) {
            ZigBeeEndpoint endpoint = node.getEndpoint(endpointId);
            out.print(String.format("            %-3d  : ", endpoint.getEndpointId()));
            outputEndpoint(out, endpoint);
        }
        out.println("Neighbors:");
        for (NeighborTable neighbor : node.getNeighbors()) {
            out.println(neighbor.toString());
        }
        out.println("Routes:");
        for (RoutingTable route : node.getRoutes()) {
            out.println(route.toString());
        }
    }

    private void outputEndpoint(PrintStream out, ZigBeeEndpoint endpoint) {
        out.println("Profile     " + String.format("%04X ", endpoint.getProfileId())
                + ZigBeeProfileType.getByValue(endpoint.getProfileId()));
        out.println("                 : Device Type " + String.format("%04X ", endpoint.getDeviceId())
                + com.zsmartsystems.zigbee.ZigBeeDeviceType.getByValue(endpoint.getDeviceId()).toString());
        for (Integer clusterId : endpoint.getInputClusterIds()) {
            out.println("                   -> " + ZclClusterType.getValueById(clusterId));
        }
        for (Integer clusterId : endpoint.getOutputClusterIds()) {
            out.println("                   <- " + ZclClusterType.getValueById(clusterId));
        }
    }
}