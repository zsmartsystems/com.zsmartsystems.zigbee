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
 * Cluster: <b>Door Lock</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Request the status of the lock. As of HA 1.2, this command includes an optional code for the
 * lock. The door lock may require a code depending on the value of the [Require PIN for RF
 * Operation attribute]
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class Toggle extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * PIN command message field.
     */
    private String pin;

    /**
     * Default constructor.
     */
    public Toggle() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
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
