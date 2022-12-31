/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;
import com.zsmartsystems.zigbee.transport.TrustCentreJoinMode;

/**
 * Handles management of the trust centre
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleTrustCentreCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "trustcentre";
    }

    @Override
    public String getDescription() {
        return "Configures the trust centre";
    }

    @Override
    public String getSyntax() {
        return "JOINMODE [DENY|INSECURE|SECURE|INSTALLCODE]";
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

        switch (args[1].toUpperCase()) {
            case "JOINMODE":
                setJoinMode(networkManager, args, out);
                break;
        }
    }

    private ZigBeeStatus setJoinMode(ZigBeeNetworkManager networkManager, String[] args, PrintStream out) {
        TrustCentreJoinMode joinMode;
        try {
            joinMode = TrustCentreJoinMode.valueOf("TC_JOIN_" + args[2].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Join mode is incorrect value.");
        }

        TransportConfig config = new TransportConfig(TransportConfigOption.TRUST_CENTRE_JOIN_MODE, joinMode);
        networkManager.getZigBeeTransport().updateTransportConfig(config);

        out.println("Join mode set to " + joinMode);

        return config.getResult(TransportConfigOption.TRUST_CENTRE_JOIN_MODE);
    }
}
