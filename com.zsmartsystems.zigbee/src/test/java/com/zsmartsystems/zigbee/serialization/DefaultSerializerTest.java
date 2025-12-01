/**
 * Copyright (c) 2016-2025 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serialization;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
import com.zsmartsystems.zigbee.zcl.field.ByteArray;
import com.zsmartsystems.zigbee.zcl.protocol.ZclDataType;

/**
 *
 * @author Chris Jackson
 *
 */
public class DefaultSerializerTest {
    @Test
    public void testSerialize_null() {
        boolean caught = false;
        try {
            testSerializedData(null, null, ZclDataType.DATA_8_BIT);
        } catch (IllegalArgumentException e) {
            caught = true;
        }

        assertTrue(caught);
    }

    @Test
    public void testSerialize_DATA_8_BIT() {
        int valIn = 0x9;
        int[] valOut = { 0x9 };
        testSerializedData(valIn, valOut, ZclDataType.DATA_8_BIT);
    }

    @Test
    public void testSerialize_RAW_OCTET() {
        ByteArray valIn = new ByteArray(new int[] { 0x00, 0x11, 0x22, 0x44, 0x88, 0xCC, 0xFF });
        int[] valOut = { 0x00, 0x11, 0x22, 0x44, 0x88, 0xCC, 0xFF };
        testSerializedData(valIn, valOut, ZclDataType.RAW_OCTET);
    }

    @Test
    public void testSerialize_SIGNED_16_BIT_INTEGER() {
        int valIn = 0x397;
        int[] valOut = { 0x97, 0x03 };
        testSerializedData(valIn, valOut, ZclDataType.SIGNED_16_BIT_INTEGER);
    }

    @Test
    public void testSerialize_IEEE_ADDRESS() {
        IeeeAddress valIn = new IeeeAddress("1234567890123456");
        int[] valOut = { 0x56, 0x34, 0x12, 0x90, 0x78, 0x56, 0x34, 0x12 };
        testSerializedData(valIn, valOut, ZclDataType.IEEE_ADDRESS);
    }

    @Test
    public void testSerialize_EXTENDED_PANID() {
        ExtendedPanId valIn = new ExtendedPanId("1234567890123456");
        int[] valOut = { 0x56, 0x34, 0x12, 0x90, 0x78, 0x56, 0x34, 0x12 };
        testSerializedData(valIn, valOut, ZclDataType.EXTENDED_PANID);
    }

    @Test
    public void testSerialize_X_UNSIGNED_8_BIT_INTEGER() {
        List<Integer> valIn = new ArrayList<Integer>();
        valIn.add(1);
        valIn.add(2);
        valIn.add(3);
        valIn.add(4);
        valIn.add(5);
        int[] valOut = new int[] { 1, 2, 3, 4, 5 };
        testSerializedData(valIn, valOut, ZclDataType.X_UNSIGNED_8_BIT_INTEGER);
    }

    private void testSerializedData(Object object, int[] output, ZclDataType type) {
        DefaultSerializer serializer = new DefaultSerializer();
        serializer.appendZigBeeType(object, type);
        int[] data = serializer.getPayload();
        System.out.println("Serialize: " + type + " >> " + object + " = " + arrayToString(data) + ", expect "
                + arrayToString(output));
        assertTrue(Arrays.equals(output, data));
    }

    private String arrayToString(int[] value) {
        StringBuilder builder = new StringBuilder(120);
        builder.append('[');
        boolean first = true;
        for (int val : value) {
            if (!first) {
                builder.append(' ');
            }
            first = false;
            builder.append(String.format("%02X", val & 0xFF));
        }
        builder.append(']');

        return builder.toString();
    }
}
