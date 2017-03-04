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
        builder.append("CacheRequest ");
        builder.append(super.toString());
        return builder.toString();
    }

}
