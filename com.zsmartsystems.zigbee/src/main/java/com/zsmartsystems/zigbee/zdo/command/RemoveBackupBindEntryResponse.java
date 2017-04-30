package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Remove Backup Bind Entry Response value object class.
 * <p>
 * The Remove_Bkup_Bind_Entry_rsp is generated from a backup binding table
 * cache device in response to a Remove_Bkup_Bind_Entry_req from the primary
 * binding table cache and contains the status of the request. This command shall be
 * unicast to the requesting device. If the remote device is not a backup binding table
 * cache, it shall return a status of NOT_SUPPORTED. If the originator of the
 * request is not recognized as a primary binding table cache, it shall return a status
 * of INV_REQUESTTYPE. Otherwise, the backup binding table cache shall delete
 * the binding entry from its binding table and return a status of SUCCESS. If the
 * entry is not found, it shall return a status of NO_ENTRY.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class RemoveBackupBindEntryResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public RemoveBackupBindEntryResponse() {
        clusterId = 0x8026;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("RemoveBackupBindEntryResponse [");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
