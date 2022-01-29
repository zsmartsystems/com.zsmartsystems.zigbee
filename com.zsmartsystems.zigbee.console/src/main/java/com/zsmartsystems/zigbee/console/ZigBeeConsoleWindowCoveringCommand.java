/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.clusters.ZclWindowCoveringCluster;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringDownClose;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToLiftPercentage;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringGoToTiltPercentage;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringStop;
import com.zsmartsystems.zigbee.zcl.clusters.windowcovering.WindowCoveringUpOpen;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleWindowCoveringCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "covering";
    }

    @Override
    public String getDescription() {
        return "Performs commands on the windowing covering cluster.";
    }

    @Override
    public String getSyntax() {
        return "ENDPOINT [UP | DOWN | OPEN | CLOSE | STOP | LIFT | TILT] [value]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length < 3 || args.length > 4) {
            throw new IllegalArgumentException("Invalid number of commands");
        }

        ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclWindowCoveringCluster cluster = (ZclWindowCoveringCluster) endpoint
                .getInputCluster(ZclWindowCoveringCluster.CLUSTER_ID);

        if (cluster == null) {
            throw new IllegalStateException("Window covering cluster not found");
        }

        switch (args[2].toUpperCase()) {
            case "UP":
            case "OPEN":
                cluster.sendCommand(new WindowCoveringUpOpen());
                break;
            case "DOWN":
            case "CLOSED":
                cluster.sendCommand(new WindowCoveringDownClose());
                break;
            case "STOP":
                cluster.sendCommand(new WindowCoveringStop());
                break;
            case "LIFT":
                cluster.sendCommand(new WindowCoveringGoToLiftPercentage(parsePercentage(args[3])));
                break;
            case "TILT":
                cluster.sendCommand(new WindowCoveringGoToTiltPercentage(parsePercentage(args[3])));
                break;
            default:
                throw new IllegalArgumentException("Invalid command argument " + args[2]);
        }
    }
}
