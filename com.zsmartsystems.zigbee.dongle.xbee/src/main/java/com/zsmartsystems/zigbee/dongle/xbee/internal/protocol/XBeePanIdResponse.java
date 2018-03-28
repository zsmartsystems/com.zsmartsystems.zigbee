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
 * Class to implement the XBee command <b>PAN ID</b>.
 * <p>
 * AT Command <b>OI</b></p>Read the 16-bit PAN ID. The OI value reflects the actual 16-bit PAN
 * ID where the device is running.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeePanIdResponse extends XBeeFrame implements XBeeResponse {
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
    private Integer panId;

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
     * @return the panId as {@link Integer}
     */
    public Integer getPanId() {
        return panId;
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

        // Deserialize field "Pan Id"
        panId = deserializeInt16();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(467);
        builder.append("XBeePanIdResponse [frameId=");
        builder.append(frameId);
        builder.append(", commandStatus=");
        builder.append(commandStatus);
        builder.append(", panId=");
        builder.append(panId);
        builder.append(']');
        return builder.toString();
    }
}
