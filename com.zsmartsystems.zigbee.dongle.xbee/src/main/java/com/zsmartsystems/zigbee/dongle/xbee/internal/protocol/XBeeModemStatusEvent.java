/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.xbee.internal.protocol;

/**
 * Class to implement the XBee command <b>Modem Status</b>.
 * <p>
 * Devices send the status messages in this frame in response to specific conditions.
 * <p>
 * This class provides methods for processing XBee API commands.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson - Initial contribution of Java code generator
 */
public class XBeeModemStatusEvent extends XBeeFrame implements XBeeEvent {
    /**
     * Response field
     */
    private ModemStatus status;

    /**
     *
     * @return the status as {@link ModemStatus}
     */
    public ModemStatus getStatus() {
        return status;
    }


    @Override
    public void deserialize(int[] incomingData) {
        initialiseDeserializer(incomingData);

        // Deserialize the fields for the response

        // Deserialize field "status"
        status = deserializeModemStatus();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(200);
        builder.append("XBeeModemStatusEvent [status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }
}
