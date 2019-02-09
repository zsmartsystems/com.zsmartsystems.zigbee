/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * Backup Bind Table Response value object class.
 * <p>
 * <p>
 * The Backup_Bind_Table_rsp is generated from a backup binding table cache device in
 * response to a Backup_Bind_Table_req from a primary binding table cache and contains the
 * status of the request. This command shall be unicast to the requesting device. If the remote
 * device is not a backup binding table cache, it shall return a status of NOT_SUPPORTED. If the
 * originator of the request is not recognized as a primary binding table cache, it shall return
 * a status of INV_REQUESTTYPE. Otherwise, the backup binding table cache shall overwrite the
 * binding entries in its binding table starting with StartIndex and continuing for
 * BindingTableListCount entries. If this exceeds its table size, it shall fill in as many
 * entries as possible and return a status of TABLE_FULL and the EntryCount parameter will be
 * the number of entries in the table. Otherwise, it shall return a status of SUCCESS and
 * EntryCount will be equal to StartIndex + BindingTableListCount from
 * Backup_Bind_Table_req.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T17:07:08Z")
public class BackupBindTableResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public BackupBindTableResponse() {
        clusterId = 0x8027;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(52);
        builder.append("BackupBindTableResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(']');
        return builder.toString();
    }

}
