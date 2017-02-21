package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Remove Backup Bind Table Request value object class.
 * <p>
 * The Remove_Bkup_Bind_Entry_req is generated from a local primary binding
 * table cache and sent to a remote backup binding table cache device to request
 * removal of the entry from backup storage. It will be generated whenever a binding
 * table entry has been unbound by the primary binding table cache. The destination
 * addressing mode for this request is unicast.
 * <p>
 * Cluster: <b>General</b>. Command is sent <b>TO</b> the server.
 * This command is a <b>generic</b> command used across the profile.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class RemoveBackupBindTableRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public RemoveBackupBindTableRequest() {
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RemoveBackupBindTableRequest");
        builder.append(super.toString());
        return builder.toString();
    }

}
