/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.dongle.xbee.internal.protocol.CommandStatus;

/**
 * Class to implement the XBee command <b>Extended PAN ID Config</b>.
 * <p>
 * AT Command <b>ID</b></p>Set or read the 64-bit extended PAN ID. If set to 0, the coordinator
 * selects a random extended PAN ID,
and the router/end device joins any extended PAN ID.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeExtendedPanIdConfigResponse extends XBeeFrame implements XBeeResponse {
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
     * 
				
     */
    private ExtendedPanId extendedPanId;

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
				
     *
     * @return the extendedPanId as {@link ExtendedPanId}
     */
    public ExtendedPanId getExtendedPanId() {
        return extendedPanId;
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

        // Deserialize field "Extended Pan Id"
        extendedPanId = deserializeExtendedPanId();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(481);
        builder.append("XBeeExtendedPanIdConfigResponse [frameId=");
        builder.append(frameId);
        builder.append(", commandStatus=");
        builder.append(commandStatus);
        builder.append(", extendedPanId=");
        builder.append(extendedPanId);
        builder.append(']');
        return builder.toString();
    }
}
