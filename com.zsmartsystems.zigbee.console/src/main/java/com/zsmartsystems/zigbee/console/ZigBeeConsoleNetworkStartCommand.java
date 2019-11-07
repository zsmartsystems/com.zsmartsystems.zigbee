/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.transport.DeviceType;
import com.zsmartsystems.zigbee.transport.TransportConfig;
import com.zsmartsystems.zigbee.transport.TransportConfigOption;

/**
 * Console command to start the network.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNetworkStartCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "netstart";
    }

    @Override
    public String getDescription() {
        return "Join or Form a network as a router or coordinator.";
    }

    @Override
    public String getSyntax() {
        return "FORM|JOIN PANID EPANID";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        DeviceType deviceType;
        if (args[1].equalsIgnoreCase("form")) {
            deviceType = DeviceType.COORDINATOR;
        } else if (args[1].equalsIgnoreCase("join")) {
            deviceType = DeviceType.ROUTER;
        } else {
            throw new IllegalArgumentException("Network must be started as coordinator (form) or router (join)");
        }

        ExtendedPanId epanId;
        try {
            epanId = new ExtendedPanId(args[3]);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid format for extended PAN Id");
        }

        final TransportConfig transportOptions = new TransportConfig();
        Integer panId = parseInteger(args[2]);

        networkManager.setZigBeePanId(panId);
        networkManager.setZigBeeExtendedPanId(epanId);
        transportOptions.addOption(TransportConfigOption.DEVICE_TYPE, deviceType);
        networkManager.getZigBeeTransport().updateTransportConfig(transportOptions);
        networkManager.startup(true);
    }
}