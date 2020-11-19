/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.*;

import java.io.PrintStream;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpSourceRouteTableCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpsrcroutetable";
    }

    @Override
    public String getDescription() {
        return "List the NCP source route table";
    }

    @Override
    public String getSyntax() {
        return "";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        EmberNcp ncp = getEmberNcp(networkManager);
        int entriesCount = ncp.getSourceRouteTableFilledSize().getSourceRouteTableFilledSize();
        out.println("Ember NCP source route table contains " + entriesCount + " entries");
        for (int index = 0; index < entriesCount; index++) {
            EzspGetSourceRouteTableEntryResponse entry = ncp.getSourceRouteTableEntry(index);
            out.println(String.format("%04X (%d) %d", entry.getDestination(), entry.getDestination(), entry.getCloserIndex()));
        }
    }
}
