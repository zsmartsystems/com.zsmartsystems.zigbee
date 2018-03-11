/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
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
public class ZigBeeConsoleNetworkJoinCommand extends ZigBeeConsoleAbstractCommand {

    @Override
    public String getDescription() {
        return "Enable or disable network join.";
    }

    @Override
    public String getSyntax() {
        return "join [ENABLE|DISABLE|PERIOD] [NODE]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public boolean process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) throws Exception {
        if (args.length < 2 | args.length > 3) {
            return false;
        }

        final int join;
        if (args[1].toLowerCase().equals("enable")) {
            join = 255;
        } else if (args[1].toLowerCase().equals("disable")) {
            join = 0;
        } else {
            join = Integer.parseInt(args[1]);
        }

        if (args.length == 3) {
            // Node defined
            ZigBeeNode node = getNode(networkManager, args[2]);
            node.permitJoin(join);
            if (join != 0) {
                out.println("Permit join enable node " + node.getNetworkAddress() + " success.");
            } else {
                out.println("Permit join disable " + node.getNetworkAddress() + " success.");
            }
        } else {
            networkManager.permitJoin(join);
            if (join != 0) {
                out.println("Permit join enable broadcast success.");
            } else {
                out.println("Permit join disable broadcast success.");
            }
        }
        return true;
    }
}