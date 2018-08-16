/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * Console command that prints the commands that are supported by a given cluster.
 *
 * @author Henning Sudbrock
 */
public class ZigBeeConsoleCommandsSupportedCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getCommand() {
        return "cmdsupported";
    }

    @Override
    public String getDescription() {
        return "Check what commands are supported within a cluster, optionally restricted to generated / received commands";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER [gen|rcv]";
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

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        final Integer clusterId = parseCluster(args[2]);
        ZclCluster cluster = getCluster(endpoint, clusterId);

        if (cluster == null) {
            out.println("Cluster not found");
            return;
        }

        if (showGenerated(args)) {
            if (cluster.discoverCommandsGenerated(false).get()) {
                out.println("Supported generated commands for " + cluster.getClusterName() + " cluster "
                        + printClusterId(cluster.getClusterId()));
                printCommands(out, cluster, cluster.getSupportedCommandsGenerated());

            } else {
                out.println("Failed to retrieve supported generated commands");
            }
            out.println();
        }

        if (showReceived(args)) {
            if (cluster.discoverCommandsReceived(false).get()) {
                out.println("Supported received commands for " + cluster.getClusterName() + " cluster "
                        + printClusterId(cluster.getClusterId()));
                printCommands(out, cluster, cluster.getSupportedCommandsReceived());
            } else {
                out.println("Failed to retrieve supported received commands");
            }
        }
    }

    private ZclCluster getCluster(ZigBeeEndpoint endpoint, Integer clusterId) {
        ZclCluster result = endpoint.getInputCluster(clusterId);
        if (result == null) {
            result = endpoint.getOutputCluster(clusterId);
        }
        return result;
    }

    private void printCommands(PrintStream out, ZclCluster cluster, Set<Integer> commandIds) {
        out.println("CommandId  Command");
        for (Integer commandId : commandIds) {
            out.print(" ");
            ZclCommand command = cluster.getCommandFromId(commandId);
            String commandName = (command != null) ? command.getClass().getSimpleName() : "unknown";
            out.print(String.format("%8d  %s", commandId, commandName));
            out.println();
        }
    }

    private boolean showGenerated(String[] args) {
        return args.length < 4 || "gen".equals(args[3]);
    }

    private boolean showReceived(String[] args) {
        return args.length < 4 || "rcv".equals(args[3]);
    }
}
