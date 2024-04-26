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

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;
import com.zsmartsystems.zigbee.console.internal.Cie;
import com.zsmartsystems.zigbee.groups.ZigBeeGroup;
import com.zsmartsystems.zigbee.zcl.clusters.ZclColorControlCluster;
import com.zsmartsystems.zigbee.zcl.clusters.colorcontrol.MoveToColorCommand;

/**
 * Uses the ColorControl cluster to change the colour of a device
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleColorCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "color";
    }

    @Override
    public String getDescription() {
        return "Sets the color on a device";
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

        float red;
        try {
            red = Float.parseFloat(args[2]);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Invalid red value");
        }
        float green;
        try {
            green = Float.parseFloat(args[3]);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Invalid green value");
        }
        float blue;
        try {
            blue = Float.parseFloat(args[4]);
        } catch (final NumberFormatException e) {
            throw new IllegalArgumentException("Invalid blue value");
        }

        final Cie cie = Cie.rgb2cie(red, green, blue);

        int x = (int) (cie.x * 65536);
        int y = (int) (cie.y * 65536);
        if (x > 65279) {
            x = 65279;
        }
        if (y > 65279) {
            y = 65279;
        }

        MoveToColorCommand command = new MoveToColorCommand(x, y, 10);

        List<ZclColorControlCluster> clusters = new ArrayList<>();
        if (WILDCARD.equals(args[1])) {
            for (ZigBeeNode node : networkManager.getNodes()) {
                for (ZigBeeEndpoint endpoint : node.getEndpoints()) {
                    ZclColorControlCluster cluster = (ZclColorControlCluster) endpoint
                            .getInputCluster(ZclColorControlCluster.CLUSTER_ID);
                    if (cluster != null) {
                        clusters.add(cluster);
                    }
                }
            }
        } else {
            ZigBeeGroup group = getGroup(networkManager, args[1]);
            if (group != null) {
                group.sendCommand(command);
                return;
            }

            ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
            ZclColorControlCluster cluster = (ZclColorControlCluster) endpoint
                    .getInputCluster(ZclColorControlCluster.CLUSTER_ID);
            if (cluster != null) {
                clusters.add(cluster);
            }
        }

        CommandResult result;
        for (ZclColorControlCluster cluster : clusters) {
            try {
                result = cluster.sendCommand(command).get();
            } catch (Exception e) {
                out.println(
                        "[Endpoint: " + cluster.getZigBeeAddress() + "] Fail to send command [" + e.getMessage() + "]");
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
