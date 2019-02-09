/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.thermostat;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Relay Status Log value object class.
 * <p>
 * Cluster: <b>Thermostat</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Thermostat cluster.
 * <p>
 * The Get Relay Status Log command is used to query the thermostat internal relay status log.
 * This command has no payload. <br> The log storing order is First in First Out (FIFO) when the
 * log is generated and stored into the Queue. <br> The first record in the log (i.e., the oldest)
 * one, is the first to be replaced when there is a new record and there is no more space in the log.
 * Thus, the newest record will overwrite the oldest one if there is no space left. <br> The log
 * storing order is Last In First Out (LIFO) when the log is being retrieved from the Queue by a
 * client device. Once the "Get Relay Status Log Response" frame is sent by the Server, the
 * "Unread Entries" attribute should be decremented to indicate the number of unread records
 * that remain in the queue. <br> If the "Unread Entries"attribute reaches zero and the Client
 * sends a new "Get Relay Status Log Request", the Server may send one of the following items as a
 * response: <br> i) resend the last Get Relay Status Log Response or ii) generate new log record
 * at the time of request and send Get Relay Status Log Response with the new data
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetRelayStatusLog extends ZclCommand {
    /**
     * Default constructor.
     */
    public GetRelayStatusLog() {
        genericCommand = false;
        clusterId = 0x0201;
        commandId = 4;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(20);
        builder.append("GetRelayStatusLog [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
