/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EmberStatus;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.structure.EzspMfgTokenId;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpTokenCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncptoken";
    }

    @Override
    public String getDescription() {
        return "Reads and writes manufacturing tokens in the NCP";
    }

    @Override
    public String getSyntax() {
        return "[]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 3) {
            throw new IllegalArgumentException("Incorrect number of arguments.");
        }

        ZigBeeDongleEzsp dongle = (ZigBeeDongleEzsp) networkManager.getZigBeeTransport();
        if (dongle == null) {
            throw new IllegalStateException("Dongle is not an Ember NCP.");
        }
        EmberNcp ncp = dongle.getEmberNcp();

        if (args.length == 1) {
            readAllTokens(ncp, out);
            return;
        }

        EzspMfgTokenId tokenId = EzspMfgTokenId.valueOf(args[1]);

        if (args.length == 2) {
            int[] token = ncp.getMfgToken(tokenId);
            out.println(String.format("%-27s %s", tokenId, tokenToString(token)));
        }

        int[] token = convertToken(tokenId, args[2]);
        if (token == null) {
            out.println("Unable to convert " + tokenId + " to array for writing to the NCP.");
            return;
        }
        out.println("Token data converted to " + tokenToString(token));

        EmberStatus status = ncp.setMfgToken(tokenId, token);
        out.println("Token write returned " + status);
    }

    private void readAllTokens(EmberNcp ncp, PrintStream out) {
        Map<EzspMfgTokenId, String> tokens = new HashMap<>();
        for (EzspMfgTokenId tokenId : EzspMfgTokenId.values()) {
            if (tokenId == EzspMfgTokenId.UNKNOWN) {
                continue;
            }
            int[] token = ncp.getMfgToken(tokenId);

            tokens.put(tokenId, tokenToString(token));
        }

        for (Entry<EzspMfgTokenId, String> token : tokens.entrySet()) {
            out.println(String.format("%-27s %s", token.getKey(), token.getValue()));
        }
    }

    private int[] convertToken(EzspMfgTokenId token, String data) {
        switch (token) {
            case EZSP_MFG_CUSTOM_EUI_64:
                if (data.length() != 16) {
                    return null;
                }
                return (new IeeeAddress(data)).getValue();

            default:
                break;
        }
        return null;
    }

    private String tokenToString(int[] token) {
        StringBuilder builder = new StringBuilder(token.length * 3);
        for (int value : token) {
            builder.append(String.format(" %02X", value));
        }
        return builder.toString();
    }
}
