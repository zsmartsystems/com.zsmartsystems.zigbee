/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Toggle Command value object class.
 * <p>
 * Cluster: <b>On/Off</b>. Command ID 0x02 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * <p>
 * On receipt of this command, if a device is in its ‘Off’ state it shall enter its ‘On’ state.
 * Otherwise, if it is in its ‘On’ state it shall enter its ‘Off’ state. On receipt of the Toggle
 * command, if the value of the OnOff attribute is equal to 0x00 and if the value of the OnTime
 * attribute is equal to 0x0000, the device shall set the OffWaitTime attribute to 0x0000. If
 * the value of the OnOff attribute is equal to 0x01, the OnTime attribute shall be set to 0x0000.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class ToggleCommand extends ZclOnOffCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0006;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x02;

    /**
     * Default constructor.
     *
     */
    public ToggleCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(16);
        builder.append("ToggleCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
