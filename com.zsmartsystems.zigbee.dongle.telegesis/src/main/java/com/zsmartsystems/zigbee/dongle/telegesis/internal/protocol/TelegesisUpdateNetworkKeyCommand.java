/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;


/**
 * Class to implement the Telegesis command <b>Update Network Key</b>.
 * <p>
 * Updates the Network Key with a new key. If the value in S08 is non-zero and is not the current
 * key, it will be used for the updated key. If S08 is zero or the current key, a random value will be
 * generated.
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisUpdateNetworkKeyCommand extends TelegesisFrame implements TelegesisCommand {
    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("AT+KEYUPD");

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
        final StringBuilder builder = new StringBuilder(122);
        builder.append("TelegesisUpdateNetworkKeyCommand [");
        if (status != null) {
            builder.append("status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
