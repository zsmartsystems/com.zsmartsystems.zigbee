package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Find Node Cache Response value object class.
 * <p>
 * The Find_node_cache_rsp is provided to notify a Local Device of the successful
 * discovery of the Primary Discovery Cache device for the given NWKAddr and
 * IEEEAddr fields supplied in the request, or to signify that the device of interest is
 * capable of responding to discovery requests. The Find_node_cache_rsp shall be
 * generated only by Primary Discovery Cache devices holding discovery
 * information for the NWKAddr and IEEEAddr in the request or the device of
 * interest itself and all other Remote Devices shall not supply a response.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class FindNodeCacheResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public FindNodeCacheResponse() {
        clusterId = 0x801C;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("FindNodeCacheResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}
