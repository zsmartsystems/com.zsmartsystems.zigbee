/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.serialization;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.zsmartsystems.zigbee.ExtendedPanId;
import com.zsmartsystems.zigbee.IeeeAddress;
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
        assertTrue(Arrays.equals(output, data));
    }
}
