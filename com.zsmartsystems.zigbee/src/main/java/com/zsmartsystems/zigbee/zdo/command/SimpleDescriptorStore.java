package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Simple Descriptor Store value object class.
 * <p>
 * The Simple_desc_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their list of Simple Descriptors on a Primary
 * Discovery Cache device which has previously received a SUCCESS status from a
 * Discovery_store_req to the same Primary Discovery Cache device. Note that each
 * Simple Descriptor for every active endpoint on the Local Device must be
 * individually uploaded to the Primary Discovery Cache device via this command
 * to enable cached discovery. Included in this request is the length of the Simple
 * Descriptor the Local Device wishes to cache and the Simple Descriptor itself. The
 * endpoint is a field within the Simple Descriptor and is accessed by the Remote
 * Device to manage the discovery cache information for the Local Device.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class SimpleDescriptorStore extends ZdoResponse {
    /**
     * Default constructor.
     */
    public SimpleDescriptorStore() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("SimpleDescriptorStore");
        builder.append(super.toString());
        return builder.toString();
    }

}
