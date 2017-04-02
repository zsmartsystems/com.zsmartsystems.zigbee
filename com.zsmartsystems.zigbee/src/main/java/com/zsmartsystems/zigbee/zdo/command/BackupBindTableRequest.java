package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Backup Bind Table Request value object class.
 * <p>
 * The Backup_Bind_Table_req is generated from a local primary binding table
 * cache and sent to the remote backup binding table cache device to request backup
 * storage of its entire binding table. The destination addressing mode for this
 * request is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class BackupBindTableRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public BackupBindTableRequest() {
        clusterId = 0x0027;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("BackupBindTableRequest");
        builder.append(super.toString());
        return builder.toString();
    }

}
