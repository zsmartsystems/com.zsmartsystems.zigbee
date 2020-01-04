/**
 * Copyright (c) 2016-2020 by the respective copyright holders.
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
 * Get Currency Conversion Command value object class.
 * <p>
 * Cluster: <b>Price</b>. Command ID 0x0F is sent <b>TO</b> the server.
 * This command is a <b>specific</b> command used for the Price cluster.
 * <p>
 * This command initiates a PublishCurrencyConversion command for the currency conversion
 * factor updates. A server shall be capable of storing both the old and the new currencies. <br>
 * A ZCL Default response with status NOT_FOUND shall be returned if there are no currency
 * conversion factor updates available
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-04-14T08:41:54Z")
public class GetCurrencyConversionCommand extends ZclCommand {
    /**
     * The cluster ID to which this command belongs.
     */
    public static int CLUSTER_ID = 0x0700;

    /**
     * The command ID.
     */
    public static int COMMAND_ID = 0x0F;

    /**
     * Default constructor.
     */
    public GetCurrencyConversionCommand() {
        clusterId = CLUSTER_ID;
        commandId = COMMAND_ID;
        genericCommand = false;
        commandDirection = ZclCommandDirection.CLIENT_TO_SERVER;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(31);
        builder.append("GetCurrencyConversionCommand [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
