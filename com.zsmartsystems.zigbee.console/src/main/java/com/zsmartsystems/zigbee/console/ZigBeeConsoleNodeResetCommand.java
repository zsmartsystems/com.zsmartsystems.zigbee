/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.basic.ResetToFactoryDefaultsCommand;

/**
 * Uses the Basic cluster to reset a device to factory defaults
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNodeResetCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "reset";
    }

    @Override
    public String getDescription() {
        return "Resets a node to factory defaults";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT";
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

        ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclBasicCluster cluster = (ZclBasicCluster) endpoint.getInputCluster(ZclBasicCluster.CLUSTER_ID);

        if (cluster == null) {
            throw new IllegalStateException(
                    "Endpoint " + endpoint.getEndpointAddress() + " does not support the BASIC cluster");
        }

        ResetToFactoryDefaultsCommand command = new ResetToFactoryDefaultsCommand();
        CommandResult result;
        try {
            result = cluster.sendCommand(command).get();
            if (result.isError()) {
                out.println("Failed to send reset command: " + result.getResponse().toString());
                return;
            }
            out.println("Node reset to factory defaults successfully");
        } catch (InterruptedException | ExecutionException e) {
            out.println("Failed to send reset command: " + e.getLocalizedMessage());
        }
    }
}
