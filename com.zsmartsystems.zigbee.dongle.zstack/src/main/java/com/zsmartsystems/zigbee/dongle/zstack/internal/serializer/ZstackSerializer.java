/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal.serializer;

import java.util.Arrays;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackSerializer {
    private int[] buffer = new int[250];
    private int length = 0;

    public void serializeUInt8(int uint8) {
        buffer[length++] = uint8 & 0xFF;
    }

    public void serializeUInt16(int uint16) {
        buffer[length++] = uint16 & 0xFF;
        buffer[length++] = (uint16 >> 8) & 0xFF;
    }

    public void serializeUInt32(int uint32) {
        buffer[length++] = uint32 & 0xFF;
        buffer[length++] = (uint32 >> 8) & 0xFF;
        buffer[length++] = (uint32 >> 16) & 0xFF;
        buffer[length++] = (uint32 >> 24) & 0xFF;
    }

    public void serializeBoolean(boolean bool) {
        buffer[length++] = bool ? 1 : 0;
    }

    public void serializeUInt8Array(int[] uint8Array) {
        for (int val : uint8Array) {
            serializeUInt8(val);
        }
    }

    public void serializeUInt16Array(int[] uint16Array) {
        for (int val : uint16Array) {
            serializeUInt16(val);
        }
    }

    public void serializeIeeeAddress(IeeeAddress address) {
        buffer[length++] = address.getValue()[0];
        buffer[length++] = address.getValue()[1];
        buffer[length++] = address.getValue()[2];
        buffer[length++] = address.getValue()[3];
        buffer[length++] = address.getValue()[4];
        buffer[length++] = address.getValue()[5];
        buffer[length++] = address.getValue()[6];
        buffer[length++] = address.getValue()[7];
    }

    public void serializeZigBeeKey(ZigBeeKey keyData) {
        serializeUInt8Array(keyData.getValue());
    }

    public int[] getBuffer() {
        return Arrays.copyOfRange(buffer, 0, length);
    }

}
