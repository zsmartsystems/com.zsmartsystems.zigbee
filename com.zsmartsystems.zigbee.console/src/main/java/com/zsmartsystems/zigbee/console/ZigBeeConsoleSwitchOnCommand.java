/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.groups.ZigBeeGroup;
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OnWithTimedOffCommand;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.ZclOnOffCommand;

/**
 * Uses the OnOff cluster to switch a device on
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleSwitchOnCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "on";
    }

    @Override
    public String getDescription() {
        return "Switches a device ON. Second parameter sets off time and uses OnWithTimedOff command.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT [OFF TIME]";
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

        Integer offTime = null;
        if (args.length == 3) {
            offTime = parseInteger(args[2]);
        }

        List<ZclOnOffCluster> clusters = new ArrayList<>();
        if (WILDCARD.equals(args[1])) {
            for (ZigBeeNode node : networkManager.getNodes()) {
                for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                    ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint.getInputCluster(ZclOnOffCluster.CLUSTER_ID);
                    if (cluster != null) {
                        clusters.add(cluster);
                    }
                }
            }
        } else {
            ZigBeeGroup group = getGroup(networkManager, args[1]);
            if (group != null) {
                ZclOnOffCommand command;
                if (offTime != null) {
                    command = new OnWithTimedOffCommand(0, offTime, 0);
                } else {
                    command = new OnCommand();
                }
                group.sendCommand(command);
                return;
            }

            ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
            ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint.getInputCluster(ZclOnOffCluster.CLUSTER_ID);
            if (cluster != null) {
                clusters.add(cluster);
            }
        }

        CommandResult result;
        for (ZclOnOffCluster cluster : clusters) {
            ZclOnOffCommand command;
            if (offTime != null) {
                command = new OnWithTimedOffCommand(0, offTime, 0);
            } else {
                command = new OnCommand();
            }
            try {
                result = cluster.sendCommand(command).get();
            } catch (Exception e) {
                out.println(
                        "[Endpoint: " + cluster.getZigBeeAddress() + "] Failed to send command [" + e.getMessage()
                                + "]");
                return;
            }
            if (result.isError() || result.isTimeout()) {
                out.println("[Endpoint: " + cluster.getZigBeeAddress() + "] Command failed [error = " + result.isError()
                        + " , timeout = " + result.isTimeout() + "]");
                return;
            }
            out.println("[Endpoint: " + cluster.getZigBeeAddress() + "] Command has been successfully sent");
        }
    }
}
