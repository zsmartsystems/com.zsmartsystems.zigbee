/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;


/**
 * Class to implement the XBee command <b>Set Network Reset</b>.
 * <p>
 * AT Command <b>NR</b></p>Resets network layer parameters on one or more modules within a
 * PAN. Responds immediately with an OK then causes a network restart. The device loses all
 * network configuration and routing information. If NR = 0: Resets network layer parameters
 * on the node issuing the command. If NR = 1: Sends broadcast transmission to reset network
 * layer parameters on all nodes in the PAN.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeSetNetworkResetCommand extends XBeeFrame implements XBeeCommand {
    /**
     */
    private Integer frameId;

    /**
     * Sends broadcast transmission to reset network layer parameters on all nodes in the PAN.
     */
    private Boolean resetRemoteDevices;

    /**
     *
     * @param frameId the frameId to set as {@link Integer}
     */
    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    /**
     * Sends broadcast transmission to reset network layer parameters on all nodes in the PAN.
     *
     * @param resetRemoteDevices the resetRemoteDevices to set as {@link Boolean}
     */
    public void setResetRemoteDevices(Boolean resetRemoteDevices) {
        this.resetRemoteDevices = resetRemoteDevices;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand(0x08);
        serializeInt8(frameId);
        serializeAtCommand("NR");
        serializeBoolean(resetRemoteDevices);

        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(386);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("XBeeSetNetworkResetCommand [frameId=");
        builder.append(frameId);
        builder.append(", resetRemoteDevices=");
        builder.append(resetRemoteDevices);
        builder.append(']');
        return builder.toString();
    }
}
