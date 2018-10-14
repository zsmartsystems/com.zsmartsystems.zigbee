/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serialization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.ZclStatus;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;
import com.zsmartsystems.zigbee.zdo.ZdoStatus;

/**
 * This performs a simple set of tests to make sure the serialiser/deserializer can read back what it writes.
 *
 * @author Chris Jackson
 *
 */
public class SerializerIntegrationTest {
    @Test
    public void testDeserialize_ZDO_STATUS() {
        ZdoStatus valIn = ZdoStatus.NO_DESCRIPTOR;
        testSerializer(valIn, ZclDataType.ZDO_STATUS);
    }

    @Test
    public void testDeserialize_ZCL_STATUS() {
        ZclStatus valIn = ZclStatus.CALIBRATION_ERROR;
        testSerializer(valIn, ZclDataType.ZCL_STATUS);
    }

    @Test
    public void testDeserialize_BYTE_ARRAY() {
        ByteArray valIn = new ByteArray(new byte[] { 1, 2, 3, 4, 5 });
        testSerializer(valIn, ZclDataType.BYTE_ARRAY);
    }

    @Test
    public void testDeserialize_BITMAP_8_BIT() {
        int valIn = 0x91;
        testSerializer(valIn, ZclDataType.BITMAP_8_BIT);
    }

    @Test
    public void testDeserialize_OCTET_STRING() {
        ByteArray valIn = new ByteArray(new byte[] { 1, 2, 3, 4, 5, 6, 7, 8 });
        testSerializer(valIn, ZclDataType.OCTET_STRING);
    }

    @Test
    public void testDeserialize_N_X_ATTRIBUTE_IDENTIFIER() {
        List<Integer> valIn = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 192 });
        testSerializer(valIn, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_N_X_UNSIGNED_8_BIT_INTEGER() {
        List<Integer> valIn = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 192 });
        testSerializer(valIn, ZclDataType.N_X_UNSIGNED_8_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_UNSIGNED_8_BIT_INTEGER_ARRAY() {
        int[] valIn = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 192 };
        testSerializer(valIn, ZclDataType.UNSIGNED_8_BIT_INTEGER_ARRAY);
    }

    @Test
    public void testDeserialize_X_UNSIGNED_8_BIT_INTEGER() {
        List<Integer> valIn = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 192 });
        testSerializer(valIn, ZclDataType.X_UNSIGNED_8_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_N_X_UNSIGNED_16_BIT_INTEGER() {
        List<Integer> valIn = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0x8888 });
        testSerializer(valIn, ZclDataType.N_X_UNSIGNED_16_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_BITMAP_32_BIT() {
        int valIn = 0x9119;
        testSerializer(valIn, ZclDataType.BITMAP_32_BIT);
    }

    @Test
    public void testDeserialize_BITMAP_16_BIT() {
        int valIn = 0x9119;
        testSerializer(valIn, ZclDataType.BITMAP_16_BIT);
    }

    @Test
    public void testDeserialize_NWK_ADDRESS() {
        int valIn = 0x9119;
        testSerializer(valIn, ZclDataType.NWK_ADDRESS);
    }

    @Test
    public void testDeserialize_ENUMERATION_8_BIT() {
        int valIn = 0x91;
        testSerializer(valIn, ZclDataType.ENUMERATION_8_BIT);
    }

    @Test
    public void testDeserialize_ENUMERATION_16_BIT() {
        int valIn = 0x9119;
        testSerializer(valIn, ZclDataType.ENUMERATION_16_BIT);
    }

    @Test
    public void testSerialize_IEEE_ADDRESS() {
        IeeeAddress valIn = new IeeeAddress("1234567890123456");
        testSerializer(valIn, ZclDataType.IEEE_ADDRESS);
    }

    @Test
    public void testSerialize_EXTENDED_PANID() {
        ExtendedPanId valIn = new ExtendedPanId("1234567890123456");
        testSerializer(valIn, ZclDataType.EXTENDED_PANID);
    }

    @Test
    public void testDeserialize_BOOLEAN() {
        boolean valIn = true;
        testSerializer(valIn, ZclDataType.BOOLEAN);
        valIn = false;
        testSerializer(valIn, ZclDataType.BOOLEAN);
    }

    @Test
    public void testDeserialize_DATA_8_BIT() {
        int valIn = 0x9;
        testSerializer(valIn, ZclDataType.DATA_8_BIT);
    }

    @Test
    public void testDeserialize_SIGNED_8_BIT_INTEGER() {
        int valIn = -23;
        testSerializer(valIn, ZclDataType.SIGNED_8_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_UNSIGNED_8_BIT_INTEGER() {
        int valIn = 0x98;
        testSerializer(valIn, ZclDataType.UNSIGNED_8_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_SIGNED_16_BIT_INTEGER() {
        int valIn = -23456;
        testSerializer(valIn, ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_UNSIGNED_16_BIT_INTEGER() {
        int valIn = 0x9971;
        testSerializer(valIn, ZclDataType.UNSIGNED_16_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_UNSIGNED_24_BIT_INTEGER() {
        int valIn = 0x997186;
        testSerializer(valIn, ZclDataType.UNSIGNED_24_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_SIGNED_32_BIT_INTEGER() {
        int valIn = -2345;
        testSerializer(valIn, ZclDataType.SIGNED_32_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_UNSIGNED_32_BIT_INTEGER() {
        int valIn = 0xE3970456;
        testSerializer(valIn, ZclDataType.UNSIGNED_32_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_UNSIGNED_48_BIT_INTEGER() {
        long valIn = 0xE39704561234L;
        testSerializer(valIn, ZclDataType.UNSIGNED_48_BIT_INTEGER);
    }

    @Test
    public void testDeserialize_ENDPOINT() {
        int valIn = 0x34;
        testSerializer(valIn, ZclDataType.ENDPOINT);
    }

    @Test
    public void testDeserialize_CLUSTERID() {
        int valIn = 0x1234;
        testSerializer(valIn, ZclDataType.CLUSTERID);
    }

    @Test
    public void testDeserialize_ZIGBEE_DATA_TYPE() {
        ZclDataType valIn = ZclDataType.BITMAP_16_BIT;
        testSerializer(valIn, ZclDataType.ZIGBEE_DATA_TYPE);
    }

    @Test
    public void testDeserialize_CHARACTER_STRING() {
        String valIn = "Hello World";
        testSerializer(valIn, ZclDataType.CHARACTER_STRING);
    }

    private void testSerializer(Object objectIn, ZclDataType type) {
        DefaultSerializer serializer = new DefaultSerializer();
        serializer.appendZigBeeType(objectIn, type);
        int[] buffer = serializer.getPayload();
        int size = buffer.length;
        DefaultDeserializer deserializer = new DefaultDeserializer(buffer);
        assertEquals(size, deserializer.getSize());
        Object objectOut = deserializer.readZigBeeType(type);
        if (objectIn instanceof Integer[]) {
            assertTrue(Arrays.equals((Integer[]) objectIn, (Integer[]) objectOut));
        } else if (objectIn instanceof int[]) {
            assertTrue(Arrays.equals((int[]) objectIn, (int[]) objectOut));
        } else if (objectIn instanceof byte[]) {
            assertTrue(Arrays.equals((byte[]) objectIn, (byte[]) objectOut));
        } else {
            assertEquals(objectIn, objectOut);
        }
        assertEquals(size, deserializer.getPosition());
        assertTrue(deserializer.isEndOfStream());
    }
}
