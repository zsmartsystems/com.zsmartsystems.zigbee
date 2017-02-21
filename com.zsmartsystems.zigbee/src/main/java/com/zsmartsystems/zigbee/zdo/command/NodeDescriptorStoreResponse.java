package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Node Descriptor Store Response value object class.
 * <p>
 * The Node_store_rsp is provided to notify a Local Device of the request status
 * from a Primary Discovery Cache device. Included in the response is a status code
 * to notify the Local Device whether the request is successful (the Primary Cache
 * Device has space to store the discovery cache data for the Local Device), whether
 * the request is not supported (meaning the Remote Device is not a Primary
 * Discovery Cache device), or insufficient space exists.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class NodeDescriptorStoreResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public NodeDescriptorStoreResponse() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("NodeDescriptorStoreResponse");
        builder.append(super.toString());
        return builder.toString();
    }

}
