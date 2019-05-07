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
 * Cancel All Messages Command value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command ID 0x03 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * The Cancel All Messages command indicates to a CLIENT | device that it should cancel all
 * display messages currently held by it.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class CancelAllMessagesCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0703;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

    /**
     * Implementation Time command message field.
     */
    private Calendar implementationTime;

    /**
     * Default constructor.
     */
    public CancelAllMessagesCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    /**
     * Gets Implementation Time.
     *
     * @return the Implementation Time
     */
    public Calendar getImplementationTime() {
        return implementationTime;
    }

    /**
     * Sets Implementation Time.
     *
     * @param implementationTime the Implementation Time
     */
    public void setImplementationTime(final Calendar implementationTime) {
        this.implementationTime = implementationTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(implementationTime, ZclDataType.UTCTIME);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        implementationTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(65);
        builder.append("CancelAllMessagesCommand [");
        builder.append(super.toString());
        builder.append(", implementationTime=");
        builder.append(implementationTime);
        builder.append(']');
        return builder.toString();
    }

}
