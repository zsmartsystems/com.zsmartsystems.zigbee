/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.api;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 *
 * @author Chris Jackson
 *
 */
public abstract class ZstackFrameResponse extends ZstackCommand {
    private int[] buffer;
    private int position;

    public ZstackFrameResponse(int[] inputBuffer) {
        buffer = inputBuffer;
        position = 2; // Skip the command ID
    }

    protected int deserializeUInt8() {
        return buffer[position++];
    }

    protected int deserializeUInt16() {
        return buffer[position++] + (buffer[position++] << 8);
    }

    protected int deserializeUInt32() {
        return buffer[position++] + (buffer[position++] << 8) + (buffer[position++] << 16) + (buffer[position++] << 24);
    }

    protected boolean deserializeBoolean() {
        return buffer[position++] != 0;
    }

    protected IeeeAddress deserializeIeeeAddress() {
        int address[] = new int[8];
        for (int cnt = 0; cnt < 8; cnt++) {
            address[cnt] = buffer[position++];
        }
        return new IeeeAddress(address);
    }

    protected int[] deserializeUInt8Array() {
        return deserializeUInt8Array(buffer.length - position);
    }

    protected int[] deserializeUInt8Array(int len) {
        int[] array = new int[len];

        for (int cnt = 0; cnt < len; cnt++) {
            array[cnt] = deserializeUInt8();
        }

        return array;
    }

    protected int[] deserializeUInt16Array(int len) {
        int[] array = new int[len];

        for (int cnt = 0; cnt < len; cnt++) {
            array[cnt] = deserializeUInt16();
        }

        return array;
    }

    protected ZigBeeKey deserializeZigBeeKey() {
        return new ZigBeeKey(deserializeUInt8Array(16));
    }
}
