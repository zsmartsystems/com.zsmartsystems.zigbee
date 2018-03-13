/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.telegesis.internal.protocol;


/**
 * Class to implement the Telegesis command <b>Set Register</b>.
 * <p>
 * Sets an S register
 * <p>
 * This class provides methods for processing Telegesis AT API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class TelegesisSetRegisterCommand extends TelegesisFrame implements TelegesisCommand {
    /**
     * Command field
     */
    private Integer register;

    /**
     * Command field
     */
    private Integer value;

    /**
     * Command field
     */
    private String password;

    /**
     *
     * @param register the register to set as {@link Integer}
     */
    public void setRegister(Integer register) {
        this.register = register;
    }

    /**
     *
     * @param value the value to set as {@link Integer}
     */
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     *
     * @param password the password to set as {@link String}
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int[] serialize() {
        // Serialize the command fields
        serializeCommand("ATS");
        serializeInt8(register);
        serializeDelimiter('=');
        serializeInt16(value);
        if (password != null) {
            serializeDelimiter(':');
            serializeString(password);
        }

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
        final StringBuilder builder = new StringBuilder(387);
        // First present the command parameters...
        // Then the responses later if they are available
        builder.append("TelegesisSetRegisterCommand [register=");
        builder.append(register);
        builder.append(", value=");
        builder.append(value);
        builder.append(", password=");
        builder.append(password);
        if (status != null) {
            builder.append(", status=");
            builder.append(status);
        }
        builder.append(']');
        return builder.toString();
    }
}
