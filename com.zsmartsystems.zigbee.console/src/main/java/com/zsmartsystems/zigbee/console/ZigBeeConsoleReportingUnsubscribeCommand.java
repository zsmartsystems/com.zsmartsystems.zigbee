/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.ConfigureReportingResponse;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleReportingUnsubscribeCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "unsubscribe";
    }

    @Override
    public String getDescription() {
        return "Unsubscribe from attribute reports.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER ATTRIBUTE [TYPE]";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 4 || args.length > 5) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        String endpointIdParam = args[1];
        String clusterSpecParam = args[2];
        String attributeIdParam = args[3];
        ZclDataType dataType = null;
        if (args.length > 4) {
        	dataType = ZclDataType.valueOf(args[4]);
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointIdParam);
        final ZclCluster cluster = getCluster(endpoint, clusterSpecParam);

        final int attributeId = parseAttribute(attributeIdParam);
        ZclAttribute attribute = cluster.getAttribute(attributeId);
        
        if (attribute == null && dataType != null) {
        	attribute = new ZclAttribute(cluster, attributeId, null, dataType, true, true, true,true);
        	
		} else if (attribute == null){
            throw new IllegalArgumentException("Can't determine data type :: attributeId (" + attributeId + ") was not found in cluster " + cluster.getClusterName());
        }

        final CommandResult result = cluster.setReporting(attribute, 0, 0xFFFF, 0).get();
        if (result.isSuccess()) {
            final ConfigureReportingResponse response = result.getResponse();
            final ZclStatus statusCode = response.getStatus();
            if (statusCode == ZclStatus.SUCCESS) {
                out.println("Attribute value configure reporting success.");
            } else {
                out.println("Attribute value configure reporting error: " + getRecordsStatus(response));
            }
        } else {
            out.println("Error executing command: " + result);
        }
    }
    
    private String getRecordsStatus(ConfigureReportingResponse response) {
        return response.getRecords().stream()
                .map(record -> record.getStatus().toString())
                .collect(Collectors.joining(" - "));
    }
}