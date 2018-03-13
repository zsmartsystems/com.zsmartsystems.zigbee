/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import com.zsmartsystems.zigbee.zcl.ZclFieldSerializer;
import com.zsmartsystems.zigbee.zcl.ZclFieldDeserializer;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoResponse;
import javax.annotation.Generated;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

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

@Generated(value = "com.zsmartsystems.zigbee.autocode.ZclProtocolCodeGenerator", date = "2018-03-12T23:36:29Z")
public class RemoveBackupBindEntryResponse extends ZdoResponse {
    /**
     * EntryCount command message field.
     */
    private Integer entryCount;

    /**
     * Default constructor.
     */
    public RemoveBackupBindEntryResponse() {
        clusterId = 0x8026;
    }

    /**
     * Gets EntryCount.
     *
     * @return the EntryCount
     */
    public Integer getEntryCount() {
        return entryCount;
    }

    /**
     * Sets EntryCount.
     *
     * @param entryCount the EntryCount
     */
    public void setEntryCount(final Integer entryCount) {
        this.entryCount = entryCount;
    }

    @Override
    public void serialize(final ZclFieldSerializer serializer) {
        super.serialize(serializer);

        serializer.serialize(status, ZclDataType.ZDO_STATUS);
        serializer.serialize(entryCount, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public void deserialize(final ZclFieldDeserializer deserializer) {
        super.deserialize(deserializer);

        status = (ZdoStatus) deserializer.deserialize(ZclDataType.ZDO_STATUS);
        if (status != ZdoStatus.SUCCESS) {
            // Don't read the full response if we have an error
            return;
        }
        entryCount = (Integer) deserializer.deserialize(ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(88);
        builder.append("RemoveBackupBindEntryResponse [");
        builder.append(super.toString());
        builder.append(", status=");
        builder.append(status);
        builder.append(", entryCount=");
        builder.append(entryCount);
        builder.append(']');
        return builder.toString();
    }

}
