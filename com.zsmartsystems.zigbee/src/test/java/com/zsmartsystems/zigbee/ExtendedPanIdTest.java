/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.security.InvalidParameterException;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ExtendedPanIdTest {

    @Test
    public void testConstructorBigInteger() {
        ExtendedPanId address = new ExtendedPanId(new BigInteger("0017880100DC880B", 16));
        assertEquals("0017880100DC880B", address.toString());
    }

    @Test
    public void testConstructorArray() {
        ExtendedPanId address = new ExtendedPanId(new int[] { 0x0b, 0x88, 0xdc, 0x00, 0x01, 0x88, 0x17, 0x00 });
        assertEquals("0017880100DC880B", address.toString());
    }

    @Test(expected = InvalidParameterException.class)
    public void testConstructorArrayShort() {
        new ExtendedPanId(new int[] { 0x0b, 0x88, 0xdc, 0x00, 0x01, 0x88, 0x17 });
    }

    @Test
    public void testConstructorString() {
        ExtendedPanId address = new ExtendedPanId("17880100dc880b");
        assertEquals("0017880100DC880B", address.toString());

        address = new ExtendedPanId("8418260000D9959B");
        assertEquals("8418260000D9959B", address.toString());
    }

    @Test
    public void testValid() {
        ExtendedPanId address = new ExtendedPanId("17880100dc880b");
        assertTrue(address.isValid());

        address = new ExtendedPanId();
        assertFalse(address.isValid());

        address = new ExtendedPanId("0000000000000000");
        assertFalse(address.isValid());

        address = new ExtendedPanId("FFFFFFFFFFFFFFFF");
        assertFalse(address.isValid());
    }

    @Test
    public void testHash() {
        ExtendedPanId address1 = new ExtendedPanId("17880100dc880b");
        ExtendedPanId address2 = new ExtendedPanId("17880100dc880b");
        assertEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    public void testEquals() {
        ExtendedPanId address1 = new ExtendedPanId("17880100dc880b");
        ExtendedPanId address2 = new ExtendedPanId("17880100dc880b");
        assertTrue(address1.equals(address2));
    }

    @Test
    public void testToString() {
        ExtendedPanId address = new ExtendedPanId("17880100dc880b");
        assertEquals("0017880100DC880B", address.toString());
    }
}
