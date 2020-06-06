/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.windowcovering;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Window Covering Up Open value object class.
 * <p>
 * Cluster: <b>Window Covering</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Window Covering cluster.
 * <p>
 * Moves window covering to InstalledOpenLimit
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class WindowCoveringUpOpen extends ZclWindowCoveringCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0102;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Default constructor.
     *
     */
    public WindowCoveringUpOpen() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(23);
        builder.append("WindowCoveringUpOpen [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
