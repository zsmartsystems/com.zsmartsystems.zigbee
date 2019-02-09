/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.electricalmeasurement;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Profile Info Command value object class.
 * <p>
 * Cluster: <b>Electrical Measurement</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Electrical Measurement cluster.
 * <p>
 * Retrieves the power profiling information from the electrical measurement server.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetProfileInfoCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public GetProfileInfoCommand() {
        genericCommand = false;
        clusterId = 0x0B04;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(24);
        builder.append("GetProfileInfoCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
