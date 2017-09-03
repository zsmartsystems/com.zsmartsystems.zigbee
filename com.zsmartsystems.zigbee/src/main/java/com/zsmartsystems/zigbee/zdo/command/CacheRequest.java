/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Cache Request value object class.
 * <p>
 * The Mgmt_Cache_req is provided to enable ZigBee devices on the network to
 * retrieve a list of ZigBee End Devices registered with a Primary Discovery Cache
 * device. The destination addressing on this primitive shall be unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class CacheRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public CacheRequest() {
        clusterId = 0x0037;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CacheRequest [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
