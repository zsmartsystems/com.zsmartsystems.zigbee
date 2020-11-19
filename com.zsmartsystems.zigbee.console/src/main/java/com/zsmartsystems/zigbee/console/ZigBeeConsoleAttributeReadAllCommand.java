/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;

import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleAttributeReadAllCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getCommand() {
        return "readall";
    }

    @Override
    public String getDescription() {
        return "Read all attributes.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER [PERIOD=x] [CYCLES=x]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclCluster cluster = getCluster(endpoint, args[2]);

        final Future<Boolean> future = cluster.discoverAttributes(false, null);
        Boolean result = future.get();
        if (result) {
            int repeatPeriod = 0;
            int repeatCycles = 10;
            final Map<Integer, String> attributes = new HashMap<>();
            for (int i = 3; i < args.length; i++) {
                String cmd[] = args[i].split("=");
                if (cmd != null && cmd.length == 2) {
                    switch (cmd[0].toLowerCase()) {
                        case "period":
                            repeatPeriod = parseInteger(cmd[1]);
                            break;
                        case "cycles":
                            repeatCycles = parseInteger(cmd[1]);
                            break;
                        default:
                            break;
                    }
                    continue;
                }
            }

            cluster.getAttributes().forEach(zclAttribute -> {
                if(zclAttribute.isImplemented()) {
                    attributes.put(zclAttribute.getId(), zclAttribute.getName());
                }
            });

            StringBuilder strAttributes = new StringBuilder();
            for (String value : attributes.values()) {
                if (strAttributes.length() != 0) {
                    strAttributes.append(", ");
                }
                strAttributes.append(value);
            }

            out.println("Reading endpoint " + endpoint.getEndpointAddress() + ", cluster " + printCluster(cluster)
                    + ", all attributes "
                    + (repeatPeriod == 0 ? "" : (" @period = " + repeatPeriod + " sec")));

            for (int cnt = 0; cnt < repeatCycles; cnt++) {
                if (!readAttribute(out, cluster, attributes)) {
                    break;
                }
                if (repeatPeriod == 0) {
                    break;
                }
                Thread.sleep(repeatPeriod * 1000L);
            }
        } else {
            out.println("Failed to retrieve supported attributes");
        }
    }

    private boolean readAttribute(PrintStream out, ZclCluster cluster, Map<Integer, String> attributes) {
        attributes.entrySet().forEach(entry -> {
            CommandResult result = null;
            try {
                result = cluster.readAttributes(Arrays.asList(entry.getKey())).get();
            } catch (Exception e) {
                out.println("Fail to read attribute");
            }

            if (result.isSuccess()) {
                final ReadAttributesResponse response = result.getResponse();

                if (response.getRecords().size() == 0) {
                    out.println("No records returned");
                    return;
                }

                for (ReadAttributeStatusRecord statusRecord : response.getRecords()) {
                    if (statusRecord.getStatus() == ZclStatus.SUCCESS) {
                        out.println("Attribute " + entry.getValue() + " (" + statusRecord.getAttributeIdentifier() + "), type "
                                + statusRecord.getAttributeDataType() + ", value: " + statusRecord.getAttributeValue());
                    } else {
                        out.println("Attribute " + entry.getValue() + " (" + statusRecord.getAttributeIdentifier() + ") error: "
                                + statusRecord.getStatus());
                    }
                }
            } else {
                out.println("Error executing command: " + result);
            }
        });
        return true;
    }
}
