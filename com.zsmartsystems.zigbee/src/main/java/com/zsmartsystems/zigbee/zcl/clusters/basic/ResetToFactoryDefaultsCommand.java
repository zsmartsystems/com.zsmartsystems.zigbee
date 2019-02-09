/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.basic;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Reset To Factory Defaults Command value object class.
 * <p>
 * Cluster: <b>Basic</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Basic cluster.
 * <p>
 * On receipt of this command, the device resets all the attributes of all its clusters to their
 * factory defaults. Note that ZigBee networking functionality,bindings, groups or other
 * persistent data are not affected by this command
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ResetToFactoryDefaultsCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public ResetToFactoryDefaultsCommand() {
        genericCommand = false;
        clusterId = 0x0000;
        commandId = 0;
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
