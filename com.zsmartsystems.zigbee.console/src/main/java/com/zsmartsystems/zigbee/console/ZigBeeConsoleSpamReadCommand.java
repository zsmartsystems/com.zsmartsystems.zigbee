/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.ZclAttribute;
import com.zsmartsystems.zigbee.zcl.ZclCluster;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleSpamReadCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getCommand() {
        return "spamread";
    }

    @Override
    public String getDescription() {
        return "Read the specified attribute REPEAT (argument value defaults to 10) times sequentially.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT CLUSTER ATTRIBUTE [REPEAT=x]";
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
        Integer attributeId = parseAttribute(args[3]);
        ZclAttribute attribute = cluster.getAttribute(attributeId);
        int repeat = args.length == 5 ? parseInteger(args[4]) : 10;

        if(!cluster.discoverAttributes(false, null).get()) {
            out.println("Failed to discover attributes");
        }

        for(int i = 1; i <= repeat; ++i) {
            out.println("[ROUND " + i + "] Reading endpoint " + endpoint.getEndpointAddress() + ", cluster " + printCluster(cluster) + ", attribute " + attribute.getId());
            cluster.readAttribute(attribute.getId());
        }
    }
}
