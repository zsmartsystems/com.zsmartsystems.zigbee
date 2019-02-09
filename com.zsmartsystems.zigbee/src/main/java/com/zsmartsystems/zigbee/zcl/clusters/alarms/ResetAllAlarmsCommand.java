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
 * Reset All Alarms Command value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * This command resets all alarms. Any alarm conditions that were in fact still active will
 * cause a new notification to be generated and, where implemented, a new record added to the
 * alarm log.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ResetAllAlarmsCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public ResetAllAlarmsCommand() {
        genericCommand = false;
        clusterId = 0x0009;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(24);
        builder.append("ResetAllAlarmsCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
