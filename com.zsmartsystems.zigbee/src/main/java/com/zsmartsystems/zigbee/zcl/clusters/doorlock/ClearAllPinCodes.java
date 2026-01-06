/**
 * Copyright (c) 2016-2026 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.doorlock;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Clear All PIN Codes value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x08 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Set the status of a user ID. User Status value of 0x00 is not allowed. In order to clear a user id,
 * the Clear ID Command shall be used. For user status value please refer to User Status Value.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:33:00Z")
public class ClearAllPinCodes extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x08;

    /**
     * Default constructor.
     *
     */
    public ClearAllPinCodes() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(19);
        builder.append("ClearAllPinCodes [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
