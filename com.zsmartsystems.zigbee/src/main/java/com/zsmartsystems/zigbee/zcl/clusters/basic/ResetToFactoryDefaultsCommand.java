/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.basic;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Reset To Factory Defaults Command value object class.
 * <p>
 * Cluster: <b>Basic</b>. Command ID 0x00 is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Basic cluster.
 * <p>
 * On receipt of this command, the device resets all the attributes of all its clusters to their
 * factory defaults. Note that ZigBee networking functionality,bindings, groups or other
 * persistent data are not affected by this command
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-01-10T12:07:00Z")
public class ResetToFactoryDefaultsCommand extends ZclBasicCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0000;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x00;

    /**
     * Default constructor.
     */
    public ResetToFactoryDefaultsCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(32);
        builder.append("ResetToFactoryDefaultsCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
