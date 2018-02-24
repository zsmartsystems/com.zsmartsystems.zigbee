/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;


/**
 * Class to implement the XBee command <b>Set API Enable</b>.
 * <p>
 * AT Command <b>AP</b></p>Enables API Mode. The device ignores this command when using SPI.
 * API mode 1 is always used.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeSetApiEnableCommand extends XBeeFrame implements XBeeCommand {
    /**
     */
    private Integer frameId;

    /**
     * 
				
     */
    private Integer mode;

    /**
     *
     * @param frameId the frameId to set as {@link Integer}
     */
    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    /**
     * 
				
     *
     * @param mode the mode to set as {@link Integer}
     */
    public void setMode(Integer mode) {
        this.mode = mode;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand(0x08);
        serializeInt8(frameId);
        serializeAtCommand("AP");
        serializeInt8(mode);

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(383);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("XBeeSetApiEnableCommand [frameId=");
        builder.append(frameId);
        builder.append(", mode=");
        builder.append(mode);
        builder.append(']');
        return builder.toString();
    }
}
