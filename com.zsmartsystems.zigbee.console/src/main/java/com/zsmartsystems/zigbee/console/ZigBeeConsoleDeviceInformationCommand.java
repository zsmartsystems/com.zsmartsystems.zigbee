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
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;

/**
 * Gets device information from the BASIC cluster
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleDeviceInformationCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public String getDescription() {
        return "Get basic info about a device";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT [MANUFACTURER|MODEL|APPVERSION|STKVERSION|HWVERSION|ZCLVERSION|DATE] [REFRESH]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        List<Integer> commands = new ArrayList<>();
        long refresh = Long.MAX_VALUE;

        for (int cnt = 2; cnt < args.length; cnt++) {
            String arg = args[cnt];
            String upperArg = arg.toUpperCase();
            if ("REFRESH".equals(upperArg)) {
                refresh = 0;
                continue;
            }

            switch (upperArg) {
                case "MANUFACTURER":
                    commands.add(ZclBasicCluster.ATTR_MANUFACTURERNAME);
                    break;
                case "MODEL":
                    commands.add(ZclBasicCluster.ATTR_MODELIDENTIFIER);
                    break;
                case "APPVERSION":
                    commands.add(ZclBasicCluster.ATTR_APPLICATIONVERSION);
                    break;
                case "STKVERSION":
                    commands.add(ZclBasicCluster.ATTR_STACKVERSION);
                    break;
                case "ZCLVERSION":
                    commands.add(ZclBasicCluster.ATTR_ZCLVERSION);
                    break;
                case "HWVERSION":
                    commands.add(ZclBasicCluster.ATTR_HWVERSION);
                    break;
                case "DATE":
                    commands.add(ZclBasicCluster.ATTR_DATECODE);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid argument " + upperArg);
            }
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        if (endpoint == null) {
            throw new IllegalArgumentException("Endpoint '" + args[1] + "' not found.");
        }

        ZclBasicCluster basicCluster = (ZclBasicCluster) endpoint.getInputCluster(0);
        if (basicCluster == null) {
            throw new IllegalArgumentException("Can't find basic cluster for " + endpoint.getEndpointAddress());
        }

        if (commands.isEmpty()) {
            for (ZclAttribute attribute : basicCluster.getAttributes()) {
                commands.add(attribute.getId());
            }
        }

        Map<String, String> responses = new TreeMap<>();
        for (Integer attributeId : commands) {
            ZclAttribute attribute = basicCluster.getAttribute(attributeId);
            out.println("Requesting " + attribute.getName());
            Object response = attribute.readValue(refresh);

            if (response != null) {
                responses.put(attribute.getName(), response.toString());
            }
        }

        out.println("Node Info             Value");
        for (Entry<String, String> command : responses.entrySet()) {
            out.println(String.format("%-20s  ", command.getKey()) + command.getValue());
        }
    }

}
