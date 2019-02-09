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
 * Cluster: <b>Alarms</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * This command causes the alarm with the earliest generated alarm entry in the alarm table to be
 * reported in a get alarm response command. This command enables the reading of logged alarm
 * conditions from the alarm table. Once an alarm condition has been reported the
 * corresponding entry in the table is removed.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetAlarmCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public GetAlarmCommand() {
        genericCommand = false;
        clusterId = 0x0009;
        commandId = 2;
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
