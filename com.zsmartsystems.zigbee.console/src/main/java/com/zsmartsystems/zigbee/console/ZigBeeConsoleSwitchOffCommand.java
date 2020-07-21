/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.zcl.clusters.ZclOnOffCluster;
import com.zsmartsystems.zigbee.zcl.clusters.onoff.OffCommand;

/**
 * Uses the OnOff cluster to switch a device off
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleSwitchOffCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "off";
    }

    @Override
    public String getDescription() {
        return "Switches a device OFF";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT";
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
            ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
            ZclOnOffCluster cluster = (ZclOnOffCluster) endpoint.getInputCluster(ZclOnOffCluster.CLUSTER_ID);
            if (cluster != null) {
                clusters.add(cluster);
            }
        }

        for (ZclOnOffCluster cluster : clusters) {
            OffCommand command = new OffCommand();
            cluster.sendCommand(command);
        }
    }
}
