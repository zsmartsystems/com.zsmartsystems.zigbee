/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.app.pollcontrol.ZclPollControlExtension;

import java.io.PrintStream;

public class ZigBeeConsoleSetCheckInTimeoutCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "setcheckintimeout";
    }

    @Override
    public String getDescription() {
        return "Sets the check in fast poll timeout";
    }

    @Override
    public String getSyntax() {
        return "TIMEOUT";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        Integer timeout = parseInteger(args[1]);

        ZclPollControlExtension extension = (ZclPollControlExtension) networkManager.getExtension(ZclPollControlExtension.class);

        if(timeout < 0) {
            extension.setTimeout(-1);
            out.println("Fast Poll Configured [startFastPolling = false, fastPollTimeout = 0]");
        } else {
            extension.setTimeout(timeout);
            out.println("Fast Poll Configured [startFastPolling = true, fastPollTimeout = " + timeout + "]");
        }
    }
}
