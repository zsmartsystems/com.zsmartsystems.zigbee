/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.pollcontrol;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Fast Poll Stop Command value object class.
 * <p>
 * Cluster: <b>Poll Control</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Poll Control cluster.
 * <p>
 * The Fast Poll Stop command is used to stop the fast poll mode initiated by the Check-in
 * response. The Fast Poll Stop command has no payload. <br> If the Poll Control Server receives
 * a Fast Poll Stop from an unbound client it should send back a DefaultResponse with a value
 * field indicating “ACTION_DENIED” . The Server shall respond with a DefaultResponse not
 * equal to ZCL_SUCCESS. <br> If the Poll Control Server receives a Fast Poll Stop command from a
 * bound client but it is unable to stop fast polling due to the fact that there is another bound
 * client which has requested that polling continue it should respond with a Default Response
 * with a status of “ACTION_DENIED” <br> If a Poll Control Server receives a Fast Poll Stop
 * command from a bound client but it is not FastPolling it should respond with a Default
 * Response with a status of ACTION_DENIED.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class FastPollStopCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public FastPollStopCommand() {
        genericCommand = false;
        clusterId = 0x0020;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(22);
        builder.append("FastPollStopCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
