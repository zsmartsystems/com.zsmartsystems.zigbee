/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;


/**
 * Class to implement the Telegesis command <b>Get Pan ID</b>.
 * <p>
 * Gets the preferred PAN ID
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisGetPanIdCommand extends TelegesisFrame implements TelegesisCommand {
    /**
     * Response field
     */
    private Integer panId;

    /**
     *
     * @return the panId as {@link Integer}
     */
    public Integer getPanId() {
        return panId;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("ATS02?");

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

        // Deserialize field "pan ID"
        panId = deserializeInt16();

        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(204);
        builder.append("TelegesisGetPanIdCommand [panId=");
        builder.append(panId);
        if (status != null) {
            builder.append(", status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
