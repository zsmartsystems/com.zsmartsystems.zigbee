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
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

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
        return "[forceNodeRemoval|fnr] NODE [PARENT]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Not enough arguments");
        }

        boolean forceNodeRemoval = args[1].equals("forceNodeRemoval") || args[1].equals("fnr");

        if (args.length > (forceNodeRemoval ? 3 : 4)) {
            throw new IllegalArgumentException("Too many arguments");
        }

        int leaverArgumentPosition = forceNodeRemoval ? 2 : 1;
        int parentArgumentPosition = forceNodeRemoval ? 3 : 2;

        ZigBeeNode leaver = getNode(networkManager, args[leaverArgumentPosition]);

        if (args.length <= parentArgumentPosition) {
            networkManager.leave(leaver.getNetworkAddress(), leaver.getIeeeAddress(), forceNodeRemoval);
        } else {
            ZigBeeNode parent = getNode(networkManager, args[parentArgumentPosition]);
            networkManager.leave(parent.getNetworkAddress(), leaver.getIeeeAddress(), forceNodeRemoval);
        }

    }
}