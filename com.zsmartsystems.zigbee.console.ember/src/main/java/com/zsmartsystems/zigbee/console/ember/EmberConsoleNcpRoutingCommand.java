/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberRouteTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberSourceRouteTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;

/**
 * Provides an interface to the NCP routing information
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpRoutingCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncprouting";
    }

    @Override
    public String getDescription() {
        return "Read NCP routing information";
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
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        Map<Integer, String> concentratorType = new HashMap<>();
        concentratorType.put(0, "NONE");
        concentratorType.put(1, "LOW ");
        concentratorType.put(2, "HIGH");

        Map<Integer, String> routeStatus = new HashMap<>();
        routeStatus.put(0, "NONE       ");
        routeStatus.put(1, "DISCOVERING");
        routeStatus.put(2, "RECEIVED   ");

        EmberNcp ncp = getEmberNcp(networkManager);

        int sourceRouteSize = ncp.getSourceRouteTableTotalSize();
        int sourceRouteFill = ncp.getSourceRouteTableFilledSize();

        int configDiscoveryTableSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_DISCOVERY_TABLE_SIZE);
        int configRouteTableSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_ROUTE_TABLE_SIZE);
        int configSourceRouteTableSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_SOURCE_ROUTE_TABLE_SIZE);

        Map<Integer, EmberRouteTableEntry> routeTableMap = new TreeMap<>();
        for (int cnt = 0; cnt < configRouteTableSize; cnt++) {
            EmberRouteTableEntry route = ncp.getRouteTableEntry(cnt);
            routeTableMap.put(route.getDestination(), route);
        }

        List<EmberSourceRouteTableEntry> sourceRouteTable = new ArrayList<>();
        for (int cnt = 0; cnt < sourceRouteFill; cnt++) {
            EmberSourceRouteTableEntry route = ncp.getSourceRouteTableEntry(cnt);
            sourceRouteTable.add(route);
        }

        out.println("Discovery Table Size Config    : " + configDiscoveryTableSize);
        out.println("Route Table Size Config        : " + configRouteTableSize);
        out.println("Source Route Table Size Config : " + configSourceRouteTableSize);
        out.println("Source Route Table Size Usage  : " + sourceRouteFill + "/" + sourceRouteSize);

        out.println();
        out.println("NCP Route Table :");
        out.println();
        out.println(String.format("%-15s  %-15s  %-6s  %-15s  %-15s", "Destination", "Next", "Age", "Concentrator", "Status"));
        for (EmberRouteTableEntry route : routeTableMap.values()) {
            out.println(String.format("%-15s  %-15s  %-6s  %-15s  %-15s",
                String.format("%04X (%5d)", route.getDestination(), route.getDestination()),
                String.format("%04X (%5d)", route.getNextHop(), route.getNextHop()),
                String.format("%04X", route.getAge()),
                concentratorType.get(route.getConcentratorType()),
                route.getConcentratorType() == 2 ? (routeStatus.get(route.getStatus()) == null ? "" : routeStatus.get(route.getStatus())) : ""));
        }

        out.println();
        out.println("NCP Source Route Table :");
        out.println();
        out.println(String.format("%-6s  %-15s  %-10s  |  %s", "IDX", "Destination", "Closer IDX", "Route"));
        for (int i = 0; i < sourceRouteTable.size(); ++i) {
            EmberSourceRouteTableEntry entry = sourceRouteTable.get(i);
            if (entry == null) {
                continue;
            }

            List<Integer> route = new ArrayList<>();
            walkTheRoute(sourceRouteTable, route, i);

            out.println(String.format("%6s  %-15s  %-10s  |  %s",
                "#" + i,
                String.format("%04X (%5d)", entry.getDestination(), entry.getDestination()),
                entry.getCloserIndex() != 255 ? "#" + entry.getCloserIndex() : "NCP",
                printRoute(route)));
        }
    }

    private void walkTheRoute(List<EmberSourceRouteTableEntry> sourceRouteTable, List<Integer> route,
            int index) {
        EmberSourceRouteTableEntry routeTable = sourceRouteTable.get(index);
        route.add(routeTable.getDestination());
        if (routeTable.getCloserIndex() == -1 || routeTable.getCloserIndex() == 255) {
            return;
        }

        walkTheRoute(sourceRouteTable, route, routeTable.getDestination());
    }

    private String printRoute(List<Integer> route) {
        StringBuilder builder = new StringBuilder();
        for (int cnt = route.size() - 1; cnt >= 0; cnt--) {
            builder.append(String.format("%04X ", route.get(cnt)));
        }
        return builder.toString();
    }
}
