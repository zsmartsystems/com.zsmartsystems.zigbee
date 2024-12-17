/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal.serializer;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackSerializerTest {

    @Test
    public void deserializeUInt16() {
        ZstackSerializer request = new ZstackSerializer();
        request.serializeUInt16(0x1234);

        assertTrue(Arrays.equals(new int[] { 0x34, 0x12 }, request.getBuffer()));
    }

    @Test
    public void serializeUInt32() {
        ZstackSerializer request = new ZstackSerializer();
        request.serializeUInt32(0x12345678);

        assertTrue(Arrays.equals(new int[] { 0x78, 0x56, 0x34, 0x12 }, request.getBuffer()));
    }

    @Test
    public void serializeIeeeAddress() {
        ZstackSerializer request = new ZstackSerializer();
        IeeeAddress address = new IeeeAddress("1234567890ABCDEF");
        request.serializeIeeeAddress(address);

        assertTrue(Arrays.equals(new int[] { 0xEF, 0xCD, 0xAB, 0x90, 0x78, 0x56, 0x34, 0x12 }, request.getBuffer()));
    }

    @Test
    public void serializeBoolean() {
        ZstackSerializer request = new ZstackSerializer();
        request.serializeBoolean(false);
        request.serializeBoolean(true);

        assertTrue(Arrays.equals(new int[] { 0x00, 0x01 }, request.getBuffer()));
    }
}
