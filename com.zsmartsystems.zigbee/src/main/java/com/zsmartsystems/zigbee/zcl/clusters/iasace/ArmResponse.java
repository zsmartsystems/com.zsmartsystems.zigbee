/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Arm Response value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * The IAS ACE cluster defines an interface to the functionality of any Ancillary
 * Control Equipment of the IAS system. Using this cluster, a ZigBee enabled ACE
 * device can access a IAS CIE device and manipulate the IAS system, on behalf of a
 * level-2 user.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class ArmResponse extends ZclCommand {
    /**
     * Arm Notification command message field.
     */
    private Integer armNotification;

    /**
     * Default constructor.
     */
    public ArmResponse() {
        genericCommand = false;
        clusterId = 1281;
        commandId = 0;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Arm Notification.
     *
     * @return the Arm Notification
     */
    public Integer getArmNotification() {
        return armNotification;
    }

    /**
     * Sets Arm Notification.
     *
     * @param armNotification the Arm Notification
     */
    public void setArmNotification(final Integer armNotification) {
        this.armNotification = armNotification;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(armNotification, ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        armNotification = (Integer) deserializer.deserialize(ZclDataType.ENUMERATION_8_BIT);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(49);
        builder.append("ArmResponse [");
        builder.append(super.toString());
        builder.append(", armNotification=");
        builder.append(armNotification);
        builder.append(']');
        return builder.toString();
    }

}
