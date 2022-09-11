/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;


/**
 * Class to implement the Telegesis command <b>Become Network Manager</b>.
 * <p>
 * Local Device takes over role of Network Manager. By default the COO is the Network Manager,
 * but any other router in the network can take over this responsibility. The Network Manager
 * can change the radio channel and the PAN ID.
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisBecomeNetworkManagerCommand extends TelegesisFrame implements TelegesisCommand {
    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("AT+BECOMENM");

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
        final StringBuilder builder = new StringBuilder(126);
        builder.append("TelegesisBecomeNetworkManagerCommand [");
        if (status != null) {
            builder.append("status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
