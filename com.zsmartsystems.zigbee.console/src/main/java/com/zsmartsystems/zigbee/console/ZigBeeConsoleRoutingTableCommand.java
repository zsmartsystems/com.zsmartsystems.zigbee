/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementRoutingResponse;
import com.zsmartsystems.zigbee.zdo.field.RoutingTable;

/**
 * Shows the routing table of a node
 *
 */
public class ZigBeeConsoleRoutingTableCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getCommand() {
        return "routingtable";
    }

    @Override
    public String getDescription() {
        return "Shows the routing table of a node.";
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
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) throws ExecutionException, InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeNode node = getNode(networkManager, args[1]);

        // Start index for the list is 0
        int startIndex = 0;
        int totalRoutes = 0;
        Set<RoutingTable> routes = new HashSet<RoutingTable>();
        do {
            final ManagementRoutingRequest routeRequest = new ManagementRoutingRequest(startIndex);
            routeRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));

            CommandResult response = networkManager.sendTransaction(routeRequest, routeRequest).get();
            final ManagementRoutingResponse routingResponse = response.getResponse();
            if (routingResponse == null) {
                out.println("Fail to request routing table");
                return;
            }

            if (routingResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                out.println("Routing table request not supported");
                return;
            } else if (routingResponse.getStatus() != ZdoStatus.SUCCESS) {
                out.println("Fail to request routing table");
                return;
            }

            // Save the routes
            routes.addAll(routingResponse.getRoutingTableList());

            // Continue with next request
            startIndex += routingResponse.getRoutingTableList().size();
            totalRoutes = routingResponse.getRoutingTableEntries();
        } while (startIndex < totalRoutes);

        String tableHeader = String.format("%-20s  %-20s  %-20s  %-20s  %-20s  %-20s",
            "Destination Address",
            "Status",
            "Memory Constrained",
            "Many To One",
            "Route Record Required",
            "Next Hop Address");

        out.println("Total routes: " + routes.size());
        out.println(tableHeader);

        for(RoutingTable route : routes) {
            printRoute(route, out);
        }
    }

    private void printRoute(RoutingTable route, PrintStream out) {
        out.println(String.format("%-20d  %-20s  %-20s  %-20s  %-20s  %-20d",
            route.getDestinationAddress(),
            route.getStatus(),
            route.isMemoryConstrained(),
            route.isManyToOne(),
            route.isRouteRecordRequired(),
            route.getNextHopAddress()));
    }
}
