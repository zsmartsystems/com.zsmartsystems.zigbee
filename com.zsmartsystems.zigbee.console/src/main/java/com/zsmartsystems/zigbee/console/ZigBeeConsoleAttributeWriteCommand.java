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
import com.zsmartsystems.zigbee.zcl.clusters.general.WriteAttributesResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleAttributeWriteCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getDescription() {
        return "Write an attribute.";
    }

    @Override
    public String getSyntax() {
        return "write ENDPOINT IN|OUT CLUSTER ATTRIBUTE VALUE";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public boolean process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) throws Exception {
        if (args.length != 5) {
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
        final Object value = parseValue(args[4], attribute.getDataType());
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
            return true;
        } else {
            out.println("Error executing command: " + result);
            return true;
        }
    }
}