package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Store Backup Bind Entry Request value object class.
 * <p>
 * The Store_Bkup_Bind_Entry_req is generated from a local primary binding table
 * cache and sent to a remote backup binding table cache device to request backup
 * storage of the entry. It will be generated whenever a new binding table entry has
 * been created by the primary binding table cache. The destination addressing mode
 * for this request is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
public class StoreBackupBindEntryRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public StoreBackupBindEntryRequest() {
        clusterId = 0x0025;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("StoreBackupBindEntryRequest ");
        builder.append(super.toString());
        return builder.toString();
    }

}
