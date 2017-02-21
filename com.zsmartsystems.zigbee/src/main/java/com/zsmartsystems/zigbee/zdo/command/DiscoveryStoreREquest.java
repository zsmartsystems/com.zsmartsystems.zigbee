package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Discovery Store REquest value object class.
 * <p>
 * The Discovery_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their discovery cache information on a Primary
 * Discovery Cache device. Included in the request is the amount of storage space
 * the Local Device requires.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class DiscoveryStoreREquest extends ZdoResponse {
    /**
     * Default constructor.
     */
    public DiscoveryStoreREquest() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DiscoveryStoreREquest");
        builder.append(super.toString());
        return builder.toString();
    }

}
