package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Active Endpoint Store value object class.
 * <p>
 * The Active_EP_store_req is provided to enable ZigBee end devices on the
 * network to request storage of their list of Active Endpoints on a Primary
 * Discovery Cache device which has previously received a SUCCESS status from a
 * Discovery_store_req to the same Primary Discovery Cache device. Included in
 * this request is the count of Active Endpoints the Local Device wishes to cache and
 * the endpoint list itself.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class ActiveEndpointStore extends ZdoResponse {
    /**
     * Default constructor.
     */
    public ActiveEndpointStore() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("ActiveEndpointStore");
        builder.append(super.toString());
        return builder.toString();
    }

}
