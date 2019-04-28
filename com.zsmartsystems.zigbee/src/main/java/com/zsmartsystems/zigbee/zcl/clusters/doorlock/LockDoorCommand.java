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
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Lock Door Command value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * This command causes the lock device to lock the door. As of HA 1.2, this command includes an
 * optional code for the lock. The door lock may require a PIN depending on the value of the
 * [Require PIN for RF Operation attribute]
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class LockDoorCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * PIN Code command message field.
     */
    private ByteArray pinCode;

    /**
     * Default constructor.
     */
    public LockDoorCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets PIN Code.
     *
     * @return the PIN Code
     */
    public ByteArray getPinCode() {
        return pinCode;
    }

    /**
     * Sets PIN Code.
     *
     * @param pinCode the PIN Code
     */
    public void setPinCode(final ByteArray pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(pinCode, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        pinCode = (ByteArray) deserializer.deserialize(ZclDataType.OCTET_STRING);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(45);
        builder.append("LockDoorCommand [");
        builder.append(super.toString());
        builder.append(", pinCode=");
        builder.append(pinCode);
        builder.append(']');
        return builder.toString();
    }

}
