package com.zsmartsystems.zigbee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.zsmartsystems.zigbee.ZigBeeDeviceAddress;

/**
 *
 * @author Chris Jackson
 *
 */
public class ZigBeeDeviceAddressTest {

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
    public void testEquals() {
        ZigBeeDeviceAddress address1 = new ZigBeeDeviceAddress("25000/33");
        ZigBeeDeviceAddress address2 = new ZigBeeDeviceAddress("25000/33");
        assertTrue(address1.equals(address2));
    }

    @Test
    public void testIsGroup() {
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress("25000/33");
        assertFalse(address.isGroup());
    }

    @Test
    public void testToString() {
        ZigBeeDeviceAddress address = new ZigBeeDeviceAddress("25000/33");
        assertEquals("25000/33", address.toString());
    }
}
