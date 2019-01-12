/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Toggle value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Request the status of the lock. As of HA 1.2, this command includes an optional code for the
 * lock. The door lock MAY require a code depending on the value of the [Require PIN for RF
 * Operation attribute]
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-08-13T17:13:06Z")
public class Toggle extends ZclCommand {
    /**
     * PIN command message field.
     */
    private String pin;

    /**
     * Default constructor.
     */
    public Toggle() {
        genericCommand = false;
        clusterId = 0x0101;
        commandId = 2;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets PIN.
     *
     * @return the PIN
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets PIN.
     *
     * @param pin the PIN
     */
    public void setPin(final String pin) {
        this.pin = pin;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(pin, ZclDataType.CHARACTER_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        pin = (String) deserializer.deserialize(ZclDataType.CHARACTER_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(32);
        builder.append("Toggle [");
        builder.append(super.toString());
        builder.append(", pin=");
        builder.append(pin);
        builder.append(']');
        return builder.toString();
    }

}
