/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

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
public class ZigBeeConsoleReportingUnsubscribeCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getDescription() {
        return "Unsubscribe from attribute reports.";
    }

    @Override
    public String getSyntax() {
        return "unsubscribe ENDPOINT IN|OUT CLUSTER ATTRIBUTE";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public boolean process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) throws Exception {
        if (args.length != 6) {
            return false;
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        final int clusterId = parseCluster(args[3]);
        final int attributeId = parseAttribute(args[4]);

        final ZclCluster cluster;
        final String direction = args[2].toUpperCase();
        if ("IN".equals(direction)) {
            cluster = endpoint.getInputCluster(clusterId);
        } else if ("OUT".equals(direction)) {
            cluster = endpoint.getOutputCluster(clusterId);
        } else {
            throw new IllegalArgumentException("Cluster direction must be IN or OUT");
        }

        final ZclAttribute attribute = cluster.getAttribute(attributeId);

        final CommandResult result = cluster.setReporting(attribute, 0, 0xFFFF, null).get();
        if (result.isSuccess()) {
            final ConfigureReportingResponse response = result.getResponse();
            final ZclStatus statusCode = response.getRecords().get(0).getStatus();
            if (statusCode == ZclStatus.SUCCESS) {
                out.println("Attribute value configure reporting success.");
            } else {
                out.println("Attribute value configure reporting error: " + statusCode);
            }
            return true;
        } else {
            out.println("Error executing command: " + result);
            return true;
        }
    }
}