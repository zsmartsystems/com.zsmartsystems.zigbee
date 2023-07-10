/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;

import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 * Class to implement the Telegesis command <b>Get Trust Centre Link Key</b>.
 * <p>
 * The link key which can be written using the password. The default password for R3xx is
 * “password”.
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisGetTrustCentreLinkKeyCommand extends TelegesisFrame implements TelegesisCommand {
    /**
     * Response field
     */
    private ZigBeeKey linkKey;

    /**
     *
     * @return the linkKey as {@link ZigBeeKey}
     */
    public ZigBeeKey getLinkKey() {
        return linkKey;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("ATS09?");

        return getPayload();
    }

    @Override
    public boolean deserialize(int[] data) {
        // Handle standard status responses (ie. OK / ERROR)
        if (handleIncomingStatus(data)) {
            return true;
        }

        initialiseDeserializer(data);

        // Deserialize the fields for the response

        // Deserialize field "link key"
        linkKey = deserializeZigBeeKey();

        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(217);
        builder.append("TelegesisGetTrustCentreLinkKeyCommand [linkKey=");
        builder.append(linkKey);
        if (status != null) {
            builder.append(", status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
