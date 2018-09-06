/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclCluster;

/**
 * Adds a binding between two devices
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleBindCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        ZigBeeConsoleArgument option = ZigBeeConsoleArgumentBuilder.create(ZigBeeConsoleArgumentType.ENDPOINT)
                .withName("SOURCE-ENDPOINT").withDescription("The source endpoint to bind to").build();
        option.chain(ZigBeeConsoleArgumentBuilder.create(ZigBeeConsoleArgumentType.CLUSTER)
                .withDescription("The cluster to bind to").build());
        option.chain(ZigBeeConsoleArgumentBuilder.create(ZigBeeConsoleArgumentType.ENDPOINT)
                .withName("DESTINATION-ENDPOINT")
                .withDescription("The destination endpoint to bind to (defaults to local node)").isOptional().build());

        return option;
    }

    @Override
    public String getCommand() {
        return "bind";
    }

    @Override
    public String getDescription() {
        return "Binds a device to another device. If destination is not specified, the local node will be used.";
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

        IeeeAddress destAddress;
        int destEndpoint;
        if (destEndpointParam != null) {
            ZigBeeEndpoint destination = getEndpoint(networkManager, destEndpointParam);
            destAddress = destination.getIeeeAddress();
            destEndpoint = destination.getEndpointId();
        } else {
            destAddress = networkManager.getNode(0).getIeeeAddress();
            destEndpoint = 1;
        }

        final CommandResult response = cluster.bind(destAddress, destEndpoint).get();
        processDefaultResponse(response, out);
    }
}
