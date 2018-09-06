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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Handles management of NCP transient link keys
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleTransientKeyCommand extends EmberConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "ncptransientkey";
    }

    @Override
    public String getDescription() {
        return "Adds a transient link key to the NCP";
    }

    @Override
    public String getSyntax() {
        return "IEEEADDRESS KEYDATA";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length < 2 || args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        IeeeAddress partner;
        try {
            partner = new IeeeAddress(args[1]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Partner address is incorrect format.");
        }

        ZigBeeKey transientKey;
        try {
            transientKey = new ZigBeeKey(args[2]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Transient key is incorrect format.");
        }

        EmberNcp ncp = getEmberNcp(networkManager);
        EmberStatus status = ncp.addTransientLinkKey(partner, transientKey);
        out.println("Ember transient key for address " + partner + " resulted in " + status);
    }

}
