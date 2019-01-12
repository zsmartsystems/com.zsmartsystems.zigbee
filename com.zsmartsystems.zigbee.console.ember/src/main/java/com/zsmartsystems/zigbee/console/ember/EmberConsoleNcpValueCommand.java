/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspValueId;

/**
 * Reads or writes an NCP {@link EzspValueId}
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpValueCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpvalue";
    }

    @Override
    public String getDescription() {
        return "Read or write an NCP memory value";
    }

    @Override
    public String getSyntax() {
        return "[VALUEID] [VALUE]";
    }

    @Override
    public String getHelp() {
        return "VALUEID is the Ember NCP value enumeration\n" + "VALUE is the value to write\n"
                + "If VALUE is not defined, then the memory will be read.\n"
                + "If no arguments are supplied then all values will be displayed.";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        EmberNcp ncp = getEmberNcp(networkManager);

        if (args.length == 1) {
            Map<EzspValueId, int[]> values = new TreeMap<>();
            for (EzspValueId valueId : EzspValueId.values()) {
                if (valueId == EzspValueId.UNKNOWN) {
                    continue;
                }

                values.put(valueId, ncp.getValue(valueId));
            }

            for (Entry<EzspValueId, int[]> value : values.entrySet()) {
                out.print(String.format("%-50s", value.getKey()));
                if (value.getValue() != null) {
                    out.print(displayValue(value.getKey(), value.getValue()));
                }
                out.println();
            }

            return;
        }

        EzspValueId valueId = EzspValueId.valueOf(args[1].toUpperCase());

        if (args.length == 2) {
            int[] value = ncp.getValue(valueId);
            if (value == null) {
                out.println("Error reading Ember NCP value " + valueId.toString());
            } else {
                out.println("Ember NCP value " + valueId.toString() + " is " + displayValue(valueId, value));
            }
        } else {
            int[] value = parseInput(valueId, Arrays.copyOfRange(args, 2, args.length));
            if (value == null) {
                throw new IllegalArgumentException("Unable to convert data to value array");
            }
            EzspStatus response = ncp.setValue(valueId, value);
            out.println("Writing Ember NCP value " + valueId.toString() + " was "
                    + (response == EzspStatus.EZSP_SUCCESS ? "" : "un") + "successful.");
        }
    }

    private String displayValue(EzspValueId valueId, int[] value) {
        StringBuilder builder = new StringBuilder();
        switch (valueId) {
            default:
                boolean first = true;
                for (int intVal : value) {
                    if (!first) {
                        builder.append(' ');
                    }
                    first = false;
                    builder.append(String.format("%02X", intVal));
                }
                break;
        }

        return builder.toString();
    }

    private int[] parseInput(EzspValueId valueId, String[] args) {
        int[] value = null;
        switch (valueId) {
            case EZSP_VALUE_APS_FRAME_COUNTER:
            case EZSP_VALUE_NWK_FRAME_COUNTER:
                Long longValue = Long.parseLong(args[0]);
                value = new int[4];
                value[0] = (int) (longValue & 0x000000FF);
                value[1] = (int) (longValue & 0x0000FF00) >> 8;
                value[2] = (int) (longValue & 0x00FF0000) >> 16;
                value[3] = (int) (longValue & 0xFF000000) >> 24;
                break;

            default:
                break;
        }
        return value;
    }
}
