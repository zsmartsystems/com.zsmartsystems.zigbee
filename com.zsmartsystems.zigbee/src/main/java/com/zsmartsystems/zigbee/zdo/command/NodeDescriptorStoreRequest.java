package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Node Descriptor Store Request value object class.
 * <p>
 * The Node_Desc_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their Node Descriptor on a Primary Discovery
 * Cache device which has previously received a SUCCESS status from a
 * Discovery_store_req to the same Primary Discovery Cache device. Included in
 * this request is the Node Descriptor the Local Device wishes to cache.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class NodeDescriptorStoreRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public NodeDescriptorStoreRequest() {
        clusterId = 0x0017;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("NodeDescriptorStoreRequest ");
        builder.append(super.toString());
        return builder.toString();
    }

}
