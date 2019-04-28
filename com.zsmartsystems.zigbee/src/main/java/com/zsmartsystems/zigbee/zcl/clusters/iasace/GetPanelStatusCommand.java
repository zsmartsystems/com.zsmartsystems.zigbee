/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Panel Status Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command ID 0x07 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * This command is used by ACE clients to request an update to the status (e.g., security system
 * arm state) of the ACE server (i.e., the IAS CIE). In particular, this command is useful for
 * battery-powered ACE clients with polling rates longer than the ZigBee standard check-in
 * rate. <br> On receipt of this command, the ACE server responds with the status of the security
 * system. The IAS ACE server shall generate a Get Panel Status Response command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class GetPanelStatusCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0501;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x07;

    /**
     * Default constructor.
     */
    public GetPanelStatusCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(24);
        builder.append("GetPanelStatusCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
