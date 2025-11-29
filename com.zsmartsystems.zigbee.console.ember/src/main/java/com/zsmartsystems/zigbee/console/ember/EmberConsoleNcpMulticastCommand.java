/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberMulticastTableEntry;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspConfigId;

/**
 * Provides an interface to the NCP routing information
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpMulticastCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpmcast";
    }

    @Override
    public String getDescription() {
        return "Read and configure NCP multicast group table";
    }

    @Override
    public String getSyntax() {
        return "[SET INDEX ID EP NW] [CLEAR INDEX]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 6) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        EmberNcp ncp = getEmberNcp(networkManager);

        if (args.length > 1) {
            if (args[1].toLowerCase().equals("set")) {
                set(ncp, args, out);
                return;
            }

            if (args[1].toLowerCase().equals("clear")) {
                clear(ncp, args, out);
                return;
            }

            throw new IllegalArgumentException("Unknown option " + args[1] + ".");
        }

        int configMcastTableSize = ncp.getConfiguration(EzspConfigId.EZSP_CONFIG_MULTICAST_TABLE_SIZE);

        Map<Integer, EmberMulticastTableEntry> mcastTableMap = new TreeMap<>();
        for (int cnt = 0; cnt < configMcastTableSize; cnt++) {
            EmberMulticastTableEntry mcastEntry = ncp.getMulticastTableEntry(cnt);
            mcastTableMap.put(cnt, mcastEntry);
        }

        out.println("Multicast Table Size Config: " + configMcastTableSize);

        final String ncpMcastTableFormat = "%5s  %-15s  %-8s  %-7s";
        out.println();
        out.println("NCP Multicast Table :");
        out.println();
        out.println(String.format(ncpMcastTableFormat, "Index", "MCast ID", "Endpoint", "Network"));
        for (Entry<Integer, EmberMulticastTableEntry> mcast : mcastTableMap.entrySet()) {
            EmberMulticastTableEntry mcastEntry = mcast.getValue();
            out.println(String.format(ncpMcastTableFormat,
                    String.format("%d", mcast.getKey()),
                    String.format("%04X (%5d)", mcastEntry.getMulticastId(), mcastEntry.getMulticastId()),
                    String.format("%d", mcastEntry.getEndpoint()),
                    String.format("%d", mcastEntry.getNetworkIndex())));
        }
    }

    private void set(EmberNcp ncp, String[] args, PrintStream out) {
        EmberMulticastTableEntry mcastEntry = new EmberMulticastTableEntry();

        int index = parseInteger(args[2]);

        mcastEntry.setMulticastId(parseInteger(args[3]));
        mcastEntry.setEndpoint(parseInteger(args[4]));
        mcastEntry.setNetworkIndex(parseInteger(args[5]));

        setMulticastTableEntry(out, ncp, index, mcastEntry);
    }

    private void clear(EmberNcp ncp, String[] args, PrintStream out) {
        EmberMulticastTableEntry mcastEntry = new EmberMulticastTableEntry();

        int index = parseInteger(args[2]);

        mcastEntry.setMulticastId(0);
        mcastEntry.setEndpoint(0);
        mcastEntry.setNetworkIndex(0);

        setMulticastTableEntry(out, ncp, index, mcastEntry);
    }

    private void setMulticastTableEntry(PrintStream out, EmberNcp ncp, int index, EmberMulticastTableEntry mcastEntry) {
        EmberStatus status = ncp.setMulticastTableEntry(index, mcastEntry);
        out.println("Multicast table index " + index + " update completed with state " + status.toString());
    }
}
