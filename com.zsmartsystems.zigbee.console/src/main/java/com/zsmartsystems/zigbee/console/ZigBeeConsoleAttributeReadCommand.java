/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.clusters.general.ReadAttributesResponse;
import com.zsmartsystems.zigbee.zcl.field.ReadAttributeStatusRecord;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleAttributeReadCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "read";
    }

    @Override
    public String getDescription() {
        return "Read one or more attributes.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER ATTRIBUTE1 [ATTRIBUTE2 ...] [PERIOD=x] [CYCLES=x]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclCluster cluster = getCluster(endpoint, args[2]);

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
            Integer attributeId = parseAttribute(args[i]);
            ZclAttribute attribute = cluster.getAttribute(attributeId);
            attributes.put(attributeId,
                    attribute != null ? attribute.getName() : String.format("Attribute %d", attributeId));
        }

        StringBuilder strAttributes = new StringBuilder();
        for (String value : attributes.values()) {
            if (strAttributes.length() != 0) {
                strAttributes.append(", ");
            }
            strAttributes.append(value);
        }

        out.println("Reading endpoint " + endpoint.getEndpointAddress() + ", cluster " + printCluster(cluster)
                + ", attributes " + strAttributes.toString()
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
    }

    private boolean readAttribute(PrintStream out, ZclCluster cluster, Map<Integer, String> attributes)
            throws InterruptedException, ExecutionException {
        CommandResult result = cluster.read(new ArrayList<>(attributes.keySet())).get();

        if (result.isSuccess()) {
            final ReadAttributesResponse response = result.getResponse();

            out.println(String.format("Response for cluster 0x%04x", response.getClusterId()));

            if (response.getRecords().size() == 0) {
                out.println("No records returned");
                return false;
            }

            for (ReadAttributeStatusRecord statusRecord : response.getRecords()) {
                if (statusRecord.getStatus() == ZclStatus.SUCCESS) {
                    out.println("Attribute " + statusRecord.getAttributeIdentifier() + ", type "
                            + statusRecord.getAttributeDataType() + ", value: " + statusRecord.getAttributeValue());
                } else {
                    out.println("Attribute " + statusRecord.getAttributeIdentifier() + " error: "
                            + statusRecord.getStatus());
                }
            }

            return true;
        } else {
            out.println("Error executing command: " + result);
            return false;
        }
    }
}
