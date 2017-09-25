/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.rssilocation;

import com.zsmartsystems.zigbee.zcl.ZclCommand;

/**
 * RSSI Request Command value object class.
 * <p>
 * Cluster: <b>RSSI Location</b>. Command is sent <b>FROM</b> the server.
 * This command is a <b>specific</b> command used for the RSSI Location cluster.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class RssiRequestCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public RssiRequestCommand() {
        genericCommand = false;
        clusterId = 11;
        commandId = 5;
        commandDirection = false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(21);
        builder.append("RssiRequestCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
