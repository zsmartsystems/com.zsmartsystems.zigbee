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
 * Discovery Cache Request value object class.
 * <p>
 * <p>
 * The Discovery_Cache_req is provided to enable devices on the network to locate a Primary
 * Discovery Cache device on the network. The destination addressing on this primitive shall
 * be broadcast to all devices for which macRxOnWhenIdle = TRUE.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class DiscoveryCacheRequest extends ZdoRequest {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x0012;

    /**
     * Default constructor.
     *
     */
    public DiscoveryCacheRequest() {
        clusterId = CLUSTER_ID;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(24);
        builder.append("DiscoveryCacheRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
