/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Remove Node Cache Request value object class.
 * <p>
 * <p>
 * The Remove_node_cache_req is provided to enable ZigBee devices on the network to request
 * removal of discovery cache information for a specified ZigBee end device from a Primary
 * Discovery Cache device. The effect of a successful Remove_node_cache_req is to undo a
 * previously successful Discovery_store_req and additionally remove any cache
 * information stored on behalf of the specified ZigBee end device on the Primary Discovery
 * Cache device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RemoveNodeCacheRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public RemoveNodeCacheRequest() {
        clusterId = 0x001B;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(25);
        builder.append("RemoveNodeCacheRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
