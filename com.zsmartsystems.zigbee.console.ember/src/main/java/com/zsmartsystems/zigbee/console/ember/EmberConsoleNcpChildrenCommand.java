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
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetChildDataResponse;
import com.zsmartsystems.zigbee.dongle.ember.ezsp.command.EzspGetParentChildParametersResponse;

/**
 *
 * @author Chris Jackson
 *
 */
public class EmberConsoleNcpChildrenCommand extends EmberConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "ncpchildren";
    }

    @Override
    public String getDescription() {
        return "Gets the NCP child information";
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

        EzspGetParentChildParametersResponse childParameters = ncp.getChildParameters();
        out.println("Ember NCP contains " + childParameters.getChildCount() + " children"
                + ((childParameters.getChildCount() == 0) ? "." : ":"));
        for (int childId = 0; childId < childParameters.getChildCount(); childId++) {
            EzspGetChildDataResponse child = ncp.getChildInformation(childId);
            out.println(String.format("%2d  %-10s  %-10s  %s", child.getChildId(), child.getStatus(),
                    child.getChildType(), child.getChildEui64()));
        }
    }
}
