/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
        return "Check what attributes are supported within a cluster.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER [MANUFACTURER-CODE]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length < 3 || args.length > 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclCluster cluster = getCluster(endpoint, args[2]);
        Integer manufacturerCode = args.length == 4 ? parseInteger(args[3]) : null;

        final Future<Boolean> future = cluster.discoverAttributes(false, manufacturerCode);
        Boolean result = future.get();
        if (result) {
            out.println("Supported attributes for " + printCluster(cluster));
            if (manufacturerCode != null) {
                out.println("For manufacturer code " + args[3]);
            }
            out.println("AttrId  Data Type                  Name");
            for (Integer attributeId : cluster.getSupportedAttributes()) {
                out.print(" ");
                ZclAttribute attribute = cluster.getAttribute(attributeId);
                out.print(printAttributeId(attributeId));
                if (attribute != null) {
                    out.print("  " + printZclDataType(attribute.getDataType()) + "  " + attribute.getName());
                }
                out.println();
            }
        } else {
            out.println("Failed to retrieve supported attributes");
        }
    }
}
