/**
 * Copyright (c) 2016-2019 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.dongle.zstack.internal.serializer;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZstackDeserializerTest {

    @Test
    public void deserializeUInt16() {
        ZstackDeserializer response = new ZstackDeserializer(new int[] { 0x34, 0x12 });

        assertEquals(0x1234, response.deserializeUInt16());
    }

    @Test
    public void deserializeUInt32() {
        ZstackDeserializer response = new ZstackDeserializer(new int[] { 0x78, 0x56, 0x34, 0x12 });

        assertEquals(0x12345678, response.deserializeUInt32());
    }

    @Test
    public void deserializeBoolean() {
        ZstackDeserializer response = new ZstackDeserializer(new int[] { 0x00, 0x01 });

        assertEquals(false, response.deserializeBoolean());
        assertEquals(true, response.deserializeBoolean());
    }

    @Test
    public void deserializeIeeeAddress() {
        ZstackDeserializer response = new ZstackDeserializer(
                new int[] { 0xEF, 0xCD, 0xAB, 0x90, 0x78, 0x56, 0x34, 0x12 });

        assertEquals(new IeeeAddress("1234567890ABCDEF"), response.deserializeIeeeAddress());
    }

}
