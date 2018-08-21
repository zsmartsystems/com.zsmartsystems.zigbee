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
public class ZigBeeConsoleAttributeReadCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "read";
    }

    @Override
    public String getDescription() {
        return "Read an attribute.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER ATTRIBUTE";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclCluster cluster = getCluster(endpoint, args[2]);

        final Integer attributeId = parseAttribute(args[3]);
        String attributeName;
        ZclAttribute attribute = cluster.getAttribute(attributeId);
        if (attribute == null) {
            attributeName = "Attribute " + attributeId;
        } else {
            attributeName = attribute.getName();
        }

        out.println("Reading " + printCluster(cluster) + ", " + attributeName);

        CommandResult result;
        result = cluster.read(attributeId).get();

        if (result.isSuccess()) {
            final ReadAttributesResponse response = result.getResponse();
            if (response.getRecords().size() == 0) {
                out.println("No records returned");
                return;
            }

            final ZclStatus statusCode = response.getRecords().get(0).getStatus();
            if (statusCode == ZclStatus.SUCCESS) {
                out.println("Cluster " + String.format("%04X", response.getClusterId()) + ", Attribute "
                        + response.getRecords().get(0).getAttributeIdentifier() + ", type "
                        + response.getRecords().get(0).getAttributeDataType() + ", value: "
                        + response.getRecords().get(0).getAttributeValue());
            } else {
                out.println("Attribute value read error: " + statusCode);
            }

            return;
        } else {
            out.println("Error executing command: " + result);
            return;
        }
    }
}
