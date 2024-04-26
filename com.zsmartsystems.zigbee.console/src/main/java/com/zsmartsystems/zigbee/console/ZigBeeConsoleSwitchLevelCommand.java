/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.groups.ZigBeeGroup;
import com.zsmartsystems.zigbee.zcl.clusters.ZclLevelControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.levelcontrol.MoveToLevelCommand;

/**
 * Uses the LevelControl cluster to set the level
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleSwitchLevelCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "level";
    }

    @Override
    public String getDescription() {
        return "Sets the device level";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT LEVEL [RATE]";
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

        int level = parsePercentage(args[2]);
        int rate = 10;
        if (args.length == 4) {
            rate = parseInteger(args[3]);
        }

        List<ZclLevelControlCluster> clusters = new ArrayList<>();
        if (WILDCARD.equals(args[1])) {
            for (ZigBeeNode node : networkManager.getNodes()) {
                for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                    ZclLevelControlCluster cluster = (ZclLevelControlCluster) endpoint
                            .getInputCluster(ZclLevelControlCluster.CLUSTER_ID);
                    if (cluster != null) {
                        clusters.add(cluster);
                    }
                }
            }
        } else {
            ZigBeeGroup group = getGroup(networkManager, args[1]);
            if (group != null) {
                MoveToLevelCommand command = new MoveToLevelCommand(level, rate);
                group.sendCommand(command);
                return;
            }

            ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
            ZclLevelControlCluster cluster = (ZclLevelControlCluster) endpoint
                    .getInputCluster(ZclLevelControlCluster.CLUSTER_ID);
            if (cluster != null) {
                clusters.add(cluster);
            }
        }

        for (ZclLevelControlCluster cluster : clusters) {
            MoveToLevelCommand command = new MoveToLevelCommand(level, rate);
            cluster.sendCommand(command);
        }
    }
}
