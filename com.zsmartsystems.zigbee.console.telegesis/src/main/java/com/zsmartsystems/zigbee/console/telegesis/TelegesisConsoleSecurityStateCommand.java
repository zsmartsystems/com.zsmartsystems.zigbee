/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.telegesis;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.console.ZigBeeConsoleArgument;
import com.zsmartsystems.zigbee.dongle.telegesis.TelegesisNcp;

/**
 * Displays information about the current security configuration of the dongle
 *
 * @author Chris Jackson
 *
 */
public class TelegesisConsoleSecurityStateCommand extends TelegesisConsoleAbstractCommand {
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "ncpsecuritystate";
    }

    @Override
    public String getDescription() {
        return "Gets the current dongle security state and configuration";
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
        TelegesisNcp ncp = getTelegesisNcp(networkManager);

        Long frameCounter = ncp.getSecurityFrameCounter();
        out.println("Frame Counter    : " + ((frameCounter == null) ? "Unknown" : frameCounter.toString()));
    }
}
