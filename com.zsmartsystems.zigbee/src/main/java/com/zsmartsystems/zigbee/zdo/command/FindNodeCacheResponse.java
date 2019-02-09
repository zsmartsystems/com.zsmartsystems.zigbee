/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Find Node Cache Response value object class.
 * <p>
 * <p>
 * The Find_node_cache_rsp is provided to notify a Local Device of the successful discovery of
 * the Primary Discovery Cache device for the given NWKAddr and IEEEAddr fields supplied in the
 * request, or to signify that the device of interest is capable of responding to discovery
 * requests. The Find_node_cache_rsp shall be generated only by Primary Discovery Cache
 * devices holding discovery information for the NWKAddr and IEEEAddr in the request or the
 * device of interest itself and all other Remote Devices shall not supply a response.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class FindNodeCacheResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public FindNodeCacheResponse() {
        clusterId = 0x801C;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(24);
        builder.append("FindNodeCacheResponse [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
