/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.dongle.zstack.api.af.AfDataOptions;
import com.zsmartsystems.zigbee.dongle.zstack.api.rpc.ZstackRpcSreqErrorSrsp;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 *
 * @author Chris Jackson
 *
 */
public abstract class ZstackFrameRequest extends ZstackCommand {
    private int[] buffer = new int[250];
    private int length = 4;

    private static final int ZSTACK_SOF = 0xFE;

    protected int ZSTACK_SREQ = 0x20;
    protected int ZSTACK_AREQ = 0x40;
    protected int ZSTACK_ACMD = 0x40;

    public abstract int[] serialize();

    protected void serializeHeader(int type, int subsystem, int id) {
        buffer[2] = type + subsystem;
        buffer[3] = id;
    }

    protected void serializeUInt8(int uint8) {
        buffer[length++] = uint8 & 0xFF;
    }

    protected void serializeUInt16(int uint16) {
        buffer[length++] = uint16 & 0xFF;
        buffer[length++] = (uint16 >> 8) & 0xFF;
    }

    protected void serializeUInt32(int uint32) {
        buffer[length++] = uint32 & 0xFF;
        buffer[length++] = (uint32 >> 8) & 0xFF;
        buffer[length++] = (uint32 >> 16) & 0xFF;
        buffer[length++] = (uint32 >> 24) & 0xFF;
    }

    protected void serializeBoolean(boolean bool) {
        buffer[length++] = bool ? 1 : 0;
    }

    protected void serializeUInt8Array(int[] uint8Array) {
        for (int val : uint8Array) {
            serializeUInt8(val);
        }
    }

    protected void serializeUInt16Array(int[] uint16Array) {
        for (int val : uint16Array) {
            serializeUInt16(val);
        }
    }

    protected void serializeIeeeAddress(IeeeAddress address) {
        buffer[length++] = address.getValue()[0];
        buffer[length++] = address.getValue()[1];
        buffer[length++] = address.getValue()[2];
        buffer[length++] = address.getValue()[3];
        buffer[length++] = address.getValue()[4];
        buffer[length++] = address.getValue()[5];
        buffer[length++] = address.getValue()[6];
        buffer[length++] = address.getValue()[7];
    }

    protected void serializeZigBeeKey(ZigBeeKey keyData) {
        serializeUInt8Array(keyData.getValue());
    }

    protected void serializeAfDataOptions(AfDataOptions options2) {
        // TODO Auto-generated method stub

    }

    protected int[] getPayload() {
        buffer[0] = ZSTACK_SOF;
        buffer[1] = length - 4;

        int checksum = 0;
        for (int cnt = 1; cnt < length; cnt++) {
            checksum ^= buffer[cnt];
        }
        buffer[length++] = checksum & 0xFF;

        return Arrays.copyOfRange(buffer, 0, length);
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
