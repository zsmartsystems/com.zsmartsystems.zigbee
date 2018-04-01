/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.zcl.protocol.ZclClusterType;
import com.zsmartsystems.zigbee.zdo.field.BindingTable;

/**
 * Gets and displays the binding table from a remote node
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleBindingTableCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "bindtable";
    }

    @Override
    public String getDescription() {
        return "Reads and displays the binding table from a node.";
    }

    @Override
    public String getSyntax() {
        return "NODEID";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        ZigBeeNode node = getNode(networkManager, args[1]);

        final Boolean result = node.updateBindingTable().get();
        if (!result) {
            out.println("Binding table read error");
            return;
        }

        out.println("Binding table for node " + node.getNetworkAddress() + " [" + node.getIeeeAddress() + "]");
        if (node.getBindingTable().isEmpty()) {
            out.println("--- Empty");
            return;
        }

        out.println("Src Address          | Dest Address         | Group | Mode | Cluster");
        for (BindingTable entry : node.getBindingTable()) {
            out.println(String.format("%s/%-3d | %s/%-3d | %5d | %4d | %04X:%s", entry.getSrcAddr().toString(),
                    entry.getSrcEndpoint(), entry.getDstNodeAddr(), entry.getDstNodeEndpoint(), entry.getDstGroupAddr(),
                    entry.getDstAddrMode(), entry.getClusterId(), ZclClusterType.getValueById(entry.getClusterId())));
        }
    }
}
