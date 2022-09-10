/**
 * Copyright (c) 2016-2022 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api.sbl;

import com.zsmartsystems.zigbee.dongle.zstack.api.ZstackFrameRequest;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;

/**
 * Class to implement the Z-Stack command <b>SB_WRITE_CMD</b>.
 * <p>
 * Note that this code is autogenerated. Manual changes may be overwritten.
 *
 * @author Chris Jackson
 */
public class ZstackSbWriteCmdSreq extends ZstackFrameRequest {

    /**
     * Payload data.
     */
    private int[] payload;

    /**
     * Request constructor
     */
    public ZstackSbWriteCmdSreq() {
        synchronousCommand = true;
    }

    /**
     * Payload data.
     *
     * @return the current payload as {@link int[]}
     */
    public int[] getPayload() {
        return payload;
    }

    /**
     * Payload data.
     *
     * @param payload the Payload to set as {@link int[]}
     */
    public void setPayload(int[] payload) {
        this.payload = payload;
    }

    @Override
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return (((response.getReqCmd0() & 0x1F) == ZSTACK_SBL) && (response.getReqCmd1() == 0x00));
    }

    @Override
    public int[] serialize() {
        // Serialize the header
        serializeHeader(ZSTACK_SREQ, ZSTACK_SBL, 0x00);

        // Serialize the fields
        serializer.serializeUInt32(payload.length);
        serializer.serializeUInt8Array(payload);
        return getPayload();
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(73);
        builder.append("ZstackSbWriteCmdSreq [payload=");
        for (int c = 0; c < payload.length; c++) {
            if (c > 0) {
                builder.append(' ');
            }
            builder.append(String.format("%02X", payload[c]));
        }
        builder.append(']');
        return builder.toString();
    }
}
