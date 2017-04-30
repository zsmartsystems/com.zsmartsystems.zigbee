package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Discovery Store Request Request value object class.
 * <p>
 * The Discovery_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their discovery cache information on a Primary
 * Discovery Cache device. Included in the request is the amount of storage space
 * the Local Device requires.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class DiscoveryStoreRequestRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public DiscoveryStoreRequestRequest() {
        clusterId = 0x0016;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("DiscoveryStoreRequestRequest [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
