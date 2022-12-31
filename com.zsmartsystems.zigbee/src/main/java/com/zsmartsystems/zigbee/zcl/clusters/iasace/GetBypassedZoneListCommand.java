/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iasace;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Bypassed Zone List Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command ID 0x08 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * Provides IAS ACE clients with a way to retrieve the list of zones to be bypassed. This provides
 * them with the ability to provide greater local functionality (i.e., at the IAS ACE client)
 * for users to modify the Bypassed Zone List and reduce communications to the IAS ACE server
 * when trying to arm the CIE security system.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class GetBypassedZoneListCommand extends ZclIasAceCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0501;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x08;

    /**
     * Default constructor.
     *
     */
    public GetBypassedZoneListCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(29);
        builder.append("GetBypassedZoneListCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
