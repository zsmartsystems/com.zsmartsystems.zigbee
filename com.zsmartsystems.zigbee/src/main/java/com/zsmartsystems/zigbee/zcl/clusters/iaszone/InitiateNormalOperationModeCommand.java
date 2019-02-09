/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.iaszone;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Initiate Normal Operation Mode Command value object class.
 * <p>
 * Cluster: <b>IAS Zone</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the IAS Zone cluster.
 * <p>
 * Used to tell the IAS Zone server to commence normal operation mode. <br> Upon receipt, the IAS
 * Zone server shall commence normal operational mode. <br> Any configurations and changes
 * made (e.g., CurrentZoneSensitivityLevel attribute) to the IAS Zone server shall be
 * retained. <br> Upon commencing normal operation mode, the IAS Zone server shall send a Zone
 * Status Change Notification command updating the ZoneStatus attribute Test bit to zero
 * (i.e., “operation mode”).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class InitiateNormalOperationModeCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public InitiateNormalOperationModeCommand() {
        genericCommand = false;
        clusterId = 0x0500;
        commandId = 1;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(37);
        builder.append("InitiateNormalOperationModeCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
