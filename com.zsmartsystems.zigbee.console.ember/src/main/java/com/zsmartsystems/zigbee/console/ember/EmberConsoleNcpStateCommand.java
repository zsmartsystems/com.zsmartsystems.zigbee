/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console.ember;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.dongle.ember.EmberNcp;
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
        out.println("Ember NCP state is " + status);
    }

}
