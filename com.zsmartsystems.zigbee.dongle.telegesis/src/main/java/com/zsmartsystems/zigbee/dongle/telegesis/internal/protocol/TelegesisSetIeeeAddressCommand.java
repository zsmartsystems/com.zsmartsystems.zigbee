/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 * Class to implement the Telegesis command <b>Set Ieee Address</b>.
 * <p>
 * Sets the local node’s unique EUI64 identifier.
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisSetIeeeAddressCommand extends TelegesisFrame implements TelegesisCommand {
    /**
     * Command field
     */
    private IeeeAddress ieeeAddress;

    /**
     *
     * @param ieeeAddress the ieeeAddress to set as {@link IeeeAddress}
     */
    public void setIeeeAddress(IeeeAddress ieeeAddress) {
        this.ieeeAddress = ieeeAddress;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("ATS04=");
        serializeIeeeAddress(ieeeAddress);

        return getPayload();
    }

    @Override
    public boolean deserialize(int[] data) {
        // Handle standard status responses (ie. OK / ERROR)
        if (handleIncomingStatus(data)) {
            return true;
        }

        initialiseDeserializer(data);


        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(210);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("TelegesisSetIeeeAddressCommand [ieeeAddress=");
        builder.append(ieeeAddress);
        if (status != null) {
            builder.append(", status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
