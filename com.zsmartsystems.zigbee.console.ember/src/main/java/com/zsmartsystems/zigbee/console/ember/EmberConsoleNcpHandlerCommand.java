/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.Map.Entry;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspValueId;

/**
 * Reads or writes an NCP {@link EzspValueId}
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpHandlerCommand extends EmberConsoleAbstractCommand {
    private final static String OPTION_STATEPOLL = "statepoll";

    @Override
    public String getCommand() {
        return "ncphandler";
    }

    @Override
    public String getDescription() {
        return "Configure or read the state of the NCP handler";
    }

    @Override
    public String getSyntax() {
        return "[statepoll PERIOD]";
    }

    @Override
    public String getHelp() {
        return "statepoll sets the period in milliseconds the handler will poll the NCP to check it is alive";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (!(networkManager.getZigBeeTransport() instanceof ZigBeeDongleEzsp)) {
            throw new IllegalArgumentException("Dongle is not an Ember NCP.");
        }
        ZigBeeDongleEzsp dongle = (ZigBeeDongleEzsp) networkManager.getZigBeeTransport();

        if (args.length == 1) {
            for (Entry<String, Long> counter : dongle.getCounters().entrySet()) {
                out.println(String.format("%20s %d", counter.getKey(), counter.getValue()));
            }

            return;
        }

        if (args.length < 1 || args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        switch (args[1].toLowerCase()) {
            case OPTION_STATEPOLL:
                Integer pollRate = Integer.parseInt(args[2]);
                dongle.setPollRate(pollRate);
                break;
            default:
                throw new IllegalArgumentException("Unknown option " + args[1]);
        }
    }
}
