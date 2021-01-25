/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadReportingConfigurationResponse;
import com.zsmartsystems.zigbee.zcl.field.AttributeReportingStatusRecord;

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
        return "ENDPOINT CLUSTER ATTRIBUTE";
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

        String endpointIdParam = args[1];
        String clusterSpecParam = args[2];
        String attributeIdParam = args[3];

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointIdParam);
        final ZclCluster cluster = getCluster(endpoint, clusterSpecParam);

        final int attributeId = parseAttribute(attributeIdParam);
        final ZclAttribute attribute = cluster.getAttribute(attributeId);
        if (attribute == null) {
            throw new IllegalArgumentException(
                    "Attribute " + attributeId + " was not found in " + printCluster(cluster));
        }

        final CommandResult result = cluster.getReporting(attribute.getId()).get();
        if (result.isSuccess()) {
            final ReadReportingConfigurationResponse response = result.getResponse();
            final AttributeReportingStatusRecord attributeReportingStatusRecord = response.getRecords().get(0);
            
            final ZclStatus statusCode = attributeReportingStatusRecord.getStatus();
            if (statusCode == ZclStatus.SUCCESS) {
                out.println("Cluster " + response.getClusterId() + ", Attribute "
                        + attributeReportingStatusRecord.getAttributeIdentifier() + ", type "
                        + attributeReportingStatusRecord.getAttributeDataType() + ", minimum reporting interval: "
                        + attributeReportingStatusRecord.getMinimumReportingInterval() + ", maximum reporting interval: "
                        + attributeReportingStatusRecord.getMaximumReportingInterval() + ", timeout: " 
                        + attributeReportingStatusRecord.getTimeoutPeriod());
            } else {
                out.println("Attribute value read error: " + statusCode);
            }
        } else {
            out.println("Error executing command: " + result);
        }
    }
}