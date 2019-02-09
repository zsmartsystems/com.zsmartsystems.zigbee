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
 * Get Bypassed Zone List Command value object class.
 * <p>
 * Cluster: <b>IAS ACE</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS ACE cluster.
 * <p>
 * Provides IAS ACE clients with a way to retrieve the list of zones to be bypassed. This provides
 * them with the ability to provide greater local functionality (i.e., at the IAS ACE client)
 * for users to modify the Bypassed Zone List and reduce communications to the IAS ACE server
 * when trying to arm the CIE security system.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetBypassedZoneListCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public GetBypassedZoneListCommand() {
        genericCommand = false;
        clusterId = 0x0501;
        commandId = 8;
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
