/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.Collection;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspEnergyScanResultHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspNetworkFoundHandler;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberZigbeeNetwork;

/**
 * Performs a scan on the Ember NCP
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpScanCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpscan";
    }

    @Override
    public String getDescription() {
        return "Performs a scan on the Ember NCP";
    }

    @Override
    public String getSyntax() {
        return "[ENERGY | ACTIVE]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        boolean energy = false;
        boolean active = false;

        if (args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        if (args.length == 1) {
            energy = true;
            active = true;
        } else {
            for (String arg : args) {
                if ("energy".equals(arg.toLowerCase())) {
                    energy = true;
                }
                if ("active".equals(arg.toLowerCase())) {
                    active = true;
                }
            }
        }

        EmberNcp ncp = getEmberNcp(networkManager);

        if (active) {
            out.println("Performing active scan...");
            Collection<EzspNetworkFoundHandler> networksFound = ncp.doActiveScan(ZigBeeChannelMask.CHANNEL_MASK_2GHZ,
                    6);

            if (networksFound == null) {
                out.println("Error performing active scan");
            } else {
                out.println("Ember NCP active scan found " + networksFound.size() + " networks");
                out.println();

                if (!networksFound.isEmpty()) {
                    outputNetworksFound(out, networksFound);
                    out.println();
                }
            }
        }

        if (energy) {
            out.println("Performing energy scan...");
            Collection<EzspEnergyScanResultHandler> channels = ncp.doEnergyScan(ZigBeeChannelMask.CHANNEL_MASK_2GHZ,
                    0);
            if (channels == null) {
                out.println("Error performing energy scan");
            } else {
                outputChannelEnergy(out, channels);
            }
        }
    }

    private void outputNetworksFound(PrintStream out, Collection<EzspNetworkFoundHandler> networksFound) {
        out.println("CH  PAN   Extended PAN      Stk  Join   Upd");
        for (EzspNetworkFoundHandler network : networksFound) {
            EmberZigbeeNetwork params = network.getNetworkFound();
            ExtendedPanId epanId = params.getExtendedPanId();
            out.println(String.format("%-2d  %04X  %s  %d    %s  %d", params.getChannel(), params.getPanId(), epanId,
                    params.getStackProfile(), params.getAllowingJoin() ? "True " : "False", params.getNwkUpdateId()));
        }
    }

    private void outputChannelEnergy(PrintStream out, Collection<EzspEnergyScanResultHandler> channels) {
        out.println("CH  RSSI");
        for (EzspEnergyScanResultHandler channel : channels) {
            out.println(String.format("%-2d  %d", channel.getChannel(), channel.getMaxRssiValue()));
        }
    }
}
