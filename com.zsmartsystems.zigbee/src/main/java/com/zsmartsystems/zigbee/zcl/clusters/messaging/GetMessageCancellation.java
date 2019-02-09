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
 * Get Message Cancellation value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * This command initiates the return of the first (and maybe only) Cancel All Messages command
 * held on the associated server, and which has an implementation time equal to or later than the
 * value indicated in the payload.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetMessageCancellation extends ZclCommand {
    /**
     * Earliest Implementation Time command message field.
     * <p>
     * UTC Timestamp indicating the earliest implementation time of a Cancel All Messages
     * command to be returned.
     */
    private Calendar earliestImplementationTime;

    /**
     * Default constructor.
     */
    public GetMessageCancellation() {
        genericCommand = false;
        clusterId = 0x0703;
        commandId = 2;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Gets Earliest Implementation Time.
     * <p>
     * UTC Timestamp indicating the earliest implementation time of a Cancel All Messages
     * command to be returned.
     *
     * @return the Earliest Implementation Time
     */
    public Calendar getEarliestImplementationTime() {
        return earliestImplementationTime;
    }

    /**
     * Sets Earliest Implementation Time.
     * <p>
     * UTC Timestamp indicating the earliest implementation time of a Cancel All Messages
     * command to be returned.
     *
     * @param earliestImplementationTime the Earliest Implementation Time
     */
    public void setEarliestImplementationTime(final Calendar earliestImplementationTime) {
        this.earliestImplementationTime = earliestImplementationTime;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        serializer.serialize(earliestImplementationTime, ZclDataType.UTCTIME);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        earliestImplementationTime = (Calendar) deserializer.deserialize(ZclDataType.UTCTIME);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(71);
        builder.append("GetMessageCancellation [");
        builder.append(super.toString());
        builder.append(", earliestImplementationTime=");
        builder.append(earliestImplementationTime);
        builder.append(']');
        return builder.toString();
    }

}
