/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
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
 * Removes a binding between two devices
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleUnbindCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "unbind";
    }

    @Override
    public String getDescription() {
        return "Unbinds a device from another device. If destination is not specified, the coordinator will be used.";
    }

    @Override
    public String getSyntax() {
        return "CLUSTERID SOURCE [DESTINATION]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final int clusterId = parseCluster(args[1]);

        final ZigBeeEndpoint source = getEndpoint(networkManager, args[2]);
        ZclCluster cluster = source.getInputCluster(clusterId);
        if (cluster == null) {
            throw new IllegalArgumentException("Cluster '" + clusterId + "' not found.");
        }

        IeeeAddress destAddress;
        int destEndpoint;
        if (args.length >= 4) {
            ZigBeeEndpoint destination = getEndpoint(networkManager, args[3]);
            destAddress = destination.getIeeeAddress();
            destEndpoint = destination.getEndpointId();
        } else {
            destAddress = networkManager.getNode(0).getIeeeAddress();
            destEndpoint = 1;
        }

        final CommandResult response = cluster.unbind(destAddress, destEndpoint).get();
        processDefaultResponse(response, out);
    }
}
