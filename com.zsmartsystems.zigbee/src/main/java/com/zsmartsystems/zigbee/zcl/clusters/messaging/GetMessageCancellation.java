/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.messaging;

import java.util.Calendar;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 * Get Message Cancellation value object class.
 * <p>
 * Cluster: <b>Messaging</b>. Command ID 0x02 is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the Messaging cluster.
 * <p>
 * This command initiates the return of the first (and maybe only) Cancel All Messages command
 * held on the associated server, and which has an implementation time equal to or later than the
 * value indicated in the payload.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-12-25T10:11:19Z")
public class GetMessageCancellation extends ZclMessagingCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0703;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Earliest Implementation Time command message field.
     * <p>
     * UTC Timestamp indicating the earliest implementation time of a Cancel All Messages
     * command to be returned.
     */
    private Calendar earliestImplementationTime;

    /**
     * Default constructor.
     *
     * @deprecated from release 1.3.0. Use the parameterised constructor instead of the default constructor and setters.
     */
    @Deprecated
    public GetMessageCancellation() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;
    }

    /**
     * Constructor providing all required parameters.
     *
     * @param earliestImplementationTime {@link Calendar} Earliest Implementation Time
     */
    public GetMessageCancellation(
            Calendar earliestImplementationTime) {

        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.SERVER_TO_CLIENT;

        this.earliestImplementationTime = earliestImplementationTime;
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
     * @deprecated as of 1.3.0. Use the parameterised constructor instead to ensure that all mandatory fields are provided.
     */
    @Deprecated
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
