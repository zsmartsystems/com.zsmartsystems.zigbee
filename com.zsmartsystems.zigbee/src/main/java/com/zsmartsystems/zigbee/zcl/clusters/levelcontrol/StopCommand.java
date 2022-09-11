/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.levelcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Stop Command value object class.
 * <p>
 * Cluster: <b>Level Control</b>. Command ID 0x03 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Level Control cluster.
 * <p>
 * Upon receipt of this command, any Move to Level, Move or Step command (and their 'with On/Off'
 * variants) currently in process shall be terminated. The value of CurrentLevel shall be left
 * at its value upon receipt of the Stop command, and RemainingTime shall be set to zero.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class StopCommand extends ZclLevelControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0008;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x03;

    /**
     * Default constructor.
     *
     */
    public StopCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(14);
        builder.append("StopCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
