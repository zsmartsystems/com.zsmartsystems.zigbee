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
import java.util.concurrent.Future;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleAttributeSupportedCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "attsupported";
    }

    @Override
    public String getDescription() {
        return "Check what attributes are supported within a cluster. The argument REDISCOVER is optional and can have the value true or false (default). The argument MANUFACTURER-CODE is optional and must be of type integer.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER [DISCOVER|NODISCOVER] [MANUFACTURER-CODE]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 3 || args.length > 5) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclCluster cluster = getCluster(endpoint, args[2]);
        boolean rediscover = false;
        if(args.length >= 4) {
            if(!args[3].equalsIgnoreCase("discover") && !args[3].equalsIgnoreCase("nodiscover")) {
                out.println("Invalid discover/nodiscover argument (must be DISCOVER or NODISCOVER)");
                return;
            }
            rediscover = args[3].equalsIgnoreCase("discover");
        }
        Integer manufacturerCode = args.length == 5 ? parseInteger(args[4]) : null;

        final Future<Boolean> future = cluster.discoverAttributes(rediscover, manufacturerCode);
        Boolean result = future.get();
        if (result) {
            out.println("Supported attributes for " + printCluster(cluster));
            if (manufacturerCode != null) {
                out.println("For manufacturer code " + args[3]);
            }
            out.println(String.format("%-14s  %-50s  %-50s", "AttrId", "Data Type", "Name"));
            for (Integer attributeId : cluster.getSupportedAttributes()) {
                ZclAttribute attribute = cluster.getAttribute(attributeId);
                out.println(String.format("%5d (0x%04x)  %-50s  %-50s",
                    attributeId, attributeId, attribute != null ? printZclDataType(attribute.getDataType()) : "", attribute != null ? attribute.getName() : ""));
            }
        } else {
            out.println("Failed to retrieve supported attributes");
        }
    }
}
