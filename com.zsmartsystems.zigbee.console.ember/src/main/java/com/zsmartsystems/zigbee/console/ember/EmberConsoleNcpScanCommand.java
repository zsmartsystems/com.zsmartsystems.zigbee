/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.List;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeChannelMask;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleArgument;
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
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

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

        List<EzspNetworkFoundHandler> networksFound = ncp.doActiveScan(ZigBeeChannelMask.CHANNEL_MASK_2GHZ, 6);
        List<EzspEnergyScanResultHandler> channels = ncp.doEnergyScan(ZigBeeChannelMask.CHANNEL_MASK_2GHZ, 6);

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

        if (channels == null) {
            out.println("Error performing energy scan");
        } else {
            outputChannelEnergy(out, channels);
        }
    }

    private void outputNetworksFound(PrintStream out, List<EzspNetworkFoundHandler> networksFound) {
        out.println("CH  PAN   Extended PAN      Stk  Join   Upd");
        for (EzspNetworkFoundHandler network : networksFound) {
            EmberZigbeeNetwork params = network.getNetworkFound();
            ExtendedPanId epanId = new ExtendedPanId(params.getExtendedPanId());
            out.println(String.format("%-2d  %04X  %s  %d    %s  %d", params.getChannel(), params.getPanId(), epanId,
                    params.getStackProfile(), params.getAllowingJoin() ? "True " : "False", params.getNwkUpdateId()));
        }
    }

    private void outputChannelEnergy(PrintStream out, List<EzspEnergyScanResultHandler> channels) {
        out.println("CH  RSSI");
        for (EzspEnergyScanResultHandler channel : channels) {
            out.println(String.format("%-2d  %d", channel.getChannel(), channel.getMaxRssiValue()));
        }
    }

}
