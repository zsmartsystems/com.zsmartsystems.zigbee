/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;


/**
 * Class to implement the Telegesis command <b>Leave Network</b>.
 * <p>
 * Disassociate Local Device From PAN
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisLeaveNetworkCommand extends TelegesisFrame implements TelegesisCommand {
    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("AT+DASSL");

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
        final StringBuilder builder = new StringBuilder(118);
        builder.append("TelegesisLeaveNetworkCommand [");
        if (status != null) {
            builder.append("status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
