/**
 * Copyright (c) 2016-2024 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;


/**
 * Class to implement the XBee command <b>Set Reset</b>.
 * <p>
 * AT Command <b>RE</b></p>This command sets all parameters except ZS and KY to their default
 * values. To change ZS and KY, you must explicitly set them. In order for the default parameters
 * to persist through subsequent resets, send a separate WR command after RE. Read-only
 * parameters are not directly affected by RE and reflect the current state of the device.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeSetResetCommand extends XBeeFrame implements XBeeCommand {
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
        serializeAtCommand("RE");

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(289);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("XBeeSetResetCommand [frameId=");
        builder.append(frameId);
        builder.append(']');
        return builder.toString();
    }
}
