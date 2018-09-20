/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.ZigBeeEndpoint;
import com.zsmartsystems.zigbee.ZigBeeEndpointAddress;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.app.time.ZclTimeClient;
import com.zsmartsystems.zigbee.app.time.ZigBeeTimeExtension;
import com.zsmartsystems.zigbee.zcl.field.ZigBeeUtcTime;

/**
 * Provides information about time servers on the network
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleTimeCommand extends ZigBeeConsoleAbstractCommand {
    private static final String NOT_SET = "Not Set";
    private static final String SECONDS = " seconds";
    private static final String MINUTES = " minutes";

    @Override
    public String getCommand() {
        return "time";
    }

    @Override
    public String getDescription() {
        return "Gets information about time servers";
    }

    @Override
    public String getSyntax() {
        return "[MANAGER | ENDPOINT] [POLL | GET | SET] [VALUE]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {

        ZigBeeTimeExtension extension = (ZigBeeTimeExtension) networkManager.getExtension(ZigBeeTimeExtension.class);
        if (extension == null) {
            throw new IllegalStateException("Time extension not found");
        }

        if (args.length == 1) {
            ZigBeeUtcTime dstStart = extension.getDstStart();
            ZigBeeUtcTime dstEnd = extension.getDstEnd();

            out.println("Current Time : " + ZigBeeUtcTime.now().toString());
            out.println("DST Start    : " + ((dstStart == null) ? NOT_SET : dstStart.toString()));
            out.println("DST Finish   : " + ((dstEnd == null) ? NOT_SET : dstEnd.toString()));
            out.println("DST Offset   : " + extension.getDstOffset() / 60 + MINUTES);
            out.println("Update delta : " + extension.getUpdateDelta() + SECONDS);
            out.println("Poll period  : " + extension.getPollPeriod() + SECONDS);
            out.println("Master       : " + extension.isMaster());
            out.println("Superceding  : " + extension.isSuperceding());
            out.println();
            showClient(out, extension);
            return;
        }

        if (args[1].equalsIgnoreCase("manager")) {
            processManagerOptions(extension, args, out);
            return;
        }

        final ZigBeeEndpoint endpoint = getEndpoint(networkManager, args[1]);

        if (args.length == 2) {
            return;
        }

        final ZclTimeClient client = extension.getTimeClient(endpoint.getEndpointAddress());
        if (client == null) {
            throw new IllegalStateException(
                    "Time client not found for endpoint " + endpoint.getEndpointAddress().toString());
        }

        switch (args[2].toLowerCase()) {
            case "set":
                break;
            case "get":
                break;
            default:
                throw new IllegalArgumentException("Unknown endpoint command option " + args[2]);
        }
    }

    private void processManagerOptions(ZigBeeTimeExtension extension, String[] args, PrintStream out) {
        if (args.length < 4) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        switch (args[2].toLowerCase()) {
            case "set":
                processManagerSettings(extension, args, out);
                break;
            default:
                throw new IllegalArgumentException("Unknown manager command option " + args[2]);
        }
    }

    private void processManagerSettings(ZigBeeTimeExtension extension, String[] args, PrintStream out) {
        if (args.length < 5) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        switch (args[3].toLowerCase()) {
            case "poll":
                extension.setPollPeriod(parseInteger(args[4]));
                out.println("Time manager poll period set to " + extension.getPollPeriod() + SECONDS);
                break;
            case "delta":
                extension.setUpdateDelta(parseInteger(args[4]));
                out.println("Time manager update delta set to " + extension.getUpdateDelta() + SECONDS);
                break;
            case "master":
                extension.setMaster(Boolean.parseBoolean(args[4]));
                out.println("Time manager set master clock source to " + extension.isMaster());
                break;
            case "superceding":
                extension.setSuperceding(Boolean.parseBoolean(args[4]));
                out.println("Time manager set superceding clock source to " + extension.isSuperceding());
                break;
            default:
                throw new IllegalArgumentException("Unknown manager setting option " + args[2]);
        }
    }

    private void showClient(PrintStream out, ZigBeeTimeExtension extension) {
        Map<ZigBeeEndpointAddress, ZclTimeClient> clients = new TreeMap<>();

        // Provide a sorted output, sorted by address
        for (ZclTimeClient client : extension.getTimeClients()) {
            clients.put(client.getZigBeeAddress(), client);
        }

        out.println(
                "Address    Last Poll             Dif  Last Update           By      Delta  s/24hr  Valid Until           DST Set  Synced  Master  Master-DST  Superceded");

        for (ZclTimeClient client : clients.values()) {
            ZigBeeUtcTime lastRequestedTime = client.getLastRequestedTime();
            ZigBeeUtcTime lastUpdateTime = extension.getLastUpdateTime(client.getZigBeeAddress());
            ZigBeeUtcTime validUntil = client.getValidUntil();
            out.println(String.format(
                    "%-9s  %-20s  %3s  %-20s  %-6s  % 2dsec  %+6.1f  %-20s  %-5b    %-5b   %-5b   %-5b       %-5b",
                    client.getZigBeeAddress().toString(),
                    ((lastRequestedTime == null) ? NOT_SET : lastRequestedTime.toString()),
                    ((lastRequestedTime == null) ? "" : Integer.toString(client.getCurrentDelta())),
                    ((lastUpdateTime == null) ? NOT_SET : lastUpdateTime.toString()),
                    extension.getLastUpdateMethod(client.getZigBeeAddress()).toString(), client.getLastDelta(),
                    client.getDailyDriftRate(), ((validUntil == null) ? NOT_SET : validUntil.toString()),
                    client.isDstSet(), client.isRemoteSynchronized(), client.isRemoteMaster(),
                    client.isRemoteMasterDst(), client.isRemoteSuperceding()));
        }
    }
}
