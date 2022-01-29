/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * System Server Discovery Request value object class.
 * <p>
 * <p>
 * The System_Server_Discovery_req is generated from a Local Device wishing to discover the
 * location of a particular system server or servers as indicated by the ServerMask parameter.
 * The destination addressing on this request is "broadcast to all devices for which
 * macRxOnWhenIdle = TRUE".
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class SystemServerDiscoveryRequest extends ZdoRequest {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0015;

    /**
     * Default constructor.
     *
     */
    public SystemServerDiscoveryRequest() {
        clusterId = CLUSTER_ID;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(31);
        builder.append("SystemServerDiscoveryRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
