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
 * Discovery Cache Response value object class.
 * <p>
 * <p>
 * The Discovery_Cache_rsp is generated by Primary Discovery Cache devices receiving the
 * Discovery_Cache_req. Remote Devices which are not Primary Discovery Cache devices (as
 * designated in its Node Descriptor) should not respond to the Discovery_Cache_req command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2020-06-06T12:25:30Z")
public class DiscoveryCacheResponse extends ZdoResponse {
    /**
     * The ZDO cluster ID.
     */
    public static int CLUSTER_ID = 0x8012;

    /**
     * Default constructor.
     *
     */
    public DiscoveryCacheResponse() {
        clusterId = CLUSTER_ID;
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(25);
        builder.append("DiscoveryCacheResponse [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
