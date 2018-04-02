/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;

/**
 * Gets device information from the BASIC cluster
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleDeviceInformationCommand extends ZigBeeConsoleAbstractCommand {
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
        return "ENDPOINT [MANUFACTURER|APPVERSION|MODEL|APPVERSION|STKVERSION|HWVERSION|ZCLVERSION|DATE] [REFRESH]";
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
        Map<String, String> commands = new TreeMap<>();
        long refresh = Long.MAX_VALUE;

        for (int cnt = 2; cnt < args.length; cnt++) {
            String arg = args[cnt];
            String upperArg = arg.toUpperCase();
            if ("REFRESH".equals(upperArg)) {
                refresh = 0;
                continue;
            }
            commands.put(upperArg, "");
        }

        if (commands.isEmpty()) {
            commands.put("MANUFACTURER", "");
            commands.put("MODEL", "");
            commands.put("APPVERSION", "");
            commands.put("STKVERSION", "");
            commands.put("ZCLVERSION", "");
            commands.put("HWVERSION", "");
            commands.put("DATE", "");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        if (endpoint == null) {
            throw new IllegalArgumentException("Endpoint '" + args[1] + "' not found.");
        }

        ZclBasicCluster basicCluster = (ZclBasicCluster) endpoint.getInputCluster(0);
        if (basicCluster == null) {
            throw new IllegalArgumentException("Can't find basic cluster for " + endpoint.getEndpointAddress());
        }

        for (String command : commands.keySet()) {
            out.println("Requesting " + command);
            Object response = null;
            switch (command) {
                case "MANUFACTURER":
                    response = basicCluster.getManufacturerName(refresh);
                    break;
                case "MODEL":
                    response = basicCluster.getModelIdentifier(refresh);
                    break;
                case "APPVERSION":
                    response = basicCluster.getApplicationVersion(refresh);
                    break;
                case "STKVERSION":
                    response = basicCluster.getStackVersion(refresh);
                    break;
                case "ZCLVERSION":
                    response = basicCluster.getZclVersion(refresh);
                    break;
                case "HWVERSION":
                    response = basicCluster.getHwVersion(refresh);
                    break;
                case "DATE":
                    response = basicCluster.getDateCode(refresh);
                    break;
                default:
                    out.println("Unknown info type: " + command);
                    break;
            }

            if (response != null) {
                commands.put(command, response.toString());
            }
        }

        out.println("Node Info     Value");
        for (Entry<String, String> command : commands.entrySet()) {
            out.println(String.format("%-12s  ", command.getKey()) + command.getValue());
        }
    }

}
