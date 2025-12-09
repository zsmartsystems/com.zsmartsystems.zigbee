/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api;

import java.util.Arrays;

import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import com.zsmartsystems.zigbee.dongle.zstack.internal.serializer.ZstackSerializer;

/**
 *
 * @author Chris Jackson
 *
 */
public abstract class ZstackFrameRequest extends ZstackCommand {
    protected ZstackSerializer serializer = new ZstackSerializer();

    private static final int ZSTACK_SOF = 0xFE;

    protected int ZSTACK_SREQ = 0x20;
    protected int ZSTACK_AREQ = 0x40;
    protected int ZSTACK_ACMD = 0x40;

    public abstract int[] serialize();

    protected void serializeHeader(int type, int subsystem, int id) {
        serializer.serializeUInt8(ZSTACK_SOF);
        serializer.serializeUInt8(0); // Length will be updated later
        serializer.serializeUInt8(type + subsystem);
        serializer.serializeUInt8(id);
    }

    protected int[] getPayload() {
        serializer.serializeUInt8(0); // Checksum will be updated later
        int buffer[] = serializer.getBuffer();
        buffer[1] = buffer.length - 5;

        int checksum = 0;
        for (int cnt = 1; cnt < buffer.length; cnt++) {
            checksum ^= buffer[cnt];
        }
        buffer[buffer.length - 1] = checksum & 0xFF;

        return Arrays.copyOfRange(buffer, 0, buffer.length);
    }

    /**
     * Processes the {@link ZstackRpcSreqErrorSrsp} response which is an error return from the NCP and checks if it is
     * related to this request.
     *
     * @param response the received {@link ZstackRpcSreqErrorSrsp}
     * @return true if this request matches the command codes in the {@link ZstackRpcSreqErrorSrsp}
     */
    public boolean matchSreqError(ZstackRpcSreqErrorSrsp response) {
        return false;
    }
}
