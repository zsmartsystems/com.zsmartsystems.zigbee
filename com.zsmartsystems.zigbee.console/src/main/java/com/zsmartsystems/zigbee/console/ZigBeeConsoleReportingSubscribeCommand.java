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
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleReportingSubscribeCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "subscribe";
    }

    @Override
    public String getDescription() {
        return "Subscribe to attribute reports.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT IN|OUT CLUSTER ATTRIBUTE MIN-INTERVAL MAX-INTERVAL REPORTABLE-CHANGE";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 7) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        final int clusterId = parseCluster(args[3]);
        final ZclCluster cluster;
        final String direction = args[2].toUpperCase();
        if ("IN".equals(direction)) {
            cluster = endpoint.getInputCluster(clusterId);
        } else if ("OUT".equals(direction)) {
            cluster = endpoint.getOutputCluster(clusterId);
        } else {
            throw new IllegalArgumentException("Cluster direction must be IN or OUT");
        }

        final int minInterval;
        try {
            minInterval = Integer.parseInt(args[5]);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Min Interval has invalid format");
        }
        final int maxInterval;
        try {
            maxInterval = Integer.parseInt(args[6]);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Max Interval has invalid format");
        }

        final int attributeId = parseAttribute(args[4]);
        final ZclAttribute attribute = cluster.getAttribute(attributeId);
        if (attribute == null) {
            throw new IllegalArgumentException(
                    "Attribute " + attributeId + " was not found in cluster " + cluster.getClusterName());
        }

        final Object reportableChange;
        if (args.length > 7) {
            reportableChange = parseValue(args[7], attribute.getDataType());
        } else {
            reportableChange = null;
        }

        final CommandResult result = cluster.setReporting(attribute, minInterval, maxInterval, reportableChange).get();
        if (result.isSuccess()) {
            final ConfigureReportingResponse response = result.getResponse();
            final ZclStatus statusCode = response.getRecords().get(0).getStatus();
            if (statusCode == ZclStatus.SUCCESS) {
                out.println("Attribute value configure reporting success.");
            } else {
                out.println("Attribute value configure reporting error: " + statusCode);
            }
        } else {
            out.println("Error executing command: " + result);
        }
    }

}