/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zcl.clusters.price;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclCommand;
import com.zsmartsystems.zigbee.zcl.protocol.ZclCommandDirection;

/**
 * Get Tariff Cancellation Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates the return of the last CancelTariff command held on the associated
 * server. <br> A ZCL Default response with status NOT_FOUND shall be returned if there is no
 * CancelTariff command available.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class GetTariffCancellationCommand extends ZclCommand {
    /**
     * Default constructor.
     */
    public GetTariffCancellationCommand() {
        genericCommand = false;
        clusterId = 0x0700;
        commandId = 16;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(31);
        builder.append("GetTariffCancellationCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
