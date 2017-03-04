package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Discovery Cache Request value object class.
 * <p>
 * The Discovery_Cache_req is provided to enable devices on the network to locate
 * a Primary Discovery Cache device on the network. The destination addressing on
 * this primitive shall be broadcast to all devices for which macRxOnWhenIdle =
 * TRUE.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class DiscoveryCacheRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public DiscoveryCacheRequest() {
        clusterId = 0x0012;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DiscoveryCacheRequest ");
        builder.append(super.toString());
        return builder.toString();
    }

}
