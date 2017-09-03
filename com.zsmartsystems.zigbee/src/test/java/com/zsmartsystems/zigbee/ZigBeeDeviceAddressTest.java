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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDeviceAddressTest {

    @Test
    public void testConstructorZdo() {
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress(25000);
        assertEquals(25000, address.getAddress());
        assertEquals(0, address.getEndpoint());
    }

    @Test
    public void testConstructor() {
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress(25000, 33);
        assertEquals(25000, address.getAddress());
        assertEquals(33, address.getEndpoint());
    }

    @Test
    public void testStringConstructor() {
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress("25000/33");
        assertEquals(25000, address.getAddress());
        assertEquals(33, address.getEndpoint());
    }

    @Test
    public void testStringConstructorError() {
        boolean exception = false;
        try {
            new ZigBeeDeviceAddress("");
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);

        exception = false;
        try {
            new ZigBeeDeviceAddress("111/22/33");
        } catch (IllegalArgumentException e) {
            exception = true;
        }
        assertTrue(exception);
    }

    @Test
    public void testStringConstructorZdo() {
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress("25000");
        assertEquals(25000, address.getAddress());
        assertEquals(0, address.getEndpoint());
    }

    @Test
    public void testEquals() {
        ZigBeeDeviceAddress address1 = new ZigBeeDeviceAddress("25000/33");
        ZigBeeDeviceAddress address2 = new ZigBeeDeviceAddress("25000/33");
        assertTrue(address1.equals(address2));
        ZigBeeDeviceAddress address3 = new ZigBeeDeviceAddress("25001/33");
        assertFalse(address1.equals(address3));
        ZigBeeDeviceAddress address4 = new ZigBeeDeviceAddress(25000, 33);
        assertTrue(address1.equals(address4));
    }

    @Test
    public void testCompareTo() {
        ZigBeeDeviceAddress address1 = new ZigBeeDeviceAddress("25000/33");
        ZigBeeDeviceAddress address2 = new ZigBeeDeviceAddress("25000/33");
        assertEquals(0, address1.compareTo(address2));
        ZigBeeDeviceAddress address3 = new ZigBeeDeviceAddress("25001/33");
        assertTrue(address1.compareTo(address3) > 0);
        ZigBeeDeviceAddress address4 = new ZigBeeDeviceAddress("24999/33");
        assertTrue(address1.compareTo(address4) < 0);
        ZigBeeDeviceAddress address5 = new ZigBeeDeviceAddress("25000/30");
        assertTrue(address1.compareTo(address5) < 0);
        ZigBeeDeviceAddress address6 = new ZigBeeDeviceAddress("25000/36");
        assertTrue(address1.compareTo(address6) > 0);
    }

    @Test
    public void testCompareToZdo() {
        ZigBeeDeviceAddress address1 = new ZigBeeDeviceAddress(25000);
        ZigBeeDeviceAddress address2 = new ZigBeeDeviceAddress(25000);
        assertEquals(0, address1.compareTo(address2));
        ZigBeeDeviceAddress address3 = new ZigBeeDeviceAddress("25001");
        assertEquals(1, address1.compareTo(address3));
    }

    @Test
    public void testIsGroup() {
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress("25000/33");
        assertFalse(address.isGroup());
    }

    @Test
    public void testToString() {
        String stringAddress = "25000/44";
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress(stringAddress);
        assertEquals(stringAddress, address.toString());
    }

    @Test
    public void testHashRemove() {
        ZigBeeDeviceAddress address1 = new ZigBeeDeviceAddress("25000/33");
        ZigBeeDeviceAddress address2 = new ZigBeeDeviceAddress("25000/33");
        final Set<ZigBeeDeviceAddress> set = new HashSet<ZigBeeDeviceAddress>();
        set.add(address1);
        set.add(address2);

        assertEquals(1, set.size());
        assertTrue(set.contains(address1));
        assertTrue(set.contains(address2));
        set.remove(address2);
        assertEquals(0, set.size());
    }
}
