/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.console;

import java.io.PrintStream;
import java.util.concurrent.ExecutionException;

import com.zsmartsystems.zigbee.ZigBeeChannel;
import com.zsmartsystems.zigbee.ZigBeeNetworkManager;
import com.zsmartsystems.zigbee.ZigBeeStatus;

/**
 * Sets the current ZigBee channel
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeConsoleChannelCommand extends ZigBeeConsoleAbstractCommand {
    @Override
    public String getCommand() {
        return "channel";
    }

    @Override
    public String getDescription() {
        return "Sets the ZigBee channel.";
    }

    @Override
    public String getSyntax() {
        return "CHANNEL";
    }

    @Override
    public String getHelp() {
        return "";
    }

    @Override
    public void process(ZigBeeNetworkManager networkManager, String[] args, PrintStream out)
            throws IllegalArgumentException, InterruptedException, ExecutionException {
        if (args.length > 2) {
            throw new IllegalArgumentException("Invalid number of arguments");
        }

        if (args.length == 1) {
            out.println("Channel is currently " + networkManager.getZigBeeChannel().toString());
            return;
        }

        String channelParam = args[1];
        ZigBeeChannel channel = null;

        try {
            Integer channelInt = parseInteger(channelParam);
            channel = ZigBeeChannel.create(channelInt);
        } catch (IllegalArgumentException e) {
            // Eatme
        }

        if (channel == null) {
            try {
                channel = ZigBeeChannel.valueOf(channelParam);
            } catch (IllegalArgumentException e) {
                // Eatme
            }
        }

        if (channel == null) {
            throw new IllegalArgumentException("Unable to pass ZigBeeChannel from " + channelParam);
        }

        if (channel == ZigBeeChannel.UNKNOWN) {
            throw new IllegalArgumentException("Invalid ZigBee channel " + channelParam);
        }

        ZigBeeStatus response = networkManager.setZigBeeChannel(channel);
        out.println("Channel set to " + channel + " completed with state " + response);
    }
}
