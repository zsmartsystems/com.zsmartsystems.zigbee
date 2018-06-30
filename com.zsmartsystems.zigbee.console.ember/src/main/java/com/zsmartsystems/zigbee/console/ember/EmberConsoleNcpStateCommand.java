/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetNetworkParametersResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkParameters;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberNetworkStatus;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpStateCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpstate";
    }

    @Override
    public String getDescription() {
        return "Gets the NCP network state";
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

        EmberNetworkStatus status = ncp.getNetworkState();
        IeeeAddress ieeeAddress = ncp.getIeeeAddress();
        int nwkAddress = ncp.getNwkAddress();
        EzspGetNetworkParametersResponse nwkParameterResponse = ncp.getNetworkParameters();
        EmberNetworkParameters nwkParameters = nwkParameterResponse.getParameters();
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
    }

}
