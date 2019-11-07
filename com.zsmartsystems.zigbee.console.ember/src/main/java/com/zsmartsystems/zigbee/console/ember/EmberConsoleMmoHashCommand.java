/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleArgument;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberAesMmoHashContext;

/**
 * Uses the NCP to perform an MMO hash
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleMmoHashCommand extends EmberConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "ncpmmohash";
    }

    @Override
    public String getDescription() {
        return "Passes an install code to the NCP and receives the MMO hash key";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        String hexString = args[1].replace(":", "").replace(" ", "");
        if (hexString.length() % 2 != 0) {
            throw new IllegalArgumentException("Code string must contain an even number of hexadecimal numbers");
        }

        int[] code = new int[hexString.length() / 2];
        for (int i = 0; i < code.length; i++) {
            code[i] = Integer.parseInt(hexString.substring(i * 2, (i * 2) + 2), 16);
        }

        EmberNcp ncp = getEmberNcp(networkManager);
        EmberAesMmoHashContext context = ncp.mmoHash(code);
        out.println("MMO Hash from code " + arrayToString(code) + " is " + arrayToString(context.getResult()));
    }

    private String arrayToString(int[] data) {
        StringBuilder builder = new StringBuilder();

        int cnt = 0;
        for (int value : data) {
            if (cnt++ == 2) {
                builder.append(':');
                cnt = 1;
            }
            builder.append(String.format("%02X", value));
        }

        return builder.toString();
    }

    @Override
    public ZigBeeConsoleArgument getArguments() {
        // TODO Auto-generated method stub
        return null;
    }
}
