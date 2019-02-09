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
 * Unlock Door Command value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * This command causes the lock device to unlock the door. As of HA 1.2, this command includes an
 * optional code for the lock. The door lock may require a code depending on the value of the
 * [Require PIN for RF Operation attribute].
 * <p>
 * * <p>
 * <b>Note:</b> If the attribute AutoRelockTime is supported the lock will close when the auto relock time
 * has expired
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class UnlockDoorCommand extends ZclCommand {
    /**
     * PIN Code command message field.
     */
    private ByteArray pinCode;

    /**
     * Default constructor.
     */
    public UnlockDoorCommand() {
        genericCommand = false;
        clusterId = 0x0101;
        commandId = 1;
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
        final StringBuilder builder = new StringBuilder(47);
        builder.append("UnlockDoorCommand [");
        builder.append(super.toString());
        builder.append(", pinCode=");
        builder.append(pinCode);
        builder.append(']');
        return builder.toString();
    }

}
