/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.alarms;

import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * Reset Alarm Log Command value object class.
 * <p>
 * Cluster: <b>Alarms</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Alarms cluster.
 * <p>
 * Attributes and commands for sending alarm notifications and configuring alarm
 * functionality.
 * <p>
 * Alarm conditions and their respective alarm codes are described in individual
 * clusters, along with an alarm mask field. Where not masked, alarm notifications
 * are reported to subscribed targets using binding.
 * <p>
 * Where an alarm table is implemented, all alarms, masked or otherwise, are
 * recorded and may be retrieved on demand.
 * <p>
 * Alarms may either reset automatically when the conditions that cause are no
 * longer active, or may need to be explicitly reset.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ResetAlarmLogCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public ResetAlarmLogCommand() {
        genericCommand = false;
        clusterId = 9;
        commandId = 3;
        commandDirection = true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(23);
        builder.append("ResetAlarmLogCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
