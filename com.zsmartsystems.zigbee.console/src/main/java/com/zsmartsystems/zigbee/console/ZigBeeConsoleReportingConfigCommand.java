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
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleReportingConfigCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "reportcfg";
    }

    @Override
    public String getDescription() {
        return "Read the reporting configuration of an attribute.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT IN|OUT CLUSTER ATTRIBUTE";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length != 4) {
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

        final int attributeId = parseAttribute(args[4]);
        final ZclAttribute attribute = cluster.getAttribute(attributeId);

        final CommandResult result = cluster.getReporting(attribute).get();
        if (result.isSuccess()) {
            final ReadAttributesResponse response = result.getResponse();

            final ZclStatus statusCode = response.getRecords().get(0).getStatus();
            if (statusCode == ZclStatus.SUCCESS) {
                out.println("Cluster " + response.getClusterId() + ", Attribute "
                        + response.getRecords().get(0).getAttributeIdentifier() + ", type "
                        + response.getRecords().get(0).getAttributeDataType() + ", value: "
                        + response.getRecords().get(0).getAttributeValue());
            } else {
                out.println("Attribute value read error: " + statusCode);
            }
        } else {
            out.println("Error executing command: " + result);
        }
    }
}