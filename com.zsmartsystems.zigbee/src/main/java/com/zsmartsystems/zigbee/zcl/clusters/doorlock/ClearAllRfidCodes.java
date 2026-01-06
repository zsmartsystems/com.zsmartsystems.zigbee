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
 * Clear All RFID Codes value object class.
 * <p>
 * Cluster: <b>Door Lock</b>. Command ID 0x19 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Door Lock cluster.
 * <p>
 * Clear out all RFIDs on the lock. If you delete all RFID codes and this user didn't have a PIN
 * code, the user status has to be set to "0 Available", the user type has to be set to the default
 * value, and all schedules which are supported have to be set to the default values.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2024-05-18T05:31:31Z")
public class ClearAllRfidCodes extends ZclDoorLockCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0101;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x19;

    /**
     * Default constructor.
     *
     */
    public ClearAllRfidCodes() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(20);
        builder.append("ClearAllRfidCodes [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
