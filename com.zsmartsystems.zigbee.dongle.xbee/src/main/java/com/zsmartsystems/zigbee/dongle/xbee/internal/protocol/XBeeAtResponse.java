/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.CommandStatus;

/**
 * Class to implement the XBee command <b>AT</b>.
 * <p>
 * A device sends this frame in response to an AT Command (0x08 or 0x09) frame. Some commands send
 * back multiple frames; for example, the ND command.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeAtResponse extends XBeeFrame implements XBeeResponse {
    /**
     * Response field
     * The frame Id
     */
    private Integer frameId;

    /**
     * Response field
     * Command name: two ASCII characters that identify the command.
     */
    private String atCommand;

    /**
     * Response field
     */
    private CommandStatus commandStatus;

    /**
     * Response field
     * The register data in binary format. If the host sets the register, the device does not return
     * this field.
     */
    private int[] commandData;

    /**
     * The frame Id
     *
     * @return the frameId as {@link Integer}
     */
    public Integer getFrameId() {
        return frameId;
    }

    /**
     * Command name: two ASCII characters that identify the command.
     *
     * @return the atCommand as {@link String}
     */
    public String getAtCommand() {
        return atCommand;
    }

    /**
     *
     * @return the commandStatus as {@link CommandStatus}
     */
    public CommandStatus getCommandStatus() {
        return commandStatus;
    }

    /**
     * The register data in binary format. If the host sets the register, the device does not return
     * this field.
     *
     * @return the commandData as {@link int[]}
     */
    public int[] getCommandData() {
        return commandData;
    }


    @Override
    public void deserialize(int[] incomingData) {
        initialiseDeserializer(incomingData);

        // Deserialize the fields for the response

        // Deserialize field "Frame Id"
        frameId = deserializeInt8();

        // Deserialize field "AT Command"
        atCommand = deserializeAtCommand();

        // Deserialize field "Command Status"
        commandStatus = deserializeCommandStatus();

        // Deserialize field "Command Data"
        commandData = deserializeData();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(464);
        builder.append("XBeeAtResponse [frameId=");
        builder.append(frameId);
        builder.append(", atCommand=");
        builder.append(atCommand);
        builder.append(", commandStatus=");
        builder.append(commandStatus);
        builder.append(", commandData=");
        if (commandData == null) {
            builder.append("null");
        } else {
            for (int cnt = 0; cnt < commandData.length; cnt++) {
                if (cnt > 0) {
                    builder.append(' ');
                }
                builder.append(String.format("%02X", commandData[cnt]));
            }
        }
        builder.append(']');
        return builder.toString();
    }
}
