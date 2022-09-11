/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;


/**
 * Class to implement the XBee command <b>Get API Mode</b>.
 * <p>
 * AT Command <b>AO</b></p>Configure the options for API. The current options select the type
 * of receive API frame to send out the UART for received RF data packets. 0 Default API Rx
 * Indicator enabled 1 Default API Explicit Rx Indicator - 0x91, this is for Explicit
 * Addressing data frames. 3 Enable ZDO passthrough of ZDO requests to the serial port that are
 * not supported by the stack, as well as Simple_Desc_req, Active_EP_req, and
 * Match_Desc_req.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeGetApiModeCommand extends XBeeFrame implements XBeeCommand {
    /**
     */
    private Integer frameId;

    /**
     *
     * @param frameId the frameId to set as {@link Integer}
     */
    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand(0x08);
        serializeInt8(frameId);
        serializeAtCommand("AO");

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(291);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("XBeeGetApiModeCommand [frameId=");
        builder.append(frameId);
        builder.append(']');
        return builder.toString();
    }
}
