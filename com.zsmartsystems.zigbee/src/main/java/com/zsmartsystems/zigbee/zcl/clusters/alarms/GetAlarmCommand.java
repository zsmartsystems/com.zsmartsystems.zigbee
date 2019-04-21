/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.alarms;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Alarm Command value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * This command causes the alarm with the earliest generated alarm entry in the alarm table to be
 * reported in a get alarm response command. This command enables the reading of logged alarm
 * conditions from the alarm table. Once an alarm condition has been reported the
 * corresponding entry in the table is removed.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class GetAlarmCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0009;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Default constructor.
     */
    public GetAlarmCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(18);
        builder.append("GetAlarmCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
