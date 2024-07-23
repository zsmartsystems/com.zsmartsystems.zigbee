/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeNode;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleNetworkJoinCommand extends ZigBeeConsoleAbstractCommand {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> scheduledFuture;

    @Override
    public String getCommand() {
        return "join";
    }

    @Override
    public String getDescription() {
        return "Enable or disable network join.";
    }

    @Override
    public String getSyntax() {
        return "[ENABLE|PERIOD|PERMANENT|DISABLE] [NODE]";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException {
        if (args.length < 2 | args.length > 3) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        ZigBeeNode node;
        if (args.length == 3) {
            node = getNode(networkManager, args[2]);
        } else {
            node = null;
        }

        if ("enable".equalsIgnoreCase(args[1])) {
            if(scheduledFuture == null) {
                enableJoin(networkManager, node, 254, out);
            } else {
                out.println("Permit join already enabled permanently. Ignoring.");
            }
        } else if ("permanent".equalsIgnoreCase(args[1])) {
            if(scheduledFuture == null) {
                scheduledFuture = scheduler.scheduleAtFixedRate(() -> enableJoin(networkManager, node, 254, out), 0, 240, TimeUnit.SECONDS);
            } else {
                out.println("Permit join already enabled permanently. Ignoring.");
            }
        } else if ("disable".equalsIgnoreCase(args[1])) {
            if(scheduledFuture != null) {
                scheduledFuture.cancel(true);
                scheduledFuture = null;
            }
            disableJoin(networkManager, node, out);
        } else {
            if(scheduledFuture == null) {
                Integer duration = Integer.parseInt(args[1]);
                if(duration <= 0) {
                    throw new IllegalArgumentException("Duration must be greater than 0 (exclusive)");
                }
                if(duration > 254) {
                    throw new IllegalArgumentException("Duration must be less than 254 (inclusive)");
                }
                enableJoin(networkManager, node, duration, out);
            } else {
                out.println("Permit join already enabled permanently. Ignoring.");
            }
        }
    }

    private void enableJoin(ZigBeeNetworkManager networkManager, ZigBeeNode node, Integer duration, PrintStream out) {
        if (node != null) {
            node.permitJoin(duration);
            out.println("Permit join enable node " + node.getNetworkAddress() + " success.");
        } else {
            networkManager.permitJoin(duration);
            out.println("Permit join enable broadcast success.");
        }
    }

    private void disableJoin(ZigBeeNetworkManager networkManager, ZigBeeNode node, PrintStream out) {
        if (node != null) {
            node.permitJoin(0);
            out.println("Permit join disable " + node.getNetworkAddress() + " success.");
        } else {
            networkManager.permitJoin(0);
            out.println("Permit join disable broadcast success.");
        }
    }
}