/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
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

        out.println("IEEE Address     : " + node.getIeeeAddress());
        out.println("Network Address  : " + node.getNetworkAddress());
        out.println("Node Descriptor  : " + node.getNodeDescriptor());
        out.println("Power Descriptor : " + node.getPowerDescriptor());
        out.println("Associations     : " + node.getAssociatedDevices().toString());
        out.println("Endpoints:");
        for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
            out.println(endpoint.toString());
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
}