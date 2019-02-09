/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.groups;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Remove All Groups Command value object class.
 * <p>
 * Cluster: <b>Groups</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Groups cluster.
 * <p>
 * The remove all groups command allows the sending device to direct the receiving entity or
 * entities to remove all group associations.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RemoveAllGroupsCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public RemoveAllGroupsCommand() {
        genericCommand = false;
        clusterId = 0x0004;
        commandId = 4;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(25);
        builder.append("RemoveAllGroupsCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
