/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.clusters.ZclIdentifyCluster;
import com.zsmartsystems.zigbee.zcl.clusters.identify.IdentifyCommand;

/**
 * Sends the identify command to a device
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleIdentifyCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "identify";
    }

    @Override
    public String getDescription() {
        return "Identifies a device";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT [TIME]";
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

        int period = 10;
        if (args.length == 3) {
            period = parseInteger(args[2]);
        }

        ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclIdentifyCluster cluster = (ZclIdentifyCluster) endpoint.getInputCluster(ZclIdentifyCluster.CLUSTER_ID);

        CommandResult result;
        IdentifyCommand command = new IdentifyCommand(period);
        try {
            result = cluster.sendCommand(command).get();
        } catch (Exception e) {
            out.println(
                    "[Endpoint: " + cluster.getZigBeeAddress() + "] Fail to send command [" + e.getMessage() + "]");
            return;
        }

    }
}
