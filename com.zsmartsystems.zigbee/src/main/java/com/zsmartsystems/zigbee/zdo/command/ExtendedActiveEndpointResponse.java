/**
 * Copyright (c) 2016-2021 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Extended Active Endpoint Response value object class.
 * <p>
 * <p>
 * The Extended_Active_EP_rsp is generated by a remote device in response to an
 * Extended_Active_EP_req directed to the remote device. This command shall be unicast to the
 * originator of the Extended_Active_EP_req command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class ExtendedActiveEndpointResponse extends ZdoResponse {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x801E;

    /**
     * Default constructor.
     *
     */
    public ExtendedActiveEndpointResponse() {
        clusterId = CLUSTER_ID;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(33);
        builder.append("ExtendedActiveEndpointResponse [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
