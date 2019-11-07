/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleArgument;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspVersionResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpStateCommand extends EmberConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "ncpstate";
    }

    @Override
    public String getDescription() {
        return "Gets the NCP network state. Optionally brings the NCP online or offline.";
    }

    @Override
    public String getSyntax() {
        return "[ONLINE | OFFLINE]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 2) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        if (args.length == 1) {
            ncpState(networkManager, out);
            return;
        }

        switch (args[1].toLowerCase()) {
            case "online":
                ncpOnline(networkManager, out);
                break;
            case "offline":
                ncpOffline(networkManager, out);
                break;
            default:
                throw new IllegalArgumentException("Unknown option specified: " + args[1].toUpperCase());
        }
    }

    private void ncpOnline(ZigBeeNetworkManager networkManager, PrintStream out) {
        EmberNcp ncp = getEmberNcp(networkManager);
        EmberStatus response = ncp.networkInit();

        out.println("NCP network initialisation result was " + response);
    }

    private void ncpOffline(ZigBeeNetworkManager networkManager, PrintStream out) {
        EmberNcp ncp = getEmberNcp(networkManager);
        EmberStatus response = ncp.leaveNetwork();

        out.println("NCP network leave result was " + response);
    }

    private void ncpState(ZigBeeNetworkManager networkManager, PrintStream out) {
        EmberNcp ncp = getEmberNcp(networkManager);

        EmberNetworkStatus status = ncp.getNetworkState();
        IeeeAddress ieeeAddress = ncp.getIeeeAddress();
        int nwkAddress = ncp.getNwkAddress();
        EzspGetNetworkParametersResponse nwkParameterResponse = ncp.getNetworkParameters();
        EmberNetworkParameters nwkParameters = nwkParameterResponse.getParameters();

        String mfgName = ncp.getMfgName();
        String mfgBoard = ncp.getMfgBoardName();
        int customVersion = ncp.getMfgCustomVersion();
        EzspVersionResponse version = ncp.getVersion(5);

        out.println("Ember NCP state    : " + status);
        out.println("Local Node Type    : " + nwkParameterResponse.getNodeType());
        out.println("IEEE Address       : " + ieeeAddress);
        out.println("NWK Address        : " + nwkAddress);
        out.println("Network PAN Id     : " + String.format("%04X", nwkParameters.getPanId()));
        out.println("Network EPAN Id    : " + nwkParameters.getExtendedPanId());
        out.println("Radio Channel      : " + nwkParameters.getRadioChannel());
        out.println("Network Manager Id : " + nwkParameters.getNwkManagerId());
        out.println("Radio TX Power     : " + nwkParameters.getRadioTxPower());
        out.println("Join Method        : " + nwkParameters.getJoinMethod());

        out.println("Board Name         : " + mfgBoard);
        out.println("Manufacturer Name  : " + mfgName);
        out.println("Stack Version      : " + printVersion(version.getStackVersion()));
        out.println("Custom Version     : " + printVersion(customVersion));
    }

    private String printVersion(int version) {
        StringBuilder builder = new StringBuilder(60);
        for (int cnt = 3; cnt >= 0; cnt--) {
            builder.append((version >> (cnt * 4)) & 0x0F);
            if (cnt != 0) {
                builder.append('.');
            }
        }
        return builder.toString();
    }
}
