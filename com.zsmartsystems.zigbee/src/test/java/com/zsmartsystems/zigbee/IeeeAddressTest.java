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
    public void testConstructorLong() {
        IeeeAddress address = new IeeeAddress(0x17880100dc880bl);
        assertEquals(0x17880100dc880bl, address.getLong());
    }

    @Test
    public void testConstructorString() {
        IeeeAddress address = new IeeeAddress("17880100dc880b");
        assertEquals(0x17880100dc880bl, address.getLong());
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
}
