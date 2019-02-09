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
 * Management Cache Response value object class.
 * <p>
 * <p>
 * The Mgmt_Cache_rsp is generated in response to an Mgmt_Cache_req. If this management
 * command is not supported, or the Remote Device is not a Primary Cache Device, a status of
 * NOT_SUPPORTED shall be returned and all parameter fields after the Status field shall be
 * omitted. Otherwise, the Remote Device shall implement the following processing. Upon
 * receipt of the Mgmt_Cache_req and after support for the Mgmt_Cache_req has been verified,
 * the Remote Device shall access an internally maintained list of registered ZigBee End
 * Devices utilizing the discovery cache on this Primary Discovery Cache device. The entries
 * reported shall be those, starting with StartIndex and including whole DiscoveryCacheList
 * records until the limit on MSDU size, i.e., aMaxMACFrameSize, is reached. Within the
 * Mgmt_Cache_rsp command, the DiscoveryCacheListEntries field shall represent the total
 * number of registered entries in the Remote Device. The parameter DiscoveryCacheListCount
 * shall be the number of entries reported in the DiscoveryCacheList field of the
 * Mgmt_Cache_rsp command.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class ManagementCacheResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ManagementCacheResponse() {
        clusterId = 0x8037;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(26);
        builder.append("ManagementCacheResponse [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
