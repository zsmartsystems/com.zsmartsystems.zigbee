/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;
import com.zsmartsystems.zigbee.database.ZigBeeNetworkBackupDao;
import com.zsmartsystems.zigbee.database.ZigBeeNodeDao;

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
                listBackups(out, networkManager);
                break;
            case "BACKUP":
                createBackup(out, networkManager, Long.parseLong(args[2]));
                break;
            case "RESTORE":
                restoreBackup(out, networkManager, Long.parseLong(args[2]));
                break;
            default:
                throw new IllegalArgumentException("Unknown option '" + args[1] + "'");
        }
    }

    private void listBackups(PrintStream out, ZigBeeNetworkManager networkManager) {
        Map<Long, ZigBeeNetworkBackupDao> sortedBackups = new TreeMap<>();
        for (ZigBeeNetworkBackupDao backup : networkManager.listBackups()) {
            sortedBackups.put(backup.getDate().getTime(), backup);
        }

        out.println(
                "DATE                      UUID                                  PANID  EPANID            CHANNEL     COORDINATOR       NODES");
        for (ZigBeeNetworkBackupDao backup : sortedBackups.values()) {
            ZigBeeNodeDao coordinator = null;
            for (ZigBeeNodeDao node : backup.getNodes()) {
                if (node.getNetworkAddress() == 0) {
                    coordinator = node;
                    break;
                }
            }
            out.println(
                    String.format("%s  %s  %04X   %s  %s  %s  %d", backup.getDate().toInstant().toString(),
                            backup.getUuid(),
                            backup.getPan(), backup.getEpan(), backup.getChannel(),
                            (coordinator != null ? coordinator.getIeeeAddress() : "        "),
                            backup.getNodes().size()));
        }
    }

    private void createBackup(PrintStream out, ZigBeeNetworkManager networkManager, Long gatewayId) {
        String  macAddress = networkManager.createBackup(gatewayId);
        if (macAddress != null) {
            out.println("Error creating backup!!");
        } else {
            out.println("Backup created with macAddress " + macAddress);
        }
    }

    private void restoreBackup(PrintStream out, ZigBeeNetworkManager networkManager, Long gatewayId) {
        if (networkManager.restoreBackup(gatewayId) == ZigBeeStatus.SUCCESS) {
            out.println("Backup restored from " + gatewayId);
        } else {
            out.println("Error restoring backup " + gatewayId);
        }
    }
}