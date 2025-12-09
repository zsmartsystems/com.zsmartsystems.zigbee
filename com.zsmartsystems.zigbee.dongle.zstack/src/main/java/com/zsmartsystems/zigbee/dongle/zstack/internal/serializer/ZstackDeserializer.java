/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal.serializer;

import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.security.ZigBeeKey;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackDeserializer {
    private int[] buffer;
    private int position;

    public ZstackDeserializer(int[] inputBuffer) {
        buffer = inputBuffer;
        position = 0;
    }

    public int deserializeUInt8() {
        return buffer[position++];
    }

    public int deserializeUInt16() {
        return buffer[position++] + (buffer[position++] << 8);
    }

    public int deserializeUInt32() {
        return buffer[position++] + (buffer[position++] << 8) + (buffer[position++] << 16) + (buffer[position++] << 24);
    }

    public boolean deserializeBoolean() {
        return buffer[position++] != 0;
    }

    public IeeeAddress deserializeIeeeAddress() {
        int address[] = new int[8];
        for (int cnt = 0; cnt < 8; cnt++) {
            address[cnt] = buffer[position++];
        }
        return new IeeeAddress(address);
    }

    public int[] deserializeUInt8Array() {
        return deserializeUInt8Array(buffer.length - position);
    }

    public int[] deserializeUInt8Array(int len) {
        int[] array = new int[len];

        for (int cnt = 0; cnt < len; cnt++) {
            array[cnt] = deserializeUInt8();
        }

        return array;
    }

    public int[] deserializeUInt16Array(int len) {
        int[] array = new int[len];

        for (int cnt = 0; cnt < len; cnt++) {
            array[cnt] = deserializeUInt16();
        }

        return array;
    }

    public ZigBeeKey deserializeZigBeeKey() {
        return new ZigBeeKey(deserializeUInt8Array(16));
    }
}
