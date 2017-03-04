package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Backup Bind Table Response value object class.
 * <p>
 * The Backup_Bind_Table_rsp is generated from a backup binding table cache
 * device in response to a Backup_Bind_Table_req from a primary binding table
 * cache and contains the status of the request. This command shall be unicast to the
 * requesting device. If the remote device is not a backup binding table cache, it shall
 * return a status of NOT_SUPPORTED. If the originator of the request is not
 * recognized as a primary binding table cache, it shall return a status of
 * INV_REQUESTTYPE. Otherwise, the backup binding table cache shall
 * overwrite the binding entries in its binding table starting with StartIndex and
 * continuing for BindingTableListCount entries. If this exceeds its table size, it shall
 * fill in as many entries as possible and return a status of TABLE_FULL and the
 * EntryCount parameter will be the number of entries in the table. Otherwise, it
 * shall return a status of SUCCESS and EntryCount will be equal to StartIndex +
 * BindingTableListCount from Backup_Bind_Table_req.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class BackupBindTableResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public BackupBindTableResponse() {
        clusterId = 0x8027;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BackupBindTableResponse ");
        builder.append(super.toString());
        return builder.toString();
    }

}
