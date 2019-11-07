/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeDeviceType;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeProfileType;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleDescribeEndpointCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "endpoint";
    }

    @Override
    public String getDescription() {
        return "Provides detailed information about an endpoint.";
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

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);

        ZigBeeProfileType profile = ZigBeeProfileType.getByValue(endpoint.getProfileId());
        ZigBeeDeviceType device = ZigBeeDeviceType.getByValue(endpoint.getDeviceId());

        out.println("IEEE Address     : " + endpoint.getIeeeAddress());
        out.println("Network Address  : " + endpoint.getParentNode().getNetworkAddress());
        out.println("Endpoint         : " + endpoint.getEndpointId());
        out.println("Device Profile   : " + String.format("0x%04X, ", endpoint.getProfileId())
                + (profile == null ? "Unknown" : profile.toString()));
        out.println("Device Type      : " + String.format("0x%04X, ", endpoint.getDeviceId())
                + (device == null ? "Unknown" : device.toString()));
        out.println("Device Version   : " + endpoint.getDeviceVersion());
        out.println("Input Clusters   : (Server)");
        printClusters(endpoint, true, out);
        out.println("Output Clusters  : (Client)");
        printClusters(endpoint, false, out);
    }

    private void printClusters(final ZigBeeEndpoint endpoint, final boolean input, final PrintStream out) {
        Collection<Integer> clusters;
        if (input) {
            clusters = endpoint.getInputClusterIds();
        } else {
            clusters = endpoint.getOutputClusterIds();
        }

        Map<Integer, ZclCluster> clusterTree = new TreeMap<Integer, ZclCluster>();
        for (Integer clusterId : clusters) {
            ZclCluster cluster;
            if (input) {
                cluster = endpoint.getInputCluster(clusterId);
            } else {
                cluster = endpoint.getOutputCluster(clusterId);
            }
            clusterTree.put(cluster.getClusterId(), cluster);
        }

        for (ZclCluster cluster : clusterTree.values()) {
            out.println("   " + printClusterId(cluster.getClusterId()) + " " + cluster.getClusterName());
            out.println("     - APS Security " + (cluster.getApsSecurityRequired() ? "en" : "dis") + "abled");
            printAttributes(cluster, out);
        }
    }

    private void printAttributes(final ZclCluster cluster, final PrintStream out) {
        Map<Integer, ZclAttribute> attributeTree = new TreeMap<Integer, ZclAttribute>();
        for (ZclAttribute attribute : cluster.getAttributes()) {
            attributeTree.put(attribute.getId(), attribute);
        }

        for (ZclAttribute attribute : attributeTree.values()) {
            out.println(String.format("        %s   %5d %s%s%s %s %-40s %s %s",
                    (cluster.getSupportedAttributes().contains(attribute.getId()) ? "S" : "U"), attribute.getId(),
                    (attribute.isReadable() ? "r" : "-"), (attribute.isWritable() ? "w" : "-"),
                    (attribute.isReportable() ? "s" : "-"), printZclDataType(attribute.getDataType()),
                    attribute.getName(),
                    (attribute.getLastValue() == null ? "" : attribute.getLastReportTime().getTime()),
                    (attribute.getLastValue() == null ? "" : attribute.getLastValue())));
        }
    }
}
