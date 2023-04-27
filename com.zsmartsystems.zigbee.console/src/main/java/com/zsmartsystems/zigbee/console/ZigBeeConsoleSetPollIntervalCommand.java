/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.clusters.ZclPollControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.SetLongPollIntervalCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.SetShortPollIntervalCommand;
import com.zsmartsystems.zigbee.zcl.clusters.pollcontrol.ZclPollControlCommand;

/**
 * Uses the PollControl cluster to set the poll interval
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleSetPollIntervalCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "setpollinterval";
    }

    @Override
    public String getDescription() {
        return "Sets the device poll interval";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT SHORT|LONG INTERVAL";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }
        if(!args[2].equals("SHORT") && !args[2].equals("LONG")) {
            throw new IllegalArgumentException("Invalid poll interval type - expected SHORT or LONG");
        }

        ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclPollControlCluster cluster = (ZclPollControlCluster) endpoint.getInputCluster(ZclPollControlCluster.CLUSTER_ID);

        boolean shortInterval = args[2].equals("SHORT");
        Integer pollInterval = parseInteger(args[3]);

        ZclPollControlCommand command;
        if(shortInterval) {
            command = new SetShortPollIntervalCommand(pollInterval);
        } else {
            command = new SetLongPollIntervalCommand(pollInterval);
        }
        CommandResult result;
        try {
            result = cluster.sendCommand(command).get();
        } catch (Exception e) {
            out.println("Fail to send command [" + e.getMessage() + "]");
            return;
        }
        if(result.isError() || result.isTimeout()) {
            out.println("Command failed [error = " + result.isError() + " , timeout = " + result.isTimeout() + "]");
            return;
        }
        out.println("Command has been successfully sent");
    }
}
