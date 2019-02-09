/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.zdo.command;

import javax.annotation.Generated;

import com.zsmartsystems.zigbee.zdo.ZdoResponse;

/**
 * Recover Source Bind Response value object class.
 * <p>
 * <p>
 * The Recover_Source_Bind_rsp is generated from a backup binding table cache device in
 * response to a Recover_Source_Bind_req from a primary binding table cache and contains the
 * status of the request. This command shall be unicast to the requesting device. If the
 * responding device is not a backup binding table cache, it shall return a status of
 * NOT_SUPPORTED. If the originator of the request is not recognized as a primary binding table
 * cache, it shall return a status of INV_REQUESTTYPE. Otherwise, the backup binding table
 * cache shall prepare a list of binding table entries from its backup beginning with
 * StartIndex. It will fit in as many entries as possible into a Recover_Source_Bind_rsp
 * command and return a status of SUCCESS. If StartIndex is more than the number of entries in the
 * Source table, a status of NO_ENTRY is returned. For a successful response,
 * SourceTableEntries is the total number of entries in the backup source table, and
 * SourceTableListCount is the number of entries which is being returned in the response.
 * <p>
 * Code is auto-generated. Modifications may be overwritten!
 */
@Generated(value = "com.zsmartsystems.zigbee.autocode.ZigBeeCodeGenerator", date = "2019-02-09T15:23:12Z")
public class RecoverSourceBindResponse extends ZdoResponse {
    /**
     * Default constructor.
     */
    public RecoverSourceBindResponse() {
        clusterId = 0x8029;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(28);
        builder.append("RecoverSourceBindResponse [");
        builder.append(super.toString());
        builder.append(']');
        return builder.toString();
    }

}
