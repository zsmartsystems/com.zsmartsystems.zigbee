/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberMfglib;
import com.zsmartsystems.zigbee.dongle.ember.ZigBeeDongleEzsp;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpMfglibCommand extends EmberConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "ncpmfglib";
    }

    @Override
    public String getDescription() {
        return "Uses the mfglib test features of the NCP. Note that these commands should not be used without understanding MfgLib";
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

        switch (args[1].toLowerCase()) {
            case "start":
                start(networkManager, out);
                break;
            case "end":
                end(networkManager, out);
                break;
            case "tone":
                switch (args[2].toLowerCase()) {
                    case "start":
                        toneStart(networkManager, out);
                        break;
                    case "stop":
                        toneStop(networkManager, out);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown option specified: tone " + args[2].toUpperCase());
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown option specified: " + args[1].toUpperCase());
        }
    }

    private void start(ZigBeeNetworkManager networkManager, PrintStream out) {
        EmberMfglib mfglib = getMfglib(networkManager);
        if (!mfglib.doMfglibStart()) {
            throw new IllegalStateException("NCP MfgLib start failed");
        }

        out.println("NCP MfgLib started.");
    }

    private void end(ZigBeeNetworkManager networkManager, PrintStream out) {
        EmberMfglib mfglib = getMfglib(networkManager);
        if (!mfglib.doMfglibEnd()) {
            throw new IllegalStateException("NCP MfgLib end failed");
        }

        out.println("NCP MfgLib ended.");
    }

    private void toneStart(ZigBeeNetworkManager networkManager, PrintStream out) {
        EmberMfglib mfglib = getMfglib(networkManager);
        if (!mfglib.doMfglibStartTone()) {
            throw new IllegalStateException("NCP MfgLib start tone failed");
        }

        out.println("NCP MfgLib tone started.");
    }

    private void toneStop(ZigBeeNetworkManager networkManager, PrintStream out) {
        EmberMfglib mfglib = getMfglib(networkManager);
        if (!mfglib.doMfglibStopTone()) {
            throw new IllegalStateException("NCP MfgLib stop tone failed");
        }

        out.println("NCP MfgLib tone stopped.");
    }

    private EmberMfglib getMfglib(ZigBeeNetworkManager networkManager) {
        if (!(networkManager.getZigBeeTransport() instanceof ZigBeeDongleEzsp)) {
            throw new IllegalArgumentException("Dongle is not an Ember NCP.");
        }
        ZigBeeDongleEzsp dongle = (ZigBeeDongleEzsp) networkManager.getZigBeeTransport();
        if (dongle == null) {
            throw new IllegalStateException("Dongle is not an Ember NCP.");
        }
        EmberMfglib mfglib = dongle.getEmberMfglib(null);

        return mfglib;
    }
}
