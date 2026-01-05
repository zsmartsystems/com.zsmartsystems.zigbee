/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import com.zsmartsystems.zigbee.*;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiRequest;
import com.zsmartsystems.zigbee.zdo.command.ManagementLqiResponse;
import com.zsmartsystems.zigbee.zdo.field.NeighborTable;

import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Lists the neighbors of a node
 *
 */
public class ZigBeeConsoleNeighborsListCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getCommand() {
        return "neighbors";
    }

    @Override
    public String getDescription() {
        return "Lists the neighbors of a node.";
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
        int totalNeighbors = 0;
        Set<NeighborTable> neighbors = new HashSet<NeighborTable>();
        do {
            final ManagementLqiRequest neighborRequest = new ManagementLqiRequest(startIndex);
            neighborRequest.setDestinationAddress(new ZigBeeEndpointAddress(node.getNetworkAddress()));

            CommandResult response = networkManager.sendTransaction(neighborRequest, neighborRequest).get();
            final ManagementLqiResponse neighborResponse = response.getResponse();
            if (neighborResponse == null) {
                out.println("Fail to request neighbors table");
                return;
            }

            if (neighborResponse.getStatus() == ZdoStatus.NOT_SUPPORTED) {
                out.println("Request neighbors table not supported");
                return;
            } else if (neighborResponse.getStatus() != ZdoStatus.SUCCESS) {
                out.println("Fail to request neighbors table");
                return;
            }

            // Some devices may report the number of entries as the total number they can maintain.
            // To avoid a loop, we need to check if there's any response.
            if (neighborResponse.getNeighborTableList().size() == 0) {
                break;
            }

            // Save the neighbors
            neighbors.addAll(neighborResponse.getNeighborTableList());

            // Continue with next request
            startIndex += neighborResponse.getNeighborTableList().size();
            totalNeighbors = neighborResponse.getNeighborTableEntries();
        } while (startIndex < totalNeighbors);

        String tableHeader = String.format("%-20s  %-20s  %-20s  %-20s  %-15s  %-9s  %-12s  %-12s  %-12s",
            "Extended PAN ID",
            "Extended Address",
            "Network Address",
            "Device Type",
            "RX On When Idle",
            "Relationship",
            "Permit Joining",
            "Depth",
            "LQI");

        out.println("Total neighbors: " + neighbors.size());
        out.println(tableHeader);

        for(NeighborTable neighborTable : neighbors) {
            printNeighborTable(neighborTable, out);
        }
    }

    private void printNeighborTable(NeighborTable neighborTable, PrintStream out) {
        out.println(String.format("%-20s  %-20s  %20d  %-20s  %15s  %9s  %12s  %12d  %12d",
            neighborTable.getExtendedPanId().toString(),
            neighborTable.getExtendedAddress().toString(),
            neighborTable.getNetworkAddress(),
            neighborTable.getDeviceType(),
            neighborTable.getRxOnWhenIdle(),
            neighborTable.getRelationship(),
            neighborTable.getPermitJoining(),
            neighborTable.getDepth(),
            neighborTable.getLqi()));
    }
}
