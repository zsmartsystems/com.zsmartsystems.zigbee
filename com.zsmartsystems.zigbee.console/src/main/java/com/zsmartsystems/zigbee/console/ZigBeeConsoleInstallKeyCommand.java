/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.security.MmoHash;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Handles management of install keys
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleInstallKeyCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "installkey";
    }

    @Override
    public String getDescription() {
        return "Adds an install key to the dongle, computing the MMO Hash from the join code";
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

        MmoHash hash;
        ZigBeeKey installKey;
        try {
            hash = new MmoHash(args[2]);
            installKey = new ZigBeeKey(hash.getHash());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Install key is incorrect format.");
        }
        installKey.setAddress(partner);

        ZigBeeStatus result = networkManager.setZigBeeInstallKey(installKey);
        out.println("Install key " + hash.toString() + " for address " + partner + " was "
                + (result == ZigBeeStatus.SUCCESS ? "" : "not") + " set.");
    }

}
