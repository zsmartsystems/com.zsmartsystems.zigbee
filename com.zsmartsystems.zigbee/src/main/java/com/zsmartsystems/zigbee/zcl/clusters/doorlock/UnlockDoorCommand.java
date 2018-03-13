/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Unlock Door Command value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class UnlockDoorCommand extends ZclCommand {
    /**
     * Pin code command message field.
     */
    private String pinCode;

    /**
     * Default constructor.
     */
    public UnlockDoorCommand() {
        genericCommand = false;
        clusterId = 257;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Pin code.
     *
     * @return the Pin code
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * Sets Pin code.
     *
     * @param pinCode the Pin code
     */
    public void setPinCode(final String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(pinCode, ZclDataType.OCTET_STRING);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        pinCode = (String) deserializer.deserialize(ZclDataType.OCTET_STRING);
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
