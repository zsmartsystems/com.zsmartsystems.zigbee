/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeDiscoveryExtension;
import com.zsmartsystems.zigbee.app.discovery.ZigBeeNodeServiceDiscoverer;

/**
 * Console command to manage network discovery.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNetworkDiscoveryCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "discovery";
    }

    @Override
    public String getDescription() {
        return "Manager network discovery.";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length != 4) {
            // throw new IllegalArgumentException("Invalid number of arguments");
        }

        ZigBeeDiscoveryExtension extension = (ZigBeeDiscoveryExtension) networkManager
                .getExtension(ZigBeeDiscoveryExtension.class);
        if (extension == null) {
            throw new IllegalStateException("Discovery extension not found");
        }

        for (ZigBeeNodeServiceDiscoverer discoverer : extension.getNodeDiscoverers()) {
            ZigBeeNode node = discoverer.getNode();
            out.println(String.format("%s  %-5d  %d  %s", node.getIeeeAddress(), node.getNetworkAddress(),
                    discoverer.getRetryPeriod(), discoverer.getTasks()));
        }
    }

}