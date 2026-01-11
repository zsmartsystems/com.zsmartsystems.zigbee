/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.app.ZigBeeApplication;
import com.zsmartsystems.zigbee.app.otaserver.ZclOtaUpgradeServer;
import com.zsmartsystems.zigbee.app.otaserver.ZigBeeOtaFile;
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
        return "[STATE | FILE | START | COMPLETE | CANCEL] [ENDPOINT] [FILENAME]";
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

        switch (args[1].toUpperCase()) {
            case "FILE":
                if (args.length < 3) {
                    throw new IllegalArgumentException("Invalid number of arguments: Filename required.");
                }
                cmdDisplayFileInfo(args[2], out);
                break;
            case "STATE":
                if (args.length < 3) {
                    throw new IllegalArgumentException("Invalid number of arguments: Endpoint required.");
                }
                cmdNodeState(networkManager, args[2], out);
                break;
            case "START":
                if (args.length < 4) {
                    throw new IllegalArgumentException("Invalid number of arguments: Endpoint and Filename required.");
                }
                cmdOtaStart(networkManager, args[2], args[3], out);
                break;
            case "COMPLETE":
                if (args.length < 3) {
                    throw new IllegalArgumentException("Invalid number of arguments: Endpoint required.");
                }
                cmdOtaComplete(networkManager, args[2], out);
                break;
            case "CANCEL":
                if (args.length < 3) {
                    throw new IllegalArgumentException("Invalid number of arguments: Endpoint required.");
                }
                cmdOtaCancel(networkManager, args[2], out);
                break;
            default:
                throw new IllegalArgumentException("Invalid command argument " + args[1]);
        }
    }

    private void cmdNodeState(ZigBeeNetworkManager networkManager, String nodeString, PrintStream out) {
        Map<Integer, ZigBeeEndpoint> applications = getApplications(networkManager, ZclOtaUpgradeCluster.CLUSTER_ID);

        ZigBeeNode node = getNode(networkManager, nodeString);

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

        cmdDisplayNode(endpoint, server, out);
    }

    private void cmdDisplayAllNodes(ZigBeeNetworkManager networkManager, PrintStream out) {
        Map<Integer, ZigBeeEndpoint> applications = getApplications(networkManager, ZclOtaUpgradeCluster.CLUSTER_ID);
        if (applications.isEmpty()) {
            out.println("No OTA upgrade servers found.");
            return;
        }

        out.println("Address    IEEE Address      State     ");
        for (ZigBeeEndpoint endpoint : applications.values()) {
            ZclOtaUpgradeServer otaServer = (ZclOtaUpgradeServer) endpoint
                    .getApplication(ZclOtaUpgradeCluster.CLUSTER_ID);
            out.println(String.format("%-9s  %s  %-8s", endpoint.getEndpointAddress(), endpoint.getIeeeAddress(),
                    otaServer.getServerStatus()));
        }
    }

    private void cmdDisplayNode(ZigBeeEndpoint endpoint, ZclOtaUpgradeServer otaServer, PrintStream out) {
        Object fileVersion = otaServer.getCurrentFileVersion();
        String fileVersionAsStr;
        if (fileVersion instanceof Integer) {
            fileVersionAsStr = String.format("%08X", ((Integer) fileVersion).intValue());
        } else {
            fileVersionAsStr = "Unknown";
        }

        out.println("OTA Upgrade configuration for " + endpoint.getEndpointAddress());
        out.println("Current state    : " + otaServer.getServerStatus());
        out.println("Firmware Version : " + fileVersionAsStr);
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

    private void cmdDisplayFileInfo(String filename, PrintStream out) {
        Path file = FileSystems.getDefault().getPath("./", filename);
        byte[] fileData;
        try {
            fileData = Files.readAllBytes(file);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading OTA File \"" + filename + "\"");
        }

        ZigBeeOtaFile otaFile = new ZigBeeOtaFile(fileData);
        out.println("OTA File: " + otaFile);
    }

    private void cmdOtaStart(ZigBeeNetworkManager networkManager, String endpointString, String filename,
            PrintStream out) {
        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointString);

        ZclOtaUpgradeServer otaServer = (ZclOtaUpgradeServer) endpoint.getApplication(ZclOtaUpgradeCluster.CLUSTER_ID);
        if (otaServer == null) {
            throw new IllegalArgumentException("OTA Server not supported by " + endpoint.getEndpointAddress() + "");
        }

        Path file = FileSystems.getDefault().getPath("./", filename);
        byte[] fileData;
        try {
            fileData = Files.readAllBytes(file);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading OTA File \"" + filename + "\"");
        }
        ZigBeeOtaFile otaFile = new ZigBeeOtaFile(fileData);
        otaServer.setFirmware(otaFile);
        out.println("OTA File \"" + filename + "\" set for node " + endpoint.getEndpointAddress());
        out.println("OTA File: " + otaFile);
    }

    private void cmdOtaComplete(ZigBeeNetworkManager networkManager, String endpointString, PrintStream out) {
        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointString);

        ZclOtaUpgradeServer otaServer = (ZclOtaUpgradeServer) endpoint.getApplication(ZclOtaUpgradeCluster.CLUSTER_ID);
        if (otaServer == null) {
            throw new IllegalArgumentException("OTA Server not supported by " + endpoint.getEndpointAddress() + "");
        }

        boolean success = otaServer.completeUpgrade();
        out.println("OTA Upgrade completion on endpoint " + endpoint.getEndpointAddress() + " returned " + success);
    }

    private void cmdOtaCancel(ZigBeeNetworkManager networkManager, String endpointString, PrintStream out) {
        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointString);

        ZclOtaUpgradeServer otaServer = (ZclOtaUpgradeServer) endpoint.getApplication(ZclOtaUpgradeCluster.CLUSTER_ID);
        if (otaServer == null) {
            throw new IllegalArgumentException("OTA Server not supported by " + endpoint.getEndpointAddress() + "");
        }

        otaServer.cancelUpgrade();
        out.println("OTA Upgrade cancelled on endpoint " + endpoint.getEndpointAddress());
    }
}
