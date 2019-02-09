/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.messaging;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Cancel All Messages value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * The CancelAllMessages command indicates to a client device that it should cancel all
 * display messages currently held by it.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class CancelAllMessages extends ZclCommand {
    /**
     * Implementation Date Time command message field.
     * <p>
     * A UTC Time field to indicate the date/time at which all existing display messages should
     * be cleared.
     */
    private Calendar implementationDateTime;

    /**
     * Default constructor.
     */
    public CancelAllMessages() {
        genericCommand = false;
        clusterId = 0x0703;
        commandId = 3;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Implementation Date Time.
     * <p>
     * A UTC Time field to indicate the date/time at which all existing display messages should
     * be cleared.
     *
     * @return the Implementation Date Time
     */
    public Calendar getImplementationDateTime() {
        return implementationDateTime;
    }

    /**
     * Sets Implementation Date Time.
     * <p>
     * A UTC Time field to indicate the date/time at which all existing display messages should
     * be cleared.
     *
     * @param implementationDateTime the Implementation Date Time
     */
    public void setImplementationDateTime(final Calendar implementationDateTime) {
        this.implementationDateTime = implementationDateTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(implementationDateTime, ZclDataType.UTCTIME);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        implementationDateTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(62);
        builder.append("CancelAllMessages [");
        builder.append(super.toString());
        builder.append(", implementationDateTime=");
        builder.append(implementationDateTime);
        builder.append(']');
        return builder.toString();
    }

}
