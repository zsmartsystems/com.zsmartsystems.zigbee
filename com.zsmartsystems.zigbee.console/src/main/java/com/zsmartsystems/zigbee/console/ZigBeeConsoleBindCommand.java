/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.groups.ZigBeeGroup;
import com.zsmartsystems.zigbee.zcl.ZclCluster;

/**
 * Adds a binding between two devices
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleBindCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "bind";
    }

    @Override
    public String getDescription() {
        return "Binds a device to another device. If destination is not specified, the coordinator will be used.";
    }

    @Override
    public String getSyntax() {
        return "CLUSTER SOURCE-ENDPOINT [DESTINATION-ENDPOINT]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 3 || args.length > 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        String clusterSpecParam = args[1];
        String sourceEndpointParam = args[2];
        String destEndpointParam = (args.length == 4) ? args[3] : null;

        final ZigBeeEndpoint sourceEndpoint = getEndpoint(networkManager, sourceEndpointParam);
        ZclCluster cluster = getCluster(sourceEndpoint, clusterSpecParam);

        CommandResult response;
        if (destEndpointParam != null) {
            ZigBeeGroup group = getGroup(networkManager, destEndpointParam);
            if (group != null) {
                response = cluster.bind(group.getAddress()).get();
            } else {
                ZigBeeEndpoint destination = getEndpoint(networkManager, destEndpointParam);
                response = cluster.bind(destination.getIeeeAddress(), destination.getEndpointId()).get();
            }
        } else {
            response = cluster.bind(networkManager.getNode(0).getIeeeAddress(), 1).get();
        }

        processDefaultResponse(response, out);
    }
}
