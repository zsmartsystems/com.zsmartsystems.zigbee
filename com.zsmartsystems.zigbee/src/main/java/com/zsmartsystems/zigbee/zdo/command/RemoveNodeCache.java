package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Remove Node Cache value object class.
 * <p>
 * The Remove_node_cache_rsp is provided to notify a Local Device of the request
 * status from a Primary Discovery Cache device. Included in the response is a status
 * code to notify the Local Device whether the request is successful (the Primary
 * Cache Device has removed the discovery cache data for the indicated device of
 * interest), or the request is not supported (meaning the Remote Device is not a
 * Primary Discovery Cache device).
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class RemoveNodeCache extends ZdoResponse {
    /**
     * Default constructor.
     */
    public RemoveNodeCache() {
        clusterId = 0x801B;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RemoveNodeCache [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
