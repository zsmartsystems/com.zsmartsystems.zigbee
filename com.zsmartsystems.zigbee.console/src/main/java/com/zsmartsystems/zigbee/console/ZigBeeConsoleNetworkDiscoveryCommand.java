/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.TimeZone;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeDiscoveryExtension;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeNodeServiceDiscoverer;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeNodeServiceDiscoverer.NodeDiscoveryTask;

/**
 * Console command to manage network discovery.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNetworkDiscoveryCommand extends ZigBeeConsoleAbstractCommand {
    private final static String NONE = "None";
    private final static String NEVER = "Never";

    private static final DateFormat dfIso8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "discovery";
    }

    @Override
    public String getDescription() {
        return "Manager network discovery.";
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
        ZigBeeDiscoveryExtension extension = (ZigBeeDiscoveryExtension) networkManager
                .getExtension(ZigBeeDiscoveryExtension.class);
        if (extension == null) {
            throw new IllegalStateException("Discovery extension not found");
        }

        TimeZone tz = TimeZone.getTimeZone("UTC");
        dfIso8601.setTimeZone(tz);

        if (args.length == 1) {
            outputDiscoveryTasks(extension, out);
            return;
        }

        switch (args[1].toLowerCase()) {
            case "period":
                if (args.length >= 3) {
                    extension.setUpdatePeriod(parseInteger(args[2]));
                }
                out.println("Mesh update period is set to " + extension.getUpdatePeriod() + " seconds.");
                return;
            default:
                break;
        }

        final ZigBeeNode node = getNode(networkManager, args[1]);
        final ZigBeeNodeServiceDiscoverer discoverer = getNodeDiscoverer(extension, node.getIeeeAddress());
        if (discoverer == null) {
            throw new IllegalStateException(
                    "Network discoverer for " + node.getIeeeAddress().toString() + " cannot be found.");
        }

        if (args.length == 2) {
            displayNodeMesh(node, discoverer, out);
            return;
        }

        switch (args[2].toLowerCase()) {
            case "rediscover":
                extension.rediscoverNode(node.getIeeeAddress());
                out.println("Rediscovery for " + node.getIeeeAddress().toString() + " has been started.");
                break;
            case "start":
                discoverer.startDiscovery();
                out.println("Discovery for " + node.getIeeeAddress().toString() + " has been started.");
                return;
            case "update":
                discoverer.updateMesh();
                out.println("Network mesh update for " + node.getIeeeAddress().toString() + " has been started.");
                return;
            default:
                break;
        }
    }

    private ZigBeeNodeServiceDiscoverer getNodeDiscoverer(ZigBeeDiscoveryExtension extension, IeeeAddress ieeeAddress) {
        for (ZigBeeNodeServiceDiscoverer discoverer : extension.getNodeDiscoverers()) {
            if (discoverer.getNode().getIeeeAddress().equals(ieeeAddress)) {
                return discoverer;
            }
        }

        return null;
    }

    private void outputDiscoveryTasks(ZigBeeDiscoveryExtension extension, PrintStream out) {
        out.println("Mesh update period : " + extension.getUpdatePeriod() + " seconds");
        out.println();
        out.println("Address           Nwk    Last Start            Last Complete         Current Tasks");
        for (ZigBeeNodeServiceDiscoverer discoverer : extension.getNodeDiscoverers()) {
            ZigBeeNode node = discoverer.getNode();
            out.println(String.format("%s  %-5d  %-20s  %-20s  %s", node.getIeeeAddress(), node.getNetworkAddress(),
                    discoverer.getLastDiscoveryStarted() == null ? NEVER
                            : dfIso8601.format(discoverer.getLastDiscoveryStarted().getTime()),
                    discoverer.getLastDiscoveryCompleted() == null ? NEVER
                            : dfIso8601.format(discoverer.getLastDiscoveryCompleted().getTime()),
                    tasksToString(discoverer.getTasks())));
        }
    }

    private void displayNodeMesh(ZigBeeNode node, ZigBeeNodeServiceDiscoverer discoverer, PrintStream out) {
        out.println("IEEE Address             : " + node.getIeeeAddress().toString());
        out.println("NWK Address              : " + node.getNetworkAddress().toString());
        out.println("Last discovery started : " + (discoverer.getLastDiscoveryStarted() == null ? NEVER
                : dfIso8601.format(discoverer.getLastDiscoveryStarted().getTime())));
        out.println("Last discovery completed : " + (discoverer.getLastDiscoveryCompleted() == null ? NEVER
                : dfIso8601.format(discoverer.getLastDiscoveryStarted().getTime())));
        out.println("Current tasks            : " + tasksToString(discoverer.getTasks()));
    }

    private String tasksToString(Collection<NodeDiscoveryTask> tasks) {
        if (tasks.isEmpty()) {
            return NONE;
        }

        StringBuilder builder = new StringBuilder();
        for (NodeDiscoveryTask task : tasks) {
            if (builder.length() != 0) {
                builder.append(", ");
            }
            builder.append(task);
        }

        return builder.toString();
    }

}