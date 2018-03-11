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
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleAttributeReadCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getDescription() {
        return "Read an attribute.";
    }

    @Override
    public String getSyntax() {
        return "read ENDPOINT CLUSTER ATTRIBUTE";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public boolean process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) throws Exception {
        if (args.length != 4) {
            return false;
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        final Integer clusterId = parseCluster(args[2]);
        final Integer attributeId = parseAttribute(args[3]);

        ZclCluster cluster = endpoint.getInputCluster(clusterId);
        if (cluster != null) {
            out.println("Using input cluster");
        } else {
            cluster = endpoint.getOutputCluster(clusterId);
            if (cluster != null) {
                out.println("Using output cluster");
            } else {
                out.println("Cluster not found");
                return false;
            }
        }

        String attributeName;
        ZclAttribute attribute = cluster.getAttribute(attributeId);
        if (attribute == null) {
            attributeName = "Attribute " + attributeId;
        } else {
            attributeName = attribute.getName();
        }

        out.println("Reading " + cluster.getClusterName() + ", " + attributeName);

        final CommandResult result = cluster.read(attributeId).get();
        if (result.isSuccess()) {
            final ReadAttributesResponse response = result.getResponse();
            if (response.getRecords().size() == 0) {
                out.println("No records returned");
                return true;
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

            return true;
        } else {
            out.println("Error executing command: " + result);
            return true;
        }
    }
}
