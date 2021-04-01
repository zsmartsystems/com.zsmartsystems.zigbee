/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.alarms;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Reset All Alarms Command value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * This command resets all alarms. Any alarm conditions that were in fact still active will
 * cause a new notification to be generated and, where implemented, a new record added to the
 * alarm log.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class ResetAllAlarmsCommand extends ZclAlarmsCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0009;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Default constructor.
     *
     */
    public ResetAllAlarmsCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
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
