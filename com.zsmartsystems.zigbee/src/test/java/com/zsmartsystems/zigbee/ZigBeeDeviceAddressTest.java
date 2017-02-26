package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    }

    @Test
    public void testCompareTo() {
        ZigBeeDeviceAddress address1 = new ZigBeeDeviceAddress("25000/33");
        ZigBeeDeviceAddress address2 = new ZigBeeDeviceAddress("25000/33");
        assertEquals(0, address1.compareTo(address2));
        ZigBeeDeviceAddress address3 = new ZigBeeDeviceAddress("25001/33");
        assertEquals(1, address1.compareTo(address3));
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
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress("25000/33");
        assertEquals("61A8/33", address.toString());
    }
}
