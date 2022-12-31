/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import com.zsmartsystems.zigbee.CommandResult;
import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.zcl.clusters.ZclBasicCluster;
import com.zsmartsystems.zigbee.zcl.clusters.basic.ResetToFactoryDefaultsCommand;

import java.io.PrintStream;

/**
 * Uses the Basic cluster to reset to factory defaults
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleFactoryResetCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "factoryreset";
    }

    @Override
    public String getDescription() {
        return "Factory reset";
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

        ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);
        ZclBasicCluster cluster = (ZclBasicCluster) endpoint.getInputCluster(ZclBasicCluster.CLUSTER_ID);

        CommandResult result;
        try {
            result = cluster.sendCommand(new ResetToFactoryDefaultsCommand()).get();
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
