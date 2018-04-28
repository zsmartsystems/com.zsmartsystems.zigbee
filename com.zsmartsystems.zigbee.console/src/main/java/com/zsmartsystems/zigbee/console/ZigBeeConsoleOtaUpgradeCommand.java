/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.app.ZigBeeApplication;
import com.zsmartsystems.zigbee.app.otaserver.ZclOtaUpgradeServer;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOtaUpgradeCluster;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleOtaUpgradeCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "otaupgrade";
    }

    @Override
    public String getDescription() {
        return "Provides detailed information about device over the air upgrade server status.";
    }

    @Override
    public String getSyntax() {
        return "[NODEID]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length == 1) {
            cmdDisplayAllNodes(networkManager, out);
            return;
        }

        Map<Integer, ZigBeeEndpoint> applications = getApplications(networkManager, ZclOtaUpgradeCluster.CLUSTER_ID);

        ZigBeeNode node = getNode(networkManager, args[1]);

        ZigBeeEndpoint endpoint = null;
        ZclOtaUpgradeServer server = null;
        for (ZigBeeEndpoint applicationEndpoint : applications.values()) {
            if (applicationEndpoint.getParentNode().equals(node)) {
                endpoint = applicationEndpoint;
                server = (ZclOtaUpgradeServer) endpoint.getApplication(ZclOtaUpgradeCluster.CLUSTER_ID);
            }
        }

        if (server == null) {
            throw new IllegalArgumentException(
                    "Node " + node.getNetworkAddress().toString() + " does not implement the OTA Upgrade server");
        }

        if (args.length == 2) {
            cmdDisplayNode(endpoint, server, out);
            return;
        }

        throw new IllegalArgumentException("Invalid number of arguments");
    }

    private void cmdDisplayAllNodes(ZigBeeNetworkManager networkManager, PrintStream out) {
        Map<Integer, ZigBeeEndpoint> applications = getApplications(networkManager, ZclOtaUpgradeCluster.CLUSTER_ID);
        if (applications.isEmpty()) {
            out.println("No OTA upgrade servers found.");
            return;
        }

        out.println("Address    Ieee Address      State     ");
        for (ZigBeeEndpoint endpoint : applications.values()) {
            ZclOtaUpgradeServer otaServer = (ZclOtaUpgradeServer) endpoint
                    .getApplication(ZclOtaUpgradeCluster.CLUSTER_ID);
            out.println(String.format("%-9s  %s  %-8s", endpoint.getEndpointAddress(), endpoint.getIeeeAddress(),
                    otaServer.getServerStatus()));
        }
    }

    private void cmdDisplayNode(ZigBeeEndpoint endpoint, ZclOtaUpgradeServer otaServer, PrintStream out) {
        out.println("OTA Upgrade configuration for " + endpoint.getEndpointAddress());
        out.println("Current state : " + otaServer.getServerStatus());
    }

    private Map<Integer, ZigBeeEndpoint> getApplications(ZigBeeNetworkManager networkManager, int clusterId) {
        Map<Integer, ZigBeeEndpoint> applications = new TreeMap<>();

        for (ZigBeeNode node : networkManager.getNodes()) {
            for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                ZigBeeApplication application = endpoint.getApplication(clusterId);
                if (application != null) {
                    applications.put(endpoint.getEndpointId(), endpoint);
                }
            }
        }

        return applications;
    }
}
