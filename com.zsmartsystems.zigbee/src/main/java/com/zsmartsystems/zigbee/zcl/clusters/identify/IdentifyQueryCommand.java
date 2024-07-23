/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.identify;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Identify Query Command value object class.
 * <p>
 * Cluster: <b>Identify</b>. Command ID 0x01 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Identify cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class IdentifyQueryCommand extends ZclIdentifyCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0003;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x01;

    /**
     * Default constructor.
     *
     */
    public IdentifyQueryCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(23);
        builder.append("IdentifyQueryCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
