/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zdo.ZdoRequest;

/**
 * Store Backup Bind Entry Request value object class.
 * <p>
 * <p>
 * The Store_Bkup_Bind_Entry_req is generated from a local primary binding table cache and
 * sent to a remote backup binding table cache device to request backup storage of the entry. It
 * will be generated whenever a new binding table entry has been created by the primary binding
 * table cache. The destination addressing mode for this request is unicast.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class StoreBackupBindEntryRequest extends ZdoRequest {
    /**
     * Default constructor.
     */
    public StoreBackupBindEntryRequest() {
        clusterId = 0x0025;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(30);
        builder.append("StoreBackupBindEntryRequest [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
