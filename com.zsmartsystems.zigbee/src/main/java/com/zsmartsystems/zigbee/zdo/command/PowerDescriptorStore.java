package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Power Descriptor Store value object class.
 * <p>
 * The Power_Desc_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their Power Descriptor on a Primary Discovery
 * Cache device which has previously received a SUCCESS status from a
 * Discovery_store_req to the same Primary Discovery Cache device. Included in
 * this request is the Power Descriptor the Local Device wishes to cache.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class PowerDescriptorStore extends ZdoResponse {
    /**
     * Default constructor.
     */
    public PowerDescriptorStore() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PowerDescriptorStore");
        builder.append(super.toString());
        return builder.toString();
    }

}
