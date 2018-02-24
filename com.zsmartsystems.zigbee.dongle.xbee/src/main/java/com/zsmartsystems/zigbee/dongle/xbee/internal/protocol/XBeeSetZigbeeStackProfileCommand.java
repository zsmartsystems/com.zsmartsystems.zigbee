/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;


/**
 * Class to implement the XBee command <b>Set ZigBee Stack Profile</b>.
 * <p>
 * AT Command <b>ZS</b></p>Set or read the Zigbee stack profile value. This must be the same on
 * all devices that will join the same
network. Effective with release 4x5E, changing ZS to a
 * different value causes all current parameters
to be written to persistent storage.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeSetZigbeeStackProfileCommand extends XBeeFrame implements XBeeCommand {
    /**
     */
    private Integer frameId;

    /**
     * 
				
     */
    private Integer stackProfile;

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
     * @param stackProfile the stackProfile to set as {@link Integer}
     */
    public void setStackProfile(Integer stackProfile) {
        this.stackProfile = stackProfile;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand(0x08);
        serializeInt8(frameId);
        serializeAtCommand("ZS");
        serializeInt8(stackProfile);

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(392);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("XBeeSetZigbeeStackProfileCommand [frameId=");
        builder.append(frameId);
        builder.append(", stackProfile=");
        builder.append(stackProfile);
        builder.append(']');
        return builder.toString();
    }
}
