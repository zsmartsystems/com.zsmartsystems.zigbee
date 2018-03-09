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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeEndpointAddressTest {

    @Test
    public void testConstructorZdo() {
        ZigBeeEndpointAddress address = new ZigBeeEndpointAddress(25000);
        assertEquals(25000, address.getAddress());
        assertEquals(0, address.getEndpoint());
    }

    @Test
    public void testConstructor() {
        ZigBeeEndpointAddress address = new ZigBeeEndpointAddress(25000, 33);
        assertEquals(25000, address.getAddress());
        assertEquals(33, address.getEndpoint());
    }

    @Test
    public void testStringConstructor() {
        ZigBeeEndpointAddress address = new ZigBeeEndpointAddress("25000/33");
        assertEquals(25000, address.getAddress());
        assertEquals(33, address.getEndpoint());
    }

    @Test
    public void testStringConstructorError() {
        boolean exception = false;
        try {
            new ZigBeeEndpointAddress("");
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);

        exception = false;
        try {
            new ZigBeeEndpointAddress("111/22/33");
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void testStringConstructorZdo() {
        ZigBeeEndpointAddress address = new ZigBeeEndpointAddress("25000");
        assertEquals(25000, address.getAddress());
        assertEquals(0, address.getEndpoint());
    }

    @Test
    public void testEquals() {
        ZigBeeEndpointAddress address1 = new ZigBeeEndpointAddress("25000/33");
        ZigBeeEndpointAddress address2 = new ZigBeeEndpointAddress("25000/33");
        assertTrue(address1.equals(address2));
        ZigBeeEndpointAddress address3 = new ZigBeeEndpointAddress("25001/33");
        assertFalse(address1.equals(address3));
        ZigBeeEndpointAddress address4 = new ZigBeeEndpointAddress(25000, 33);
        assertTrue(address1.equals(address4));
    }

    @Test
    public void testCompareTo() {
        ZigBeeEndpointAddress address1 = new ZigBeeEndpointAddress("25000/33");
        ZigBeeEndpointAddress address2 = new ZigBeeEndpointAddress("25000/33");
        assertEquals(0, address1.compareTo(address2));
        ZigBeeEndpointAddress address3 = new ZigBeeEndpointAddress("25001/33");
        assertTrue(address1.compareTo(address3) > 0);
        ZigBeeEndpointAddress address4 = new ZigBeeEndpointAddress("24999/33");
        assertTrue(address1.compareTo(address4) < 0);
        ZigBeeEndpointAddress address5 = new ZigBeeEndpointAddress("25000/30");
        assertTrue(address1.compareTo(address5) < 0);
        ZigBeeEndpointAddress address6 = new ZigBeeEndpointAddress("25000/36");
        assertTrue(address1.compareTo(address6) > 0);
    }

    @Test
    public void testCompareToZdo() {
        ZigBeeEndpointAddress address1 = new ZigBeeEndpointAddress(25000);
        ZigBeeEndpointAddress address2 = new ZigBeeEndpointAddress(25000);
        assertEquals(0, address1.compareTo(address2));
        ZigBeeEndpointAddress address3 = new ZigBeeEndpointAddress("25001");
        assertEquals(1, address1.compareTo(address3));
    }

    @Test
    public void testIsGroup() {
        ZigBeeEndpointAddress address = new ZigBeeEndpointAddress("25000/33");
        assertFalse(address.isGroup());
    }

    @Test
    public void testToString() {
        String stringAddress = "25000/44";
        ZigBeeEndpointAddress address = new ZigBeeEndpointAddress(stringAddress);
        assertEquals(stringAddress, address.toString());
    }

    @Test
    public void testHashRemove() {
        ZigBeeEndpointAddress address1 = new ZigBeeEndpointAddress("25000/33");
        ZigBeeEndpointAddress address2 = new ZigBeeEndpointAddress("25000/33");
        final Set<ZigBeeEndpointAddress> set = new HashSet<ZigBeeEndpointAddress>();
        set.add(address1);
        set.add(address2);

        assertEquals(1, set.size());
        assertTrue(set.contains(address1));
        assertTrue(set.contains(address2));
        set.remove(address2);
        assertEquals(0, set.size());
    }
}