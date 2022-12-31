/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareCallback;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareStatus;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportFirmwareUpdate;
import com.zsmartsystems.zigbee.transport.ZigBeeTransportTransmit;

/**
 * Perform a firmware update on the dongle - if the dongle supports this feature.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleFirmwareCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "firmware";
    }

    @Override
    public String getDescription() {
        return "Updates the dongle firmware";
    }

    @Override
    public String getSyntax() {
        return "[VERSION | CANCEL | FILE]";
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

        ZigBeeTransportTransmit dongle = networkManager.getZigBeeTransport();
        if (!(dongle instanceof ZigBeeTransportFirmwareUpdate)) {
            throw new IllegalStateException("Dongle does not implement firmware updates");
        }
        ZigBeeTransportFirmwareUpdate firmwareUpdate = (ZigBeeTransportFirmwareUpdate) dongle;

        if (args[1].toLowerCase().equals("version")) {
            out.println("Dongle firmware version is currently " + firmwareUpdate.getFirmwareVersion());
            return;
        }

        if (args[1].toLowerCase().equals("cancel")) {
            out.println("Cancelling dongle firmware update!");
            firmwareUpdate.cancelUpdateFirmware();
            return;
        }

        networkManager.shutdown();

        File firmwareFile = new File(args[1]);
        InputStream firmwareStream;
        try {
            firmwareStream = new FileInputStream(firmwareFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Firmware file is not found");
        }

        firmwareUpdate.updateFirmware(firmwareStream, new ZigBeeTransportFirmwareCallback() {
            @Override
            public void firmwareUpdateCallback(ZigBeeTransportFirmwareStatus status) {
                out.println("Dongle firmware status: " + status + ".");
            }
        });

        out.println("Starting dongle firmware update...");
    }
}
