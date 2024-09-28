/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;

/**
 * Console command to backup the network.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNetworkBackupCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "netbackup";
    }

    @Override
    public String getDescription() {
        return "Backup or restore the network.";
    }

    @Override
    public String getSyntax() {
        return "[BACKUP] [RESTORE UUID] [LIST]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        switch (args[1].toUpperCase()) {
            case "LIST":
                listBackups();
                break;
            case "BACKUP":
                createBackup();
                break;
            case "RESTORE":
                restoreBackup();
                break;
            default:
                throw new IllegalArgumentException("Unknown option '" + args[1] + "'");
        }
    }

    private void listBackups() {

    }

    private void createBackup() {

    }

    private void restoreBackup() {

    }
}