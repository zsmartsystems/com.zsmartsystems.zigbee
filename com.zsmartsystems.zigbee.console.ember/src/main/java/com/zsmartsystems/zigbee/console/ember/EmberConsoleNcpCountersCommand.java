/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleArgument;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberCounterType;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpCountersCommand extends EmberConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "ncpcounters";
    }

    @Override
    public String getDescription() {
        return "Gets the NCP debug counters";
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

        int[] counters = ncp.getCounters();
        out.println("Ember NCP counters -: ");
        for (int counter = 0; counter < counters.length; counter++) {
            out.println(String.format("  [%-2d] %-45s: %d", counter, EmberCounterType.getEmberCounterType(counter),
                    counters[counter]));
        }
    }
}
