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
    protected ZigBeeConsoleArgument initializeArguments() {
        ZigBeeConsoleArgument arguments = ZigBeeConsoleArgumentBuilder.create(ZigBeeConsoleArgumentType.ROOT).build();

        return arguments;
    }

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
        return "ENDPOINT CLUSTER ATTRIBUTE MIN-INTERVAL MAX-INTERVAL REPORTABLE-CHANGE";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 6 || args.length > 7) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        String endpointIdParam = args[1];
        String clusterSpecParam = args[2];
        String attributeIdParam = args[3];
        String minIntervalParam = args[4];
        String maxIntervalParam = args[5];
        String reportableChangeParam = (args.length == 7) ? args[6] : null;

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointIdParam);
        final ZclCluster cluster = getCluster(endpoint, clusterSpecParam);

        final int attributeId = parseAttribute(attributeIdParam);
        final ZclAttribute attribute = cluster.getAttribute(attributeId);
        if (attribute == null) {
            throw new IllegalArgumentException(
                    "Attribute " + attributeId + " was not found in cluster " + cluster.getClusterName());
        }

        final int minInterval;
        try {
            minInterval = Integer.parseInt(minIntervalParam);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Min Interval has invalid format");
        }
        final int maxInterval;
        try {
            maxInterval = Integer.parseInt(maxIntervalParam);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Max Interval has invalid format");
        }

        final Object reportableChange;
        if (reportableChangeParam != null) {
            reportableChange = parseValue(reportableChangeParam, attribute.getDataType());
        } else {
            reportableChange = null;
        }

        final CommandResult result = cluster.setReporting(attribute, minInterval, maxInterval, reportableChange).get();
        if (result.isSuccess()) {
            final ConfigureReportingResponse response = result.getResponse();
            final ZclStatus statusCode = response.getStatus();
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