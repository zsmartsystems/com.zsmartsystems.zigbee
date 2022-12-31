/**
 * Copyright (c) 2016-2023 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.IeeeAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeKeyTest {

    @Test
    public void testConstructorArray() {
        ZigBeeKey key = new ZigBeeKey(new int[] { 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0x00, 0xAA,
                0xBB, 0xCC, 0xDD, 0xEE, 0xFF });
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorArrayShort() {
        new ZigBeeKey(
                new int[] { 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0x00, 0xAA, 0xBB, 0xCC, 0xDD, 0xEE });
    }

    @Test
    public void testConstructorString() {
        ZigBeeKey key = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());

        key = new ZigBeeKey("11 22 33 44 55 66 77 88 99 00 AA BB CC DD EE FF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());

        key = new ZigBeeKey("11,22,33,44,55,66,77,88,99,00,AA,BB,CC,DD,EE,FF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());

        key = new ZigBeeKey("11:22:33:44:55:66:77:88:99:00:AA:BB:CC:DD:EE:FF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());

        key = new ZigBeeKey("0x11 0x22 0x33 0x44 0x55 0x66 0x77 0x88 0x99 0x00 0xAA 0xBB 0xCC 0xDD 0xEE 0xFF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());

        key = new ZigBeeKey("0x11,0x22,0x33,0x44,0x55,0x66,0x77,0x88,0x99,0x00,0xAA,0xBB,0xCC,0xDD,0xEE,0xFF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());

        key = new ZigBeeKey("0x11:0x22:0x33:0x44:0x55:0x66:0x77:0x88:0x99:0x00:0xAA:0xBB:0xCC:0xDD:0xEE:0xFF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorStringShort() {
        new ZigBeeKey("11223344556677889900AABBCCDDEE");
    }

    @Test
    public void testValid() {
        ZigBeeKey key = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertTrue(key.isValid());

        key = new ZigBeeKey("00000000000000000000000000000000");
        assertFalse(key.isValid());
    }

    @Test
    public void testRandom() {
        ZigBeeKey key = ZigBeeKey.createRandom();
        assertTrue(key.isValid());
    }

    @Test
    public void testHash() {
        ZigBeeKey key1 = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        ZigBeeKey key2 = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertEquals(key1.hashCode(), key2.hashCode());
    }

    @Test
    public void testEquals() {
        ZigBeeKey key1 = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        ZigBeeKey key2 = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertTrue(key1.equals(key2));
    }

    @Test
    public void testToString() {
        ZigBeeKey key = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());
    }

    @Test
    public void testAddress() {
        ZigBeeKey key = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertFalse(key.hasAddress());
        assertNull(key.getAddress());
        key.setAddress(new IeeeAddress("1234567890ABCDE"));
        assertTrue(key.hasAddress());
        assertEquals(new IeeeAddress("1234567890ABCDE"), key.getAddress());
    }

    @Test
    public void testOutgoingFrameCounter() {
        ZigBeeKey key = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertFalse(key.hasOutgoingFrameCounter());
        assertNull(key.getOutgoingFrameCounter());
        key.setOutgoingFrameCounter(1);
        assertTrue(key.hasOutgoingFrameCounter());
        assertEquals(Integer.valueOf(1), key.getOutgoingFrameCounter());
    }

    @Test
    public void testIncomingFrameCounter() {
        ZigBeeKey key = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertFalse(key.hasIncomingFrameCounter());
        assertNull(key.getIncomingFrameCounter());
        key.setIncomingFrameCounter(1);
        assertTrue(key.hasIncomingFrameCounter());
        assertEquals(Integer.valueOf(1), key.getIncomingFrameCounter());
    }

    @Test
    public void testSequenceNumber() {
        ZigBeeKey key = new ZigBeeKey("11223344556677889900AABBCCDDEEFF");
        assertFalse(key.hasSequenceNumber());
        assertNull(key.getSequenceNumber());
        key.setSequenceNumber(2);
        assertTrue(key.hasSequenceNumber());
        assertEquals(Integer.valueOf(2), key.getSequenceNumber());
    }

}
