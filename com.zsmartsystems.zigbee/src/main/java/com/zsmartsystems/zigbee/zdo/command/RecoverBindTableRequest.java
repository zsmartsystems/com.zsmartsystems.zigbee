package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Recover Bind Table Request value object class.
 * <p>
 * The Recover_Bind_Table_req is generated from a local primary binding table
 * cache and sent to a remote backup binding table cache device when it wants a
 * complete restore of the binding table. The destination addressing mode for this
 * request is unicast.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class RecoverBindTableRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public RecoverBindTableRequest() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RecoverBindTableRequest");
        builder.append(super.toString());
        return builder.toString();
    }

}
