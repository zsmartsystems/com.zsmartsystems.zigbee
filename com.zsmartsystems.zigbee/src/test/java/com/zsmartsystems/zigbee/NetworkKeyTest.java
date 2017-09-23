/**
 * Copyright (c) 2016-2017 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.InvalidParameterException;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class NetworkKeyTest {

    @Test
    public void testConstructorArray() {
        NetworkKey key = new NetworkKey(new int[] { 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0x00, 0xAA,
                0xBB, 0xCC, 0xDD, 0xEE, 0xFF });
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());
    }

    @Test(expected = InvalidParameterException.class)
    public void testConstructorArrayShort() {
        new NetworkKey(
                new int[] { 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0x00, 0xAA, 0xBB, 0xCC, 0xDD, 0xEE });
    }

    @Test
    public void testConstructorString() {
        NetworkKey key = new NetworkKey("11223344556677889900AABBCCDDEEFF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());
    }

    @Test(expected = InvalidParameterException.class)
    public void testConstructorStringShort() {
        NetworkKey key = new NetworkKey("11223344556677889900AABBCCDDEE");
    }

    @Test
    public void testValid() {
        NetworkKey key = new NetworkKey("11223344556677889900AABBCCDDEEFF");
        assertTrue(key.isValid());

        key = new NetworkKey("00000000000000000000000000000000");
        assertFalse(key.isValid());
    }

    @Test
    public void testHash() {
        NetworkKey key1 = new NetworkKey("11223344556677889900AABBCCDDEEFF");
        NetworkKey key2 = new NetworkKey("11223344556677889900AABBCCDDEEFF");
        assertEquals(key1.hashCode(), key2.hashCode());
    }

    @Test
    public void testEquals() {
        NetworkKey key1 = new NetworkKey("11223344556677889900AABBCCDDEEFF");
        NetworkKey key2 = new NetworkKey("11223344556677889900AABBCCDDEEFF");
        assertTrue(key1.equals(key2));
    }

    @Test
    public void testToString() {
        NetworkKey key = new NetworkKey("11223344556677889900AABBCCDDEEFF");
        assertEquals("11223344556677889900AABBCCDDEEFF", key.toString());
    }
}
