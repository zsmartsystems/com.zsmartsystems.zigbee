/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.security.ZigBeeKey;
import com.zsmartsystems.zigbee.transport.DeviceType;

/**
 * Console command to backup the network. This prints the important information required to backup the coordinator to a
 * string which can then be parsed and set back into the same, or a different coordinator.
 * Key counters are incremented with an assumption of 1 frame per second since the backup.
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNetworkBackupCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    protected ZigBeeConsoleArgument initializeArguments() {
        return null;
    }

    @Override
    public String getCommand() {
        return "netbackup";
    }

    @Override
    public String getDescription() {
        return "Backup or restore the coordinator.";
    }

    @Override
    public String getSyntax() {
        return "[RESTORE STRING]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length > 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        if (args.length == 1) {
            out.println(netBackup(networkManager));
            return;
        }

        String[] parameters = args[1].split("\\>");
        netRestore(networkManager, parameters, out);
    }

    private String netBackup(ZigBeeNetworkManager networkManager) {
        TimeZone timezone = TimeZone.getTimeZone("UTC");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        formatter.setTimeZone(timezone);
        String nowAsIso = formatter.format(new Date());

        int pan = networkManager.getZigBeePanId();
        ExtendedPanId epan = networkManager.getZigBeeExtendedPanId();
        ZigBeeChannel channel = networkManager.getZigBeeChannel();
        ZigBeeKey networkKey = networkManager.getZigBeeNetworkKey();
        ZigBeeKey linkKey = networkManager.getZigBeeLinkKey();

        StringBuilder builder = new StringBuilder();
        builder.append(nowAsIso);
        builder.append('>');
        builder.append(DeviceType.COORDINATOR); // Future proof
        builder.append('>');
        builder.append(String.format("%04X", pan));
        builder.append('>');
        builder.append(epan.toString());
        builder.append('>');
        builder.append(channel.toString());
        builder.append('>');
        builder.append(networkKey.toString());
        builder.append('>');
        if (networkKey.hasSequenceNumber()) {
            builder.append(String.format("%02X", networkKey.getSequenceNumber()));
        }
        builder.append('>');
        if (networkKey.hasIncomingFrameCounter()) {
            builder.append(String.format("%08X", networkKey.getIncomingFrameCounter()));
        }
        builder.append('>');
        if (networkKey.hasOutgoingFrameCounter()) {
            builder.append(String.format("%08X", networkKey.getOutgoingFrameCounter()));
        }
        builder.append('>');
        builder.append(linkKey.toString());
        builder.append('>');
        if (linkKey.hasSequenceNumber()) {
            builder.append(String.format("%02X", linkKey.getSequenceNumber()));
        }
        builder.append('>');
        if (linkKey.hasIncomingFrameCounter()) {
            builder.append(String.format("%08X", linkKey.getIncomingFrameCounter()));
        }
        builder.append('>');
        if (linkKey.hasOutgoingFrameCounter()) {
            builder.append(String.format("%08X", linkKey.getOutgoingFrameCounter()));
        }

        return builder.toString();
    }

    private void netRestore(ZigBeeNetworkManager networkManager, String[] parameters, PrintStream out)
            throws IllegalArgumentException {
        int secondsSinceBackup;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
            Date backupTime = formatter.parse(parameters[0]);
            secondsSinceBackup = (int) ((new Date().getTime() - backupTime.getTime()) / 1000);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error parsing backup time");
        }

        int pan = Integer.parseInt(parameters[2], 16);
        ExtendedPanId epan = new ExtendedPanId(parameters[3]);
        ZigBeeChannel channel = ZigBeeChannel.valueOf(parameters[4]);
        ZigBeeKey networkKey = parseKey(secondsSinceBackup, parameters[5], parameters[6], parameters[7], parameters[8]);
        ZigBeeKey linkKey = parseKey(secondsSinceBackup, parameters[9], parameters[10], parameters[11], parameters[12]);

        out.println("Restoring network as " + parameters[1] + " after " + getTimeSince(secondsSinceBackup));
        out.println("PAN ID                       :");
        out.println("Extended PAN ID              :");
        out.println("Channel                      :");
        out.println("Network Key                  :" + networkKey);
        if (networkKey.hasSequenceNumber()) {
            out.println("Network Key Sequence         :" + networkKey.getSequenceNumber());
        }
        if (networkKey.hasIncomingFrameCounter()) {
            out.println("Network Key Incoming Counter :" + networkKey.getIncomingFrameCounter());
        }
        if (networkKey.hasIncomingFrameCounter()) {
            out.println("Network Key Outgoing Counter :" + networkKey.getOutgoingFrameCounter());
        }
        out.println("Link Key       :" + linkKey);
        if (linkKey.hasSequenceNumber()) {
            out.println("Link Key Sequence            :" + linkKey.getSequenceNumber());
        }
        if (linkKey.hasIncomingFrameCounter()) {
            out.println("Link Key Incoming Counter    :" + linkKey.getIncomingFrameCounter());
        }
        if (linkKey.hasIncomingFrameCounter()) {
            out.println("Link Key Outgoing Counter    :" + linkKey.getOutgoingFrameCounter());
        }

        networkManager.setZigBeePanId(pan);
        networkManager.setZigBeeExtendedPanId(epan);
        networkManager.setZigBeeChannel(channel);
        networkManager.setZigBeeNetworkKey(networkKey);
        networkManager.setZigBeeLinkKey(linkKey);
        networkManager.startup(true);
    }

    private ZigBeeKey parseKey(int secondsSinceBackup, String keyString, String sequence, String incount,
            String outcount) {
        ZigBeeKey key = new ZigBeeKey(keyString);
        try {
            if (!sequence.isEmpty()) {
                key.setSequenceNumber(Integer.parseInt(sequence, 16));
            }
            if (!incount.isEmpty()) {
                key.setIncomingFrameCounter(Integer.parseInt(incount, 16) + secondsSinceBackup);
            }
            if (!outcount.isEmpty()) {
                key.setOutgoingFrameCounter(Integer.parseInt(outcount, 16) + secondsSinceBackup);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing key parameters");
        }

        return key;
    }

    private String getTimeSince(int secondsSince) {
        if (secondsSince < 60) {
            return secondsSince + " seconds";
        }
        if (secondsSince < 3600) {
            return secondsSince / 60 + " minutes";
        }
        if (secondsSince < 86400) {
            return secondsSince / 3600 + " hours";
        }
        return secondsSince / 86400 + " days";
    }
}