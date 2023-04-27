/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

/**
 * Class to implement the XBee command <b>Scan Channels</b>.
 * <p>
 * AT Command <b>SC</b></p>Set or read the list of channels to scan. Coordinator - Bit field
 * list of channels to choose from prior to starting network. Router/End Device - Bit field list
 * of channels scanned to find a Coordinator/Router to join. Write changes to SC using the WR
 * command to preserve the SC setting if a power cycle occurs.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeScanChannelsResponse extends XBeeFrame implements XBeeResponse {
    /**
     * Response field
     */
    private Integer frameId;

    /**
     * Response field
     */
    private CommandStatus commandStatus;

    /**
     * Response field
     */
    private Integer channels;

    /**
     *
     * @return the frameId as {@link Integer}
     */
    public Integer getFrameId() {
        return frameId;
    }

    /**
     *
     * @return the commandStatus as {@link CommandStatus}
     */
    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

    /**
     *
     * @return the channels as {@link Integer}
     */
    public Integer getChannels() {
        return channels;
    }


    @Override
    public void deserialize(int[] incomingData) {
        initialiseDeserializer(incomingData);

        // Deserialize the fields for the response

        // Deserialize field "Frame ID"
        frameId = deserializeInt8();
        deserializeAtCommand();

        // Deserialize field "Command Status"
        commandStatus = deserializeCommandStatus();
        if (commandStatus != CommandStatus.OK || isComplete()) {
            return;
        }

        // Deserialize field "Channels"
        channels = deserializeInt16();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(474);
        builder.append("XBeeScanChannelsResponse [frameId=");
        builder.append(frameId);
        builder.append(", commandStatus=");
        builder.append(commandStatus);
        if (commandStatus == CommandStatus.OK) {
            builder.append(", channels=");
            builder.append(channels);
        }
        builder.append(']');
        return builder.toString();
    }
}
