/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.colorcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Stop Move Step Command value object class.
 * <p>
 * Cluster: <b>Color Control</b>. Command ID 0x47 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Color Control cluster.
 * <p>
 * The Stop Move Step command is provided to allow Move to and Step commands to be stopped. (Note
 * this automatically provides symmetry to the Level Control cluster.)
 * <p>
 * Upon receipt of this command, any Move to, Move or Step command currently in process shall be
 * ter- minated. The values of the CurrentHue, EnhancedCurrentHue and CurrentSaturation
 * attributes shall be left at their present value upon receipt of the Stop Move Step command,
 * and the RemainingTime attribute shall be set to zero.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class StopMoveStepCommand extends ZclColorControlCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0300;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x47;

    /**
     * Default constructor.
     *
     */
    public StopMoveStepCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(22);
        builder.append("StopMoveStepCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
