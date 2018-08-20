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
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleAttributeWriteCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "write";
    }

    @Override
    public String getDescription() {
        return "Write an attribute.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER ATTRIBUTE VALUE";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length != 5) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        String endpointIdParam = args[1];
        String clusterIdParam = args[2];
        String attributeIdParam = args[3];
        String attributeValueParam = args[4];

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, endpointIdParam);
        final ZclCluster cluster = getCluster(endpoint, clusterIdParam);

        final int attributeId = parseAttribute(attributeIdParam);

        final ZclAttribute attribute = cluster.getAttribute(attributeId);
        if (attribute == null) {
            throw new IllegalArgumentException("Could not find attribute with ID " + attributeId);
        }

        final Object value = parseValue(attributeValueParam, attribute.getDataType());
        final CommandResult result = cluster.write(attribute, value).get();
        if (result.isSuccess()) {
            final WriteAttributesResponse response = result.getResponse();
            final int statusCode = response.getRecords().get(0).getStatus();
            if (statusCode == 0) {
                out.println("Attribute value write success.");
            } else {
                final ZclStatus status = ZclStatus.getStatus((byte) statusCode);
                out.println("Attribute value write error: " + status);
            }
        } else {
            out.println("Error executing command: " + result);
        }
    }
}