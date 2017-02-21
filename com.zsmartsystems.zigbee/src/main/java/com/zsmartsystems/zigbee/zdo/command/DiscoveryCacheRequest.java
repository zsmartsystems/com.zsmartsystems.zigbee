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
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class DiscoveryCacheRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public DiscoveryCacheRequest() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DiscoveryCacheRequest");
        builder.append(super.toString());
        return builder.toString();
    }

}
