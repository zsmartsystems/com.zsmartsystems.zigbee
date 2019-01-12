/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNetworkLeaveCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "leave";
    }

    @Override
    public String getDescription() {
        return "Remove a node from the network.";
    }

    @Override
    public String getSyntax() {
        return "NODE [PARENT]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        ZigBeeNode leaver = getNode(networkManager, args[1]);

        if (args.length == 2) {
            networkManager.leave(leaver.getNetworkAddress(), leaver.getIeeeAddress());
            return;
        }

        if (args.length == 3) {
            ZigBeeNode parent = getNode(networkManager, args[2]);
            networkManager.leave(parent.getNetworkAddress(), leaver.getIeeeAddress());
            return;
        }
    }
}