/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclCluster;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleClusterCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getCommand() {
        return "cluster";
    }

    @Override
    public String getDescription() {
        return "Edit a cluster.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER [MANUFACTURER=id]";
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Too few arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        final ZclCluster cluster = getCluster(endpoint, args[2]);

        Integer manufacturer = null;

        for (int i = 3; i < args.length; i++) {
            String cmd[] = args[i].split("=");
            if (cmd != null && cmd.length == 2) {
                switch (cmd[0].toLowerCase()) {
                    case "manufacturer":
                        manufacturer = parseInteger(cmd[1]);
                        break;
                    default:
                        break;
                }
                continue;
            }
        }

        if (manufacturer != null) {
            if (cluster.setManufacturerCode(manufacturer)) {
                out.println("Manufacturer code set to " + manufacturer);
            } else {
                out.println("Error setting manufacturer code!");
            }
        }

        out.println("Cluster updated: " + cluster.toString());
    }
}
