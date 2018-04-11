/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspConfigId;
import com.zsmartsystems.zigbee.dongle.ember.internal.ezsp.structure.EzspValueId;

/**
 * Reads or writes an NCP {@link EzspValueId}
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpConfigurationCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpconfig";
    }

    @Override
    public String getDescription() {
        return "Read or write an NCP configuration value";
    }

    @Override
    public String getSyntax() {
        return "[CONFIGID] [VALUE]";
    }

    @Override
    public String getHelp() {
        return "CONFIGID is the Ember NCP configuration enumeration\n" + "VALUE is the value to write\n"
                + "If VALUE is not defined, then the memory will be read.\n"
                + "If no arguments are supplied then all configiration will be displayed.";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        EmberNcp ncp = getEmberNcp(networkManager);

        if (args.length == 1) {
            Map<EzspConfigId, Integer> values = new TreeMap<>();
            for (EzspConfigId configId : EzspConfigId.values()) {
                if (configId == EzspConfigId.UNKNOWN) {
                    continue;
                }

                values.put(configId, ncp.getConfiguration(configId));
            }

            for (Entry<EzspConfigId, Integer> value : values.entrySet()) {
                out.print(String.format("%-55s", value.getKey()));
                if (value.getValue() != null) {
                    out.print(value.getValue());
                }
                out.println();
            }

            return;
        }

        EzspConfigId configId = EzspConfigId.valueOf(args[1].toUpperCase());

        if (args.length == 2) {
            Integer value = ncp.getConfiguration(configId);
            if (value == null) {
                out.println("Error reading Ember NCP configuration " + configId.toString());
            } else {
                out.println("Ember NCP configuration " + configId.toString() + " is " + value);
            }
        } else {
            try {
                Integer value = Integer.parseInt(args[2]);
                boolean response = ncp.setConfiguration(configId, value);
                out.println("Writing Ember NCP configuration " + configId.toString() + " was " + (response ? "" : "un")
                        + "successful.");
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Unable to convert data to integer");
            }
        }
    }
}
