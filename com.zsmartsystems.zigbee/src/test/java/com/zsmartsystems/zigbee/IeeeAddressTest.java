/**
 * Copyright (c) 2016-2018 by the respective copyright holders.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class IeeeAddressTest {

    @Test
    public void testConstructorArray() {
        IeeeAddress address = new IeeeAddress(new int[] { 0x0b, 0x88, 0xdc, 0x00, 0x01, 0x88, 0x17, 0x00 });
        assertEquals("0017880100DC880B", address.toString());
    }

    @Test
    public void testConstructorString() {
        IeeeAddress address = new IeeeAddress("17880100dc880b");
        assertEquals("0017880100DC880B", address.toString());

        address = new IeeeAddress("8418260000D9959B");
        assertEquals("8418260000D9959B", address.toString());
    }

    @Test
    public void testHash() {
        IeeeAddress address1 = new IeeeAddress("17880100dc880b");
        IeeeAddress address2 = new IeeeAddress("17880100dc880b");
        assertEquals(address1.hashCode(), address2.hashCode());
    }

    @Test
    public void testEquals() {
        IeeeAddress address1 = new IeeeAddress("17880100dc880b");
        IeeeAddress address2 = new IeeeAddress("17880100dc880b");
        assertTrue(address1.equals(address2));
    }

    @Test
    public void testToString() {
        IeeeAddress address = new IeeeAddress("17880100dc880b");
        assertEquals("0017880100DC880B", address.toString());
    }

    @Test
    public void testCompareTo() {
        IeeeAddress address1 = new IeeeAddress("17880100dc880b");

        IeeeAddress address2 = new IeeeAddress("17880100dc880b");
        assertEquals(0, address1.compareTo(address2));

        address2 = new IeeeAddress("17880100dc880c");
        assertEquals(-1, address1.compareTo(address2));

        address2 = new IeeeAddress("17880100dc880a");
        assertEquals(1, address1.compareTo(address2));

        address2 = new IeeeAddress("27880100dc880c");
        assertEquals(-1, address1.compareTo(address2));

        address2 = new IeeeAddress("17880120dc880c");
        assertEquals(-1, address1.compareTo(address2));

        address2 = new IeeeAddress("16880100dc880b");
        assertEquals(1, address1.compareTo(address2));
    }
}
