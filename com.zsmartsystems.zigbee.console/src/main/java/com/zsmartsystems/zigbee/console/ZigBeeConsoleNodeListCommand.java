/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.ZigBeeProfileType;

/**
 * Lists the devices in the network
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNodeListCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getDescription() {
        return "Lists the known devices in the network.";
    }

    @Override
    public String getSyntax() {
        return "devicelist";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public boolean process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) throws Exception {
        final Set<ZigBeeNode> nodes = networkManager.getNodes();
        final List<Integer> nodeIds = new ArrayList<Integer>();

        for (ZigBeeNode node : nodes) {
            nodeIds.add(node.getNetworkAddress());
        }

        Collections.sort(nodeIds);
        out.println("Network Addr  IEEE Address      Device Type  EP   Profile");
        for (Integer nodeId : nodeIds) {
            ZigBeeNode node = networkManager.getNode(nodeId);
            out.print(String.format("%-6d  %04X  %s  %-11s", node.getNetworkAddress(), node.getNetworkAddress(),
                    node.getIeeeAddress(), node.getLogicalType()));
            List<Integer> endpointIds = new ArrayList<Integer>();
            for (final ZigBeeEndpoint endpoint : node.getEndpoints()) {
                endpointIds.add(endpoint.getEndpointId());
            }
            Collections.sort(endpointIds);
            boolean first = true;
            for (Integer endpointId : endpointIds) {
                if (!first) {
                    out.print("                                             ");
                }
                first = false;
                ZigBeeEndpoint endpoint = node.getEndpoint(endpointId);
                out.println(String.format("  %-3d  %s", endpoint.getEndpointId(),
                        ZigBeeProfileType.getProfileType(endpoint.getProfileId())));
            }
            if (first) {
                out.println();
            }
        }
        return true;
    }
}
