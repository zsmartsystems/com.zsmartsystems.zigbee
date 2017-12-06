/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;


/**
 * Class to implement the Telegesis command <b>Set Frame Cnt</b>.
 * <p>
 * Sets the security frame counter
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisSetFrameCntCommand extends TelegesisFrame implements TelegesisCommand {
    /**
     * Command field
     */
    private Long frameCnt;

    /**
     * Response field
     */
    private Long frameCntConfirmed;

    /**
     *
     * @param frameCnt the frameCnt to set as {@link Long}
     */
    public void setFrameCnt(Long frameCnt) {
        this.frameCnt = frameCnt;
    }

    /**
     *
     * @return the frameCntConfirmed as {@link Long}
     */
    public Long getFrameCntConfirmed() {
        return frameCntConfirmed;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("AT+FRAMECNT=");
        serializeInt32(frameCnt);

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

        // Deserialize field "frame cnt confirmed"
        frameCntConfirmed = deserializeInt32();

        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(297);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("TelegesisSetFrameCntCommand [frameCnt=");
        builder.append(String.format("%08X", frameCnt));
        builder.append(", frameCntConfirmed=");
        builder.append(String.format("%08X", frameCntConfirmed));
        if (status != null) {
            builder.append(", status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
