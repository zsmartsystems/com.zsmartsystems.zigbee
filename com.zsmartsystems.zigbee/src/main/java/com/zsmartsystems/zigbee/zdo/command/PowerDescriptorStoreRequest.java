package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Power Descriptor Store Request value object class.
 * <p>
 * The Power_Desc_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their Power Descriptor on a Primary Discovery
 * Cache device which has previously received a SUCCESS status from a
 * Discovery_store_req to the same Primary Discovery Cache device. Included in
 * this request is the Power Descriptor the Local Device wishes to cache.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class PowerDescriptorStoreRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public PowerDescriptorStoreRequest() {
        clusterId = 0x0018;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PowerDescriptorStoreRequest");
        builder.append(super.toString());
        return builder.toString();
    }

}
