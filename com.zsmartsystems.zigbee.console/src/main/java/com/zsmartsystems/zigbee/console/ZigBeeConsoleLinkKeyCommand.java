/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.security.MmoHash;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Handles management of link keys
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleLinkKeyCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "linkkey";
    }

    @Override
    public String getDescription() {
        return "Sets the link key int the dongle, optionally computing the MMO Hash from the join code";
    }

    @Override
    public String getSyntax() {
        return "[MMO] KEYDATA";
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

        ZigBeeKey key;
        if (args.length == 2) {
            key = new ZigBeeKey(args[1]);
        } else if (args[1].equalsIgnoreCase("mmo")) {
            MmoHash hash;
            try {
                hash = new MmoHash(args[2]);
                key = new ZigBeeKey(hash.getHash());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("MMO key is incorrect format.");
            }
        } else {
            throw new IllegalArgumentException("Invalid arguments.");
        }

        ZigBeeStatus result = networkManager.setZigBeeLinkKey(key);
        out.println("Link key " + key.toString() + " was " + (result == ZigBeeStatus.SUCCESS ? "" : "not") + " set.");
    }

}
