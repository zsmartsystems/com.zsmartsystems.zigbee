/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNetworkStateCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpnetworkparms";
    }

    @Override
    public String getDescription() {
        return "Gets the current NCP network parameters";
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

        EmberNetworkParameters networkState = ncp.getNetworkParameters();
        out.println("PAN ID         : " + networkState.getPanId());
        out.println("Extended PAN ID: " + networkState.getExtendedPanId());
        out.println("Radio Channel  : " + networkState.getRadioChannel());
        out.println("Transmit Power : " + networkState.getRadioTxPower());
        out.println("Join Method    : " + networkState.getJoinMethod());
        out.println("Network Manager: " + networkState.getNwkManagerId());
    }
}
