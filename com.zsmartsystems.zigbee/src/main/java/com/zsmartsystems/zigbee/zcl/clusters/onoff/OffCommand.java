/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.onoff;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Off Command value object class.
 * <p>
 * Cluster: <b>On/Off</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the On/Off cluster.
 * <p>
 * On receipt of this command, a device shall enter its ‘Off’ state. This state is device
 * dependent, but it is recommended that it is used for power off or similar functions. On
 * receipt of the Off command, the OnTime attribute shall be set to 0x0000.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class OffCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public OffCommand() {
        genericCommand = false;
        clusterId = 0x0006;
        commandId = 0;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(13);
        builder.append("OffCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
